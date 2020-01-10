

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Revegetation {
	static int N, M;
	static ArrayList<Integer>[] pastures;
	static int[] pastureSeed;
	static boolean[] available;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pastureSeed = new int[N];
		available = new boolean[4];
		pastures = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			pastures[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int temp1 = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			pastures[temp1 - 1].add(temp2 - 1);
			pastures[temp2 - 1].add(temp1 - 1);
		}
		Arrays.fill(pastureSeed, -1); 
		pastureSeed[0] = 0;
		
		for (int i = 1; i < N; i++) {
			Arrays.fill(available, true);
			for (int j = 0; j < pastures[i].size(); j++) {
				if(pastureSeed[pastures[i].get(j)]!=-1)
					available[pastureSeed[pastures[i].get(j)]] = false;
			}
			int s;
			for(s= 0;s<4;s++) {
				if(available[s])
					break;
			}
			pastureSeed[i] = s;
		}
		String answer = "";
		for (int i = 0; i < N; i++) {
			answer += pastureSeed[i]+1;
		}
		FileWriter fw = new FileWriter("revegetate.out");
		fw.write(answer);
		fw.close();
	}

}
