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
public class BASICInvoiceData implements BASICInvoice {
    
    protected boolean test = true;
    protected String id;
    protected String name = "INVOICE";
    protected String typeCode = "380";
    protected String date;
    protected String dateFormat = "102";
    protected List<String> notes = new ArrayList<String>();
    protected String sellerName;
    protected String sellerPostcode;
    protected String sellerLineOne;
    protected String sellerLineTwo;
    protected String sellerCityName;
    protected String sellerCountryID;
    protected List<String> sellerTaxRegistrationID = new ArrayList<String>();
    protected List<String> sellerTaxRegistrationSchemeID = new ArrayList<String>();
    protected String buyerName;
    protected String buyerPostcode;
    protected String buyerLineOne;
    protected String buyerLineTwo;
    protected String buyerCityName;
    protected String buyerCountryID;
    protected List<String> buyerTaxRegistrationID = new ArrayList<String>();
    protected List<String> buyerTaxRegistrationSchemeID = new ArrayList<String>();
    protected String deliveryDate;
    protected String deliveryDateFormat = "102";
    protected String paymentReference;
    protected String invoiceCurrencyCode;
    protected List<String> paymentMeansID = new ArrayList<String>();
    protected List<String> paymentMeansSchemeAgencyID = new ArrayList<String>();
    protected List<String> paymentMeansPayeeAccountIBAN = new ArrayList<String>();
    protected List<String> paymentMeansPayeeAccountName = new ArrayList<String>();
    protected List<String> paymentMeansPayeeAccountProprietaryID = new ArrayList<String>();
    protected List<String> paymentMeansPayeeFinancialInstitutionBIC = new ArrayList<String>();
    protected List<String> paymentMeansPayeeFinancialInstitutionGermanBankleitzahlID = new ArrayList<String>();
    protected List<String> paymentMeansPayeeFinancialInstitutionName = new ArrayList<String>();
    protected List<String> taxCalculatedAmount = new ArrayList<String>();
    protected List<String> taxCalculatedAmountCurrencyID = new ArrayList<String>();
    protected List<String> taxTypeCode = new ArrayList<String>();
    protected List<String> taxBasisAmount = new ArrayList<String>();
    protected List<String> taxBasisAmountCurrencyID = new ArrayList<String>();
    protected List<String> taxApplicablePercent = new ArrayList<String>();
    protected String lineTotalAmount;
    protected String lineTotalAmountCurrencyID;
    protected String chargeTotalAmount;
    protected String chargeTotalAmountCurrencyID;
    protected String allowanceTotalAmount;
    protected String allowanceTotalAmountCurrencyID;
    protected String taxBasisTotalAmount;
    protected String taxBasisTotalAmountCurrencyID;
    protected String taxTotalAmount;
    protected String taxTotalAmountCurrencyID;
    protected String grandTotalAmount;
    protected String grandTotalAmountCurrencyID;
    protected List<String> billedQuantity = new ArrayList<String>();
    protected List<String> billedQuantityUnitCode = new ArrayList<String>();
    protected List<String> specifiedTradeProductName = new ArrayList<String>();
    
    
    public boolean getTestIndicator() {
        return test;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getDateTime() {
        return date;
    }

    public String getDateTimeFormat() {
        return dateFormat;
    }

    public String[] getNotes() {
        return (String[]) notes.toArray(new String[notes.size()]);
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerPostcode() {
        return sellerPostcode;
    }

    public String getSellerLineOne() {
        return sellerLineOne;
    }

    public String getSellerLineTwo() {
        return sellerLineTwo;
    }

    public String getSellerCityName() {
        return sellerCityName;
    }

    public String getSellerCountryID() {
        return sellerCountryID;
    }

    public String[] getSellerTaxRegistrationID() {
        return (String[]) sellerTaxRegistrationID.toArray(new String[sellerTaxRegistrationID.size()]);
    }

    public String[] getSellerTaxRegistrationSchemeID() {
        return (String[]) sellerTaxRegistrationSchemeID.toArray(new String[sellerTaxRegistrationSchemeID.size()]);
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerPostcode() {
        return buyerPostcode;
    }

    public String getBuyerLineOne() {
        return buyerLineOne;
    }

    public String getBuyerLineTwo() {
        return buyerLineTwo;
    }

    public String getBuyerCityName() {
        return buyerCityName;
    }

    public String getBuyerCountryID() {
        return buyerCountryID;
    }

    public String[] getBuyerTaxRegistrationID() {
        return (String[]) buyerTaxRegistrationID.toArray(new String[buyerTaxRegistrationID.size()]);
    }

    public String[] getBuyerTaxRegistrationSchemeID() {
        return (String[]) buyerTaxRegistrationSchemeID.toArray(new String[buyerTaxRegistrationSchemeID.size()]);
    }

    public String getDeliveryDateTime() {
        return deliveryDate;
    }

    public String getDeliveryDateTimeFormat() {
        return deliveryDateFormat;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public String getInvoiceCurrencyCode() {
        return invoiceCurrencyCode;
    }

    public String[] getPaymentMeansID() {
        return (String[])paymentMeansID.toArray(new String[paymentMeansID.size()]);
    }

    public String[] getPaymentMeansSchemeAgencyID() {
        return (String[])paymentMeansSchemeAgencyID.toArray(new String[paymentMeansSchemeAgencyID.size()]);
    }

    public String[] getPaymentMeansPayeeAccountIBAN() {
        return (String[])paymentMeansPayeeAccountIBAN.toArray(new String[paymentMeansPayeeAccountIBAN.size()]);
    }

    public String[] getPaymentMeansPayeeAccountAccountName() {
        return (String[])paymentMeansPayeeAccountName.toArray(new String[paymentMeansPayeeAccountName.size()]);
    }

    public String[] getPaymentMeansPayeeAccountProprietaryID() {
        return (String[])paymentMeansPayeeAccountProprietaryID.toArray(new String[paymentMeansPayeeAccountProprietaryID.size()]);
    }

    public String[] getPaymentMeansPayeeFinancialInstitutionBIC() {
        return (String[])paymentMeansPayeeFinancialInstitutionBIC.toArray(new String[paymentMeansPayeeFinancialInstitutionBIC.size()]);
    }

    public String[] getPaymentMeansPayeeFinancialInstitutionGermanBankleitzahlID() {
        return (String[])paymentMeansPayeeFinancialInstitutionGermanBankleitzahlID.toArray(new String[paymentMeansPayeeFinancialInstitutionGermanBankleitzahlID.size()]);
    }

    public String[] getPaymentMeansPayeeFinancialInstitutionName() {
        return (String[])paymentMeansPayeeFinancialInstitutionName.toArray(new String[paymentMeansPayeeFinancialInstitutionName.size()]);
    }

    public String[] getTaxCalculatedAmount() {
        return (String[])taxCalculatedAmount.toArray(new String[taxCalculatedAmount.size()]);
    }

    public String[] getTaxCalculatedAmountCurrencyID() {
        return (String[])taxCalculatedAmountCurrencyID.toArray(new String[taxCalculatedAmountCurrencyID.size()]);
    }

    public String[] getTaxTypeCode() {
        return (String[])taxTypeCode.toArray(new String[taxTypeCode.size()]);
    }

    public String[] getTaxBasisAmount() {
        return (String[])taxBasisAmount.toArray(new String[taxBasisAmount.size()]);
    }

    public String[] getTaxBasisAmountCurrencyID() {
        return (String[])taxBasisAmountCurrencyID.toArray(new String[taxBasisAmountCurrencyID.size()]);
    }

    public String[] getTaxApplicablePercent() {
        return (String[])taxApplicablePercent.toArray(new String[taxApplicablePercent.size()]);
    }

    public String getLineTotalAmount() {
        return lineTotalAmount;
    }

    public String getLineTotalAmountCurrencyID() {
        return lineTotalAmountCurrencyID;
    }

    public String getChargeTotalAmount() {
        return chargeTotalAmount;
    }

    public String getChargeTotalAmountCurrencyID() {
        return chargeTotalAmountCurrencyID;
    }

    public String getAllowanceTotalAmount() {
        return allowanceTotalAmount;
    }

    public String getAllowanceTotalAmountCurrencyID() {
        return allowanceTotalAmountCurrencyID;
    }

    public String getTaxBasisTotalAmount() {
        return taxBasisTotalAmount;
    }

    public String getTaxBasisTotalAmountCurrencyID() {
        return taxBasisTotalAmountCurrencyID;
    }

    public String getTaxTotalAmount() {
        return taxTotalAmount;
    }

    public String getTaxTotalAmountCurrencyID() {
        return taxTotalAmountCurrencyID;
    }

    public String getGrandTotalAmount() {
        return grandTotalAmount;
    }

    public String getGrandTotalAmountCurrencyID() {
        return grandTotalAmount;
    }

    public String[] getBilledQuantity() {
        return (String[])billedQuantity.toArray(new String[billedQuantity.size()]);
    }

    public String[] getBilledQuantityUnitCode() {
        return (String[])billedQuantityUnitCode.toArray(new String[billedQuantityUnitCode.size()]);
    }

    public String[] getSpecifiedTradeProductName() {
        return (String[])specifiedTradeProductName.toArray(new String[specifiedTradeProductName.size()]);
    }

    public void setTest(boolean test) {
        this.test = test;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void addNote(String note) {
        notes.add(note);
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setSellerPostcode(String sellerPostcode) {
        this.sellerPostcode = sellerPostcode;
    }

    public void setSellerLineOne(String sellerLineOne) {
        this.sellerLineOne = sellerLineOne;
    }

    public void setSellerLineTwo(String sellerLineTwo) {
        this.sellerLineTwo = sellerLineTwo;
    }

    public void setSellerCityName(String sellerCityName) {
        this.sellerCityName = sellerCityName;
    }

    public void setSellerCountryID(String sellerCountryID) {
        this.sellerCountryID = sellerCountryID;
    }

    public void addSellerTaxRegistration(String schemeID, String taxId) {
        sellerTaxRegistrationSchemeID.add(schemeID);
        sellerTaxRegistrationID.add(taxId);
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setBuyerPostcode(String buyerPostcode) {
        this.buyerPostcode = buyerPostcode;
    }

    public void setBuyerLineOne(String buyerLineOne) {
        this.buyerLineOne = buyerLineOne;
    }

    public void setBuyerLineTwo(String buyerLineTwo) {
        this.buyerLineTwo = buyerLineTwo;
    }

    public void setBuyerCityName(String buyerCityName) {
        this.buyerCityName = buyerCityName;
    }

    public void setBuyerCountryID(String buyerCountryID) {
        this.buyerCountryID = buyerCountryID;
    }

    public void setBuyerTaxRegistration(String schemeID, String taxId) {
        buyerTaxRegistrationSchemeID.add(schemeID);
        buyerTaxRegistrationID.add(taxId);
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDeliveryDateFormat(String deliveryDateFormat) {
        this.deliveryDateFormat = deliveryDateFormat;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
        this.invoiceCurrencyCode = invoiceCurrencyCode;
    }

    public void addPaymentMeans(String schemeAgencyID, String id, String iban, String accountname, String proprietaryID, String bic, String germanBankleitzahlID, String institutionname) {
        paymentMeansID.add(id);
        paymentMeansSchemeAgencyID.add(schemeAgencyID);
        paymentMeansPayeeAccountIBAN.add(iban);
        paymentMeansPayeeAccountName.add(accountname);
        paymentMeansPayeeAccountProprietaryID.add(proprietaryID);
        paymentMeansPayeeFinancialInstitutionBIC.add(bic);
        paymentMeansPayeeFinancialInstitutionGermanBankleitzahlID.add(germanBankleitzahlID);
        paymentMeansPayeeFinancialInstitutionName.add(institutionname);
    }

    public void addTaxCalculatedAmount(String calculatedAmount, String calculatedAmountCurrencyID, String typeCode,
            String basisAmount, String basisAmountCurrencyID, String applicablePercent) {
        taxCalculatedAmount.add(calculatedAmount);
        taxCalculatedAmountCurrencyID.add(calculatedAmountCurrencyID);
        taxTypeCode.add(typeCode);
        taxBasisAmount.add(basisAmount);
        taxBasisAmountCurrencyID.add(basisAmountCurrencyID);
        taxApplicablePercent.add(applicablePercent);
    }

    public void setLineTotalAmount(String lineTotalAmount) {
        this.lineTotalAmount = lineTotalAmount;
    }

    public void setLineTotalAmountCurrencyID(String lineTotalAmountCurrencyID) {
        this.lineTotalAmountCurrencyID = lineTotalAmountCurrencyID;
    }

    public void setChargeTotalAmount(String chargeTotalAmount) {
        this.chargeTotalAmount = chargeTotalAmount;
    }

    public void setChargeTotalAmountCurrencyID(String chargeTotalAmountCurrencyID) {
        this.chargeTotalAmountCurrencyID = chargeTotalAmountCurrencyID;
    }

    public void setAllowanceTotalAmount(String allowanceTotalAmount) {
        this.allowanceTotalAmount = allowanceTotalAmount;
    }

    public void setAllowanceTotalAmountCurrencyID(String allowanceTotalAmountCurrencyID) {
        this.allowanceTotalAmountCurrencyID = allowanceTotalAmountCurrencyID;
    }

    public void setTaxBasisTotalAmount(String taxBasisTotalAmount) {
        this.taxBasisTotalAmount = taxBasisTotalAmount;
    }

    public void setTaxBasisTotalAmountCurrencyID(String taxBasisTotalAmountCurrencyID) {
        this.taxBasisTotalAmountCurrencyID = taxBasisTotalAmountCurrencyID;
    }

    public void setTaxTotalAmount(String taxTotalAmount) {
        this.taxTotalAmount = taxTotalAmount;
    }

    public void setTaxTotalAmountCurrencyID(String taxTotalAmountCurrencyID) {
        this.taxTotalAmountCurrencyID = taxTotalAmountCurrencyID;
    }

    public void setGrandTotalAmount(String grandTotalAmount) {
        this.grandTotalAmount = grandTotalAmount;
    }

    public void setGrandTotalAmountCurrencyID(String grandTotalAmountCurrencyID) {
        this.grandTotalAmountCurrencyID = grandTotalAmountCurrencyID;
    }

    public void addIncludedSupplyChainTradeLineItem(String billedQuantity, String billedQuantityUnitCode, String specifiedTradeProductName) {
        this.billedQuantity.add(billedQuantity);
        this.billedQuantityUnitCode.add(billedQuantityUnitCode);
        this.specifiedTradeProductName.add(specifiedTradeProductName);
    }
    
}
