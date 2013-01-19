import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		collatzConjecture(1000);
	}

	public static void collatzConjecture(int start) {
		Function<Integer, List<Integer>> successors = new Function<Integer, List<Integer>>() {
			@Override
			public List<Integer> apply(Integer o) {
				List<Integer> l = new ArrayList<Integer>();
				if (o <= 1) {
					// Done
				} else if (o % 2 == 0) {
					l.add(o / 2);
				} else {
					l.add(o * 3 + 1);
				}
				return l;
			}
		};
		
		Function<Integer, Boolean> goal = new Function<Integer, Boolean>() {
			int count = 0;
			@Override
			public Boolean apply(Integer o) {
				boolean done = o <= 1;
				System.out.printf("%d%s", o, done ? ": " + count + " steps\n" : ", ");
				count++;
				return done;
			}
		};
		
		Search.search(start, Impl.<Integer>queue(), successors, goal);
	}
}
