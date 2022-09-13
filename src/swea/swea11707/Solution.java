package swea.swea11707;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Solution {
	
	private static int n, m;
	
	private final static UserSolution usersolution = new UserSolution();
	
	private static char[][] words = new char[4000][11];
	
	private static int mstrcmp(char[] a, char[] b)
	{
		int i;
		for (i = 0; a[i] != '\0'; i++)
		{
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return a[i] - b[i];
	}

	private static void String2Char(String s, char[] b) {
		int n = s.length();
		for (int i = 0; i < n; ++i) {
			b[i] = s.charAt(i);
		}
		b[n] = '\0';
	}
	
	private static void inputWords(int wordCnt, Scanner sc) {
		
		for (int i = 0; i < wordCnt; ++i) {
			String2Char(sc.next(), words[i]);	
		}
	}
	
	private static boolean run(int m, Scanner sc) {
		
		boolean accepted = true;
		char[][] correctWord = new char[5][11];
		char[][] answerWord = new char[5][11];
		
		while(m-- > 0) {
			
			int id, timestamp, correctWordN, answerWordN;
			int wordIdx;
			
			id = sc.nextInt();
			timestamp = sc.nextInt();
			wordIdx = sc.nextInt();
			
			correctWordN = usersolution.search(id, timestamp, words[wordIdx], correctWord);
			
			answerWordN = sc.nextInt();
			
			for (int i = 0; i < answerWordN; ++i) {
				String2Char(sc.next(), answerWord[i]);
			}
			
			if (correctWordN != answerWordN) {				
				accepted = false;
			} else {
				for (int i = 0; i < answerWordN; ++i) {
					boolean isExist = false;
					
					for (int j = 0; j < correctWordN ; ++j) {
						if (mstrcmp(answerWord[i], correctWord[j]) == 0) {
							isExist = true;
						}
					}
					
					if (!isExist) {
						accepted = false;
					}
				}
			}
		}
		
		return accepted;
	}
	
	public static void main(String[] args) throws Exception {
		
		int test, T;
		int wordCnt;
		
		//System.setIn(new java.io.FileInputStream("sample_input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for (test = 1 ; test <= T ; ++test) {
			
			wordCnt = sc.nextInt();
			
			inputWords(wordCnt, sc);
			
			n = sc.nextInt();
			m = sc.nextInt();
			
			usersolution.init(n);
			
			if (run(m, sc)) {
				System.out.println("#" + test + " 100");
			} else {
				System.out.println("#" + test + " 0");
			}
		}
	}
}

class UserSolution {
	class Record {
		int searchTimestamp;
		char[] searchWord;
		
		public Record(int searchTimestamp, char[] searchWord) {
			this.searchTimestamp = searchTimestamp;
			this.searchWord = searchWord;
		}
	}
	
	Map<Integer, Record> searchHistory;
	Map<String, Map<String, Set<Integer>>> tempDB;
	Map<String, char[][]> DB;
	
	int mstrcmp(char[] a, char[] b) {
		int i;
		for (i = 0; a[i] != '\0'; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return a[i] - b[i];
	}
	
	int mstrcntdiff(char[] a, char[] b) {
		int i, cnt = 0;
		for (i = 0; a[i] != '\0'; i++) {
			if (a[i] != b[i]) cnt++;
		}
		return cnt;
	}

	int mstrncmp(char[] a, char[] b, int len) {
		for (int i = 0; i < len; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return 0;
	}

	int mstrlen(char[] a) {
		int len = 0;

		while (a[len] != '\0')
			len++;

		return len;
	}

	void mstrcpy(char[] dest, char[] src) {
		int i = 0;
		while (src[i] != '\0') {
			dest[i] = src[i];
			i++;
		}
		dest[i] = src[i];
	}

	void mstrncpy(char[] dest, char[] src, int len) {
		for (int i = 0; i < len; i++) {
			dest[i] = src[i];
		}
		dest[len] = '\0';
	}
	
	boolean cmplendiff1words(char[] longword, char[] shortword) {
		boolean wrong = false;

		for(int i = 0; shortword[i] != '\0'; i++) {
			if(!wrong && longword[i] != shortword[i]) {
				wrong = true;
				continue;
			}
			
			if(wrong && longword[i+1] != shortword[i]) return false;
		}
		
		return true;
	}
	
	void putWord(char[][] correctWord, char[] word) {
		for(int i = 0; i < 5; i++) {
			if(correctWord[i][0] == '\0') {
				mstrncpy(correctWord[i], word, word.length);
				return;
			}
		}
	}
	
	void init(int n) {
		searchHistory = new HashMap<>();
		tempDB = new HashMap<>();
		DB = new HashMap<>();
	}
	
	int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {
		if(DB.containsKey(new String(searchWord))) {
			int cnt = 0;
			char[][] correct = DB.get(new String(searchWord));
			
			for(int i = 0; i < 5; i++) {
				if(correct[i][0] != '\0') {
					cnt++;
					mstrcpy(correctWord[i], correct[i]);
				}
			}
			return cnt;
		}
		else if(!searchHistory.containsKey(mId) || searchTimestamp - searchHistory.get(mId).searchTimestamp > 10) {
			searchHistory.put(mId, new Record(searchTimestamp, searchWord));
			return 0;
		}
		
		Record record = searchHistory.get(mId);
		
		int len1 = mstrlen(searchWord);
		int len2 = mstrlen(record.searchWord);
		
		// 길이가 같은 경우
		if(len1 == len2) {
			if(mstrcntdiff(searchWord, record.searchWord) == 1) {
				String wrong = new String(record.searchWord);
				String correct = new String(searchWord);
				
				if(!tempDB.containsKey(wrong)) {
					tempDB.put(wrong, new HashMap<>());
					tempDB.get(wrong).put(correct, new HashSet<>());
					tempDB.get(wrong).get(correct).add(mId);
				}
				else {
					if(!tempDB.get(wrong).containsKey(correct)) {
						tempDB.get(wrong).put(correct, new HashSet<>());
						tempDB.get(wrong).get(correct).add(mId);
					}
					else {
						tempDB.get(wrong).get(correct).add(mId);
					}
				}
				
				if(tempDB.get(wrong).get(correct).size() == 3) {
					if(!DB.containsKey(wrong)) {
						DB.put(wrong, new char[5][11]);
						putWord(DB.get(wrong), correct.trim().toCharArray());
					}
					else {
						putWord(DB.get(wrong), correct.trim().toCharArray());
					}
				}
			}
		}
		// 길이 차이가 1인 경우
		else if(Math.abs(len2 - len1) == 1) {
			// 삭제
			if(len1 > len2 && cmplendiff1words(searchWord, record.searchWord)) {
				String wrong = new String(record.searchWord);
				String correct = new String(searchWord);
				
				if(!tempDB.containsKey(wrong)) {
					tempDB.put(wrong, new HashMap<>());
					tempDB.get(wrong).put(correct, new HashSet<>());
					tempDB.get(wrong).get(correct).add(mId);
				}
				else {
					if(!tempDB.get(wrong).containsKey(correct)) {
						tempDB.get(wrong).put(correct, new HashSet<>());
						tempDB.get(wrong).get(correct).add(mId);
					}
					else {
						tempDB.get(wrong).get(correct).add(mId);
					}
				}
				
				if(tempDB.get(wrong).get(correct).size() == 3) {
					if(!DB.containsKey(wrong)) {
						DB.put(wrong, new char[5][11]);
						putWord(DB.get(wrong), correct.trim().toCharArray());
					}
					else {
						putWord(DB.get(wrong), correct.trim().toCharArray());
					}
				}
			}
			// 치환
			else if(len1 < len2 && cmplendiff1words(record.searchWord, searchWord)) {
				String wrong = new String(record.searchWord);
				String correct = new String(searchWord);
				
				if(!tempDB.containsKey(wrong)) {
					tempDB.put(wrong, new HashMap<>());
					tempDB.get(wrong).put(correct, new HashSet<>());
					tempDB.get(wrong).get(correct).add(mId);
				}
				else {
					if(!tempDB.get(wrong).containsKey(correct)) {
						tempDB.get(wrong).put(correct, new HashSet<>());
						tempDB.get(wrong).get(correct).add(mId);
					}
					else {
						tempDB.get(wrong).get(correct).add(mId);
					}
				}
				
				if(tempDB.get(wrong).get(correct).size() == 3) {
					if(!DB.containsKey(wrong)) {
						DB.put(wrong, new char[5][11]);
						putWord(DB.get(wrong), correct.trim().toCharArray());
					}
					else {
						putWord(DB.get(wrong), correct.trim().toCharArray());
					}
				}
			}
		}
		
		return 0;
	}
}
