import java.text.*;
import java.util.*;

public class Today0 {
    public static void main (String args[]) {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL);
        Date now = new Date();
        String dateStr = formatter.format( now );
        
        System.out.println( dateStr );
    }
}