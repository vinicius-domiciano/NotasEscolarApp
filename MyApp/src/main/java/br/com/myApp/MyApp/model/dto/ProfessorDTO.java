package br.com.myApp.MyApp.model.dto;

import br.com.myApp.MyApp.model.Materia;
import br.com.myApp.MyApp.model.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProfessorDTO {

    public ProfessorDTO() {
    }

    public ProfessorDTO(Professor professor) {
        this.idProfessor = professor.getIdProfessor();
        this.nome = professor.getNome();
        this.email = professor.getEmail();
        this.senha = professor.getSenha();
        this.materia = new MateriaDTO(professor.getMateria());
        this.alunos = AlunoDTO.convertAlunoToDTO(professor.getAlunos());
    }

    private UUID idProfessor;
    private String nome;
    private String email;
    private String senha;
    private MateriaDTO materia;
    private List<AlunoDTO> alunos = new ArrayList<>();

    public static List<ProfessorDTO> convertProfessorToDTO(List<Professor> professores) {
        return professores.stream().map(ProfessorDTO::new).collect(Collectors.toList());
    }

    public UUID getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(UUID idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public MateriaDTO getMateria() {
        return materia;
    }

    public void setMateria(MateriaDTO materia) {
        this.materia = materia;
    }

    public List<AlunoDTO> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoDTO> alunos) {
        this.alunos = alunos;
    }
}
