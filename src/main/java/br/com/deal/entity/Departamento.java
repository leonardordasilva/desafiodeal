package br.com.deal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Departamento {
    @JsonProperty("departamento_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer departamentoId;

    @JsonProperty("departamento_name")
    @Column(length = 50)
    private String departamentoName;

    public Departamento(String departamentoId) {
        this.departamentoId = Integer.valueOf(departamentoId);
    }
}
