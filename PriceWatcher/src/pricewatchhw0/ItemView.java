package pricewatchhw0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;


/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {
    
	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		
		
		
		void clicked();
	}
	
	/** Directory for image files: src/image in Eclipse. */
    
	/** View-page clicking listener. */
    private ClickListener listener;
    
    /** Create a new instance. */
    public ItemView() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if(e.getX() <55 && e.getY() < 55 && e.getX() > 15 && e.getY() > 15){
            	if (isViewPageClicked(e.getX(), e.getY()) && listener != null)// {
            		//if(e.getX() <60 && e.getY() < 60)
            		{
            			listener.clicked();
            			if(Desktop.isDesktopSupported())
            			{
            				try {
            					Desktop.getDesktop().browse(new URI("https://www.amazon.com/Element-ELEFW195R-720p-Certified-Refurbished/dp/B01M2BWNUO/ref=sr_1_4?s=tv&ie=UTF8&qid=1538624337&sr=1-4&keywords=tv"));
            				} catch (IOException e1) {
            					// TODO Auto-generated catch block
            					e1.printStackTrace();
            				} catch (URISyntaxException e1) {
            					// TODO Auto-generated catch block
            					e1.printStackTrace();
            				}
            			}
            			else
            			{
            			
            			}
            		}
            	}
            }
        });
    }
        
    /** Set the view-page click listener. */
    public void setClickListener(ClickListener listener) {
    	this.listener = listener;
    }
    
    /** Overridden here to display the details of the item. */
    @Override
    /** displays first scrren*/
	public void paint(Graphics g) {
        super.paint(g); 
       
        
        //--
        //-- WRITE YOUR CODE HERE!
        //--
        int x = 20, y = 30;
        g.drawString("Welcome to item manager!", x, y);
        y += 20;
        g.drawString("Click refresh to see your item and check its price!", x, y);
        

    }
    public int drawclass(Graphics g,String tag, String data)
    {
    	
    	return 1;
    }
    public void paint(Graphics g,Item product){
    	String lastPrice = product.returnLastPrice();
    	String price = product.returnCurrentPrice();
    	String percent = product.returnPercentString();
    	String name = product.returnName();
    	float value = product.returnPercent();
    	String web = product.returnURL();
        super.paint(g); 
        //change drawimages into its own method 
        
        // value is the value of the % so it is easy to tell if it is pos or neg
        
        int x = 20, y = 30;// change += 20 to a final varable that is = to 20
        y -=20;
        g.drawImage(getImage("arrow.jpg"), x, y,this);
        y += 60;
        g.drawString(name , x, y);
        y += 20;
        g.drawString("First Price : "+lastPrice, x, y);
        y += 20;
        g.drawString("Current Price : ", x, y);
        y += 20;
        g.drawString("Percentage Change : ", x, y);
        y -= 20;
        x += 80;
        if(value <0)// possibly make this its own method
        {
            g.setColor(Color.RED); //changes color to red if it is neg
        	try
            {
                Clip clip = AudioSystem.getClip();
                
                clip.open(AudioSystem.getAudioInputStream(getClass().getResource("no.wav"))); //needs to be in its own method
                clip.start();
                
               
                //pays audio file
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
        	
        }
        else
        {
        	g.setColor(Color.GREEN); //changes color to green if it is pos
        	try
            {
                Clip clip = AudioSystem.getClip();
                //URL url = new URL(getClass().getResource(IMAGE_DIR), "yes.wav");
                clip.open(AudioSystem.getAudioInputStream(getClass().getResource("yes.wav")));
                clip.start();
                //pays audio file
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
        }
        g.drawString(price, x, y);
        y += 20;
        x += 40;
        g.drawString(percent, x, y);
        x -= 120;
        y += 20;
        g.setColor(Color.BLACK);
        web = web.substring(0, 30);
        web = web + "...";
        g.drawString("URL : "+web, x, y);
        y += 20;
        g.drawImage(getImage("image.jpg"), x, y,this);

    }
    /** paints the graphics */
    //String lastPrice, String price , String percent , String name , float value , String web) {
    //product.returnLastPrice(),product.returnCurrentPrice(),product.returnPercentString(),product.returnName(),product.returnPercent(),product.returnURL()
    public void paint(Graphics g,String lastPrice, String price , String percent , String name , float value , String web) {
        super.paint(g); 
        //change drawimages into its own method 
        
        // value is the value of the % so it is easy to tell if it is pos or neg
        
        int x = 20, y = 30;// change += 20 to a final varable that is = to 20
        y -=20;
        g.drawImage(getImage("arrow.jpg"), x, y,this);
        y += 60;
        g.drawString(name , x, y);
        y += 20;
        g.drawString("First Price : "+lastPrice, x, y);
        y += 20;
        g.drawString("Current Price : ", x, y);
        y += 20;
        g.drawString("Percentage Change : ", x, y);
        y -= 20;
        x += 80;
        if(value <0)// possibly make this its own method
        {
            g.setColor(Color.RED); //changes color to red if it is neg
        	try
            {
                Clip clip = AudioSystem.getClip();
                
                clip.open(AudioSystem.getAudioInputStream(getClass().getResource("no.wav"))); //needs to be in its own method
                clip.start();
                
               
                //pays audio file
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
        	
        }
        else
        {
        	g.setColor(Color.GREEN); //changes color to green if it is pos
        	try
            {
                Clip clip = AudioSystem.getClip();
                //URL url = new URL(getClass().getResource(IMAGE_DIR), "yes.wav");
                clip.open(AudioSystem.getAudioInputStream(getClass().getResource("yes.wav")));
                clip.start();
                //pays audio file
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
        }
        g.drawString(price, x, y);
        y += 20;
        x += 40;
        g.drawString(percent, x, y);
        x -= 120;
        y += 20;
        g.setColor(Color.BLACK);
        web = web.substring(0, 30);
        web = web + "...";
        g.drawString("URL : "+web, x, y);
        y += 20;
        g.drawImage(getImage("image.jpg"), x, y,this);

    }
    
    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
    	
		if(Desktop.isDesktopSupported())
		{
			try {
				Desktop.getDesktop().browse(new URI("https://www.amazon.com/Element-ELEFW195R-720p-Certified-Refurbished/dp/B01M2BWNUO/ref=sr_1_4?s=tv&ie=UTF8&qid=1538624337&sr=1-4&keywords=tv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			
		}
    	return new Rectangle(20, 20, 30, 20).contains(x,  y);
    }
        
    /** Return the image stored in the given file. */
 
    public Image getImage(String file) {
        try {
        	//URL url = new URL(getClass().getClassLoader().getResourceAsStream(IMAGE_DIR), file);
            return ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
