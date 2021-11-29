package com.sonic.simple.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RocketMq配置类
 *
 * @author chenwenjie.star
 * @date 2021/11/23 5:15 下午
 */
@Component
@ConfigurationProperties(prefix = "rocketmq")
@Data
public class RocketMQConfig {

    private Topic topic;

    private Group group;

    @Data
    @Component
    public static class Topic {

        private String testTaskTopic;

        private String testDataTopic;

        private String timeoutTestTopic;

        public String getTestTaskTopicWithTag(String tag) {
            return String.format("%s:%s", testTaskTopic, tag);
        }

    }

    @Data
    @Component
    public static class Group {

        private String testTaskGroup;

        private String testDataGroup;

        private String timeoutTestGroup;

    }

}
