import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author EmilyJackson
 * @version 1.0
 *
 */
public class Impl {
	
	/**
	 * This is a method that returns a list of all adjacent vertices to the parameter vertecies
	 * @param map
	 * @return List of adjacent vertices
	 */
	public static <T> Function<T, List<T>> adjacencyList(final Map<T, List<T>> map) {
		return new Function<T, List<T>>() {
			
			public List<T> apply(T o) {
				
				return map.get(o);
			}
		};
	}
	/**
	 * This function calculate the euclidean distance squared between two points.
	 * @return  The function to calculate Euclidean distance
	 */
	public static Function<Pair<Point, Point>, Integer> euclideanDistanceSquared() {
		return new Function<Pair<Point,Point>, Integer>() {
			
			public Integer apply(Pair<Point, Point> o) {
				int distSquared = (int) (Math.pow(o.a.x - o.b.y, 2) + Math.pow(o.a.y - o.b.y, 2));
				return distSquared;
			}
		};
	}
	/**
	 * This function calculates the manhattan distance (as the crow flies) between two points. 
	 * @return
	 */
	public static Function<Pair<Point, Point>, Integer> manhattanDistance() {
		return new Function<Pair<Point,Point>, Integer>() {
			
			public Integer apply(Pair<Point, Point> o) {
				int horizontalDist = Math.abs(o.a.x - o.b.x);
				int verticalDist = Math.abs(o.a.y - o.b.y);
				return horizontalDist + verticalDist;
			}
		};
	}
	/**
	 * This structure acts as a queue (First in first out)
	 * @return
	 */
	public static <T> Structure<T> queue() {
		return new Structure<T>() {
			LinkedList<T> ll = new LinkedList<T>();
			/**
			 * Empties the queue
			 */
			public void clear() {
				ll.clear();	
			}
			/**
			 * Returns if the queue is empty
			 */
			public boolean isEmpty() {				
				return ll.isEmpty();
			}
			/**
			 * adds the item to the end of the queue
			 * 
			 */
			public void add(T item) {
				ll.addLast(item);
			}
			/**
			 * removes the first item in the queue
			 */
			public T remove() {				
				return ll.remove(0);
			}
		};
	}
	
	/**
	 * This structure implements like a stack (first in last out)
	 * @return
	 */
	public static <T> Structure<T> stack() {
		return new Structure<T>() {

			LinkedList<T> ll = new LinkedList<T>();
			/**
			 * clears the list
			 */
			public void clear() {
				ll.clear();
			}
			/**
			 * sees if the list is clear
			 */
			public boolean isEmpty() {
				return ll.isEmpty();
			}
			/**
			 * adds to the end of the list
			 */
			public void add(T item) {
				ll.addLast(item);
			}
			/**
			 * removes from the end of the list
			 */
			public T remove() {
				return ll.removeLast();
			}
		};
	}
	
}
