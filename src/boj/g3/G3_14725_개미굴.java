package boj.g3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class G3_14725_개미굴 {
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		class Room {
			private Map<String, Room> childRooms = new TreeMap<>();
			private boolean isLastRoom;
			private int depth = -1;
			
			public Map<String, Room> getChildRooms() {
				return childRooms;
			}
			
			public void setIsLastRoom(boolean isLastRoom) {
				this.isLastRoom = isLastRoom;
			}
			
			public int getDepth() {
				return depth;
			}
			
			public void setDepth(int depth) {
				this.depth = depth;
			}
		}
		
		class AntHouse {
			private Room entrance;
			
			public AntHouse() {
				entrance = new Room();
			}
			
			public Room getEntrance() {
				return entrance;
			}
			
			public void insert(String[] words) {
				Room thisRoom = this.entrance;
				int d = 0;
				
				for(int i = 0; i < words.length; i++, d++) {
					thisRoom = thisRoom.getChildRooms().computeIfAbsent(words[i], s -> new Room());
					thisRoom.setDepth(d);
				}
				thisRoom.setIsLastRoom(true);
			}
			
			public void print(String word, Room room) {
				if(room.getDepth() != -1) {
					StringBuilder lines = new StringBuilder();
					for(int i = 0; i < room.getDepth(); i++) {
						lines.append("--");
					}
					sb.append(lines).append(word).append("\n");
				}
				
				if(room.isLastRoom) {
					return;
				}
				else {
					for(Entry<String, Room> entry: room.getChildRooms().entrySet()) {
						print(entry.getKey(), entry.getValue());
					}
				}
			}
		}
		
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		AntHouse antHouse = new AntHouse();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			String[] words = new String[K];
			for(int j = 0; j < K; j++) {
				words[j] = st.nextToken();
			}
			antHouse.insert(words);
		}
		
		antHouse.print(null, antHouse.getEntrance());
		System.out.println(sb);
	}
}
