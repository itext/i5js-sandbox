/*
 * ZUGFeRD example written by Bruno Lowagie
 */
package zugferd;

import java.io.File;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class TransformInvoice {
    public static final String XSL = "resources/zugferd/invoice.xsl";
    public static final String XML = "resources/xml/comfort00002.xml";
    
    public static void main(String[] args) throws TransformerException {
        StreamSource xml = new StreamSource(new File(XML));
        StreamSource xsl = new StreamSource(new File(XSL));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsl);
        StringWriter writer = new StringWriter();
        transformer.transform(xml, new StreamResult(writer));
        System.out.println(writer.toString());
    }
}
