package pricewatchhw0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {
    
	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		
		//https://www.amazon.com/Element-ELEFW195R-720p-Certified-Refurbished/dp/B01M2BWNUO/ref=sr_1_4?s=tv&ie=UTF8&qid=1538624337&sr=1-4&keywords=tv
		void clicked();
	}
	
	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "image.jpg";
    
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
        
    /** Set the view-page click listener. */
    public void setClickListener(ClickListener listener) {
    	this.listener = listener;
    }
    public void refreshButtonClicked(float lastPrice, float price , float percent , String name)
    {
    	//will cause paint to repaint the screen
    	
    }
    
    /** Overridden here to display the details of the item. */
    @Override
	public void paint(Graphics g) {
        super.paint(g); 
        //Dimension dim = getSize();
        
        //--
        //-- WRITE YOUR CODE HERE!
        //--
        int x = 20, y = 30;
        g.drawImage(getImage("image.jpg"), x, y,this);
        g.drawString("Welcome to item manager!", x, y);
        y += 20;
        g.drawString("Click refresh to see your item and check its price!", x, y);

    }
    public void paint(Graphics g,String lastPrice, String price , String percent , String name , float value) {
        super.paint(g); 
        //Dimension dim = getSize();
        
        //--
        //-- WRITE YOUR CODE HERE!
        //--
        int x = 20, y = 30;
        g.drawImage(getImage("image.jpg"), x, y,this);
        
        g.drawString(name , x, y);
        y += 20;
        g.drawString(lastPrice, x, y);
        y += 20;
        if(value <0)
        {
            g.setColor(Color.RED);
        	
        }
        else
        {
        	g.setColor(Color.GREEN);
        }
        g.drawString(price, x, y);
        y += 20;
        g.drawString(percent, x, y);
        y += 20;

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
}
