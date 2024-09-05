package br.com.usuario_service.adapters.output.kafkalog;

import br.com.usuario_service.adapters.input.api.response.ResponseUsuario;
import br.com.usuario_service.application.domain.model.KafkaLogModel;
import br.com.usuario_service.application.port.out.IKafkaLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class KafkaLong implements IKafkaLog {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    @Override
    public void execute(KafkaLogModel log) {
        kafkaTemplate.send("topic-info", log);
    }
}
