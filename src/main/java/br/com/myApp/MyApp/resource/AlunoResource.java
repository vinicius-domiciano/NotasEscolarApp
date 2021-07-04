package br.com.myApp.MyApp.resource;

import java.util.List;
import java.util.UUID;

import br.com.myApp.MyApp.model.dto.aluno.AlunoAllDTO;
import br.com.myApp.MyApp.model.dto.aluno.AlunoDefaultDTO;
import br.com.myApp.MyApp.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/escola/alunos", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { "*/*" })
public class AlunoResource {

	private final AlunoService alunoService;

	@Autowired
	public AlunoResource(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	// Listando todos os alunos
	@GetMapping
	public ResponseEntity<List<AlunoDefaultDTO>> findAllAluno() {
		return new ResponseEntity<>(this.alunoService.findAll(), HttpStatus.OK);
	}

	// Buscando um aluno
	@GetMapping("/buscar/{idAluno}")
	public ResponseEntity<AlunoAllDTO> getAluno(@PathVariable(name = "idAluno") UUID idAluno) {
		return new ResponseEntity<>(this.alunoService.findAlunoById(idAluno), HttpStatus.OK);
	}

	// Cadastrar Aluno
	@PostMapping
	public ResponseEntity<AlunoDefaultDTO> adicionarAluno(@Valid @RequestBody AlunoDefaultDTO alunoDTO) {
		return new ResponseEntity<>(this.alunoService.saveAluno(alunoDTO), HttpStatus.CREATED);
	}

	// Atualizar aluno
	@PutMapping("/atualizar")
	public ResponseEntity<AlunoDefaultDTO> atualizarAluno(@RequestBody @Valid AlunoDefaultDTO alunoAllDTO) {
		return new ResponseEntity<>(this.alunoService.updateAluno(alunoAllDTO), HttpStatus.OK);
	}

	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAluno(@PathVariable(name = "id") UUID idAluno) {
		this.alunoService.deleteAluno(idAluno);
	}
//
//
//	/*Adicionanr Aluno a turma*/
//	@PostMapping("/{idAluno}/adicionar/turma/{idTurma}")
//	public ResponseEntity addAlunoTurma(@PathVariable UUID idAluno,
//							  @PathVariable UUID idTurma) {
//
//		Aluno aluno = this.findById(idAluno)
//				.orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar o aluno"));
//
//		Turma turma = turmaRepository.findById(idTurma)
//				.orElseThrow(() -> new NotFoundException("Ops, não foi possivel encontrar a turma"));
//
//		aluno.setTurma(turma);
//		this.save(aluno);
//
//		String uriPath = "/" + idAluno + "/adicionar/turma/" + idTurma;
//
//		return ResponseEntity.created(URI.create(uriPath)).build();
//	}
}
