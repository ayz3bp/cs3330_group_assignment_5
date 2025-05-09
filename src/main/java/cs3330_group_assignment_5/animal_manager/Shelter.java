package cs3330_group_assignment_5.animal_manager;

import java.util.*;

class Shelter<T extends Pet> {
	List<T> shelterList;
	
	public Shelter() {
		this.shelterList = new ArrayList<>();
	}
	
	public void sortShelterByName() {
		Collections.sort(shelterList);
	}
		
	public void addPet(T newPet) {
		shelterList.add(newPet);
	}
	
	public List<T> getShelterList(){
		return shelterList;
	}

	public void sortShelterBySpecies() {
		Collections.sort(shelterList, new Species());
	}
	
	public void sortShelterByAge() {
		Collections.sort(shelterList, new Age());
	}

	
}
