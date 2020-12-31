package br.com.myApp.MyApp.resource;

import br.com.myApp.MyApp.model.Materia;
import br.com.myApp.MyApp.model.Professor;
import br.com.myApp.MyApp.model.dto.professor.ProfessorAllDTO;
import br.com.myApp.MyApp.model.dto.professor.ProfessorDefaultDTO;
import br.com.myApp.MyApp.repository.MateriaRepository;
import br.com.myApp.MyApp.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/escola/professores")
public class ProfessorResource {

    @Autowired
    private final ProfessorRepository professorRepository;

    @Autowired
    private final MateriaRepository materiaRepository;

    public ProfessorResource(ProfessorRepository professorRepository, MateriaRepository materiaRepository) {
        this.professorRepository = professorRepository;
        this.materiaRepository = materiaRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<ProfessorDefaultDTO>> listProfessor() {
        return new ResponseEntity<>(ProfessorDefaultDTO.convertProfessorToDTO(professorRepository.findAll()),
                HttpStatus.OK);
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<Object> searchProfessor(@PathVariable UUID idProfessor) {
        Professor professor = professorRepository.findById(idProfessor).orElse(null);

        if (professor == null)
            return new ResponseEntity<>("{'Error':'Ops. Não foi posivel encontrar o professor'}",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ProfessorAllDTO(professor), HttpStatus.OK);
    }

    @PostMapping("")
    public Professor addProfessor(@RequestBody @Valid Professor professor) {
        return professorRepository.save(professor);
    }

    @PostMapping("/{idProfessor}/adicionar/materia/{idMateria}")
    public ResponseEntity<Object> addMateriaProfessor(@PathVariable UUID idProfessor,
                                                      @PathVariable UUID idMateria) {
        Professor professor = professorRepository.findById(idProfessor).orElse(null);
        Materia materia = materiaRepository.findById(idMateria).orElse(null);

        if (professor == null)
            return new ResponseEntity<>("Não foi possivel adicionar, professor não encontrado", HttpStatus.NOT_FOUND);
        else if (materia == null)
            return new ResponseEntity<>("Não foi possivel adicionar, materia não encontrado", HttpStatus.NOT_FOUND);


        if(professor.getMaterias().indexOf(materia) > 0)
            return new ResponseEntity<>("Não foi possivel adicionar, materia já cadastrada", HttpStatus.BAD_REQUEST);

        professor.getMaterias().add(materia);
        professorRepository.save(professor);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
