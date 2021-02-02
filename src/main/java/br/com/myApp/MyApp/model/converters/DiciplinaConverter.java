package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.Professor;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;
import br.com.myApp.MyApp.model.dto.professor.ProfessorIdentifyDTO;
import org.springframework.core.convert.converter.Converter;

public class DiciplinaConverter implements Converter<DiciplinaDefaultDTO, Diciplina> {


    @Override
    public Diciplina convert(DiciplinaDefaultDTO diciplinaDefaultDTO) {
        return new Diciplina(diciplinaDefaultDTO.getIdDiciplina(),
                diciplinaDefaultDTO.getSerie());
    }
}
