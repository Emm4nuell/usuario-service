package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.KafkaLogModel;

public interface IKafkaLog {
    void execute(KafkaLogModel log);
}
