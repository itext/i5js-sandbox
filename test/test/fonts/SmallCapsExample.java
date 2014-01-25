/**
 * Example written by Bruno Lowagie in answer to:
 * http://thread.gmane.org/gmane.comp.java.lib.itext.general/65892
 * 
 * Some text displayed using a Small Caps font.
 */
package test.fonts;

import test.GenericTest;

public class SmallCapsExample extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.fonts.SmallCapsExample");
	}
	
    @Override
    protected String getCmpPdf() {
        return "cmpfiles/fonts/cmp_small_caps.pdf";
    }
}
