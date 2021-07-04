package br.com.myApp.MyApp.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.dto.materia.MateriaDefaultDTO;
import br.com.myApp.MyApp.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.myApp.MyApp.model.Materia;
import br.com.myApp.MyApp.repository.MateriaRepository;

@RestController
@RequestMapping(path = "/escola/materias", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.ALL_VALUE })
public class MateriaResource {

	private final MateriaService materiaService;

	@Autowired
	public MateriaResource(MateriaService materiaService) {
		this.materiaService = materiaService;
	}

	@GetMapping
	public ResponseEntity<List<MateriaDefaultDTO>> getMaterias() {
		return new ResponseEntity<>(this.materiaService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/buscar/{idMateria}")
	public ResponseEntity<MateriaDefaultDTO> getMateria(@PathVariable UUID idMateria) {
		return new ResponseEntity<>(this.materiaService.findMateriaById(idMateria), HttpStatus.OK) ;
	}

//	@PostMapping("")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Materia addMateria(@Valid @RequestBody Materia materia) {
//		return materiaRepository.save(materia);
//	}
//
//	@PutMapping("")
//	public ResponseEntity<?> atualizarMateria(@Valid @RequestBody Materia materia) {
//		if (materiaRepository.findById(materia.getIdMateria()).isPresent())
//			return ResponseEntity.ok(materiaRepository.save(materia));
//
//		throw new NotFoundException("Ops, não foi possivel atualizar, a materia não foi encontrada");
//	}
//
//	@DeleteMapping("/{idMateria}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void excluirMateria(@PathVariable UUID idMateria) {
//		if (materiaRepository.findById(idMateria).isPresent())
//			materiaRepository.deleteById(idMateria);
//
//		throw new NotFoundException("Ops, não foi possivel deletar, a materia não foi encontrada");
//	}
//
}
