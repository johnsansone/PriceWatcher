package pricewatchhw0;

import java.util.ArrayList;

public class ViewList {
	// ref: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
	private ArrayList<ItemView> itemList = new ArrayList<ItemView>(1);

//a class for the list of items
	public ViewList() {
		
		// TODO Auto-generated constructor stub
	}
	/**  */
	
	public void addItem(ItemView item)
	{
		int size = itemList.size();
		//Item temp = new Item();
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
	public ItemView returnItem(int i)
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
	public boolean deleteItem(ItemView item)
	{
		int i = itemList.indexOf(item);
		if(i == -1)
		{
			return false;
		}
		itemList.remove(i);
		return true;
	}
	public boolean doesItemExist(ItemView item)
	{
		int i = itemList.indexOf(item);
		if(i == -1)
		{
			return false;
		}
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
