package br.com.myApp.MyApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pontos")
public class Pontos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ponto")
	private Long idPonto;
	private double pontuacao;

//	Relacionando com tabela Notas
	@ManyToOne
	private Notas nota;

//	Relacionando com tabela Materia
	@ManyToOne
	private Materia materia;

	/*
	 * Getters e setters
	 */

	public Long getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(Long idPonto) {
		this.idPonto = idPonto;
	}

	public double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Notas getNota() {
		return nota;
	}

	public void setNota(Notas nota) {
		this.nota = nota;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

}
