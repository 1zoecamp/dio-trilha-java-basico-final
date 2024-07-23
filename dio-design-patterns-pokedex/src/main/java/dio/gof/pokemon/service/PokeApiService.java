package dio.gof.pokemon.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dio.gof.pokemon.model.Pokemon;

/**
 * @see <a href="https://pokeapi.co/docs/v2">PokeApi</a>
 */
@FeignClient(name = "pokeapi", url = "https://pokeapi.co/api/v2/pokemon/")
public interface PokeApiService {

	@GetMapping("/{name}")
	Pokemon getPokemon(@PathVariable("name") String name);
}
