package br.com.myApp.MyApp.model.dto.pontos;

import br.com.myApp.MyApp.model.Pontos;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;
import br.com.myApp.MyApp.model.dto.notas.NotasDefaultDTO;

public class PontosAllDTO extends PontosDefaultDTO{

    private NotasDefaultDTO nota;
    private DiciplinaDefaultDTO diciplina;

    /*Construtor*/

    public PontosAllDTO() {
    }

    public PontosAllDTO(Pontos ponto) {
        this.setIdPonto(ponto.getIdPonto());
        this.setPontuacao(ponto.getPontuacao());
        this.nota = new NotasDefaultDTO(ponto.getNota());
        this.diciplina = new DiciplinaDefaultDTO(ponto.getDiciplina());
    }

    /** GETTER E SETTER **/

    public NotasDefaultDTO getNota() {
        return nota;
    }

    public void setNota(NotasDefaultDTO nota) {
        this.nota = nota;
    }

    public DiciplinaDefaultDTO getDiciplina() {
        return diciplina;
    }

    public void setDiciplina(DiciplinaDefaultDTO diciplina) {
        this.diciplina = diciplina;
    }
}
