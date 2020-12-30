package br.com.myApp.MyApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myApp.MyApp.model.Materia;

import java.util.UUID;

public interface MateriaRepository extends JpaRepository<Materia, UUID>{

}
