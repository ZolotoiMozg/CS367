package program3;

import java.util.Iterator;

public class test {

	public static void main(String[] args) {
		/**
		 
		// TODO Auto-generated method stub
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>();
		KeyWord aaa = new KeyWord("aaa");
		KeyWord abc = new KeyWord("abc");
		KeyWord bbb = new KeyWord("bbb");
		KeyWord ccc = new KeyWord("ccc");
		KeyWord ddd = new KeyWord("ddd");
		DictionaryADT<KeyWord> dic = new BSTDictionary<KeyWord>();
		try {
			dic.insert(new KeyWord("abc"));
			dic.insert(new KeyWord("09f"));
			dic.insert(new KeyWord("bbb"));
			dic.insert(new KeyWord("eee"));
			dic.insert(new KeyWord("ccc"));
			dic.insert(new KeyWord("ddd"));
			dic.insert(new KeyWord("ggg"));										//NOTE: when ggg is only inserted after inserting duplicates, it will not show up in iteration
			dic.insert(new KeyWord("423"));
			dic.insert(new KeyWord("aaa"));
			dic.insert(new KeyWord("aaa"));
			dic.insert(new KeyWord("bbb"));
			dic.insert(new KeyWord("ggg"));
			
			} catch (DuplicateException e){
			System.out.println("duplicate found?");
		}
		
		System.out.println(dic.totalPathLength());
		System.out.println(dic.lookup(new KeyWord("abc")));
		System.out.println(dic.lookup(new KeyWord("bbb")));
		
		dic.lookup(new KeyWord("ccc")).increment();
		System.out.println(dic.lookup(new KeyWord("ccc")).getOccurrences() + "!!!!!");
		System.out.println(ccc.getOccurrences() + "!!!!!");
		ddd.increment();
		aaa.increment();
		abc.increment();
		ccc.increment();
		ccc.increment();
		ccc.increment();
		ddd.increment();
		System.out.println(ccc.getOccurrences() + "!!!!!");
		
		ah.insert(aaa);
		ah.insert(abc);
		ah.insert(bbb);
		ah.insert(ccc);
		ah.insert(ddd);
		
		System.out.println(ah.getMax().getWord());
		System.out.println(ah.getMax().getPriority());
		
		//Iterator<KeyWord> iter = new dic.iterator();
		
		
		System.out.println("lookup new abc " + dic.lookup(new KeyWord("abc")));
		System.out.println("lookup abc " + dic.lookup(abc).equals(dic.lookup(new KeyWord("abc"))));
		System.out.println("lookup new aaa " + dic.lookup(new KeyWord("aaa")));
		System.out.println("lookup aaa " + dic.lookup(aaa).compareTo(dic.lookup(new KeyWord("aaa"))));
		System.out.println("lookup new bbb " + dic.lookup(new KeyWord("bbb")));
		System.out.println("lookup bbb " + dic.lookup(bbb).compareTo(dic.lookup(new KeyWord("bbb"))));
		System.out.println("lookup new ccc " + dic.lookup(new KeyWord("ccc")));
		System.out.println("lookup ccc " + dic.lookup(ccc).compareTo(dic.lookup(new KeyWord("ccc"))));
		System.out.println("lookup new ddd " + dic.lookup(new KeyWord("ddd")));
		System.out.println("lookup ddd " + dic.lookup(ddd).compareTo(dic.lookup(new KeyWord("ddd"))));
		
		
		
		Iterator<KeyWord> iter = dic.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().getWord() + " iteration");
		}
		
		
		System.out.println(ah.removeMax().getOccurrences());
		System.out.println(ah.removeMax().getOccurrences());
		System.out.println(ah.removeMax().getWord());
		System.out.println(ah.removeMax().getWord());
		System.out.println(ah.removeMax().getWord());
		
		
		
		System.out.println("fff".compareTo("fff"));
		System.out.println("bbb".compareTo("ccc"));
		System.out.println(bbb.compareTo(ccc));
		
		*/
	}

}
