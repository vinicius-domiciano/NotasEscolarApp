package br.com.myApp.MyApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myApp.MyApp.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
