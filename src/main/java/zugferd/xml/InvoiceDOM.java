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
import zugferd.codes.DateFormatCode;
import zugferd.codes.DocumentTypeCode;
import zugferd.codes.FreeTextSubjectCode;
import zugferd.exceptions.InvalidCodeException;

/**
 * @author iText
 */
public final class InvoiceDOM {
    
    // Profiles that are supported:
    public static final String BASIC = "resources/zugferd/basic.xml";
    public static final String COMFORT = "resources/zugferd/comfort.xml";
    
    // The DOM document
    private Document doc;
    
    /**
     * Creates an object that will import data into an XML template.
     * @param data If this is an instance of BASICInvoice, the BASIC profile will be used;
     *             If this is an instance of COMFORTInvoice, the COMFORT profile will be used.
     */
    public InvoiceDOM(BASICInvoice data)
            throws ParserConfigurationException, SAXException, IOException,
            DataIncompleteException, InvalidCodeException {
        // Checking the profile and the document type code
        DocumentTypeCode dtCode;
        String template;
        if (data instanceof COMFORTInvoice) {
            template = COMFORT;
            dtCode = new DocumentTypeCode(DocumentTypeCode.COMFORT);
        }
        else {
            template = BASIC;
            dtCode = new DocumentTypeCode(DocumentTypeCode.COMFORT);
        }
        if (!dtCode.isValid(data.getTypeCode()))
            throw new InvalidCodeException(data.getTypeCode(), "document type code");
        // Loading the XML template
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	doc = docBuilder.parse(template);
        // importing the data
        importData(data);
    }
    
    private void importData(BASICInvoice data) throws DataIncompleteException, InvalidCodeException {
        
        /* SpecifiedExchangedDocumentContext (required) */
        
        Element element = (Element) doc.getElementsByTagName("rsm:SpecifiedExchangedDocumentContext").item(0);
        // TestIndicator (optional)
        setContent(element, "udt:Indicator", data.getTestIndicator() ? "true" : "false", null);
        
        /* HeaderExchangedDocument */
        
        element = (Element) doc.getElementsByTagName("rsm:HeaderExchangedDocument").item(0);
        // ID (required)
        if (isEmpty(data.getId())) 
            throw new DataIncompleteException("HeaderExchangedDocument > ID");
        setContent(element, "ram:ID", data.getId(), null);
        // Name (required)
        if (isEmpty(data.getName())) 
            throw new DataIncompleteException("HeaderExchangedDocument > Name");
        setContent(element, "ram:Name", data.getName(), null);
        // TypeCode (required; already checked upon creation of the InvoiceDOM)
        setContent(element, "ram:TypeCode", data.getTypeCode(), null);
        // IssueDateTime (required)
        setDateTime(element, "udt:DateTimeString", data.getDateTimeFormat(), data.getDateTime());
        // IncludedNote (optional): header level
        setIncludedNotes(element, FreeTextSubjectCode.HEADER, data);
        
        /* SpecifiedSupplyChainTradeTransaction */
        
        element = (Element) doc.getElementsByTagName("rsm:SpecifiedSupplyChainTradeTransaction").item(0);
        // buyer reference (optional; comfort only)
        setBuyerReference(element, data);
        // SellerTradeParty (required)
        if (isEmpty(data.getSellerName())) 
            throw new DataIncompleteException("SpecifiedSupplyChainTradeTransaction > ApplicableSupplyChainTradeAgreement > SellerTradeParty > Name");
        setSellerTradeParty(element, data);
        // BuyerTradeParty (required)
        if (isEmpty(data.getBuyerName())) 
            throw new DataIncompleteException("SpecifiedSupplyChainTradeTransaction > ApplicableSupplyChainTradeAgreement > BuyerTradeParty > Name");
        setBuyerTradeParty(element, data);
        
        // ApplicableSupplyChainTradeDelivery
        if (!isEmpty(data.getDeliveryDateTimeFormat())) {
            Element parent = (Element)element.getElementsByTagName("ram:ActualDeliverySupplyChainEvent").item(0);
            setDateTime(parent, "udt:DateTimeString", data.getDeliveryDateTimeFormat(), data.getDeliveryDateTime());
        }
        // ApplicableSupplyChainTradeSettlement
        setNodeContent(doc, "ram:PaymentReference", 0, data.getPaymentReference());
        if (isEmpty(data.getInvoiceCurrencyCode())) 
            throw new DataIncompleteException("ApplicableSupplyChainTradeSettlement > InvoiceCurrencyCode");
        setNodeContent(doc, "ram:InvoiceCurrencyCode", 0, data.getInvoiceCurrencyCode());
        processPaymentMeans(doc, data);
        processTax(doc, data);
        if (isEmpty(data.getLineTotalAmount()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > LineTotalAmount");
        if (isEmpty(data.getLineTotalAmountCurrencyID()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > LineTotalAmount . currencyID");
        setNodeContent(doc, "ram:LineTotalAmount", 0, data.getLineTotalAmount(), "currencyID", data.getLineTotalAmountCurrencyID());
        if (isEmpty(data.getChargeTotalAmount()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > ChargeTotalAmount");
        if (isEmpty(data.getChargeTotalAmountCurrencyID()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > ChargeTotalAmount . currencyID");
        setNodeContent(doc, "ram:ChargeTotalAmount", 0, data.getChargeTotalAmount(), "currencyID", data.getChargeTotalAmountCurrencyID());
        if (isEmpty(data.getAllowanceTotalAmount()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > AllowanceTotalAmount");
        if (isEmpty(data.getAllowanceTotalAmountCurrencyID()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > AllowanceTotalAmount . currencyID");
        setNodeContent(doc, "ram:AllowanceTotalAmount", 0, data.getAllowanceTotalAmount(), "currencyID", data.getAllowanceTotalAmountCurrencyID());
        if (isEmpty(data.getTaxBasisTotalAmount()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > TaxBasisTotalAmount");
        if (isEmpty(data.getTaxBasisTotalAmountCurrencyID()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > TaxBasisTotalAmount . currencyID");
        setNodeContent(doc, "ram:TaxBasisTotalAmount", 0, data.getTaxBasisTotalAmount(), "currencyID", data.getTaxBasisTotalAmountCurrencyID());
        if (isEmpty(data.getTaxTotalAmount()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > TaxTotalAmount");
        if (isEmpty(data.getTaxTotalAmountCurrencyID()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > TaxTotalAmount . currencyID");
        setNodeContent(doc, "ram:TaxTotalAmount", 0, data.getTaxTotalAmount(), "currencyID", data.getTaxTotalAmountCurrencyID());
        if (isEmpty(data.getGrandTotalAmount()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > GrandTotalAmount");
        if (isEmpty(data.getGrandTotalAmountCurrencyID()))
            throw new DataIncompleteException("SpecifiedTradeSettlementMonetarySummation > GrandTotalAmount . currencyID");
        setNodeContent(doc, "ram:GrandTotalAmount", 0, data.getGrandTotalAmount(), "currencyID", data.getGrandTotalAmountCurrencyID());
        processLines(doc, data);
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
     * Set the content of a date tag along with the attribute that defines the format.
     * @param parent    the parent element that holds the date tag
     * @param tag       the date tag we want to change
     * @param dateTimeFormat    the format that will be used as an attribute
     * @param dateTime  the actual date
     */
    protected void setDateTime(Element parent, String tag, String dateTimeFormat, Date dateTime) throws InvalidCodeException {
        DateFormatCode dfCode = new DateFormatCode();
        if (!dfCode.isValid(dateTimeFormat))
            throw new InvalidCodeException(dateTimeFormat, "date / time format code");
        setContent(parent, tag, dfCode.convertToString(dateTime, dateTimeFormat), new String[]{"format", dateTimeFormat});
    }
    
    /**
     * Includes notes and (in case of the COMFORT profile) the subject codes
     * for those notes.
     * @param parent    the parent element of the tag we want to change
     * @param tag   the name of the tag
     * @param level the level where the notices are added (header or line)
     * @param data  the invoice data (BASICInvoice and COMFORTInvoice are supported)
     */
    protected void setIncludedNotes(Element parent, int level, BASICInvoice data)
        throws DataIncompleteException, InvalidCodeException {
        Node includedNoteNode = parent.getElementsByTagName("ram:IncludedNote").item(0);
        String[] notes = data.getNotes();
        int n = notes.length;
        FreeTextSubjectCode ftsCode = new FreeTextSubjectCode(level);
        String[] notesCodes = null;
        if (data instanceof COMFORTInvoice) {
            notesCodes = ((COMFORTInvoice)data).getNotesCodes();
            if (n != notesCodes.length)
                throw new DataIncompleteException("Number of included notes is not equal to number of codes for included notes.");
        }
        for (int i = 0; i < n; i++) {
            Element noteNode = (Element)includedNoteNode.cloneNode(true);
            Node content = noteNode.getElementsByTagName("ram:Content").item(0);
            content.setTextContent(notes[i]);
            if (notesCodes != null) {
                if (!ftsCode.isValid(notesCodes[i]))
                    throw new InvalidCodeException(notesCodes[i], "included note subject code");
                Node code = noteNode.getElementsByTagName("ram:SubjectCode").item(0);
                code.setTextContent(notesCodes[i]);
            }
            parent.insertBefore(noteNode, includedNoteNode);
        }
    }
    
    protected void setBuyerReference(Element parent, BASICInvoice data) {
        if (data instanceof COMFORTInvoice) {
            String buyerReference = ((COMFORTInvoice) data).getBuyerReference();
            if (!isEmpty(buyerReference)) {
                setContent(parent, "ram:BuyerReference", buyerReference, null);
            }
        }
    }
    
    protected void setSellerTradeParty(Element parent, BASICInvoice data) throws DataIncompleteException {
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
    
    protected void setBuyerTradeParty(Element parent, BASICInvoice data) throws DataIncompleteException {
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
    
    protected void processTradeParty(Element element, String tagname,
        String id, String[] globalID, String[] globalIDScheme,
        String name, String postcode, String lineOne, String lineTwo,
        String cityName, String countryID,
        String[] taxRegistrationID, String[] taxRegistrationSchemeID) throws DataIncompleteException {
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
                schemeID.setTextContent(globalIDScheme[i]);
                party.insertBefore(idNode, node);
            }
        }
        setContent(party, "ram:Name", name, null);
        setContent(party, "ram:PostcodeCode", postcode, null);
        setContent(party, "ram:LineOne", lineOne, null);
        setContent(party, "ram:LineTwo", lineTwo, null);
        setContent(party, "ram:CityName", cityName, null);
        setContent(party, "ram:CountryID", countryID, null);
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
            schemeID.setTextContent(taxRegistrationSchemeID[i]);
            tax.insertBefore(idNode, node);
        }           
    }
    
    protected void setNodeContent(Document doc, String tagname, int idx, String content, String... attributes) {
        Node node = doc.getElementsByTagName(tagname).item(idx);
        if (node == null)
            return;
        node.setTextContent(content);
        int n = attributes.length;
        if (n == 0) return;
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
    
    protected void setNodeSubContent(Document doc, String tagname, int idx, String[] content, String[] attrs) {
        if (content.length == 0)
            return;
        Node node = doc.getElementsByTagName(tagname).item(idx);
        for (String text : content) {
            Node newNode = node.cloneNode(true);
            NodeList list = newNode.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node childNode = list.item(i);
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    childNode.setTextContent(text);
                    if (attrs != null && attrs.length <= i) {
                        Node attr = childNode.getAttributes().item(0);
                        attr.setTextContent(attrs[i]);
                    }
                }
            }
            Node parent = node.getParentNode();
            parent.insertBefore(newNode, node);
        }
    }

    protected void setNodeSubContent(Node parent, Node node, String[] content, String[] attrs) {
        if (content.length == 0)
            return;
        int n = content.length;
        for (int i = 0; i < n; i++) {
            Node newNode = node.cloneNode(true);
            NodeList list = newNode.getChildNodes();
            for (int j = 0; j < list.getLength(); j++) {
                Node childNode = list.item(j);
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    childNode.setTextContent(content[i]);
                    Node attr = childNode.getAttributes().item(0);
                    attr.setTextContent(attrs[i]);
                }
            }
            parent.insertBefore(newNode, node);
        }
    }
    
    protected void processPaymentMeans(Document doc, BASICInvoice data) {
        String[][] pmID = data.getPaymentMeansID();
        String[][] pmSchemeAgencyID = data.getPaymentMeansSchemeAgencyID();
        String[] pmIBAN = data.getPaymentMeansPayeeAccountIBAN();
        String[] pmAccountName = data.getPaymentMeansPayeeAccountAccountName();
        String[] pmAccountID = data.getPaymentMeansPayeeAccountProprietaryID();
        String[] pmBIC = data.getPaymentMeansPayeeFinancialInstitutionBIC();
        String[] pmGermanBankleitzahlID = data.getPaymentMeansPayeeFinancialInstitutionGermanBankleitzahlID();
        String[] pmFinancialInst = data.getPaymentMeansPayeeFinancialInstitutionName();
        for (int i = 0; i < pmID.length; i++) {
            processPaymentMeans(doc, data,
                    pmID[i],
                    pmSchemeAgencyID[i],
                    pmIBAN[i],
                    pmAccountName[i],
                    pmAccountID[i],
                    pmBIC[i],
                    pmGermanBankleitzahlID[i],
                    pmFinancialInst[i]
            );
        }
    }
    
    protected void processPaymentMeans(Document doc, BASICInvoice data,
        String[] id, String[] scheme, String iban, String accName, String accID, String bic, String bank, String inst) {  
        NodeList l = doc.getElementsByTagName("ram:SpecifiedTradeSettlementPaymentMeans");
        Element element = (Element) l.item(l.getLength() - 1);
        Node parent = element.getParentNode();
        Element newNode = (Element)parent.insertBefore(element.cloneNode(true), element);
        Node idNode = newNode.getElementsByTagName("ram:ID").item(0);
        int n = id.length;
        for (int j = n - 1; j >= 0; j--) {
            Node c = newNode.insertBefore(idNode.cloneNode(true), idNode.getNextSibling());
            c.setTextContent(id[j]);
            c.getAttributes().item(0).setTextContent(scheme[j]);
        }
        Element accNode = (Element)newNode.getElementsByTagName("ram:PayeePartyCreditorFinancialAccount").item(0);
        Node ibanNode = accNode.getElementsByTagName("ram:IBANID").item(0);
        ibanNode.setTextContent(iban);
        Node accNameNode = accNode.getElementsByTagName("ram:AccountName").item(0);
        accNameNode.setTextContent(accName);
        Node accIDNode = accNode.getElementsByTagName("ram:ProprietaryID").item(0);
        accIDNode.setTextContent(accID);
        Element instNode = (Element)newNode.getElementsByTagName("ram:PayeeSpecifiedCreditorFinancialInstitution").item(0);
        Node bicNode = instNode.getElementsByTagName("ram:BICID").item(0);
        bicNode.setTextContent(bic);
        Node bankNode = instNode.getElementsByTagName("ram:GermanBankleitzahlID").item(0);
        bankNode.setTextContent(bank);
        Node nameNode = instNode.getElementsByTagName("ram:Name").item(0);
        nameNode.setTextContent(inst);
    }
    
    protected void processTax(Document doc, BASICInvoice data) {
        String[] calculated = data.getTaxCalculatedAmount();
        String[] calculatedCurr = data.getTaxCalculatedAmountCurrencyID();
        String[] typeCode = data.getTaxTypeCode();
        String[] basisAmount = data.getTaxBasisAmount();
        String[] basisAmountCurr = data.getTaxBasisAmountCurrencyID();
        String[] percent = data.getTaxApplicablePercent();
        for (int i = calculated.length - 1; i >= 0; i--) {
            processTax(doc, calculated[i], calculatedCurr[i],
                    typeCode[i], basisAmount[i], basisAmountCurr[i], percent[i]);
        }
    }
    
    protected void processTax(Document doc, String... content) {/*
        Node node = doc.getElementsByTagName("ram:ApplicableTradeTax").item(0);
        int counter = 0;
        Node newNode = node.cloneNode(true);
        Node childNode;
        NodeList list = newNode.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            childNode = list.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                childNode.setTextContent(content[counter++]);
                if (counter == 1 || counter == 4) {
                    childNode.getAttributes().item(0).setTextContent(content[counter++]);
                }
            }
        }
        Node parent = node.getParentNode();
        parent.insertBefore(newNode, node);*/
    }
    
    protected void processLines(Document doc, BASICInvoice data) throws DataIncompleteException {
        String[] quantity = data.getLineItemBilledQuantity();
        if (quantity.length == 0)
            throw new DataIncompleteException("You can create an invoice without any line items");
        String[] quantityCode = data.getLineItemBilledQuantityUnitCode();
        String[] name = data.getLineItemSpecifiedTradeProductName();
        for (int i = quantity.length - 1; i >= 0; i--) {
            processLine(doc, quantity[i], quantityCode[i], name[i]);
        }
    }
    
    protected void processLine(Document doc, String quantity, String code, String name) {
        Node node = doc.getElementsByTagName("ram:IncludedSupplyChainTradeLineItem").item(0);
        Node newNode = node.cloneNode(true);
        Node childNode;
        NodeList list = newNode.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            childNode = list.item(i);
            if ("ram:SpecifiedSupplyChainTradeDelivery".equals(childNode.getNodeName())) {
                NodeList l = childNode.getChildNodes();
                for (int j = 0; j < l.getLength(); j++) {
                    Node grandchildNode = l.item(j);
                    if (grandchildNode.getNodeType() == Node.ELEMENT_NODE) {
                        grandchildNode.setTextContent(quantity);
                        grandchildNode.getAttributes().item(0).setTextContent(code);
                    }
                }
            }
            else if ("ram:SpecifiedTradeProduct".equals(childNode.getNodeName())) {
                NodeList l = childNode.getChildNodes();
                for (int j = 0; j < l.getLength(); j++) {
                    Node grandchildNode = l.item(j);
                    if (grandchildNode.getNodeType() == Node.ELEMENT_NODE)
                        grandchildNode.setTextContent(name);
                }
            }
        }
        Node parent = node.getParentNode();
        parent.insertBefore(newNode, node);
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
    
    protected static void removeNodes(Node node) {
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
    
    protected boolean isEmpty(String s) {
        if (s == null) return true;
        return s.trim().length() == 0;
    }
}
