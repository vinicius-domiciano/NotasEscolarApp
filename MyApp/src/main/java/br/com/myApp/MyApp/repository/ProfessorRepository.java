package br.com.myApp.MyApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myApp.MyApp.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
