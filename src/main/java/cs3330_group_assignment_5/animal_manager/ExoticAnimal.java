package cs3330_group_assignment_5.animal_manager;

class ExoticAnimal {
	String uniqueNum;
	
	String animalName;
	
	String category;
	
	String subSpecies;
	
	int yearsOld;
	
	public ExoticAnimal() {}
	
	
	public void setSubSpecies(String subSpecies) {
		this.subSpecies = subSpecies;
	}

	public int getYearsOld() {
		return yearsOld;
	}

	public void setYearsOld(int yearsOld) {
		this.yearsOld = yearsOld;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public String getAnimalName() {
		return animalName;
	}

	public String getCategory() {
		return category;
	}

	public String getSubSpecies() {
		return subSpecies;
	}



}
