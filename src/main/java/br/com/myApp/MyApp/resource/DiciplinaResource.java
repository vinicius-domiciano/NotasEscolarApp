package br.com.myApp.MyApp.resource;

import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaAllDTO;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;
import br.com.myApp.MyApp.service.DiciplinaService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/escola/diciplinas", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { "*/*" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiciplinaResource {

    private final DiciplinaService diciplinaService;

    @Autowired
    public DiciplinaResource(DiciplinaService diciplinaService) {
        this.diciplinaService = diciplinaService;
    }

    @GetMapping
    public ResponseEntity<List<DiciplinaDefaultDTO>> listAllDiciplina() {
        return new ResponseEntity<>(this.diciplinaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idDiciplina}")
    public  ResponseEntity<DiciplinaAllDTO> searchDiciplina(@PathVariable(name = "idDiciplina") UUID idDiciplina) {
        return new ResponseEntity<>(this.diciplinaService.findDiciplinaById(idDiciplina), HttpStatus.OK);
    }

//
//    @PostMapping("")
//    public ResponseEntity<?> addDiciplina(@Valid @RequestBody DiciplinaDefaultDTO requestBody) {
//        UUID idProfessor = requestBody.getProfessorIdentify().getIdProfessor();
//        UUID idMateria = requestBody.getMateria().getIdMateria();
//
//        if (idMateria == null || idMateria.toString().isEmpty())
//            throw new BadRequestException("Ops, é necessario informar o id da materia");
//        else if (idProfessor == null || idProfessor.toString().isEmpty())
//            throw new BadRequestException("Ops, é necessario informar o id do professor");
//
//        Diciplina diciplinaConverted = new DiciplinaConverter().convert(requestBody);
//        diciplinaConverted.setProfessor(
//                new Professor(
//                        requestBody.getProfessorIdentify().getIdProfessor(),
//                        requestBody.getProfessorIdentify().getNome()
//                )
//        );
//        diciplinaConverted.setMateria(
//                new MateriaConverter().convert(requestBody.getMateria())
//        );
//
//        Diciplina diciplina = diciplinaRepository.save(diciplinaConverted);
//
//        return new ResponseEntity<>(
//                new DiciplinaDefaultDTO(diciplina),
//                HttpStatus.CREATED
//        );
//    }
//
//    @PostMapping("/{idDiciplina}/adicionar/turmas")
//    public ResponseEntity<?> adicionarTurmaDiciplina(@PathVariable UUID idDiciplina,
//                                                     @Valid @RequestBody List<TurmaIdentifyDTO> turmas) {
//        Diciplina diciplina = diciplinaRepository
//                .findById(idDiciplina)
//                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontra a diciplina"));
//
//        List<TurmaIdentifyDTO> turmasExistente = turmas
//                .stream()
//                .filter(turmaIdentifyDTO -> diciplina.getTurmas().indexOf(turmaIdentifyDTO) >= 1)
//                .collect(Collectors.toList());
//
//        if (!turmasExistente.isEmpty()) {
//            throw new NotFoundException("Ops, alguma(s) turma(s) ja foram cadastrada(s)");
//        }
//
//        List<Turma> turmaList = turmas
//                .stream()
//                .map(turmaIdentifyDTO -> turmaRepository.findById(turmaIdentifyDTO.getIdTurma()).orElse(null))
//                .collect(Collectors.toList());
//
//        turmaList.stream()
//                .filter(turma -> turma != null)
//                .forEach(turma -> {
//                    diciplina.addTurma(turma);
//                });
//
//        Diciplina diciplinaNew = diciplinaRepository.save(diciplina);
//        System.out.println("******************************");
//        System.out.println(diciplinaNew.toString());
//        System.out.println("**************************");
//
//        return new ResponseEntity<>(
//                new DiciplinaAllDTO(diciplinaNew),
//                HttpStatus.CREATED
//        );
//    }

//
//    @DeleteMapping("/{idDiciplina}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteDiciplina(@PathVariable UUID idDiciplina) {
//        Diciplina diciplina = diciplinaRepository
//                .findById(idDiciplina)
//                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar a diciplina"));
//
//        diciplinaRepository.delete(diciplina);
//    }
//
//    @DeleteMapping("/{idDiciplina}/remover/turma/{idTurma}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteTurmaDiciplina(@PathVariable UUID idDiciplina,
//                                        @PathVariable UUID idTurma) {
//        Diciplina diciplina = diciplinaRepository.findById(idDiciplina)
//                .orElseThrow(() -> new NotFoundException("Ops, diciplina não foi encontrada"));
//        Turma turma = turmaRepository.findById(idTurma)
//                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar a turma"));
//
//        List<Turma> turmas = diciplina.getTurmas();
//        turmas.remove(turma);
//
//        diciplina.setTurmas(turmas);
//
//        diciplinaRepository.save(diciplina);
//    }

}
