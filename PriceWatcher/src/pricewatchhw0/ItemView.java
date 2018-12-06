package pricewatchhw0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.awt.Desktop;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/** A special panel to display the detail of an item. */


public class ItemView extends JPanel implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -3310899902362172734L;

	/**
	 * 
	 */

	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}
    JTextField textFieldedit;
    JTextField textFieldedit2;
    JFrame editwindow;

	private void addEditClicked(ActionEvent event) {
		try {
            URL url = new URL(textFieldedit2.getText());
            URLConnection conn = url.openConnection();
            conn.connect();

            if((textFieldedit2.getText().contains("bestbuy"))||(textFieldedit2.getText().contains("walmart"))||(textFieldedit2.getText().contains("target")))
            {
            	product.setName(textFieldedit.getText());
            	product.setURL(textFieldedit2.getText());
        
            	paintComponent(this.getGraphics());
	    
            	editwindow.dispose(); 
            }
            else
            {
            	JOptionPane.showMessageDialog(null,"This URL will not work as this website is not supported enter another please");
        	
            	
            }
        } catch (MalformedURLException e) {
        	JOptionPane.showMessageDialog(null,"This URL will not work as something is wrong with it enter another please");
        	return;
        } catch (IOException e) {
            // the connection couldn't be established
        }
		
	}

	private void cancleClicked(ActionEvent event) {

		editwindow.dispose(); 
	}
    public void edit(Item item)
    {
    	if(isClicked == 1)
		{
    	editwindow = new JFrame();

    	editwindow = new JFrame("Edit Menu");
    	editwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,5));
        //JPanel panel2 = new JPanel();
        panel.add(new JLabel("Item Name"));
        textFieldedit = new JTextField(10);
        
        panel.add(textFieldedit);
        
        textFieldedit.setText(item.returnName());
        JButton refresh = new JButton("Change");
        refresh.addActionListener(this::addEditClicked);

        //JPanel panel3 = new JPanel();
        panel.add(new JLabel("URL"));
        textFieldedit2 = new JTextField(10);
        textFieldedit2.setText(item.returnURL());
        panel.add(textFieldedit2);
        JButton remove = new JButton("Cancel");
        remove.addActionListener(this::cancleClicked);


        panel.add(refresh);
        panel.add(remove);

        //optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        //optionPane.add(panel,1);
        //dialog = optionPane.createDialog(null, "ADD");
        editwindow.add(panel);
        editwindow.pack();
        editwindow.setVisible(true);
		}
    }
	/** Directory for image files: src/image in Eclipse. */
	/** View-page clicking listener. */
    private ClickListener listener;
    private Item product;
    int isClicked = 0;
    //private int id;
    /** Create a new instance. */
    public ItemView(Item item) {
    	product = item;
        setBackground(Color.WHITE);
    	
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	listener.clicked();
            	if(isClicked == 0)
            	{
            		setBackground(Color.GRAY);
            		isClicked = 1;
            	}
            	else
            	{
            		setBackground(Color.WHITE);
            		isClicked = 0;
            	}
            	if(e.getX() <55 && e.getY() < 55 && e.getX() > 15 && e.getY() > 15){
            	if (isViewPageClicked(e.getX(), e.getY()) && listener != null)// {
            		//if(e.getX() <60 && e.getY() < 60)
            		{

            		if(Desktop.isDesktopSupported())
            		{
            			try {
            				Desktop.getDesktop().browse(new URI(product.returnURL()));
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
    /** Plays audio file yes.wav */
    public void yesAudio()
    {
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
    /** Plays audio file no.wav */
    public void noAudio()
    {
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
    /** Causes paint to be called so audio does not have to play every time something is repainted.*/
    public void re(Graphics g)
    {

        if(product.returnPercent() <=0)
        {
        	yesAudio();
        	
        }
        else
        {
           
            noAudio();
        }
        paint(g);
    }
    /** Overridden here to display the details of the item. */
    @Override
    /** paints the graphics */
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        //super.paint(g); 
        int x = 20, y = 30;// change += 20 to a final varable that is = to 20
        y -=20;
        g.drawImage(getImage("arrow.jpg"), x, y,this);
        y += 60;
        g.drawString(product.returnName() , x, y);
        y += 20;
        g.drawString("First Price :       $"+product.returnLastPrice(), x, y);
        y += 20;
        g.drawString("Current Price :     ", x, y);
        y += 20;
        g.drawString("Percentage Change : ", x, y);
        y -= 20;
        x += 80;
        if(product.returnPercent() <=0)
        {
        	
        	g.setColor(Color.GREEN); //changes color to green if it is pos
        }
        else
        {
            g.setColor(Color.RED); //changes color to red if it is neg
            
        }
        g.drawString("$"+ product.returnCurrentPrice(), x, y);
        y += 20;
        x += 40;
        g.drawString(product.returnPercentString(), x, y);
        x -= 120;
        y += 20;
        g.setColor(Color.BLACK);
        g.drawString("URL :                "+product.returnURL()+"...", x, y);
        y += 20;
        g.drawString("DATE :              "+product.returnDate(), x, y);
        g.dispose();    
        }
    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
    	return new Rectangle(20, 20, 30, 20).contains(x,  y);
    }
    public int returnisClicked()
    {
    	return isClicked;
    	
    }
    public void openURL()
    {

		if(Desktop.isDesktopSupported())
		{
			try {
				Desktop.getDesktop().browse(new URI(product.returnURL()));
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
        
    /** Return the image stored in the given file. */
 
    public Image getImage(String file) {
        try {
        	
            return ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
