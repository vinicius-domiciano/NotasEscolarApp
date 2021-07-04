package br.com.myApp.MyApp.model.dto.notas;

import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.dto.aluno.AlunoDefaultDTO;
import br.com.myApp.MyApp.model.dto.aluno.AlunoIdentifyDTO;
import br.com.myApp.MyApp.model.enumerations.BimestreEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NotasDefaultDTO {

    private UUID idNota;
    private int ano;
    private BimestreEnum bimestre;

    @NotNull(message = "É necessario enviar o aluno com seu id")
    private AlunoIdentifyDTO alunoIdentify;

    //Construtor

    public NotasDefaultDTO() {
    }

    public NotasDefaultDTO(Notas notas) {
        this.idNota = notas.getIdNota();
        this.ano = notas.getAno();
        this.bimestre = notas.getBimestre();
        this.alunoIdentify = new AlunoIdentifyDTO(notas.getAluno());
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

    public AlunoIdentifyDTO getAlunoIdentify() {
        return alunoIdentify;
    }

    public void setAlunoIdentify(AlunoIdentifyDTO alunoIdentify) {
        this.alunoIdentify = alunoIdentify;
    }

    /*Metodos*/

    public static List<NotasDefaultDTO> convertNotasToDTO(List<Notas> notas) {
        return notas.stream().map(NotasDefaultDTO::new).collect(Collectors.toList());
    }
}
