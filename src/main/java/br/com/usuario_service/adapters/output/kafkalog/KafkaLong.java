package br.com.usuario_service.adapters.output.kafkalog;

import br.com.usuario_service.application.port.out.IKafkaLog;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaLong implements IKafkaLog {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void execute(String log) {
        kafkaTemplate.send("topic-info", log);
    }
}
