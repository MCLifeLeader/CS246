import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Dimension;

public class Hello
    extends JFrame
    implements ActionListener
{
    private JButton button;
    private int state = 0;
    
    public Hello()
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        button = new JButton( "Hello" );
        button.setFont( new Font( "SansSerif", Font.BOLD, 24 ) );
        button.addActionListener( this );

        getContentPane().add( button, "Center" );
        setSize( 200, 200 );
    }
    
    public void actionPerformed( ActionEvent e )
    {
        if ( state == 0 )
        {
            button.setText( "Goodbye" );
            state++;
        }
        else
        { 
            System.exit( 0 );
        }
    }
    
    public static void main (String args[])
    {
        Hello hello = new Hello();
        hello.setVisible( true );
    }
}
