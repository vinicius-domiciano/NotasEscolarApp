package br.com.myApp.MyApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myApp.MyApp.model.Professor;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {

}
