package Work;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class endStartPoint implements Comparable<endStartPoint> {
	Point point;
	boolean start;
	int index;

	endStartPoint(Point point, int index, boolean start) {
		this.point = point;
		this.index = index;
		this.start = start;
	}

	@Override
	public int compareTo(endStartPoint o) {
		// TODO Auto-generated method stub
		if (this.point.x == o.point.x) {
			if (this.start) {
				return -1;
			}
			return 1;
		}
		return this.point.x - o.point.x;
	}

}

class Line {
	Point start;
	Point end;

	Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

}

public class CowSteeplechase {
	static int N;
	static ArrayList<Line> lines = new ArrayList<Line>();
	static ArrayList<endStartPoint> points = new ArrayList<endStartPoint>();
	static ArrayList<endStartPoint> activePoints = new ArrayList<endStartPoint>();
	static boolean[] intersecting;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src/Work/Hurdel"));
		String str = br.readLine();
		String[] arr = str.split(" ");
		N = Integer.parseInt(arr[0]);
		intersecting = new boolean[N];

		for (int i = 0; i < N; i++) {
			str = br.readLine();
			arr = str.split(" ");
			if (Integer.parseInt(arr[0]) < Integer.parseInt(arr[2])) {
				lines.add(new Line(new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])),
						new Point(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]))));
			} else {
				lines.add(new Line(new Point(Integer.parseInt(arr[2]), Integer.parseInt(arr[3])),
						new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]))));
			}
		}
		for (int i = 0; i < N; i++) {
			points.add(new endStartPoint(lines.get(i).start, i, true));
			points.add(new endStartPoint(lines.get(i).end, i, false));
		}
		Collections.sort(points);
		for (int n = 0; n < N * 2; n++) {
			if (points.get(n).start) {
				activePoints.add(points.get(n));
			} else {
				for (int i = 0; i < activePoints.size(); i++) {
					if (Line2D.linesIntersect(lines.get(activePoints.get(i).index).start.x, lines.get(activePoints.get(i).index).start.y, lines.get(activePoints.get(i).index).end.x,
							lines.get(activePoints.get(i).index).end.y, lines.get(points.get(n).index).start.x,
							lines.get(points.get(n).index).start.y, lines.get(points.get(n).index).end.x,
							lines.get(points.get(n).index).end.y)) {
						if (intersecting[points.get(n).index]) {
							System.out.println(points.get(n).index + 1);
							break;
						} else {
							intersecting[points.get(n).index] = true;
							intersecting[activePoints.get(i).index] = true;
							
						}
					}
				}
				activePoints.remove(points.get(n));
			}
			
		}
	

	}

	/*
	 * for(int n = 0;n<N;n++) { for(int i = 0;i<N;i++) { if(p1.get(i).x == n) {
	 * pt1.add(p1.get(i)); pt2.add(p2.get(i)); } } for(int i = 0;i<pt1.size();i++) {
	 * if(pt2.get(i).x<n) { pt1.remove(i); pt2.remove(i); } } for(int i =
	 * 0;i<pt1.size();i++) { if(pt2.get(i).x<n) { pt1.remove(i); pt2.remove(i); }
	 * int num = 0; for(int j = i+1; j<pt1.size();j++) {
	 * if(Line2D.linesIntersect(pt1.get(i).x, pt1.get(i).y, pt2.get(i).x,
	 * pt2.get(i).y, pt1.get(j).x, pt1.get(j).y, pt2.get(j).x, pt2.get(j).y)) {
	 * num++; } } if(num>1) { for(int c = 0;c<N;c++) { if(p1.get(c).x ==
	 * pt1.get(i).x && p1.get(c).y == pt1.get(i).y) { if(p2.get(c).x == pt2.get(i).x
	 * && p2.get(c).y == pt2.get(i).y) { System.out.println(c+1); } } }
	 * 
	 * 
	 * } } }
	 */

}
