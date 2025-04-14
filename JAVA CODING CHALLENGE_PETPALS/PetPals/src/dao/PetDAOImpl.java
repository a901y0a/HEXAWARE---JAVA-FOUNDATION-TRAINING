package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.model.Cat;
import entity.model.Dog;
import entity.model.Pet;
import utily.DBConnUtil;

public class PetDAOImpl implements PetDAO {
    private static final String INSERT_PET_SQL = "INSERT INTO pets (name, age, breed, pet_type) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_PETS_SQL = "SELECT * FROM pets";

    @Override
    public void addPet(Pet pet) {
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware JDBC\\PetPals\\resources\\db.properties");
             PreparedStatement stmt = conn.prepareStatement(INSERT_PET_SQL)) {

            stmt.setString(1, pet.getName());
            stmt.setInt(2, pet.getAge());
            stmt.setString(3, pet.getBreed());
            stmt.setString(4, pet instanceof Dog ? "Dog" : "Cat");
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware JDBC\\PetPals\\resources\\db.properties");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_PETS_SQL)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String breed = rs.getString("breed");
                String petType = rs.getString("pet_type");

                if ("Dog".equals(petType)) {
                    pets.add(new Dog(name, age, breed));
                } else {
                	//String color = rs.getString("");
                    pets.add(new Cat(name, age, breed, "Unknown"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }
}
