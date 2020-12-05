package br.com.myApp.MyApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myApp.MyApp.model.Pontos;

import java.util.UUID;

public interface PontosRepository extends JpaRepository<Pontos, UUID> {

}
