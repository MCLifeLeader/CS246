import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

public class Today
    extends JFrame
{
    private JLabel dateLabel;
    private Font   dateFont;

    public Today( String dStr )
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        dateLabel = new JLabel( "Today is " + dStr );
        dateFont = new Font( "Serif", Font.BOLD, 24 );
        dateLabel.setFont( dateFont );
        Border empty = BorderFactory.createEmptyBorder( 10, 20, 10, 20 );
        dateLabel.setBorder( empty );
        dateLabel.setBorder( BorderFactory.createEmptyBorder( 10, 20, 10, 20 ));
        dateLabel.setHorizontalAlignment( SwingConstants.CENTER );
        dateLabel.setBackground( Color.white );
        dateLabel.setOpaque( true );
        dateLabel.setForeground( Color.red );
        getContentPane().add( dateLabel, BorderLayout.CENTER );
        
        pack();
    } 

    public static void main (String args[])
    {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL);
        Date now = new Date();
        String dateStr = formatter.format( now );
        Today today = new Today( dateStr );
        today.setVisible( true );
    }

}
