/*Name:Xiaoyu Zheng
 * Email:xzheng10@u.rochester.edu
 * Lab number:7
 * Lab Section: Tue 2:00 pm and Thu 2:00 pm
 */

  public interface DoublyLinkedList<AnyType> {
       public void insert(AnyType x);
       public void delete(AnyType x);
       public boolean contains(AnyType x);
       public AnyType lookup(AnyType x);
       public boolean isEmpty();
       public void printList();
       public void printListRev();
   }
