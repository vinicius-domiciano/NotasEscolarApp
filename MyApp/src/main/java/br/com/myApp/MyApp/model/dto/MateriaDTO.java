package br.com.myApp.MyApp.model.dto;

import br.com.myApp.MyApp.model.Materia;

import java.util.UUID;

public class MateriaDTO {

    public MateriaDTO() {
    }

    public MateriaDTO(Materia materia) {
        this.idMateria = materia.getIdMateria();
        this.materia = materia.getMateria();
    }

    private UUID idMateria;
    private String materia;

    public UUID getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(UUID idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
