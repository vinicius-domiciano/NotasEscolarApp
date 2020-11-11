package br.com.myApp.MyApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_materia")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_materia;
	private String materia;

	public Long getId_materia() {
		return id_materia;
	}

	public void setId_materia(Long id_materia) {
		this.id_materia = id_materia;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

}
