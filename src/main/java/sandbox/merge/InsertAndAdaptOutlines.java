/*
 * Example written in answer to:
 * http://stackoverflow.com/questions/34381849
 */
package sandbox.merge;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.SimpleBookmark;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import sandbox.WrapToTest;

@WrapToTest
public class InsertAndAdaptOutlines {

    /** The original PDF file. */
    public static final String INSERT
        = "resources/pdfs/hello.pdf";
    /** The original PDF file. */
    public static final String SRC
        = "resources/pdfs/bookmarks.pdf";
    /** The resulting PDF file. */
    public static final String DEST
        = "results/merge/bookmarks_hello.pdf";
    
    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public static void main(String[] args)
        throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new InsertAndAdaptOutlines().manipulatePdf(SRC, DEST);
    }
    
    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public void manipulatePdf(String src, String dest)
        throws IOException, DocumentException {
        PdfReader insert = new PdfReader(INSERT);
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(DEST));
        stamper.insertPage(4, insert.getPageSize(1));
        PdfContentByte cb = stamper.getOverContent(4);
        cb.addTemplate(stamper.getImportedPage(insert, 1), 0, 0);
        List<HashMap<String, Object>> outlines = SimpleBookmark.getBookmark(reader);
        HashMap<String, Object> entry = new HashMap<String, Object>();
        entry.put("Title", "Hello");
        entry.put("Action", "GoTo");
        entry.put("Page", "4 Fit");
        updateOutline(outlines, entry, 4);
        stamper.setOutlines(outlines);
        stamper.close();
    }
    
    public boolean updateOutline(List<HashMap<String, Object>> outlines, HashMap<String, Object> entry, int p) {
        int index = 0;
        for (HashMap<String, Object> outline : outlines) {
            Object kids = outline.get("Kids");
            if (kids != null) {
                updateOutline((List<HashMap<String, Object>>)kids, entry, p);
            }
            else {
                if (p < getPage(outline)) {
                    outlines.add(index, entry);
                    return true;
                }
                index++;
            }
        }
        return false;
    }
    
    public int getPage(HashMap<String, Object> outline) {
        Object page = outline.get("Page");
        if (page == null) return -1;
        String p = page.toString().substring(0, page.toString().indexOf(" "));
        return Integer.parseInt(p);
    }
}
