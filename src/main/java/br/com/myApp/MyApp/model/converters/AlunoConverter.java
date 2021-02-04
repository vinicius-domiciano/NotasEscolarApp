package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.dto.aluno.AlunoDefaultDTO;
import org.springframework.core.convert.converter.Converter;

public class AlunoConverter implements Converter<AlunoDefaultDTO, Aluno> {

    @Override
    public Aluno convert(AlunoDefaultDTO alunoDefaultDTO) {
        return new Aluno(alunoDefaultDTO.getIdAluno(),
                alunoDefaultDTO.getNome(),
                alunoDefaultDTO.getRa(),
                alunoDefaultDTO.getSenha(),
                alunoDefaultDTO.getSerie());
    }
}
