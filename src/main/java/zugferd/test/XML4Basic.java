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
import zugferd.xml.BASICInvoiceDOM;
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
        data.setSellerCountryID("SellerTradeParty.PostalTradeAddress.CountryID");
        data.addSellerTaxRegistration("SellerTradeParty.SpecifiedTaxRegistration[0]", "SellerTradeParty.SpecifiedTaxRegistration.SchemeID[0]");
        data.addSellerTaxRegistration("SellerTradeParty.SpecifiedTaxRegistration[1]", "SellerTradeParty.SpecifiedTaxRegistration.SchemeID[1]");
        data.addSellerTaxRegistration("SellerTradeParty.SpecifiedTaxRegistration[2]", "SellerTradeParty.SpecifiedTaxRegistration.SchemeID[2]");
        // Buyer
        data.setBuyerName("BuyerTradeParty.Name");
        data.setBuyerPostcode("BuyerTradeParty.PostalTradeAddress.Postcode");
        data.setBuyerLineOne("BuyerTradeParty.PostalTradeAddress.LineOne");
        data.setBuyerLineTwo("BuyerTradeParty.PostalTradeAddress.LineTwo");
        data.setBuyerCityName("BuyerTradeParty.PostalTradeAddress.CityName");
        data.setBuyerCountryID("BuyerTradeParty.PostalTradeAddress.CountryID");
        data.addBuyerTaxRegistration("BuyerTradeParty.SpecifiedTaxRegistration[0]", "BuyerTradeParty.SpecifiedTaxRegistration.SchemeID[0]");
        data.addBuyerTaxRegistration("BuyerTradeParty.SpecifiedTaxRegistration[1]", "BuyerTradeParty.SpecifiedTaxRegistration.SchemeID[1]");
        data.addBuyerTaxRegistration("BuyerTradeParty.SpecifiedTaxRegistration[2]", "BuyerTradeParty.SpecifiedTaxRegistration.SchemeID[2]");
        
        // ApplicableSupplyChainTradeDelivery
        data.setDeliveryDate(sdf.parse("2015/04/01"), DateFormatCode.YYYYMMDD);
        
        // ApplicableSupplyChainTradeSettlement
        data.setPaymentReference("ApplicableSupplyChainTradeSettlement.PaymentReference");
        data.setInvoiceCurrencyCode("ApplicableSupplyChainTradeSettlement.InvoiceCurrencyCode");
        String[] scheme1 = { "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[0][0]", "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[0][1]", "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[0][2]" };
        String[] id1 = { "SpecifiedTradeSettlementPaymentMeans.ID[0][0]", "SpecifiedTradeSettlementPaymentMeans.ID[0][1]",  "SpecifiedTradeSettlementPaymentMeans.ID[0][2]" };
        data.addPaymentMeans(scheme1, id1,
                "SpecifiedTradeSettlementPaymentMeans.IBANID[0]", "SpecifiedTradeSettlementPaymentMeans.AccountName[0]", "SpecifiedTradeSettlementPaymentMeans.ProprietaryID[0]",
                "SpecifiedTradeSettlementPaymentMeans.BICID[0]", "SpecifiedTradeSettlementPaymentMeans.GermanBankleitzahlID[0]", "SpecifiedTradeSettlementPaymentMeans.Name[0]");
        String[] scheme2 = { "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[1][0]", "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[1][1]" };
        String[] id2 = { "SpecifiedTradeSettlementPaymentMeans.ID[1][0]", "SpecifiedTradeSettlementPaymentMeans.ID[1][1]" };
        data.addPaymentMeans(scheme2, id2,
                "SpecifiedTradeSettlementPaymentMeans.IBANID[1]", "SpecifiedTradeSettlementPaymentMeans.AccountName[1]", "SpecifiedTradeSettlementPaymentMeans.ProprietaryID[1]",
                "SpecifiedTradeSettlementPaymentMeans.BICID[1]", "SpecifiedTradeSettlementPaymentMeans.GermanBankleitzahlID[1]", "SpecifiedTradeSettlementPaymentMeans.Name[1]");
        
        // ram:ApplicableTradeTax
        data.addApplicableTradeTax("ApplicableTradeTax.CalculatedAmount[0]", "ApplicableTradeTax.CalculatedAmountCurrencyID[0]",
            "ApplicableTradeTax.TypeCode[0]", "ApplicableTradeTax.BasisAmount[0]", "ApplicableTradeTax.BasisAmountCurrencyID[0]",
            "ApplicableTradeTax.ApplicablePercent[0]");
        data.addApplicableTradeTax("ApplicableTradeTax.CalculatedAmount[1]", "ApplicableTradeTax.CalculatedAmountCurrencyID[1]",
            "ApplicableTradeTax.TypeCode[1]", "ApplicableTradeTax.BasisAmount[1]", "ApplicableTradeTax.BasisAmountCurrencyID[1]",
            "ApplicableTradeTax.ApplicablePercent[1]");
        data.addApplicableTradeTax("ApplicableTradeTax.CalculatedAmount[2]", "ApplicableTradeTax.CalculatedAmountCurrencyID[2]",
            "ApplicableTradeTax.TypeCode[2]", "ApplicableTradeTax.BasisAmount[2]", "ApplicableTradeTax.BasisAmountCurrencyID[2]",
            "ApplicableTradeTax.ApplicablePercent[2]");
        
        // SpecifiedTradeSettlementMonetarySummation
        data.setMonetarySummation("SpecifiedTradeSettlementMonetarySummation.LineTotalAmount", "SpecifiedTradeSettlementMonetarySummation.LineTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.ChargeTotalAmount", "SpecifiedTradeSettlementMonetarySummation.ChargeTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.AllowanceTotalAmount", "SpecifiedTradeSettlementMonetarySummation.AllowanceTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.TaxBasisTotalAmount", "SpecifiedTradeSettlementMonetarySummation.TaxBasisTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.TaxTotalAmount", "SpecifiedTradeSettlementMonetarySummation.TaxTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.GrandTotalAmount", "SpecifiedTradeSettlementMonetarySummation.GrandTotalAmountCurrencyID");
        
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
        BASICInvoiceDOM dom = new BASICInvoiceDOM();
        dom.importData(data);
        byte[] xml = dom.exportDoc();
        System.out.println(new String(xml));
    }
}
