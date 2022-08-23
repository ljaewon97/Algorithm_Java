package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class G4_04386_별자리_만들기 {
	static double[][] edges, stars;
	static int[] parent, depth;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		stars = new double[N][2];
		edges = new double[N*(N-1)/2][];
		parent = new int[N];
		depth = new int[N];
		
		for(int i = 0; i < N; i++) {
			parent[i] = i;
			depth[i] = 1;
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		int idx = 0;
		
		for(int i = 0; i < N-1; i++) {
			double x1 = stars[i][0];
			double y1 = stars[i][1];
			
			for(int j = i+1; j < N; j++) {
				double x2 = stars[j][0];
				double y2 = stars[j][1];
				
				double dist = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
				edges[idx++] = new double[] {i, j, dist};
			}
		}
		
		Arrays.sort(edges, new Comparator<double[]>() {
			public int compare(double[] o1, double[] o2) {
				return Double.compare(o1[2], o2[2]);
			}
		});
		
		int edgeCnt = 0;
		double ans = 0;
		
		for(int i = 0; i < edges.length; i++) {
			if(edgeCnt == N-1) break;
			
			if(union((int) edges[i][0], (int) edges[i][1])) {
				edgeCnt++;
				ans += edges[i][2];
			}
		}
		
		System.out.println(ans);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	static boolean union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		
		if(p1 == p2) return false;
		
		if(depth[p1] < depth[p2]) {
			parent[p1] = p2;
			depth[p2] += depth[p1];
		}
		else {
			parent[p2] = p1;
			depth[p1] += depth[p2];
		}
		
		return true;
	}
}
