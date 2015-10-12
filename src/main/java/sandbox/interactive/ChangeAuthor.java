/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * https://www.linkedin.com/groups/Script-Change-Author-Name-Comments-159987.S.5984062085800144899
 */
package sandbox.interactive;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ChangeAuthor {
    public static final String SRC = "resources/pdfs/page229_annotations.pdf";
    public static final String DEST = "results/interactive/page229_changed_author.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ChangeAuthor().manipulatePdf(SRC, DEST);
    }
    
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary pageDict = reader.getPageN(1);
        PdfArray annots = pageDict.getAsArray(PdfName.ANNOTS);
        if (annots != null) {
            PdfDictionary annot;
            for (int i = 0; i < annots.size(); i++) {
                changeAuthor(annots.getAsDict(i));
            }
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
    }
    
    public void changeAuthor(PdfDictionary annot) {
        if (annot == null) return;
        PdfString t = annot.getAsString(PdfName.T);
        if (t == null) return;
        if ("iText".equals(t.toString()))
            annot.put(PdfName.T, new PdfString("Bruno Lowagie"));
    }
}
