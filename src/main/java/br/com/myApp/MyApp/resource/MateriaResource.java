package br.com.myApp.MyApp.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import br.com.myApp.MyApp.model.dto.materia.MateriaDefaultDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/escola/materias")
public class MateriaResource {

	@Autowired
	private MateriaRepository materiaRepository;
	
	@GetMapping("")
	public ResponseEntity<List<MateriaDefaultDTO>> getMaterias() {
		List<Materia> materias = materiaRepository.findAll();
		return new ResponseEntity<>(
				MateriaDefaultDTO.convertMateriaToDTO(materias),
				HttpStatus.OK);
	}
	
	@GetMapping("/{idMateria}")
	public ResponseEntity<?> getMateria(@PathVariable UUID idMateria) {
		Optional<Materia> materia = materiaRepository.findById(idMateria);
		return materia.isPresent() ?
				ResponseEntity.ok(new MateriaDefaultDTO(materia.get())) :
				ResponseEntity.notFound().build();

	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Materia addMateria(@Valid @RequestBody Materia materia) {
		return materiaRepository.save(materia);
	}
	
	@PutMapping("")
	public ResponseEntity<?> atualizarMateria(@Valid @RequestBody Materia materia) {
		if (materiaRepository.findById(materia.getIdMateria()).isPresent())
			return ResponseEntity.ok(materiaRepository.save(materia));

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{idMateria}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirMateria(@PathVariable UUID idMateria) {
		if (materiaRepository.findById(idMateria).isPresent())
			materiaRepository.deleteById(idMateria);
		
	}
	
}
