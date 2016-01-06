/*
 * This example is written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/34617914
 */
package sandbox.logging;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfString;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class CustomPageDictKeyMerge {

    public static final String SRC1 = "resources/pdfs/hello.pdf";
    public static final String SRC2 = "resources/pdfs/united_states.pdf";
    public static final String SRC3 = "resources/pdfs/hero.pdf";
    public static final String DEST = "results/logging/custom_page_dict_key_merge.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CustomPageDictKeyMerge().createPdf(DEST);
    }
    
    
    public void createPdf(String filename) throws IOException, DocumentException {
        PdfName marker = new PdfName("ITXT_PageMarker");
        List<PdfReader> readers = new ArrayList<PdfReader>();
        readers.add(new PdfReader(SRC1));
        readers.add(new PdfReader(SRC2));
        readers.add(new PdfReader(SRC3));
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(filename));
        document.open();
        int counter = 0;
        int n;
        PdfImportedPage importedPage;
        PdfDictionary pageDict;
        for (PdfReader reader : readers) {
            counter++;
            n = reader.getNumberOfPages();
            for (int p = 1; p <= n; p++) {
                pageDict = reader.getPageN(p);
                pageDict.put(marker, new PdfString(String.format("Page %s of document %s", p, counter)));
                importedPage = copy.getImportedPage(reader, p);
                copy.addPage(importedPage);
            }
        }
        // close the document
        document.close();
        for (PdfReader reader : readers) {
            reader.close();
        }
    }
}
