package hr.fer.ikrpelnik.datastruct;

import java.util.Arrays;

import static junit.framework.Assert.*;

import org.junit.Test;

public class TestSuffixArray {

	public static void main(String[] args) {
		SuffixArray sa = new SuffixArray("banana");
		for(int i = 0; i <= 6; ++i) {
			System.out.println(sa.getSuffix(i));
		}
	}
	
	@Test
	public void testInitSA1() {
		SuffixArray sa = new SuffixArray("banana");
		int i = 0;
		for(String str : generateStrings("banana")) {
			assertEquals(str, sa.getSuffix(i));
			i++;
		}	
	}
	
	@Test
	public void testInitSA2() {
		SuffixArray sa = new SuffixArray("abracadabra");
		int i = 0;
		for(String str : generateStrings("abracadabra")) {
			assertEquals(str, sa.getSuffix(i));
			i++;
		}	
	}
	
	@Test
	public void testInitSA3() {
		SuffixArray sa = new SuffixArray("");
		int i = 0;
		for(String str : generateStrings("")) {
			assertEquals(str, sa.getSuffix(i));
			i++;
		}	
	}
	
	@Test
	public void testInitSA4() {
		SuffixArray sa = new SuffixArray("aaaaaaaaaaaaaaaaaaaaaa");
		int i = 0;
		for(String str : generateStrings("aaaaaaaaaaaaaaaaaaaaaa")) {
			assertEquals(str, sa.getSuffix(i));
			i++;
		}	
	}
	
	@Test
	public void testInitSA5() {
		SuffixArray sa = new SuffixArray("ababababababasfasfagagnababababababa");
		int i = 0;
		for(String str : generateStrings("ababababababasfasfagagnababababababa")) {
			assertEquals(str, sa.getSuffix(i));
			i++;
		}	
	}
	
	
	private String[] generateStrings(String str) {
		String[] s = new String[str.length()+1];
		s[0] = "";
		for(int i = str.length()-1; i >= 0; i--)
			s[i+1] = str.substring(i) ;
		Arrays.sort(s); 
		return s;
	}
}
