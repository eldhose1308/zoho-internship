
public class Browser {

	final int HISTORY_SIZE = 10;
	static final int VISITEDURLS_SIZE = 20;
	
	private String[] history = new String[HISTORY_SIZE];
	private int historySize = -1;

	private static String[] visitedUrls = new String[VISITEDURLS_SIZE];
	private static int visitedUrlsSize = -1;

	
	public Browser() {
//		System.out.println("New Browser instance added");
	}
	
	public Browser(String[] history) {
		this.setHistory(history);
	}
	
	
	public void setHistory(String[] history) {
		if(history.length > HISTORY_SIZE | visitedUrls.length > VISITEDURLS_SIZE)
		{
			System.out.println("Array Size is out of bound");
			System.exit(0);
		}
		
		this.history = history;
		this.historySize = this.history.length;
		this.setVisitedUrls(history);

	}
	
	public void setVisitedUrls(String[] newVisitedUrl) {
		for(String url : newVisitedUrl)
			visitedUrls[++visitedUrlsSize] = url;
	}
	
	
	
	public static void getVisitedUrls() {
		for(int i = 0; i < visitedUrlsSize + 1; i++)
			System.out.println(visitedUrls[i]);
	}
	

	public void getHistory() {
		for(int i = 0; i < historySize; i++)
			System.out.println(history[i]);
		
		System.out.println("\n-------------------\n");
	}
	
	
	public void addNewUrl(String url) {
		
		this.history = reshapeArray(history,historySize,url);
		this.historySize++;

		visitedUrls = reshapeArray(visitedUrls,visitedUrlsSize + 1,url);
		visitedUrlsSize++;
		
	}
	
	public String[] reshapeArray(String[] arr,int length,String url) {
		String[] newArr = new String[length + 1];
		for(int i = 0; i < length; i++)
			newArr[i] = arr[i];
		
		newArr[length] = url;
				
		return newArr;
	}
	
	public static void main(String[] args) {
		String[] historyUrls1 = {"www.google.com","www.instagram.com"};
		Browser firefox = new Browser(historyUrls1);
		
		
		
		String[] historyUrls2 = {"www.facebook.com","www.twitter.com"};
		Browser chrome = new Browser(historyUrls2);
	    
		chrome.addNewUrl("www.zoho.com");
		
		
		System.out.println("Browser 1 :");
		firefox.getHistory();

		System.out.println("Browser 2 :");
		chrome.getHistory();
		
		
		System.out.println("Visited Urls : ");
		Browser.getVisitedUrls();
		
		
		
		
	}

}
