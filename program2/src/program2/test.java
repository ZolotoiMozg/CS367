package program2;

public class test {
	public static void main(String[] args){
		LinkedLoop<String> lloop = new LinkedLoop<String>();
		//DblListnode<String> node1 = new DblListnode("first");
		//DblListnode<String> node2 = new DblListnode("second");
		//DblListnode<String> node0 = new DblListnode("zeroeth");
		
		
		
		//System.out.println(node1.getData());
		lloop.insert("first");
		lloop.insert("middle1");
		lloop.insert("middle2");
		lloop.insert("middle3");
		lloop.insert("middle4");
		lloop.insert("middle5");
		lloop.insert("last");
		System.out.println("current: " + lloop.getCurrent());
		System.out.println("size: " + lloop.size());
		
		
		System.out.println("removing: " + lloop.removeCurrent());
		System.out.println("size: " + lloop.size());
		
		System.out.println("removing: " + lloop.removeCurrent());
		System.out.println("size: " + lloop.size());
		
		System.out.println("removing: " + lloop.removeCurrent());
		System.out.println("size: " + lloop.size());
		
		System.out.println("removing: " + lloop.removeCurrent());
		System.out.println("size: " + lloop.size());
		
		System.out.println("removing: " + lloop.removeCurrent());
		System.out.println("size: " + lloop.size());
		
		System.out.println("removing: " + lloop.removeCurrent());
		System.out.println("size: " + lloop.size());
		
		System.out.println("removing: " + lloop.removeCurrent());
		System.out.println("size: " + lloop.size());
		
		try{
			System.out.println("removing test: " + lloop.removeCurrent());
			System.out.println("size: " + lloop.size());
		} catch (EmptyLoopException exc){
			System.out.println("Can't remove; already empty");
		}
		
		/**
		lloop.forward();
		System.out.println("current after forward: " + lloop.getCurrent());
		System.out.println("size: " + lloop.size());
		System.out.println("is the list empty? " + lloop.isEmpty());
		*/
	}
	
	
	
}
