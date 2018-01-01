import javax.swing.*;
import javax.swing.border.*;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;

public class Grid
    extends JFrame
{
    private Font labelFont;
    private Border labelBorder;
    
    public Grid( )
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        labelFont = new Font( "SansSerif", Font.BOLD, 24 );
        labelBorder = BorderFactory.createLineBorder( Color.red, 1 );
        getContentPane().setLayout( new GridLayout( 0, 2 ) );
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
        Grid grid = new Grid();
        grid.addLabel( "1" );
        grid.addLabel( "2" );
        grid.addLabel( "3" );
        grid.addLabel( "4" );
        grid.addLabel( "5" );
        grid.setVisible( true );
    }
}
