import javax.swing.*;
import javax.swing.border.*;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;

public class Flow
    extends JFrame
{
    private Font labelFont;
    private Border labelBorder;
    
    public Flow( )
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        labelFont = new Font( "SansSerif", Font.BOLD, 24 );
        labelBorder = BorderFactory.createLineBorder( Color.red, 1 );
        getContentPane().setLayout( new FlowLayout() );
        setSize(200, 200 );
    }
    
    public void addLabel( String labelStr )
    {
        JLabel label = new JLabel( labelStr );
        label.setFont( labelFont );
        label.setBorder( labelBorder );
        label.setHorizontalAlignment( SwingConstants.CENTER );
        getContentPane().add( label );
    }
    
    public static void main (String args[])
    {
        Flow flow = new Flow();
        flow.addLabel( "one" );
        flow.addLabel( "two" );
        flow.addLabel( "three" );
        flow.addLabel( "four" );
        flow.addLabel( "five" );
        flow.setVisible( true );
    }
}
