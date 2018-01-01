import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class Today1
    extends JFrame
{
    private JLabel dateLabel;
    
    public Today1( String dStr )
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        dateLabel = new JLabel( "Today is " + dStr );
        getContentPane().add( dateLabel, BorderLayout.CENTER );

        pack();
    }
    
    public static void main (String args[])
    {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL);
        Date now = new Date();
        String dateStr = formatter.format( now );
        Today1 today = new Today1( dateStr );
        today.setVisible( true );
    }
}