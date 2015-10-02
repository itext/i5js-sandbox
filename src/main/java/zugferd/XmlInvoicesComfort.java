/*
 * Example that shows how to create XML invoices.
 */
package zugferd;

import com.itextpdf.text.zugferd.InvoiceDOM;
import com.itextpdf.text.zugferd.exceptions.DataIncompleteException;
import com.itextpdf.text.zugferd.exceptions.InvalidCodeException;
import com.itextpdf.text.zugferd.profiles.BasicProfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import zugferd.data.InvoiceData;
import zugferd.pojo.Invoice;
import zugferd.pojo.PojoFactory;

/**
 * @author  Bruno Lowagie
 */
public class XmlInvoicesComfort {
    public static final String DEST = "results/zugferd/xml/comfort%05d.xml";
    
    public static void main(String[] args) throws SQLException, ParserConfigurationException, SAXException, IOException, TransformerException, DataIncompleteException, InvalidCodeException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        PojoFactory factory = PojoFactory.getInstance();
        List<Invoice> invoices = factory.getInvoices();
        InvoiceData invoiceData = new InvoiceData();
        BasicProfile comfort;
        InvoiceDOM dom;
        for (Invoice invoice : invoices) {
            comfort = invoiceData.createComfortProfileData(invoice);
            dom = new InvoiceDOM(comfort);
            byte[] xml = dom.toXML();
            FileOutputStream fos = new FileOutputStream(String.format(DEST, invoice.getId()));
            fos.write(xml);
            fos.flush();
            fos.close();
        }
        factory.close();
    }

}
