package br.com.myApp.MyApp.resource;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.model.Notas;
import br.com.myApp.MyApp.model.converters.AlunoIdentifyConverter;
import br.com.myApp.MyApp.model.converters.NotasConverter;
import br.com.myApp.MyApp.model.dto.aluno.AlunoIdentifyDTO;
import br.com.myApp.MyApp.model.dto.notas.NotasAllDTO;
import br.com.myApp.MyApp.model.dto.notas.NotasDefaultDTO;
import br.com.myApp.MyApp.repository.AlunoRepository;
import br.com.myApp.MyApp.repository.NotasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/escola/notas", headers = "Accept=application/json")
public class NotasResource {

    private final NotasRepository notasRepository;
    private final AlunoRepository alunoRepository;

    public NotasResource(NotasRepository notasRepository, AlunoRepository alunoRepository) {
        this.notasRepository = notasRepository;
        this.alunoRepository = alunoRepository;
    }

    @GetMapping("/{idNota}")
    public ResponseEntity<?> searchDetaisNota(@PathVariable UUID idNota) {
        Notas nota = notasRepository.findById(idNota).orElse(null);

        if (nota == null)
            return new ResponseEntity<>("{'Error':'Ops. Não foi posivel encontrar a nota'}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new NotasAllDTO(nota), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> adicionarNota(@RequestBody @Valid NotasDefaultDTO notasDefaultDTO) {
        if (notasDefaultDTO.getAlunoIdentify().getIdAluno() == null)
            return new ResponseEntity<>("{\"Error\":\"Ops. É necessario enviar o id do aluno\"}",
                    HttpStatus.BAD_REQUEST);

        UUID idAluno = notasDefaultDTO.getAlunoIdentify().getIdAluno();
        Aluno aluno = alunoRepository.findById(idAluno).orElse(null);

        if(idAluno.toString().isEmpty() || idAluno == null || aluno == null)
            return new ResponseEntity<>("{\"Error\":\"Ops. Não foi posivel encontrar o aluno\"}",
                    HttpStatus.NOT_FOUND);

        Optional<Notas> notaOptional = notasRepository
                .findByAnoAndBimestreAndAluno(notasDefaultDTO.getAno(),
                    notasDefaultDTO.getBimestre(),
                    new AlunoIdentifyConverter().convert(notasDefaultDTO.getAlunoIdentify()));

        if (notaOptional.isPresent())
            return new ResponseEntity<>("{\"Error\":\"Ops. A nota para esse aluno ja existe\"}",
                    HttpStatus.BAD_REQUEST);

        notasDefaultDTO.setAlunoIdentify(new AlunoIdentifyDTO(aluno));
        Notas notaConverted = new NotasConverter().convert(notasDefaultDTO);

        return new ResponseEntity<>(new NotasDefaultDTO(notasRepository.save(notaConverted)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{idNota}")
    public ResponseEntity<String> delete(@PathVariable UUID idNota) {
        Notas nota = notasRepository.findById(idNota).orElse(null);

        if (nota == null)
            return new ResponseEntity<>("{\"Error\":\"Ops. Não foi posivel encontrar a nota\"}",
                    HttpStatus.NOT_FOUND);

        notasRepository.delete(nota);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
