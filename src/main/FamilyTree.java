package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
		head = new Node<>();
		head.setNext(progenitor);
	}

	/**
	 * @return the head
	 */
	public Node<Person> getHead() {
		return head;
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
		if (!head.getNext().getPartners().isEmpty()) {
			queue.addAll(head.getNext().getPartners());
		}

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
		if (!head.getNext().getPartners().isEmpty()) {
			queue.addAll(head.getNext().getPartners());

			while (!queue.isEmpty()) {
				Person currentPerson = queue.poll();
				if (currentPerson.equals(target)) {
					return currentPerson;
				}
				queue.addAll(currentPerson.getChildren());
			}
		}
		return null;
	}

	/**
	 * Finds people in the family tree with the specified name using
	 * breadth-first search.
	 *
	 * @param name
	 *            the name of the people to search for
	 * @return a List containing Person objects with matching names, or an empty
	 *         list if none are found
	 */
	public List<Person> findPeople(String name) {
		List<Person> foundPeople = new ArrayList<>();
		Queue<Person> queue = new LinkedList<>();

		queue.add(head.getNext());
		if (!head.getNext().getPartners().isEmpty()) {
			queue.addAll(head.getNext().getPartners());

			while (!queue.isEmpty()) {
				Person currentPerson = queue.poll();

				if (currentPerson.getName().equalsIgnoreCase(name)) {
					foundPeople.add(currentPerson); // Found a person with
													// matching
													// name
				}
				// Add children to the queue for further exploration
				queue.addAll(currentPerson.getChildren());
			}
			return foundPeople;
		}
		return null;
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
			if (bioParent.getSpouse().equals(spouse)
					&& spouse.getSpouse().equals(bioParent)) {
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
			Person unknownParent = parent.createOppositeGender(bioParent);

			bioParent.addChild(child);
			unknownParent.addChild(child);

			return true;
		}
		return false;
	}

	/**
	 * Assigns a parent to a child if the child does not already have a parent
	 * assigned.
	 *
	 * @param parent
	 *            the parent to assign
	 * @param child
	 *            the child to assign the parent to
	 * @return true if the parent was successfully assigned to the child, false
	 *         otherwise
	 */
	public boolean assignParent(Person parent, Person child) {
		Person kid = findPerson(child);

		if (kid != null
				&& (kid.getFather() == null || kid.getMother() == null)) {
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

	/**
	 * Marries two persons by updating their marital status and partner lists.
	 * If both spouses are found and are already married to each other, their
	 * existing marriage is dissolved. If only one spouse is found, they are
	 * married to a new person of the opposite gender. If neither spouse is
	 * found, the method returns false.
	 *
	 * @param spouseA
	 *            The first person to be married.
	 * @param spouseB
	 *            The second person to be married.
	 * @return true if the marriage is successful, false otherwise.
	 */
	public boolean marry(Person spouseA, Person spouseB) {
		Person groom = findPerson(spouseA);
		Person bride = findPerson(spouseB);

		// cannot self-marry
		if (groom == bride) {
			return false;
		}

		// Error handling if both spouses are not found
		if (groom == null && bride == null) {
			return false;
		}

		// Check if both spouses are already married to each other
		if (groom != null && bride != null
				&& groom.getPartners().contains(bride)
				&& bride.getPartners().contains(groom)) {

			/*
			 * Remove existing marriage if spouses are already married to each
			 * other
			 */
			groom.getPartners().remove(bride);
			bride.getPartners().remove(groom);

			/*
			 * Adding them back in so they are located at the last index (are
			 * current spouses)
			 */
			haveWedding(groom, bride);
			return true;

			/*
			 * If the groom is not null but the bride is null, create a new
			 * spouse for the groom with the opposite gender of spouseB, using
			 * spouseB's name, and proceed with the wedding
			 */
		} else if (groom != null && bride == null) {
			haveWedding(groom,
					groom.createOppositeGender(spouseB.getName(), groom));
			return true;

			/*
			 * If the groom is null but the bride is not null, create a new
			 * spouse for the bride with the opposite gender of spouseA, using
			 * spouseA's name, and proceed with the wedding
			 */
		} else if (groom == null && bride != null) {
			haveWedding(bride.createOppositeGender(spouseA.getName(), bride),
					bride);
			return true;

		} else {
			haveWedding(groom, bride);
			return true;
		}
	}

	/**
	 * Simulates a wedding ceremony between a groom and a bride. Updates the
	 * marital status and partner lists of both individuals.
	 *
	 * @param groom
	 *            The groom involved in the wedding ceremony.
	 * @param bride
	 *            The bride involved in the wedding ceremony.
	 */
	private void haveWedding(Person groom, Person bride) {
		groom.getPartners().add(bride);
		groom.setMarried(true);

		bride.getPartners().add(groom);
		bride.setMarried(true);
	}

	/**
	 * Checks if two persons are blood-related within the family tree.
	 *
	 * @param a
	 *            the first person to check for blood relation
	 * @param b
	 *            the second person to check for blood relation
	 * @return true if both persons are blood-related within the family tree,
	 *         otherwise false
	 */
	public boolean areBloodRelated(Person a, Person b) {
		return isFamilyMember(a) && isFamilyMember(b);
	}

	/**
	 * Checks if a given person is a member of the local family tree.
	 *
	 * @param target
	 *            the person to check for membership in the local family tree
	 * @return true if the person is a member of the family tree, otherwise
	 *         false
	 */
	private boolean isFamilyMember(Person target) {
		Queue<Person> queue = new LinkedList<>();
		queue.add(head.getNext());

		while (!queue.isEmpty()) {
			Person currentPerson = queue.poll();
			if (currentPerson.equals(target)) {
				return true;
			}
			queue.addAll(currentPerson.getChildren());
		}
		return false;
	}

	/**
	 * Retrieves the set of siblings for a given person.
	 *
	 * Siblings share at least one parent with the specified person. Returns an
	 * empty set if the person has no siblings.
	 *
	 * @param target
	 *            The person for whom siblings are to be retrieved.
	 * @return A set containing the siblings of the target person, or an empty
	 *         set if there are none.
	 */
	public Set<Person> getSiblings(Person target) {
		Set<Person> siblings = new HashSet<>();

		if (target.getFather() != null) {
			siblings.addAll(target.getFather().getChildren());
		}

		if (target.getMother() != null) {
			siblings.addAll(target.getMother().getChildren());

		}
		// remove target from siblings set
		siblings.remove(target);

		return siblings.isEmpty() ? Collections.emptySet() : siblings;
	}

	/**
	 * Divorces the specified person from their spouse. This method sets the
	 * marital status of both the divorcer and their spouse to false,
	 * effectively ending their marriage.
	 *
	 * @param target
	 *            the person who wants to get divorced
	 * @return true if the divorce was successfully processed, false otherwise
	 */
	public boolean divorce(Person target) {
		Person divorcer = findPerson(target);

		// Check if the divorcer is found
		if (divorcer == null) {
			return false;
		}

		// Check if the divorcer's spouse relationship is consistent
		if (divorcer.getSpouse().getSpouse() != divorcer) {
			return false;
		}

		// Set the divorcer's married status and their partner's to false
		divorcer.setMarried(false);
		divorcer.getSpouse().setMarried(false);

		return true;

	}

	/**
	 * Allows a person to adopt another person as their child.
	 *
	 * @param newParent
	 *            the person adopting the child
	 * @param child
	 *            the person being adopted
	 * @return true if the adoption is successful (i.e., the child is not
	 *         already related to the adoptive parent), false otherwise
	 */
	public boolean adopt(Person newParent, Person child) {

		// checks to see if child is not related to Adopted Parent.
		if (!child.isChildOf(newParent)) {

			// set child's guardian to newParent and
			child.setGuardian(newParent);

			// add child to adopted parent's children list
			newParent.getChildren().add(child);

			// Change adopted status to true
			child.setWasAdopted(true);
			return true;
		}

		// If the child is already related to the parent
		return false;
	}

	/**
	 * Determines if two persons are closely related, meaning they share a common ancestor no later than their great-grandparents.
	 *
	 * @param a The first person to check for relatedness.
	 * @param b The second person to check for relatedness.
	 * @return {@code true} if the persons are closely related, {@code false} otherwise.
	 */
	public boolean areCloselyRelated(Person a, Person b) {

		Person one = findPerson(a);
		Person two = findPerson(b);

		if (one == null || two == null) {
			return false; // If either person is not found, they can't be
							// related
		}

		int count = 0;

		while (count < 3) {
			// Move up one generation for both persons on the father's side
			Person relativeOf_a_father = one.getFather();
			Person relativeOf_b_father = two.getFather();

			// Move up one generation for both persons on the mother's side
			Person relativeOf_a_mother = one.getMother();
			Person relativeOf_b_mother = two.getMother();

			/*
			 * Check for a common ancestor on both the father's and mother's
			 * sides
			 */
			if (relativeOf_a_father.equals(relativeOf_b_father)
					|| relativeOf_a_mother.equals(relativeOf_b_mother)) {
				return true;
				// If a common ancestor is found, they are closely related
			}

			count++;
		}

		return false; /*
						 * If no common ancestor is found up to the
						 * great-grandparent level on either side, they are not
						 * closely related
						 */
	}

	/*
	 * checkRelation(), findHighestRoot(), generationsBeteween() method(s) to be
	 * implemented in future.
	 */
}
