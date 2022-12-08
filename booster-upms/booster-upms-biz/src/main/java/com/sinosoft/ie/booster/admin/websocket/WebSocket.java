package com.sinosoft.ie.booster.admin.websocket;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.ie.booster.admin.api.entity.BaseImContentEntity;
import com.sinosoft.ie.booster.admin.api.model.message.IMUnreadNumModel;
import com.sinosoft.ie.booster.admin.service.BaseImContentService;
import com.sinosoft.ie.booster.admin.service.BaseMessageService;
import com.sinosoft.ie.booster.common.core.model.PageModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.*;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 消息聊天
 *
 * @author booster开发平台组
 * @since 2019年9月26日 上午9:18
 */
@Slf4j
@Component
@ServerEndpoint(value = "/Message/websocket")
@Scope("prototype")
public class WebSocket {
	private RemoteTokenServices tokenService;
	private BaseImContentService imContentService;
	private BaseMessageService messageService;
	private ConfigValueUtil configValueUtil;
	private RedisUtil redisUtil;
	private CacheKeyUtil cacheKeyUtil;


	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session) {
		log.info("连接上来:" + session.getId());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) {
		OnlineUserModel user = OnlineUserProvider.getOnlineUserList().stream().anyMatch(t -> t.getConnectionId().equals(session.getId())) ? OnlineUserProvider.getOnlineUserList().stream().filter(t -> t.getConnectionId().equals(session.getId())).findFirst().get() : null;
		if (user != null) {
			String userId = user.getUserId();
			OnlineUserProvider.removeModel(user);
			//通知所有在线，有用户离线
			for (OnlineUserModel item : OnlineUserProvider.getOnlineUserList().stream().filter(t -> !userId.equals(t.getUserId())).collect(Collectors.toList())) {
				if (!item.getUserId().equals(userId)) {
					JSONObject map = new JSONObject();
					map.put("method", WebSocketCommand.OFFLINE.getMethod());
					map.put("userId", userId);
					synchronized (session) {
						try {
							item.getWebSocket().getBasicRemote().sendText(map.toJSONString());
						} catch (Exception e) {
							log.error("通知用户离线发生错误：" + e.getMessage());
						}
					}
				}
			}
			log.info("调用onclose,关闭的用户为:" + userId);
		}
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		this.init();
		log.info("消息内容:" + message);
		JSONObject receivedMessage = JSONObject.parseObject(message);
		// 验证令牌信息
		String receivedToken = receivedMessage.getString("token");
		OAuth2Authentication auth2Authentication = tokenService.loadAuthentication(receivedToken.split(" ")[1]);
		if (ObjectUtil.isNotNull(auth2Authentication)) {
			SecurityContextHolder.getContext().setAuthentication(auth2Authentication);
		} else {
			return;
		}
		String userId = String.valueOf(SecurityUtils.getUser().getUsername());
		List<OnlineUserModel> onlineUserList = OnlineUserProvider.getOnlineUserList().stream().filter(q -> !q.getUserId().equals(userId)).collect(Collectors.toList());
		List<String> onlineUsers = onlineUserList.stream().map(OnlineUserModel::getUserId).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
		WebSocketCommand receivedMethod = WebSocketCommand.parse(receivedMessage.getString("method"));
		switch (Objects.requireNonNull(receivedMethod)) {
			case ON_CONNECTION:
				//建立连接
				System.out.println("开启新连接");
				//app-true, PC-false
				Boolean isMobileDevice = receivedMessage.getBoolean("mobileDevice");
				OnlineUserModel model = new OnlineUserModel();
				model.setConnectionId(session.getId());
				model.setUserId(userId);
				model.setIsMobileDevice(isMobileDevice);
				model.setWebSocket(session);
				OnlineUserProvider.addModel(model);
				//反馈信息给登录者
				List<IMUnreadNumModel> unreadNums = imContentService.getUnreadList(userId);
				int unreadNoticeCount = messageService.getUnreadNoticeCount(userId);
				int unreadMessageCount = messageService.getUnreadMessageCount(userId);
				String noticeDefaultText = messageService.getInfoDefault(1).getTitle();
				String messageDefaultText = messageService.getInfoDefault(2).getTitle();
				JSONObject object = new JSONObject();
				object.put("method", WebSocketCommand.INIT_MESSAGE.getMethod());
				object.put("onlineUsers", onlineUsers);
				object.put("unreadNums", JsonUtil.listToJsonfield(unreadNums));
				object.put("unreadNoticeCount", unreadNoticeCount);
				object.put("noticeDefaultText", noticeDefaultText);
				object.put("unreadMessageCount", unreadMessageCount);
				object.put("messageDefaultText", messageDefaultText);
				//收到用户显示消息
				session.getAsyncRemote().sendText(object.toJSONString());
				//通知所有在线用户，有用户在线
				for (OnlineUserModel item : onlineUserList) {
					if (!item.getUserId().equals(userId)) {
						JSONObject map = new JSONObject();
						map.put("method", WebSocketCommand.ONLINE.getMethod());
						map.put("userId", userId);
						item.getWebSocket().getAsyncRemote().sendText(map.toJSONString());
					}
				}
				break;
			case PING:
				JSONObject heartbeat = new JSONObject();
				heartbeat.put("method", WebSocketCommand.PONG.getMethod());
				heartbeat.put("onlineUsers", onlineUsers);
				session.getAsyncRemote().sendText(heartbeat.toJSONString());
				break;
			case SEND_MESSAGE:
				//发送消息
				String toUserId = receivedMessage.getString("toUserId");
				//text/voice/image
				String messageType = receivedMessage.getString("messageType");
				String messageContent = receivedMessage.getString("messageContent");
				String fileName = "";
				String directoryPath = configValueUtil.getImContentFilePath();
				if (!"text".equals(messageType)) {
					String type = ("voice".equals(messageType) ? ".mp3" : ".png");
					fileName = RandomUtil.uuId() + type;
					String filePath = directoryPath + fileName;
					String fileBase64 = JSONObject.parseObject(messageContent).getString("base64").replaceAll("%", "").replaceAll(",", "").replaceAll(" ", "+");
					Base64Util.base64ToFile(fileBase64, filePath);
					if ("image".equals(messageType)) {
						makeThumbnail(filePath, (directoryPath + "T" + fileName), 300, 300);
					}
				}
				List<OnlineUserModel> user = OnlineUserProvider.getOnlineUserList().stream().filter(q -> q.getUserId().equals(userId)).collect(Collectors.toList());
				OnlineUserModel onlineUser = user.size() > 0 ? user.get(0) : null;
				List<OnlineUserModel> toUser = OnlineUserProvider.getOnlineUserList().stream().filter(q -> q.getUserId().equals(toUserId)).collect(Collectors.toList());
				if (user.size() != 0) {
					//saveMessage
					if ("text".equals(messageType)) {
						imContentService.sendMessage(onlineUser.getUserId(), toUserId, messageContent, messageType);
					} else if ("image".equals(messageType)) {
						JSONObject image = new JSONObject();
						image.put("path", fileName);
						image.put("width", JSONObject.parseObject(messageContent).getString("width"));
						image.put("height", JSONObject.parseObject(messageContent).getString("height"));
						imContentService.sendMessage(onlineUser.getUserId(), toUserId, image.toJSONString(), messageType);
					} else if ("voice".equals(messageType)) {
						JSONObject voice = new JSONObject();
						voice.put("path", fileName);
						voice.put("length", JSONObject.parseObject(messageContent).getString("length"));
						imContentService.sendMessage(onlineUser.getUserId(), toUserId, voice.toJSONString(), messageType);
					}
					for (OnlineUserModel onlineToUser : user) {
						//sendMessage
						JSONObject objectMessage = new JSONObject();
						objectMessage.put("method", WebSocketCommand.SEND_MESSAGE.getMethod());
						objectMessage.put("UserId", onlineToUser.getUserId());
						objectMessage.put("toUserId", toUserId);
						if ("text".equals(messageType)) {
							objectMessage.put("messageType", messageType);
							objectMessage.put("toMessage", messageContent);
							objectMessage.put("dateTime", DateUtil.getNow());
							onlineToUser.getWebSocket().getAsyncRemote().sendText(objectMessage.toJSONString());
						} else if ("image".equals(messageType)) {
							JSONObject image = new JSONObject();
							image.put("path", fileName);
							image.put("width", JSONObject.parseObject(messageContent).getString("width"));
							image.put("height", JSONObject.parseObject(messageContent).getString("height"));
							objectMessage.put("messageType", messageType);
							objectMessage.put("toMessage", image);
							objectMessage.put("dateTime", DateUtil.getNow());
							onlineToUser.getWebSocket().getAsyncRemote().sendText(objectMessage.toJSONString());
						} else if ("voice".equals(messageType)) {
							JSONObject voice = new JSONObject();
							voice.put("path", fileName);
							voice.put("length", JSONObject.parseObject(messageContent).getString("length"));
							objectMessage.put("messageType", messageType);
							objectMessage.put("toMessage", voice);
							objectMessage.put("dateTime", DateUtil.getNow());
							onlineToUser.getWebSocket().getAsyncRemote().sendText(objectMessage.toJSONString());
						}
					}
				}
				JSONObject receive = new JSONObject();
				receive.put("method", WebSocketCommand.RECEIVE_MESSAGE.getMethod());
				assert onlineUser != null;
				receive.put("formUserId", onlineUser.getUserId());
				if (toUser.size() != 0) {
					for (OnlineUserModel onlineToUser : toUser) {
						if ("text".equals(messageType)) {
							receive.put("messageType", messageType);
							receive.put("formMessage", messageContent);
							receive.put("dateTime", DateUtil.getNow());
							synchronized (session) {
								onlineToUser.getWebSocket().getAsyncRemote().sendText(receive.toJSONString());
							}
						} else if ("image".equals(messageType)) {
							JSONObject image = new JSONObject();
							image.put("path", fileName);
							image.put("width", JSONObject.parseObject(messageContent).getString("width"));
							image.put("height", JSONObject.parseObject(messageContent).getString("height"));
							receive.put("messageType", messageType);
							receive.put("formMessage", image);
							receive.put("dateTime", DateUtil.getNow());
							synchronized (session) {
								onlineToUser.getWebSocket().getAsyncRemote().sendText(receive.toJSONString());
							}
						} else if ("voice".equals(messageType)) {
							JSONObject voice = new JSONObject();
							voice.put("path", fileName);
							voice.put("length", JSONObject.parseObject(messageContent).getString("length"));
							receive.put("messageType", messageType);
							receive.put("formMessage", voice);
							receive.put("dateTime", DateUtil.getNow());
							synchronized (session) {
								onlineToUser.getWebSocket().getAsyncRemote().sendText(receive.toJSONString());
							}
						}
					}
				}
				break;
			case UPDATE_READ_MESSAGE:
				//更新已读
				String formUserId = receivedMessage.getString("formUserId");
				onlineUser = OnlineUserProvider.getOnlineUserList().stream().filter(q -> q.getConnectionId().equals(session.getId())).findFirst().orElse(new OnlineUserModel());
				if (onlineUser != null) {
					imContentService.readMessage(formUserId, onlineUser.getUserId());
				}
				break;
			case MESSAGE_LIST:
				//获取消息列表
				String sendUserId = receivedMessage.getString("toUserId");
				String receiveUserId = receivedMessage.getString("formUserId");
				PageModel pageModel = new PageModel();
				pageModel.setPage(receivedMessage.getInteger("currentPage"));
				pageModel.setRows(receivedMessage.getInteger("pageSize"));
				pageModel.setSord(receivedMessage.getString("sord"));
				List<BaseImContentEntity> data = imContentService.getMessageList(sendUserId, receiveUserId, pageModel).stream().sorted(Comparator.comparing(BaseImContentEntity::getSendTime)).collect(Collectors.toList());
				JSONObject objectMessage = new JSONObject();
				objectMessage.put("method", WebSocketCommand.MESSAGE_LIST.getMethod());
				objectMessage.put("list", JsonUtil.getListToJsonArray(data));
				JSONObject pagination = new JSONObject();
				pagination.put("total", pageModel.getRecords());
				pagination.put("currentPage", pageModel.getPage());
				pagination.put("pageSize", receivedMessage.getInteger("pageSize"));
				objectMessage.put("pagination", pagination);
				session.getAsyncRemote().sendText(objectMessage.toJSONString());
				break;
			default:
				break;
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		OnlineUserModel user = OnlineUserProvider.getOnlineUserList().stream().anyMatch(t -> t.getConnectionId().equals(session.getId())) ? OnlineUserProvider.getOnlineUserList().stream().filter(t -> t.getConnectionId().equals(session.getId())).findFirst().get() : null;
		if (user != null) {
			log.error("调用onError,用户：" + user.getUserId());
		}
		try {
			onClose(session);
		} catch (Exception e) {
			log.error("发生error,调用onclose失败，session为：" + session);
		}
		if (error.getMessage() != null) {
			error.printStackTrace();
		}
	}

	/**
	 * 初始化
	 */
	private void init() {
		tokenService = SpringContextHolder.getBean(RemoteTokenServices.class);
		messageService = SpringContextHolder.getBean(BaseMessageService.class);
		imContentService = SpringContextHolder.getBean(BaseImContentService.class);
		configValueUtil = SpringContextHolder.getBean(ConfigValueUtil.class);
		redisUtil = SpringContextHolder.getBean(RedisUtil.class);
		cacheKeyUtil = SpringContextHolder.getBean(CacheKeyUtil.class);
	}

	/**
	 * 缩略图
	 *
	 * @param imgPathOld
	 * @param imgPathNew
	 * @param width
	 * @param height
	 */
	private static void makeThumbnail(String imgPathOld, String imgPathNew, int width, int height) {
		try {
			if (FileUtil.fileIsFile(imgPathOld)) {
				Thumbnails.of(imgPathOld)
						.size(width, height)
						.toFile(imgPathNew);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
