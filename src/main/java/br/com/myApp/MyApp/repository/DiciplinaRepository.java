package br.com.myApp.MyApp.repository;

import br.com.myApp.MyApp.model.Diciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiciplinaRepository extends JpaRepository<Diciplina, UUID> {
}
