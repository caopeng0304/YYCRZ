package com.sinosoft.ie.booster.mqroute.cache;

import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;
import com.sinosoft.ie.booster.mqroute.entity.TopicEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 缓存数据类
 *
 * @author gt
 */
public class MqCache {

	//标识符，默认是0，如果消费者有更新则+1，消费者获取信息时比对不一致则更新
	public static int sign = 0;

	//主题缓存<k,v>=<id,ent>
	public static Map<Long, TopicEntity> topicMap = new HashMap<>();
	//<k,v>=<topName,id>
	public static Map<String, Long> topMap = new HashMap<>();

	public static void setTopicMap(List<TopicEntity> list) {
		topicMap = list.stream().collect(Collectors.toMap(TopicEntity::getId, x -> x));
		topMap = list.stream().collect(Collectors.toMap(TopicEntity::getTopicname, TopicEntity::getId));

	}

	public static Map<Long, List<ConsumerEntity>> consumerListMap = new HashMap<>();

	public static void setConsumerListMap(List<ConsumerEntity> list) {
		consumerListMap = list.stream().collect(Collectors.groupingBy(ConsumerEntity::getTopid));
	}

	/**
	 * 通过主题id获取主题
	 *
	 * @param id
	 * @return
	 */
	public static TopicEntity getTopic(Long id) {
		return topicMap.get(id);
	}

	/**
	 * 通过主题名称获取消费者集合
	 *
	 * @param topName
	 * @return
	 */
	public static List<ConsumerEntity> getConsumerList(String topName) {
		Long id = topMap.get(topName);
		List<ConsumerEntity> clist = consumerListMap.get(id);
		return clist;
	}

}
