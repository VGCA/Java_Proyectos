package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    /**
     * Method to generate rules for create a new topic
     * and its configurations
     * @return new topic created
     */
    @Bean
    public NewTopic generateTopic() {

        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE);
        configurations.put(TopicConfig.RETENTION_MS_CONFIG,"86400000"); //Tiempo de retención de mensajes
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824"); //Tamaño máximo de segmento
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"1000012"); //Tamaño máximo de cada mensaje

        return TopicBuilder.name("unProgramadorNace-topic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }
}
