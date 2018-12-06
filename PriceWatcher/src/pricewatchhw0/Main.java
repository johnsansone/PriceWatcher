package pricewatchhw0;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.Window;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;





import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
//import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

//import com.sun.glass.events.KeyEvent;

/**
* A dialog for tracking the price of an item.
*
* @author Yoonsik Cheon
*/
@SuppressWarnings("serial")
public class Main extends JFrame {
	JPanel cpanel;
	//private PriceFinder price;    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(700, 600);
    JProgressBar it = new JProgressBar();
    ItemView itemView2;
    ItemList itemList;
    JTextField textFieldadd;
    JTextField textFieldadd2;
    JPanel control;
    ViewList viewList;
    JFrame frame;
    inOut file;
    JProgressBar pbar;
    JScrollPane scroll;
    /** Special panel to display the watched item. */
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
    	it.setMinimum(0);
    	it.setMaximum(size*4);
    	it.setValue(0);
    	it.setStringPainted(true);
    	for(int i = 0;i < size; ++i)
    	{
    		
    		
    		getPrice(i);
    		//try {
			//	itemList.returnItem(i).setPrice(price.getPriceFromPage(itemList.returnItem(i).returnURL()));
			//} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//	JOptionPane.showMessageDialog(null,"unable to get price from website for :"+ itemList.returnItem(i).returnName());
			//} //returnURL()
            //viewList.returnItem(i).paint(viewList.returnItem(i).getGraphics());//, itemList.returnItem(i));
    		//viewList.returnItem(i).re(viewList.returnItem(i).getGraphics());
    	}
        showMessage("Refresh clicked!");
    } 
    private void editbuttonClicked(ActionEvent event)
    {
    	int size = viewList.getSize();
    	for(int i = 0;i < size; ++i)
    	{
    		if(viewList.returnItem(i).returnisClicked() == 0)
    		{
    			
    		}
    		else
    		{
    			viewList.returnItem(i).edit(itemList.returnItem(i));
    		}
    	//viewList.returnItem(i).returnisClicked();
    	}
    	
    }
    
    private void refreshButtonClicked() {
    	
    	int size = viewList.getSize();
    	it.setMinimum(0);
    	it.setMaximum(size*4);
    	it.setValue(0);
    	it.setStringPainted(true);
    	
    	for(int i = 0;i < size; ++i)
    	{
    		getPrice(i);
    		//try {
			//	itemList.returnItem(i).setPrice(price.getPriceFromPage(itemList.returnItem(i).returnURL()));
			//} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//JOptionPane.showMessageDialog(null,"unable to get price from website for :"+ itemList.returnItem(i).returnName());
			//}
            //viewList.returnItem(i).paint(viewList.returnItem(i).getGraphics());//, itemList.returnItem(i));

        	//PriceFinder price2 = new PriceFinder(viewList.returnItem(i),itemList.returnItem(i));
    		//price2.start();
    		//threadSearch(i);
    		//viewList.returnItem(i).rep(viewList.returnItem(i).getGraphics());
    		
    		//viewList.returnItem(i).rep(viewList.returnItem(i).getGraphics());
    	}
		
    	//getprice(0);
        showMessage("Refresh clicked!");
        pack();
        board.repaint();
    }
    private void addItemClicked(ActionEvent event) {
    	
    	//bring up method that creates item
    	addItem();
    	showMessage("Add Item clicked!");
    }
	private void addClicked(ActionEvent event) {

        try {
            URL url = new URL(textFieldadd2.getText());
            URLConnection conn = url.openConnection();
            conn.connect();

            if((textFieldadd2.getText().contains("bestbuy"))||(textFieldadd2.getText().contains("walmart"))||(textFieldadd2.getText().contains("target")))
            {
            	int size = viewList.getSize();
                itemList.addItem(new Item());
                itemList.returnItem(size).setDate();
                itemList.returnItem(size).setName(textFieldadd.getText());
                itemList.returnItem(size).setURL(textFieldadd2.getText());
        	    viewList.addItem(new ItemView(itemList.returnItem(size)));
        	    viewList.returnItem(size).setPreferredSize(new Dimension(300, 200));
        	    viewList.returnItem(size).setClickListener(this::viewPageClicked); 
        	    //System.out.println(" before get price");
        	    getPrice(size);
        	    board.add(viewList.returnItem(size));
        	    refreshButtonClicked();
               // try {
        	   try {
				file.saveItemToFile(itemList);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        		//} catch (ClassNotFoundException e) {
        			// TODO Auto-generated catch block
        		//	e.printStackTrace();
        		//}
        		frame.dispose(); 
            	
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

		frame.dispose(); 
	}
	
	private void RemoveItemClicked(ActionEvent event) {
    	removeClickedItems();
    	//bring up method that creates item
    	showMessage("Remove Item clicked!");
    }
    
    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. */
    private void viewPageClicked() {    	

		
    	showMessage("View clicked!");
    }
    
	public void getPrice(int i)
	{
		
		
		/*
		for (int i2 = 0; i2 <= 100; i2++) 
		{
			
	      try {
	        SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	        	  //it.updateBar(1);
	          }
	        });
	        java.lang.Thread.sleep(100);
	      } catch (InterruptedException e) {
	        ;
	      }
		}*/
		pack();
		new PriceFinder(viewList.returnItem(i),itemList.returnItem(i),it,itemList).start();
	}
    private void addItem()
    {
    	//JOptionPane.createDialog
    	frame = new JFrame("Add Menu");
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Add An Item To The List ");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,5));
        //JPanel panel2 = new JPanel();
        panel.add(new JLabel("Item Name"));
        textFieldadd = new JTextField(10);
        panel.add(textFieldadd);
        
        JButton refresh = new JButton("ADD");
        
        
        refresh.addActionListener(this::addClicked);

        //JPanel panel3 = new JPanel();
        panel.add(new JLabel("URL"));
        textFieldadd2 = new JTextField(10);
        panel.add(textFieldadd2);
        JButton remove = new JButton("Cancel");
        remove.addActionListener(this::cancleClicked);


        panel.add(refresh);
        panel.add(remove);

        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        optionPane.add(panel,1);
        //dialog = optionPane.createDialog(null, "ADD");
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        //panel.add(remove);
        
        //refresh.addActionListener(this::refreshButtonClicked);//(this::refreshButtonClicked);
        //remove.addActionListener(this::RemoveItemClicked);
        //refresh.addActionListener(this::addItemClicked);
        
        
    	/*
        try {
			itemList.returnItem(size).setPrice(price.getPriceFromPage(itemList.returnItem(size).returnURL()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null,"unable to get price from website for :"+ itemList.returnItem(size).returnName());
		}
        */
        /*
    	int size = viewList.getSize();
        itemList.addItem(new Item());
        itemList.returnItem(size).setDate();
        itemList.returnItem(size).setName(textField.getText());
        itemList.returnItem(size).setURL(textField2.getText());
	    viewList.addItem(new ItemView(itemList.returnItem(size)));
	    viewList.returnItem(size).setPreferredSize(new Dimension(300, 200));
	    viewList.returnItem(size).setClickListener(this::viewPageClicked); 
	    System.out.println(" before get price");
	    getPrice(size);
	    board.add(viewList.returnItem(size));
	    refreshButtonClicked();
        try {
	    file.saveItemToFile(itemList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }
    
    private void removeClickedItems()
    {
    	
    	int size = viewList.getSize();
    	for(int i = 0;i < size; i++)
    	{
    		if(viewList.returnItem(i).returnisClicked() > 0)
    		{
    			
    			board.remove(viewList.returnItem(i));
        		itemList.deleteItem(i);
                
        		viewList.deleteItem(i);
    			i--;
    			size--;

    	        try {
    				file.saveItemToFile(itemList);
    			} catch (ClassNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
    /** Configure UI. */
    private void configureUI() {
    	file = new inOut();
    	new Item();
    	//price = new PriceFinder();
        itemList = new ItemList();
        viewList = new ViewList();
        setLayout(new BorderLayout());
        control = makeControlPanel();
        
        control.setBackground(Color.WHITE);
        control.setOpaque(false);  
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16)); 
        add(control, BorderLayout.NORTH);
        board = new JPanel();
        board.setOpaque(false);
        board.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createEmptyBorder(10,16,0,16),
        		BorderFactory.createLineBorder(Color.GRAY)));
        board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
        
        //addItem();
        //addItem();
        //addItem();
        //addItem();
        scroll = new JScrollPane(board);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        add(scroll, BorderLayout.CENTER);
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
       // control.setComponentPopupMenu(popup);
        board.getRootPane().addMouseListener(new MouseAdapter() {
 
            @Override
            //need to make this work with the items as well later
            public void mousePressed(MouseEvent e) {
                //showPopup(e);
            	if(e.getButton()==3)
            	{
            	JDialog dialog = null;
                JOptionPane optionPane = new JOptionPane();
                optionPane.setMessage("Set Message");
                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(4,1));
                JButton refresh = new JButton("Refresh");
                JButton remove = new JButton("Remove");
                JButton add = new JButton("Add");
                JButton edit = new JButton("Edit");
                //editbuttonClicked
                panel.add(refresh);
                panel.add(remove);
                panel.add(add);
                panel.add(edit);
                refresh.addActionListener(Main.this::refreshButtonClicked);//(this::refreshButtonClicked);
                remove.addActionListener(Main.this::RemoveItemClicked);
                add.addActionListener(Main.this::addItemClicked);
                edit.addActionListener(Main.this::editbuttonClicked);
                
                optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                optionPane.add(panel,1);
                dialog = optionPane.createDialog(null, "Icon/Text Button");
                dialog.setVisible(true);
            	}
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
                //showPopup(e);
            }
 
           // private void showPopup(MouseEvent e) {
           //     if (e.isPopupTrigger()) {
                    //popup.show(e.getComponent(),
                            //e.getX(), e.getY());
            //    }
           // }
        });
        pack();
        /*
        for(int i = 0; i < itemList.getSize(); i++)
        {
        	viewList.addItem(new ItemView(itemList.returnItem(i)));
        	viewList.returnItem(i).setPreferredSize(new Dimension(300, 200));
        	viewList.returnItem(i).setClickListener(this::viewPageClicked);
        }
        for(int i = 0;viewList.getSize() > i ; i++)
        {
        	if(viewList.returnItem(i)==null)
        	{
        		
        	}
        	else
        	{
            	board.add(viewList.returnItem(i));
        		
        	}
        }
        */
        //System.out.println("this far1");
        loadItems();
        pack();
    }
    public void loadItems()
    {
    	try {
			itemList = file.loadItemFromFile();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(int i = 0;itemList.getSize() > i ; i++)
        {
        	if(itemList.returnItem(i)==null)
        	{
        		int counter = i;
        		while(itemList.returnItem(counter)==null && counter < itemList.getSize())
            	{
            		counter +=1;
            	}
        		if( counter == itemList.getSize())
        		{
        			if(itemList.returnItem(counter)==null)
                	{
        				itemList.trimSize();
                	}
        		}
        		else
        		{
        			itemList.addItem(itemList.returnItem(counter),i);
        			itemList.deleteItem(counter);
        		}
        	}
        	else
        	{
            	
        		
        	}
        }

        for(int i = 0; i < itemList.getSize(); i++)
        {
        	viewList.addItem(new ItemView(itemList.returnItem(i)));
        	viewList.returnItem(i).setPreferredSize(new Dimension(300, 200));
        	viewList.returnItem(i).setClickListener(this::viewPageClicked);
        
        }
        for(int i = 0;viewList.getSize() > i ; i++)
        {
        	if(viewList.returnItem(i)==null)
        	{
        		
        	}
        	else
        	{
            	board.add(viewList.returnItem(i));
        		
        	}
        }
        //System.out.println("this far 2");
        //System.out.println(itemList.getSize());
        refreshButtonClicked();
    }
    public Image getImage(String file) {
        try {
        	
            return ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    

class RefreshMenuListener implements MenuListener {

    @Override
    public void menuSelected(MenuEvent e) {
    	refreshButtonClicked();
    }

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
class AddMenuListener implements MenuListener {

    @Override
    public void menuSelected(MenuEvent e) {
    	//refreshButtonClicked();
    	addItem();
    }

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
class QMenuListener implements MenuListener {

    @Override
    public void menuSelected(MenuEvent e) {
    	JOptionPane.showMessageDialog(null,"BY: Micheal Sansone & Manali @ 2018");
    }

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	
    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {
    	cpanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenu addMenu = new JMenu("Add");
        addMenu.addMenuListener(new AddMenuListener());
        menubar.add(fileMenu);
        fileMenu.add(addMenu);
        JMenu refreshMenu = new JMenu("Refresh");
        refreshMenu.addMenuListener(new RefreshMenuListener());
        fileMenu.add(refreshMenu);
        
        JMenu fileMenu3 = new JMenu("About");
        fileMenu3.addMenuListener(new QMenuListener());
        fileMenu.add(fileMenu3);
        
        setJMenuBar(menubar);
    	JButton refreshButton = new JButton("Refresh");
    	JButton removeButton = new JButton("Remove");
    	JButton addButton = new JButton("Add");
    	refreshButton.setFocusPainted(false);
    	removeButton.setFocusPainted(false);
    	addButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        removeButton.addActionListener(this::RemoveItemClicked);
        addButton.addActionListener(this::addItemClicked);
        cpanel.add(refreshButton);
        cpanel.add(removeButton);
        cpanel.add(addButton);
        cpanel.add(it);
        return cpanel;
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
