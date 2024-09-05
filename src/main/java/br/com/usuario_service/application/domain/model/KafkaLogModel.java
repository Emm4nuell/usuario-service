package br.com.usuario_service.application.domain.model;

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

    public KafkaLogModel(String message) {
        this.message = message;
    }
}
