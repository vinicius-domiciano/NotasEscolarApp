package br.com.myApp.MyApp.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.myApp.MyApp.model.dto.AlunoDTO;
import org.hibernate.type.UUIDCharType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myApp.MyApp.model.Aluno;
import br.com.myApp.MyApp.repository.AlunoRepository;

@RestController
@RequestMapping("/escola/alunos")
public class AlunoResource {

	@Autowired
	private AlunoRepository alunoRepository;
	
//	Listando todos os alunos
	@GetMapping("")
	public List<AlunoDTO> getAlunos() {
		List<AlunoDTO> alunoDTO = AlunoDTO.convertAlunoToDTO(alunoRepository.findAll());
		return alunoDTO;
	}
	
//	Buscando um aluno
	@GetMapping("/{idAluno}")
	public ResponseEntity<?> getAluno(@PathVariable UUID idAluno) {

		Optional<Aluno> aluno = alunoRepository.findById(idAluno);
		return aluno.isPresent() ?
				ResponseEntity.ok(new AlunoDTO(aluno.get())) :
				ResponseEntity.notFound().build();
	}
	
}
