package pricewatchhw0;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
* A dialog for tracking the price of an item.
*
* @author Yoonsik Cheon
*/
@SuppressWarnings("serial")
public class Main extends JFrame {
	private Item product;
	private PriceFinder price;    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(700, 600);
    ItemView itemView2;
    ItemList itemList;
    ViewList viewList;
    /** Special panel to display the watched item. */
    private ItemView itemView;
    private JPanel board;
      
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
    private void refreshButtonClicked(ActionEvent event) {
    	
    	int size = viewList.getSize();
    	for(int i = 0;i < size; ++i)
    	{

    		itemList.returnItem(i).setPrice(price.getNewPrice());
            //viewList.returnItem(i).paint(viewList.returnItem(i).getGraphics());//, itemList.returnItem(i));
    		viewList.returnItem(i).re(viewList.returnItem(i).getGraphics());
    	}
        showMessage("Refresh clicked!");
    }
    private void addItem(ActionEvent event) {
    	
    	//bring up method that creates item
    	showMessage("Add Item clicked!");
    }
    
    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. */
    private void viewPageClicked() {    	
    	
    	showMessage("View clicked!");
    }
        
    private void addItem()
    {
    	int size = viewList.getSize();
        itemList.addItem(new Item());
        itemList.returnItem(size).setPrice(price.getNewPrice());
        viewList.addItem(new ItemView(itemList.returnItem(size)));
        viewList.returnItem(size).setPreferredSize(new Dimension(300, 200));
        viewList.returnItem(size).setClickListener(this::viewPageClicked); 
        board.add(viewList.returnItem(size));
    	
    }
    /** Configure UI. */
    private void configureUI() {
    	product = new Item();
    	price = new PriceFinder();
        itemList = new ItemList();

        //itemList.addItem(new Item());
        //itemList.returnItem(0).setPrice(price.getNewPrice());
        
        viewList = new ViewList();
        setLayout(new BorderLayout());
        JPanel control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16)); 
        add(control, BorderLayout.NORTH);
        board = new JPanel();
        board.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createEmptyBorder(10,16,0,16),
        		BorderFactory.createLineBorder(Color.GRAY)));
        board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
        addItem();
        addItem();
        addItem();
        addItem();
        JScrollPane scroll = new JScrollPane(board);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        add(scroll, BorderLayout.CENTER);
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
        pack();
    }
      
    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	JButton refreshButton = new JButton("Refresh");
    	refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        panel.add(refreshButton);
        return panel;
    }

    /** Show briefly the given string in the message bar. */
    private void showMessage(String msg) {
        msgBar.setText(msg);
        new Thread(() -> {
        	try {
				Thread.sleep(3 * 1000); // 3 seconds
			} catch (InterruptedException e) {
			}
        	if (msg.equals(msgBar.getText())) {
        		SwingUtilities.invokeLater(() -> msgBar.setText(" "));
        	}
        }).start();
    }
    
    public static void main(String[] args) {
        new Main();
    }

}
