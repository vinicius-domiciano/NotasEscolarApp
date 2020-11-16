package br.com.myApp.MyApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_notas")
public class Notas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nota")
	private Long idNota;
	private int ano;
	private int bimestre;

//	Relacionando com tabela Aluno
	@ManyToOne
	private Aluno aluno;
	
//	Relacionando com tabela Pontos
	@OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pontos> pontos = new ArrayList<Pontos>();

	/*
	 *Getters e setters
	*/
	
	public Long getIdNota() {
		return idNota;
	}

	public void setIdNota(Long idNota) {
		this.idNota = idNota;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getBimestre() {
		return bimestre;
	}

	public void setBimestre(int bimestre) {
		this.bimestre = bimestre;
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
