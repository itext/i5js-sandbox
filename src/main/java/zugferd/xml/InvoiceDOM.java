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

import zugferd.exceptions.DataIncompleteException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import zugferd.codes.CountryCode;
import zugferd.codes.CurrencyCode;
import zugferd.codes.DateFormatCode;
import zugferd.codes.DocumentTypeCode;
import zugferd.codes.FreeTextSubjectCode;
import zugferd.codes.GlobalIdentifierCode;
import zugferd.codes.MeasurementUnitCode;
import zugferd.codes.NumberChecker;
import zugferd.codes.PaymentMeansCode;
import zugferd.codes.TaxCategoryCode;
import zugferd.codes.TaxIDTypeCode;
import zugferd.codes.TaxTypeCode;
import zugferd.exceptions.InvalidCodeException;

/**
 * @author iText
 */
public final class InvoiceDOM {
    
    // Profiles that are supported:
    public static final String TEMPLATE = "resources/zugferd/zugferd-template.xml";
    
    public static final CountryCode COUNTRY_CODE = new CountryCode();
    public static final CurrencyCode CURR_CODE = new CurrencyCode();
    public static final DateFormatCode DF_CODE = new DateFormatCode();
    public static final GlobalIdentifierCode GI_CODE = new GlobalIdentifierCode();
    public static final MeasurementUnitCode M_UNIT_CODE = new MeasurementUnitCode();
    public static final NumberChecker DEC2 = new NumberChecker(NumberChecker.TWO_DECIMALS);
    public static final NumberChecker DEC4 = new NumberChecker(NumberChecker.FOUR_DECIMALS);
    public static final PaymentMeansCode PM_CODE = new PaymentMeansCode();
    public static final TaxCategoryCode TC_CODE = new TaxCategoryCode();
    public static final TaxIDTypeCode TIDT_CODE = new TaxIDTypeCode();
    public static final TaxTypeCode TT_CODE = new TaxTypeCode();
    
    // The DOM document
    private final Document doc;
    
    /**
     * Creates an object that will import data into an XML template.
     * @param data If this is an instance of BASICInvoice, the BASIC profile will be used;
     *             If this is an instance of COMFORTInvoice, the COMFORT profile will be used.
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @throws zugferd.exceptions.DataIncompleteException
     * @throws zugferd.exceptions.InvalidCodeException
     */
    public InvoiceDOM(BASICInvoice data)
            throws ParserConfigurationException, SAXException, IOException,
            DataIncompleteException, InvalidCodeException {
        // Loading the XML template
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	doc = docBuilder.parse(TEMPLATE);
        // importing the data
        importSpecifiedExchangedDocumentContext(data);
        importHeaderExchangedDocument(data);
        importSpecifiedSupplyChainTradeTransaction(data);
    }
    
    /**
     * Checks if a string is empty and throws a DataIncompleteException if so.
     * @param   s   the String to check
     * @param   message the message if an exception is thrown   
     */
    private void check(String s, String message) throws DataIncompleteException {
        if (s == null || s.trim().length() == 0)
            throw new DataIncompleteException(message);
    }
    
    /**
     * Imports the data for the following tag: rsm:SpecifiedExchangedDocumentContext
     * @param   data    the invoice data
     */
    private void importSpecifiedExchangedDocumentContext(BASICInvoice data) {
        Element element = (Element) doc.getElementsByTagName("rsm:SpecifiedExchangedDocumentContext").item(0);
        // TestIndicator (optional)
        setContent(element, "udt:Indicator", data.getTestIndicator() ? "true" : "false", null);
    }
    
    /**
     * Helper method to set the content of a tag.
     * @param parent    the parent element of the tag
     * @param tag       the tag for which we want to set the content
     * @param content   the new content for the tag
     * @param attributes    a sequence of attributes of which
     *                      the odd elements are keys, the even elements the
     *                      corresponding value.
     */
    private void setContent(Element parent, String tag, String content, String[] attributes) {
        Node node = parent.getElementsByTagName(tag).item(0);
        // content
        node.setTextContent(content);
        // attributes
        if (attributes == null || attributes.length == 0)
            return;
        int n = attributes.length;
        String attrName, attrValue;
        NamedNodeMap attrs = node.getAttributes();
        Node attr;
        for (int i = 0; i < n; i++) {
            attrName = attributes[i];
            if (++i == n) continue;
            attrValue = attributes[i];
            attr = attrs.getNamedItem(attrName);
            if (attr != null)
                attr.setTextContent(attrValue);
        }
    }
    
    /**
     * Imports the data for the following tag: rsm:HeaderExchangedDocument
     * @param   data    the invoice data
     */
    private void importHeaderExchangedDocument(BASICInvoice data)
            throws DataIncompleteException, InvalidCodeException {
        Element element = (Element) doc.getElementsByTagName("rsm:HeaderExchangedDocument").item(0);
        // ID (required)
        check(data.getId(), "HeaderExchangedDocument > ID");
        setContent(element, "ram:ID", data.getId(), null);
        // Name (required)
        check(data.getName(), "HeaderExchangedDocument > Name");
        setContent(element, "ram:Name", data.getName(), null);
        // TypeCode (required)
        DocumentTypeCode dtCode = new DocumentTypeCode(
                data instanceof COMFORTInvoice ? DocumentTypeCode.COMFORT : DocumentTypeCode.BASIC);
        setContent(element, "ram:TypeCode", dtCode.check(data.getTypeCode()), null);
        // IssueDateTime (required)
        check(data.getDateTimeFormat(), "HeaderExchangedDocument > DateTimeString");
        setDateTime(element, "udt:DateTimeString", data.getDateTimeFormat(), data.getDateTime());
        // IncludedNote (optional): header level
        String[][] notes = data.getNotes();
        String[] notesCodes = null;
        if (data instanceof COMFORTInvoice) {
            notesCodes = ((COMFORTInvoice)data).getNotesCodes();
        }
        setIncludedNotes(element, FreeTextSubjectCode.HEADER, notes, notesCodes);
    }
    
    /**
     * Set the content of a date tag along with the attribute that defines the format.
     * @param parent    the parent element that holds the date tag
     * @param tag       the date tag we want to change
     * @param dateTimeFormat    the format that will be used as an attribute
     * @param dateTime  the actual date
     * @throws zugferd.exceptions.InvalidCodeException
     */
    protected void setDateTime(Element parent, String tag, String dateTimeFormat, Date dateTime)
            throws InvalidCodeException {
        if (dateTimeFormat == null) return;
        setContent(parent, tag, DF_CODE.convertToString(dateTime, DF_CODE.check(dateTimeFormat)), new String[]{"format", dateTimeFormat});
    }
    
    /**
     * Includes notes and (in case of the COMFORT profile) the subject codes
     * for those notes.
     * @param parent    the parent element of the tag we want to change
     * @param level the level where the notices are added (header or line)
     * @param notes array of notes
     * @param notesCodes    array of codes for the notes.
     *          If not null, notes and notesCodes need to have an equal number of elements.
     * @throws zugferd.exceptions.DataIncompleteException
     * @throws zugferd.exceptions.InvalidCodeException
     */
    protected void setIncludedNotes(Element parent, int level, String notes[][], String[] notesCodes)
        throws DataIncompleteException, InvalidCodeException {
        Node includedNoteNode = parent.getElementsByTagName("ram:IncludedNote").item(0);
        int n = notes.length;
        FreeTextSubjectCode ftsCode = new FreeTextSubjectCode(level);
        if (notesCodes != null && n != notesCodes.length)
            throw new DataIncompleteException("Number of included notes is not equal to number of codes for included notes.");
        for (int i = 0; i < n; i++) {
            Element noteNode = (Element)includedNoteNode.cloneNode(true);
            Node content = noteNode.getElementsByTagName("ram:Content").item(0);
            for (String note : notes[i]) {
                Node newNode = content.cloneNode(true);
                newNode.setTextContent(note);
                noteNode.insertBefore(newNode, content);
            }
            if (notesCodes != null) {
                Node code = noteNode.getElementsByTagName("ram:SubjectCode").item(0);
                code.setTextContent(ftsCode.check(notesCodes[i]));
            }
            parent.insertBefore(noteNode, includedNoteNode);
        }
    }
    /**
     * Imports the data for the following tag: rsm:SpecifiedSupplyChainTradeTransaction
     * @param   data    the invoice data
     */
    private void importSpecifiedSupplyChainTradeTransaction(BASICInvoice data)
        throws DataIncompleteException, InvalidCodeException {
        Element element = (Element) doc.getElementsByTagName("rsm:SpecifiedSupplyChainTradeTransaction").item(0);
        
        /* ram:ApplicableSupplyChainTradeAgreement */

        // buyer reference (optional; comfort only)
        if (data instanceof COMFORTInvoice) {
            String buyerReference = ((COMFORTInvoice) data).getBuyerReference();
            setContent(element, "ram:BuyerReference", buyerReference, null);
        }
        // SellerTradeParty (required)
        check(data.getSellerName(), "SpecifiedSupplyChainTradeTransaction > ApplicableSupplyChainTradeAgreement > SellerTradeParty > Name");
        setSellerTradeParty(element, data);
        // BuyerTradeParty (required)
        check(data.getBuyerName(), "SpecifiedSupplyChainTradeTransaction > ApplicableSupplyChainTradeAgreement > BuyerTradeParty > Name");
        setBuyerTradeParty(element, data);
        
        /* ram:ApplicableSupplyChainTradeDelivery */
        
        if (data instanceof COMFORTInvoice) {
            COMFORTInvoice cData = (COMFORTInvoice)data;
            // BuyerOrderReferencedDocument (optional)
            Element document = (Element)element.getElementsByTagName("ram:BuyerOrderReferencedDocument").item(0);
            setDateTime(document, "ram:IssueDateTime", cData.getBuyerOrderReferencedDocumentIssueDateTimeFormat(), cData.getBuyerOrderReferencedDocumentIssueDateTime());
            setContent(document, "ram:ID", cData.getBuyerOrderReferencedDocumentID(), null);
            // ContractReferencedDocument (optional)
            document = (Element)element.getElementsByTagName("ram:ContractReferencedDocument").item(0);
            setDateTime(document, "ram:IssueDateTime", cData.getContractReferencedDocumentIssueDateTimeFormat(), cData.getContractReferencedDocumentIssueDateTime());
            setContent(document, "ram:ID", cData.getContractReferencedDocumentID(), null);
            // CustomerOrderReferencedDocument (optional)
            document = (Element)element.getElementsByTagName("ram:CustomerOrderReferencedDocument").item(0);
            setDateTime(document, "ram:IssueDateTime", cData.getCustomerOrderReferencedDocumentIssueDateTimeFormat(), cData.getCustomerOrderReferencedDocumentIssueDateTime());
            setContent(document, "ram:ID", cData.getCustomerOrderReferencedDocumentID(), null);
        }
        
        /* ram:ApplicableSupplyChainTradeDelivery */
        
        // ActualDeliverySupplyChainEvent (optional)
        Element parent = (Element)element.getElementsByTagName("ram:ActualDeliverySupplyChainEvent").item(0);
        setDateTime(parent, "udt:DateTimeString", data.getDeliveryDateTimeFormat(), data.getDeliveryDateTime());
        // DeliveryNoteReferencedDocument (optional)
        if (data instanceof COMFORTInvoice) {
            COMFORTInvoice cData = (COMFORTInvoice)data;
            Element document = (Element)element.getElementsByTagName("ram:DeliveryNoteReferencedDocument").item(0);
            setDateTime(document, "ram:IssueDateTime", cData.getDeliveryNoteReferencedDocumentIssueDateTimeFormat(), cData.getDeliveryNoteReferencedDocumentIssueDateTime());
            setContent(document, "ram:ID", cData.getDeliveryNoteReferencedDocumentID(), null);
        }
        
        /* ram:ApplicableSupplyChainTradeSettlement */
        
        // ram:PaymentReference (optional)
        setContent(element, "ram:PaymentReference", data.getPaymentReference(), null);
        // ram:InvoiceCurrencyCode (required)
        setContent(element, "ram:InvoiceCurrencyCode", CURR_CODE.check(data.getInvoiceCurrencyCode()), null);
        // ram:InvoiceeTradeParty (optional)
        if (data instanceof COMFORTInvoice) {
            setInvoiceeTradeParty(element, (COMFORTInvoice)data);
        }
        
        // ram:SpecifiedTradeSettlementPaymentMeans
        parent = (Element)element.getElementsByTagName("ram:ApplicableSupplyChainTradeSettlement").item(0);
        processPaymentMeans(parent, data);
        
        // ram:ApplicableTradeTax
        processTax(parent, data);
        
        if (data instanceof COMFORTInvoice) {
            
            // ram:BillingSpecifiedPeriod
            
            COMFORTInvoice cData = (COMFORTInvoice)data;
            Element period = (Element)element.getElementsByTagName("ram:BillingSpecifiedPeriod").item(0);
            Element start = (Element)period.getElementsByTagName("ram:StartDateTime").item(0);
            setDateTime(start, "udt:DateTimeString", cData.getBillingStartDateTimeFormat(), cData.getBillingStartDateTime());
            // ContractReferencedDocument (optional)
            Element end = (Element)period.getElementsByTagName("ram:EndDateTime").item(0);
            setDateTime(end, "udt:DateTimeString", cData.getBillingEndDateTimeFormat(), cData.getBillingEndDateTime());
            
            // ram:SpecifiedTradeAllowanceCharge
            processSpecifiedTradeAllowanceCharge(parent, cData);
            
            // ram:SpecifiedLogisticsServiceCharge
            processSpecifiedLogisticsServiceCharge(parent, cData);
            
            // ram:SpecifiedTradePaymentTerms
            processSpecifiedTradePaymentTerms(parent, cData);
        }
        
        // ram:SpecifiedTradeSettlementMonetarySummation
        check(DEC2.check(data.getLineTotalAmount()), "SpecifiedTradeSettlementMonetarySummation > LineTotalAmount");
        check(CURR_CODE.check(data.getLineTotalAmountCurrencyID()), "SpecifiedTradeSettlementMonetarySummation > LineTotalAmount . currencyID");
        setContent(element, "ram:LineTotalAmount", data.getLineTotalAmount(), new String[]{"currencyID", data.getLineTotalAmountCurrencyID()});
        check(DEC2.check(data.getChargeTotalAmount()), "SpecifiedTradeSettlementMonetarySummation > ChargeTotalAmount");
        check(CURR_CODE.check(data.getChargeTotalAmountCurrencyID()), "SpecifiedTradeSettlementMonetarySummation > ChargeTotalAmount . currencyID");
        setContent(element, "ram:ChargeTotalAmount", data.getChargeTotalAmount(), new String[]{"currencyID", data.getChargeTotalAmountCurrencyID()});
        check(DEC2.check(data.getAllowanceTotalAmount()), "SpecifiedTradeSettlementMonetarySummation > AllowanceTotalAmount");
        check(CURR_CODE.check(data.getAllowanceTotalAmountCurrencyID()), "SpecifiedTradeSettlementMonetarySummation > AllowanceTotalAmount . currencyID");
        setContent(element, "ram:AllowanceTotalAmount", data.getAllowanceTotalAmount(), new String[]{"currencyID", data.getAllowanceTotalAmountCurrencyID()});
        check(DEC2.check(data.getTaxBasisTotalAmount()), "SpecifiedTradeSettlementMonetarySummation > TaxBasisTotalAmount");
        check(CURR_CODE.check(data.getTaxBasisTotalAmountCurrencyID()), "SpecifiedTradeSettlementMonetarySummation > TaxBasisTotalAmount . currencyID");
        setContent(element, "ram:TaxBasisTotalAmount", data.getTaxBasisTotalAmount(), new String[]{"currencyID", data.getTaxBasisTotalAmountCurrencyID()});
        check(DEC2.check(data.getTaxTotalAmount()), "SpecifiedTradeSettlementMonetarySummation > TaxTotalAmount");
        check(CURR_CODE.check(data.getTaxTotalAmountCurrencyID()), "SpecifiedTradeSettlementMonetarySummation > TaxTotalAmount . currencyID");
        setContent(element, "ram:TaxTotalAmount", data.getTaxTotalAmount(), new String[]{"currencyID", data.getTaxTotalAmountCurrencyID()});
        check(DEC2.check(data.getGrandTotalAmount()), "SpecifiedTradeSettlementMonetarySummation > GrandTotalAmount");
        check(CURR_CODE.check(data.getGrandTotalAmountCurrencyID()), "SpecifiedTradeSettlementMonetarySummation > GrandTotalAmount . currencyID");
        setContent(element, "ram:GrandTotalAmount", data.getGrandTotalAmount(), new String[]{"currencyID", data.getGrandTotalAmountCurrencyID()});
        if (data instanceof COMFORTInvoice) {
            COMFORTInvoice cData = (COMFORTInvoice)data;
            setContent(element, "ram:TotalPrepaidAmount", cData.getTotalPrepaidAmount(), new String[]{"currencyID", cData.getTotalPrepaidAmountCurrencyID()});
            setContent(element, "ram:DuePayableAmount", cData.getDuePayableAmount(), new String[]{"currencyID", cData.getDuePayableAmountCurrencyID()});
        }
        
        /* ram:IncludedSupplyChainTradeLineItem */
        if (data instanceof COMFORTInvoice)
            processLinesComfort(element, (COMFORTInvoice)data);
        else
            processLinesBasic(element, data);
    }
    
    private void setSellerTradeParty(Element parent, BASICInvoice data)
        throws DataIncompleteException, InvalidCodeException {
        String id = null;
        String[] globalID = null;
        String[] globalIDScheme = null;
        if (data instanceof COMFORTInvoice) {
            id = ((COMFORTInvoice)data).getSellerID();
            globalID = ((COMFORTInvoice)data).getSellerGlobalID();
            globalIDScheme = ((COMFORTInvoice)data).getSellerGlobalSchemeID();
        }
        String name = data.getSellerName();
        String postcode = data.getSellerPostcode();
        String lineOne = data.getSellerLineOne();
        String lineTwo = data.getSellerLineTwo();
        String cityName = data.getSellerCityName();
        String countryID = data.getSellerCountryID();
        String[] taxRegistrationID = data.getSellerTaxRegistrationID();
        String[] taxRegistrationSchemeID = data.getSellerTaxRegistrationSchemeID();
        processTradeParty(parent, "ram:SellerTradeParty", id, globalID, globalIDScheme,
                name, postcode, lineOne, lineTwo, cityName, countryID,
                taxRegistrationID, taxRegistrationSchemeID);
    }
    
    private void setBuyerTradeParty(Element parent, BASICInvoice data)
        throws DataIncompleteException, InvalidCodeException {
        String id = null;
        String[] globalID = null;
        String[] globalIDScheme = null;
        if (data instanceof COMFORTInvoice) {
            id = ((COMFORTInvoice)data).getBuyerID();
            globalID = ((COMFORTInvoice)data).getBuyerGlobalID();
            globalIDScheme = ((COMFORTInvoice)data).getBuyerGlobalSchemeID();
        }
        String name = data.getBuyerName();
        String postcode = data.getBuyerPostcode();
        String lineOne = data.getBuyerLineOne();
        String lineTwo = data.getBuyerLineTwo();
        String cityName = data.getBuyerCityName();
        String countryID = data.getBuyerCountryID();
        String[] taxRegistrationID = data.getBuyerTaxRegistrationID();
        String[] taxRegistrationSchemeID = data.getBuyerTaxRegistrationSchemeID();
        processTradeParty(parent, "ram:BuyerTradeParty", id, globalID, globalIDScheme,
                name, postcode, lineOne, lineTwo, cityName, countryID,
                taxRegistrationID, taxRegistrationSchemeID);
    }
    
    private void setInvoiceeTradeParty(Element parent, COMFORTInvoice data)
        throws DataIncompleteException, InvalidCodeException {
        String name = data.getInvoiceeName();
        if (name == null) return;
        String id = data.getInvoiceeID();
        String[] globalID = data.getInvoiceeGlobalID();
        String[] globalIDScheme = data.getInvoiceeGlobalSchemeID();
        String postcode = data.getInvoiceePostcode();
        String lineOne = data.getInvoiceeLineOne();
        String lineTwo = data.getInvoiceeLineTwo();
        String cityName = data.getInvoiceeCityName();
        String countryID = data.getInvoiceeCountryID();
        String[] taxRegistrationID = data.getInvoiceeTaxRegistrationID();
        String[] taxRegistrationSchemeID = data.getInvoiceeTaxRegistrationSchemeID();
        processTradeParty(parent, "ram:InvoiceeTradeParty", id, globalID, globalIDScheme,
                name, postcode, lineOne, lineTwo, cityName, countryID,
                taxRegistrationID, taxRegistrationSchemeID);
    }
    
    private void processTradeParty(Element element, String tagname,
        String id, String[] globalID, String[] globalIDScheme,
        String name, String postcode, String lineOne, String lineTwo,
        String cityName, String countryID,
        String[] taxRegistrationID, String[] taxRegistrationSchemeID)
        throws DataIncompleteException, InvalidCodeException {
        Element party = (Element) element.getElementsByTagName(tagname).item(0);
        Node node;
        if (id != null) {
            node = party.getElementsByTagName("ram:ID").item(0);
            node.setTextContent(id);
        }
        if (globalID != null) {
            int n = globalID.length;
            if (globalIDScheme == null || globalIDScheme.length != n)
                throw new DataIncompleteException("Number of global ID schemes is not equal to number of global IDs.");
            node = party.getElementsByTagName("ram:GlobalID").item(0);
            for (int i = 0; i < n; i++) {
                Element idNode = (Element)node.cloneNode(true);
                NamedNodeMap attrs = idNode.getAttributes();
                idNode.setTextContent(globalID[i]);
                Node schemeID = attrs.getNamedItem("schemeID");
                schemeID.setTextContent(GI_CODE.check(globalIDScheme[i]));
                party.insertBefore(idNode, node);
            }
        }
        setContent(party, "ram:Name", name, null);
        setContent(party, "ram:PostcodeCode", postcode, null);
        setContent(party, "ram:LineOne", lineOne, null);
        setContent(party, "ram:LineTwo", lineTwo, null);
        setContent(party, "ram:CityName", cityName, null);
        if (countryID != null) {
            setContent(party, "ram:CountryID", COUNTRY_CODE.check(countryID), null);
        }
        int n = taxRegistrationID.length;
        if (taxRegistrationSchemeID != null && taxRegistrationSchemeID.length != n)
            throw new DataIncompleteException("Number of tax ID schemes is not equal to number of tax IDs.");
        Element tax = (Element) party.getElementsByTagName("ram:SpecifiedTaxRegistration").item(0);
        node = tax.getElementsByTagName("ram:ID").item(0);
        for (int i = 0; i < n; i++) {
            Element idNode = (Element)node.cloneNode(true);
            idNode.setTextContent(taxRegistrationID[i]);
            NamedNodeMap attrs = idNode.getAttributes();
            Node schemeID = attrs.getNamedItem("schemeID");
            schemeID.setTextContent(TIDT_CODE.check(taxRegistrationSchemeID[i]));
            tax.insertBefore(idNode, node);
        }           
    }
    
    private void processPaymentMeans(Element parent, BASICInvoice data) throws InvalidCodeException {
        String[] pmID = data.getPaymentMeansID();
        int n = pmID.length;
        String[] pmTypeCode = new String[n];
        String[][] pmInformation = new String[n][];
        String[] pmSchemeAgencyID = data.getPaymentMeansSchemeAgencyID();
        String[] pmPayerIBAN = new String[n];
        String[] pmPayerProprietaryID = new String[n];
        String[] pmIBAN = data.getPaymentMeansPayeeAccountIBAN();
        String[] pmAccountName = data.getPaymentMeansPayeeAccountAccountName();
        String[] pmAccountID = data.getPaymentMeansPayeeAccountProprietaryID();
        String[] pmPayerBIC = new String[n];
        String[] pmPayerGermanBankleitzahlID = new String[n];
        String[] pmPayerFinancialInst = new String[n];
        String[] pmBIC = data.getPaymentMeansPayeeFinancialInstitutionBIC();
        String[] pmGermanBankleitzahlID = data.getPaymentMeansPayeeFinancialInstitutionGermanBankleitzahlID();
        String[] pmFinancialInst = data.getPaymentMeansPayeeFinancialInstitutionName();
        if (data instanceof COMFORTInvoice) {
            COMFORTInvoice cData = (COMFORTInvoice)data;
            pmTypeCode = cData.getPaymentMeansTypeCode();
            pmInformation = cData.getPaymentMeansInformation();
            pmPayerIBAN = cData.getPaymentMeansPayerAccountIBAN();
            pmPayerProprietaryID = cData.getPaymentMeansPayerAccountProprietaryID();
            pmPayerBIC = cData.getPaymentMeansPayerFinancialInstitutionBIC();
            pmPayerGermanBankleitzahlID = cData.getPaymentMeansPayerFinancialInstitutionGermanBankleitzahlID();
            pmPayerFinancialInst = cData.getPaymentMeansPayerFinancialInstitutionName();
        }
        Node node = parent.getElementsByTagName("ram:SpecifiedTradeSettlementPaymentMeans").item(0);
        for (int i = 0; i < pmID.length; i++) {
            Node newNode = node.cloneNode(true);
            processPaymentMeans((Element)newNode, data,
                    pmTypeCode[i],
                    pmInformation[i],
                    pmID[i],
                    pmSchemeAgencyID[i],
                    pmPayerIBAN[i],
                    pmPayerProprietaryID[i],
                    pmIBAN[i],
                    pmAccountName[i],
                    pmAccountID[i],
                    pmPayerBIC[i],
                    pmPayerGermanBankleitzahlID[i],
                    pmPayerFinancialInst[i],
                    pmBIC[i],
                    pmGermanBankleitzahlID[i],
                    pmFinancialInst[i]
            );
            parent.insertBefore(newNode, node);
        }
    }
    
    private void processPaymentMeans(Element parent, BASICInvoice data,
        String typeCode, String[] information,
        String id, String scheme,
        String payerIban, String payerProprietaryID,
        String iban, String accName, String accID,
        String payerBic, String payerBank, String payerInst,
        String bic, String bank, String inst) throws InvalidCodeException {
        if (typeCode != null) {
            setContent(parent, "ram:TypeCode", PM_CODE.check(typeCode), null);
        }
        if (information != null) {
            Node node = parent.getElementsByTagName("ram:Information").item(0);
            for (String info : information) {
                Node newNode = node.cloneNode(true);
                newNode.setTextContent(info);
                parent.insertBefore(newNode, node);
            }
        }
        setContent(parent, "ram:ID", id, new String[]{"schemeAgencyID", scheme});
        Element payer = (Element)parent.getElementsByTagName("ram:PayerPartyDebtorFinancialAccount").item(0);
        setContent(payer, "ram:IBANID", payerIban, null);
        setContent(payer, "ram:ProprietaryID", payerProprietaryID, null);
        Element payee = (Element)parent.getElementsByTagName("ram:PayeePartyCreditorFinancialAccount").item(0);
        setContent(payee, "ram:IBANID", iban, null);
        setContent(payee, "ram:AccountName", accName, null);
        setContent(payee, "ram:ProprietaryID", accID, null);
        payer = (Element)parent.getElementsByTagName("ram:PayerSpecifiedDebtorFinancialInstitution").item(0);
        setContent(payer, "ram:BICID", payerBic, null);
        setContent(payer, "ram:GermanBankleitzahlID", payerBank, null);
        setContent(payer, "ram:Name", payerInst, null);
        payee = (Element)parent.getElementsByTagName("ram:PayeeSpecifiedCreditorFinancialInstitution").item(0);
        setContent(payee, "ram:BICID", bic, null);
        setContent(payee, "ram:GermanBankleitzahlID", bank, null);
        setContent(payee, "ram:Name", inst, null);
    }
    
    private void processTax(Element parent, BASICInvoice data) throws InvalidCodeException, DataIncompleteException {
        String[] calculated = data.getTaxCalculatedAmount();
        int n = calculated.length;
        String[] calculatedCurr = data.getTaxCalculatedAmountCurrencyID();
        String[] typeCode = data.getTaxTypeCode();
        String[] exemptionReason = new String[n];
        String[] basisAmount = data.getTaxBasisAmount();
        String[] basisAmountCurr = data.getTaxBasisAmountCurrencyID();
        String[] category = new String[n];
        String[] percent = data.getTaxApplicablePercent();
        if (data instanceof COMFORTInvoice) {
            COMFORTInvoice cData = (COMFORTInvoice)data;
            exemptionReason = cData.getTaxExemptionReason();
            category = cData.getTaxCategoryCode();
        }
        Node node = parent.getElementsByTagName("ram:ApplicableTradeTax").item(0);
        for (int i = 0; i < n; i++) {
            Node newNode = node.cloneNode(true);
            processTax((Element)newNode, calculated[i], calculatedCurr[i], typeCode[i],
                exemptionReason[i], basisAmount[i], basisAmountCurr[i],
                category[i], percent[i]);
            parent.insertBefore(newNode, node);
        }
    }
    
    private void processTax(Element parent,
        String calculatedAmount, String currencyID, String typeCode,
        String exemptionReason, String basisAmount, String basisAmountCurr,
        String category, String percent)
            throws InvalidCodeException, DataIncompleteException {
        // Calculated amount (required; 2 decimals)
        check(CURR_CODE.check(currencyID), "ApplicableTradeTax > CalculatedAmount > CurrencyID");
        setContent(parent, "ram:CalculatedAmount", DEC2.check(calculatedAmount), new String[]{"currencyID", currencyID});
        // TypeCode (required)
        check(typeCode, "ApplicableTradeTax > TypeCode");
        setContent(parent, "ram:TypeCode", TT_CODE.check(typeCode), null);
        // exemption reason (optional)
        setContent(parent, "ram:ExemptionReason", exemptionReason, null);
        // basis amount (required, 2 decimals)
        check(CURR_CODE.check(basisAmountCurr), "ApplicableTradeTax > BasisAmount > CurrencyID");
        setContent(parent, "ram:BasisAmount", DEC2.check(basisAmount), new String[]{"currencyID", basisAmountCurr});
        // Category code (optional)
        if (category != null) {
            setContent(parent, "ram:CategoryCode", TC_CODE.check(category), null);
        }
        // Applicable percent (required; 2 decimals)
        setContent(parent, "ram:ApplicablePercent", DEC2.check(percent), null);
    }
    
    private void processSpecifiedTradeAllowanceCharge(Element parent, COMFORTInvoice data)
            throws InvalidCodeException {
        Boolean[] indicator = data.getSpecifiedTradeAllowanceChargeIndicator();
        String[] actualAmount = data.getSpecifiedTradeAllowanceChargeActualAmount();
        String[] actualAmountCurr = data.getSpecifiedTradeAllowanceChargeActualAmountCurrency();
        String[] reason = data.getSpecifiedTradeAllowanceChargeReason();
        String[][] typeCode = data.getSpecifiedTradeAllowanceChargeTaxTypeCode();
        String[][] categoryCode = data.getSpecifiedTradeAllowanceChargeTaxCategoryCode();
        String[][] percent = data.getSpecifiedTradeAllowanceChargeTaxApplicablePercent();
        Node node = (Element)parent.getElementsByTagName("ram:SpecifiedTradeAllowanceCharge").item(0);
        for (int i = 0; i < indicator.length; i++) {
            Node newNode = node.cloneNode(true);
            processSpecifiedTradeAllowanceCharge((Element)newNode, indicator[i],
                actualAmount[i], actualAmountCurr[i], reason[i],
                typeCode[i], categoryCode[i], percent[i]);
            parent.insertBefore(newNode, node);
        }
    }
    
    private void processSpecifiedTradeAllowanceCharge(Element parent, boolean indicator,
        String actualAmount, String actualAmountCurrency, String reason,
        String[] typeCode, String[] categoryCode, String[] percent) throws InvalidCodeException {
        setContent(parent, "udt:Indicator", indicator ? "true" : "false", null);
        setContent(parent, "ram:ActualAmount", DEC4.check(actualAmount), new String[]{"currencyID", CURR_CODE.check(actualAmountCurrency)});
        setContent(parent, "ram:Reason", reason, null);
        Node node = parent.getElementsByTagName("ram:CategoryTradeTax").item(0);
        for (int i = 0; i < typeCode.length; i++) {
            Element newNode = (Element) node.cloneNode(true);
            setContent(newNode, "ram:TypeCode", TT_CODE.check(typeCode[i]), null);
            setContent(newNode, "ram:CategoryCode", TC_CODE.check(categoryCode[i]), null);
            setContent(newNode, "ram:ApplicablePercent", DEC2.check(percent[i]), null);
            parent.insertBefore(newNode, node);
        }
    }
    
    private void processSpecifiedLogisticsServiceCharge(Element parent, COMFORTInvoice data)
        throws InvalidCodeException {
        String[][] description = data.getSpecifiedLogisticsServiceChargeDescription();
        String[] appliedAmount = data.getSpecifiedLogisticsServiceChargeAmount();
        String[] appliedAmountCurr = data.getSpecifiedLogisticsServiceChargeAmountCurrency();
        String[][] typeCode = data.getSpecifiedLogisticsServiceChargeTaxTypeCode();
        String[][] categoryCode = data.getSpecifiedLogisticsServiceChargeTaxCategoryCode();
        String[][] percent = data.getSpecifiedLogisticsServiceChargeTaxApplicablePercent();
        Node node = parent.getElementsByTagName("ram:SpecifiedLogisticsServiceCharge").item(0);
        for (int i = 0; i < appliedAmount.length; i++) {
            Node newNode = node.cloneNode(true);
            processSpecifiedLogisticsServiceCharge((Element)newNode,
                description[i], appliedAmount[i], appliedAmountCurr[i],
                typeCode[i], categoryCode[i], percent[i]);
            parent.insertBefore(newNode, node);
        }
    }
    
    private void processSpecifiedLogisticsServiceCharge(Element parent,
            String[] description, String appliedAmount, String currencyID,
            String[] typeCode, String[] categoryCode, String[] percent)
        throws InvalidCodeException {
        Node node = parent.getElementsByTagName("ram:Description").item(0);
        for (String d : description) {
            Node newNode = node.cloneNode(true);
            newNode.setTextContent(d);
            parent.insertBefore(newNode, node);
        }
        setContent(parent, "ram:AppliedAmount", DEC4.check(appliedAmount), new String[]{"currencyID", CURR_CODE.check(currencyID)});
        node = parent.getElementsByTagName("ram:AppliedTradeTax").item(0);
        for (int i = 0; i < typeCode.length; i++) {
            Element newNode = (Element) node.cloneNode(true);
            setContent(newNode, "ram:TypeCode", TT_CODE.check(typeCode[i]), null);
            setContent(newNode, "ram:CategoryCode", TC_CODE.check(categoryCode[i]), null);
            setContent(newNode, "ram:ApplicablePercent", DEC2.check(percent[i]), null);
            parent.insertBefore(newNode, node);
        }
    }
    
    private void processSpecifiedTradePaymentTerms(Element parent, COMFORTInvoice data)
            throws InvalidCodeException {
        String[][] description = data.getSpecifiedTradePaymentTermsDescription();
        Date[] dateTime = data.getSpecifiedTradePaymentTermsDueDateTime();
        String[] dateTimeFormat = data.getSpecifiedTradePaymentTermsDueDateTimeFormat();
        Node node = parent.getElementsByTagName("ram:SpecifiedTradePaymentTerms").item(0);
        for (int i = 0; i < description.length; i++) {
            Node newNode = node.cloneNode(true);
            processSpecifiedTradePaymentTerms((Element)newNode,
                    description[i], dateTime[i], dateTimeFormat[i]);
            parent.insertBefore(newNode, node);
        }
    }
    
    private void processSpecifiedTradePaymentTerms(Element parent,
            String[] description, Date dateTime, String dateTimeFormat) throws InvalidCodeException {
        Node node = parent.getElementsByTagName("ram:Description").item(0);
        for (String d : description) {
            Node newNode = node.cloneNode(true);
            newNode.setTextContent(d);
            parent.insertBefore(newNode, node);
        }
        if (dateTimeFormat != null)
            setDateTime(parent, "udt:DateTimeString", dateTimeFormat, dateTime);
    }
    
    private void processLinesComfort(Element parent, COMFORTInvoice data)
            throws DataIncompleteException, InvalidCodeException {
        String[] lineIDs = data.getLineItemLineID();
        if (lineIDs.length == 0)
            throw new DataIncompleteException("You can create an invoice without any line items");
        String[][][] includedNote = data.getLineItemIncludedNote();
        String[] grossPriceChargeAmount = data.getLineItemGrossPriceChargeAmount();
        String[] grossPriceChargeAmountCurrencyID = data.getLineItemGrossPriceChargeAmountCurrencyID();
        String[] grossPriceBasisQuantity = data.getLineItemGrossPriceBasisQuantity();
        String[] grossPriceBasisQuantityCode = data.getLineItemGrossPriceBasisQuantityCode();
        Boolean[][] grossPriceTradeAllowanceChargeIndicator = data.getLineItemGrossPriceTradeAllowanceChargeIndicator();
        String[][] grossPriceTradeAllowanceChargeActualAmount = data.getLineItemGrossPriceTradeAllowanceChargeActualAmount();
        String[][] grossPriceTradeAllowanceChargeActualAmountCurrencyID = data.getLineItemGrossPriceTradeAllowanceChargeActualAmountCurrencyID();
        String[][] grossPriceTradeAllowanceChargeReason = data.getLineItemGrossPriceTradeAllowanceChargeReason();
        String[] netPriceChargeAmount = data.getLineItemNetPriceChargeAmount();
        String[] netPriceChargeAmountCurrencyID = data.getLineItemNetPriceChargeAmountCurrencyID();
        String[] netPriceBasisQuantity = data.getLineItemNetPriceBasisQuantity();
        String[] netPriceBasisQuantityCode = data.getLineItemNetPriceBasisQuantityCode();
        String[] billedQuantity = data.getLineItemBilledQuantity(); // BASIC
        String[] billedQuantityUnitCode = data.getLineItemBilledQuantityUnitCode();
        String[][] settlementTaxTypeCode = data.getLineItemSettlementTaxTypeCode();
        String[][] settlementTaxExemptionReason = data.getLineItemSettlementTaxExemptionReason();
        String[][] settlementTaxCategoryCode = data.getLineItemSettlementTaxCategoryCode();
        String[][] settlementTaxApplicablePercent = data.getLineItemSettlementTaxApplicablePercent();
        String[] totalAmount = data.getLineItemLineTotalAmount();
        String[] totalAmountCurrencyID = data.getLineItemLineTotalAmountCurrencyID();
        String[] specifiedTradeProductGlobalID = data.getLineItemSpecifiedTradeProductGlobalID();
        String[] specifiedTradeProductSchemeID = data.getLineItemSpecifiedTradeProductSchemeID();
        String[] specifiedTradeProductSellerAssignedID = data.getLineItemSpecifiedTradeProductSellerAssignedID();
        String[] specifiedTradeProductBuyerAssignedID = data.getLineItemSpecifiedTradeProductBuyerAssignedID();
        String[] specifiedTradeProductName = data.getLineItemSpecifiedTradeProductName(); // BASIC
        String[] specifiedTradeProductDescription = data.getLineItemSpecifiedTradeProductDescription();
        Node node = parent.getElementsByTagName("ram:IncludedSupplyChainTradeLineItem").item(0);
        for (int i = 0; i < lineIDs.length; i++) {
            Node newNode = node.cloneNode(true);
            processLine((Element)newNode, lineIDs[i], includedNote[i],
                    grossPriceChargeAmount[i], grossPriceChargeAmountCurrencyID[i],
                    grossPriceBasisQuantity[i], grossPriceBasisQuantityCode[i],
                    grossPriceTradeAllowanceChargeIndicator[i],
                    grossPriceTradeAllowanceChargeActualAmount[i],
                    grossPriceTradeAllowanceChargeActualAmountCurrencyID[i],
                    grossPriceTradeAllowanceChargeReason[i],
                    netPriceChargeAmount[i], netPriceChargeAmountCurrencyID[i],
                    netPriceBasisQuantity[i], netPriceBasisQuantityCode[i],
                    billedQuantity[i], billedQuantityUnitCode[i],
                    settlementTaxTypeCode[i], settlementTaxExemptionReason[i],
                    settlementTaxCategoryCode[i], settlementTaxApplicablePercent[i],
                    totalAmount[i], totalAmountCurrencyID[i],
                    specifiedTradeProductGlobalID[i], specifiedTradeProductSchemeID[i],
                    specifiedTradeProductSellerAssignedID[i], specifiedTradeProductBuyerAssignedID[i],
                    specifiedTradeProductName[i], specifiedTradeProductDescription[i]
            );
            parent.insertBefore(newNode, node);
        }
    }
    
    protected void processLine(Element parent, String lineID, String[][] note,
        String grossPriceChargeAmount, String grossPriceChargeAmountCurrencyID,
        String grossPriceBasisQuantity, String grossPriceBasisQuantityCode,
        Boolean[] grossPriceTradeAllowanceChargeIndicator,
        String[] grossPriceTradeAllowanceChargeActualAmount,
        String[] grossPriceTradeAllowanceChargeActualAmountCurrencyID,
        String[] grossPriceTradeAllowanceChargeReason,
        String netPriceChargeAmount, String netPriceChargeAmountCurrencyID,
        String netPriceBasisQuantity, String netPriceBasisQuantityCode,
        String billedQuantity, String billedQuantityCode,
        String[] settlementTaxTypeCode, String[] settlementTaxExemptionReason,
        String[] settlementTaxCategoryCode, String[] settlementTaxApplicablePercent,
        String totalAmount, String totalAmountCurrencyID,
        String specifiedTradeProductGlobalID, String specifiedTradeProductSchemeID,
        String specifiedTradeProductSellerAssignedID, String specifiedTradeProductBuyerAssignedID,
        String specifiedTradeProductName, String specifiedTradeProductDescription)
            throws DataIncompleteException, InvalidCodeException {

        /* ram:AssociatedDocumentLineDocument */
        Element sub = (Element)parent.getElementsByTagName("ram:AssociatedDocumentLineDocument").item(0);
        setContent(sub, "ram:LineID", lineID, null);
        setIncludedNotes(sub, FreeTextSubjectCode.LINE, note, null);

        /* ram:SpecifiedSupplyChainTradeAgreement */
        // ram:GrossPriceProductTradePrice
        if (grossPriceChargeAmount != null) {
            sub = (Element)parent.getElementsByTagName("ram:GrossPriceProductTradePrice").item(0);
            setContent(sub, "ram:ChargeAmount", DEC4.check(grossPriceChargeAmount),
                    new String[]{"currencyID", CURR_CODE.check(grossPriceChargeAmountCurrencyID)});
            if (grossPriceBasisQuantity != null)
                setContent(sub, "ram:BasisQuantity", DEC4.check(grossPriceBasisQuantity),
                        new String[]{"unitCode", M_UNIT_CODE.check(grossPriceBasisQuantityCode)});
            Node node = sub.getElementsByTagName("ram:AppliedTradeAllowanceCharge").item(0);
            for (int i = 0; i < grossPriceTradeAllowanceChargeIndicator.length; i++) {
                Node newNode = node.cloneNode(true);
                processAppliedTradeAllowanceCharge((Element)newNode,
                        grossPriceTradeAllowanceChargeIndicator[i],
                        grossPriceTradeAllowanceChargeActualAmount[i],
                        grossPriceTradeAllowanceChargeActualAmountCurrencyID[i],
                        grossPriceTradeAllowanceChargeReason[i]);
                sub.insertBefore(newNode, node);
            }
        }
        // ram:NetPriceProductTradePrice
        if (netPriceChargeAmount != null) {
            sub = (Element)parent.getElementsByTagName("ram:NetPriceProductTradePrice").item(0);
            setContent(sub, "ram:ChargeAmount", DEC4.check(netPriceChargeAmount),
                    new String[]{"currencyID", CURR_CODE.check(netPriceChargeAmountCurrencyID)});
            if (netPriceBasisQuantity != null)
                setContent(sub, "ram:BasisQuantity", DEC4.check(netPriceBasisQuantity),
                        new String[]{"unitCode", M_UNIT_CODE.check(netPriceBasisQuantityCode)});
        }
        
        /* ram:SpecifiedSupplyChainTradeDelivery */
        sub = (Element)parent.getElementsByTagName("ram:SpecifiedSupplyChainTradeDelivery").item(0);
        setContent(sub, "ram:BilledQuantity", DEC4.check(billedQuantity),
                new String[]{"unitCode", M_UNIT_CODE.check(billedQuantityCode)});
        
        /* ram:SpecifiedSupplyChainTradeSettlement */
        sub = (Element)parent.getElementsByTagName("ram:SpecifiedSupplyChainTradeSettlement").item(0);
        Node node = sub.getElementsByTagName("ram:ApplicableTradeTax").item(0);
        for (int i = 0; i < settlementTaxApplicablePercent.length; i++) {
            Node newNode = node.cloneNode(true);
            processTax((Element) newNode, settlementTaxTypeCode[i], settlementTaxExemptionReason[i],
                settlementTaxCategoryCode[i], settlementTaxApplicablePercent[i]);
            sub.insertBefore(newNode, node);
        }
        setContent(sub, "ram:LineTotalAmount", totalAmount, new String[]{"currencyID", totalAmountCurrencyID});
        
        /* ram:SpecifiedTradeProduct */
        sub = (Element)parent.getElementsByTagName("ram:SpecifiedTradeProduct").item(0);
        setContent(sub, "ram:GlobalID", specifiedTradeProductGlobalID, new String[]{"schemeID", GI_CODE.check(specifiedTradeProductSchemeID)});
        setContent(sub, "ram:SellerAssignedID", specifiedTradeProductSellerAssignedID, null);
        setContent(sub, "ram:BuyerAssignedID", specifiedTradeProductBuyerAssignedID, null);
        setContent(sub, "ram:Name", specifiedTradeProductName, null);
        setContent(sub, "ram:Description", specifiedTradeProductDescription, null);
    }
    
    private void processAppliedTradeAllowanceCharge(Element parent,
            boolean indicator, String actualAmount,  String currencyID, String reason) throws DataIncompleteException, InvalidCodeException {
        setContent(parent, "udt:Indicator", indicator ? "true" : "false", null);
        check(DEC4.check(actualAmount), "AppliedTradeAllowanceCharge > ActualAmount");
        setContent(parent, "ram:ActualAmount", actualAmount, new String[]{"currencyID", CURR_CODE.check(currencyID)});
        setContent(parent, "ram:Reason", reason, null);
    }
    
    private void processTax(Element parent,
        String typeCode, String exemptionReason,
        String category, String percent)
            throws InvalidCodeException, DataIncompleteException {
        // Calculated amount (required; 2 decimals)
        // TypeCode (required)
        check(typeCode, "ApplicableTradeTax > TypeCode");
        setContent(parent, "ram:TypeCode", TT_CODE.check(typeCode), null);
        // exemption reason (optional)
        setContent(parent, "ram:ExemptionReason", exemptionReason, null);
        // Category code (optional)
        if (category != null) {
            setContent(parent, "ram:CategoryCode", TC_CODE.check(category), null);
        }
        // Applicable percent (required; 2 decimals)
        setContent(parent, "ram:ApplicablePercent", DEC2.check(percent), null);
    }
    
    private void processLinesBasic(Element parent, BASICInvoice data)
            throws DataIncompleteException, InvalidCodeException {
        String[] quantity = data.getLineItemBilledQuantity();
        if (quantity.length == 0)
            throw new DataIncompleteException("You can create an invoice without any line items");
        String[] quantityCode = data.getLineItemBilledQuantityUnitCode();
        String[] name = data.getLineItemSpecifiedTradeProductName();
        Node node = parent.getElementsByTagName("ram:IncludedSupplyChainTradeLineItem").item(0);
        for (int i = 0; i < quantity.length; i++) {
            Node newNode = node.cloneNode(true);
            processLine((Element)newNode, quantity[i], quantityCode[i], name[i]);
            parent.insertBefore(newNode, node);
        }
    }
    
    protected void processLine(Element parent, String quantity, String code, String name) throws InvalidCodeException {
        Element sub = (Element)parent.getElementsByTagName("ram:SpecifiedSupplyChainTradeDelivery").item(0);
        setContent(sub, "ram:BilledQuantity", DEC4.check(quantity),
                new String[]{"unitCode", M_UNIT_CODE.check(code)});
        sub = (Element)parent.getElementsByTagName("ram:SpecifiedTradeProduct").item(0);
        setContent(sub, "ram:Name", name, null);
    }
    
    public byte[] exportDoc() throws TransformerException {
        removeNodes(doc);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	DOMSource source = new DOMSource(doc);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Result result = new StreamResult(out);
        transformer.transform(source, result);
        return out.toByteArray();
    }
    
    private static void removeNodes(Node node) {
        NodeList list = node.getChildNodes();
        for (int i = list.getLength() - 1; i >= 0; i--) {
            removeNodes(list.item(i));
        }
        boolean emptyElement = node.getNodeType() == Node.ELEMENT_NODE
            && node.getChildNodes().getLength() == 0;
        boolean emptyText = node.getNodeType() == Node.TEXT_NODE
            && node.getNodeValue().trim().isEmpty();
        if (emptyElement || emptyText) {
            node.getParentNode().removeChild(node);
        }
    }
}
