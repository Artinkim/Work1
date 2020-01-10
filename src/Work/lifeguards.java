
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class SENodes implements Comparable<SENodes> {
	boolean start;
	int pos;
	int index;

	SENodes(boolean start, int pos, int index) {
		this.start = start;
		this.pos = pos;
		this.index = index;
	}

	@Override
	public int compareTo(SENodes o) {
		// TODO Auto-generated method stub
		if (this.pos == o.pos) {
			if (this.start) {
				return -1;
			}
			return 1;
		}
		return this.pos - o.pos;
	}
}

class Range implements Comparable<Range>{
	SENodes start;
	SENodes end;

	Range(SENodes start, SENodes end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Range o) {
		// TODO Auto-generated method stub
		return this.start.pos - o.start.pos;
	}
	

}

public class lifeguards {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		SENodes[] nodes = new SENodes[N * 2];
		Range[] ranges = new Range[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ranges[i] = new Range(new SENodes(true, Integer.parseInt(st.nextToken()), i),
					new SENodes(false, Integer.parseInt(st.nextToken()), i));
		}
		for (int i = 0; i < N; i++) {
			nodes[i] = ranges[i].start;
			nodes[i + N] = ranges[i].end;
		}
		Arrays.sort(nodes);
		ArrayList<SENodes> tempArr = new ArrayList<SENodes>();
		int[] lostTime = new int[N];
		int temp = 0;
		int low = 999999999;
		int index = -1;
		for (int i = 0; i < 2 * N; i++) {
			if (tempArr.size() == 1) {
				lostTime[tempArr.get(0).index] += nodes[i].pos - temp;
			}
			if (nodes[i].start) {
				tempArr.add(nodes[i]);
				
			} else {	
				if (lostTime[nodes[i].index] < low) {
					low = lostTime[nodes[i].index];
					index = nodes[i].index;
				}
				tempArr.remove(ranges[nodes[i].index].start);
			}
				temp = nodes[i].pos;
		}
		ranges[index].start.pos = 0;
		ranges[index].end.pos = -1;
		int timeCovered = 0;
		int max = 0;
		Arrays.sort(ranges);
		for (int i = 1; i < ranges.length; i++) {
			if(ranges[i].start.pos-max>0) {
				timeCovered += ranges[i].end.pos-ranges[i].start.pos;
			} else {
				timeCovered+=ranges[i].end.pos-max;
			}
			max = ranges[i].end.pos;
		}
		FileWriter fw = new FileWriter("lifeguards.out");
		fw.write(timeCovered+"");
		fw.close();
	}
}
