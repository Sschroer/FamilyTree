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
	private Node<Person> head;

	/**
	 * This is the familyTree constructor.
	 */
	public FamilyTree() {
		head = new Node<>();
	}

	/**
	 * Constructs a FamilyTree with the given progenitor as the head of the
	 * family tree.
	 *
	 * @param progenitor
	 *            the Person object representing the progenitor or root of the
	 *            family tree
	 */
	public FamilyTree(Person progenitor) {
		head.setNext(progenitor);
	}

	/**
	 * @return the head
	 */
	public Node<Person> getHead() {
		return head;
	}

	/**
	 * @param newHead
	 *            the head to set
	 */
	public void setNext(Person newHead) {
		head.setNext(newHead);
	}

	/**
	 * Finds a person in the family tree with the specified name and birthday
	 * using breadth-first search.
	 *
	 * @param name
	 *            the name of the person to search for
	 * @param birthday
	 *            the birthday of the person to search for
	 * @return the Person object if found, or null if not found
	 */
	public Person findPerson(String name, LocalDate birthday) {
		// Create a queue for a breadth first search
		Queue<Person> queue = new LinkedList<>();
		queue.add(head.getNext());

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
	 * Finds a person in the family tree matching the provided target person
	 * using breadth-first search.
	 *
	 * @param target
	 *            the person object to search for
	 * @return the Person object if found, or null if not found
	 */
	public Person findPerson(Person target) {
		Queue<Person> queue = new LinkedList<>();
		queue.add(head.getNext());

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
	 * Finds people in the family tree with the specified name using breadth-first search.
	 *
	 * @param name the name of the people to search for
	 * @return a List containing Person objects with matching names, or an empty list if none are found
	 */
	public List<Person> findPeople(String name) {
		List<Person> foundPeople = new ArrayList<>();
		Queue<Person> queue = new LinkedList<>();
		queue.add(head.getNext());

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

	/**
	 * Assigns a parent to a child if the child does not already have that type of parent assigned.
	 *
	 * @param parent the parent to assign
	 * @param child  the child to assign the parent to
	 * @return true if the parent was successfully assigned to the child, false otherwise
	 */
	public boolean assignParent(Person parent, Person child) {
	    Person kid = findPerson(child);

	    if (kid != null && (kid.getFather() == null || kid.getMother() == null)) {  
	        if (parent.getGender() == Sex.MALE) {
	            kid.setFather(parent);
	            
	        } else {
	            kid.setMother(parent);
	        }
	        parent.addChild(child);
	        return true; 
	    }
	    return false;
	}
	
	/*
	 * getSiblings(), marry(), divorce(), getParents(),
	 * relationshipBetween() methods to be implemented in future.
	 */
}
