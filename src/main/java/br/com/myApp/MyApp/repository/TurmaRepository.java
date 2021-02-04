package br.com.myApp.MyApp.repository;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TurmaRepository extends JpaRepository<Turma, UUID> {

    @Query(
            value = "SELECT t.* FROM tbl_turma AS t WHERE periodo LIKE %?1% AND serie LIKE %?2% AND turma LIKE %?3%",
            nativeQuery = true
    )
    List<Turma> filtersParams(String periodo, String serie, String turma);

}
