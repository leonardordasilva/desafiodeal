package br.com.deal.controller;

import br.com.deal.entity.Funcionario;
import br.com.deal.repository.FuncionarioRepository;
import br.com.deal.util.Constantes;
import br.com.deal.util.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final Validator validator;

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(Validator validator, FuncionarioRepository funcionarioRepository) {
        this.validator = validator;
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid Funcionario funcionario, UriComponentsBuilder uriBuilder) {
        if (validator.exist(funcionario)) {
            return ResponseEntity.badRequest().body(Constantes.FUNCIONARIO_CADASTRADO);
        }

        if (!validator.validateCargo(funcionario)) {
            return ResponseEntity.badRequest().body(Constantes.CARGO_INEXISTENTE);
        }

        if (!validator.validateDepartamento(funcionario)) {
            return ResponseEntity.badRequest().body(Constantes.DEPARTAMENTO_INEXISTENTE);
        }

        if (funcionario.getCargo().getCargoId().equals(1)
                && validator.existChefiaDepartamento(funcionario)) {
            return ResponseEntity.badRequest().body(Constantes.DEPARTAMENTO_CHEFIADA);
        }

        funcionario.getDepartamentos().add(funcionario.getDepartamento());
        funcionarioRepository.save(funcionario);

        URI uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getFuncionarioId()).toUri();
        return ResponseEntity.created(uri).body(funcionario);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            return ResponseEntity.ok(funcionario);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();

        if (!funcionarioList.isEmpty()) {
            return ResponseEntity.ok(funcionarioList);
        }

        return ResponseEntity.notFound().build();
    }
}