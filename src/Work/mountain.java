

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class point implements Comparable<point> {
	int x;
	int y;

	point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(point o) {
		// TODO Auto-generated method stub
		return o.y - this.y;
	}
}

public class mountain {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		boolean[] band = new boolean[N];
		ArrayList<point> peaks = new ArrayList<point>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			peaks.add(new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(peaks);
		int sub = 0;
		for (int i = 0; i < peaks.size() - 1; i++) {
			if (!band[i]) {
				for (int j = peaks.size() - 1; j > i; j--) {
					if (!band[j]) {
						if (peaks.get(j).x >= peaks.get(i).x - peaks.get(i).y + peaks.get(j).y
								&& peaks.get(j).x <= peaks.get(i).x + peaks.get(i).y - peaks.get(j).y) {
							band[j] = true;
							sub++;
						}
					}
				}
			}
		}
		FileWriter fw = new FileWriter("mountains.out");
		fw.write(peaks.size()-sub + "");
		fw.close();
	}
}
