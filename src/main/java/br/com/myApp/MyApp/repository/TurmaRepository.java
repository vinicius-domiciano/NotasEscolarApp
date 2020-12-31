package br.com.myApp.MyApp.repository;

import br.com.myApp.MyApp.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TurmaRepository extends JpaRepository<Turma, UUID> {
}
