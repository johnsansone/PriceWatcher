package pricewatchhw0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		try {

		    FileInputStream fis = new FileInputStream("ItemList.ser");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    ItemList itemlist = (ItemList) ois.readObject();
		    ois.close();
			return itemlist;

		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		return null;
		
	}
	public void saveListToFile(ViewList viewlist) throws ClassNotFoundException
	{

		try {

		    //Write Student array to file.
		    FileOutputStream fos = new FileOutputStream("ViewList.ser",false);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(viewlist);
		    oos.close();
		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
	}
	public void saveItemToFile(ItemList itemlist) throws ClassNotFoundException
	{

		try {

		    //Write Student array to file.
		    FileOutputStream fos = new FileOutputStream("ItemList.ser",false);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(itemlist);
		    oos.close();
		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
