package com.sinosoft.ie.booster.mqroute.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.sinosoft.ie.booster.mqroute.config.MqConfig;
import com.sinosoft.ie.booster.mqroute.console.Assembly;
import com.sinosoft.ie.booster.mqroute.topic.KafkaTopicHandle;
import com.sinosoft.ie.booster.mqroute.topic.RocketTopicHandle;

/**
 * 收到注入bean
 * 在bean还未创建时执行，所以得不到MqConfig实例，不能用
 * @author gt
 *
 */
//@Component
public class DefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Autowired
	private MqConfig mqConfig;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		if( Assembly.KAFKA.equals(mqConfig.getMq_active())) {
			registerBean(registry, "hello", KafkaTopicHandle.class);
		}else {
			registerBean(registry, "hello", RocketTopicHandle.class);
		}

	}

	/**
	 * 注册bean
	 **/
	private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
		RootBeanDefinition bean = new RootBeanDefinition(beanClass);
		registry.registerBeanDefinition(name, bean);
	}
}
