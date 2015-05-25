package sandbox.fonts.tutorial;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class F99_ConvertToUnicodeNotation {
    public static void main(String[] args) {
        String s = "Vous êtes d'où?";
        System.out.print("\"");
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 31 && c < 127)
                System.out.print(String.valueOf(c));
            else
                System.out.print(String.format("\\u%04x", (int)c));
        }
        System.out.println("\"");
    }

}
