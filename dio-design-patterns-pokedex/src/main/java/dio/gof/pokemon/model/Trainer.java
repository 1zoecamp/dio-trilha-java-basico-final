package dio.gof.pokemon.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trainer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer age;
	@ElementCollection
	private List<Pokemon> pokemons;

	// constructor
	public Trainer() {
		this.pokemons = new ArrayList<Pokemon>();
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}

	// extra methods
	public void addPokemon(Pokemon pokemon) {
		this.pokemons.add(pokemon);
	}

	public Pokemon findPokemonByName(String pokemonName) {
		for (Pokemon pokemon : pokemons) {
			if (pokemon.getName().equalsIgnoreCase(pokemonName)) {
				return pokemon;
			}
		}
		throw new IllegalArgumentException("Pokémon não encontrado");
	}

}
