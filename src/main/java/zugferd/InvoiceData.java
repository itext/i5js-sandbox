/*
 * Code written by Bruno Lowagie in the context of an example.
 */
package zugferd;

import java.text.SimpleDateFormat;
import zugferd.pojo.Invoice;
import zugferd.xml.BASICInvoiceData;
import zugferd.xml.BASICLevel;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class InvoiceData {
    
    protected BASICLevel invoiceData;
    
    public InvoiceData(Invoice invoice) {
        invoiceData = new BASICInvoiceData();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        invoiceData.setDate(df.format(invoice.getInvoiceDate()));
        invoiceData.addNote("This is a test invoice. Nothing on this invoice is real.");
        invoiceData.setSellerName("Das Company");
        invoiceData.setSellerLineOne("ZUG Business Center");
        invoiceData.setSellerLineTwo("Highway 1");
        invoiceData.setSellerPostcode("9000");
        invoiceData.setSellerCityName("Ghent");
        invoiceData.setSellerCountryID("BE");
        invoiceData.addSellerTaxRegistration("FC", "201/113/40209");
        invoiceData.addSellerTaxRegistration("VA", "BE123456789");
        invoiceData.setPaymentReference(String.format("%09d", invoice.getId()));
        invoiceData.setInvoiceCurrencyCode("EUR");
        invoiceData.addPaymentMeans("", "", "BE 41 7360 0661 9710", "", "", "KREDBEBB", "", "KBC");
        invoiceData.addPaymentMeans("", "", "BE 56 0015 4298 7888", "", "", "GEBABEBB", "", "BNP Paribas");
    }
}
