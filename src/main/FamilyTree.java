package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This is the FamilyTree Object.
 * 
 * @author Stephen Schroer
 *
 */
public class FamilyTree {
	private Person head;

	/**
	 * This is the familyTree constructor.
	 */
	public FamilyTree() {
		head = new Person();
	}

	/**
	 * @return the head
	 */
	public Person getHead() {
		return head;
	}

	/**
	 * @param newHead
	 *            the head to set
	 */
	public void setHead(Person newHead) {
		head = newHead;
	}

	/**
	 * Finds a person in the family tree by their name and birthday.
	 * 
	 * This method performs a breadth-first search starting from the head of the
	 * family tree. It iteratively explores each person's children, adding them
	 * to a queue for further exploration. If a person's name and birthday match
	 * the specified parameters (case-insensitive name match and exact birthday
	 * match), the method returns that person. If no person with the specified
	 * name and birthday is found in the family tree, the method returns
	 * {@code null}.
	 * 
	 * @param name
	 *            The name of the person to search for.
	 * @param birthday
	 *            The birthday of the person to search for.
	 * @return The person found with the specified name and birthday, or
	 *         {@code null} if not found.
	 */

	public Person findPerson(String name, LocalDate birthday) {
		// Create a queue for a breadth first search
		Queue<Person> queue = new LinkedList<>();
		queue.addAll(head.getChildren());

		while (!queue.isEmpty()) {
			Person currentPerson = queue.poll();

			// Checks if name and birthday match
			if (currentPerson.getName().equalsIgnoreCase(name)
					&& currentPerson.getBirthday().equals(birthday)) {
				return currentPerson; // found person
			}

			// add children to the queue for further exploration
			queue.addAll(currentPerson.getChildren());
		}

		// person not found
		return null;
	}

	/**
	 * Finds a person in the family tree.
	 * 
	 * This method performs a breadth-first search starting from the head of the
	 * family tree. It iteratively explores each person's children, adding them
	 * to a queue for further exploration. If a person in the family tree equals
	 * the specified target person (based on object equality), the method
	 * returns that person. If the target person is not found in the family
	 * tree, the method returns {@code null}.
	 * 
	 * @param target
	 *            The person to search for in the family tree.
	 * @return The person found in the family tree that equals the specified
	 *         target person, or {@code null} if the target person is not found.
	 */
	public Person findPerson(Person target) {
		Queue<Person> queue = new LinkedList<>();
		queue.addAll(head.getChildren());

		while (!queue.isEmpty()) {
			Person currentPerson = queue.poll();
			if (currentPerson.equals(target)) {
				return currentPerson;
			}
			queue.addAll(currentPerson.getChildren());
		}
		return null;
	}

	/**
	 * Finds all people with the specified name in the family tree.
	 * 
	 * This method performs a breadth-first search starting from the head of the
	 * family tree. It iteratively explores each person's children, adding them
	 * to a queue for further exploration. If a person's name matches the
	 * specified name (case-insensitive), they are added to the list of found
	 * people. The method returns a list containing all people with the
	 * specified name found in the family tree.
	 * 
	 * @param name
	 *            The name to search for.
	 * @return A list containing all people with the specified name found in the
	 *         family tree.
	 */
	public List<Person> findPeople(String name) {
		List<Person> foundPeople = new ArrayList<>();
		Queue<Person> queue = new LinkedList<>();
		queue.addAll(head.getChildren());

		while (!queue.isEmpty()) {
			Person currentPerson = queue.poll();
			// Check if name and birthday match
			if (currentPerson.getName().equalsIgnoreCase(name)) {
				foundPeople.add(currentPerson); // Found a person with matching
												// name
			}
			// Add children to the queue for further exploration
			queue.addAll(currentPerson.getChildren());
		}
		return foundPeople;
	}

	/**
	 * Adds a child to the specified parent and their current spouse.
	 * 
	 * This method first attempts to find the specified parent and their current
	 * spouse in the family tree. If both parent and spouse are found, and they
	 * are each other's current spouses, the child is added to both parents.
	 * Otherwise, the child is not added and the method returns false.
	 * 
	 * @param parent
	 *            The parent to whom the child will be added.
	 * @param currentSpouse
	 *            The current spouse of the parent.
	 * @param child
	 *            The child to be added to the parent and their spouse.
	 * @return {@code true} if the child is successfully added to both parents,
	 *         {@code false} otherwise.
	 */
	public boolean addChild(Person parent, Person currentSpouse, Person child) {
		Person bioParent = findPerson(parent);
		Person spouse = findPerson(currentSpouse);

		if (bioParent != null && spouse != null) {
			if (bioParent.getCurrentSpouse().equals(spouse)
					&& spouse.getCurrentSpouse().equals(bioParent)) {
				bioParent.addChild(child);
				spouse.addChild(child);
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a child to a specified parent and creates another parent of the
	 * opposite gender, adding the child to both parents.
	 * 
	 * @param parent
	 *            The parent to whom the child will be added.
	 * @param child
	 *            The child to be added to the parent.
	 * @return {@code true} if the child is successfully added to both parents,
	 *         {@code false} otherwise.
	 */
	public boolean addChild(Person parent, Person child) {
		Person bioParent = findPerson(parent);

		if (bioParent != null) {
			Sex parentGender = bioParent.getGender();
			Sex unknownParentGender = (parentGender == Sex.MALE)
					? Sex.FEMALE
					: Sex.MALE;

			Person unknownParent = new Person(unknownParentGender);

			bioParent.addChild(child);
			unknownParent.addChild(child);

			return true;
		}
		return false;
	}

	/* addParent(), getSiblings(), marry(), divorce(), getParents(),
	 relationshipBetween() methods to be implemented in future. */
}
