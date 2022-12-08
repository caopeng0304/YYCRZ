package com.sinosoft.ie.booster.mqroute.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.remoting.RPCHook;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties.Producer;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MyRocketMQTemplate  implements InitializingBean{
	
	@Autowired
	private RocketConfig mqConfigure;
	
//	@Lazy
//	@Bean
    public DefaultMQProducer defaultMQProducer() {
        RocketMQProperties.Producer producerConfig =new Producer();
        String nameServer = mqConfigure.getNamesrvAddr();
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(mqConfigure.getProducerGroup());
        producer.setNamesrvAddr(nameServer);
       
        producer.setSendMsgTimeout(mqConfigure.getTimeoutMillis());
        producer.setMaxMessageSize(producerConfig.getMaxMessageSize());
        producer.setCompressMsgBodyOverHowmuch(producerConfig.getCompressMessageBodyThreshold());
        //producer.start();
        return producer;
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		//defaultMQProducer();
		//defaultMQProducer.start();
	}
	
	@Lazy
	@Bean
    public DefaultMQAdminExt getInstance() throws Exception {
        RPCHook rpcHook = null;
        final String accessKey = mqConfigure.getAccessKey();
        final String secretKey = mqConfigure.getSecretKey();
        boolean isEnableAcl = StringUtils.isNotEmpty(accessKey) && StringUtils.isNotEmpty(secretKey);
        if (isEnableAcl) {
            rpcHook = new AclClientRPCHook(new SessionCredentials(accessKey, secretKey));
        }
        DefaultMQAdminExt mqAdminExt = null;
        if (mqConfigure.getTimeoutMillis() == null) {
            mqAdminExt = new DefaultMQAdminExt(rpcHook);
        } else {
            mqAdminExt = new DefaultMQAdminExt(rpcHook, mqConfigure.getTimeoutMillis());
        }
        mqAdminExt.setVipChannelEnabled(Boolean.parseBoolean(mqConfigure.getIsVIPChannel()));
        mqAdminExt.setUseTLS(mqConfigure.isUseTLS());
        mqAdminExt.setInstanceName(Long.toString(System.currentTimeMillis()));
        mqAdminExt.start();
        log.info("create MQAdmin instance {} success.", mqAdminExt);
        return mqAdminExt;
    }

}
