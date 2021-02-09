package br.com.myApp.MyApp.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Turma;
import br.com.myApp.MyApp.model.converters.AlunoConverter;
import br.com.myApp.MyApp.model.dto.aluno.AlunoAllDTO;
import br.com.myApp.MyApp.model.dto.aluno.AlunoDefaultDTO;
import br.com.myApp.MyApp.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.repository.AlunoRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/escola/alunos")
public class AlunoResource {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TurmaRepository turmaRepository;

//	Listando todos os alunos
	@GetMapping("")
	public List<AlunoDefaultDTO> getAlunos() {
		List<AlunoDefaultDTO> alunoDTO = AlunoDefaultDTO.convertAlunoToDTO(alunoRepository.findAll());
		return alunoDTO;
	}
	
//	Buscando um aluno
	@GetMapping("/{idAluno}")
	public ResponseEntity<?> getAluno(@PathVariable UUID idAluno) {

		Aluno aluno = alunoRepository
				.findById(idAluno)
				.orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar o aluno"));

		return ResponseEntity.ok(new AlunoAllDTO(aluno));
	}

	/*Cadastrar Aluno*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlunoDefaultDTO adicionarAluno(@Valid @RequestBody AlunoDefaultDTO alunoDTO) {
		Aluno aluno = new AlunoConverter().convert(alunoDTO);
		return new AlunoDefaultDTO(alunoRepository.save(aluno));
	}

	/*Adicionanr Aluno a turma*/
	@PostMapping("/{idAluno}/adicionar/turma/{idTurma}")
	public ResponseEntity addAlunoTurma(@PathVariable UUID idAluno,
							  @PathVariable UUID idTurma) {

		Aluno aluno = alunoRepository.findById(idAluno)
				.orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar o aluno"));

		Turma turma = turmaRepository.findById(idTurma)
				.orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar a turma"));

		aluno.setTurma(turma);
		alunoRepository.save(aluno);

		String uriPath = "/" + idAluno + "/adicionar/turma/" + idTurma;

		return ResponseEntity.created(URI.create(uriPath)).build();
	}
	
}
