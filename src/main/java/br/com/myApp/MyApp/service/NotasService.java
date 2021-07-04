package br.com.myApp.MyApp.service;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.converters.AlunoConverter;
import br.com.myApp.MyApp.model.converters.AlunoIdentifyConverter;
import br.com.myApp.MyApp.model.converters.NotasConverter;
import br.com.myApp.MyApp.model.dto.aluno.AlunoIdentifyDTO;
import br.com.myApp.MyApp.model.dto.notas.NotasAllDTO;
import br.com.myApp.MyApp.model.dto.notas.NotasDefaultDTO;
import br.com.myApp.MyApp.model.enumerations.BimestreEnum;
import br.com.myApp.MyApp.repository.NotasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.UUID;

public class NotasService extends BaseService<NotasRepository, Notas, NotasDefaultDTO>{

    private final NotasConverter converter;
    private final AlunoService alunoService;

    @Autowired
    public NotasService(NotasRepository notasRepository, NotasConverter converter, AlunoService alunoService) {
        super(notasRepository);
        this.converter = converter;
        this.alunoService = alunoService;
    }

    public NotasAllDTO findNotasById(UUID id) {
        var nota = this.findById(id)
                .orElseThrow(() -> new NotFoundException("Ops. Não foi posivel encontrar a nota"));

        return new NotasAllDTO(nota);
    }

    public NotasAllDTO findNotaByAnoAndBimestreAndAluno(int ano, BimestreEnum bimestre, Aluno aluno) {
        var nota = this.getRepository()
                .findByAnoAndBimestreAndAluno(ano, bimestre, aluno)
                .orElseGet(Notas::new);

        return new NotasAllDTO(nota);
    }

    public NotasDefaultDTO saveNota(NotasDefaultDTO notasDefaultDTO) {
        if (Objects.isNull(notasDefaultDTO.getAlunoIdentify()) || Objects.isNull(notasDefaultDTO.getAlunoIdentify().getIdAluno()) || notasDefaultDTO.getAlunoIdentify().getIdAluno().toString().isEmpty())
            throw new BadRequestException("Ops. É necessario enviar o id do aluno");

        var idAluno = notasDefaultDTO.getAlunoIdentify().getIdAluno();
        var alunoDTO = this.alunoService.findAlunoById(idAluno);
        var aluno = new AlunoConverter().convert(alunoDTO);

        var notaDTO = this.findNotaByAnoAndBimestreAndAluno(
                notasDefaultDTO.getAno(),
                notasDefaultDTO.getBimestre(),
                aluno
        );

        if (Objects.nonNull(notaDTO.getIdNota()))
            throw new BadRequestException("Ops. A nota para esse aluno ja existe");
        else if (Objects.isNull(aluno))
            throw new BadRequestException("Erro, aluno não encontrado");

        notasDefaultDTO.setAlunoIdentify(new AlunoIdentifyDTO(aluno));
        var nota = this.converter.convert(notasDefaultDTO);

        return this.save(nota);
    }

    public void deleteNota(UUID id) {
        if (Objects.isNull(id) || id.toString().isEmpty())
            throw new BadRequestException("Ops. É necessario enviar o id da nota");

        this.findNotasById(id);
        this.deleteById(id);
    }

}
