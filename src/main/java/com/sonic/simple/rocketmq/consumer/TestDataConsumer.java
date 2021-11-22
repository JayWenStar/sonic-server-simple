package com.sonic.simple.rocketmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.sonic.simple.config.RocketMQConfig;
import com.sonic.simple.services.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试数据消费
 *
 * @author chenwenjie.star
 * @date 2021/11/23 5:45 下午
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "${rocketmq.topic.test-data-topic}",
        consumerGroup = "${rocketmq.group.test-data-group}"
)
public class TestDataConsumer implements RocketMQListener<JSONObject> {

    @Autowired
    private AgentsService agentsService;
    @Autowired
    private DevicesService devicesService;
    @Autowired
    private ResultsService resultsService;
    @Autowired
    private TestCasesService testCasesService;
    @Autowired
    private ResultDetailService resultDetailService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private RocketMQConfig rocketMQConfig;

    @Override
    public void onMessage(JSONObject jsonMsg) {

        log.info("TestDataConsumer消费者收到消息  : " + jsonMsg.toString());
        switch (jsonMsg.getString("msg")) {
            case "step":
            case "perform":
            case "record":
            case "status":
                // todo 处理过去无效的消息
                if (jsonMsg.get("agentId") == null || jsonMsg.get("agentId").equals("")) {
                    break;
                }
                resultDetailService.saveByTransport(jsonMsg);
                break;
            case "findSteps":
                JSONObject j = testCasesService.findSteps(jsonMsg.getInteger("caseId"));
                if (j != null) {
                    JSONObject steps = new JSONObject();
                    steps.put("msg", "runStep");
                    steps.put("pf", j.get("pf"));
                    steps.put("steps", j.get("steps"));
                    steps.put("gp", j.get("gp"));
                    steps.put("sessionId", jsonMsg.getString("sessionId"));
                    steps.put("pwd", jsonMsg.getString("pwd"));
                    steps.put("udId", jsonMsg.getString("udId"));

                    // todo 改造成事务消息
                    rocketMQTemplate.convertAndSend(
                            rocketMQConfig.getTopic().getTestTaskTopicWithTag(jsonMsg.getString("key")),
                            steps
                    );
                }
                break;
        }
    }
}
