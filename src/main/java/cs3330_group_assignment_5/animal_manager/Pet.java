package cs3330_group_assignment_5.animal_manager;

import java.util.*;

abstract class Pet implements java.lang.Comparable<Pet>{
	
	public abstract int getAge();

	public abstract int getId();
	
	public abstract void setAge(int age);
	
	public abstract void setAdopted(Boolean adopted);
	
	public abstract void setId(int id);
	
	public abstract String getName();
	
	public abstract String getType();
	
	public abstract String getSpecies();
	
	public abstract void setName(String name);
	
	public abstract void setType(String type);
	
	public abstract void setSpecies(String species);
	
	public abstract Boolean getAdopted();
	
	public int compareTo(Pet compPet) {
		String compName = compPet.getName();
		String name = getName();
		
		int compVal = name.compareTo(compName);
		
		if(compVal != 0) {
			return compVal;
		}
			
		else {
			return 0;
		}
		
	}
}

class Id implements Comparator<Pet>{
	
	public int compare(Pet pet, Pet compPet) {
		
		return Integer.compare(pet.getId(), compPet.getId());
		
	}
}

class Age implements Comparator<Pet>{
	
	public int compare(Pet pet, Pet compPet) {
		
		return Double.compare(pet.getAge(), compPet.getAge());
		
	}
}

class Species implements Comparator<Pet>{
	
	public int compare(Pet pet, Pet compPet) {
		
		return pet.getSpecies().compareTo(compPet.getSpecies());
		
	}
}


