package br.com.usuario_service.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.ACKS_CONFIG, "0");

        // Timeout para solicitação, precisa ser maior para evitar TimeoutException
//        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 4000); // Ajuste o timeout da requisição
//        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 10000); // Timeout total de entrega
//        config.put(ProducerConfig.LINGER_MS_CONFIG, 1000); // Acumular mensagens até 1 segundo antes de enviar
//        config.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 15000); // Tempo máximo de bloqueio
//        config.put(ProducerConfig.RETRIES_CONFIG, 5); // Número de tentativas de reenvio em caso de falha

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic topicInfo(){
        return TopicBuilder
                .name("topic-info")
                .partitions(2)
                .replicas(1)
                .build();
    }
}
