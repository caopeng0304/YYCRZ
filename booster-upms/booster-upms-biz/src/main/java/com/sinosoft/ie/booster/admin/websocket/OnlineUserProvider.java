package com.sinosoft.ie.booster.admin.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
public class OnlineUserProvider {

	/**
	 * 在线用户
	 */
	private static final List<OnlineUserModel> ONLINE_USER_LIST = new ArrayList<>();

	public static List<OnlineUserModel> getOnlineUserList() {
		synchronized (ONLINE_USER_LIST) {
			return ONLINE_USER_LIST;
		}
	}

	public static void addModel(OnlineUserModel model) {
		synchronized (ONLINE_USER_LIST) {
			Map<String, List<OnlineUserModel>> groupByUserId = ONLINE_USER_LIST.stream().collect(Collectors.groupingBy(OnlineUserModel::getUserId));
			if (groupByUserId.containsKey(model.getUserId())) {
				for (OnlineUserModel currentUser : groupByUserId.get(model.getUserId())) {
					if (currentUser.getIsMobileDevice().equals(model.getIsMobileDevice())) {
						ONLINE_USER_LIST.remove(currentUser);
					}
				}
			}
			ONLINE_USER_LIST.add(model);
		}
	}

	public static void removeModel(OnlineUserModel model) {
		synchronized (ONLINE_USER_LIST) {
			ONLINE_USER_LIST.remove(model);
		}
	}
}
