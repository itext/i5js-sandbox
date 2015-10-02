/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27867868/how-can-i-decrypt-a-pdf-document-with-the-owner-password
 */
package sandbox.security;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DecryptPdf3 {

    class MyReader extends PdfReader {
        public MyReader(String filename) throws IOException {
            super(filename);
        }
        public void decryptOnPurpose() {
            encrypted = false;
        }
    }
    
    public static final String SRC = "resources/pdfs/hello_encrypted2.pdf";
    public static final String DEST = "results/security/hello3.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DecryptPdf3().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        MyReader.unethicalreading = true;
        MyReader reader = new MyReader(src);
        reader.decryptOnPurpose();
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }
}
