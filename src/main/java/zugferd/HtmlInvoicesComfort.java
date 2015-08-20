/*
 * This example was written by Bruno Lowagie.
 */
package zugferd;

import com.itextpdf.text.zugferd.InvoiceDOM;
import com.itextpdf.text.zugferd.exceptions.DataIncompleteException;
import com.itextpdf.text.zugferd.exceptions.InvalidCodeException;
import com.itextpdf.text.zugferd.profiles.ComfortProfile;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.SAXException;
import zugferd.data.InvoiceData;
import zugferd.pojo.Invoice;
import zugferd.pojo.PojoFactory;

/**
 * @author Bruno Lowagie
 */
public class HtmlInvoicesComfort {
    public static final String DEST = "results/zugferd/html/comfort%05d.html";
    public static final String XSL = "resources/zugferd/invoice.xsl";
    
    public static void main(String[] args) throws SQLException, IOException, ParserConfigurationException, SAXException, DataIncompleteException, InvalidCodeException, TransformerException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        HtmlInvoicesComfort app = new HtmlInvoicesComfort();
        PojoFactory factory = PojoFactory.getInstance();
        List<Invoice> invoices = factory.getInvoices();
        for (Invoice invoice : invoices) {
            app.createHtml(invoice, new FileWriter(String.format(DEST, invoice.getId())));
        }
        factory.close();
    }
    
    public void createHtml(Invoice invoice, Writer writer) throws IOException, ParserConfigurationException, SAXException, DataIncompleteException, InvalidCodeException, TransformerException {
        ComfortProfile comfort = new InvoiceData().createComfortProfileData(invoice);
        InvoiceDOM dom = new InvoiceDOM(comfort);
        StreamSource xml = new StreamSource(new ByteArrayInputStream(dom.toXML()));
        StreamSource xsl = new StreamSource(new File(XSL));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsl);
        transformer.transform(xml, new StreamResult(writer));
        writer.flush();
        writer.close();
    }
}
