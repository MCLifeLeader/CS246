import javax.swing.*;
import javax.swing.event.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;

public class FavoriteColor
    extends JFrame
{
    private ColorLabel colorLabel;
    private JColorChooser chooser;
    
    public FavoriteColor( String person, Color c )
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
              
        chooser = new JColorChooser( c );
        colorLabel = new ColorLabel( "This is " + person + "'s favorite color.", chooser ); 
        chooser.setPreviewPanel( colorLabel );
        chooser.getSelectionModel().addChangeListener( colorLabel );
        getContentPane().add( chooser, BorderLayout.CENTER );
        pack();
    }
    
    public static void main(String args[])
    {
        FavoriteColor favor = new FavoriteColor( "Jud", new Color( 255, 200, 100 ));
        favor.setVisible( true );
    }
    
    static private class ColorLabel
        extends JLabel
        implements ChangeListener
    {
        JColorChooser chooser;
        
        ColorLabel( String text, JColorChooser cc )
        {
            super( text );
            chooser = cc;
            setFont( new Font( "Serif", Font.BOLD, 36 ));
            setBorder( BorderFactory.createEmptyBorder( 10, 20, 10, 20 ));
            setHorizontalAlignment( SwingConstants.CENTER );
            setBackground( Color.white );
            setOpaque( true );
            setForeground( chooser.getColor() );
        }
        
        public void stateChanged( ChangeEvent e )
        {
            Color newColor = chooser.getColor();
            setForeground( newColor );
        }
    }
}
