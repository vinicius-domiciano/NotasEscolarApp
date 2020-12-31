package br.com.myApp.MyApp.model.dto.pontos;

import br.com.myApp.MyApp.model.Pontos;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PontosDefaultDTO {

    private UUID idPonto;
    private double pontuacao;

    /*Construtor*/

    public PontosDefaultDTO() {
    }

    public PontosDefaultDTO(Pontos pontos) {
        this.idPonto = pontos.getIdPonto();
        this.pontuacao = pontos.getPontuacao();
    }

    /** GETTER E SETTER **/

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

    /*Metodos*/

    public static List<PontosDefaultDTO> convertPontosToDTO(List<Pontos> pontos) {
        return pontos.stream().map(PontosDefaultDTO::new).collect(Collectors.toList());
    }
}
