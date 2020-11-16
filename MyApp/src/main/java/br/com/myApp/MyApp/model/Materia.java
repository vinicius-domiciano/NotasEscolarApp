package br.com.myApp.MyApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_materia")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_materia")
	private Long idMateria;
	private String materia;
	
//	Relacionando com a tabela Professor 
	@OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Professor> professores = new ArrayList<Professor>();
	
//	Relacionando com a tabela Pontos
	@OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pontos> pontos = new ArrayList<Pontos>();

	/*
	 *Getters e setters
	*/
	
	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	/*
	 * metodos de adicionação/remoção de tabelas relacionadas 
	*/
	
	public void addPontos (Pontos ponto) {
		pontos.add(ponto);
		ponto.setMateria(this);
	}
	
	public void removePontos (Pontos ponto) {
		pontos.remove(ponto);
		ponto.setMateria(null);
	}
	
	public void addProfessor (Professor professor) {
		professores.add(professor);
		professor.setMateria(this);
	}
	
	public void removeProfessor (Professor professor) {
		professores.remove(professor);
		professor.setMateria(null);
	}
	

}
