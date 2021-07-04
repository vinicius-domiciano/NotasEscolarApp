package br.com.myApp.MyApp.resource;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

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
	public ResponseEntity<MateriaDefaultDTO> getMateria(@PathVariable(name = "idMateria") UUID idMateria) {
		return new ResponseEntity<>(this.materiaService.findMateriaById(idMateria), HttpStatus.OK) ;
	}

	@PostMapping
	public ResponseEntity<MateriaDefaultDTO> addMateria(@Valid @RequestBody MateriaDefaultDTO materia) {
		return new ResponseEntity<>(this.materiaService.saveMateria(materia), HttpStatus.CREATED);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<MateriaDefaultDTO> atualizarMateria(@Valid @RequestBody MateriaDefaultDTO materia) {
		return new ResponseEntity<>(this.materiaService.updateMateria(materia), HttpStatus.OK);
	}

	@DeleteMapping("/deletar/{idMateria}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirMateria(@PathVariable(name = "idMateria") UUID idMateria) {
		this.materiaService.deleteMateria(idMateria);
	}

}
