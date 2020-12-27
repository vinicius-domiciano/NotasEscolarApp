package br.com.myApp.MyApp.model.dto.materia;

import br.com.myApp.MyApp.model.Materia;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MateriaDefaultDTO {

    private UUID idMateria;
    private String materia;

    public MateriaDefaultDTO() {
    }

    public MateriaDefaultDTO(Materia materia) {
        this.idMateria = materia.getIdMateria();
        this.materia = materia.getMateria();
    }

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

    /*Metodos*/

    public static List<MateriaDefaultDTO> convertMateriaToDTO(List<Materia> materias) {
        return materias.stream().map(MateriaDefaultDTO::new).collect(Collectors.toList());
    }

}
