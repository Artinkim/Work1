

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HomeWork {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("homework.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] prefixSum = new int[N];
		int[] prefix = new int[N];
		ArrayList<Integer> answer = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			prefix[i] = arr[i];
			prefixSum[i] = arr[i];
		}
		
		for (int i = 1; i <N-1; i++) {
			prefixSum[N-i-1] = arr[N-1-i]+prefixSum[N-i];
			prefix[N-i-1] = Math.min(arr[N-1-i], prefix[N-i]);
		}
		float high = Float.MIN_VALUE;
		for (int i = 1; i < N-1; i++) {
			if((float)((float)(prefixSum[i]-prefix[i])/(N-i))==high) {
				answer.add(i);
			} 
			if( (float) ((float)(prefixSum[i]-prefix[i])/(N-i))>high) {
				high = (float)((float)(prefixSum[i]-prefix[i])/(N-i));
				answer = new ArrayList<Integer>();
				answer.add(i);
			}
		}
		FileWriter fw = new FileWriter("homework.out");
		for (int i = 0; i < answer.size(); i++) {
			fw.write(answer.get(i)+"\n");
		}
		fw.close();
	}

}
