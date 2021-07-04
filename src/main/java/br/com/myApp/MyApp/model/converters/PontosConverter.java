package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Pontos;
import br.com.myApp.MyApp.model.dto.pontos.PontosDefaultDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PontosConverter implements Converter<PontosDefaultDTO, Pontos> {
    @Override
    public Pontos convert(PontosDefaultDTO pontosDefaultDTO) {
        return new Pontos(pontosDefaultDTO.getIdPonto(),
                pontosDefaultDTO.getPontuacao());
    }
}
