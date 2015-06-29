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
    protected List<String> sellerGlobalID;
    protected List<String> sellerGlobalSchemeID;
    protected String buyerID;
    protected List<String> buyerGlobalID;
    protected List<String> buyerGlobalSchemeID;
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
}
