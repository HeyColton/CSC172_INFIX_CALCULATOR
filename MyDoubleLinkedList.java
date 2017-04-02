/*Name:Xiaoyu Zheng
 * Email:xzheng10@u.rochester.edu
 * Lab number:7
 * Lab Section: Tue 2:00 pm and Thu 2:00 pm
 */

public class MyDoubleLinkedList<AnyType> implements DoublyLinkedList<AnyType> {
	MyDoubleNode<AnyType> First;
	MyDoubleNode<AnyType> Last;

	// constructor to initiate LinkedList
	public MyDoubleLinkedList() {
		First = new MyDoubleNode<AnyType>();
		Last = First;
	}

	@Override
	public void insert(AnyType x) {
		// TODO Auto-generated method stub
		// insert only when x is not in the list
		if (contains(x) == false) {
			// if the list is empty
			if (isEmpty() == true) {
				First.data = x;
				Last.data = x;
				// if the list is not empty
			} else {
				MyDoubleNode<AnyType> Node = new MyDoubleNode<AnyType>();
				Node.data = x;
				Node.next = First;
				First.prev = Node;
				First = Node;
			}
		}
	}
	public void insertRev(AnyType x) {
		// TODO Auto-generated method stub
		// insert only when x is not in the list
		if (contains(x) == false) {
			// if the list is empty
			if (isEmpty() == true) {
				First.data = x;
				Last.data = x;
				// if the list is not empty
			} else {
				MyDoubleNode<AnyType> Node = new MyDoubleNode<AnyType>();
				Node.data = x;
				Node.prev = Last;
				Last.next = Node;
				Last = Node;
			}
		}
	}
	@Override
	public void delete(AnyType x) {
		// TODO Auto-generated method stub
		MyDoubleNode<AnyType> node = First;
		// check the first node
		if (First.data == x) {
			First = First.next;
			First.prev = null;
		}
		// loop to find the target and skip this target in the link
		while (node.next != null) {
			if (node.next.data == x) {
				if (node.next.next != null) {
					node.next = node.next.next;
					node.next.prev = node;

				} else {
					node.next = null;
					Last = node;
				}
				break;
			}
			node = node.next;
		}
	}
	public void deleteRev(AnyType x){
		MyDoubleNode<AnyType> node = Last;
		// check the first node
		if (Last.data == x) {
			Last = Last.prev;
			First.next = null;
		}
		// loop to find the target and skip this target in the link
		while (node.prev != null) {
			if (node.prev.data == x) {
				if (node.prev.prev != null) {
					node.prev = node.prev.prev;
					node.prev.next = node;

				} else {
					node.prev = null;
					First = node;
				}
				break;
			}
			node = node.prev;
		}
	}

	@Override
	public boolean contains(AnyType x) {
		// TODO Auto-generated method stub
		MyDoubleNode<AnyType> node = First;
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
		MyDoubleNode<AnyType> node = First;
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
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (First.data == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	// the runtime should be n
	public void printList() {
		// TODO Auto-generated method stub
		MyDoubleNode<AnyType> node = new MyDoubleNode<AnyType>();
		node = First;
		while (node != Last) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println(Last.data);
	}

	@Override
	public void printListRev() {
		// TODO Auto-generated method stub
		MyDoubleNode<AnyType> node = new MyDoubleNode<AnyType>();
		node = Last;
		// loop until the first node
		while (node != First) {
			System.out.print(node.data + " ");
			node = node.prev;
		}
		// the first node is not included in the list, so print it
		System.out.println(First.data);
	}

}
