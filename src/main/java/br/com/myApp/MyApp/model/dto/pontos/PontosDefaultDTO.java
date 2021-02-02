package br.com.myApp.MyApp.model.dto.pontos;

import br.com.myApp.MyApp.model.Pontos;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;
import br.com.myApp.MyApp.model.dto.notas.NotasDefaultDTO;
import br.com.myApp.MyApp.model.dto.notas.NotasIdentifyDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PontosDefaultDTO {

    private UUID idPonto;
    private double pontuacao;
    private NotasIdentifyDTO notaIdentify;
    private DiciplinaDefaultDTO diciplinaIdentify;

    /*Construtor*/

    public PontosDefaultDTO() {
    }

    public PontosDefaultDTO(Pontos pontos) {
        this.idPonto = pontos.getIdPonto();
        this.pontuacao = pontos.getPontuacao();
        this.notaIdentify = new NotasIdentifyDTO(pontos.getNota());
        this.diciplinaIdentify = new DiciplinaDefaultDTO(pontos.getDiciplina());
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

    public NotasIdentifyDTO getNotaIdentify() {
        return notaIdentify;
    }

    public void setNotaIdentify(NotasIdentifyDTO notaIdentify) {
        this.notaIdentify = notaIdentify;
    }

    public DiciplinaDefaultDTO getDiciplinaIdentify() {
        return diciplinaIdentify;
    }

    public void setDiciplinaIdentify(DiciplinaDefaultDTO diciplinaIdentify) {
        this.diciplinaIdentify = diciplinaIdentify;
    }

    /*Metodos*/

    public static List<PontosDefaultDTO> convertPontosToDTO(List<Pontos> pontos) {
        return pontos.stream().map(PontosDefaultDTO::new).collect(Collectors.toList());
    }
}
