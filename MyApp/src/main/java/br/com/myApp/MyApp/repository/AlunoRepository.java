package br.com.myApp.MyApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myApp.MyApp.model.Aluno;

import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID>{

}
