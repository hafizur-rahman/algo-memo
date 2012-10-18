package com.jdreamer.algo.trie;

import java.util.List;
import java.util.Set;

public interface Trie<T> {
	public void add(String key, T value);
	
	public T find(String key);
	
	public List<T> search(String prefix);
	
	public boolean contains(String key);
	
	public Set<String> getAllKeys();
	
	public int size();
}