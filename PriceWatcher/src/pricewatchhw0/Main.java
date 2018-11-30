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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private PriceFinder price;    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(700, 600);
    ItemView itemView2;
    ItemList itemList;
    ViewList viewList;
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
    	for(int i = 0;i < size; ++i)
    	{

    		itemList.returnItem(i).setPrice(price.getNewPrice());
            //viewList.returnItem(i).paint(viewList.returnItem(i).getGraphics());//, itemList.returnItem(i));
    		viewList.returnItem(i).re(viewList.returnItem(i).getGraphics());
    	}
        showMessage("Refresh clicked!");
    } private void refreshButtonClicked() {
    	
    	int size = viewList.getSize();
    	for(int i = 0;i < size; ++i)
    	{

    		itemList.returnItem(i).setPrice(price.getNewPrice());
            //viewList.returnItem(i).paint(viewList.returnItem(i).getGraphics());//, itemList.returnItem(i));
    		viewList.returnItem(i).re(viewList.returnItem(i).getGraphics());
    	}
        showMessage("Refresh clicked!");
    }
    private void addItemClicked(ActionEvent event) {
    	
    	//bring up method that creates item
    	addItem();
    	showMessage("Add Item clicked!");
    }
	private void addClicked(ActionEvent event) {
		JOptionPane.getRootFrame().dispose(); 
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
        
    private void addItem()
    {
    	//JOptionPane.createDialog
    	JDialog dialog = null;
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Add An Iten To The List ");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,5));
        //JPanel panel2 = new JPanel();
        panel.add(new JLabel("Item Name"));
        JTextField textField = new JTextField(10);
        panel.add(textField);
        
        JButton refresh = new JButton("ADD");
        refresh.addActionListener(this::addClicked);

        //JPanel panel3 = new JPanel();
        panel.add(new JLabel("URL"));
        JTextField textField2 = new JTextField(10);
        panel.add(textField2);
        JButton remove = new JButton("Cancel");


        panel.add(refresh);
        panel.add(remove);

        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        //optionPane.add(panel,1);
        dialog = optionPane.createDialog(null, "ADD");
        dialog.setVisible(true);
        
        //panel.add(remove);
        
        //refresh.addActionListener(this::refreshButtonClicked);//(this::refreshButtonClicked);
        //remove.addActionListener(this::RemoveItemClicked);
        //refresh.addActionListener(this::addItemClicked);
        
        
    	int size = viewList.getSize();
        itemList.addItem(new Item());
        itemList.returnItem(size).setDate();
        itemList.returnItem(size).setPrice(price.getNewPrice());
        itemList.returnItem(size).setName(textField.getText());
        itemList.returnItem(size).setURL(textField2.getText());
        viewList.addItem(new ItemView(itemList.returnItem(size)));
        viewList.returnItem(size).setPreferredSize(new Dimension(300, 200));
        viewList.returnItem(size).setClickListener(this::viewPageClicked); 
        board.add(viewList.returnItem(size));
        refreshButtonClicked();
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
    		}
    	}
    }
    
    /** Configure UI. */
    private void configureUI() {
    	new Item();
    	price = new PriceFinder();
        itemList = new ItemList();
        //JMenuItem menuItem;
        //JMenuItem menuItem2;
        //JMenuItem menuItem3;
        //JMenuItem menuItem4;
        //final JPopupMenu popup = new JPopupMenu();
        itemList.addItem(new Item());
        itemList.returnItem(0).setPrice(price.getNewPrice());
        /*
        menuItem = new JMenuItem("Add",new ImageIcon(getImage("add1.png")));
        menuItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {addItem();}});
        menuItem.setMnemonic(KeyEvent.VK_P);
        popup.add(menuItem);

        menuItem2 = new JMenuItem("Remove", new ImageIcon(getImage("remove1.jpg"))); 
        menuItem2.setMnemonic(KeyEvent.VK_F);
        menuItem2.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		removeClickedItems();
        		popup.hide();
            }
        }
        );

        popup.add(menuItem2);

        menuItem3 = new JMenuItem("Edit",
                new ImageIcon(getImage("edit1.png")));
        menuItem3.setMnemonic(KeyEvent.VK_F);
        menuItem3.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {

            	int size = viewList.getSize();
            	int count=0;
            	for(int i = 0;i < size; ++i)
            	{
            		count++;
            	}
            	popup.hide();
            }
        });
        popup.add(menuItem3);

        menuItem4 = new JMenuItem("View",
                new ImageIcon(getImage("view1.jpg")));
        menuItem4.setMnemonic(KeyEvent.VK_F);
        menuItem4.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	int size = viewList.getSize();
            	for(int i = 0;i < size; ++i)
            	{

            		if(viewList.returnItem(i).returnisClicked() > 0)
            		{
            			viewList.returnItem(i).openURL();
            		}
            	}
            	popup.hide();
            }
        });
        popup.add(menuItem4);
 		*/
        // add mouse listener
        viewList = new ViewList();
        setLayout(new BorderLayout());
        JPanel control = makeControlPanel();

        //control.setBackground(Color.BLACK);
        control.setOpaque(false);  
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16)); 
        //add(control, BorderLayout.NORTH);
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
        JScrollPane scroll = new JScrollPane(board);
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
                panel.setLayout(new GridLayout(3,1));
                JButton refresh = new JButton("Refresh");
                JButton remove = new JButton("Remove");
                JButton add = new JButton("Add");
                panel.add(refresh);
                panel.add(remove);
                panel.add(add);
                refresh.addActionListener(Main.this::refreshButtonClicked);//(this::refreshButtonClicked);
                remove.addActionListener(Main.this::RemoveItemClicked);
                add.addActionListener(Main.this::addItemClicked);
                
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
        pack();
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
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JMenuBar menubar = new JMenuBar();
        ImageIcon add = new ImageIcon(getImage("add.png"));
        JMenu fileMenu = new JMenu();
        fileMenu.addMenuListener(new AddMenuListener());
        fileMenu.setIcon(add);
        menubar.add(fileMenu);
        
        ImageIcon refresh = new ImageIcon(getImage("refresh.jpg"));
        JMenu fileMenu2 = new JMenu();
        fileMenu2.addMenuListener(new RefreshMenuListener());
        fileMenu2.setIcon(refresh);
        menubar.add(fileMenu2);
        
        ImageIcon question = new ImageIcon(getImage("question.jpg"));
        JMenu fileMenu3 = new JMenu();
        fileMenu3.addMenuListener(new QMenuListener());
        fileMenu3.setIcon(question);
        menubar.add(fileMenu3);
        
        setJMenuBar(menubar);
        //panel.add(menubar);
    	JButton refreshButton = new JButton("Refresh");
    	JButton removeButton = new JButton("Remove");
    	JButton addButton = new JButton("Add");
    	refreshButton.setFocusPainted(false);
    	removeButton.setFocusPainted(false);
    	addButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        removeButton.addActionListener(this::RemoveItemClicked);
        addButton.addActionListener(this::addItemClicked);
        //panel.add(refreshButton);
       // panel.add(removeButton);
        //panel.add(addButton);
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
