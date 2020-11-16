package br.com.myApp.MyApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_professor")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_professor")
	private Long idProfessor;
	private String nome;
	private String email;
	private String senha;

	// Relacionando com tabela Materia
	@ManyToOne
	private Materia materia;

	// Relacionando com tabela Alunos
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Aluno> alunos = new ArrayList<Aluno>();

	/*
	 * Getters e setters
	 */

	public Long getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Long idProfessor) {
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
