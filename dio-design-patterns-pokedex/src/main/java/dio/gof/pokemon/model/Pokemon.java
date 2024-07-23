package dio.gof.pokemon.model;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;

@Embeddable
public class Pokemon {
	private String name;
	private Integer height;
	private Integer weight;
	private LocalDate caughtAt;
	
	private Pokemon() {
		this.caughtAt = LocalDate.now();
	}

	// getters and setters

	public String getName() {
		return name;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCaughtAt() {
		return caughtAt;
	}

}
