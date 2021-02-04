package br.com.myApp.MyApp.model.dto.notas;

import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.enumerations.BimestreEnum;

import java.util.UUID;

public class NotasIdentifyDTO {

    private UUID idNota;
    private BimestreEnum bimestre;

    public NotasIdentifyDTO() {
    }

    public NotasIdentifyDTO(Notas nota) {
        this.idNota = nota.getIdNota();
        this.bimestre = nota.getBimestre();
    }

    public UUID getIdNota() {
        return idNota;
    }

    public void setIdNota(UUID idNota) {
        this.idNota = idNota;
    }

    public BimestreEnum getBimestre() {
        return bimestre;
    }

    public void setBimestre(BimestreEnum bimestre) {
        this.bimestre = bimestre;
    }
}
