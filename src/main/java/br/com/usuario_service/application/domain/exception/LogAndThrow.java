package br.com.usuario_service.application.domain.exception;

import br.com.usuario_service.application.domain.model.KafkaLogModel;
import br.com.usuario_service.application.port.out.IKafkaLog;

public class LogAndThrow extends RuntimeException{
    public LogAndThrow(IKafkaLog iKafkaLog, RuntimeException exception) {
        super(exception);
        iKafkaLog.execute(new KafkaLogModel(exception.getMessage()));
    }
}
