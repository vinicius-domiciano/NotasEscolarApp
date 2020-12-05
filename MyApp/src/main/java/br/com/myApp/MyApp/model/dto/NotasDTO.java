package br.com.myApp.MyApp.model.dto;

import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.enumerations.BimestreEnum;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NotasDTO {

    public NotasDTO() {
    }

    public NotasDTO(Notas notas) {
        this.idNota = notas.getIdNota();
        this.ano = notas.getAno();
        this.bimestre = notas.getBimestre();
        this.pontos = PontosDTO.convertPontosToDTO(notas.getPontos());
    }

    private UUID idNota;
    private int ano;
    private BimestreEnum bimestre;
    private List<PontosDTO> pontos;

    public static List<NotasDTO> convertNotasToDTO(List<Notas> notas) {
        return notas.stream().map(NotasDTO::new).collect(Collectors.toList());
    }

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

    public List<PontosDTO> getPontos() {
        return pontos;
    }

    public void setPontos(List<PontosDTO> pontos) {
        this.pontos = pontos;
    }
}
