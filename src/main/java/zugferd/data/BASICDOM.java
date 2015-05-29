/*
 * Code written by Bruno Lowagie in the context of an example.
 */
package zugferd.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * @author iText
 */
public class BASICDOM {
    public static final String XML = "resources/zugferd/basic.xml";
    
    public static final String XSI ="http://www.w3.org/2001/XMLSchema-instance";
    public static final String RSM = "urn:ferd:CrossIndustryDocument:invoice:1p0";
    public static final String RAM = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:12";
    public static final String UDT = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:15";
    
    protected Document doc;
    
    public BASICDOM() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	doc = docBuilder.parse(XML);
    }
    
    public void importData(BASICLevel data) {
        Node indicator = doc.getElementsByTagName("udt:Indicator").item(0);
        indicator.setTextContent(data.getTestIndicator() ? "true" : "false");
    }
    
    public byte[] exportDoc() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Result result = new StreamResult(out);
        transformer.transform(source, result);
        return out.toByteArray();
    }
}
