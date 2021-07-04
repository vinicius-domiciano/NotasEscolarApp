package br.com.myApp.MyApp.model.converters;

import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.Pontos;
import br.com.myApp.MyApp.model.dto.pontos.PontosDefaultDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PontosConverter implements Converter<PontosDefaultDTO, Pontos> {
    @Override
    public Pontos convert(PontosDefaultDTO pontosDefaultDTO) {
        var nota = new Notas(pontosDefaultDTO.getNotaIdentify().getIdNota());
        var diciplina = new Diciplina(pontosDefaultDTO.getDiciplinaIdentify().getIdDiciplina());

        return new Pontos(
                pontosDefaultDTO.getIdPonto(),
                pontosDefaultDTO.getPontuacao(),
                nota,
                diciplina
        );
    }
}
