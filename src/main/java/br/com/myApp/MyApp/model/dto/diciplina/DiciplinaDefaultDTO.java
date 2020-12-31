package br.com.myApp.MyApp.model.dto.diciplina;

import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.enumerations.SerieEnum;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DiciplinaDefaultDTO {

    private UUID idDiciplina;
    private SerieEnum serie;

    public DiciplinaDefaultDTO() {
    }

    public DiciplinaDefaultDTO(Diciplina diciplina) {
        this.idDiciplina = diciplina.getIdDiciplina();
        this.serie = diciplina.getSerie();
    }

    public UUID getIdDiciplina() {
        return idDiciplina;
    }

    public void setIdDiciplina(UUID idDiciplina) {
        this.idDiciplina = idDiciplina;
    }

    public SerieEnum getSerie() {
        return serie;
    }

    public void setSerie(SerieEnum serie) {
        this.serie = serie;
    }

    /*Metodos*/

    public static List<DiciplinaDefaultDTO> convertDiciplinaToDTO(List<Diciplina> diciplinas) {
        return diciplinas.stream().map(DiciplinaDefaultDTO::new).collect(Collectors.toList());
    }
}
