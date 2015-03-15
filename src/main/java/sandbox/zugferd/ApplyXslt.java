package sandbox.zugferd;

import java.io.File;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class ApplyXslt {
    
    class MyErrorListener implements ErrorListener {

        public void warning(TransformerException exception) throws TransformerException {
            System.out.println("Warning:" + exception.getMessage());
        }

        public void error(TransformerException exception) throws TransformerException {
            System.out.println("Error:" + exception.getMessage());
        }

        public void fatalError(TransformerException exception) throws TransformerException {
            System.out.println("Fatal error:" + exception.getMessage());
        }
    }
    
    TransformerFactory factory = TransformerFactory.newInstance();
    ErrorListener errorListener = new MyErrorListener();
    
    public static void main(String[] args) throws TransformerConfigurationException, TransformerException {
        ApplyXslt app = new ApplyXslt();
        File file = new File("results/zugferd");
        file.mkdir();
        app.execute(
            "resources/zugferd/ZUGFeRD-invoice_basic_einfach.xml",
            "resources/zugferd/ZUGFeRD_1p0_c1p0_s1p0.xslt",
            "results/zugferd/ZUGFeRD-invoice_basic_einfach.txt"
        );
    }
    
    public void execute(String xml, String xslt, String txt) throws TransformerConfigurationException, TransformerException {
        Source xsltSource = new StreamSource(new File(xslt));
        Transformer transformer = factory.newTransformer(xsltSource);
        transformer.setErrorListener(errorListener);
        Source xmlSource = new StreamSource(new File(xml));
        transformer.transform(xmlSource, new StreamResult(new File(txt)));
    }
}
