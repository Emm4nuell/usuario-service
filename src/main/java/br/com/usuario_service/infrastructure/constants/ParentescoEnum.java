package br.com.usuario_service.infrastructure.constants;

import lombok.Getter;

@Getter
public enum ParentescoEnum {
    PAI("Pai", 1),
    MAE("Mãe", 2),
    FILHO("Filho", 3),
    AVO("Avô/Avó", 4), // Pode representar ambos (avô e avó) ou ser dividido em dois enums se necessário
    TIO("Tio/Tia", 5),
    IRMAO("Irmão/Irmã", 6);  // Pode representar ambos (irmão e irmã)

    private final String label;
    private final int number;

    ParentescoEnum(String label, int number) {
        this.label = label;
        this.number = number;
    }
}
