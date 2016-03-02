/*
 * Example written in answer to:
 * http://stackoverflow.com/questions/35669782/
 */
package sandbox.objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class PdfOnButtonClick {
    
    public class PdfActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser dialog = new JFileChooser();
            int dialogResult = dialog.showSaveDialog(null);
            if (dialogResult==JFileChooser.APPROVE_OPTION){
                String filePath = dialog.getSelectedFile().getPath();
                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));
                    document.open();
                    document.add(new Paragraph("File with path " + filePath));
                    document.close();
                }
                catch(DocumentException de) {
                    de.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setTitle("ATUL doesn't know how to code");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton button = new JButton("Push ATUL, push!");
        button.addActionListener(new PdfOnButtonClick().new PdfActionListener());
        frame.getContentPane().add(button);
        frame.setVisible(true);
    }
}
