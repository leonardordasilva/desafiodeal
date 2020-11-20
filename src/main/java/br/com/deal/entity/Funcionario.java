package br.com.deal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Funcionario {
    @JsonProperty("funcionario_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer funcionarioId;

    @NotNull
    @NotEmpty(message = "Nome é obrigatório")
    @JsonProperty("funcionario_name")
    @Column(length = 50)
    private String funcionarioName;

    @JsonProperty("funcionario_age")
    private Integer funcionarioAge;

    @JsonProperty("funcionario_birthday")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate funcionarioBirthday;

    @NotNull
    @NotEmpty(message = "Documento de identificação é obrigatório")
    @JsonProperty("funcionario_document")
    @Column(length = 50)
    private String funcionarioDocument;

    @JsonProperty("cargo")
    @OneToOne
    @JoinColumn(name = "cargoId")
    private Cargo cargo;

    @JsonProperty("departamento")
    @OneToOne
    @JoinColumn(name = "departamentoId")
    private Departamento departamento;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionario_departamento",
            joinColumns = @JoinColumn(name = "funcionarioId"),
            inverseJoinColumns = @JoinColumn(name = "departamentoId"))
    private List<Departamento> departamentos = new ArrayList<>();
}