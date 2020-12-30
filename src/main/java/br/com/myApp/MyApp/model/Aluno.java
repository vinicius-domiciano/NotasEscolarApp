package br.com.myApp.MyApp.model;

import br.com.myApp.MyApp.model.enumerations.SerieEnum;
import br.com.myApp.MyApp.model.enumerations.TurmaEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Aluno")
@Table(name = "tbl_aluno")
public class Aluno {

	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "UUID", strategy = "uuid4")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(name = "id_aluno", columnDefinition = "CHAR(36)")
	private UUID idAluno;

	@NotNull
	private String nome;

	@NotNull
	@NaturalId
	@Column(unique = true)
	private String ra;

	@NotNull
	private String senha;

	@Enumerated(EnumType.ORDINAL)
	private SerieEnum serie;

	@Enumerated(EnumType.ORDINAL)
	private TurmaEnum turma;

//	Relacionando com tabela Notas
	@OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notas> notas = new ArrayList<Notas>();
	
//	Relacionando com tabela Professores
	@NotNull
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumns({
			@JoinColumn(name="id_aluno", referencedColumnName="id_aluno"),
			@JoinColumn(name="id_professor", referencedColumnName="id_professor")
	})
	private List<Professor> professores = new ArrayList<>();

	/*
	 *Getters e setters
	*/

	public UUID getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(UUID idAluno) {
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

	public SerieEnum getSerie() {
		return serie;
	}

	public void setSerie(SerieEnum serie) {
		this.serie = serie;
	}

	public TurmaEnum getTurma() {
		return turma;
	}

	public void setTurma(TurmaEnum turma) {
		this.turma = turma;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Notas> getNotas() {
		return notas;
	}

	public void setNotas(List<Notas> notas) {
		this.notas = notas;
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

	public void addProfessor(Professor professor) {
		professores.add(professor);
		professor.getAlunos().add(this);
	}

	public void removeProfessor(Professor professor) {
		professores.remove(professor);
		professor.getAlunos().remove(this);
	}

}
