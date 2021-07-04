package br.com.myApp.MyApp.service;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.Pontos;
import br.com.myApp.MyApp.model.converters.PontosConverter;
import br.com.myApp.MyApp.model.dto.pontos.PontosAllDTO;
import br.com.myApp.MyApp.model.dto.pontos.PontosDefaultDTO;
import br.com.myApp.MyApp.repository.PontosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class PontosService extends BaseService<PontosRepository, Pontos, PontosDefaultDTO> {

    private final PontosConverter converter;
    private final NotasService notasService;
    private final DiciplinaService diciplinaService;

    @Autowired
    public PontosService(PontosRepository pontosRepository, PontosConverter converter, NotasService notasService, DiciplinaService diciplinaService) {
        super(pontosRepository);
        this.converter = converter;
        this.notasService = notasService;
        this.diciplinaService = diciplinaService;
    }

    public PontosAllDTO findPontoById(UUID id) {
        var ponto = this.findById(id)
                .orElseThrow(() -> new NotFoundException("Ponto não encontrado para o id informado"));

        return new PontosAllDTO(ponto);
    }

    public PontosDefaultDTO savePonto(PontosDefaultDTO pontoDTO) {
        if (Objects.isNull(pontoDTO.getNotaIdentify()))
            throw new BadRequestException("Ops, é necessario enviar o id da nota");
        else if (Objects.isNull(pontoDTO.getDiciplinaIdentify()))
            throw new BadRequestException("Ops, é necessario enviar o id da diciplina");

        var ponto = this.converter.convert(pontoDTO);

        if (Objects.isNull(ponto)) throw new BadRequestException("Erro ao converter ponto");

        this.existDiciplina(ponto.getDiciplina());
        this.existNota(ponto.getNota());

        return this.save(ponto);
    }

    private void existNota(Notas nota) {
        this.notasService.findNotasById(nota.getIdNota());
    }

    private void existDiciplina(Diciplina diciplina) {
        this.diciplinaService.findDiciplinaById(diciplina.getIdDiciplina());
    }

}
