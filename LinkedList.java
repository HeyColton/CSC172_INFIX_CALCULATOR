/*Name:Xiaoyu Zheng
 * Email:xzheng10@u.rochester.edu
 * Lab number:6
 * Lab Section: Tue 2:00 pm and Thu 2:00 pm
 */

public class LinkedList<AnyType> implements SimpleLinkedList<AnyType> {
	// create the first node in linkedList
	MyNode<AnyType> First;

	// constructor to initiate the program
	public LinkedList() {
		First = new MyNode<AnyType>();
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void insert(AnyType x) {
		// constant runtime

		// if there is no such object in the list, then we add this as the first
		// and move the previous one to next
		if (contains(x) == false) {
			MyNode<AnyType> Node = new MyNode<AnyType>();
			Node.next = First;
			Node.data = x;
			First = Node;

		}
	}

	@Override
	public void delete(AnyType x) {
		// TODO Auto-generated method stub
		MyNode<AnyType> node = First;
		// check the first node
		if (First.data == x) {
			First = First.next;
		}
		// loop to find the target and skip this target in the link
		while (node.next != null) {
			if (node.next.data == x) {
				if (node.next.next != null) {
					node.next = node.next.next;

				} else {
					node.next = null;
				}
				break;
			}
			node = node.next;
		}

	}

	@Override
	public boolean contains(AnyType x) {
		// TODO Auto-generated method stub
		MyNode<AnyType> node = First;
		Boolean result = false;
		// loop to detect whether there is the target
		while (node.next != null) {
			if (node.data == x) {
				result = true;
				break;
			}
			node = node.next;
		}
		// the last node cannot be checked in the loop
		if (node.data == x) {
			result = true;
		}
		return result;
	}

	@Override
	public AnyType lookup(AnyType x) {
		// TODO Auto-generated method stub
		MyNode<AnyType> node = First;
		AnyType result = null;
		// loop to find the target
		while (node.next != null) {
			if (node.data == x) {
				result = x;
				break;
			}
			node = node.next;
		}
		// the last node cannot be checked in loop
		if (node.data == x) {
			result = x;
		}
		return result;
	}

	@Override
	// check the first data. If it is null, then it is empty.
	public boolean isEmpty() {
		if (First.data == null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void printList() {
		// n constants runtime
		// set a recursion to check every node
		print(First);
	}

	public void print(MyNode<AnyType> node) {
		if (node.next == null) {
			System.out.println(node.data);
		} else {
			System.out.print(node.data + " ");
			print(node.next);
		}
	}
}
