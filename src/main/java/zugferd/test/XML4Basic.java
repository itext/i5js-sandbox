/*
 * This class creates an XML that has the structure needed to conform with
 * the BASIC level, but the content isn't valid. Instead we used test values
 * that make it easy for us to detect errors.
 */
package zugferd.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import zugferd.codes.DateFormatCode;
import zugferd.codes.DocumentTypeCode;
import zugferd.codes.TaxIDTypeCode;
import zugferd.xml.InvoiceDOM;
import zugferd.xml.BASICInvoiceData;
import zugferd.exceptions.DataIncompleteException;
import zugferd.exceptions.InvalidCodeException;

/**
 * @author iText
 */
public class XML4Basic {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, DataIncompleteException, TransformerException, InvalidCodeException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        BASICInvoiceData data = new BASICInvoiceData();
        // SpecifiedExchangedDocumentContext
        data.setTest(true);
        
        // HeaderExchangedDocument
        data.setId("HeaderExchangedDocument.ID");
        data.setName("HeaderExchangedDocument.Name");
        data.setTypeCode(DocumentTypeCode.COMMERCIAL_INVOICE);
        data.setDate(sdf.parse("2015/04/01"), DateFormatCode.YYYYMMDD);
        data.addNote("HeaderExchangedDocument.Note[0]");
        data.addNote("HeaderExchangedDocument.Note[1]");
        data.addNote("HeaderExchangedDocument.Note[2]");
        
        // SpecifiedSupplyChainTradeTransaction>
        // Seller
        data.setSellerName("SellerTradeParty.Name");
        data.setSellerPostcode("SellerTradeParty.PostalTradeAddress.Postcode");
        data.setSellerLineOne("SellerTradeParty.PostalTradeAddress.LineOne");
        data.setSellerLineTwo("SellerTradeParty.PostalTradeAddress.LineTwo");
        data.setSellerCityName("SellerTradeParty.PostalTradeAddress.CityName");
        data.setSellerCountryID("BE");
        data.addSellerTaxRegistration(TaxIDTypeCode.VAT, "SellerTradeParty.SpecifiedTaxRegistration.SchemeID[0]");
        data.addSellerTaxRegistration(TaxIDTypeCode.FISCAL_NUMBER, "SellerTradeParty.SpecifiedTaxRegistration.SchemeID[1]");
        // Buyer
        data.setBuyerName("BuyerTradeParty.Name");
        data.setBuyerPostcode("BuyerTradeParty.PostalTradeAddress.Postcode");
        data.setBuyerLineOne("BuyerTradeParty.PostalTradeAddress.LineOne");
        data.setBuyerLineTwo("BuyerTradeParty.PostalTradeAddress.LineTwo");
        data.setBuyerCityName("BuyerTradeParty.PostalTradeAddress.CityName");
        data.setBuyerCountryID("FR");
        data.addBuyerTaxRegistration(TaxIDTypeCode.VAT, "BuyerTradeParty.SpecifiedTaxRegistration.SchemeID[0]");
        data.addBuyerTaxRegistration(TaxIDTypeCode.FISCAL_NUMBER, "BuyerTradeParty.SpecifiedTaxRegistration.SchemeID[1]");
        
        // ApplicableSupplyChainTradeDelivery
        data.setDeliveryDate(sdf.parse("2015/04/01"), DateFormatCode.YYYYMMDD);
        
        // ApplicableSupplyChainTradeSettlement
        data.setPaymentReference("ApplicableSupplyChainTradeSettlement.PaymentReference");
        data.setInvoiceCurrencyCode("EUR");
        data.addPaymentMeans("SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[0]", "SpecifiedTradeSettlementPaymentMeans.ID[0]",
                "SpecifiedTradeSettlementPaymentMeans.IBANID[0]", "SpecifiedTradeSettlementPaymentMeans.AccountName[0]", "SpecifiedTradeSettlementPaymentMeans.ProprietaryID[0]",
                "SpecifiedTradeSettlementPaymentMeans.BICID[0]", "SpecifiedTradeSettlementPaymentMeans.GermanBankleitzahlID[0]", "SpecifiedTradeSettlementPaymentMeans.Name[0]");
        data.addPaymentMeans("SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[1]", "SpecifiedTradeSettlementPaymentMeans.ID[1]",
                "SpecifiedTradeSettlementPaymentMeans.IBANID[1]", "SpecifiedTradeSettlementPaymentMeans.AccountName[1]", "SpecifiedTradeSettlementPaymentMeans.ProprietaryID[1]",
                "SpecifiedTradeSettlementPaymentMeans.BICID[1]", "SpecifiedTradeSettlementPaymentMeans.GermanBankleitzahlID[1]", "SpecifiedTradeSettlementPaymentMeans.Name[1]");
        
        // ram:ApplicableTradeTax
        data.addApplicableTradeTax("6.00", "EUR", "VAT", "100.00", "EUR", "6.00");
        data.addApplicableTradeTax("21.00", "EUR", "VAT", "100.00", "EUR", "21.00");
        
        // SpecifiedTradeSettlementMonetarySummation
        data.setMonetarySummation("1000.00", "EUR",
                "0.00", "EUR",
                "0.00", "EUR",
                "1000.00", "EUR",
                "210.00", "EUR",
                "1210.00", "EUR");
        
        data.addIncludedSupplyChainTradeLineItem("IncludedSupplyChainTradeLineItem.SpecifiedSupplyChainTradeDelivery.BilledQuantity[0]",
                "IncludedSupplyChainTradeLineItem.SpecifiedSupplyChainTradeDelivery.BilledQuantity.unitCode[0]",
                "IncludedSupplyChainTradeLineItem.SpecifiedTradeProduct.Name[0]");
        data.addIncludedSupplyChainTradeLineItem("IncludedSupplyChainTradeLineItem.SpecifiedSupplyChainTradeDelivery.BilledQuantity[1]",
                "IncludedSupplyChainTradeLineItem.SpecifiedSupplyChainTradeDelivery.BilledQuantity.unitCode[1]",
                "IncludedSupplyChainTradeLineItem.SpecifiedTradeProduct.Name[1]");
        data.addIncludedSupplyChainTradeLineItem("IncludedSupplyChainTradeLineItem.SpecifiedSupplyChainTradeDelivery.BilledQuantity[2]",
                "IncludedSupplyChainTradeLineItem.SpecifiedSupplyChainTradeDelivery.BilledQuantity.unitCode[2]",
                "IncludedSupplyChainTradeLineItem.SpecifiedTradeProduct.Name[2]");
        
        // Create the XML
        InvoiceDOM dom = new InvoiceDOM(data);
        byte[] xml = dom.exportDoc();
        System.out.println(new String(xml));
    }
}
