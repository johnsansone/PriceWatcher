package pricewatchhw0;

import java.util.ArrayList;

public class ThreadManager {

	public ThreadManager() {
		// TODO Auto-generated constructor stub
	}
	private ArrayList<PriceFinder> itemList = new ArrayList<PriceFinder>(1);

	//a class for the list of items
		
		
		public ArrayList<PriceFinder> returnList()
		{
			return itemList;
		}
		/**  */
		public void addItem(PriceFinder item)
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
			
		}public void addItem(PriceFinder item, int i)
		{
			if(i<itemList.size())
			{
				itemList.add(i,item);
			}
		}
		public void trimSize()
		{
			itemList.trimToSize();
		}
		public int getSize()
		{
			int size = itemList.size();
			return size;
			
		}
		
		/*
		public ArrayList returnList()
		{
			return itemList;
		}*/
}
