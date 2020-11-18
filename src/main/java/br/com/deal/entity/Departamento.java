package br.com.deal.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer departamento_id;

    @Column(length = 50)
    private String departamento_name;

    @OneToMany(mappedBy = "funcionario")
    private List<FuncionarioDepartamento> funcionarioDepartamentoList = new ArrayList<>();
}
