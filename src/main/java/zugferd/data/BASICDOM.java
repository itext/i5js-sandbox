/*
 * Code written by Bruno Lowagie in the context of an example.
 */
package zugferd.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
        
        String[] notes = data.getNotes();
        if (notes.length > 0) {
            Node node = doc.getElementsByTagName("ram:IncludedNote").item(0);
            for (String note : notes) {
                Node newNode = node.cloneNode(true);
                NodeList list = newNode.getChildNodes();
                for (int j = 0; j < list.getLength(); j++) {
                    Node childNode = list.item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        childNode.setTextContent(note);
                    }
                }
                Node parent = node.getParentNode();
                parent.insertBefore(newNode, node);
            }
        }
        try {
            processPaymentMeans(doc, data);
        }
        catch(ArrayIndexOutOfBoundsException aioobe) {
        }
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
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (counter == 0) {
                    childNode.setTextContent(paymentMeans[counter++]);
                    childNode.getAttributes().item(0).setTextContent(paymentMeans[counter++]);
                }
                else {
                    l = childNode.getChildNodes();
                    for (int j = 0; j < l.getLength(); j++) {
                        grandchildNode = l.item(j);
                        if (grandchildNode.getNodeType() == Node.ELEMENT_NODE) {
                            grandchildNode.setTextContent(paymentMeans[counter++]);
                        }
                        else {
                            grandchildNode.setTextContent("");
                        }
                    }
                }
            }
        }
        Node parent = node.getParentNode();
        parent.insertBefore(newNode, node);
    }
    
    public byte[] exportDoc() throws TransformerException {
        removeNodes(doc);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	DOMSource source = new DOMSource(doc);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Result result = new StreamResult(out);
        transformer.transform(source, result);
        return out.toByteArray();
    }
    
    public static void removeNodes(Node node) {
        NodeList list = node.getChildNodes();
        for (int i = list.getLength() - 1; i >= 0; i--) {
            removeNodes(list.item(i));
        }
        boolean emptyElement = node.getNodeType() == Node.ELEMENT_NODE
            && node.getChildNodes().getLength() == 0;
        boolean emptyText = node.getNodeType() == Node.TEXT_NODE
            && node.getNodeValue().trim().isEmpty();
        if (emptyElement || emptyText) {
            node.getParentNode().removeChild(node);
        }
    }
}
