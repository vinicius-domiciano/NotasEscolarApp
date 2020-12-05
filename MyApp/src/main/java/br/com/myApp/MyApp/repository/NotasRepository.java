package br.com.myApp.MyApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myApp.MyApp.model.Notas;

import java.util.UUID;

public interface NotasRepository extends JpaRepository<Notas, UUID> {

}
