package br.com.deal.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@Entity
public class FuncionarioDepartamento implements Serializable {
    private static final long serialVersionUID = -2658147541252499555L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @JoinColumn(name = "funcionario_id")
    @ManyToOne
    private Funcionario funcionario;

    @JoinColumn(name = "departamento_id")
    @OneToOne
    private Departamento departamento;
}
