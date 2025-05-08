package cs3330_group_assignment_5.animal_manager;

class BasicAnimal extends Pet {
	
	int num;
	
	String name;
	
	String type;
	
	String species;
	
	int age;
	
	Boolean adopted;
	
	public BasicAnimal() {}
	
	public int getId() {
		
		return num;
		
	}
	
	@Override
	public String getName() {
		
		return name;
		
	}
	
	@Override
	public String getType() {
		
		return type;
		
	}
	
	@Override
	public String getSpecies() {
		
		return species;
		
	}
	
	@Override
	public int getAge() {
		
		return age;
		
	}
	
	@Override
	public Boolean getAdopted() {
		
		return adopted;
		
	}
	
	@Override
	public void setId(int num) {
		
		this.num = num;
		
	}
	
	@Override
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	@Override
	public void setType(String type) {
		
		this.type = type;
		
	}
	
	@Override
	public void setSpecies(String species) {
		
		this.species = species;
		
	}
	
	@Override
	public void setAge(int age) {
		
		this.age = age;
		
	}
	
	@Override
	public void setAdopted(Boolean adopted) {
		
		this.adopted = adopted;
		
	}
}
