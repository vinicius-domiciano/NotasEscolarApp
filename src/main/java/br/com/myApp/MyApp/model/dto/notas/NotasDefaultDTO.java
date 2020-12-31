package br.com.myApp.MyApp.model.dto.notas;

import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.enumerations.BimestreEnum;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NotasDefaultDTO {

    private UUID idNota;
    private int ano;
    private BimestreEnum bimestre;

    //Construtor

    public NotasDefaultDTO() {
    }

    public NotasDefaultDTO(Notas notas) {
        this.idNota = notas.getIdNota();
        this.ano = notas.getAno();
        this.bimestre = notas.getBimestre();
    }

    /** GETTER E SETTER **/

    public UUID getIdNota() {
        return idNota;
    }

    public void setIdNota(UUID idNota) {
        this.idNota = idNota;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public BimestreEnum getBimestre() {
        return bimestre;
    }

    public void setBimestre(BimestreEnum bimestre) {
        this.bimestre = bimestre;
    }

    /*Metodos*/

    public static List<NotasDefaultDTO> convertNotasToDTO(List<Notas> notas) {
        return notas.stream().map(NotasDefaultDTO::new).collect(Collectors.toList());
    }
}
