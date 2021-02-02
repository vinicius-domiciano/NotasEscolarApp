package br.com.myApp.MyApp.repository;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.enumerations.BimestreEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myApp.MyApp.model.Notas;

import java.util.Optional;
import java.util.UUID;

public interface NotasRepository extends JpaRepository<Notas, UUID> {

    Optional<Notas> findByAnoAndBimestreAndAluno(int ano, BimestreEnum bimestre, Aluno aluno);

}
