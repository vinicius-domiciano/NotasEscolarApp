package br.com.myApp.MyApp.resource;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.Materia;
import br.com.myApp.MyApp.model.Professor;
import br.com.myApp.MyApp.model.Turma;
import br.com.myApp.MyApp.model.converters.DiciplinaConverter;
import br.com.myApp.MyApp.model.converters.MateriaConverter;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaAllDTO;
import br.com.myApp.MyApp.model.dto.diciplina.DiciplinaDefaultDTO;
import br.com.myApp.MyApp.model.dto.turma.TurmaIdentifyDTO;
import br.com.myApp.MyApp.repository.DiciplinaRepository;
import br.com.myApp.MyApp.repository.TurmaRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/escola/diciplinas")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiciplinaResource {

    private final DiciplinaRepository diciplinaRepository;
    private final TurmaRepository turmaRepository;

    public DiciplinaResource(DiciplinaRepository diciplinaRepository, TurmaRepository turmaRepository) {
        this.diciplinaRepository = diciplinaRepository;
        this.turmaRepository = turmaRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> addDiciplina(@Valid @RequestBody DiciplinaDefaultDTO requestBody) {
        UUID idProfessor = requestBody.getProfessorIdentify().getIdProfessor();
        UUID idMateria = requestBody.getMateria().getIdMateria();

        if (idMateria == null || idMateria.toString().isEmpty())
            throw new BadRequestException("Ops, é necessario informar o id da materia");
        else if (idProfessor == null || idProfessor.toString().isEmpty())
            throw new BadRequestException("Ops, é necessario informar o id do professor");

        Diciplina diciplinaConverted = new DiciplinaConverter().convert(requestBody);
        diciplinaConverted.setProfessor(
                new Professor(
                        requestBody.getProfessorIdentify().getIdProfessor(),
                        requestBody.getProfessorIdentify().getNome()
                )
        );
        diciplinaConverted.setMateria(
                new MateriaConverter().convert(requestBody.getMateria())
        );

        Diciplina diciplina = diciplinaRepository.save(diciplinaConverted);

        return new ResponseEntity<>(
                new DiciplinaDefaultDTO(diciplina),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/{idDiciplina}/adicionar/turmas")
    public ResponseEntity<?> adicionarTurmaDiciplina(@PathVariable UUID idDiciplina,
                                                     @Valid @RequestBody List<TurmaIdentifyDTO> turmas) {
        Diciplina diciplina = diciplinaRepository
                .findById(idDiciplina)
                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontra a diciplina"));

        List<TurmaIdentifyDTO> turmasExistente = turmas
                .stream()
                .filter(turmaIdentifyDTO -> diciplina.getTurmas().indexOf(turmaIdentifyDTO) >= 1)
                .collect(Collectors.toList());

        if (!turmasExistente.isEmpty()) {
            throw new NotFoundException("Ops, alguma(s) turma(s) ja foram cadastrada(s)");
        }

        List<Turma> turmaList = turmas
                .stream()
                .map(turmaIdentifyDTO -> turmaRepository.findById(turmaIdentifyDTO.getIdTurma()).orElse(null))
                .collect(Collectors.toList());

        turmaList.stream()
                .filter(turma -> turma != null)
                .forEach(turma -> {
                    diciplina.addTurma(turma);
                });

        Diciplina diciplinaNew = diciplinaRepository.save(diciplina);
        System.out.println("******************************");
        System.out.println(diciplinaNew.toString());
        System.out.println("**************************");

        return new ResponseEntity<>(
                new DiciplinaAllDTO(diciplinaNew),
                HttpStatus.CREATED
        );
    }

    @GetMapping("")
    public ResponseEntity<?> listAllDiciplina() {
        List<Diciplina> diciplinas = diciplinaRepository.findAll();

        return new ResponseEntity<>(
                DiciplinaDefaultDTO.convertDiciplinaToDTO(diciplinas),
                HttpStatus.OK
        );
    }

    @GetMapping("/{idDiciplina}")
    public  ResponseEntity<?> searchDiciplina(@PathVariable UUID idDiciplina) {
        Diciplina diciplina = diciplinaRepository
                .findById(idDiciplina)
                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontra a diciplina"));

        return new ResponseEntity<>(
                new DiciplinaAllDTO(diciplina),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{idDiciplina}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiciplina(@PathVariable UUID idDiciplina) {
        Diciplina diciplina = diciplinaRepository
                .findById(idDiciplina)
                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar a diciplina"));

        diciplinaRepository.delete(diciplina);
    }

    @DeleteMapping("/{idDiciplina}/remover/turma/{idTurma}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTurmaDiciplina(@PathVariable UUID idDiciplina,
                                        @PathVariable UUID idTurma) {
        Diciplina diciplina = diciplinaRepository.findById(idDiciplina)
                .orElseThrow(() -> new NotFoundException("Ops, diciplina não foi encontrada"));
        Turma turma = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar a turma"));

        List<Turma> turmas = diciplina.getTurmas();
        turmas.remove(turma);

        diciplina.setTurmas(turmas);

        diciplinaRepository.save(diciplina);
    }

}
