package dio.gof.pokemon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.gof.pokemon.model.Trainer;
import dio.gof.pokemon.service.TrainerService;

@RestController
@RequestMapping("trainers")
public class TrainerRestController {

	@Autowired
	private TrainerService trainerService;
	
	@GetMapping
	public List<Trainer> getAllTrainers() {
		return trainerService.getAllTrainers();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Trainer>> getById(@PathVariable Long id){
		return ResponseEntity.ok(trainerService.getTrainerById(id));
	}
	
	@PostMapping
	public Trainer create(@RequestBody Trainer trainer) { 
		return trainerService.saveTrainer(trainer);
	}
	
	@PutMapping("/{id}/name")
	public ResponseEntity<Optional<Trainer>> updateName(@PathVariable Long id, @RequestBody String name) {
		trainerService.updateTrainerName(id, name);
		return ResponseEntity.ok(trainerService.getTrainerById(id));
	}
	
	@PutMapping("/{id}/age")
	public ResponseEntity<Optional<Trainer>> updateAge(@PathVariable Long id, @RequestBody Integer age) {
		trainerService.updateTrainerAge(id, age);
		return ResponseEntity.ok(trainerService.getTrainerById(id));
	}
	
	@PutMapping("/{id}/catch")
	public ResponseEntity<Optional<Trainer>> catchPokemon(@PathVariable Long id, @RequestBody String pokemon) {
		trainerService.catchPokemon(id, pokemon);
		return ResponseEntity.ok(trainerService.getTrainerById(id));
	}
	
	@PutMapping("/{id}/release")
	public ResponseEntity<Optional<Trainer>> releasePokemon(@PathVariable Long id, @RequestBody String pokemon) {
		trainerService.releasePokemon(id, pokemon);
		return ResponseEntity.ok(trainerService.getTrainerById(id));
	}
	
	@PutMapping("/{id}/releaseAll")
	public ResponseEntity<Optional<Trainer>> releaseAllPokemons(@PathVariable Long id) {
		trainerService.releaseAllPokemons(id);
		return ResponseEntity.ok(trainerService.getTrainerById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		trainerService.deleteTrainer(id);
		return ResponseEntity.ok().build();
	}
}
