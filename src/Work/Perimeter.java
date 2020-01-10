

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Perimeter {
	static int N = 0;
	static boolean[][] grid;
	static boolean[][] visited;
	static Queue<Point> queue = new LinkedList<Point>();
	static Queue<Point> currentQueue = new LinkedList<Point>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		grid = new boolean[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				if (str[j].equals("#")) {
					grid[i][j] = true;
					queue.add(new Point(i, j));
				}
			}
		}
		int area = -1;
		int perimeter = -1;
		for (int i = 0; i < queue.size(); i++) {
			Point tempPoint = queue.poll();
			if (visited[tempPoint.x][tempPoint.y])
				continue;
			int tempArea = 1;
			int tempPerimeter = 0;
			visited[tempPoint.x][tempPoint.y] = true;
			currentQueue.add(tempPoint);
			while (!currentQueue.isEmpty()) {
				Point point = currentQueue.poll();
				if (point.x != 0) {
					if (grid[point.x - 1][point.y]) {
						if (!visited[point.x - 1][point.y]) {
							visited[point.x-1][point.y] = true;
							currentQueue.add(new Point(point.x - 1, point.y));
							tempArea++;
						}
					} else {
						tempPerimeter++;
					}
				} else {
					tempPerimeter++;
				}
				
				if (point.x != N - 1) {
					if (grid[point.x + 1][point.y]) {
						if (!visited[point.x + 1][point.y]) {
							visited[point.x+1][point.y] = true;
							currentQueue.add(new Point(point.x + 1, point.y));
							tempArea++;
						}
					} else {
						tempPerimeter++;
					}
				} else {
					tempPerimeter++;
				}
				
				if (point.y != 0) {
					if (grid[point.x][point.y - 1]) {
						if (!visited[point.x][point.y - 1]) {
							visited[point.x][point.y-1] = true;
							currentQueue.add(new Point(point.x, point.y - 1));
							tempArea++;
						}
					} else {
						tempPerimeter++;
					}
				} else {
					tempPerimeter++;
				}
				
				if (point.y != N - 1) {
					if (grid[point.x][point.y + 1]) {
						if (!visited[point.x][point.y + 1]) {
							visited[point.x][point.y+1] = true;
							currentQueue.add(new Point(point.x, point.y + 1));
							tempArea++;
						}
					} else {
						tempPerimeter++;
					}
				} else {
					tempPerimeter++;
				}
			}
			
			if(area == tempArea) {
				perimeter = Math.min(perimeter, tempPerimeter);
			}
			perimeter = Math.max(perimeter, tempPerimeter);
			area = Math.max(area, tempArea);

		}
		FileWriter fw = new FileWriter("perimeter.out");
		fw.write(area+" ");
		fw.write(perimeter+"");
		fw.close();

	}

}
