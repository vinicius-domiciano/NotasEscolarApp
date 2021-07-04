package br.com.myApp.MyApp.model;

import br.com.myApp.MyApp.model.enumerations.BimestreEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Notas")
@Table(name = "tbl_notas")
public class Notas {

	public Notas() {
	}

	public Notas(UUID idNota) {
		this.idNota = idNota;
	}

	public Notas(UUID idNota, int ano, @NotNull BimestreEnum bimestre, @NotNull Aluno aluno) {
		this.idNota = idNota;
		this.ano = ano;
		this.bimestre = bimestre;
		this.aluno = aluno;
	}

	public Notas(UUID idNota, int ano, @NotNull BimestreEnum bimestre, @NotNull Aluno aluno, List<Pontos> pontos) {
		this.idNota = idNota;
		this.ano = ano;
		this.bimestre = bimestre;
		this.aluno = aluno;
		this.pontos = pontos;
	}

	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "UUID", strategy = "uuid4")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(name = "id_nota", columnDefinition = "CHAR(36)")
	private UUID idNota;

	private int ano;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private BimestreEnum bimestre;

//	Relacionando com tabela Aluno
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_aluno", foreignKey = @ForeignKey(name = "ALUNO_ID_FK"))
	private Aluno aluno;
	
//	Relacionando com tabela Pontos
	@OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pontos> pontos = new ArrayList<>();

	/*
	 *Getters e setters
	*/

	public UUID getIdNota() {
		return idNota;
	}

	public void setIdNota(UUID idNota) {
		this.idNota = idNota;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public BimestreEnum getBimestre() {
		return bimestre;
	}

	public void setBimestre(BimestreEnum bimestre) {
		this.bimestre = bimestre;
	}

	public List<Pontos> getPontos() {
		return pontos;
	}

	public void setPontos(List<Pontos> pontos) {
		this.pontos = pontos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	/*
	 * metodos de adicionação/remoção de tabelas relacionadas 
	*/

	public void addPontos (Pontos ponto) {
		pontos.add(ponto);
		ponto.setNota(this);
	}

	public void removePontos (Pontos ponto) {
		pontos.remove(ponto);
		ponto.setNota(null);
	}
}
