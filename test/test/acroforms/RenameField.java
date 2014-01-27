/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20401125/overlapping-characters-in-text-field-itext-pdf
 * 
 * Sometimes you need to change the font of a field.
 */
package test.acroforms;

import test.GenericTest;

public class RenameField extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.acroforms.RenameField");
	}
	
    @Override
    protected String getCmpPdf() {
        return "cmpfiles/acroforms/cmp_subscribe_renamed.pdf";
    }
}
