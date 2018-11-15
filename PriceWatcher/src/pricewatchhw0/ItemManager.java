package pricewatchhw0;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class ItemManager extends JPanel{

    private JList<ArrayList> menuList;
    public ItemManager(ArrayList list) {
        
        DefaultListModel<ArrayList> listModel = new DefaultListModel<>();
        listModel.addElement(list);
       
        
        menuList = new JList<>(listModel);
        
         
               
        
    }
     
      
	//public ItemManager() {
		// TODO Auto-generated constructor stub
//	}

}
