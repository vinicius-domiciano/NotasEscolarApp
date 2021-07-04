package br.com.myApp.MyApp.model;

import br.com.myApp.MyApp.model.enumerations.SerieEnum;
import br.com.myApp.MyApp.model.enumerations.TurmaEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Aluno")
@Table(name = "tbl_aluno")
public class Aluno {

	public Aluno() {
	}

	public Aluno(UUID idAluno, @NotNull String nome) {
		this.idAluno = idAluno;
		this.nome = nome;
	}

	public Aluno(UUID idAluno, @NotNull String nome, @NotNull String ra, @NotNull String senha, SerieEnum serie, Date dataNascimento) {
		this.idAluno = idAluno;
		this.nome = nome;
		this.ra = ra;
		this.senha = senha;
		this.serie = serie;
		this.dataNascimento = dataNascimento;
	}

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

	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Enumerated(EnumType.ORDINAL)
	private SerieEnum serie;

//	Relacionando com tabela Notas
	@OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notas> notas = new ArrayList<Notas>();

	//Relacionando com tabela turma
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_turma", foreignKey = @ForeignKey(name = "TURMA_ID_FK"))
	private Turma turma;

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

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Notas> getNotas() {
		return notas;
	}

	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}

	public Turma getTurma() {
		return turma;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
