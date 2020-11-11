package br.com.myApp.MyApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pontos")
public class Pontos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ponto;
	private double pontuação;

	public Long getId_ponto() {
		return id_ponto;
	}

	public void setId_ponto(Long id_ponto) {
		this.id_ponto = id_ponto;
	}

	public double getPontuação() {
		return pontuação;
	}

	public void setPontuação(double pontuação) {
		this.pontuação = pontuação;
	}

}
