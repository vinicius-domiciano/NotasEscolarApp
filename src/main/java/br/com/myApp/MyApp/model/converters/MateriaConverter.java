package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Materia;
import br.com.myApp.MyApp.model.dto.materia.MateriaDefaultDTO;
import org.springframework.core.convert.converter.Converter;

public class MateriaConverter implements Converter<MateriaDefaultDTO, Materia> {

    @Override
    public Materia convert(MateriaDefaultDTO materiaDefaultDTO) {
        return new Materia(
                materiaDefaultDTO.getIdMateria(),
                materiaDefaultDTO.getMateria()
        );
    }
}
