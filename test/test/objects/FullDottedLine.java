/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20233630/itextsharp-how-to-add-a-full-line-break
 * 
 * We create a Chunk and add a background color.
 */
package test.objects;

import test.GenericTest;

public class FullDottedLine extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.objects.FullDottedLine");
	}
	
    @Override
    protected String getCmpPdf() {
        return "cmpfiles/objects/cmp_full_dotted_line.pdf";
    }

}
