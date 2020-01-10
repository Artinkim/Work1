

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class log implements Comparable<log>{
	int day;
	int cow;
	int change;
	log(int day, int cow, int change) {
		this.day = day;
		this.cow = cow;
		this.change = change;
	}
	@Override
	public int compareTo(log o) {
		return this.day - o.day;
	}
}
public class measurement {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		log[] arr = new log[N];
		int[] scores = new int[N];
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken())-1;
			int c;
			String d = st.nextToken();
			if(d.charAt(0) == '-') {
				c = -Integer.parseInt(d.substring(1));
			} else {
				c = Integer.parseInt(d.substring(1));
			}
			arr[i] = new log(a,b,c);
			
		}
		Arrays.sort(arr);
		int highscore = 0;
		int change = 0;
		ArrayList<Integer> current = new ArrayList<Integer>();
		for(int i = 0;i<N;i++) {
			scores[arr[i].cow] += arr[i].change;
			if(scores[arr[i].cow]>=highscore) {
				boolean replace = true;
				for(int n = 0;n<current.size();n++) {
					if(scores[arr[i].cow]>highscore)
					if(current.get(n) == arr[i].cow)
						replace = false;
				}
				if(replace) {
					change++;
					highscore = scores[arr[i].cow];
				}
			} else {
				
			}
		}
		FileWriter fw = new FileWriter("measurement.out");
		fw.write(highscore+"");
		fw.close();
	}
}
