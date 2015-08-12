package com.liferay.docs.examplejsonserializationportlet.model;

import java.util.List;

import com.liferay.portal.kernel.json.JSON;

// Uncomment the following annotation to enable strict mode.
// When strict mode is active, only getters decorated with the @JSON annotation
// are included in the serialization.
// @JSON(strict = true)
public class Pet {
	
	// Uncomment the following annotation to include the favorite foods list in
	// the serialization.
	// @JSON
	public List<String> getFavoriteFoods() {
		return favoriteFoods;
	}

	// Uncomment the following annotation to include the name field in the
	// serialization when strict mode is active.
	// @JSON
	public String getName() {
		return name;
	}

	// Uncomment the following annotation to include the species field in the
	// serialization when strict mode is active.
	// @JSON
	public String getSpecies() {
		return species;
	}

	public void setFavoriteFoods(List<String> favoriteFoods) {
		this.favoriteFoods = favoriteFoods;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	private String name;

	private String species;
	
	private List<String> favoriteFoods;

}
