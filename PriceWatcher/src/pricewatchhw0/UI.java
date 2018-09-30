package pricewatchhw0;
import java.io.PrintStream;
import java.util.Scanner;
public class UI {
	Scanner in = new Scanner(System.in);
	private PrintStream out;
	
	private Item item;
	
	public UI(Item item){
		this(System.out,item);
	}
	public UI(PrintStream out, Item item){
		this.out = out;
		this.item = item;
	}
	
	public void showMessage(String msg) {
		// prints out messages
		out.println(msg);
	}
	public void showItem(){
		//show name of item
		out.println("hi, I am your item :" + this.item.getName());
	}
	public int promptUser(){
		//ask user to enter -1, 1, or 2
		out.println("enter -1 to quit, enter 1 to check price, enter 2 to show page");
		return -1;
	}
	public int readSelection() {
		//gets selection and shows output
		int n = in.nextInt();
		if (n == -1)
		{
			return n;
		}
		else if (n == 1)
		{
			out.println("the price was " + this.item.getFirstP());
			out.println("the price is currently " + this.item.getNewPrice());
			out.println("the difference is " + this.item.percentChange() + "%");
		}
		else if (n == 2){
			
			//java.awt.Desktop.getDesktop().browse(https://www.amazon.com/TCL-49S405-49-Inch-Ultra-Smart/dp/B01MYGISTO/ref=sr_1_1_sspa?s=tv&ie=UTF8&qid=1536783114&sr=1-1-spons&keywords=tv&psc=1);
		}
		//gets response and sends it out
		//sample
		return n;
	}

}
