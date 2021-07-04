package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.dto.notas.NotasDefaultDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotasConverter implements Converter<NotasDefaultDTO, Notas> {
    @Override
    public Notas convert(NotasDefaultDTO notasDefaultDTO) {
        return new Notas(notasDefaultDTO.getIdNota(),
                notasDefaultDTO.getAno(),
                notasDefaultDTO.getBimestre(),
                new AlunoIdentifyConverter().convert(notasDefaultDTO.getAlunoIdentify()));
    }
}
