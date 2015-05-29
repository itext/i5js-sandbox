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
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author iText
 */
public class BASICDOM {
    public static final String XML = "resources/zugferd/basic.xml";
    
    protected Document doc;
    
    public BASICDOM() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	doc = docBuilder.parse(XML);
    }
    
    public void importData(BASICLevel data) {
        setNodeContent(doc, "udt:Indicator", 0, data.getTestIndicator() ? "true" : "false");
        setNodeContent(doc, "ram:ID", 1, data.getId());
        setNodeContent(doc, "ram:Name", 0, data.getName());
        setNodeContent(doc, "ram:TypeCode", 0, data.getTypeCode());
        setNodeContent(doc, "udt:DateTimeString", 0, data.getDateTime(), "format", data.getDateTimeFormat());
        
        try {
            processPaymentMeans(doc, data);
        }
        catch(ArrayIndexOutOfBoundsException aioobe) {
        }
    }
    
    protected void processPaymentMeans(Document doc, BASICLevel data) {
        String[] pmID = data.getPaymentMeansID();
        String[] pmSchemeAgencyID = data.getPaymentMeansSchemeAgencyID();
        String[] pmIBAN = data.getPaymentMeansPayeeAccountIBAN();
        String[] pmAccountName = data.getPaymentMeansPayeeAccountAccountName();
        String[] pmAccountID = data.getPaymentMeansPayeeAccountProprietaryID();
        String[] pmBIC = data.getPaymentMeansPayeeFinancialInstitutionBIC();
        String[] pmGermanBankleitzahlID = data.getPaymentMeansPayeeFinancialInstitutionGermanBankleitzahlID();
        String[] pmFinancialInst = data.getPaymentMeansPayeeFinancialInstitutionName();
        for (int i = 0; i < pmID.length; i++) {
            processPaymentMeans(doc, data, i,
                    pmID[i],
                    pmSchemeAgencyID[i],
                    pmIBAN[i],
                    pmAccountName[i],
                    pmAccountID[i],
                    pmBIC[i],
                    pmGermanBankleitzahlID[i],
                    pmFinancialInst[i]
            );
        }
    }
    
    protected void processPaymentMeans(Document doc, BASICLevel data, int n,
        String... paymentMeans) {
        Node node = doc.getElementsByTagName("ram:SpecifiedTradeSettlementPaymentMeans").item(0);
        Node newNode = node.cloneNode(true);
        NodeList list = newNode.getChildNodes();
        Node childNode;
        NodeList l;
        Node grandchildNode;
        int counter = 0;
        for (int i = 0; i < list.getLength(); i++) {
            childNode = list.item(i);
            if (childNode instanceof Element) {
                switch (counter) {
                    case 0:
                        childNode.setTextContent(paymentMeans[counter++]);
                        childNode.getAttributes().item(0).setTextContent(paymentMeans[counter++]);
                        break;
                    case 2:
                        l = childNode.getChildNodes();
                        for (int j = 0; j < l.getLength(); j++) {
                            grandchildNode = l.item(j);
                            if (grandchildNode instanceof Element) {
                                switch (counter) {
                                    case 2:
                                        grandchildNode.setTextContent(paymentMeans[counter++]);
                                        break;
                                    case 3:
                                        grandchildNode.setTextContent(paymentMeans[counter++]);
                                        break;
                                    case 4:
                                        grandchildNode.setTextContent(paymentMeans[counter++]);
                                        break;
                                    default:
                                        counter++;
                                }
                            }
                        }
                        break;
                    case 5:
                        l = childNode.getChildNodes();
                        for (int j = 0; j < l.getLength(); j++) {
                            grandchildNode = l.item(j);
                            if (grandchildNode instanceof Element) {
                                switch (counter) {
                                    case 5:
                                        grandchildNode.setTextContent(paymentMeans[counter++]);
                                        break;
                                    case 6:
                                        grandchildNode.setTextContent(paymentMeans[counter++]);
                                        break;
                                    case 7:
                                        grandchildNode.setTextContent(paymentMeans[counter++]);
                                        break;
                                    default:
                                        counter++;
                                }
                            }
                        }
                        break;
                    default:
                        counter++;
                }
            }
        }
        Node parent = node.getParentNode();
        parent.insertBefore(newNode, node);
        if (n == 0) parent.removeChild(node);
    }
    
    public void setNodeContent(Document doc, String tagname, int idx, String content, String... attributes) {
        Node node = doc.getElementsByTagName(tagname).item(idx);
        if (node == null)
            return;
        node.setTextContent(content);
        int n = attributes.length;
        if (n == 0) return;
        String attrName, attrValue;
        NamedNodeMap attrs = node.getAttributes();
        Node attr;
        for (int i = 0; i < n; i++) {
            attrName = attributes[i];
            if (++i == n) continue;
            attrValue = attributes[i];
            attr = attrs.getNamedItem(attrName);
            if (attr != null)
                attr.setTextContent(attrValue);
        }
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
