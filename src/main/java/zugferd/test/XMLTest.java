/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zugferd.test;

import com.itextpdf.text.zugferd.InvoiceDOM;
import com.itextpdf.text.zugferd.exceptions.DataIncompleteException;
import com.itextpdf.text.zugferd.exceptions.InvalidCodeException;
import com.itextpdf.text.zugferd.profiles.BasicProfile;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import zugferd.InvoiceData;
import zugferd.pojo.Invoice;
import zugferd.pojo.PojoFactory;

/**
 *
 * @author iText
 */
public class XMLTest {
    public static void main(String[] args) throws SQLException, ParserConfigurationException, SAXException, IOException, TransformerException, DataIncompleteException, InvalidCodeException {
        PojoFactory factory = PojoFactory.getInstance();
        List<Invoice> invoices = factory.getInvoices();
        InvoiceData invoiceData = new InvoiceData();
        BasicProfile basic;
        InvoiceDOM dom;
        for (Invoice invoice : invoices) {
            basic = invoiceData.importInvoice(invoice);
            dom = new InvoiceDOM(basic);
            byte[] xml = dom.toXML();
            System.out.println(new String(xml));
        }
        factory.close();
    }

}
