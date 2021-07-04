package br.com.myApp.MyApp.service;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.Materia;
import br.com.myApp.MyApp.model.Professor;
import br.com.myApp.MyApp.model.converters.DiciplinaConverter;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaAllDTO;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;
import br.com.myApp.MyApp.repository.DiciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class DiciplinaService extends BaseService<DiciplinaRepository, Diciplina, DiciplinaDefaultDTO>{

    private final DiciplinaConverter converter;
    private final MateriaService materiaService;
    private final ProfessorService professorService;

    @Autowired
    public DiciplinaService(DiciplinaRepository diciplinaRepository, DiciplinaConverter converter, MateriaService materiaService, ProfessorService professorService) {
        super(diciplinaRepository);
        this.converter = converter;
        this.materiaService = materiaService;
        this.professorService = professorService;
    }

    public DiciplinaAllDTO findDiciplinaById(UUID id) {
        var diciplina = this.findById(id)
                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontra a diciplina"));

        return new DiciplinaAllDTO(diciplina);
    }

    //TODO verificar se existe o id, se sim remover
    public DiciplinaDefaultDTO saveDiciplina(DiciplinaDefaultDTO diciplinaDTO) {
        var diciplina = this.converter.convert(diciplinaDTO);

        if (Objects.isNull(diciplina)) throw new BadRequestException("Erro ao converter diciplina");

        this.existProfessor(diciplina.getProfessor());
        this.existMateria(diciplina.getMateria());

        return this.save(diciplina);
    }

    public DiciplinaDefaultDTO updateDiciplina(DiciplinaDefaultDTO diciplinaDefaultDTO) {
        if (Objects.isNull(diciplinaDefaultDTO.getIdDiciplina()) || diciplinaDefaultDTO.getIdDiciplina().toString().isEmpty())
            throw new BadRequestException("Erro, necessario enviar o id da diciplina");

        var diciplina = this.converter.convert(diciplinaDefaultDTO);

        if (Objects.isNull(diciplina)) throw new BadRequestException("Erro ao converter diciplina");

        this.findDiciplinaById(diciplina.getIdDiciplina());
        this.existProfessor(diciplina.getProfessor());
        this.existMateria(diciplina.getMateria());

        return this.save(diciplina);
    }

    public void deleteDiciplina(UUID id) {
        this.findDiciplinaById(id);
        this.deleteById(id);
    }

    private void existProfessor(Professor professor) {
        this.professorService.findProfessorById(professor.getIdProfessor());
    }

    private void existMateria(Materia materia) {
        this.materiaService.findMateriaById(materia.getIdMateria());
    }

}
