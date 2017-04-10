import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class NewsUpdater {
	private Document doc;
	
	public boolean connect(String url){
		try {
			doc = Jsoup.connect(url).get();
		} 
		catch (IOException e) {
			System.out.println("¡¨Ω” ß∞‹£°");
			return false;
		}  
		
		return true;
	}
	
	public String getContent(){
		String str=doc.html().replace("&nbsp;"," ");
		doc=Jsoup.parse(str);
		
		Element content = null;
		
		if(doc.getElementById("articleContent") != null){
			content=doc.getElementById("articleContent");
			
		}
		if(doc.getElementById("page_a") != null){
			content=doc.getElementById("page_a");
		}
		
		String text=content.text();
		return text;
	}
}
