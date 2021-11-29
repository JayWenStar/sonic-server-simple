//package com.sonic.simple.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.MappingJackson2MessageConverter;
//import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
//import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
//
///**
// * todo 确认无误有清除代码
// *
// * @author ZhouYiXun
// * @des rabbitmq监听配置
// * @date 2021/8/23 19:26
// */
//@Configuration
//public class RabbitListenerConfig implements RabbitListenerConfigurer {
//    @Override
//    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
//        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
//    }
//
//    @Bean
//    MessageHandlerMethodFactory messageHandlerMethodFactory() {
//        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
//        messageHandlerMethodFactory.setMessageConverter(mappingJackson2MessageConverter());
//        return messageHandlerMethodFactory;
//    }
//
//    @Bean
//    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
//        return new MappingJackson2MessageConverter();
//    }
//}