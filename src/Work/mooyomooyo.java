

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class point {
	int x;
	int y;

	point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class mooyomooyo {
	public static void main(String[] args) throws IOException {
		Queue<point> queue = new LinkedList<point>();
		Queue<point> currentQueue = new LinkedList<point>();
		BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] grid = new int[10][N];
		boolean[][] visited = new boolean[10][N];

		for (int i = N - 1; i > -1; i--) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < 10; j++) {
				grid[j][i] = Integer.parseInt(str[j]);
				if (grid[j][i] != 0)
					queue.add(new point(j, i));
			}
		}
		boolean again = true;
		int count = 0;
		while (again) {
			count++;
			again = false;
			visited = new boolean[10][N];
			while (!queue.isEmpty()) {
				point currentPoint = queue.poll();
				if (visited[currentPoint.x][currentPoint.y])
					continue;
				int color = grid[currentPoint.x][currentPoint.y];
				Queue<point> tempQueue = new LinkedList<point>();
				visited[currentPoint.x][currentPoint.y] = true;
				currentQueue.add(currentPoint);
				System.out.println("COLOR" + color);
				while (!currentQueue.isEmpty()) {
					point tempPoint = currentQueue.poll();
					System.out.print(tempPoint.x);
					System.out.print(tempPoint.y);
					tempQueue.add(tempPoint);
					if (tempPoint.x != 0) {
						if (!visited[tempPoint.x - 1][tempPoint.y]) {
							if (grid[tempPoint.x - 1][tempPoint.y] == color) {
								visited[tempPoint.x - 1][tempPoint.y] = true;
								currentQueue.add(new point(tempPoint.x - 1, tempPoint.y));
							}
						}
					}

					if (tempPoint.y != 0) {
						if (!visited[tempPoint.x][tempPoint.y - 1]) {
							if (grid[tempPoint.x][tempPoint.y - 1] == color) {
								visited[tempPoint.x][tempPoint.y - 1] = true;
								currentQueue.add(new point(tempPoint.x, tempPoint.y - 1));
							}
						}
					}

					if (tempPoint.x != 9) {
						if (!visited[tempPoint.x + 1][tempPoint.y]) {
							if (grid[tempPoint.x + 1][tempPoint.y] == color) {
								visited[tempPoint.x + 1][tempPoint.y] = true;
								currentQueue.add(new point(tempPoint.x + 1, tempPoint.y));
							}
						}
					}

					if (tempPoint.y != N - 1) {
						if (!visited[tempPoint.x][tempPoint.y + 1]) {
							if (grid[tempPoint.x][tempPoint.y + 1] == color) {
								visited[tempPoint.x][tempPoint.y + 1] = true;
								currentQueue.add(new point(tempPoint.x, tempPoint.y + 1));
							}
						}
					}
				}
				if (tempQueue.size() >= K) {
					again = true;
					while (!tempQueue.isEmpty()) {
						point p = tempQueue.poll();
						grid[p.x][p.y] = 0;
						System.out.print(grid[p.x][p.y]);
					}
				}
			}
			boolean falling = true;
			while (falling) {
				falling = false;
				for (int i = 0; i < N - 1; i++) {
					for (int j = 0; j < 10; j++) {
						if (grid[j][i] == 0) {
							if (grid[j][i + 1] != 0) {
								falling = true;
								grid[j][i] = grid[j][i + 1];
								grid[j][i + 1] = 0;
							}
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 10; j++) {
					if (grid[j][i] != 0) {
						queue.add(new point(j, i));
					}
				}
			}
			System.out.println(again);
		}
		FileWriter fw = new FileWriter("mooyomooyo.out");
		
		for (int i = N -1; i > -1; i--) {
			for (int j = 0; j < 10; j++) {
				fw.write(grid[j][i]+"");
			}
			fw.write("\n");
		}
		fw.close();
	}

}
