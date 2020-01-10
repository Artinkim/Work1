

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GrassPlanting {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("planting.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] plots = new ArrayList[N];
		int[] grassColor = new int[N];
		
		Arrays.fill(grassColor, -1); 
		for (int i = 0; i < N; i++) {
			plots[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int temp1 = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			plots[temp1 - 1].add(temp2 - 1);
			plots[temp2 - 1].add(temp1 - 1);
		}
		int maxColor = 0;
		grassColor[0] = 0;
		
		boolean[] takenColors = new boolean[N];
		for (int i = 1; i < N; i++) {
			Arrays.fill(takenColors,false);
			for (int j = 0; j < plots[i].size(); j++) {
				if (grassColor[plots[i].get(j)] != -1)
					takenColors[grassColor[plots[i].get(j)]] = true;
				for (int n = 0; n < plots[plots[i].get(j)].size(); n++) {
					if (grassColor[plots[plots[i].get(j)].get(n)] != -1)
						takenColors[grassColor[plots[plots[i].get(j)].get(n)]] = true;
				}
			}
			grassColor[i] = 0;
			while (grassColor[i] <= maxColor && takenColors[grassColor[i]]) {grassColor[i]++;}
			maxColor = Math.max(maxColor, grassColor[i]);
			
		}
		FileWriter fw = new FileWriter("planting.out");
		fw.write(maxColor + 1 + "");
		fw.close();
	}
}
