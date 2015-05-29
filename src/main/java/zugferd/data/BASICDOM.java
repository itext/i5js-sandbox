/*
 * Code written by Bruno Lowagie in the context of an example.
 */
package zugferd.data;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * @author iText
 */
public class BASICDOM {
    public static final String XML = "resources/zugferd/basic.xml";
    
    public BASICDOM(BASICLevel data) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	Document doc = docBuilder.parse(XML);
        
        Node cii = doc.getFirstChild();
        Node specifiedExchangedDocumentContext = cii.getFirstChild();
        Node testIndicator = specifiedExchangedDocumentContext.getFirstChild();
        Node indicator = testIndicator.getFirstChild();
        indicator.setTextContent(data.getTestIndicator() ? "true" : "false");
    }
}
