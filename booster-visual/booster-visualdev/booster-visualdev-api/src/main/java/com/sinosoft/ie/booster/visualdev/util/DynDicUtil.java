package com.sinosoft.ie.booster.visualdev.util;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDictService;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.visualdev.feign.DataInterFaceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author booster开发平台组
 * @since 2021/3/16
 */
@Component
public class DynDicUtil {

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private CacheKeyUtil cacheKeyUtil;
	@Autowired
	private DataInterFaceApi dataInterfaceService;
	@Autowired
	private RemoteDictService dictionaryDataService;


	public final String regEx = "[\\[\\]\"]";

	/**
	 * 获取数据字典数据
	 *
	 * @param dictionaryType
	 * @param field
	 * @return
	 */
	public String getDicName(String dictionaryType, String field) {
		//去除中括号以及双引号
		field = field.replaceAll(regEx, "" );
		String[] fields = field.split("," );
		StringBuilder fieldsValue = new StringBuilder();
		List<SysDictItemEntity> dictItemEntityList = dictionaryDataService.getDictByType(dictionaryType).getData();
		Map<String, List<SysDictItemEntity>> dictItemEntityMap = dictItemEntityList.stream().collect(Collectors.groupingBy(SysDictItemEntity::getValue));
		for (String item : fields) {
			if (dictItemEntityMap.containsKey(item)) {
				SysDictItemEntity dictItemEntity = dictItemEntityMap.get(item).get(0);
				fieldsValue.append(dictItemEntity.getLabel()).append("/");
			}
		}
		return StrUtil.isEmpty(fieldsValue) ? fieldsValue.toString() : fieldsValue.substring(0, fieldsValue.length() - 1);
	}

	/**
	 * 获取远端数据
	 *
	 * @param urlId
	 * @param label
	 * @param value
	 * @param feildValue
	 * @return
	 * @throws IOException
	 */
	public String getDynName(String urlId, String label, String value, String feildValue) throws IOException {
		if (redisUtil.exists(cacheKeyUtil.getDynamic() + feildValue)) {
			return redisUtil.getString(cacheKeyUtil.getDynamic() + feildValue).toString();
		}
		//去除中括号以及双引号
		feildValue = feildValue.replaceAll(regEx, "" );
		//获取远端数据
		Object object = dataInterfaceService.infoToId(urlId);
		Map<String, Object> dynamicMap = JsonUtil.entityToMap(object);
		if (dynamicMap.get("data" ) != null) {
			List<Map<String, Object>> dataList = JsonUtil.getJsonToListMap(dynamicMap.get("data" ).toString());
			//判断是否多选
			String[] feildValues = feildValue.split("," );
			if (feildValues.length > 1) {
				//转换的真实值
				StringBuilder feildVa = new StringBuilder();
				for (String feild : feildValues) {
					for (Map<String, Object> data : dataList) {
						if (String.valueOf(data.get(value)).equals(feild)) {
							feildVa.append(data.get(label) + "/" );
						}
					}
				}
				String finalValue = feildVa.substring(0, feildVa.length() - 1);
				redisUtil = SpringContextHolder.getBean(RedisUtil.class);
				redisUtil.insert(cacheKeyUtil.getDynamic() + feildValue, finalValue, 20);
				return finalValue;
			}
			for (Map<String, Object> data : dataList) {
				if (feildValue.equals(value)) {
					redisUtil = SpringContextHolder.getBean(RedisUtil.class);
					redisUtil.insert(cacheKeyUtil.getDynamic() + feildValue, data.get(label).toString(), 20);
					return data.get(label).toString();
				}
				return feildValue;
			}
		}
		return feildValue;
	}
}
