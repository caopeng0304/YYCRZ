package com.sinosoft.ie.booster.yypass.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserDTO;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.entity.PassLogEntity;
import com.sinosoft.ie.booster.yypass.service.PassBasicInfoService;
import com.sinosoft.ie.booster.yypass.service.PassLogService;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class SaticScheduController {

	@Autowired
	PassBasicInfoService passBasicInfoService;
	@Autowired
	PassLogService passLogService;

	@Value("${queryByUserGrooupCc}")
	private String queryByUserGrooupCc;
	@Value("${deleteByUserGrooup}")
	private String deleteByUserGrooup;
	@Value("${queryGroupId}")
	private String queryGroupId;
	@Value("${addPerson}")
	private String addPerson;
	@Value("${queryPersonId}")
	private String queryPersonId;
	@Value("${Config_host}")
	private String Config_host;
	@Value("${Config_appKey}")
	private String Config_appKey;
	@Value("${Config_appSecret}")
	private String Config_appSecret;
	@Autowired
	RemoteUserService remoteUserService;

	@Scheduled(cron = "0 0 1 * * ? ")//；正式用 每天23点执行一次：0 0 23 * * ?
	private void configureTasks() throws Exception {
		Date date = new Date();
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("is_delete","1");
		List<PassBasicInfoEntity> list = passBasicInfoService.list(queryWrapper);
		if (list != null && list.size() > 0){
			for (PassBasicInfoEntity entity : list){
				Date endTime = entity.getEndTime();
				if (date.getTime() > endTime.getTime()){ // 关闭权限
					Map<String, String> path = new HashMap<String, String>(2) {
						{
							put("https://", queryByUserGrooupCc);//根据现场环境部署确认是http还是https
						}
					};
					JSONObject jsonBody = new JSONObject();
					jsonBody.put("jobNo",entity.getIdCard());
					jsonBody.put("pageNo",1);
					jsonBody.put("pageSize",1000);
					String body = jsonBody.toString();
					ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
					String resultl = null;
					try {
						resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, body, null, null, "application/json", null);
					} catch (Exception e) {
						e.printStackTrace();
					}
					net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
					net.sf.json.JSONObject data = jsonObject1.getJSONObject("data");
					List<net.sf.json.JSONObject> groupList = (List<net.sf.json.JSONObject>) data.get("personGroupList");
					//删除分组
					for (net.sf.json.JSONObject jsonObject : groupList) {
						System.out.println(entity.getIdCard() + jsonObject.get("personGroupId")+"???????????????????删除????????????????????????");
						path = new HashMap<String, String>(2) {
							{
								put("https://", deleteByUserGrooup);//根据现场环境部署确认是http还是https
							}
						};
						String[] jobNos = new String[]{entity.getIdCard()};
						jsonBody = new JSONObject();
						jsonBody.put("personGroupId",jsonObject.get("personGroupId"));
						jsonBody.put("jobNos",jobNos);
						jsonBody.put("pageSize",1000);
						body = jsonBody.toString();
						artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
						resultl = null;
						try {
							resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, body, null, null, "application/json", null);
							System.out.println("resultl + " + resultl);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					entity.setIsInout("1");
					passBasicInfoService.update(entity.getId(),entity);
					//燕园出入证号
					String userName = entity.getIdCard();
                    //修改燕园出入证人员状态（禁用）
					remoteUserService.lockUser(userName, CommonConstants.STATUS_LOCK);
				}
			}
		}
	}



	@Scheduled(cron = "0 0 2 * * ? ")//；注销后一年，删除数据
	private void configureTasksDelete() throws Exception {
		Date date = new Date();
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("person_state","20");
		List<PassBasicInfoEntity> list = passBasicInfoService.list(queryWrapper);
		if (list != null && list.size() > 0){
			List<String> deleteList = new ArrayList<>();
			for (PassBasicInfoEntity entity : list){
				Date logoutTime = entity.getLogoutTime();
				if (logoutTime != null){
					Date year = getYear(logoutTime);
					if (date.getTime() > year.getTime()){
						if (StringUtils.isNotEmpty(entity.getDoorPersonId())){
							deleteList.add(entity.getDoorPersonId());
						}
						// 删除本地组织数据
						if (StringUtils.isNotEmpty(entity.getIdCard())){
							UserDTO userDTO = new UserDTO();
							userDTO.setUsername(entity.getIdCard());
							remoteUserService.syndeleteUser(userDTO);
						}
						entity.setIsDelete("0");
						passBasicInfoService.update(entity.getId(),entity);
						// 删除本地记录
						deletePassLog(entity.getId());

					}
				}
				// 删除海康数据
				if (deleteList != null && deleteList.size() > 0){   // 删除海康数据
					deleteToDoorpost(deleteList);
				}

			}
		}
	}

	public void deletePassLog(String passBasicInfoId){
		QueryWrapper<PassLogEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().and(a->a.eq(PassLogEntity::getPassBasicInfoId,passBasicInfoId));
		List<PassLogEntity> list = passLogService.list(queryWrapper);
		if (list != null && list.size() > 0){
			for (PassLogEntity passLogEntity :list){
				passLogEntity.setIsDelete("0");
				passLogService.update(passLogEntity.getId(),passLogEntity);
			}
		}
	}

	public Boolean deleteToDoorpost(List<String> list){
		try{

			JSONObject content = new JSONObject();
			content.put("personIds",list);
			System.out.println(content.toString());
			Map<String, String> path = new HashMap<String, String>(2) {
				{
					put("https://", "/artemis/api/resource/v1/person/batch/delete");//根据现场环境部署确认是http还是https
				}
			};
			ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
			String resultl = null;

			resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, content.toString(), null, null, "application/json", null);

			System.out.println("调用门闸接口删除人员信息："+resultl);
			net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
			String code = jsonObject1.getString("code");
			if ("0".equals(code)){
				return true;
			}
			return true;
		}catch (Exception e){
			System.out.println("用门闸接口删除人员信息异常。"+e.getMessage());
			return false;
		}
	}


	public static Date getYear(Date time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		int day=calendar.get(Calendar.DATE);
		// 后一天为 +1   前一天 为-1
		calendar.set(Calendar.DATE,day+365);
        Date d = new Date();
        d.setTime(calendar.getTime().getTime());
        return d;
	}

	public static void main(String args[]){
		Date d = getYear(new Date());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(d));
	}







}

