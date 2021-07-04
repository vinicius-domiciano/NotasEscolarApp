package br.com.myApp.MyApp.resource;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.Pontos;
import br.com.myApp.MyApp.model.converters.PontosConverter;
import br.com.myApp.MyApp.model.dto.pontos.PontosAllDTO;
import br.com.myApp.MyApp.model.dto.pontos.PontosDefaultDTO;
import br.com.myApp.MyApp.service.PontosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/escola/pontos", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.ALL_VALUE })
public class PontosResource {

    private final PontosService pontosService;

    @Autowired
    public PontosResource(PontosService pontosService) {
        this.pontosService = pontosService;
    }

    @GetMapping("/buscar/{idPonto}")
    public ResponseEntity<PontosAllDTO> searchPonto(@PathVariable(name = "idPonto") UUID idPonto) {
        return new ResponseEntity<>(this.pontosService.findPontoById(idPonto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PontosDefaultDTO> addPontos (@RequestBody @Valid PontosDefaultDTO ponto) {
        return new ResponseEntity<>(this.pontosService.savePonto(ponto), HttpStatus.CREATED);
    }
}
