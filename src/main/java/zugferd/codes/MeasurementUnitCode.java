/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zugferd.codes;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class MeasurementUnitCode {
    
    public static final String ITEM = "C62";
    public static final String DAY = "DAY"; 
    public static final String HA = "HAR"; 
    public static final String HR = "HUR"; 
    public static final String KG = "KGM"; 
    public static final String KM = "KTM"; 
    public static final String KWH = "KWH"; 
    public static final String SUM = "LS"; 
    public static final String L = "LTR"; 
    public static final String MIN = "MIM";
    public static final String MM2 = "MMK"; 
    public static final String MM = "MMT"; 
    public static final String M2 = "MTK"; 
    public static final String M3 = "MTQ";
    public static final String M = "MTR"; 
    public static final String NO = "NAR"; 
    public static final String PR = "NPR"; 
    public static final String PCT = "P1";
    public static final String SET = "SET"; 
    public static final String T = "TNE"; 
    public static final String WK = "WEE";     
    
    public static boolean isValid(String code) {
        return (code.equals(ITEM) ||
                code.equals(DAY) ||
                code.equals(HA) ||
                code.equals(HR) ||
                code.equals(KG) ||
                code.equals(KM) ||
                code.equals(KWH) ||
                code.equals(SUM) ||
                code.equals(L) ||
                code.equals(MIN) ||
                code.equals(MM2) ||
                code.equals(MM) ||
                code.equals(M2) ||
                code.equals(M3) ||
                code.equals(M) ||
                code.equals(NO) ||
                code.equals(PR) ||
                code.equals(PCT) ||
                code.equals(SET) ||
                code.equals(T) ||
                code.equals(WK));
    }
    
}
