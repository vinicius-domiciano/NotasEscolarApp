package br.com.myApp.MyApp.resource;

import br.com.myApp.MyApp.model.dto.professor.ProfessorAllDTO;
import br.com.myApp.MyApp.model.dto.professor.ProfessorDefaultDTO;
import br.com.myApp.MyApp.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/escola/professores", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.ALL_VALUE })
public class ProfessorResource {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorResource(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDefaultDTO>> listProfessor() {
        return new ResponseEntity<>(this.professorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<ProfessorAllDTO> searchProfessor(@PathVariable(name = "idProfessor") UUID idProfessor) {
        return new ResponseEntity<>(this.professorService.findProfessorById(idProfessor), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessorDefaultDTO> addProfessor(@RequestBody @Valid ProfessorDefaultDTO professor) {
        return new ResponseEntity<>(this.professorService.saveProfessor(professor), HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{idProfessor}")
    public ResponseEntity<ProfessorDefaultDTO> updateProfessor(@RequestBody @Valid ProfessorDefaultDTO professorDTO) {
        return new ResponseEntity<>(this.professorService.updateProfessor(professorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{idProfessor}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProf(@PathVariable(name = "idProfessor") UUID idProfessor) {
        this.professorService.deleteProfessor(idProfessor);
    }

//
//    @PostMapping("/{idProfessor}/adicionar/materia/{idMateria}")
//    public ResponseEntity<Object> addMateriaProfessor(@PathVariable UUID idProfessor,
//                                                      @PathVariable UUID idMateria) {
//        Professor professor = professorRepository.findById(idProfessor).orElse(null);
//        Materia materia = materiaRepository.findById(idMateria).orElse(null);
//
//        if (professor == null)
//            throw new NotFoundException("Não foi possivel adicionar, professor não encontrado");
//        else if (materia == null)
//            throw new NotFoundException("Não foi possivel adicionar, materia não encontrado");
//
//        if(professor.getMaterias().indexOf(materia) > 0)
//            return new ResponseEntity<>("Não foi possivel adicionar, materia já cadastrada", HttpStatus.BAD_REQUEST);
//
//        professor.getMaterias().add(materia);
//        professorRepository.save(professor);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
