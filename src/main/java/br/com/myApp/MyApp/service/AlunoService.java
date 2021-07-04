package br.com.myApp.MyApp.service;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.converters.AlunoConverter;
import br.com.myApp.MyApp.model.dto.aluno.AlunoAllDTO;
import br.com.myApp.MyApp.model.dto.aluno.AlunoDefaultDTO;
import br.com.myApp.MyApp.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class AlunoService extends BaseService<AlunoRepository, Aluno, AlunoDefaultDTO> {

    private final AlunoConverter converter;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, AlunoConverter converter) {
        super(alunoRepository);
        this.converter = converter;
    }

    public AlunoDefaultDTO saveAluno(AlunoDefaultDTO alunoDefaultDTO) {
        var aluno = this.converter.convert(alunoDefaultDTO);
        return this.save(aluno);
    }
    
    public AlunoAllDTO findAlunoById(UUID id) {
        var aluno = this.findById(id)
                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar o aluno"));
        
        return new AlunoAllDTO(aluno);
    }
    
    public AlunoDefaultDTO updateAluno(AlunoDefaultDTO alunoDefaultDTO) {
        var aluno = this.converter.convert(alunoDefaultDTO);
        
        if (Objects.isNull(alunoDefaultDTO.getIdAluno()) || alunoDefaultDTO.getIdAluno().toString().isEmpty())
            throw new BadRequestException("Necessario enviar o id");

        this.findAlunoById(alunoDefaultDTO.getIdAluno());

        return this.save(aluno);
    }

    public void deleteAluno(UUID id) {
        if (Objects.isNull(id) || id.toString().isEmpty())
            throw new BadRequestException("Necessario enviar o id correto");

        this.findAlunoById(id);
        this.deleteById(id);
    }

}
