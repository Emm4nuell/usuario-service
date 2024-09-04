package br.com.usuario_service.application.port.out;

public interface IKafkaLog {
    void execute(String log);
}
