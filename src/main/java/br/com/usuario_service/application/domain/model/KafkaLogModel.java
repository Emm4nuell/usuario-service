package br.com.usuario_service.application.domain.model;

import br.com.usuario_service.application.port.out.IKafkaLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaLogModel {
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public KafkaLogModel(IKafkaLog log, RuntimeException exception){
        log.execute(this);
        throw exception;
    }
    public KafkaLogModel(String message) {
        this.message = message;
    }
}
