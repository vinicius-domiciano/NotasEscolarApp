package br.com.myApp.MyApp.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.propertyeditors.UUIDEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_materia")
public class Materia {

	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "UUID", strategy = "uuid4")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(name = "id_materia", columnDefinition = "CHAR(36)")
	private UUID idMateria;

	@NotNull
	private String materia;

	public Materia() {
	}

	public Materia(UUID idMateria, @NotNull String materia) {
		this.idMateria = idMateria;
		this.materia = materia;
	}

	/*
	 *Getters e setters
	*/


	public UUID getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(UUID idMateria) {
		this.idMateria = idMateria;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}
	

}
