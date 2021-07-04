package br.com.myApp.MyApp.service;

import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.converters.DiciplinaConverter;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaAllDTO;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;
import br.com.myApp.MyApp.repository.DiciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DiciplinaService extends BaseService<DiciplinaRepository, Diciplina, DiciplinaDefaultDTO>{

    private final DiciplinaConverter converter;
    private final ProfessorService professorService;

    @Autowired
    public DiciplinaService(DiciplinaRepository diciplinaRepository, DiciplinaConverter converter, ProfessorService professorService) {
        super(diciplinaRepository);
        this.converter = converter;
        this.professorService = professorService;
    }

    public DiciplinaAllDTO findDiciplinaById(UUID id) {
        var diciplina = this.findById(id)
                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontra a diciplina"));

        return new DiciplinaAllDTO(diciplina);
    }



}
