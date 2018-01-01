import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

public class Today1a
    extends JFrame
{
    private JLabel dateLabel;
    private Font dateFont;
    
    public Today1a( String dStr )
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        dateLabel = new JLabel( "Today is " + dStr );
        dateFont = new Font( "Serif", Font.BOLD, 24 );
        dateLabel.setFont( dateFont );
        dateLabel.setForeground( Color.red );
        dateLabel.setBackground( Color.white );
        dateLabel.setOpaque( true );

        getContentPane().setLayout( null );
        getContentPane().add( dateLabel );
        dateLabel.setBounds( 50, 20, 250, 80 );
        setSize( 350, 150 );
    }
    
    public static void main (String args[])
    {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL);
        Date now = new Date();
        String dateStr = formatter.format( now );
        Today1a today = new Today1a( dateStr );
        today.setVisible( true );
    }
}
