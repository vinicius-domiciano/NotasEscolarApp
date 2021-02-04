package br.com.myApp.MyApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_professor")
public class Professor {

	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "UUID", strategy = "uuid4")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(name = "id_professor", columnDefinition = "CHAR(36)")
	private UUID idProfessor;

	@NotNull
	private String nome;

	@NotNull
	@NaturalId
	@Column(unique = true)
	private String email;

	@NotNull
	private String senha;

	// Relacionando com tabela Materia
	@NotNull
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumns({
			@JoinColumn(name="id_materia", referencedColumnName="id_materia"),
			@JoinColumn(name="id_professor", referencedColumnName="id_professor")
	})
	private List<Materia> materias = new ArrayList<>();

	//Relacionando com tabela Diciplina
	@NotNull
	@OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Diciplina> diciplinas = new ArrayList<>();

	//Construtores

	public Professor() {
	}

	public Professor(UUID idProfessor, @NotNull String nome) {
		this.idProfessor = idProfessor;
		this.nome = nome;
	}

	public Professor(UUID idProfessor, @NotNull String nome, @NotNull String email, @NotNull String senha) {
		this.idProfessor = idProfessor;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Professor(UUID idProfessor, @NotNull String nome, @NotNull String email, @NotNull String senha,
					 @NotNull List<Materia> materias, @NotNull List<Diciplina> diciplinas) {
		this.idProfessor = idProfessor;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.materias = materias;
		this.diciplinas = diciplinas;
	}


	/*
	 * Getters e setters
	 */

	public UUID getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(UUID idProfessor) {
		this.idProfessor = idProfessor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public List<Diciplina> getDiciplinas() {
		return diciplinas;
	}

	public void setDiciplinas(List<Diciplina> diciplinas) {
		this.diciplinas = diciplinas;
	}

	/*
	 * metodos de adicionação/remoção de tabelas relacionadas
	 */

	public void  addDiciplina(Diciplina diciplina) {
		diciplinas.add(diciplina);
		diciplina.setProfessor(this);
	}

	public void removeDiciplina (Diciplina diciplina) {
		diciplinas.remove(diciplina);
		diciplina.setProfessor(null);
	}
}
