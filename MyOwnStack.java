/*Name:Xiaoyu Zheng
 * Email:xzheng10@u.rochester.edu
 * Lab number:6
 * Lab Section: Tue 2:00 pm and Thu 2:00 pm
 */
public class MyOwnStack<AnyType> extends LinkedList<AnyType> implements Stack<AnyType> {
//constructor to initiate First node
	MyNode<AnyType> First;
	public MyOwnStack(){
		
		First=new MyNode<AnyType>();
	
	}
	@Override
	//check if the stack is empty
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(First.data==null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	//add one stuff on the top
	public void push(AnyType x) {
		// TODO Auto-generated method stub
		
			MyNode<AnyType> Node=new MyNode<AnyType>();
			Node.next=First;
			Node.data=x;
			First=Node;
		
	}

	@Override
	//check empty first, skip the first to the first.next and print first.data
	public AnyType pop() {
		// TODO Auto-generated method stub
		AnyType data;
		if(isEmpty()==true){
		return null;
		}else{
		    data=First.data;
			First=First.next;
			return data;
		}
		
	}

	@Override
	//get the data of the top node
	public AnyType peek() {
		// TODO Auto-generated method stub
		return First.data;
	}

}
