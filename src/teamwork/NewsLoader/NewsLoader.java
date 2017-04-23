import java.io.File;   
import java.io.IOException;   
import javax.xml.parsers.DocumentBuilder;   
import javax.xml.parsers.DocumentBuilderFactory;   
import javax.xml.parsers.ParserConfigurationException;   
import org.w3c.dom.Document;   
import org.w3c.dom.Element;   
import org.w3c.dom.Node;   
import org.w3c.dom.NodeList;   
import org.xml.sax.SAXException;

public class NewsLoader{
	private NewsLoader newsLoader = new NewsLoader();
	private Document document;
	private Element rootElement;
	private NodeList nodes;

	private boolean loadFrom(String filename){
		if(document = newsLoader.parse(filename))return true;
		else return false;
	}

	private boolean hasNext(){
		// rootElement = document.getDocumentElement();
		// nodes = rootElement.getChildNodes();
		// if(nodes.getLength==0)return false;
		// else return true;
	}

	private News next(){
		String nodeTitle = "";
		String nodeDate = "";
		String nodeLocation = "";
		String nodeId = "";
		String nodeType = "";
		String nodeUrl = "";
		//String nodeContent = "";
		nodes = rootElement.getChildNodes;
		for(int i=0;i<nodes.getLength();i++){
			Node node1 = nodes.item(i);
			NodeList childNodes = node1.getChildNodes();
			for(int j=0;j<childNodes.getLength();j++){
				Node node2 = childNodes.item(j);
				if(node2.getNodeName().equals("Title")) nodeTitle = node2.getTextContent();
				if(node2.getNodeName().equals("Date")) nodeDate = node2.getTextContent();
				if(node2.getNodeName().equals("Location")) nodeLocation = node2.getTextContent();
				if(node2.getNodeName().equals("ID")) nodeId = node2.getTextContent();
				if(node2.getNodeName().equals("Type")) nodeType = node2.getTextContent();
				if(node2.getNodeName().equals("Url")) nodeUrl = node2.getTextContent();
				//if(node2.getNodeName().equals("Content")) nodeContent = node2.getTextContent();
			} 
		}
		News news = new News(nodeId,nodeTitle,null,nodeDate,nodeLocation,nodeType,nodeUrl);
		return news;
	}
}