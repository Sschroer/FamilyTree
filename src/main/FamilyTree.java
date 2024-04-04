package main;

/**
 * This is the FamilyTree Object.
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
	 * @param newHead the head to set
	 */
	public void setHead(Person newHead) {
		head = newHead;
	}
	
	

}
