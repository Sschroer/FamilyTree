package main;

import java.util.Objects;

/**
 * Represents a linking node. Each node holds a reference to the next node. This
 * class is generic, allowing it to store any type of data.
 * 
 * @author Stephen Schroer
 * @param <T>
 *            the type of data stored in the node
 */
public class Node<T> {
	T next;

	/**
	 * @return the next
	 */
	public T getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(T next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [next=" + next + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(next);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node<T> other = (Node<T>) obj;
		return Objects.equals(next, other.next);
	}

}
