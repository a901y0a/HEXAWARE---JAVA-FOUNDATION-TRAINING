package entity.model;

import java.util.ArrayList;
import java.util.List;

import dao.PetDAO;
import dao.PetDAOImpl;

public class PetShelter {
	private List<Pet> availablePets = new ArrayList<>();
	
	PetDAO petDAO = new PetDAOImpl();

	public void addPet(Pet pet) {
		availablePets.add(pet);
	}

	public void removePet(Pet pet) {
		availablePets.remove(pet);
	}

	public List<Pet> listAvailablePets() {
		
		return petDAO.getAllPets();
		//return availablePets;
	}
}