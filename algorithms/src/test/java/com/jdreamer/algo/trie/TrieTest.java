package com.jdreamer.algo.trie;

import junit.framework.TestCase;

public class TrieTest extends TestCase {
	Trie<Product> productTrie = new TrieImpl<Product>();
	
	public void setUp() throws Exception {
		productTrie.add("ham", new Product(1, "ham"));
		productTrie.add("hammer", new Product(2, "hammer"));
		productTrie.add("hammock", new Product(3, "hammock"));
		productTrie.add("ipod", new Product(4, "ipod"));
		productTrie.add("iphone", new Product(5, "iphone"));
	}
	
	public void testAdd() {
		assertEquals(5, productTrie.size());
	}
	
	public void testFind() {
		assertNotNull(productTrie.find("ham"));
	}
	
	public void testSearch() {
		assertEquals(3, productTrie.search("ha").size());
	}
	
	public void testContains() {
		assertEquals(true, productTrie.contains("ipod"));
	}
	
	public void testGetAllKeys() {
		assertEquals(5, productTrie.getAllKeys().size());
	}
	
	class Product {
		private int productId;
		private String productDesc;
		
		public Product(int productId, String productDesc) {
			this.productId = productId;
			this.productDesc = productDesc;
		}
		
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public String getProductDesc() {
			return productDesc;
		}
		public void setProductDesc(String productDesc) {
			this.productDesc = productDesc;
		}
		
	}
}