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
    protected List<String> paymentMeansPayerFinancialInstitutionBIC;
    protected List<String> paymentMeansPayerFinancialInstitutionGermanBankleitzahlID;
    protected List<String> paymentMeansPayerFinancialInstitutionName;
    protected List<String> taxExemptionReason;
    protected List<String> taxCategoryCode;
    protected String billingStartDateTime;
    protected String billingStartDateTimeFormat;
    protected String billingEndDateTime;
    protected String billingEndDateTimeFormat;
    protected List<String> chargeIndicator;
    protected List<String> chargeActualAmount;
    protected List<String> chargeActualAmountCurrency;
    protected List<String> chargeReason;
    protected List<String[]> chargeTaxTypeCode;
    protected List<String[]> chargeTaxCategoryCode;
    protected List<String[]> chargeTaxApplicablePercent;
            
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
    
    public String[] getChargeIndicator() {
        return (String[])chargeIndicator.toArray(new String[chargeIndicator.size()]);
    }
    
    public String[] getChargeActualAmount() {
        return (String[])chargeActualAmount.toArray(new String[chargeActualAmount.size()]);
    }
    
    public String[] getChargeActualAmountCurrency() {
        return (String[])chargeActualAmountCurrency.toArray(new String[chargeActualAmountCurrency.size()]);
    }
    
    public String[] getChargeReason() {
        return (String[])chargeReason.toArray(new String[chargeReason.size()]);
    }

    public String[][] getChargeTaxTypeCode() {
        int n = chargeTaxTypeCode.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = chargeTaxTypeCode.get(i);
        }
        return array;
    }
    
    public String[][] getChargeTaxCategoryCode() {
        int n = chargeTaxCategoryCode.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = chargeTaxCategoryCode.get(i);
        }
        return array;
    }
    
    public String[][] getChargeTaxApplicablePercent() {
        int n = chargeTaxApplicablePercent.size();
        String[][] array = new String[n][];
        for (int i = 0; i < n; i++) {
            array[i] = chargeTaxApplicablePercent.get(i);
        }
        return array;
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
    
    public void addCharge(String indicator, String actualAmount, String actualAmountCurrency,
            String reason, String[] typeCodes, String[] categoryCodes, String[] applicablePercent) {
        this.chargeIndicator.add(indicator);
        this.chargeActualAmount.add(actualAmount);
        this.chargeActualAmountCurrency.add(actualAmountCurrency);
        this.chargeReason.add(reason);
        this.chargeTaxTypeCode.add(typeCodes);
        this.chargeTaxCategoryCode.add(categoryCodes);
        this.chargeTaxApplicablePercent.add(applicablePercent);
    }
}
