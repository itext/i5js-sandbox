/*
 * This class creates an XML that has the structure needed to conform with
 * the COMFORT level, but the content isn't valid. Instead we used test values
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
import zugferd.xml.COMFORTInvoiceDOM;
import zugferd.xml.COMFORTInvoiceData;
import zugferd.exceptions.DataIncompleteException;
import zugferd.exceptions.InvalidCodeException;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class XML4Comfort {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, DataIncompleteException, TransformerException, InvalidCodeException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        
        COMFORTInvoiceData data = new COMFORTInvoiceData();
        // SpecifiedExchangedDocumentContext
        data.setTest(true);
                    
        // HeaderExchangedDocument
        data.setId("HeaderExchangedDocument.ID");
        data.setName("HeaderExchangedDocument.Name");
        data.setTypeCode(DocumentTypeCode.DEBIT_NOTE_FINANCIAL_ADJUSTMENT);
        data.setDate(sdf.parse("2016/04/01"), DateFormatCode.YYYYMMDD);
        data.addNote("HeaderExchangedDocument.Note[0]", "SubjectCode[0]");
        data.addNote("HeaderExchangedDocument.Note[1]", "SubjectCode[1]");
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
        data.setDeliveryDate(sdf.parse("2016/04/02"), DateFormatCode.YYYYMMDD);
        
        // ApplicableSupplyChainTradeSettlement
        data.setPaymentReference("ApplicableSupplyChainTradeSettlement.PaymentReference");
        data.setInvoiceCurrencyCode("ApplicableSupplyChainTradeSettlement.InvoiceCurrencyCode");
        String[] information = { "SpecifiedTradeSettlementPaymentMeans.Information[0]", "SpecifiedTradeSettlementPaymentMeans.Information[1]", "SpecifiedTradeSettlementPaymentMeans.Information[2]" };
        String[] scheme1 = { "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[0][0]", "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[0][1]", "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[0][2]" };
        String[] id1 = { "SpecifiedTradeSettlementPaymentMeans.ID[0][0]", "SpecifiedTradeSettlementPaymentMeans.ID[0][1]",  "SpecifiedTradeSettlementPaymentMeans.ID[0][2]" };
        data.addPaymentMeans("SpecifiedTradeSettlementPaymentMeans.TypeCode[0]", information, scheme1, id1,
                "SpecifiedTradeSettlementPaymentMeans.Payee.IBANID[0]", "SpecifiedTradeSettlementPaymentMeans.Payee.AccountName[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.ProprietaryID[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.IBANID[0]", "SpecifiedTradeSettlementPaymentMeans.Payer.AccountName[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.ProprietaryID[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.BICID[0]", "SpecifiedTradeSettlementPaymentMeans.Payee.GermanBankleitzahlID[0]", "SpecifiedTradeSettlementPaymentMeans.Payee.Name[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.BICID[0]", "SpecifiedTradeSettlementPaymentMeans.Payer.GermanBankleitzahlID[0]", "SpecifiedTradeSettlementPaymentMeans.Payer.Name[0]");
        String[] scheme2 = { "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[1][0]", "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[1][1]" };
        String[] id2 = { "SpecifiedTradeSettlementPaymentMeans.ID[1][0]", "SpecifiedTradeSettlementPaymentMeans.ID[1][1]" };
        data.addPaymentMeans("SpecifiedTradeSettlementPaymentMeans.TypeCode[1]", information, scheme2, id2,
                "SpecifiedTradeSettlementPaymentMeans.Payee.IBANID[1]", "SpecifiedTradeSettlementPaymentMeans.Payee.AccountName[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.ProprietaryID[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.IBANID[1]", "SpecifiedTradeSettlementPaymentMeans.Payer.AccountName[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.ProprietaryID[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.BICID[1]", "SpecifiedTradeSettlementPaymentMeans.Payee.GermanBankleitzahlID[1]", "SpecifiedTradeSettlementPaymentMeans.Payee.Name[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.BICID[1]", "SpecifiedTradeSettlementPaymentMeans.Payer.GermanBankleitzahlID[1]", "SpecifiedTradeSettlementPaymentMeans.Payer.Name[1]");
        
        // ram:ApplicableTradeTax
        data.addApplicableTradeTax("ApplicableTradeTax.CalculatedAmount[0]", "ApplicableTradeTax.CalculatedAmountCurrencyID[0]",
            "ApplicableTradeTax.TypeCode[0]", "ApplicableTradeTax.ExemptionReason[0]", "ApplicableTradeTax.BasisAmount[0]",
            "ApplicableTradeTax.BasisAmountCurrencyID[0]", "ApplicableTradeTax.CategoryCOde[0]", 
            "ApplicableTradeTax.ApplicablePercent[0]");
        data.addApplicableTradeTax("ApplicableTradeTax.CalculatedAmount[1]", "ApplicableTradeTax.CalculatedAmountCurrencyID[1]",
            "ApplicableTradeTax.TypeCode[1]", "ApplicableTradeTax.BasisAmount[1]", "ApplicableTradeTax.BasisAmountCurrencyID[1]",
            "ApplicableTradeTax.ApplicablePercent[1]");
        data.addApplicableTradeTax("ApplicableTradeTax.CalculatedAmount[2]", "ApplicableTradeTax.CalculatedAmountCurrencyID[2]",
            "ApplicableTradeTax.TypeCode[2]", "ApplicableTradeTax.ExemptionReason[2]", "ApplicableTradeTax.BasisAmount[2]", "ApplicableTradeTax.BasisAmountCurrencyID[2]",
            "ApplicableTradeTax.CategoryCode[2]", "ApplicableTradeTax.ApplicablePercent[2]");
        
        // SpecifiedTradeSettlementMonetarySummation
        data.setMonetarySummation("SpecifiedTradeSettlementMonetarySummation.LineTotalAmount", "SpecifiedTradeSettlementMonetarySummation.LineTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.ChargeTotalAmount", "SpecifiedTradeSettlementMonetarySummation.ChargeTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.AllowanceTotalAmount", "SpecifiedTradeSettlementMonetarySummation.AllowanceTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.TaxBasisTotalAmount", "SpecifiedTradeSettlementMonetarySummation.TaxBasisTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.TaxTotalAmount", "SpecifiedTradeSettlementMonetarySummation.TaxTotalAmountCurrencyID",
                "SpecifiedTradeSettlementMonetarySummation.GrandTotalAmount", "SpecifiedTradeSettlementMonetarySummation.GrandTotalAmountCurrencyID");
        
        String[][] notes0 = {
            { "IncludedSupplyChainTradeLineItem[0].AssociatedDocumentLineDocument.IncludedNote.Content[0]",
              "IncludedSupplyChainTradeLineItem[0].AssociatedDocumentLineDocument.IncludedNote[0].Content[1]",
              "IncludedSupplyChainTradeLineItem[0].AssociatedDocumentLineDocument.IncludedNote[0].Content[2]"},
            { "IncludedSupplyChainTradeLineItem[0].AssociatedDocumentLineDocument.IncludedNote[1].Content[0]",
              "IncludedSupplyChainTradeLineItem[0].AssociatedDocumentLineDocument.IncludedNote[1].Content[1]"},
            { "IncludedSupplyChainTradeLineItem[0].AssociatedDocumentLineDocument.IncludedNote[2].Content[0]" }
        };
        String[] indicator = {
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[0].ChargeIndicator",
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[1].ChargeIndicator",
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[2].ChargeIndicator"
        };
        String[] actualamount = {
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[0].ActualAmount",
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[1].ActualAmount",
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[2].ActualAmount"
        };
        String[] actualamountCurr = {
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[0].ActualAmount.CurrencyID",
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[1].ActualAmount.CurrencyID",
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[2].ActualAmount.CurrencyID"
        };
        String[] reason = {
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[0].Reason",
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[1].Reason",
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[2].Reason"
        };
        String[] taxTC = {
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[0].TypeCode",
             "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[1].TypeCode" };
        String[] taxER = {
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[0].ExemptionReason",
             "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[1].ExemptionReason" };
        String[] taxCC = {
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[0].CategoryCode",
             "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[1].CategoryCode" };
        String[] taxAP = {
            "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[0].ApplicablePercent",
             "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[1].ApplicablePercent" };
        data.addIncludedSupplyChainTradeLineItem(
                "IncludedSupplyChainTradeLineItem[0].AssociatedDocumentLineDocument.LineID", notes0,
                "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.ChargeAmount",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.ChargeAmount.CurrencyID",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.BasisQuantity",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.BasisQuantity.unitCode",
                indicator, actualamount, actualamountCurr, reason,
                "IncludedSupplyChainTradeLineItem[0].NetPriceProductTradePrice.ChargeAmount",
                "IncludedSupplyChainTradeLineItem[0].NetPriceProductTradePrice.ChargeAmount.CurrencyID",
                "IncludedSupplyChainTradeLineItem[0].NetPriceProductTradePrice.BasisQuantity",
                "IncludedSupplyChainTradeLineItem[0].NetPriceProductTradePrice.BasisQuantity.unitCode",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeDelivery.BilledQuantity",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeDelivery.BilledQuantity.unitCode",
                taxTC, taxER, taxCC, taxAP,
                "IncludedSupplyChainTradeLineItem[0].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.LineTotalAmount",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedTradeProduct.GlobalID",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedTradeProduct.GlobalID.schemeID",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedTradeProduct.SellerAssignedID",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedTradeProduct.BuyerAssignedID",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedTradeProduct.Name",
                "IncludedSupplyChainTradeLineItem[0].SpecifiedTradeProduct.Description");
        String[][] notes1 = {
            { "IncludedSupplyChainTradeLineItem.ram:AssociatedDocumentLineDocument[1].IncludedNote[0].Content[0]",
              "IncludedSupplyChainTradeLineItem.ram:AssociatedDocumentLineDocument[1].IncludedNote[0].Content[1]"},
            { "IncludedSupplyChainTradeLineItem.ram:AssociatedDocumentLineDocument1].IncludedNote[4].Content[0]" }
        };
        String[] indicator1 = {
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[0].ChargeIndicator",
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[1].ChargeIndicator",
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[2].ChargeIndicator"
        };
        String[] actualamount1 = {
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[0].ActualAmount",
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[1].ActualAmount",
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[2].ActualAmount"
        };
        String[] actualamountCurr1 = {
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[0].ActualAmount.CurrencyID",
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[1].ActualAmount.CurrencyID",
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[2].ActualAmount.CurrencyID"
        };
        String[] reason1 = {
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[0].Reason",
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[1].Reason",
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.AppliedTradeAllowanceCharge[2].Reason"
        };
        String[] taxTC1 = {
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[0].TypeCode",
             "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[1].TypeCode" };
        String[] taxER1 = {
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[0].ExemptionReason",
             "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[1].ExemptionReason" };
        String[] taxCC1 = {
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[0].CategoryCode",
             "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[1].CategoryCode" };
        String[] taxAP1 = {
            "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[0].ApplicablePercent",
             "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.ApplicableTradeTax[1].ApplicablePercent" };
        data.addIncludedSupplyChainTradeLineItem(
                "IncludedSupplyChainTradeLineItem[1].AssociatedDocumentLineDocument.LineID", notes1,
                "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.ChargeAmount",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.ChargeAmount.CurrencyID",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.BasisQuantity",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeAgreement.GrossPriceProductTradePrice.BasisQuantity.unitCode",
                indicator1, actualamount1, actualamountCurr1, reason1,
                "IncludedSupplyChainTradeLineItem[1].NetPriceProductTradePrice.ChargeAmount",
                "IncludedSupplyChainTradeLineItem[1].NetPriceProductTradePrice.ChargeAmount.CurrencyID",
                "IncludedSupplyChainTradeLineItem[1].NetPriceProductTradePrice.BasisQuantity",
                "IncludedSupplyChainTradeLineItem[1].NetPriceProductTradePrice.BasisQuantity.unitCode",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeDelivery.BilledQuantity",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeDelivery.BilledQuantity.unitCode",
                taxTC1, taxER1, taxCC1, taxAP1,
                "IncludedSupplyChainTradeLineItem[1].SpecifiedSupplyChainTradeSettlement.SpecifiedTradeSettlementMonetarySummation.LineTotalAmount",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedTradeProduct.GlobalID",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedTradeProduct.GlobalID.schemeID",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedTradeProduct.SellerAssignedID",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedTradeProduct.BuyerAssignedID",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedTradeProduct.Name",
                "IncludedSupplyChainTradeLineItem[1].SpecifiedTradeProduct.Description");
        COMFORTInvoiceDOM dom = new COMFORTInvoiceDOM();
        dom.importData(data);
        byte[] xml = dom.exportDoc();
        System.out.println(new String(xml));
    }
}
