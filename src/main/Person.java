package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
	private Person guardian;
	private boolean wasAdopted;
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
		guardian = null;
		wasAdopted = false;
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
		guardian = null;
		wasAdopted = false;
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
	public Person(String name, String gender, LocalDate birthday,
			LocalDate deathdate) throws GenderException {
		this.name = name;
		this.gender = parseGender(gender);
		this.birthday = birthday;
		this.deathdate = deathdate;
		this.mother = null;
		this.father = null;
		guardian = null;
		wasAdopted = false;
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
		guardian = null;
		wasAdopted = false;
		isMarried = false;
		this.children = new ArrayList<>();
		this.partners = new ArrayList<>();

	}

	/**
	 * Constructs a new Person object with the specified name and gender.
	 *
	 * @param name
	 *            The name of the person.
	 * @param gender
	 *            The gender of the person.
	 */
	protected Person(String name, Sex gender) {
		this.name = name;
		this.gender = gender;
		this.birthday = null;
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
		gender = gender.trim();

		if (gender != null && !gender.isBlank()) {
			if (gender.equalsIgnoreCase("female")
					|| gender.equalsIgnoreCase("f")) {
				return Sex.FEMALE;
			}
			if (gender.equalsIgnoreCase("male")
					|| gender.equalsIgnoreCase("m")) {
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
	 * Retrieves a list of parents for this person.
	 *
	 * @return A list containing the parents of this person. If the person has
	 *         no parents, an empty list is returned.
	 */
	public List<Person> getParents() {
		List<Person> parents = new ArrayList<>();

		if (getFather() == null && getMother() == null) {
			return Collections.emptyList();
		}
		if (getFather() != null) {
			parents.add(getFather());
		}
		if (getMother() != null) {
			parents.add(getMother());
		}

		return parents;
	}

	/**
	 * @return the guardian
	 */
	public Person getGuardian() {
		return guardian;
	}

	/**
	 * @param guardian the guardian to set
	 */
	public void setGuardian(Person guardian) {
		this.guardian = guardian;
	}

	/**
	 * @return the wasAdopted
	 */
	public boolean isWasAdopted() {
		return wasAdopted;
	}

	/**
	 * @param wasAdopted the wasAdopted to set
	 */
	public void setWasAdopted(boolean wasAdopted) {
		this.wasAdopted = wasAdopted;
	}

	/**
	 * @return the isMarried
	 */
	public boolean isMarried() {
		return isMarried;
	}

	/**
	 * @param isMarried
	 *            the isMarried to set
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
	 * Retrieves the spouse of the person. Assumes that current spouse will
	 * always be at the last index.
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
	 * Checks if the current instance is considered an ex-partner of the
	 * provided person. An ex-partner is defined as a person who was previously
	 * in a relationship with the provided person, excluding the current
	 * instance if present and if the current instance is marked as married.
	 *
	 * @param a
	 *            The person to check for ex-partnership.
	 * @return {@code true} if the current instance is considered an ex-partner
	 *         based on its presence in the list of partners of the provided
	 *         person, excluding itself if marked as the current partner and if
	 *         marked as married, {@code false} otherwise.
	 */
	public boolean isExOf(Person a) {
		List<Person> partners = a.getPartners();
		int lastIndex = partners.size() - 1;

		if (lastIndex >= 0 && partners.get(lastIndex) == this
				&& this.isMarried) {
			return false;
			// Exclude the last index as it represents the current partner
		}

		return partners.contains(this);
	}

	/**
	 * Returns a new Person object with the opposite gender of the provided
	 * Person.
	 *
	 * @param a
	 *            The Person object whose gender will be used to determine the
	 *            opposite gender.
	 * @return A new Person object with the opposite gender of the provided
	 *         Person.
	 */
	public Person createOppositeGender(Person a) {
		Sex unknownSex = (a.getGender() == Sex.MALE) ? Sex.FEMALE : Sex.MALE;
		return new Person(unknownSex);
	}

	/**
	 * Creates a new Person object with the opposite gender of the specified
	 * person.
	 *
	 * @param a
	 *            The Person whose gender opposite is to be determined.
	 * @param name
	 *            The name for the new Person object.
	 * @return A new Person object with the opposite gender of the specified
	 *         person.
	 */
	public Person createOppositeGender(String name, Person a) {
		Sex unknownSex = (a.getGender() == Sex.MALE) ? Sex.FEMALE : Sex.MALE;
		return new Person(name, unknownSex);
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

	/**
	 * Checks if the current person is a child of the specified target person.
	 *
	 * @param target
	 *            the target person to check if the current person is their
	 *            child
	 * @return true if the current person is a child of the target person, false
	 *         otherwise
	 */
	public boolean isChildOf(Person target) {
		return target.getChildren().contains(this);
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", birthday="
				+ birthday + ", deathdate=" + deathdate + ", mother=" + mother
				+ ", father=" + father + ", guardian=" + guardian
				+ ", wasAdopted=" + wasAdopted + ", isMarried=" + isMarried
				+ ", children=" + children + ", partners=" + partners + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, children, deathdate, father, gender,
				guardian, isMarried, mother, name, partners, wasAdopted);
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
				&& gender == other.gender
				&& Objects.equals(guardian, other.guardian)
				&& isMarried == other.isMarried
				&& Objects.equals(mother, other.mother)
				&& Objects.equals(name, other.name)
				&& Objects.equals(partners, other.partners)
				&& wasAdopted == other.wasAdopted;
	}
}
