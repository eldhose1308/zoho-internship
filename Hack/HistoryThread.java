package Hack;

import java.util.*;

class HistoryReader implements Runnable{
	Browser browser;
	
	
	public HistoryReader(Browser browser) {
		this.browser = browser;
	}
	
	
	public void run() {
		System.out.println("Reader");
		try {
			ArrayList<String> history = browser.getHistory();
			System.out.println(history.size());
			
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
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
			browser.clearHistory();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}



class Browser{
	ArrayList<String> history = new ArrayList<String>();
	
	public void setHistory(String url) {
		synchronized(history) {
			this.history.add(url);
		}
    }
	

	public ArrayList<String> getHistory() {
        return history;
    }
	
	public void clearHistory() {
		synchronized(history) {
			Iterator<String> iterator = history.iterator();
		    while (iterator.hasNext()) {
		    	iterator.next();
		    	iterator.remove();
			}
		}
	
	}
	
	
}

public class HistoryThread {

	public static void main(String[] args) {

		Browser browser = new Browser();
//		String[] visitedUrls = {"google.com","twitter.com","zoho.com"};
		
		for(int i = 0; i < 999; i ++)
			browser.setHistory(String.valueOf(i));
		
	
		
		
		HistoryReader historyReader = new HistoryReader(browser);
		HistoryDeleter historyDeleter = new HistoryDeleter(browser);

        Thread historyReaderThread = new Thread(historyReader);
        Thread historyDeleterThread = new Thread(historyDeleter);

        
        try {
	        historyReaderThread.start();
	        historyReaderThread.join();
	        historyDeleterThread.start();
	        
	        
	        
//	        historyReaderThread.join();
	        
//	        historyReaderThread.start();
	        
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        
	}

}


