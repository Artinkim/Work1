

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LemonNade {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int i;
		for(i = N-1;i>-1;i--) {
			if(arr[i]<N-1-i) {
				break;
			}
		}
		FileWriter fw = new FileWriter("lemonade.out");
		fw.write(N-i-1+"");
		fw.close();
	}
}
