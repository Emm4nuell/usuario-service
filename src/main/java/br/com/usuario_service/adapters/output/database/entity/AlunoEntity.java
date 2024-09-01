package br.com.usuario_service.adapters.output.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "aluno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private Date data_nascimento;
    private String sexo;
    private LocalDateTime data_cadastro;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
//    @JsonIgnore
    private UsuarioEntity usuario;
}
