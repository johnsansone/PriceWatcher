package pricewatchhw0;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemList implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ref: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
	private ArrayList<Item> itemList = new ArrayList<Item>(1);

//a class for the list of items
	public ItemList() {
		
		// TODO Auto-generated constructor stub
	}
	/**  */
	public void addItem(Item item)
	{
		int size = itemList.size();
		if(size ==1 && itemList.isEmpty())
		{
			itemList.add(item);
		}
		else
		{
			itemList.ensureCapacity(itemList.size()+1);
			itemList.add(item);
			
		}
		
	}
	public int getSize()
	{
		int size = itemList.size();
		return size;
		
	}
	public Item returnItem(int i)
	{
		try {
		    return itemList.get(i);
		}
		catch(IndexOutOfBoundsException e )
		{
			return null;
		}
		
	}
	/*
	public ArrayList returnList()
	{
		return itemList;
	}*/
	public boolean deleteItem(Item item)
	{
		int i = itemList.indexOf(item);
		if(i == -1)
		{
			return false;
		}
		itemList.remove(i);
		return true;
	}
	public boolean deleteItem(int i)
	{
		try {
		    itemList.remove(i);
		}
		catch(IndexOutOfBoundsException e )
		{
			return false;
		}
		return true;
		
	}
/*
 * how will this be sorted?
 * what sorting method do we want to use?
 * 
 */
}
