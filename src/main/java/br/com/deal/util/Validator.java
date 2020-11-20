package br.com.deal.util;

import br.com.deal.entity.Cargo;
import br.com.deal.entity.Departamento;
import br.com.deal.entity.Funcionario;
import br.com.deal.repository.CargoRepository;
import br.com.deal.repository.DepartamentoRepository;
import br.com.deal.repository.FuncionarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Validator {
    private final FuncionarioRepository funcionarioRepository;

    private final CargoRepository cargoRepository;

    private final DepartamentoRepository departamentoRepository;

    public Validator(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, DepartamentoRepository departamentoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.departamentoRepository = departamentoRepository;
    }

    public boolean exist(Funcionario funcionario) {
        Boolean response = Boolean.FALSE;

        Funcionario funcionarioRepositoryByFuncionario_document = funcionarioRepository.findByFuncionarioDocument(funcionario.getFuncionarioDocument());

        if (funcionarioRepositoryByFuncionario_document != null) {
            response = Boolean.TRUE;
        }

        return response;
    }

    public boolean validateCargo(Funcionario funcionario) {
        Boolean response = Boolean.FALSE;

        Optional<Cargo> cargoOptional = cargoRepository.findById(funcionario.getCargo().getCargoId());

        if (cargoOptional.isPresent()) {
            funcionario.setCargo(cargoOptional.get());
            response = Boolean.TRUE;
        }

        return response;
    }

    public boolean validateDepartamento(Funcionario funcionario) {
        Boolean response = Boolean.FALSE;

        Optional<Departamento> departamentoOptional = departamentoRepository.findById(funcionario.getDepartamento().getDepartamentoId());

        if (departamentoOptional.isPresent()) {
            funcionario.setDepartamento(departamentoOptional.get());
            response = Boolean.TRUE;
        }

        return response;
    }
}