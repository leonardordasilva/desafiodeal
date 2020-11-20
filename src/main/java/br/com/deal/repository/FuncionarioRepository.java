package br.com.deal.repository;

import br.com.deal.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Funcionario findByFuncionarioDocument(String document);

    @Query("select funcionario from Funcionario funcionario where funcionario.cargo.cargoId = 1 and funcionario.departamento.departamentoId = ?1")
    Funcionario findChefeDepartamento(Integer departamentoId);
}