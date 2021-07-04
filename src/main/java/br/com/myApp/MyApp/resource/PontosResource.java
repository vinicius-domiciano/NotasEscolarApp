package br.com.myApp.MyApp.resource;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Diciplina;
import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.Pontos;
import br.com.myApp.MyApp.model.converters.PontosConverter;
import br.com.myApp.MyApp.model.dto.notas.NotasIdentifyDTO;
import br.com.myApp.MyApp.model.dto.pontos.PontosAllDTO;
import br.com.myApp.MyApp.model.dto.pontos.PontosDefaultDTO;
import br.com.myApp.MyApp.repository.DiciplinaRepository;
import br.com.myApp.MyApp.repository.NotasRepository;
import br.com.myApp.MyApp.repository.PontosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/escola/pontos")
public class PontosResource {

    private final PontosRepository pontosRepository;
    private final NotasRepository notasRepository;
    private final DiciplinaRepository diciplinaRepository;

    public PontosResource(PontosRepository pontosRepository, NotasRepository notasRepository, DiciplinaRepository diciplinaRepository) {
        this.pontosRepository = pontosRepository;
        this.notasRepository = notasRepository;
        this.diciplinaRepository = diciplinaRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> addPontos (@RequestBody @Valid PontosDefaultDTO ponto) {
        if (ponto.getNotaIdentify().getIdNota() == null)
            throw new BadRequestException("Ops, é necessario enviar o id da nota");
        else if (ponto.getDiciplinaIdentify().getIdDiciplina() == null)
            throw new BadRequestException("Ops, é necessario enviar o id da diciplina");

        UUID idNota = ponto.getNotaIdentify().getIdNota();
        UUID idDiciplina = ponto.getDiciplinaIdentify().getIdDiciplina();
        Notas nota = notasRepository.findById(idNota).orElse(null);
        Diciplina diciplina = diciplinaRepository.findById(idDiciplina).orElse(null);

        if (nota == null || idNota.toString().isEmpty())
            throw new NotFoundException("Ops, Não foi possivel encontrar a nota para o id fornecido");
        else if (diciplina == null || idDiciplina.toString().isEmpty())
            throw new NotFoundException("Ops, Não foi possivel encontrar a diciplina para o id fornecido");
            
        Pontos pontoConverted = new PontosConverter().convert(ponto);
        pontoConverted.setDiciplina(diciplina);
        pontoConverted.setNota(nota);

        PontosDefaultDTO newPonto = new PontosDefaultDTO(pontosRepository.save(pontoConverted));

        return new ResponseEntity<>(newPonto, HttpStatus.CREATED);
    }

    @GetMapping("/{idPonto}")
    public ResponseEntity<?> searchPonto(@PathVariable UUID idPonto) {
        if (idPonto == null || idPonto.toString().isEmpty())
            throw new BadRequestException("Ops, Não foi possivel indentificar id informado");

        Pontos ponto = pontosRepository.findById(idPonto).orElse(null);

        if (ponto == null)
            throw new NotFoundException("Ops, não foi possivel encontrar o ponto pelo id informado");

        return new ResponseEntity<>(new PontosAllDTO(ponto), HttpStatus.OK);
    }
}
