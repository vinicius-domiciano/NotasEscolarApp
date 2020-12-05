package br.com.myApp.MyApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_materia", foreignKey = @ForeignKey(name = "MATERIA_ID_FK"))
	private Materia materia;

	// Relacionando com tabela Alunos
	@NotNull
	@JsonIgnore
	@ManyToMany(mappedBy = "professores")
	private List<Aluno> alunos = new ArrayList<>();

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

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    /*
	 * metodos de adicionação/remoção de tabelas relacionadas
	 */

	public void addAluno(Aluno aluno) {
		alunos.add(aluno);
		aluno.getProfessores().add(this);
	}

	public void removeAluno(Aluno aluno) {
		alunos.remove(aluno);
		aluno.getProfessores().remove(this);
	}
}
