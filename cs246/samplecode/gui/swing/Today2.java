import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

public class Today2
    extends JFrame
{
    private JLabel dateLabel;
    private Font   dateFont;
    
    public Today2( String dStr )
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        dateLabel = new JLabel( "Today is " + dStr );
        dateFont = new Font( "Serif", Font.BOLD, 24 );
        dateLabel.setFont( dateFont );
        dateLabel.setForeground( Color.red );
        dateLabel.setBackground( Color.white );
        getContentPane().add( dateLabel, BorderLayout.CENTER );
        pack();
    }
    
    public static void main (String args[])
    {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL);
        Date now = new Date();
        String dateStr = formatter.format( now );
        Today2 today = new Today2( dateStr );
        today.setVisible( true );
    }
}
