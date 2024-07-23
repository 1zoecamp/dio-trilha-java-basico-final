package dio.gof.pokemon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.gof.pokemon.model.Pokemon;
import dio.gof.pokemon.model.Trainer;
import dio.gof.pokemon.repository.TrainerRepository;
import dio.gof.pokemon.service.PokeApiService;
import dio.gof.pokemon.service.TrainerService;

/**
 * Implementação da <b>Strategy</b> {@link TrainerService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 */
@Service
public class TrainerServiceImpl implements TrainerService {
	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	private PokeApiService pokeApiService;

	// Strategy: Implementar os métodos definidos na interface
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples
	@Override
	public List<Trainer> getAllTrainers() {
		return trainerRepository.findAll();
	}

	@Override
	public Optional<Trainer> getTrainerById(Long id) {
		return trainerRepository.findById(id);
	}

	@Override
	public Trainer saveTrainer(Trainer trainer) {
		return trainerRepository.save(trainer);
	}

	@Override
	public Trainer updateTrainerName(Long id, String newName) {
		Optional<Trainer> dbTrainer = trainerRepository.findById(id);
		if (dbTrainer.isPresent()) {
			Trainer trainer = dbTrainer.get();
			trainer.setName(newName);
			return trainer;
		} else {
			throw new IllegalArgumentException("Treinador nº " + id + " não encontrado");
		}
	}

	@Override
	public Trainer updateTrainerAge(Long id, Integer newAge) {
		Optional<Trainer> dbTrainer = trainerRepository.findById(id);
		if (dbTrainer.isPresent()) {
			Trainer trainer = dbTrainer.get();
			trainer.setAge(newAge);
			return trainer;
		} else {
			throw new IllegalArgumentException("Treinador nº " + id + " não encontrado");
		}
	}

	@Override
	public Trainer catchPokemon(Long id, String pokemonName) {
		Optional<Trainer> optionalTrainer = trainerRepository.findById(id);
		boolean success = Math.random() > 0.5;
		if (optionalTrainer.isPresent()) {
			// Se o treinador for encontrado, adicionar o novo Pokémon
			Trainer trainer = optionalTrainer.get();
			Pokemon newPokemon = pokeApiService.getPokemon(pokemonName);

			if (success) {
				trainer.addPokemon(newPokemon);
				trainerRepository.save(trainer);

				System.out.println("Parabéns! Você acaba de capturar " + pokemonName);
			} else {
				System.out.println("O pokémon %n acaba de fugir " + pokemonName);
			}
			return trainer;
		} else {
			// Se o treinador não for encontrado, pode lançar uma exceção ou retornar nulo
			throw new IllegalArgumentException("Treinador nº " + id + " não encontrado");
		}
	}

	@Override
	public Trainer releasePokemon(Long id, String pokemonName) {
		Optional<Trainer> dbTrainer = trainerRepository.findById(id);
		if (dbTrainer.isPresent()) {
			Trainer trainer = dbTrainer.get();
			trainer.getPokemons().removeIf(pokemon -> pokemon.getName().equalsIgnoreCase(pokemonName));
			trainerRepository.save(trainer);

			return trainer;
		} else {
			throw new IllegalArgumentException("Treinador nº " + id + " não encontrado");
		}
	}

	@Override
	public Trainer releaseAllPokemons(Long id) {
		Optional<Trainer> dbTrainer = trainerRepository.findById(id);
		if (dbTrainer.isPresent()) {
			Trainer trainer = dbTrainer.get();
			if (trainer.getPokemons() == null) {
				System.out.println("Nenhum pokémon encontrado");
			} else {
				trainer.setPokemons(null);
				trainerRepository.save(trainer);
			}
			return trainer;
		} else {
			throw new IllegalArgumentException("Treinador nº " + id + " não encontrado");
		}
	}

	@Override
	public void deleteTrainer(Long id) {
		trainerRepository.deleteById(id);
	}

}
