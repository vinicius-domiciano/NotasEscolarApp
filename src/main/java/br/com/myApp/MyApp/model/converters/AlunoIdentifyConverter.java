package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.dto.aluno.AlunoIdentifyDTO;
import org.springframework.core.convert.converter.Converter;

public class AlunoIdentifyConverter implements Converter<AlunoIdentifyDTO, Aluno> {

    @Override
    public Aluno convert(AlunoIdentifyDTO alunoIdentifyDTO) {
        return new Aluno(alunoIdentifyDTO.getIdAluno(),
                alunoIdentifyDTO.getNome());
    }
}
