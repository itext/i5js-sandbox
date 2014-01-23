/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20131610/renaming-named-destinations-in-pdf-files
 * 
 * Searching for all the named destinations, with the purpose to rename them.
 * Change the destination for all GoTo actions from Link annotations on the first page.
 */
package sandbox.annotations;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import sandbox.SandboxTest;

public class RenameDestinations extends SandboxTest {

    String inputPdf = "./resources/pdfs/nameddestinations.pdf";

    @Override
    protected String getOutPdf() {
        return "./results/annotations/nameddests.pdf";
    }

    @Override
    protected String getCmpPdf() {
        return "./resources/results/annotations/cmp_nameddests.pdf";
    }

    @Override
    public void makePdf(String outPdf) throws Exception {
        PdfReader reader = new PdfReader(inputPdf);
        Map<String, PdfString> renamed = new HashMap<String, PdfString>();
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary names = catalog.getAsDict(PdfName.NAMES);
        PdfDictionary dests = names.getAsDict(PdfName.DESTS);
        PdfArray name = dests.getAsArray(PdfName.NAMES);
        for (int i = 0; i < name.size(); i += 2) {
            PdfString original = name.getAsString(i);
            PdfString newName = new PdfString("new" + original.toString());
            name.set(i, newName);
            renamed.put(original.toString(), newName);
        }
        PdfDictionary page = reader.getPageN(1);
        PdfArray annotations = page.getAsArray(PdfName.ANNOTS);
        PdfDictionary annotation;
        PdfDictionary action;
        PdfString n;
        for (int i = 0; i < annotations.size(); i++) {
            annotation = annotations.getAsDict(i);
            action = annotation.getAsDict(PdfName.A);
            if (action == null)
                continue;
            n = action.getAsString(PdfName.D);
            if (n != null && renamed.containsKey(n.toString())) {
                action.put(PdfName.D, renamed.get(n.toString()));
            }
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outPdf));
        stamper.close();
    }

    public static void main(String[] args) throws Exception {
        SandboxTest test = new RenameDestinations();
        test.makePdf();
    }
}
