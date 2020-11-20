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
public class Cargo {
    @JsonProperty("cargo_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer cargoId;

    @JsonProperty("cargo_name")
    @Column(length = 50)
    private String cargoName;

    public Cargo(String cargoId) {
        this.cargoId = Integer.valueOf(cargoId);
    }
}