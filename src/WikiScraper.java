import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * TODO: You will have to implement memoization somewhere in this class.
 */
public class WikiScraper {
	
	private static Map<String,Set<String>> cachedLinks = new HashMap<String,Set<String>>();
			
	/*
	 * This function finds a set for all the wikilinks
	 */
	public static Set<String> findWikiLinks(String link) throws IOException  {

			String html = fetchHTML(link);
			Set<String> links = scrapeHTML(html);

			return links;
		
	}
	

	
	/*
	 * This function fecthes the wikipeia pages html by an 
	 * input stream. And then returns the html as a String.
	 */
	private static String fetchHTML(String link) throws IOException {
		StringBuffer buffer = null;

			URL url = new URL(getURL(link));
			InputStream is = url.openStream();
			int ptr = 0;
			buffer = new StringBuffer();
			while ((ptr = is.read()) != -1) {
			    buffer.append((char)ptr);
			}
		
		return buffer.toString();
	}
	
	/*
	 * Gives you the URL backbone for your link
	 */
	private static String getURL(String link) {
		return "https://en.wikipedia.org/wiki/" + link;
	}
	
	/*
	 * This scrapes the html page and finds a set for all
	 * the wikipedia links that this page links too.
	 */
	private static Set<String> scrapeHTML(String html) {
		Set<String> wikiLinkSet = new HashSet<String>();
		for (String line: html.split("\n")) {
			int aTagStart = line.indexOf("<a href=\"/wiki/");
			String curr=line;
			while (aTagStart!=-1) {
				curr = curr.substring(aTagStart,curr.length());
				String link = getLink(curr);
				if (link!=null) {
					wikiLinkSet.add(link);
				}
				curr =curr.substring(1,curr.length());
				aTagStart = curr.indexOf("<a href=\"/wiki/");
			}

		}
		return wikiLinkSet;
	}
	public static String getLink(String line) {
		String[] aTag = line.split(" ");
		String href = aTag[1];
		String[] hrefArr = href.split("\"");
		String[] linkArr = hrefArr[1].split("/");
		String link=linkArr[2];
		int noIndex = link.indexOf(":");
		int noIndex2 = link.indexOf("#");
		if (noIndex!=-1||noIndex2!=-1) {
			return null;
		}
		return link;
	}
}
