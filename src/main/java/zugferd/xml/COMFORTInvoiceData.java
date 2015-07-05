/*
 * $Id$
 *
 * This file is part of the iText (R) project.
 * Copyright (c) 2014-2015 iText Group NV
 * Authors: Bruno Lowagie, et al.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License version 3
 * as published by the Free Software Foundation with the addition of the
 * following permission added to Section 15 as permitted in Section 7(a):
 * FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
 * ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
 * OF THIRD PARTY RIGHTS
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA, 02110-1301 USA, or download the license from the following URL:
 * http://itextpdf.com/terms-of-use/
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License,
 * a covered work must retain the producer line in every PDF that is created
 * or manipulated using iText.
 *
 * You can be released from the requirements of the license by purchasing
 * a commercial license. Buying such a license is mandatory as soon as you
 * develop commercial activities involving the iText software without
 * disclosing the source code of your own applications.
 * These activities include: offering paid services to customers as an ASP,
 * serving PDFs on the fly in a web application, shipping iText with a closed
 * source product.
 *
 * For more information, please contact iText Software Corp. at this
 * address: sales@itextpdf.com
 */
package zugferd.xml;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iText
 */
public class COMFORTInvoiceData extends BASICInvoiceData implements COMFORTInvoice {
    
    protected List<String> notesCodes = new ArrayList<String>();
    protected String buyerReference;
    protected String sellerID;
    protected List<String> sellerGlobalID = new ArrayList<String>();
    protected List<String> sellerGlobalSchemeID = new ArrayList<String>();
    protected String buyerID;
    protected List<String> buyerGlobalID = new ArrayList<String>();
    protected List<String> buyerGlobalSchemeID = new ArrayList<String>();
    protected String buyerOrderReferencedDocumentIssueDateTime;
    protected String buyerOrderReferencedDocumentIssueDateTimeFormat;
    protected String buyerOrderReferencedDocumentID;
    protected String contractReferencedDocumentIssueDateTime;
    protected String contractReferencedDocumentIssueDateTimeFormat;
    protected String contractReferencedDocumentID;
    protected String customerOrderReferencedDocumentIssueDateTime;
    protected String customerOrderReferencedDocumentIssueDateTimeFormat;
    protected String customerOrderReferencedDocumentID;
    protected String deliveryNoteReferencedDocumentIssueDateTime;
    protected String deliveryNoteReferencedDocumentIssueDateTimeFormat;
    protected String deliveryNoteReferencedDocumentID;
    protected String invoiceeID;
    protected List<String> invoiceeGlobalID = new ArrayList<String>();
    protected List<String> invoiceeGlobalSchemeID = new ArrayList<String>();
    protected String invoiceeName;
    protected String invoiceePostcode;
    protected String invoiceeLineOne;
    protected String invoiceeLineTwo;
    protected String invoiceeCityName;
    protected String invoiceeCountryID;
    protected List<String> invoiceeTaxRegistrationID = new ArrayList<String>();
    protected List<String> invoiceeTaxRegistrationSchemeID = new ArrayList<String>();
    protected List<String> paymentMeansTypeCode = new ArrayList<String>();
    protected List<String[]> paymentMeansInformation = new ArrayList<String[]>();
    protected List<String> paymentMeansPayerAccountIBAN = new ArrayList<String>();
    protected List<String> paymentMeansPayerAccountProprietaryID = new ArrayList<String>();
    protected List<String> paymentMeansPayerFinancialInstitutionBIC = new ArrayList<String>();
    protected List<String> paymentMeansPayerFinancialInstitutionGermanBankleitzahlID = new ArrayList<String>();
    protected List<String> paymentMeansPayerFinancialInstitutionName = new ArrayList<String>();
    protected List<String> taxExemptionReason = new ArrayList<String>();
    protected List<String> taxCategoryCode = new ArrayList<String>();
    protected String billingStartDateTime;
    protected String billingStartDateTimeFormat;
    protected String billingEndDateTime;
    protected String billingEndDateTimeFormat;
    protected List<String> tradeAllowanceChargeIndicator = new ArrayList<String>();
    protected List<String> tradeAllowanceChargeActualAmount = new ArrayList<String>();
    protected List<String> tradeAllowanceChargeActualAmountCurrency = new ArrayList<String>();
    protected List<String> tradeAllowanceChargeReason = new ArrayList<String>();
    protected List<String[]> tradeAllowanceChargeTaxTypeCode = new ArrayList<String[]>();
    protected List<String[]> tradeAllowanceChargeTaxCategoryCode = new ArrayList<String[]>();
    protected List<String[]> tradeAllowanceChargeTaxApplicablePercent = new ArrayList<String[]>();
    protected List<String[]> logisticsServiceChargeDescription = new ArrayList<String[]>();
    protected List<String> logisticsServiceChargeAmount = new ArrayList<String>();
    protected List<String> logisticsServiceChargeAmountCurrency = new ArrayList<String>();
    protected List<String[]> logisticsServiceChargeTaxTypeCode = new ArrayList<String[]>();
    protected List<String[]> logisticsServiceChargeTaxCategoryCode = new ArrayList<String[]>();
    protected List<String[]> logisticsServiceChargeTaxApplicablePercent = new ArrayList<String[]>();
    protected List<String[]> tradePaymentTermsInformation = new ArrayList<String[]>();
    protected List<String> tradePaymentTermsDueDateTime = new ArrayList<String>();
    protected List<String> tradePaymentTermsDueDateTimeFormat = new ArrayList<String>();
    protected String totalPrepaidAmount;
    protected String totalPrepaidAmountCurrencyID;
    protected String duePayableAmount;
    protected String duePayableAmountCurrencyID;
    protected List<String> lineItemLineID = new ArrayList<String>();
    protected List<String[][]> lineItemIncludedNote = new ArrayList<String[][]>();
    protected List<String> lineItemGrossPriceChargeAmount = new ArrayList<String>();
    protected List<String> lineItemGrossPriceChargeAmountCurrencyID = new ArrayList<String>();
    protected List<String> lineItemGrossPriceBasisQuantity = new ArrayList<String>();
    protected List<String> lineItemGrossPriceBasisQuantityCode = new ArrayList<String>();
    protected List<String[]> lineItemGrossPriceTradeAllowanceChargeIndicator = new ArrayList<String[]>();
    protected List<String[]> lineItemGrossPriceTradeAllowanceChargeActualAmount = new ArrayList<String[]>();
    protected List<String[]> lineItemGrossPriceTradeAllowanceChargeActualAmountCurrencyID = new ArrayList<String[]>();
    protected List<String[]> lineItemGrossPriceTradeAllowanceChargeReason = new ArrayList<String[]>();
    protected List<String> lineItemNetPriceChargeAmount = new ArrayList<String>();
    protected List<String> lineItemNetPriceChargeAmountCurrencyID = new ArrayList<String>();
    protected List<String> lineItemNetPriceBasisQuantity = new ArrayList<String>();
    protected List<String> lineItemNetPriceBasisQuantityCode = new ArrayList<String>();
            
    public String[] getNotesCodes() {
        return (String[]) notesCodes.toArray(new String[notesCodes.size()]);
    }

    public String getBuyerReference() {
        return buyerReference;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String[] getSellerGlobalID() {
        return (String[]) sellerGlobalID.toArray(new String[sellerGlobalID.size()]);
    }

    public String[] getSellerGlobalSchemeID() {
        return (String[]) sellerGlobalSchemeID.toArray(new String[sellerGlobalSchemeID.size()]);
    }

    public String getBuyerID() {
        return buyerID;
    }

    public String[] getBuyerGlobalID() {
        return (String[]) buyerGlobalID.toArray(new String[buyerGlobalID.size()]);
    }

    public String[] getBuyerGlobalSchemeID() {
        return (String[]) buyerGlobalSchemeID.toArray(new String[buyerGlobalSchemeID.size()]);
    }

    public String getBuyerOrderReferencedDocumentIssueDateTime() {
        return buyerOrderReferencedDocumentIssueDateTime;
    }

    public String getBuyerOrderReferencedDocumentIssueDateTimeFormat() {
        return buyerOrderReferencedDocumentIssueDateTimeFormat;
    }

    public String getBuyerOrderReferencedDocumentID() {
        return buyerOrderReferencedDocumentID;
    }

    public String getContractReferencedDocumentIssueDateTime() {
        return contractReferencedDocumentIssueDateTime;
    }

    public String getContractReferencedDocumentIssueDateTimeFormat() {
        return contractReferencedDocumentIssueDateTimeFormat;
    }

    public String getContractReferencedDocumentID() {
        return contractReferencedDocumentID;
    }

    public String getCustomerOrderReferencedDocumentIssueDateTime() {
        return customerOrderReferencedDocumentIssueDateTime;
    }

    public String getCustomerOrderReferencedDocumentIssueDateTimeFormat() {
        return customerOrderReferencedDocumentIssueDateTimeFormat;
    }

    public String getCustomerOrderReferencedDocumentID() {
        return customerOrderReferencedDocumentID;
    }

    public String getDeliveryNoteReferencedDocumentIssueDateTime() {
        return deliveryNoteReferencedDocumentIssueDateTime;
    }

    public String getDeliveryNoteReferencedDocumentIssueDateTimeFormat() {
        return deliveryNoteReferencedDocumentIssueDateTimeFormat;
    }

    public String getDeliveryNoteReferencedDocumentID() {
        return deliveryNoteReferencedDocumentID;
    }
    public String getInvoiceeID() {
        return invoiceeID;
    }
    
    public String[] getInvoiceeGlobalID() {
        return (String[]) invoiceeGlobalID.toArray(new String[invoiceeGlobalID.size()]);
    }

    public String[] getInvoiceeGlobalSchemeID() {
        return (String[]) invoiceeGlobalSchemeID.toArray(new String[invoiceeGlobalSchemeID.size()]);
    }
    
    public String getInvoiceeName() {
        return invoiceeName;
    }
    
    public String getInvoiceePostcode() {
        return invoiceePostcode;
    }
    
    public String getInvoiceeLineOne() {
        return invoiceeLineOne;
    }
    
    public String getInvoiceeLineTwo() {
        return invoiceeLineTwo;
    }
    
    public String getInvoiceeCityName() {
        return invoiceeCityName;
    }
    
    public String getInvoiceeCountryID() {
        return invoiceeCountryID;
    }
    
    public String[] getInvoiceeTaxRegistrationID() {
        return (String[]) invoiceeTaxRegistrationID.toArray(new String[invoiceeTaxRegistrationID.size()]);
    }
    
    public String[] getInvoiceeTaxRegistrationSchemeID() {
        return (String[]) invoiceeTaxRegistrationSchemeID.toArray(new String[invoiceeTaxRegistrationSchemeID.size()]);
    }

    public String[] getPaymentMeansTypeCode() {
        return (String[]) paymentMeansTypeCode.toArray(new String[paymentMeansTypeCode.size()]);
    }
    
    public String[][] getPaymentMeansInformation() {
        int n = paymentMeansInformation.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = paymentMeansInformation.get(i);
        }
        return array;
    }
    
    public String[] getPaymentMeansPayerAccountIBAN() {
        return (String[]) paymentMeansPayerAccountIBAN.toArray(new String[paymentMeansPayerAccountIBAN.size()]);
    }
    
    public String[] getPaymentMeansPayerAccountProprietaryID() {
        return (String[]) paymentMeansPayerAccountProprietaryID.toArray(new String[paymentMeansPayerAccountProprietaryID.size()]);
    }
    
    public String[] getPaymentMeansPayerFinancialInstitutionBIC() {
        return (String[]) paymentMeansPayerFinancialInstitutionBIC.toArray(new String[paymentMeansPayerFinancialInstitutionBIC.size()]);
    }
    
    public String[] getPaymentMeansPayerFinancialInstitutionGermanBankleitzahlID() {
        return (String[]) paymentMeansPayerFinancialInstitutionGermanBankleitzahlID.toArray(new String[paymentMeansPayerFinancialInstitutionGermanBankleitzahlID.size()]);
    }
    
    public String[] getPaymentMeansPayerFinancialInstitutionName() {
        return (String[]) paymentMeansPayerFinancialInstitutionName.toArray(new String[paymentMeansPayerFinancialInstitutionName.size()]);
    }

    public String[] getTaxExemptionReason() {
        return (String[])taxExemptionReason.toArray(new String[taxExemptionReason.size()]);
    }

    public String[] getTaxCategoryCode() {
        return (String[])taxCategoryCode.toArray(new String[taxCategoryCode.size()]);
    }
    
    public String getBillingStartDateTime() {
        return billingStartDateTime;
    }
    
    public String getBillingStartDateTimeFormat() {
        return billingStartDateTimeFormat;
    }
    
    public String getBillingEndDateTime() {
        return billingEndDateTime;
    }
    
    public String getBillingEndDateTimeFormat() {
        return billingEndDateTimeFormat;
    }
    
    public String[] getTradeAllowanceChargeIndicator() {
        return (String[])tradeAllowanceChargeIndicator.toArray(new String[tradeAllowanceChargeIndicator.size()]);
    }
    
    public String[] getTradeAllowanceChargeActualAmount() {
        return (String[])tradeAllowanceChargeActualAmount.toArray(new String[tradeAllowanceChargeActualAmount.size()]);
    }
    
    public String[] getTradeAllowanceChargeActualAmountCurrency() {
        return (String[])tradeAllowanceChargeActualAmountCurrency.toArray(new String[tradeAllowanceChargeActualAmountCurrency.size()]);
    }
    
    public String[] getTradeAllowanceChargeReason() {
        return (String[])tradeAllowanceChargeReason.toArray(new String[tradeAllowanceChargeReason.size()]);
    }

    public String[][] getTradeAllowanceChargeTaxTypeCode() {
        int n = tradeAllowanceChargeTaxTypeCode.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = tradeAllowanceChargeTaxTypeCode.get(i);
        }
        return array;
    }
    
    public String[][] getTradeAllowanceChargeTaxCategoryCode() {
        int n = tradeAllowanceChargeTaxCategoryCode.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = tradeAllowanceChargeTaxCategoryCode.get(i);
        }
        return array;
    }
    
    public String[][] getTradeAllowanceChargeTaxApplicablePercent() {
        int n = tradeAllowanceChargeTaxApplicablePercent.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = tradeAllowanceChargeTaxApplicablePercent.get(i);
        }
        return array;
    }
    
    public String[][] getLogisticsServiceChargeDescription() {
        int n = logisticsServiceChargeDescription.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = logisticsServiceChargeDescription.get(i);
        }
        return array;
    }
    public String[] getLogisticsServiceChargeAmount() {
        return (String[])logisticsServiceChargeAmount.toArray(new String[logisticsServiceChargeAmount.size()]);
    }
    public String[] getLogisticsServiceChargeAmountCurrency() {
        return (String[])logisticsServiceChargeAmountCurrency.toArray(new String[logisticsServiceChargeAmountCurrency.size()]);
    }
    public String[][] getLogisticsServiceChargeTaxTypeCode() {
        int n = logisticsServiceChargeTaxTypeCode.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = logisticsServiceChargeTaxTypeCode.get(i);
        }
        return array;
    }
    public String[][] getLogisticsServiceChargeTaxCategoryCode() {
        int n = logisticsServiceChargeTaxCategoryCode.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = logisticsServiceChargeTaxCategoryCode.get(i);
        }
        return array;
    }
    public String[][] getLogisticsServiceChargeTaxApplicablePercent() {
        int n = logisticsServiceChargeTaxApplicablePercent.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = logisticsServiceChargeTaxApplicablePercent.get(i);
        }
        return array;
    }
    
    public String[][] getTradePaymentTermsInformation() {
        int n = tradePaymentTermsInformation.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = tradePaymentTermsInformation.get(i);
        }
        return array;
    }
    public String[] getTradePaymentTermsDueDateTime() {
        return (String[])tradePaymentTermsDueDateTime.toArray(new String[tradePaymentTermsDueDateTime.size()]);
    }
    public String[] getTradePaymentTermsDueDateTimeFormat() {
        return (String[])tradePaymentTermsDueDateTimeFormat.toArray(new String[tradePaymentTermsDueDateTimeFormat.size()]);
    }
    public String getTotalPrepaidAmount() {
        return totalPrepaidAmount;
    }
    public String getTotalPrepaidAmountCurrencyID() {
        return totalPrepaidAmountCurrencyID;
    }
    public String getDuePayableAmount() {
        return duePayableAmount;
    }
    public String getDuePayableAmountCurrencyID() {
        return duePayableAmountCurrencyID;
    }
    public String[] getLineItemLineID() {
        return (String[])lineItemLineID.toArray(new String[lineItemLineID.size()]);
    }
    public String[][][] getLineItemIncludedNote() {
        int n = lineItemIncludedNote.size();
        String[][][] array = new String[n][][];
        for (int i = 0; i < n; i++) {
            array[i] = lineItemIncludedNote.get(i);
        }
        return array;
    }
    
    public String[] getLineItemGrossPriceChargeAmount() {
        return (String[])lineItemGrossPriceChargeAmount.toArray(new String[lineItemGrossPriceChargeAmount.size()]);
    }
    public String[] getLineItemGrossPriceChargeAmountCurrencyID() {
        return (String[])lineItemGrossPriceChargeAmountCurrencyID.toArray(new String[lineItemGrossPriceChargeAmountCurrencyID.size()]);
    }
    public String[] getLineItemGrossPriceBasisQuantity() {
        return (String[])lineItemGrossPriceBasisQuantity.toArray(new String[lineItemGrossPriceBasisQuantity.size()]);
    }
    public String[] getLineItemGrossPriceBasisQuantityCode() {
        return (String[])lineItemGrossPriceBasisQuantityCode.toArray(new String[lineItemGrossPriceBasisQuantityCode.size()]);
    }
    public String[][] getLineItemGrossPriceTradeAllowanceChargeIndicator() {
        int n = lineItemGrossPriceTradeAllowanceChargeIndicator.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = lineItemGrossPriceTradeAllowanceChargeIndicator.get(i);
        }
        return array;
    }
    public String[][] getLineItemGrossPriceTradeAllowanceChargeActualAmount() {
        int n = lineItemGrossPriceTradeAllowanceChargeActualAmount.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = lineItemGrossPriceTradeAllowanceChargeActualAmount.get(i);
        }
        return array;
    }
    public String[][] getLineItemGrossPriceTradeAllowanceChargeActualAmountCurrencyID() {
        int n = lineItemGrossPriceTradeAllowanceChargeActualAmountCurrencyID.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = lineItemGrossPriceTradeAllowanceChargeActualAmountCurrencyID.get(i);
        }
        return array;
    }
    public String[][] getLineItemGrossPriceTradeAllowanceChargeReason() {
        int n = lineItemGrossPriceTradeAllowanceChargeReason.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = lineItemGrossPriceTradeAllowanceChargeReason.get(i);
        }
        return array;
    }
    public String[] getLineItemNetPriceChargeAmount() {
        return (String[])lineItemNetPriceChargeAmount.toArray(new String[lineItemNetPriceChargeAmount.size()]);
    }
    public String[] getLineItemNetPriceChargeAmountCurrencyID() {
        return (String[])lineItemNetPriceChargeAmountCurrencyID.toArray(new String[lineItemNetPriceChargeAmountCurrencyID.size()]);
    }
    public String[] getLineItemNetPriceBasisQuantity() {
        return (String[])lineItemNetPriceBasisQuantity.toArray(new String[lineItemNetPriceBasisQuantity.size()]);
    }
    public String[] getLineItemNetPriceBasisQuantityCode() {
        return (String[])lineItemNetPriceBasisQuantityCode.toArray(new String[lineItemNetPriceBasisQuantityCode.size()]);
    }
    
    
    @Override
    public void addNote(String note) {
        throw new UnsupportedOperationException("This method can only be used for the BASIC level.");
    }
    
    public void addNote(String code, String note) {
    }

    public void setBuyerReference(String buyerReference) {
        this.buyerReference = buyerReference;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public void addSellerGlobaleID(String sellerGlobalSchemeID, String sellerGlobalID) {
        this.sellerGlobalID.add(sellerGlobalID);
        this.sellerGlobalSchemeID.add(sellerGlobalSchemeID);
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public void setBuyerGlobaleID(String buyerGlobalSchemeID, String buyerGlobalID) {
        this.buyerGlobalID.add(buyerGlobalID);
        this.buyerGlobalSchemeID.add(buyerGlobalSchemeID);
    }
    
    public void setBuyerOrderReferencedDocumentIssueDateTime(String buyerOrderReferencedDocumentIssueDateTime, String buyerOrderReferencedDocumentIssueDateTimeFormat) {
        this.buyerOrderReferencedDocumentIssueDateTime = buyerOrderReferencedDocumentIssueDateTime;
        this.buyerOrderReferencedDocumentIssueDateTimeFormat = buyerOrderReferencedDocumentIssueDateTimeFormat;
    }

    public void setBuyerOrderReferencedDocumentID(String buyerOrderReferencedDocumentID) {
        this.buyerOrderReferencedDocumentID = buyerOrderReferencedDocumentID;
    }

    public void setContractReferencedDocumentIssueDateTime(String contractReferencedDocumentIssueDateTime, String contractReferencedDocumentIssueDateTimeFormat) {
        this.contractReferencedDocumentIssueDateTime = contractReferencedDocumentIssueDateTime;
        this.contractReferencedDocumentIssueDateTimeFormat = contractReferencedDocumentIssueDateTimeFormat;
    }

    public void setContractReferencedDocumentID(String contractReferencedDocumentID) {
        this.contractReferencedDocumentID = contractReferencedDocumentID;
    }

    public void setCustomerOrderReferencedDocumentIssueDateTime(String customerOrderReferencedDocumentIssueDateTime, String customerOrderReferencedDocumentIssueDateTimeFormat) {
        this.customerOrderReferencedDocumentIssueDateTime = customerOrderReferencedDocumentIssueDateTime;
        this.customerOrderReferencedDocumentIssueDateTimeFormat = customerOrderReferencedDocumentIssueDateTimeFormat;
    }

    public void setCustomerOrderReferencedDocumentID(String customerOrderReferencedDocumentID) {
        this.customerOrderReferencedDocumentID = customerOrderReferencedDocumentID;
    }

    public void setDeliveryNoteReferencedDocumentIssueDateTime(String deliveryNoteReferencedDocumentIssueDateTime, String deliveryNoteReferencedDocumentIssueDateTimeFormat) {
        this.deliveryNoteReferencedDocumentIssueDateTime = deliveryNoteReferencedDocumentIssueDateTime;
        this.deliveryNoteReferencedDocumentIssueDateTimeFormat = deliveryNoteReferencedDocumentIssueDateTimeFormat;
    }

    public void setDeliveryNoteReferencedDocumentID(String deliveryNoteReferencedDocumentID) {
        this.deliveryNoteReferencedDocumentID = deliveryNoteReferencedDocumentID;
    }
    public void setInvoiceeID(String invoiceeID) {
        this.invoiceeID = invoiceeID;
    }
    
    public void addInvoiceeGlobalID(String invoiceeGlobalSchemeID, String invoiceeGlobalID) {
        this.invoiceeGlobalSchemeID.add(invoiceeGlobalSchemeID);
        this.invoiceeGlobalID.add(invoiceeGlobalID);
    }
    
    public void setInvoiceeName(String invoiceeName) {
        this.invoiceeName = invoiceeName;
    }
    
    public void setInvoiceePostcode(String invoiceePostcode) {
        this.invoiceePostcode = invoiceePostcode;
    }
    
    public void setInvoiceeLineOne(String invoiceeLineOne) {
        this.invoiceeLineOne = invoiceeLineOne;
    }
    
    public void setInvoiceeLineTwo(String invoiceeLineTwo) {
        this.invoiceeLineTwo = invoiceeLineTwo;
    }
    
    public void setInvoiceeCityName(String invoiceeCityName) {
        this.invoiceeCityName = invoiceeCityName;
    }
    
    public void setInvoiceeCountryID(String invoiceeCountryID) {
        this.invoiceeCountryID = invoiceeCountryID;
    }
    
    public void addInvoiceeTaxRegistrationID(String schemeID, String taxId) {
        invoiceeTaxRegistrationSchemeID.add(schemeID);
        invoiceeTaxRegistrationID.add(taxId);
    }

    @Override
    public void addPaymentMeans(String schemeAgencyID, String id, String iban, String accountname, String proprietaryID, String bic, String germanBankleitzahlID, String institutionname) {
        throw new UnsupportedOperationException("This method can only be used for the BASIC level.");
    }

    public void addPaymentMeans(
            String typeCode, String[] information,
            String schemeAgencyID, String id,
            String ibanDebtor, String proprietaryIDDebtor,
            String ibanCreditor, String accountnameCreditor, String proprietaryIDCreditor,
            String bicDebtor, String germanBankleitzahlIDDebtor, String institutionnameDebtor,
            String bicCreditor, String germanBankleitzahlIDCreditor, String institutionnameCreditor
    ) {
        paymentMeansTypeCode.add(typeCode);
        paymentMeansInformation.add(information);
        paymentMeansID.add(id);
        paymentMeansSchemeAgencyID.add(schemeAgencyID);
        paymentMeansPayerAccountIBAN.add(ibanDebtor);
        paymentMeansPayerAccountProprietaryID.add(proprietaryIDDebtor);
        paymentMeansPayeeAccountIBAN.add(ibanCreditor);
        paymentMeansPayeeAccountName.add(accountnameCreditor);
        paymentMeansPayeeAccountProprietaryID.add(proprietaryIDCreditor);
        paymentMeansPayerFinancialInstitutionBIC.add(bicDebtor);
        paymentMeansPayerFinancialInstitutionGermanBankleitzahlID.add(germanBankleitzahlIDDebtor);
        paymentMeansPayerFinancialInstitutionName.add(institutionnameDebtor);
        paymentMeansPayeeFinancialInstitutionBIC.add(bicCreditor);
        paymentMeansPayeeFinancialInstitutionGermanBankleitzahlID.add(germanBankleitzahlIDCreditor);
        paymentMeansPayeeFinancialInstitutionName.add(institutionnameCreditor);
    }

    @Override
    public void addApplicableTradeTax(String calculatedAmount, String calculatedAmountCurrencyID, String typeCode,
            String basisAmount, String basisAmountCurrencyID, String applicablePercent) {
        throw new UnsupportedOperationException("This method can only be used for the BASIC level.");
    }

    public void addApplicableTradeTax(String calculatedAmount, String calculatedAmountCurrencyID, String typeCode,
            String exemptionReason, String basisAmount, String basisAmountCurrencyID, String categoryCode, String applicablePercent) {
        taxCalculatedAmount.add(calculatedAmount);
        taxCalculatedAmountCurrencyID.add(calculatedAmountCurrencyID);
        taxTypeCode.add(typeCode);
        taxExemptionReason.add(exemptionReason);
        taxBasisAmount.add(basisAmount);
        taxBasisAmountCurrencyID.add(basisAmountCurrencyID);
        taxCategoryCode.add(categoryCode);
        taxApplicablePercent.add(applicablePercent);
    }
    
    public void setBillingStartEnd(String billingStartDateTime, String billingStartDateTimeFormat,
            String billingEndDateTime, String billingEndDateTimeFormat) {
        this.billingStartDateTime = billingStartDateTime;
        this.billingStartDateTimeFormat = billingStartDateTimeFormat;
        this.billingEndDateTime = billingEndDateTime;
        this.billingEndDateTimeFormat = billingEndDateTimeFormat;
    }
    
    public void addTradeAllowanceCharge(String indicator, String actualAmount, String actualAmountCurrency,
            String reason, String[] typeCodes, String[] categoryCodes, String[] applicablePercent) {
        this.tradeAllowanceChargeIndicator.add(indicator);
        this.tradeAllowanceChargeActualAmount.add(actualAmount);
        this.tradeAllowanceChargeActualAmountCurrency.add(actualAmountCurrency);
        this.tradeAllowanceChargeReason.add(reason);
        this.tradeAllowanceChargeTaxTypeCode.add(typeCodes);
        this.tradeAllowanceChargeTaxCategoryCode.add(categoryCodes);
        this.tradeAllowanceChargeTaxApplicablePercent.add(applicablePercent);
    }
    
    public void addLogisticsServiceCharge(String[] description, String actualAmount, String actualAmountCurrency,
            String[] typeCodes, String[] categoryCodes, String[] applicablePercent) {
        this.logisticsServiceChargeDescription.add(description);
        this.logisticsServiceChargeAmount.add(actualAmount);
        this.logisticsServiceChargeAmountCurrency.add(actualAmountCurrency);
        this.logisticsServiceChargeTaxTypeCode.add(typeCodes);
        this.logisticsServiceChargeTaxCategoryCode.add(categoryCodes);
        this.logisticsServiceChargeTaxApplicablePercent.add(applicablePercent);
    }
    public void setTotalPrepaidAmount(String totalPrepaidAmount) {
        this.totalPrepaidAmount = totalPrepaidAmount;
    }
    public void setTotalPrepaidCurrencyID(String totalPrepaidCurrencyID) {
        this.totalPrepaidAmountCurrencyID = totalPrepaidCurrencyID;
    }
    public void setDuePayableAmount(String duePayableAmount) {
        this.duePayableAmount = duePayableAmount;
    }
    public void setDuePayableAmountCurrencyID(String duePayableAmountCurrencyID) {
        this.duePayableAmountCurrencyID = duePayableAmountCurrencyID;
    }
    
    @Override
    public void addIncludedSupplyChainTradeLineItem(String billedQuantity, String billedQuantityUnitCode, String specifiedTradeProductName) {
        throw new UnsupportedOperationException("This method can only be used for the BASIC level.");
    }
    
    public void addIncludedSupplyChainTradeLineItem(String id, String[][] notes,
            String grossPriceChargeAmount, String grossPriceChargeAmountCurrencyID,
            String grossPriceBasisQuantity, String grossPriceBasisQuantityCode,
            String[] grossPriceTradeAllowanceChargeIndicator,
            String[] grossPriceTradeAllowanceChargeActualAmount,
            String[] grossPriceTradeAllowanceChargeActualAmountCurrencyID,
            String[] grossPriceTradeAllowanceChargeReason,
            String netPriceChargeAmount, String netPriceChargeAmountCurrencyID,
            String netPriceBasisQuantity, String netPriceBasisQuantityCode,
            String billedQuantity, String billedQuantityUnitCode, String specifiedTradeProductName) {
        this.lineItemLineID.add(id);
        this.lineItemIncludedNote.add(notes);
        this.lineItemGrossPriceChargeAmount.add(grossPriceChargeAmount);
        this.lineItemGrossPriceChargeAmountCurrencyID.add(grossPriceChargeAmountCurrencyID);
        this.lineItemGrossPriceBasisQuantity.add(grossPriceBasisQuantity);
        this.lineItemGrossPriceBasisQuantityCode.add(grossPriceBasisQuantityCode);
        this.lineItemGrossPriceTradeAllowanceChargeIndicator.add(grossPriceTradeAllowanceChargeIndicator);
        this.lineItemGrossPriceTradeAllowanceChargeActualAmount.add(grossPriceTradeAllowanceChargeActualAmount);
        this.lineItemGrossPriceTradeAllowanceChargeActualAmountCurrencyID.add(grossPriceTradeAllowanceChargeActualAmountCurrencyID);
        this.lineItemGrossPriceTradeAllowanceChargeReason.add(grossPriceTradeAllowanceChargeReason);
        this.lineItemNetPriceChargeAmount.add(netPriceChargeAmount);
        this.lineItemNetPriceChargeAmountCurrencyID.add(netPriceChargeAmountCurrencyID);
        this.lineItemNetPriceBasisQuantity.add(netPriceBasisQuantity);
        this.lineItemNetPriceBasisQuantityCode.add(netPriceBasisQuantityCode);
        this.lineItemBilledQuantity.add(billedQuantity);
        this.lineItemBilledQuantityUnitCode.add(billedQuantityUnitCode);
        this.lineItemSpecifiedTradeProductName.add(specifiedTradeProductName);
    }
}

