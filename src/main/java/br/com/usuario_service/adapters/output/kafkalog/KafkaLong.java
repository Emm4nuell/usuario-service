package br.com.usuario_service.adapters.output.kafkalog;

import br.com.usuario_service.adapters.output.response.ResponseErrorLog;
import br.com.usuario_service.application.domain.model.KafkaLogModel;
import br.com.usuario_service.application.port.out.IKafkaLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaLong implements IKafkaLog {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    @Override
    public void execute(KafkaLogModel model) {
        var response = mapper.convertValue(model, ResponseErrorLog.class);
        Thread thread = new Thread(() -> {
            try {
                CompletableFuture<SendResult<String, Object>> result = kafkaTemplate.send("topic-info", response);
                result.exceptionally(ex -> {
                    throw new KafkaException("Erro ao enviar Log para o Broker Kafka");
                });
            } catch (KafkaException e) {
                throw new KafkaException("Erro ao enviar Log para o Broker Kafka");
            }
        });
        thread.start();
    }
}
