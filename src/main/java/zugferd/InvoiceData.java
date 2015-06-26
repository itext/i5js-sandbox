/*
 * Code written by Bruno Lowagie in the context of an example.
 */
package zugferd;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;
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
        Map<Double,Double> taxes = new TreeMap<Double, Double>();
        double tax;
        for (Item item : invoice.getItems()) {
            tax = item.getProduct().getVat();
            if (taxes.containsKey(tax)) {
                taxes.put(tax, taxes.get(tax) + item.getCost());
            }
            else {
                taxes.put(tax, item.getCost());
            }
            invoiceData.addIncludedSupplyChainTradeLineItem(String.valueOf(item.getQuantity()), "C62", item.getProduct().getName());
        }
        double total, tA;
        double ltN = 0;
        double ttA = 0;
        double gtA = 0;
        for (Map.Entry<Double, Double> t : taxes.entrySet()) {
            tax = t.getKey();
            total = round(t.getValue());
            gtA += total;
            tA = round((100 * total) / (100 + tax));
            ttA += (total - tA);
            ltN += tA;
            invoiceData.addApplicableTradeTax(format(total - tA), "EUR", "VAT", format(tA), "EUR", format(tax));
        }
        invoiceData.setLineTotalAmount(format(ltN));
        invoiceData.setLineTotalAmountCurrencyID("EUR");
        invoiceData.setChargeTotalAmount(format(0));
        invoiceData.setChargeTotalAmountCurrencyID("EUR");
        invoiceData.setAllowanceTotalAmount(format(0));
        invoiceData.setAllowanceTotalAmountCurrencyID("EUR");
        invoiceData.setTaxBasisTotalAmount(format(ltN));
        invoiceData.setTaxBasisTotalAmountCurrencyID("EUR");
        invoiceData.setTaxTotalAmount(format(ttA));
        invoiceData.setTaxTotalAmountCurrencyID("EUR");
        invoiceData.setGrandTotalAmount(format(gtA));
        invoiceData.setGrandTotalAmountCurrencyID("EUR");
        return invoiceData;
    }
    
    public static double round(double d) {
        d = d * 100;
        long tmp = Math.round(d);
        return (double) tmp / 100;
    }
    
    public static String format(double d) {
        return String.format("%.2f", d);
    }
    
}
