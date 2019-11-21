package org.study.cloud.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.study.cloud.common.constant.RedisConstant;
import org.study.cloud.manage.component.MqConsumer;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-13 13:55
 */
@Configuration
public class RedisConsumerConfig {

    /**
     * 订阅
     * @author lipo
     * @date 2019-11-13 11:57
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //添加多个消费者
        container.addMessageListener(new MessageListenerAdapter(new MqConsumer(), "onMessage"), new PatternTopic(RedisConstant.TOPIC));
        return container;
    }

}
