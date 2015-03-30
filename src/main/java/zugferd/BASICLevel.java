/*
 * Code written by Bruno Lowagie in the context of an example.
 */
package zugferd;

public interface BASICLevel {
    public boolean getTestIndicator();
    public String getIdentifier();
    public String getName();
    public String getTypeCode();
    public String getDateTime();
    public String getDateTimeFormat();
    public String[] getNotes();
    public String getSellerName();
    public String getSellerPostcode();
    public String getSellerLineOne();
    public String getSellerLineTwo();
    public String getSellerCityName();
    public String getSellerCountryID();
    public String[] getSellerTaxRegistrationID();
    public String[] getSellerTaxRegistrationShemeID();
    public String getBuyerName();
    public String getBuyerPostcode();
    public String getBuyerLineOne();
    public String getBuyerLineTwo();
    public String getBuyerCityName();
    public String getBuyerCountryID();
    public String[] getBuyerTaxRegistrationID();
    public String[] getBuyerTaxRegistrationShemeID();
    public String getDeliveryDateTime();
    public String getDeliveryDateTimeFormat();
    public String getPaymentReference();
    public String getInvoiceCurrencyCode();
    public String[] getPaymentMeansID();
    public String[] getPaymentMeansSchemeAgencyID();
    public String[] getPaymentMeansPayeeAccountIBAN();
    public String[] getPaymentMeansPayeeAccountAccountName();
    public String[] getPaymentMeansPayeeAccountProprietaryID();
    public String[] getPaymentMeansPayeeFinancialInstitutionBIC();
    public String[] getPaymentMeansPayeeFinancialInstitutionGermanBankleitzahlID();
    public String[] getPaymentMeansPayeeFinancialInstitutionName();
    public String[] getTaxCalculatedAmount();
    public String[] getTaxCalculatedAmountCurrencyID();
    public String[] getTaxTypeCode();
    public String[] getTaxBasisAmount();
    public String[] getTaxBasisAmountCurrencyID();
    public String[] getTaxApplicablePercent();
    public String getLineTotalAmount();
    public String getLineTotalAmountCurrencyID();
    public String getChargeTotalAmount();
    public String getChargeTotalAmountCurrencyID();
    public String getAllowanceTotalAmount();
    public String getAllowanceTotalAmountCurrencyID();
    public String getTaxBasisTotalAmount();
    public String getTaxBasisTotalAmountCurrencyID();
    public String getTaxTotalAmount();
    public String getTaxTotalAmountCurrencyID();
    public String getGrandTotalAmount();
    public String getGrandTotalAmountCurrencyID();
    public String getBilledQuantity();
    public String getBilledQuantityUnitCode();
    public String getSpecifiedTradeProductName();
    
}

