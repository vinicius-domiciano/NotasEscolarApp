package br.com.myApp.MyApp.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
@RequestMapping("/escola")
public class MateriaResource {

	@Autowired
	private MateriaRepository materiaRepository;
	
	@GetMapping("/materias")
	public List<Materia> getMaterias() {
		return materiaRepository.findAll();
	}
	
	@GetMapping("/materias/{idMateria}")
	public ResponseEntity<?> getMateria(@PathVariable Long idMateria ) {
		Optional<Materia> materia = materiaRepository.findById(idMateria); 
		return materia.isPresent() ?
				ResponseEntity.ok(materia.get()) :
				ResponseEntity.notFound().build();
					
	}
	
	@PostMapping("/materias")
	@ResponseStatus(HttpStatus.CREATED)
	public Materia addMateria(@Valid @RequestBody Materia materia) {
		return materiaRepository.save(materia);
	}
	
	@PutMapping("/materias")
	public ResponseEntity<?> atualizarMateria(@Valid @RequestBody Materia materia) {
		if (materiaRepository.findById(materia.getId_materia()).isPresent())
			return ResponseEntity.ok(materiaRepository.save(materia));
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/materias/{idMateria}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirMateria(@PathVariable Long idMateria) {
		if (materiaRepository.findById(idMateria).isPresent())
			materiaRepository.deleteById(idMateria);
		
	}
	
}
