package br.com.myApp.MyApp.model.dto.turma;

import br.com.myApp.MyApp.model.Turma;
import br.com.myApp.MyApp.model.enumerations.PeriodoEnum;
import br.com.myApp.MyApp.model.enumerations.SerieEnum;
import br.com.myApp.MyApp.model.enumerations.TurmaEnum;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TurmaDefaultDTO {

    private UUID idTurma;
    private TurmaEnum turma;
    private SerieEnum serie;
    private PeriodoEnum periodo;

    /*Contrutor*/

    public TurmaDefaultDTO() {
    }

    public TurmaDefaultDTO(Turma turma) {
        this.idTurma = turma.getIdTurma();
        this.turma = turma.getTurma();
        this.serie = turma.getSerie();
        this.periodo = turma.getPeriodo();
    }

    /** GETTER E SETTER **/

    public UUID getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(UUID idTurma) {
        this.idTurma = idTurma;
    }

    public TurmaEnum getTurma() {
        return turma;
    }

    public void setTurma(TurmaEnum turma) {
        this.turma = turma;
    }

    public SerieEnum getSerie() {
        return serie;
    }

    public void setSerie(SerieEnum serie) {
        this.serie = serie;
    }

    public PeriodoEnum getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEnum periodo) {
        this.periodo = periodo;
    }

    /*Metodos*/

    public static List<TurmaDefaultDTO> convertTurmaToDTO(List<Turma> turmas) {
        return turmas.stream().map(TurmaDefaultDTO::new).collect(Collectors.toList());
    }
}
