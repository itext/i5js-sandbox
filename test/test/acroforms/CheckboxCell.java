/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20401125/overlapping-characters-in-text-field-itext-pdf
 * 
 * Sometimes you need to change the font of a field.
 */
package test.acroforms;

import test.GenericTest;

public class CheckboxCell extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.acroforms.CheckboxCell");
	}
	
    @Override
    protected String getCmpPdf() {
        return "cmpfiles/acroforms/cmp_checkbox_in_cell.pdf";
    }
}
