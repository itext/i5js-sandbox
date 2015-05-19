/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27867868/how-can-i-decrypt-a-pdf-document-with-the-owner-password
 */
package sandbox.security;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStream;
import java.io.IOException;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class GetN2fromSig {
    public static final String SRC = "resources/pdfs/signature_n2.pdf";
    
    public static void main(String[] args) throws IOException {
        PdfReader reader = new PdfReader(SRC);
        AcroFields fields = reader.getAcroFields();
        Item item = fields.getFieldItem("Signature1");
        PdfDictionary widget = item.getWidget(0);
        PdfDictionary ap = widget.getAsDict(PdfName.AP);
        PdfStream normal = ap.getAsStream(PdfName.N);
        PdfDictionary resources = normal.getAsDict(PdfName.RESOURCES);
        PdfDictionary xobject = resources.getAsDict(PdfName.XOBJECT);
        PdfStream frm = xobject.getAsStream(PdfName.FRM);
        PdfDictionary res = frm.getAsDict(PdfName.RESOURCES);
        PdfDictionary xobj = res.getAsDict(PdfName.XOBJECT);
        PRStream n2 = (PRStream) xobj.getAsStream(PdfName.N2);
        byte[] stream = PdfReader.getStreamBytes(n2);
        System.out.println(new String(stream));
    }
}
