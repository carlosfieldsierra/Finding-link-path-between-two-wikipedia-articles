import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WikiRacer {
	
	
	

	/*
	 * Do not edit this main function
	 */
	public static void main(String[] args) throws IOException {
//		List<String> ladder = findWikiLadder(args[0], args[1]);
		List<String> ladder = findWikiLadder("Fruit","Strawberry");

		System.out.println(ladder);
	
	}

	


	/*
	 * Do not edit the method signature/header of this function
	 * TODO: Fill this function in.
	 */
	private static List<String> findWikiLadder(String start, String end) throws IOException {
		 // Links that already have been visted 
		 Set<String> vistedLinks = new HashSet<String>();
		 // The links to the endPage
		 Set<String> endPageLinks = WikiScraper.findWikiLinks(end);
		 // Memoization for links that have already been Visted 
		 Map<String,Set<String>> cachedLinkSets = new HashMap<String,Set<String>>();
		 // Max priority queue
		 MaxPQ q = new MaxPQ();

		 List<String> startLst =  new ArrayList<String>();
		 startLst.add(start);
		 q.enqueue(startLst, 0);
		
		
		

		 // While loop
		 while (!q.isEmpty()) {
			 List<String> ladder =  q.denqueue().ladder;

			 String lastLink = ladder.get(ladder.size()-1);
			 Set<String> startPageLinks;
			 if (cachedLinkSets.containsKey(lastLink)) {
				 startPageLinks= cachedLinkSets.get(lastLink);
			 } else {
				 startPageLinks = WikiScraper.findWikiLinks(lastLink);
				 cachedLinkSets.put(lastLink, startPageLinks);
			 }
			 if (startPageLinks.contains(end)) {
				 ladder.add(end);
				 return ladder;
			 }
			 addToQueue(ladder,q,startPageLinks,endPageLinks,vistedLinks);	 
		 
		 }
		return new ArrayList<String>();
	}
	/*
	 * This function goes through all the links in the start page links set and finds how many intersections that has with
	 * the end page link set, and then adds the links to its proper ladder, and then its priority based on numbder of links
	 * share with end page.
	 * 
	 * @param: List<String> ladder: the ladder in which this link comes from,
	 * 		   MaxPQ q: the max priority queue to hold all ladders
	 * 		   Set<String> startPageLinks: the set of start page links for the link your starting from at this call
	 * 		   Set<String> vistedLinks: the set of links already visited
	 * 			
	 */
	private static void addToQueue(List<String> ladder, MaxPQ q, Set<String> startPageLinks, Set<String> endPageLinks,Set<String> vistedLinks) {
		 
		 startPageLinks.parallelStream().forEach(link -> {
			 if (!vistedLinks.contains(link)) {
			
				 Set<String> linkSet;
				try {
					linkSet = WikiScraper.findWikiLinks(link);
					 linkSet.retainAll(endPageLinks); // Intersection
					 int priority = linkSet.size();
					 ArrayList<String> lst = new ArrayList<String>(ladder);
					 lst.add(link);
					 q.enqueue(lst, priority);	
					 vistedLinks.add(link);
				} catch (IOException e) {
					
				}
		
			 }
		 });
		
	}
	



}
