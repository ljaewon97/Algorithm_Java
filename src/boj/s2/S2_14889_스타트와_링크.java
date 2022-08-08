package boj.s2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S2_14889_스타트와_링크 {
	static int[][] abil;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N];
		abil = new int[N][N];
		int min = Integer.MAX_VALUE;
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			list.add(i);
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				abil[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<List<Integer>> comb = combResult(list, visited, 0, N, N/2);
		
		for(int i = 0; i < comb.size(); i++) {
			List<Integer> t1 = comb.get(i);
			List<Integer> t2 = new ArrayList<>();
			boolean[] v1 = new boolean[N/2];
			boolean[] v2 = new boolean[N/2];
			int a1 = 0, a2 = 0;
			for(Integer num: list) {
				if(!t1.contains(num)) {
					t2.add(num);
				}
			}
			
			List<List<Integer>> team1 = combResult(t1, v1, 0, N/2, 2);
			List<List<Integer>> team2 = combResult(t2, v2, 0, N/2, 2);
			
			a1 = getTeamScore(team1);
			a2 = getTeamScore(team2);
			
			int diff = Math.abs(a1 - a2);
			if(diff < min) {
				min = diff;
			}
		}
		
		System.out.println(min);
	}
	
	static int getTeamScore(List<List<Integer>> team) {
		int temp = 0;
		for(List<Integer> l: team) {
			temp += getAbil(abil, l.get(0), l.get(1));
		}
		return temp;
	}
	
	static int getAbil(int[][] abil, int p1, int p2) {
		return abil[p1][p2] + abil[p2][p1];
	}
	
	static List<List<Integer>> combResult(List<Integer> list, boolean[] visited, int start, int n, int r) {
		List<List<Integer>> res = new ArrayList<>();
		combination(res, list, visited, start, n, r);
		return res;
	}
	
	static void combination(List<List<Integer>> res, List<Integer> list, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			addToList(res, list, visited, n);
			return;
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(res, list, visited, i+1, n, r-1);
			visited[i] = false;
		}
	}
	
	static void addToList(List<List<Integer>> res, List<Integer> list, boolean[] visited, int n) {
		List<Integer> temp = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			if(visited[i]) {
				temp.add(list.get(i));
			}
		}
		res.add(temp);
	}
	
}
