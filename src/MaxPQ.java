import java.util.List;

/*
 * TODO: This file should contain your priority queue backed by a binary
 * max-heap.
 */
public class MaxPQ {
	private Node[] theHeap;
    private int capacity;
    private int pos;
    public MaxPQ() {
        pos = 1;
        capacity = 1000000;
        theHeap = new Node[capacity];
    }
    /*
     * Grows the array if the size is to small for all the patients
     */
    public void growArray() {
    	Node[] oldArr = theHeap;
    	theHeap = new Node[capacity * 2];
        for (int i=1;pos>i;i++) {
        	theHeap[i] = oldArr[i];
        }
        capacity = capacity * 2;
    }
    
    public boolean isEmpty() {
    	return pos<=1;
    }
    
    
	
    public void enqueue(List<String> lst,int pri) {
        if (pos == capacity) {
        	growArray();
        } else {
            theHeap[pos++] = new Node(lst,pri);
            if (pos==2) {
            	return;
            }

            int child = pos - 1;
            int parent = child / 2;
            
            while (theHeap[parent].priority < theHeap[child].priority ) {
                Node tmp = theHeap[parent];
                theHeap[parent] = theHeap[child];
                theHeap[child] = tmp;
                child = parent;
                parent = child / 2;
                if (parent<1) {
                	return;
                	
                }
            }
        }
    }
    
    
    
    @Override 
    public String toString() {
    	String res = "{";
		for (int i=1;pos>i;i++) {
			if (theHeap[i].ladder.get(0)==null) {
				return "NOTHING AT FIRST INDEX HEADASSS for index: "+i;
			}
			res+=theHeap[i].ladder.get(theHeap[i].ladder.size()-1)+" ("+theHeap[i].priority+") ,";
		}
		res+="}";
		return res;
    }

   
    public Node peek() {
    	return theHeap[1];
    }
    
    public Node denqueue() {
        if (pos == capacity) {
        	growArray();
        }
        

        Node returnVal = theHeap[1];
        theHeap[1] = theHeap[pos - 1];
        theHeap[pos - 1] = null;

        int curIndex = 1;
        int child1 = (curIndex * 2);
        int child2 = (curIndex * 2) + 1;
        boolean isTrue = true;
        pos--;
        while (isTrue) {
        	
            if (child1 > pos - 1) {
                isTrue = false;
            }
            else if (child2 > pos - 1) {
                if (theHeap[child1].priority > theHeap[curIndex].priority) {
                    Node tmp = theHeap[curIndex];
                    theHeap[curIndex] = theHeap[child1];
                    theHeap[child1] = tmp;
                }
                isTrue = false;
            }

            // What child is bigger
            else if (theHeap[child1].priority < theHeap[curIndex].priority
                    && theHeap[child2].priority < theHeap[curIndex].priority) {
                isTrue = false;
            } else if (theHeap[child1].priority > theHeap[child2].priority) {
                Node tmp = theHeap[curIndex];

                theHeap[curIndex] = theHeap[child1];
                theHeap[child1] = tmp;
                curIndex = child1;
                child1 = (curIndex * 2);
                child2 = (curIndex * 2) + 1;


            } else if (theHeap[child1].priority < theHeap[child2].priority) {
                Node tmp = theHeap[curIndex];
                theHeap[curIndex] = theHeap[child2];
                theHeap[child2] = tmp;
                curIndex = child2;
                child1 = (curIndex * 2);

                child2 = (curIndex * 2) + 1;


            } else {

                isTrue = false;
            }

        }

        return returnVal;
    }

	
	
	
	
	
	public class Node{
		List<String> ladder;
		int priority;
		public Node(List<String> ladder, int priority) {
			this.ladder=ladder;
			this.priority=priority;
		}
	}

}
