package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.Professor;
import br.com.myApp.MyApp.model.Turma;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;
import br.com.myApp.MyApp.model.dto.professor.ProfessorIdentifyDTO;
import br.com.myApp.MyApp.model.dto.turma.TurmaDefaultDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DiciplinaConverter implements Converter<DiciplinaDefaultDTO, Diciplina> {


    @Override
    public Diciplina convert(DiciplinaDefaultDTO diciplinaDefaultDTO) {
        var materia = new MateriaConverter().convert(diciplinaDefaultDTO.getMateria());
        var professor = new Professor(diciplinaDefaultDTO.getProfessorIdentify().getIdProfessor(),
                diciplinaDefaultDTO.getProfessorIdentify().getNome());
        List<Turma> turma = new ArrayList<>();

        if (Objects.nonNull(diciplinaDefaultDTO.getTurmasIdentify())) {
            turma = diciplinaDefaultDTO.getTurmasIdentify().stream()
                    .map(dto -> new Turma(dto.getIdTurma(), dto.getTurma()))
                    .collect(Collectors.toList());
        }

        return new Diciplina(diciplinaDefaultDTO.getIdDiciplina(),
                diciplinaDefaultDTO.getSerie(),
                materia,
                professor,
                turma);
    }
}
