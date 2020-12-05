package br.com.myApp.MyApp.model.dto;

import br.com.myApp.MyApp.model.Pontos;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PontosDTO {

    public PontosDTO() {
    }

    public PontosDTO(Pontos pontos) {
        this.idPonto = pontos.getIdPonto();
        this.pontuacao = pontos.getPontuacao();
        this.materia = new MateriaDTO(pontos.getMateria());
    }

    private UUID idPonto;
    private double pontuacao;
    private MateriaDTO materia;

    public static List<PontosDTO> convertPontosToDTO(List<Pontos> pontos) {
        return pontos.stream().map(PontosDTO::new).collect(Collectors.toList());
    }

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

    public MateriaDTO getMateria() {
        return materia;
    }

    public void setMateria(MateriaDTO materia) {
        this.materia = materia;
    }
}
