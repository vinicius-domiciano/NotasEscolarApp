package br.com.myApp.MyApp.resource;

import br.com.myApp.MyApp.model.dto.notas.NotasAllDTO;
import br.com.myApp.MyApp.model.dto.notas.NotasDefaultDTO;
import br.com.myApp.MyApp.service.NotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/escola/notas", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.ALL_VALUE })
public class NotasResource {

    private final NotasService notasService;

    @Autowired
    public NotasResource(NotasService notasService) {
        this.notasService = notasService;
    }

    @GetMapping("/buscar/{idNota}")
    public ResponseEntity<NotasAllDTO> searchDetaisNota(@PathVariable(name = "idNota") UUID idNota) {
        return new ResponseEntity<>(this.notasService.findNotasById(idNota), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NotasDefaultDTO> adicionarNota(@RequestBody @Valid NotasDefaultDTO notasDefaultDTO) {
        return new ResponseEntity<>(this.notasService.saveNota(notasDefaultDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{idNota}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "idNota") UUID idNota) {
        this.notasService.deleteNota(idNota);
    }
}
