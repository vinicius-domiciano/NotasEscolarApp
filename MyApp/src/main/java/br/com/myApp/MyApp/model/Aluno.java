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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aluno")
	private Long idAluno;

	private String nome;
	private String ra;
	private String senha;
	private String serie;

//	Relacionando com tabela Notas
	@OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notas> notas = new ArrayList<Notas>();
	
//	Relacionando com tabela Professor
	@ManyToMany(mappedBy = "alunos")
	private List<Professor> professores = new ArrayList<>();

	/*
	 *Getters e setters
	*/
	
	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}
	
	/*
	 * metodos de adicionação/remoção de tabelas relacionadas 
	*/
	
	public void addNotas (Notas nota) {
		notas.add(nota);
		nota.setAluno(this);
	}
	
	public void removeNotas (Notas nota) {
		notas.remove(nota);
		nota.setAluno(null);
	}

}
