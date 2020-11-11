package br.com.myApp.MyApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_notas")
public class Notas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_nota;
	private int ano;
	private int bimestre;

	public Long getId_nota() {
		return id_nota;
	}

	public void setId_nota(Long id_nota) {
		this.id_nota = id_nota;
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

}
