import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author EmilyJackson
 * @version 2.0
 *
 */

public class Search {
	/**
	 * This is the general graph search.
	 * @param start
	 * @param struct
	 * @param successors
	 * @param goal
	 */
	public static <T> void search(T start, 
			Structure<T> struct,
			Function<T, List<T>> successors, 
			Function<T, Boolean> goal) {
			
			Set<T> visited = new HashSet<T>();
			struct.add(start);
			while (!struct.isEmpty()){
				T item = struct.remove();
				if (!visited.contains(item)){
					visited.add(item);
					boolean done = goal.apply(item);		
					for (T items : successors.apply(item)){
						struct.add(items);
					}
					if (done) break;
				
				}
			}
	}
	/**
	 * This is Dijkstra's shortest path algorithm that implements a priority queue.
	 * @param start
	 * @param dist
	 * @param successors
	 * @param goal
	 */
	public static <T> void distance(T start,
			Function<Pair<T, T>, Integer> dist,
			Function<T, List<T>> successors,
			Function<Pair<T, Integer>, Boolean> goal) {
		
		PriorityQueue vertexQueue = new PriorityQueue();
     	vertexQueue.add(new Pair(start, 0));
     	Set<T> visited = new HashSet<T>();
		while (!vertexQueue.isEmpty()) {
		    Pair<T, Integer> min = vertexQueue.remove();
		    if (!visited.contains(min.a)){
		    	visited.add(min.a);
		    	for (T items: successors.apply(min.a)){
		    		int totalDistance = dist.apply(new Pair(items, min.a)) + min.b;
		    		vertexQueue.add(new Pair(items, totalDistance)); 
		    		
		    	}
		    	if(goal.apply(min)) break;
		    }
		}
	}

	/**
	 * Private inner class of priority queue as a min heap for Dijkstra's search. 	
	 * @author EmilyJ
	 *
	 * @param <T>
	 */
	private static class PriorityQueue<T> {
		

		Pair<T, Integer>[] data;
		int size;
		Comparator comp;
		/**
		 * Constructor for the priority queue.
		 */
		public PriorityQueue() {
			data = (Pair<T, Integer>[]) new Object[11];
			size = 0;
			comp = new Comparator();
			
		}
		/**
		 * Clear out the heap but setting the size to zero. 
		 */
		public void clear() {
			size = 0;
		}
		/**
		 * 	Checks if the heap is empty based on the size.
		 * @return
		 */
		public boolean isEmpty() {				
			return size==0;
		}
		/**
		 * Adds item to this heap
		 * @param item
		 */
		public void add(Pair<T, Integer> item) {
				
			resize();
			data[size] = item;
			shiftUp(size);
		    size++;
				
		}
		/**
		  *Removes and returns the minimum element of this heap
		  * 
		  * @return the minimum element of this heap as defined by the comparator
		 */
		public Pair<T, Integer> remove() {				
			Pair<T, Integer> min = data[0];
			data[0] = data[size - 1];
			data[size-1] = null;
	        if(size > 0){ 
	        	shiftDown(0);
	        }
	        size--;
	        return min;
		
		}
		/**
		 * This method doubles the length of the underlying array when it is full.
		 */
		public void resize(){
			if (size == data.length){
				//resize
				Pair<T, Integer>[] old = data;
				data = (Pair<T, Integer>[]) new Object[old.length*2];
				for(int i = 0; i < old.length; i++){
					data[i] = old[i];
				}
			}
		}
		/**
		 * THis mehtod goes up the heap and compares the node's children to find the smaller of the
		 * two and swap it for the parent 
		 * @param index
		 */
		private void shiftUp(int index){
		    if(index > 0) {
		    	int parent = ( index - 1 ) / 2;
		        if(comp.compareTo(data[parent].b, data[index].b) == 1 ) {
		        	swap(parent, index);
		            shiftUp(parent);
		        }
		    }
		}
		/**
		* THis method swaps the data at the two indexes
		* @param x
		* @param y
		 */
		private void swap(int x, int y){
	        Pair<T, Integer> temp = data[x];
	        data[x] = data[y];
		    data[y] = temp;
	    }
		/**
		 * This method goes down the heap and compare the nodes children to find the smaller of the 
		 * two and then swaps it with the parent and continues down the heap.
		 * @param index
		 */
		private void shiftDown(int index){
			int lChild = (2 * index) + 1;
		    int rChild = (2 * index) + 2;
		 
		    if(rChild >= size && lChild >= size) return;
		    
		    int smlChild = (comp.compareTo(data[rChild].b, data[lChild].b) > 0 ? lChild : rChild);
		 
		    if(comp.compareTo(data[index].b, data[smlChild].b) > 0) {
		    	swap(smlChild, index);
		        shiftDown(smlChild);
		    }
		}
	}

	
	/**
	 * This is a private inner class of comparator that acts as the default comparator between
	 * T's
	 * @author EmilyJ
	 *
	 */
	private static class Comparator{
		/**
		 * It returns a positive number if the first is bigger than the second
		 * A negative number if the second is bigger than the first
		 * and a 0 if they are equal.
		 * @param a
		 * @param b
		 * @return
		 */
		public int compareTo(Integer a, Integer b){
			if (a > b){
				return 1;}
			else if (b > a){
				return -1;}
			else{
				return 0;
			}
		}
	}
}


