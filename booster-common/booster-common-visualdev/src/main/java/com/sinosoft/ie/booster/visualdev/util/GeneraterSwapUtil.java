package com.sinosoft.ie.booster.visualdev.util;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDeptService;
import com.sinosoft.ie.booster.admin.api.feign.RemotePositionService;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.admin.api.model.position.PositionInfoVO;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeneraterSwapUtil {

	/**
	 * 日期时间戳字符串转换
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String DateSwap(String date, String format) {
		if (StrUtil.isNotEmpty(date)) {
			DateTimeFormatter ftf = DateTimeFormatter.ofPattern(format);
			if (date.contains(",")) {
				String[] dates = date.split(",");
				long time1 = Long.parseLong(dates[0]);
				long time2 = Long.parseLong(dates[1]);
				String value1 = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time1), ZoneId.systemDefault()));
				String value2 = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time2), ZoneId.systemDefault()));
				return value1 + "至" + value2;
			}
			long time = Long.parseLong(date);
			return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
		}
		return date;
	}

	/**
	 * 行政区划转换
	 *
	 * @param data
	 * @return
	 */
	public List<String> provinceData(String data) {
		if (StrUtil.isNotEmpty(data)) {
			String[] strs = data.split(",");
			return new ArrayList<>(Arrays.asList(strs));
		}
		return new ArrayList<>();
	}

	/**
	 * 公司部门id转名称
	 *
	 * @param id
	 * @return
	 */
	public String comSelectValue(String id) {
		if (StrUtil.isNotEmpty(id)) {
			RemoteDeptService organizeService = SpringContextHolder.getBean(RemoteDeptService.class);
			List<SysDeptEntity> orgMapList = organizeService.getList().getData();
			for (SysDeptEntity organizeEntity : orgMapList) {
				if (id.equals(String.valueOf(organizeEntity.getDeptId()))) {
					return organizeEntity.getName();
				}
			}
			return id;
		}
		return id;
	}

	/**
	 * 岗位id转名称
	 *
	 * @param id
	 * @return
	 */
	public static String posSelectValue(String id) {
		if (StrUtil.isNotEmpty(id)) {
			RemotePositionService positionService = SpringContextHolder.getBean(RemotePositionService.class);
			R<PositionInfoVO> positionInfoVO = positionService.getInfo(id);
			if (positionInfoVO.getData() != null) {
				return positionInfoVO.getData().getFullName();
			}
			return id;
		}
		return id;
	}

	/**
	 * 用户id转名称
	 *
	 * @param id
	 * @return
	 */
	public static String userSelectValue(String id) {
		if (StrUtil.isNotEmpty(id)) {
			RemoteUserService userService = SpringContextHolder.getBean(RemoteUserService.class);
			UserVO userVO = userService.getUserById(Long.valueOf(id)).getData();
			if (userVO != null) {
				return userVO.getUsername();
			}
			return id;
		}
		return id;
	}

	/**
	 * 用户id转名称(多选)
	 *
	 * @param ids
	 * @return
	 */
	public String userSelectValues(String ids) {
		if (StrUtil.isNotEmpty(ids)) {
			RemoteUserService userService = SpringContextHolder.getBean(RemoteUserService.class);
			List<String> userNameList = userService.getUserByIds(ids).getData()
					.stream().map(SysUserEntity::getUsername).collect(Collectors.toList());
			return String.join("-", userNameList);
		}
		return ids;
	}
}
