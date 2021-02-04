package br.com.myApp.MyApp.model.dto.turma;

import br.com.myApp.MyApp.model.Turma;
import br.com.myApp.MyApp.model.enumerations.TurmaEnum;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TurmaIdentifyDTO {

    public TurmaIdentifyDTO() {
    }

    public TurmaIdentifyDTO(Turma turma) {
        this.idTurma = turma.getIdTurma();
        this.turma = turma.getTurma();
    }

    private UUID idTurma;
    private TurmaEnum turma;

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

    public static List<TurmaIdentifyDTO> convertTurmaToDTO(List<Turma> turmas) {
        return turmas.stream().map(TurmaIdentifyDTO::new).collect(Collectors.toList());
    }
}
