package com.sinosoft.ie.booster.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.admin.api.entity.BaseImContentEntity;
import com.sinosoft.ie.booster.admin.api.model.message.IMUnreadNumModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 聊天内容
 *
 * @author booster code generator
 * @since 2021-09-18
 */
@Mapper
public interface BaseImContentMapper extends BaseMapper<BaseImContentEntity> {

	List<IMUnreadNumModel> getUnreadList(@Param("receiveUserId") String receiveUserId);

	List<IMUnreadNumModel> getUnreadLists(@Param("receiveUserId") String receiveUserId);

	int readMessage(@Param("map") Map<String, String> map);
}
