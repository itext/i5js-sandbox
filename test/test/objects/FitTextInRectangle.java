/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20909450/correct-text-position-center-in-rectangle-itext
 * 
 * Doing some font math to vertically fit a piece of text inside a rectangle.
 */
package test.objects;

import test.GenericTest;

public class FitTextInRectangle extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.objects.FitTextInRectangle");
	}
	
    @Override
    protected String getCmpPdf() {
        return "cmpfiles/objects/cmp_chunk_in_rectangle.pdf";
    }
}
