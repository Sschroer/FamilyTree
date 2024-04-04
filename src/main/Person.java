package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is the person object.
 * 
 * @author Stephen Schroer
 *
 */
public class Person {
	private String name;
	private final Sex gender;
	private String birthday;
	private String deathdate;
	private Person mother;
	private Person father;
	private Person currentSpouse;
	private final List<Person> children;
	private final List<Person> exes;

	/**
	 * 
	 * @param name
	 *            The name of the person.
	 * @param gender
	 *            The gender of the person. Must be either "male" or "female".
	 * @throws GenderException
	 *             If the gender provided is not "male" or "female".
	 */
	public Person(String name, String gender) throws GenderException {
		this.name = name;
		this.gender = parseGender(gender);
		this.birthday = "";
		this.deathdate = "";
		this.mother = null;
		this.father = null;
		this.currentSpouse = null;
		this.children = new ArrayList<>();
		this.exes = new ArrayList<>();
	}

	/**
	 * @param name
	 *            The name of the person.
	 * @param gender
	 *            The gender of the person. Must be either "male" or "female".
	 * @param birthday
	 *            The birthday of the person.
	 * @param deathdate
	 *            The day the person died.
	 * @throws GenderException
	 *             If the gender provided is not "male" or "female".
	 */
	public Person(String name, String gender, String birthday, String deathdate)
			throws GenderException {
		this.name = name;
		this.gender = parseGender(gender);
		this.birthday = birthday;
		this.deathdate = deathdate;
		this.mother = null;
		this.father = null;
		this.currentSpouse = null;
		this.children = new ArrayList<>();
		this.exes = new ArrayList<>();
	}

	/**
	 * @param name
	 *            The name of the person.
	 * @param gender
	 *            The gender of the person. Must be either "male" or "female".
	 * @param birthday
	 *            The birthday of the person.
	 * @throws GenderException
	 *             If the gender provided is not "male" or "female".
	 */
	public Person(String name, String gender, String birthday)
			throws GenderException {
		this.name = name;
		this.gender = parseGender(gender);
		this.birthday = birthday;
		this.deathdate = "";
		this.mother = null;
		this.father = null;
		this.currentSpouse = null;
		this.children = new ArrayList<>();
		this.exes = new ArrayList<>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gender
	 */
	public Sex getGender() {
		return gender;
	}

	/**
	 * Compares String inupt and returns the correct gender enum.
	 * 
	 * @param gender
	 *            The gender of the person. Must be either "male" or "female".
	 * @return Sex Enum
	 * @throws GenderException
	 */
	private Sex parseGender(String gender) throws GenderException {
		if (gender != null && !gender.isBlank()) {
			if (gender.compareToIgnoreCase("female") == 0
					|| gender.compareToIgnoreCase("f") == 0) {
				return Sex.FEMALE;
			}
			if (gender.compareToIgnoreCase("male") == 0
					|| gender.compareToIgnoreCase("m") == 0) {
				return Sex.MALE;
			}
		}

		throw new GenderException("Invalid gender: \"" + gender
				+ "\". Please enter \"male\" or \"female\"");
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the deathdate
	 */
	public String getDeathdate() {
		return deathdate;
	}

	/**
	 * @param deathdate
	 *            the deathdate to set
	 */
	public void setDeathdate(String deathdate) {
		this.deathdate = deathdate;
	}

	/**
	 * @return the mother
	 */
	public Person getMother() {
		return mother;
	}

	/**
	 * @param mother
	 *            the mother to set
	 */
	public void setMother(Person mother) {
		this.mother = mother;
	}

	/**
	 * @return the father
	 */
	public Person getFather() {
		return father;
	}

	/**
	 * @param father
	 *            the father to set
	 */
	public void setFather(Person father) {
		this.father = father;
	}

	/**
	 * @param spouse
	 *            the currentSpouse to set
	 */
	public void setCurrentSpouse(Person spouse) {
		if (currentSpouse != null) {
			exes.add(currentSpouse);
		}
		currentSpouse = spouse;
	}

	/**
	 * @return the currentSpouse
	 */
	public Person getCurrentSpouse() {
		return currentSpouse;
	}

	/**
	 * @return the children
	 */
	public List<Person> getChildren() {
		return children;
	}

	/**
	 * @return the exes
	 */
	public List<Person> getExes() {
		return exes;
	}

	/**
	 * Add child to Person's children and assign them as a parent
	 * 
	 * @param child
	 *            The Child to add to children
	 * @return true if the child was added successfully, false if the child was
	 *         already present in the list of children.
	 */
	public boolean addChild(Person child) {
		if (!children.contains(child)) {
			children.add(child);
			// Set this person as the parent of the child
			if (gender == Sex.FEMALE) {
				child.setMother(this);
			} else {
				child.setFather(this);
			}
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", birthday="
				+ birthday + ", deathdate=" + deathdate + ", mother=" + mother
				+ ", father=" + father + ", currentSpouse=" + currentSpouse
				+ ", children=" + children + ", exes=" + exes + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, children, currentSpouse, deathdate, exes,
				father, gender, mother, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(birthday, other.birthday)
				&& Objects.equals(children, other.children)
				&& Objects.equals(currentSpouse, other.currentSpouse)
				&& Objects.equals(deathdate, other.deathdate)
				&& Objects.equals(exes, other.exes)
				&& Objects.equals(father, other.father)
				&& gender == other.gender
				&& Objects.equals(mother, other.mother)
				&& Objects.equals(name, other.name);
	}

}
