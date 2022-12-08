package com.sinosoft.ie.booster.common.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @author lengleng
 * @since 2019/2/1 Redis 配置类
 */
@EnableCaching
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisTemplateConfiguration {

	/**
	 * 配置cacheManager Bean，
	 * 指定缓存有校期及Key和Value的Serializer
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = getObjectJackson2JsonRedisSerializer();
		// 配置序列化（解决乱码的问题）
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(CacheConstants.CACHE_DEFAULT_TIME))
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
		return RedisCacheManager.builder(factory)
				.cacheDefaults(config)
				.transactionAware() //启用支持事务的cache
				.build();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = getObjectJackson2JsonRedisSerializer();
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		// key采用String的序列化方式
		redisTemplate.setKeySerializer(stringRedisSerializer);
		// hash的key采用String的序列化方式
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForHash();
	}

	@Bean
	public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	@Bean
	public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
	}

	@Bean
	public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
	}

	@Bean
	public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
	}

	private Jackson2JsonRedisSerializer<Object> getObjectJackson2JsonRedisSerializer() {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		//解决jdk1.8中新时间API的序列化时出现com.fasterxml.jackson.databind.exc.InvalidTypeIdException的问题
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.registerModule(new JavaTimeModule());
		// 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		return jackson2JsonRedisSerializer;
	}
}
