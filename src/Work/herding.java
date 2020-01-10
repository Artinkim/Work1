import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class herding {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("herding.in"));
		int a = 0, b = 0, c = 0;
		int s = 0;
		int l = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		if (a == b - 1 && b + 1 == c) {
			
		} else {
			if (a == b - 2 || b + 2 == c) {
				s = 1;
			}else {
				s = 2;
			}
			
			if (b - a >= c - b) {
				l = b - a - 1;
			} else {
				l = c - b - 1;
			}
			
		}

		FileWriter fr = new FileWriter("herding.out");
		fr.write(s + "");
		fr.write("\n");
		fr.write(l + "");
		fr.close();
	}

}
