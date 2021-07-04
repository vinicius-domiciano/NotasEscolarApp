package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Turma;
import br.com.myApp.MyApp.model.dto.turma.TurmaDefaultDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TurmaConverter implements Converter<TurmaDefaultDTO, Turma> {
    @Override
    public Turma convert(TurmaDefaultDTO turmaDefaultDTO) {
        return new Turma(turmaDefaultDTO.getIdTurma(),
                turmaDefaultDTO.getTurma(),
                turmaDefaultDTO.getSerie(),
                turmaDefaultDTO.getPeriodo());
    }

}
