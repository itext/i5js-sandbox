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
import zugferd.codes.FreeTextSubjectCode;
import zugferd.codes.GlobalIdentifierCode;
import zugferd.codes.PaymentMeansCode;
import zugferd.codes.TaxIDTypeCode;
import zugferd.xml.COMFORTInvoiceData;
import zugferd.exceptions.DataIncompleteException;
import zugferd.exceptions.InvalidCodeException;
import zugferd.xml.InvoiceDOM;

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
        data.addNote("HeaderExchangedDocument.Note[0]", FreeTextSubjectCode.REGULATORY_INFORMATION);
        data.addNote("HeaderExchangedDocument.Note[1]", FreeTextSubjectCode.PRICE_CONDITIONS);
        data.addNote("HeaderExchangedDocument.Note[2]", FreeTextSubjectCode.PAYMENT_INFORMATION);
        
        // SpecifiedSupplyChainTradeTransaction
        data.setBuyerReference("BuyerReference");
        // Seller
        data.setSellerID("SellerTradeParty.ID");
        data.addSellerGlobalID(GlobalIdentifierCode.DUNS, "SellerTradeParty.GlobalID[0]");
        data.addSellerGlobalID(GlobalIdentifierCode.GTIN, "SellerTradeParty.GlobalID[1]");
        data.addSellerGlobalID(GlobalIdentifierCode.ODETTE, "SellerTradeParty.GlobalID[2]");
        data.setSellerName("SellerTradeParty.Name");
        data.setSellerPostcode("SellerTradeParty.PostalTradeAddress.Postcode");
        data.setSellerLineOne("SellerTradeParty.PostalTradeAddress.LineOne");
        data.setSellerLineTwo("SellerTradeParty.PostalTradeAddress.LineTwo");
        data.setSellerCityName("SellerTradeParty.PostalTradeAddress.CityName");
        data.setSellerCountryID("DE");
        data.addSellerTaxRegistration(TaxIDTypeCode.VAT, "SellerTradeParty.SpecifiedTaxRegistration.SchemeID[0]");
        data.addSellerTaxRegistration(TaxIDTypeCode.FISCAL_NUMBER, "SellerTradeParty.SpecifiedTaxRegistration.SchemeID[1]");
        // Buyer
        data.setBuyerID("BuyerTradeParty.ID");
        data.addBuyerGlobalID(GlobalIdentifierCode.DUNS, "BuyerTradeParty.GlobalID[0]");
        data.addBuyerGlobalID(GlobalIdentifierCode.GTIN, "BuyerTradeParty.GlobalID[1]");
        data.addBuyerGlobalID(GlobalIdentifierCode.ODETTE, "BuyerTradeParty.GlobalID[2]");
        data.setBuyerName("BuyerTradeParty.Name");
        data.setBuyerPostcode("BuyerTradeParty.PostalTradeAddress.Postcode");
        data.setBuyerLineOne("BuyerTradeParty.PostalTradeAddress.LineOne");
        data.setBuyerLineTwo("BuyerTradeParty.PostalTradeAddress.LineTwo");
        data.setBuyerCityName("BuyerTradeParty.PostalTradeAddress.CityName");
        data.setBuyerCountryID("BE");
        data.addBuyerTaxRegistration(TaxIDTypeCode.VAT, "BuyerTradeParty.SpecifiedTaxRegistration.SchemeID[0]");
        data.addBuyerTaxRegistration(TaxIDTypeCode.FISCAL_NUMBER, "BuyerTradeParty.SpecifiedTaxRegistration.SchemeID[1]");
        
        // ApplicableSupplyChainTradeAgreement
        data.setBuyerOrderReferencedDocumentIssueDateTime(sdf.parse("2016/04/02"), DateFormatCode.YYYYMMDD);
        data.setBuyerOrderReferencedDocumentID("ApplicableSupplyChainTradeAgreement.BuyerOrderReferencedDocumentID");
        data.setContractReferencedDocumentIssueDateTime(sdf.parse("2016/04/03"), DateFormatCode.YYYYMMDD);
        data.setContractReferencedDocumentID("ApplicableSupplyChainTradeAgreement.ContractReferencedDocument");
        data.setCustomerOrderReferencedDocumentIssueDateTime(sdf.parse("2016/04/04"), DateFormatCode.YYYYMMDD);
        data.setCustomerOrderReferencedDocumentID("ApplicableSupplyChainTradeAgreement.CustomerOrderReferencedDocument");
        
        // ApplicableSupplyChainTradeDelivery
        data.setDeliveryDate(sdf.parse("2016/04/05"), DateFormatCode.YYYYMMDD);
        data.setDeliveryNoteReferencedDocumentIssueDateTime(sdf.parse("2016/04/06"), DateFormatCode.YYYYMMDD);
        data.setDeliveryNoteReferencedDocumentID("ApplicableSupplyChainTradeAgreement.DeliveryNoteReferencedDocument");
        
        // ApplicableSupplyChainTradeSettlement
        data.setPaymentReference("ApplicableSupplyChainTradeSettlement.PaymentReference");
        data.setInvoiceCurrencyCode("USD");
        
        data.setInvoiceeID("InvoiceeTradeParty.ID");
        data.addInvoiceeGlobalID(GlobalIdentifierCode.DUNS, "InvoiceeTradeParty.GlobalID[0]");
        data.addInvoiceeGlobalID(GlobalIdentifierCode.GTIN, "InvoiceeTradeParty.GlobalID[1]");
        data.addInvoiceeGlobalID(GlobalIdentifierCode.ODETTE, "InvoiceeTradeParty.GlobalID[2]");
        data.setInvoiceeName("InvoiceeTradeParty.Name");
        data.setInvoiceePostcode("InvoiceeTradeParty.PostalTradeAddress.Postcode");
        data.setInvoiceeLineOne("InvoiceeTradeParty.PostalTradeAddress.LineOne");
        data.setInvoiceeLineTwo("InvoiceeTradeParty.PostalTradeAddress.LineTwo");
        data.setInvoiceeCityName("InvoiceeTradeParty.PostalTradeAddress.CityName");
        data.setInvoiceeCountryID("BE");
        data.addInvoiceeTaxRegistration(TaxIDTypeCode.VAT, "InvoiceeTradeParty.SpecifiedTaxRegistration.SchemeID[0]");
        data.addInvoiceeTaxRegistration(TaxIDTypeCode.FISCAL_NUMBER, "InvoiceeTradeParty.SpecifiedTaxRegistration.SchemeID[1]");

        String[] information0 = { "SpecifiedTradeSettlementPaymentMeans.Information[0][0]", "SpecifiedTradeSettlementPaymentMeans.Information[0][1]", "SpecifiedTradeSettlementPaymentMeans.Information[0][2]" };
        data.addPaymentMeans(PaymentMeansCode.PAYMENT_TO_BANK_ACCOUNT, information0, "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[0]", "SpecifiedTradeSettlementPaymentMeans.ID[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.IBANID[0]", "SpecifiedTradeSettlementPaymentMeans.Payer.ProprietaryID[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.IBANID[0]", "SpecifiedTradeSettlementPaymentMeans.Payee.AccountName[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.ProprietaryID[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.BICID[0]", "SpecifiedTradeSettlementPaymentMeans.Payer.GermanBankleitzahlID[0]", "SpecifiedTradeSettlementPaymentMeans.Payer.Name[0]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.BICID[0]", "SpecifiedTradeSettlementPaymentMeans.Payee.GermanBankleitzahlID[0]", "SpecifiedTradeSettlementPaymentMeans.Payee.Name[0]");
        String[] information1 = { "SpecifiedTradeSettlementPaymentMeans.Information[1][0]", "SpecifiedTradeSettlementPaymentMeans.Information[1][1]" };
        data.addPaymentMeans(PaymentMeansCode.CASH, information1, "SpecifiedTradeSettlementPaymentMeans.schemeAgencyID[1]", "SpecifiedTradeSettlementPaymentMeans.ID[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.IBANID[1]", "SpecifiedTradeSettlementPaymentMeans.Payer.ProprietaryID[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.IBANID[1]", "SpecifiedTradeSettlementPaymentMeans.Payee.AccountName[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.ProprietaryID[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payer.BICID[1]", "SpecifiedTradeSettlementPaymentMeans.Payer.GermanBankleitzahlID[1]", "SpecifiedTradeSettlementPaymentMeans.Payer.Name[1]",
                "SpecifiedTradeSettlementPaymentMeans.Payee.BICID[1]", "SpecifiedTradeSettlementPaymentMeans.Payee.GermanBankleitzahlID[1]", "SpecifiedTradeSettlementPaymentMeans.Payee.Name[1]");
        
        // ram:ApplicableTradeTax
        data.addApplicableTradeTax("6.00", "USD", "VAT", "ApplicableTradeTax.ExemptionReason[0]", "100.00", "USD", "S", "6.00");
        data.addApplicableTradeTax("21.00", "USD", "VAT", "ApplicableTradeTax.ExemptionReason[1]", "100.00", "USD", "S", "21.00");
        
        data.setBillingStartEnd(sdf.parse("2016/04/01"), DateFormatCode.YYYYMMDD, sdf.parse("2016/04/30"), DateFormatCode.YYYYMMDD);
        
        data.addTradeAllowanceCharge("TradeAllowanceCharge.Indicator[0]", "0.1234", "USD", "TradeAllowanceCharge.Reason[0]",
            new String[]{"VAT", "VAT"}, new String[]{"S", "S"}, new String[]{"6.00", "21.00"});
        data.addTradeAllowanceCharge("TradeAllowanceCharge.Indicator[1]", "0.0120", "USD", "TradeAllowanceCharge.Reason[1]",
            new String[]{"VAT", "VAT"}, new String[]{"S", "S"}, new String[]{"0.00", "8.00"});
        
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
        InvoiceDOM dom = new InvoiceDOM(data);
        byte[] xml = dom.exportDoc();
        System.out.println(new String(xml));
    }
}
