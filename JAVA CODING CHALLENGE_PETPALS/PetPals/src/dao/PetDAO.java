package dao;

import entity.model.Pet;
import java.util.List;

public interface PetDAO {
    void addPet(Pet pet);
    List<Pet> getAllPets();
}
