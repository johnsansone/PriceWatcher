package pricewatchhw0;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.lang.Thread;
/**
* A dialog for tracking the price of an item.
*
* @author Yoonsik Cheon
*/
@SuppressWarnings("serial")
public class Main extends JFrame{

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(400, 300);
      
    /** Special panel to display the watched item. */
    private ItemView itemView;
      
    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

    /** Create a new dialog. */
    public Main() {
    	this(DEFAULT_SIZE);
    }
    
    /** Create a new dialog of the given screen dimension. */
    public Main(Dimension dim) {
        super("Price Watcher");
        setSize(dim);
        
        configureUI();
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        //setResizable(false);
        showMessage("Welcome!");
    }
  
    /** Callback to be invoked when the refresh button is clicked. 
     * Find the current price of the watched item and display it 
     * along with a percentage price change. */
    public class refreshButtonClicked implements ActionListener {

    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
    		showMessage("Refresh clicked!");
    	}

    }
    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. 
     * @throws URISyntaxException 
     * @throws IOException */
    public class viewPageClicked implements ActionListener {

    	public void actionPerformed(ActionEvent e) {  	
    		try {
				Desktop.getDesktop().browse(new URI("http://www.bestbuy.com/site/samsung-ue590-series-28-led-4k-uhd-monitor-black/5484022.p?skuId=5484022"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	showMessage("View clicked!");
    	}

    }
    //private void viewPageClicked() throws IOException, URISyntaxException {    	
	//	Desktop.getDesktop().browse(new URI("http://www.bestbuy.com/site/samsung-ue590-series-28-led-4k-uhd-monitor-black/5484022.p?skuId=5484022"));
    //	showMessage("View clicked!");
   // }
        
    /** Configure UI. */
    private void configureUI() {
        setLayout(new BorderLayout());
        JPanel control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16)); 
        add(control, BorderLayout.NORTH);
        JPanel board = new JPanel();
        board.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createEmptyBorder(10,16,0,16),
        		BorderFactory.createLineBorder(Color.GRAY)));
        board.setLayout(new GridLayout(1,1));
        itemView = new ItemView();
        //itemView.setClickListener(this.new refreshButtonClicked());
        board.add(itemView);
        add(board, BorderLayout.CENTER);
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
    }
      
    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	JButton refreshButton = new JButton("Refresh");
    	refreshButton.setFocusPainted(false);
		refreshButton.addActionListener(new refreshButtonClicked());
        panel.add(refreshButton);
        return panel;
    }

    /** Show briefly the given string in the message bar. */
    private void showMessage(final String msg) {
        msgBar.setText(msg);
         Thread threadrun = new Thread();
        
			//try {
			//	Thread.sleep(3 * 1000);
			//} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//} // 3 seconds

    		//if (msg.equals(msgBar.getText())) 
    		//	msgBar.setText(" ");
    	
		 	threadrun.start();
        class threadrun extends Thread{
        	
        	public void run(){
        		msgBar.setText("here");
        		try {
        			msgBar.setText("here");
        			Thread.sleep(3 * 1000); // 3 seconds

            		if (msg.equals(msgBar.getText())) {
            			msgBar.setText(" ");
            		}
				} 	
        		catch (InterruptedException e) {
				}
        		
        	}
        }
        Thread thread = new Thread(new threadrun());
	 	threadrun.start();
        //thread.run();
    }
    
    public static void main(String[] args) {
        new Main();
    }


}
