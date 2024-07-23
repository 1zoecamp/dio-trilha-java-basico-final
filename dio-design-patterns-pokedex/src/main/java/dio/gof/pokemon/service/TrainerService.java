package dio.gof.pokemon.service;

import java.util.List;
import java.util.Optional;

import dio.gof.pokemon.model.Trainer;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 */
public interface TrainerService {

	public List<Trainer> getAllTrainers();

	public Optional<Trainer> getTrainerById(Long id);

	public Trainer saveTrainer(Trainer trainer);

	public Trainer catchPokemon(Long id, String pokemonName);

	public Trainer updateTrainerName(Long id, String newName);

	public Trainer updateTrainerAge(Long id, Integer newAge);

	public Trainer releasePokemon(Long id, String pokemonName);

	public Trainer releaseAllPokemons(Long id);

	public void deleteTrainer(Long id);
}
