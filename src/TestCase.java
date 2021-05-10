import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestCase {

	@Test
	void empty_test() {
		MaxPQ q = new MaxPQ();
		assertEquals(q.isEmpty(),true);
		List<String> lst = new ArrayList<String>();
		lst.add("A");
		q.enqueue(lst, 10);
		assertEquals(q.isEmpty(),false);
		q.denqueue();
		assertEquals(q.isEmpty(),true);
	}
	@Test
	void enqueue_test_1() {
		MaxPQ q = new MaxPQ();
		List<String> lst = new ArrayList<String>();
		lst.add("A");
		List<String> lst1 = new ArrayList<String>();
		lst1.add("B");
		List<String> lst2 = new ArrayList<String>();
		lst2.add("C");
		q.enqueue(lst, 10);
		assertEquals(q.toString(),"{A (10) ,}");
		q.enqueue(lst1, 20);
		assertEquals(q.toString(),"{B (20) ,A (10) ,}");
		q.enqueue(lst2, 30);
		assertEquals(q.toString(),"{C (30) ,A (10) ,B (20) ,}");
	}
	
	@Test
	void enqueue_test_2() {
		MaxPQ q = new MaxPQ();
		List<String> lst = new ArrayList<String>();
		lst.add("A");
		List<String> lst1 = new ArrayList<String>();
		lst1.add("B");
		List<String> lst2 = new ArrayList<String>();
		lst2.add("C");
		List<String> lst3 = new ArrayList<String>();
		lst3.add("D");
		List<String> lst4 = new ArrayList<String>();
		lst4.add("E");
		
		q.enqueue(lst, 20);
//		System.out.println(q.toString());

		q.enqueue(lst1, 30);
//		System.out.println(q.toString());

		q.enqueue(lst2, 19);
//		System.out.println(q.toString());

		q.enqueue(lst3, 100);
//		System.out.println(q.toString());

		q.enqueue(lst4, 56);

//		System.out.println(q.toString());
		assertEquals(q.toString(),"{D (100) ,E (56) ,C (19) ,A (20) ,B (30) ,}");

		
	}
	
	@Test
	void enqueue_test_3() {
		MaxPQ q = new MaxPQ();
		List<String> lst = new ArrayList<String>();
		lst.add("A");
		List<String> lst1 = new ArrayList<String>();
		lst1.add("B");
		List<String> lst2 = new ArrayList<String>();
		lst2.add("C");
		List<String> lst3 = new ArrayList<String>();
		lst3.add("D");
		List<String> lst4 = new ArrayList<String>();
		lst4.add("E");
		List<String> lst5 = new ArrayList<String>();
		lst5.add("F");
		List<String> lst6 = new ArrayList<String>();
		lst6.add("G");
		List<String> lst7 = new ArrayList<String>();
		lst7.add("H");

		q.enqueue(lst, 20);
//		System.out.println(q.toString());

		q.enqueue(lst1, 30);
//		System.out.println(q.toString());

		q.enqueue(lst2, 19);
//		System.out.println(q.toString());

		q.enqueue(lst3, 100);
//		System.out.println(q.toString());

		q.enqueue(lst4, 56);
//		System.out.println(q.toString());
		
		q.enqueue(lst5, 700);
//		System.out.println(q.toString());
		q.enqueue(lst6, 19);
//		System.out.println(q.toString());
		q.enqueue(lst7, 50);
//		System.out.println(q.toString());
		assertEquals(q.toString(),"{F (700) ,E (56) ,D (100) ,H (50) ,B (30) ,C (19) ,G (19) ,A (20) ,}");

	}

	@Test
	void enqueue_test_4() {
		MaxPQ q = new MaxPQ();
		List<String> lst;
		for (int i=0;5>i;i++) {
			lst = new ArrayList<String>();
			lst.add(Integer.toString(i));
			q.enqueue(lst, i);
			if (i==0) {
				assertEquals(q.toString(),"{0 (0) ,}");
			} 
			if (i==2) {
				assertEquals(q.toString(),"{2 (2) ,0 (0) ,1 (1) ,}");
			} 
			if (i==4) {
				assertEquals(q.toString(),"{4 (4) ,3 (3) ,1 (1) ,0 (0) ,2 (2) ,}");
				
			}
		}
 	}

	@Test
	void denqueue_test_1() {
		MaxPQ q = new MaxPQ();
		List<String> lst = new ArrayList<String>();
		lst.add("A");
		List<String> lst1 = new ArrayList<String>();
		lst1.add("B");
		List<String> lst2 = new ArrayList<String>();
		lst2.add("C");
		q.enqueue(lst, 10);
		assertEquals(q.toString(),"{A (10) ,}");
		q.enqueue(lst1, 20);
		assertEquals(q.toString(),"{B (20) ,A (10) ,}");
		q.enqueue(lst2, 30);
		assertEquals(q.toString(),"{C (30) ,A (10) ,B (20) ,}");
		
		assertEquals("C",q.denqueue().ladder.get(0));
		assertEquals("B",q.denqueue().ladder.get(0));
		assertEquals("A",q.denqueue().ladder.get(0));

		
		
	}
		
	@Test
	void denqueue_test_2() {
		MaxPQ q = new MaxPQ();
		List<String> lst = new ArrayList<String>();
		lst.add("A");
		List<String> lst1 = new ArrayList<String>();
		lst1.add("B");
		List<String> lst2 = new ArrayList<String>();
		lst2.add("C");
		List<String> lst3 = new ArrayList<String>();
		lst3.add("D");
		List<String> lst4 = new ArrayList<String>();
		lst4.add("E");
		
		q.enqueue(lst, 20);
//		System.out.println(q.toString());

		q.enqueue(lst1, 30);
//		System.out.println(q.toString());

		q.enqueue(lst2, 19);
//		System.out.println(q.toString());

		q.enqueue(lst3, 100);
//		System.out.println(q.toString());

		q.enqueue(lst4, 56);

//		System.out.println(q.toString());
		assertEquals(q.toString(),"{D (100) ,E (56) ,C (19) ,A (20) ,B (30) ,}");
		assertEquals("D",q.denqueue().ladder.get(0));
		assertEquals(q.toString(),"{E (56) ,B (30) ,C (19) ,A (20) ,}");
		assertEquals("E",q.denqueue().ladder.get(0));
		assertEquals("B",q.denqueue().ladder.get(0));




	}
	
	@Test
	void denqueue_test_3() {
		MaxPQ q = new MaxPQ();
		List<String> lst;
		int size =100;
		for (int i=0;size>i;i++) {
			lst = new ArrayList<String>();
			lst.add(Integer.toString(i));
			q.enqueue(lst, i);

		}
	
		for (int i=0;size>i;i++) {
			
			assertEquals(size-1-i, q.denqueue().priority);
		}
	}


}
