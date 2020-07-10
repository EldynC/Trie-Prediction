

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Trie {
	static class TrieNode {
		LinkedList<TrieNode> children;
		int count;
		char value;
		boolean isEndOfWord;

		TrieNode(char c) {
			isEndOfWord = false;
			children = new LinkedList<TrieNode>();
			value = c;
			count = 0;
		}

		public TrieNode getChild(char c) {
			if (children != null)
				for (TrieNode child : children)
					if (child.value == c)
						return child;
			return null;
		}
	};

	static TrieNode root = new TrieNode(' ');
	static void insert(String key) {
		TrieNode current = root;
		for (int i = 0; i < key.length(); i++) {
			char characters = key.charAt(i);
			TrieNode newNode = current.getChild(characters);
			if (newNode != null)
				current = newNode;
			if (newNode == null) {
				current.children.add(new TrieNode(characters));
				current = current.getChild(characters);
			}

		}
		current.isEndOfWord = true;
		current.count++;
	}

	static boolean find(String key) {
		TrieNode current = root;
		for (int i = 0; i < key.length(); i++) {
			char characters = key.charAt(i);
			TrieNode newNode = current.getChild(characters);
			if (newNode == null) {
				return false;
			} else
				current = current.getChild(characters);
		}
		return current.isEndOfWord;
	}

	static HashMap<String,Integer> predict(String chars, int n) {
		TrieNode current = root;
		HashMap<String, Integer> b = new HashMap<String, Integer>();
		String a = "";
		DFS(current.getChild(chars.charAt(0)), chars, a, b);
		HashMap<String, Integer> c = sortByValue(b);
		List<String> list = new ArrayList<String>();
		int i = 0;
		for(String key: c.keySet())
		{
			if(i<n)
				list.add(key);
			i++;
		}
		System.out.println(list.toString());
		return b;

	}
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> mapList) 
    { 
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(mapList.entrySet()); 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        });  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> val : list) { 
            temp.put(val.getKey(), val.getValue()); 
        } 
        return temp; 
    } 
	

	

	 static String DFS(TrieNode root, String prefix, String a, HashMap<String,Integer> b) {
		if (root.isEndOfWord && root.children.isEmpty()) {
				a += root.value;
				b.put(a,root.count);
			return a;
		} else if (root.isEndOfWord && !root.children.isEmpty()) {
				a += root.value;
				b.put(a,root.count);
		}

		else if (!root.isEndOfWord) {
			a += root.value;
		}

		if (root.children.size() > 1) { 
			for(int x = 0; x < root.children.size(); x++)
				DFS(root.children.get(x),prefix,a,b);
		}

		else {
			DFS(root.children.get(0), prefix, a, b);
		}

		return a;
	}
	public static void main(String args[]) {
		String keys[] = { "test", "apple", "tester", "ten", "testing", "tennant", "tenure", "tenacity", "tentacle",
				"tenantry", "tendency", "tent", "tenor", "tend", "tenders", "tend", "tending", "tender", "test", "test",
				"test", "quarintine", "quaffle", "quarrel", "quirrell", "quirrell", "quirrell", "quirrell", "quaffle",
				"quaffle", "quaffle", "quaffle", "quarintine" };
		
		int i;
		for (i = 0; i < keys.length; i++)
			insert(keys[i]);

		predict("quar", 4);

	}
}
