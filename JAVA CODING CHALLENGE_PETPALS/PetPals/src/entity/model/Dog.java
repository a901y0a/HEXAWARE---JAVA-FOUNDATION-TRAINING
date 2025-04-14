package entity.model;

public class Dog extends Pet {
	private String dogBreed;

	public Dog(String name, int age, String dogBreed) {
		super(name, age, dogBreed);
		this.dogBreed = dogBreed;
	}

	public String getDogBreed() { return dogBreed; }
	public void setDogBreed(String dogBreed) { this.dogBreed = dogBreed; }

	@Override
	public String toString() {
		return super.toString() + ", Dog Breed=" + dogBreed;
	}
}
