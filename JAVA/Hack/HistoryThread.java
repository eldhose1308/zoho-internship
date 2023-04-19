package Hack;

import java.util.*;
import java.lang.reflect.*;

class HistoryReader implements Runnable{
	Browser browser;
	
	public HistoryReader(Browser browser) {
		this.browser = browser;
	}
	
	
	public void run() {
		System.out.println("Reader");

		try {
			browser.getHistory();
		}catch(Exception e) {
			System.out.println("ReadThread" + e.getMessage());
		}
	}
}


class HistoryDeleter implements Runnable{
	Browser browser;

	public HistoryDeleter(Browser browser) {
		this.browser = browser;
	}
	
	
	public void run() {
		System.out.println("Deleter");
		try {
			browser.deleteHistory();
		}catch(Exception e) {
			System.out.println("DeleteThread " + e.getMessage());
		}
		
	}
}

class Browser{
	private int[] history;
	private int idx;
	private int arrSize;
	
	public Browser(int[] history) {
		this.history = history;
        this.arrSize = history.length;
		this.idx = 0;
	}
	
	public void setHistory(int url) {
		if(history.length > idx)
			this.history[idx++] = url;
		
		this.arrSize = history.length;
	}
	
	 public void getHistory() {
		synchronized(history) {
			System.out.println("Getting history ");
			if(arrSize == 0) {
				System.out.println("No elements to show ");
				return;
			}
			for(int i = 0; i < arrSize; i++) {
				System.out.println("Showing - " + history[i] + " : " + arrSize);
			}
		}
	}
	
	 public void deleteHistory() {
		synchronized(history) {
			System.out.println("Deleting history ");

			int tempSize = arrSize;
			
			for(int i = 0; i < tempSize; i++) {
				System.out.println("Deleting - " + history[i] + " : " + arrSize);
				history[i] = 0;
				this.arrSize--;
			}
		}
		
	}
}


public class HistoryThread {

	public static void main(String[] args) {

		int capacity = 100;
		int[] tempArr = new int[capacity];
		for(int i = 0; i < capacity; i++) 
			tempArr[i] = (i+1) * 10;
		
		Browser browser = new Browser(tempArr);
        
		HistoryReader historyReader = new HistoryReader(browser);
		HistoryDeleter historyDeleter = new HistoryDeleter(browser);

        Thread historyReaderThread = new Thread(historyReader);
        Thread historyDeleterThread = new Thread(historyDeleter);
        
        
        try {
	        historyReaderThread.start();

	        historyDeleterThread.start();
	        historyDeleterThread.join();
	        
//	        historyReaderThread.start();
// 	        historyReaderThread.join();
	        
 	        
 	       Field arrSizeField = Browser.class.getDeclaredField("arrSize");
 	       arrSizeField.setAccessible(true);
 	       int arrSize =  (Integer) arrSizeField.get(browser);
 	        
	       System.out.println("Final length of array is " + arrSize);
	        
        }catch(IllegalThreadStateException itse) {
        	System.out.println("IllegalThreadStateException " + itse);
        }catch(Exception e) {
        	System.out.println("Exception " + e);
        }
        

       
	}

}


