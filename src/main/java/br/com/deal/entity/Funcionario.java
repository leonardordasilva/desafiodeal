package br.com.deal.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionario_departamento",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id"))
    private List<Departamento> departamentos = new ArrayList<>();
}
