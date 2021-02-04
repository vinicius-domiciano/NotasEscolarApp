package br.com.myApp.MyApp.model.dto.aluno;

import br.com.myApp.MyApp.model.Aluno;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class AlunoIdentifyDTO {

    public AlunoIdentifyDTO() {
    }

    public AlunoIdentifyDTO(Aluno aluno) {
        this.idAluno = aluno.getIdAluno();
        this.nome = aluno.getNome();
    }

    @NotNull(message = "É necessario o id do aluno")
    private UUID idAluno;

    private String nome;

    public UUID getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(UUID idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
