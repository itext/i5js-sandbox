/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20119709/itext-hyphen-in-table-cell
 * 
 * We create a table with and we add a word that needs to be hyphenated.
 */
package test.tables;

import test.GenericTest;

public class HyphenationExample extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.tables.HyphenationExample");
	}

    @Override
    protected String getCmpPdf() {
        return "cmpfiles/tables/cmp_hyphenation_table.pdf";
    }
}
