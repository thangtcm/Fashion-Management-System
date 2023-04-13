/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author couni
 */
public class StringHandle {
    public static boolean IsEmpty(String string)
    {
    //Be careful not to write: : string.equals("")

            return "".equals(string)||string==null;
    }
    public static String addWildcards(String inputString) {
        String[] parts = inputString.split("\\s+");
        StringBuilder builder = new StringBuilder();
        for (String part : parts) {
            builder.append("%").append(part).append("% ");
        }
        return builder.toString().trim();
    }
}
