package com.sonic.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.sonic.simple.config.RocketMQConfig;
import com.sonic.simple.config.WebAspect;
import com.sonic.simple.models.Agents;
import com.sonic.simple.models.Devices;
import com.sonic.simple.models.http.RespEnum;
import com.sonic.simple.models.http.RespModel;
import com.sonic.simple.services.AgentsService;
import com.sonic.simple.services.DevicesService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transport/exchange")
public class ExchangeController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private DevicesService devicesService;
    @Autowired
    private AgentsService agentsService;
    @Autowired
    private RocketMQConfig rocketMQConfig;

    @WebAspect
    @GetMapping("/reboot")
    public RespModel reboot(@RequestParam(name = "id") int id) {
        Devices device = devicesService.findById(id);
        if (device!=null) {
            Agents agent = agentsService.findById(device.getAgentId());
            if (agent!=null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("msg", "reboot");
                jsonObject.put("udId", device.getUdId());
                jsonObject.put("platform", device.getPlatform());
                rocketMQTemplate.convertAndSend(rocketMQConfig.getTopic().getTestTaskTopic(), jsonObject);
                // todo 确认无误后移除
                //rabbitTemplate.convertAndSend("MsgDirectExchange", agent.getSecretKey(), jsonObject);
                return new RespModel(2000, "发送成功！");
            } else {
                return new RespModel(RespEnum.ID_NOT_FOUND);
            }
        } else {
            return new RespModel(RespEnum.ID_NOT_FOUND);
        }
    }
}