package pricewatchhw0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class inOut {

	public inOut() {
		// TODO Auto-generated constructor stub
	}
	public ViewList loadListFromFile() throws ClassNotFoundException
	{
		try {

		    FileInputStream fis = new FileInputStream("ViewList.ser");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    ViewList viewlist = (ViewList) ois.readObject();
		    ois.close();
			return viewlist;

		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		return null;
		
	}
	public ItemList loadItemFromFile() throws ClassNotFoundException
	{

	    //System.out.println("this far in load");
		try {
			File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
			//System.out.println(jarDir.getAbsolutePath());
			String local ="./ItemList.ser";
			System.out.println(local);
		    FileInputStream fis = new FileInputStream(local);
			//System.out.println(local);
			//InputStream fis = ClassLoader.getSystemClassLoader().getResourceAsStream("ItemList.ser");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    
			ArrayList<Item> items = (ArrayList<Item>) ois.readObject();
		    ItemList itemlist = new ItemList(items);
		    ois.close();
		    //System.out.println(itemlist.getSize());
		    System.out.println("worked read");
		    return itemlist;
			

		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
			InputStream fis = ClassLoader.getSystemClassLoader().getResourceAsStream("ItemList.ser");
		    ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
			ArrayList<Item> items = null;
			try {
				items = (ArrayList<Item>) ois.readObject();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    ItemList itemlist = new ItemList(items);
		    try {
				ois.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		    try {
		    	File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
			//System.out.println(jarDir.getAbsolutePath());
			String local = "./ItemList.ser";
		    FileOutputStream fos = new FileOutputStream(local,false);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(itemlist.returnList());
			    oos.close();
			    System.out.println(itemlist.getSize());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    return itemlist;
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		return null;
		
	}
	
	public void saveItemToFile(ItemList itemlist) throws ClassNotFoundException
	{

		try {

		    //Write Student array to file.

			File jarDir = new File("./ItemList.ser");
			//System.out.println(jarDir.getAbsolutePath());
		    FileOutputStream fos = new FileOutputStream(jarDir,false);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(itemlist.returnList());
		    oos.close();
		    System.out.println("worked");
		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
