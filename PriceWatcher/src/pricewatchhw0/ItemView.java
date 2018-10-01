package pricewatchhw0;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pricewatchhw0.Main.refreshButtonClicked;



/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {
    
	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}
	
	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "/image/";
        
	/** View-page clicking listener. */
    private ClickListener listener;
    
    /** Create a new instance. */
    public ItemView() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
            		listener.clicked();
            	}
            }
        });
    }
        
    /** Set the view-page click listener. 
     * @param object */

    public void setClickListener(ClickListener listener) {
    	this.listener = listener;
    }
   // public void setClickListener(Object obj) {
    	
    //	this.viewPageClicked();
   // }
    
    /** Overridden here to display the details of the item. */
    @Override
	public void paint(Graphics g) {
        super.paint(g); 
        Dimension dim = getSize();
        
        //--
        //-- WRITE YOUR CODE HERE!
        //--
        int x = 20, y = 30;
         g.drawImage(getImage("dolar.png"), x, y, this);
        //g.drawString("[View]", x, y);
        y += 20;
        g.drawString("Hi, I am your item!", x, y);

    }
    
    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
    	//--
    	//-- WRITE YOUR CODE HERE
    	//--
    	return new Rectangle(20, 20, 30, 20).contains(x,  y);
    }
        
    /** Return the image stored in the given file. */
    public Image getImage(String file) {
        try {
        	URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	public void setClickListener(refreshButtonClicked refreshButtonClicked) {
		// TODO Auto-generated method stub
		
	}

}
