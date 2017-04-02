/*Name:Xiaoyu Zheng
 * Email:xzheng10@u.rochester.edu
 * Lab number:7
 * Lab Section: Tue 2:00 pm and Thu 2:00 pm
 */
public interface Queue<AnyType> {
        public boolean isEmpty();
        public void enqueue(AnyType x);
        public AnyType dequeue();
        public AnyType peek();
}