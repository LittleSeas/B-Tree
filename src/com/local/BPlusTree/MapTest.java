package com.local.BPlusTree;

import java.util.*;
import java.util.Map.Entry;

public class MapTest {
	public static void main(String[] args) {
		Map<Integer, Integer> mm = new HashMap<Integer, Integer>();
		for (int i = 0; i < 10; i++) {
			mm.put(i, i);
		}
		for (Entry<Integer, Integer> e : mm.entrySet()) {
			System.out.println("key:" + e.getKey());
			System.out.println("value:" + e.getValue());
		}
		for (Iterator<Entry<Integer, Integer>> i = mm.entrySet().iterator(); i
				.hasNext();) {
			Entry<Integer, Integer> e = i.next();
			System.out.println("key:" + e.getKey());
			System.out.println("value:" + e.getValue());
		}
	}
}