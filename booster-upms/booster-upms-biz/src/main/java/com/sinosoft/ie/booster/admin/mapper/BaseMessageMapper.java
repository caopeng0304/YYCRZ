package com.sinosoft.ie.booster.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.admin.api.entity.BaseMessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 消息实例
 *
 * @author booster code generator
 * @since 2021-09-18
 */
@Mapper
public interface BaseMessageMapper extends BaseMapper<BaseMessageEntity> {

	List<BaseMessageEntity> getMessageList(@Param("map") Map<String, String> map);

	int getUnreadNoticeCount(@Param("userId") String userId);

	int getUnreadMessageCount(@Param("userId") String userId);

	List<BaseMessageEntity> getInfoDefault(@Param("type") int type);
}
