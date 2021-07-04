package br.com.myApp.MyApp.resource;

import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Turma;
import br.com.myApp.MyApp.model.converters.TurmaConverter;
import br.com.myApp.MyApp.model.dto.turma.TurmaAllDTO;
import br.com.myApp.MyApp.model.dto.turma.TurmaDefaultDTO;
import br.com.myApp.MyApp.model.enumerations.PeriodoEnum;
import br.com.myApp.MyApp.model.enumerations.SerieEnum;
import br.com.myApp.MyApp.model.enumerations.TurmaEnum;
import br.com.myApp.MyApp.repository.TurmaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/escola/turmas", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.ALL_VALUE })
public class TurmaResource {

    private final TurmaRepository turmaRepository;

    public TurmaResource(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> addTurma(@Valid @RequestBody TurmaDefaultDTO turmaDTO) {
        Turma turmaConverted = new TurmaConverter().convert(turmaDTO);
        Turma turma = turmaRepository.save(turmaConverted);

        return new ResponseEntity<>(turma, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> listAllTurmas() {
        List<TurmaDefaultDTO> turmas = TurmaDefaultDTO
                .convertTurmaToDTO(turmaRepository.findAll());

        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    @GetMapping("/{idTurma}")
    public ResponseEntity<?> searchByIdTurma(@PathVariable UUID idTurma) {
        Turma turma = turmaRepository.findById(idTurma).orElse(null);
        if (turma == null)
            throw new NotFoundException("Ops, não foi possivel encontrar a turma.");

        return new ResponseEntity<>(
                new TurmaAllDTO(turma),
                HttpStatus.OK
        );
    }

    @GetMapping("/filters")
    public ResponseEntity<?> listByFiltersTurma(
        @RequestParam(name = "serie", required = false, defaultValue = "") String serieIndex,
        @RequestParam(name = "periodo", required = false, defaultValue = "") String periodoIndex,
        @RequestParam(name = "turma", required = false, defaultValue = "") String turmaIndex
    ) {
        List<TurmaDefaultDTO> turmas = TurmaDefaultDTO
                .convertTurmaToDTO(turmaRepository.filtersParams(periodoIndex, serieIndex, turmaIndex));

        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    @DeleteMapping("/{idTurma}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTurma(@PathVariable UUID idTurma) {
        Turma turma = turmaRepository.findById(idTurma).orElse(null);

        if (turma != null)
            turmaRepository.deleteById(idTurma);
    }

}
