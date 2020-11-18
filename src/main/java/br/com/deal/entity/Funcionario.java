package br.com.deal.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer funcionario_id;

    @Column(length = 50)
    private String funcionario_name;

    private Integer funcionario_age;

    private LocalDate funcionario_birthday;

    @Column(length = 50)
    private String funcionario_document;

    @OneToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo_id;

    @OneToOne(mappedBy = "departamento")
    private FuncionarioDepartamento funcionarioDepartamento;
}
