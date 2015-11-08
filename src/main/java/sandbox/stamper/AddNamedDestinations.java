/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/29884833/add-named-destinations-to-an-existing-pdf-document-with-itext
 */
package sandbox.stamper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.SimpleNamedDestination;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddNamedDestinations {

    public static final String SRC = "resources/pdfs/primes.pdf";
    public static final String DEST = "results/stamper/primes_with_destination.pdf";
    public static final String XML = "results/stamper/primes_with_destination.xml";
   
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        AddNamedDestinations app = new AddNamedDestinations();
        app.manipulatePdf(SRC, DEST);
        app.createXml(DEST, XML);
    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        int n = reader.getNumberOfPages();
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfDestination destination = new PdfDestination(PdfDestination.FIT);
        PdfWriter writer = stamper.getWriter();
        for (int i = 1; i < n; ) {
            if (getFactors(++i).size() > 1) continue;
                writer.addNamedDestination("prime" + i, i - 1, destination);
        }
        stamper.close();
        reader.close();
    }
    
    public static List<Integer> getFactors(int n) {
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors;
    }
    
    public void createXml(String src, String dest) throws IOException {
        PdfReader reader = new PdfReader(src);
        HashMap<String,String> map = SimpleNamedDestination.getNamedDestination(reader, false);
        SimpleNamedDestination.exportToXML(map, new FileOutputStream(dest),
                "ISO8859-1", true);
        reader.close();
    }
}