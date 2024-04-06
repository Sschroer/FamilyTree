package main;

import java.time.LocalDate;
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
	private LocalDate birthday;
	private LocalDate deathdate;
	private Person mother;
	private Person father;
	private boolean isMarried;
	private final List<Person> children;
	private final List<Person> partners;


	/**
	 * This method creates a person who's sex is known.
	 * 
	 * @param gender
	 *            The gender of the person. Must be either "male" or "female".
	 */
	protected Person(Sex gender) {
		name = "";
		this.gender = gender;
		birthday = null;
		deathdate = null;
		mother = null;
		father = null;
		isMarried = false;
		this.children = new ArrayList<>();
		this.partners = new ArrayList<>();
	}

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
		this.birthday = null;
		this.deathdate = null;
		this.mother = null;
		this.father = null;
		isMarried = false;
		this.children = new ArrayList<>();
		this.partners = new ArrayList<>();

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
	public Person(String name, String gender, LocalDate birthday, LocalDate deathdate)
			throws GenderException {
		this.name = name;
		this.gender = parseGender(gender);
		this.birthday = birthday;
		this.deathdate = deathdate;
		this.mother = null;
		this.father = null;
		isMarried = false;
		this.children = new ArrayList<>();
		this.partners = new ArrayList<>();

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
	public Person(String name, String gender, LocalDate birthday)
			throws GenderException {
		this.name = name;
		this.gender = parseGender(gender);
		this.birthday = birthday;
		this.deathdate = null;
		this.mother = null;
		this.father = null;
		isMarried = false;
		this.children = new ArrayList<>();
		this.partners = new ArrayList<>();

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
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the deathdate
	 */
	public LocalDate getDeathdate() {
		return deathdate;
	}

	/**
	 * @param deathdate
	 *            the deathdate to set
	 */
	public void setDeathdate(LocalDate deathdate) {
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
	 * @return the isMarried
	 */
	public boolean isMarried() {
		return isMarried;
	}

	/**
	 * @param isMarried the isMarried to set
	 */
	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	/**
	 * @return the children
	 */
	public List<Person> getChildren() {
		return children;
	}

	/**
	 * @return the partners
	 */
	public List<Person> getPartners() {
		return partners;
	}
	
	/**
	 * Retrieves the spouse of the person.
	 *
	 * @return the spouse of the person if they are married, otherwise null
	 */
	public Person getSpouse() {
		if (isMarried) {
			return partners.get(partners.size() - 1);
		}
		return null;
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
				+ ", father=" + father + ", isMarried=" + isMarried
				+ ", children=" + children + ", partners=" + partners + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, children, deathdate, father, gender,
				isMarried, mother, name, partners);
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
				&& Objects.equals(deathdate, other.deathdate)
				&& Objects.equals(father, other.father)
				&& gender == other.gender && isMarried == other.isMarried
				&& Objects.equals(mother, other.mother)
				&& Objects.equals(name, other.name)
				&& Objects.equals(partners, other.partners);
	}
}
