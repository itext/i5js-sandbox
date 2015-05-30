/*
 * Code written by Bruno Lowagie in the context of an example.
 */
package zugferd;

import java.text.SimpleDateFormat;
import zugferd.pojo.Customer;
import zugferd.pojo.Invoice;
import zugferd.pojo.Item;
import zugferd.xml.BASICInvoice;
import zugferd.xml.BASICInvoiceData;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class InvoiceData {
    
    public InvoiceData() {
    }
    
    public BASICInvoice importInvoice(Invoice invoice) {
        BASICInvoiceData invoiceData = new BASICInvoiceData();
        invoiceData.setTest(true);
        invoiceData.setId(String.format("I/%05d", invoice.getId()));
        invoiceData.setName("INVOICE");
        invoiceData.setTypeCode("380");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        invoiceData.setDate(df.format(invoice.getInvoiceDate()));
        invoiceData.addNote("This is a test invoice.\nNothing on this invoice is real.\nThis invoice is part of a tutorial.");
        invoiceData.setSellerName("Das Company");
        invoiceData.setSellerLineOne("ZUG Business Center");
        invoiceData.setSellerLineTwo("Highway 1");
        invoiceData.setSellerPostcode("9000");
        invoiceData.setSellerCityName("Ghent");
        invoiceData.setSellerCountryID("BE");
        invoiceData.addSellerTaxRegistration("FC", "201/113/40209");
        invoiceData.addSellerTaxRegistration("VA", "BE123456789");
        Customer customer = invoice.getCustomer();
        invoiceData.setBuyerName(String.format("%s, %s", customer.getLastName(), customer.getFirstName()));
        invoiceData.setBuyerPostcode(customer.getPostalcode());
        invoiceData.setBuyerLineOne(customer.getStreet());
        invoiceData.setBuyerCityName(customer.getCity());
        invoiceData.setBuyerCountryID(customer.getCountryId());
        invoiceData.setPaymentReference(String.format("%09d", invoice.getId()));
        invoiceData.setInvoiceCurrencyCode("EUR");
        invoiceData.addPaymentMeans("", "", "BE 41 7360 0661 9710", "", "", "KREDBEBB", "", "KBC");
        invoiceData.addPaymentMeans("", "", "BE 56 0015 4298 7888", "", "", "GEBABEBB", "", "BNP Paribas");
        double taxpercentage = 21;
        double total = round(invoice.getTotal());
        double tax = round((100 * total) / (100 + taxpercentage));
        double net = total - tax;
        invoiceData.addApplicableTradeTax(format(tax), "EUR", "VAT", format(net), "EUR", format(taxpercentage));
        invoiceData.setLineTotalAmount(format(net));
        invoiceData.setLineTotalAmountCurrencyID("EUR");
        invoiceData.setChargeTotalAmount(format(0));
        invoiceData.setChargeTotalAmountCurrencyID("EUR");
        invoiceData.setAllowanceTotalAmount(format(0));
        invoiceData.setAllowanceTotalAmountCurrencyID("EUR");
        invoiceData.setTaxBasisTotalAmount(format(net));
        invoiceData.setTaxBasisTotalAmountCurrencyID("EUR");
        invoiceData.setTaxTotalAmount(format(tax));
        invoiceData.setTaxTotalAmountCurrencyID("EUR");
        invoiceData.setGrandTotalAmount(format(total));
        invoiceData.setGrandTotalAmountCurrencyID("EUR");
        for (Item item : invoice.getItems()) {
            invoiceData.addIncludedSupplyChainTradeLineItem(String.valueOf(item.getQuantity()), "C62", item.getProduct().getName());
        }
        return invoiceData;
    }
    
    public static double round(double d) {
        d = d * 100;
        long tmp = Math.round(d);
        return (double) tmp / 100;
    }
    
    public String format(double d) {
        return String.format("%.2f", d);
    }
    
}
