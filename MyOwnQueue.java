/*Name:Xiaoyu Zheng
 * Email:xzheng10@u.rochester.edu
 * Lab number:7
 * Lab Section: Tue 2:00 pm and Thu 2:00 pm
 */
public class MyOwnQueue<AnyType> extends MyDoubleLinkedList<AnyType> implements Queue<AnyType> {
MyDoubleNode<AnyType> First;
MyDoubleNode<AnyType> Last;
//constructor
	public MyOwnQueue(){
		super();
    }
	@Override
	//check if the queue is empty
	public boolean isEmpty() {
		if(First==null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void enqueue(AnyType x) {
		// TODO Auto-generated method stub
		
		if (isEmpty() == true) {
			MyDoubleNode<AnyType> node1=new MyDoubleNode<AnyType>();
			node1.data=x;
			First=node1;
			Last=node1;
			// if the list is not empty
		} else {
			MyDoubleNode<AnyType> Node = new MyDoubleNode<AnyType>();
			Node.data = x;
			Node.prev = Last;
			Last.next = Node;
			Last = Node;
		}
	}

	@Override
	public AnyType dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()==true){
			return null;
		}else{
			MyDoubleNode<AnyType> node = First;
			First=node.next;
			node.next=null;
			return node.data;
			
		}
	}

	@Override
	public AnyType peek() {
		// TODO Auto-generated method stub
		return First.data;
	} 
}