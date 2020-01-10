import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

class Boot {
	int depth;
	int stepDistance;
	Boot(int depth,int stepDistance){
		this.depth = depth;
		this.stepDistance = stepDistance;
	}
}
public class snowboots { 
	 static int[] tiles; 
	 static int[][] mem; 
	 static Boot[] boots;
	 static boolean visited[];
	 static int N;
	 static int B;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		mem = new int[B][N];
		for(int i=0;i<B;i++) {
			for(int j=0;j<N;j++) {
				mem[i][j] = -1;
			}
		}
		visited = new boolean[B];
		tiles = new int[N];
		boots = new Boot[B];
		st = new StringTokenizer(br.readLine());
		for(int i = 0;i<N;i++) {
			tiles[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0;i<B;i++) {
			st = new StringTokenizer(br.readLine());
			boots[i] = new Boot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		FileWriter fw = new FileWriter("snowboots.out");
		fw.write(bootCheck(0,0)+"");
		fw.close();
	}
	static int bootCheck(int boot,int tile) {
		int answer = Integer.MAX_VALUE;
		
		if(mem[boot][tile] != -1) {
			return mem[boot][tile];
		}
		if(boots[boot].depth<tiles[tile]) {
			return answer;
		}
		
		if(tile >= N-1) {
			return boot;
		}
		
		for(int i = 1; i<=boots[boot].stepDistance && tile+i<N; i++) {
				answer = Math.min(answer, bootCheck(boot,tile+i));
				mem[boot][tile+i] = answer;
		}
		
		for(int i = 1; boot+i<B; i++) {
			answer = Math.min(answer, bootCheck(boot+i,tile));
			mem[boot+i][tile] = answer;
		}

		return answer;
	}
}
