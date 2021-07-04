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
import br.com.myApp.MyApp.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final TurmaService turmaService;

    @Autowired
    public TurmaResource(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping("")
    public ResponseEntity<List<TurmaDefaultDTO>> listAllTurmas() {
        return new ResponseEntity<>(this.turmaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idTurma}")
    public ResponseEntity<TurmaAllDTO> searchByIdTurma(@PathVariable(name = "idTurma") UUID idTurma) {
        return new ResponseEntity<>(this.turmaService.findTurmaById(idTurma), HttpStatus.OK);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<TurmaDefaultDTO>> listByFiltersTurma(
        @RequestParam(name = "serie", required = false, defaultValue = "") String serieIndex,
        @RequestParam(name = "periodo", required = false, defaultValue = "") String periodoIndex,
        @RequestParam(name = "turma", required = false, defaultValue = "") String turmaIndex
    ) {
        return new ResponseEntity<>(this.turmaService.filterParam(periodoIndex, serieIndex, turmaIndex), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TurmaDefaultDTO> addTurma(@Valid @RequestBody TurmaDefaultDTO turmaDTO) {
        return new ResponseEntity<>(this.turmaService.saveTurma(turmaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<TurmaDefaultDTO> updateTurma(@Valid @RequestBody TurmaDefaultDTO turmaDTO) {
        return new ResponseEntity<>(this.turmaService.updateTurma(turmaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{idTurma}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTurma(@PathVariable(name = "idTurma") UUID idTurma) {
        this.turmaService.deleteTurma(idTurma);
    }

}
