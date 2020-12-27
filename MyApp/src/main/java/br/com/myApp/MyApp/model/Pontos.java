package br.com.myApp.MyApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "tbl_pontos")
public class Pontos {

	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "UUID", strategy = "uuid4")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(name = "id_ponto", columnDefinition = "CHAR(36)")
	private UUID idPonto;

	@NotNull
	private double pontuacao;

//	Relacionando com tabela Notas
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nota", foreignKey = @ForeignKey(name = "NOTA_ID_FK"))
	private Notas nota;

//	Relacionando com tabela Materia
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_diciplina", foreignKey = @ForeignKey(name = "MATERIA_ID_FK"))
	private Diciplina diciplina;

	/*
	 * Getters e setters
	 */

	public UUID getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(UUID idPonto) {
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

	public Diciplina getDiciplina() {
		return diciplina;
	}

	public void setDiciplina(Diciplina diciplina) {
		this.diciplina = diciplina;
	}
}
