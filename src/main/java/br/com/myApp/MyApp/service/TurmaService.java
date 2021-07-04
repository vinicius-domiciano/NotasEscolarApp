package br.com.myApp.MyApp.service;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Turma;
import br.com.myApp.MyApp.model.converters.TurmaConverter;
import br.com.myApp.MyApp.model.dto.turma.TurmaAllDTO;
import br.com.myApp.MyApp.model.dto.turma.TurmaDefaultDTO;
import br.com.myApp.MyApp.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TurmaService extends BaseService<TurmaRepository, Turma, TurmaDefaultDTO> {

    private final TurmaConverter converter;

    public TurmaService(TurmaRepository turmaRepository, TurmaConverter converter) {
        super(turmaRepository);
        this.converter = converter;
    }

    public TurmaAllDTO findTurmaById(UUID id) {
        var turma = this.findById(id)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada para o id infromado"));

        return new TurmaAllDTO(turma);
    }

    public List<TurmaDefaultDTO> filterParam(String periodoIndex, String serieIndex, String turmaIndex) {
        var turmas =  this.getRepository().filtersParams(periodoIndex, serieIndex, turmaIndex);

        return TurmaDefaultDTO.convertTurmaToDTO(turmas);
    }

    //TODO verificar se tem id, se sem remover
    public TurmaDefaultDTO saveTurma(TurmaDefaultDTO turmaDefaultDTO) {
        var turma = this.converter.convert(turmaDefaultDTO);

        return this.save(turma);
    }

    public TurmaDefaultDTO updateTurma(TurmaDefaultDTO turmaDefaultDTO) {
        var turma = this.converter.convert(turmaDefaultDTO);

        if (Objects.isNull(turma))
            throw new BadRequestException("Erro ao converter turma");
        else if (Objects.isNull(turma.getIdTurma()) || turma.getIdTurma().toString().isEmpty())
            throw new BadRequestException("É necessario enviar o id da turma");

        return this.save(turma);
    }

    public void deleteTurma(UUID id) {
        this.findTurmaById(id);
        this.deleteById(id);
    }
}
