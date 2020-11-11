package br.com.myApp.MyApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myApp.MyApp.model.Notas;

public interface NotasRepository extends JpaRepository<Notas, Long> {

}
