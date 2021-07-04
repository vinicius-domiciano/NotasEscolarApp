package br.com.myApp.MyApp.service;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Professor;
import br.com.myApp.MyApp.model.converters.ProfessorConverter;
import br.com.myApp.MyApp.model.dto.professor.ProfessorAllDTO;
import br.com.myApp.MyApp.model.dto.professor.ProfessorDefaultDTO;
import br.com.myApp.MyApp.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class ProfessorService extends BaseService<ProfessorRepository, Professor, ProfessorDefaultDTO> {

    private final ProfessorConverter converter;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, ProfessorConverter converter) {
        super(professorRepository);
        this.converter = converter;
    }

    public ProfessorAllDTO findProfessorById(UUID id) {
        var professor = this.findById(id)
                .orElseThrow(() -> new NotFoundException("Ops. Não foi posivel encontrar o professor"));

        return new ProfessorAllDTO(professor);
    }

    //TODO Validar se tem id, para remover
    public ProfessorDefaultDTO saveProfessor(ProfessorDefaultDTO professorDefaultDTO) {
        var professor = this.converter.convert(professorDefaultDTO);

        return this.save(professor);
    }

    public ProfessorDefaultDTO updateProfessor(ProfessorDefaultDTO professorDefaultDTO) {
        var professor = this.converter.convert(professorDefaultDTO);

        if (Objects.isNull(professorDefaultDTO.getIdProfessor()) || professorDefaultDTO.getIdProfessor().toString().isEmpty())
            throw new BadRequestException("Necessario enviar o id");

        this.findProfessorById(professorDefaultDTO.getIdProfessor());

        return this.save(professor);
    }

    public void deleteProfessor(UUID id) {
        if (Objects.isNull(id) || id.toString().isEmpty())
            throw new BadRequestException("Necessario enviar o id");

        this.findProfessorById(id);
        this.deleteById(id);
    }
}
