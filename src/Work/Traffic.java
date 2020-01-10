

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

class road {
	String ramp;
	int low;
	int high;

	road(String ramp, int low, int high) {
		this.ramp = ramp;
		this.low = low;
		this.high = high;
	}
}

public class Traffic {
	public static void main(String[] args) throws IOException {
		int high = 999999999;
		int low = -1;
		int N = 0;
		BufferedReader br = new BufferedReader(new FileReader("traffic.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		road[] roads = new road[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			roads[i] = new road(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for (int i = N-1; i > -1; i--) {
			if (roads[i].ramp.equals("none")) {
				low = Math.max(roads[i].low, low);
				System.out.println(roads[i].high);
				System.out.println(Math.min(roads[i].high, high));
				high = Math.min(roads[i].high, high);
				
			}
			if (roads[i].ramp.equals("off")) {
				low += roads[i].low;
				high += roads[i].high;
			}

			if (roads[i].ramp.equals("on")) {
				low -= roads[i].high;
				high -= roads[i].low;
				low = Math.max(0, low);
			}
			
		}
		System.out.println(low);
		System.out.println(high);
		FileWriter fw = new FileWriter("traffic.out");
		fw.write("" + low);
		fw.write(" " + high);
		high = 999999999;
		low = -1;
		for (int i = 0; i <N; i++) {
			if (roads[i].ramp.equals("none")) {
				low = Math.max(roads[i].low, low);
				high = Math.min(roads[i].high, high);
			}
			if (roads[i].ramp.equals("on")) {
				low += roads[i].low;
				high += roads[i].high;
			}

			if (roads[i].ramp.equals("off")) {
				low -= roads[i].high;
				high -= roads[i].low;
				low = Math.max(low, 0);
			}
			
		}
		
		fw.write("\n");
		fw.write("" + low);
		fw.write(" " + high);
		fw.close();
	}
}
