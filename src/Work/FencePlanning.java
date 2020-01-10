package Work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

class IntPair {
	public int x;
	public int y;

	IntPair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class FencePlanning {
	static int N;
	static int M;
	static ArrayList<IntPair> grid = new ArrayList<IntPair>();
	static ArrayList<Integer>[] connections = new ArrayList[100000];
	static int lowX;
	static int lowY;
	static int highX;
	static int highY;
	static Stack<Integer> stack = new Stack<Integer>();
	static boolean[] visited = new boolean[100000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src/Work/Fence"));
		String str = br.readLine();
		String[] arr = str.split(" ");
		N = Integer.parseInt(arr[0]);
		M = Integer.parseInt(arr[1]);
		str = br.readLine();
		for (int i = 0; i < N; i++) {
			arr = str.split(" ");
			grid.add(new IntPair(Integer.parseInt(arr[0]) - 1, (Integer.parseInt(arr[1]) - 1)));
			str = br.readLine();
		}
		for (int i = 0; i < connections.length; i++) {
			connections[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			arr = str.split(" ");
			connections[Integer.parseInt(arr[0]) - 1].add(Integer.parseInt(arr[1]) - 1);
			connections[Integer.parseInt(arr[1]) - 1].add(Integer.parseInt(arr[0]) - 1);
			str = br.readLine();
		}
		br.close();
		int finalFences = Integer.MAX_VALUE;
		for (int i = 0; i < grid.size(); i++) {
			if (visited[i])
				continue;
			lowX = Integer.MAX_VALUE;
			lowY = Integer.MAX_VALUE;
			highY = 0;
			highX = 0;
			floodFill(i);
			int fences = (highX - lowX + highY - lowY) * 2;
			if (finalFences > fences) {
				finalFences = fences;
			}
		}
		System.out.println(finalFences);
	}

	public static void floodFill(int current) {
		if (visited[current])
			return;
		visited[current] = true;
		for (int i = 0; i < connections[current].size(); i++) {
			if (!visited[connections[current].get(i)]) {
				stack.push(connections[current].get(i));
			}
		}
		if (grid.get(current).x < lowX) {
			lowX = grid.get(current).x;
		}
		if (grid.get(current).y < lowY) {
			lowY = grid.get(current).y;
		}
		if (grid.get(current).x > highX) {
			highX = grid.get(current).x;
		}
		if (grid.get(current).y > highY) {
			highY = grid.get(current).y;
		}
		if (!stack.isEmpty()) {
			current = stack.pop();
			floodFill(current);
		}

	}
}
