package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Professor;
import br.com.myApp.MyApp.model.dto.professor.ProfessorDefaultDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfessorConverter implements Converter<ProfessorDefaultDTO, Professor> {

    @Override
    public Professor convert(ProfessorDefaultDTO professorDefaultDTO) {
        return new Professor(professorDefaultDTO.getIdProfessor(),
                professorDefaultDTO.getNome(),
                professorDefaultDTO.getEmail(),
                professorDefaultDTO.getSenha());
    }
}
