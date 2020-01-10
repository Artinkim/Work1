

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Shuffle {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken())-1;
		}
		int[] arr2 = new int[N];
		for(int n = 0;n<20;n++) {
			for (int i = 0; i < N; i++) {
				arr2[i] = arr[arr[i]];
			}
			arr = arr2;
			arr2 = new int[N];
		}
		int number = 0;
		boolean[] used = new boolean[N];
		for(int i = 0;i<N;i++) {
			if(!used[arr[i]]) {
				used[arr[i]] = true;
				number++;
			}
		}
		FileWriter fw = new FileWriter("shuffle.out");
		fw.write(number + "");
		fw.close();
	}

}
