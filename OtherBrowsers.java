import java.util.*;



class Browsers {
	private ArrayList<String> history = new ArrayList<String>();
	private static ArrayList<String> visitedUrls = new ArrayList<>();
	
	private String delimitHistory = "##";
	
	public Browsers() {	
	}
	
	
	public Browsers(ArrayList<String> history) {
		this.setHistory(history);
	}
	
	public void setHistory(String history) {
		int visitCount = 1;
		int index = 0;
		String urlString,urlCount;
		boolean urlFound = false;
		
		for(String url : this.history) {
			
			urlString = url.split(delimitHistory)[0];
			urlCount = url.split(delimitHistory)[1];


			if(history.equals(urlString)) {
				visitCount = Integer.parseInt(urlCount) + 1;
				this.history.set(index, urlString + delimitHistory + visitCount);
				urlFound = true;
			}

			index++;
			
		}
		
		if(!urlFound)
			this.history.add(history + delimitHistory + visitCount);
		

	}
	
	
	public void setHistory(ArrayList<String> history) {
		for(String url : history) {
			this.setHistory(url);
		}
		
		this.setVisitedUrls(history);		
	}
	
	
	public void visitUrl(String url) {
		this.setHistory(url);
	}
	
		
	

	public void getAllVisitUrlDetails() {
		System.out.println("\n-------------------");
		for (String url : this.history) {
			System.out.println(url);
		}
		
		System.out.println("-------------------\n");

	}
	
	
	public ArrayList<String> getHistory(){
		return history;
	}
	
	public void setVisitedUrls(ArrayList<String> new_visitedUrls) {
		for(String newUrls : new_visitedUrls)
			setVisitedUrls(newUrls);
	}
	
	
	public void setVisitedUrls(String url) {
		visitedUrls.add(url);
	}
	
	public static ArrayList<String> getVisitedUrls() { 
		return visitedUrls;
	}
	
	
	public void WhoAmI() {
		System.out.println("I am a browser");
	}
	
	
	
	// Member Inner Class
	class Bookmarks{
		ArrayList<String> bookmarks = new ArrayList<String>();
		
		public void setBookmarks(String bookmark) {
			this.bookmarks.add(bookmark);
		}
		
		public String[] getBookmarks() {
			int bookmarkSize = bookmarks.size();
			String[] allBookmarks = new String[bookmarkSize];
			for(int i = 0; i < bookmarkSize; i++)
				allBookmarks[i] = bookmarks.get(i);
			
			return allBookmarks;	
		}
		
	}
	
	
	
	
	// Static Inner Class
	static class History{
		public void display() {
			System.out.println("Inside static history class");
		}
	}
	
}



// Anonymous Inner Class
abstract class Shortcuts{
	abstract void display();
}


interface MultipleAccountContainers{
	public void addContainer(String container);
	public void leaveContainer(String container);
	public String[] viewAllContainers();
}




class GoogleChrome extends Browsers{
	//5
	private final String versionNumber = "1.0";
	private boolean isLocationAccessible,isCameraAccessible,isMicrophoneAccessible;
	
	public GoogleChrome() {
		super();
		this.isLocationAccessible = false;
		this.isCameraAccessible = false;
		this.isMicrophoneAccessible = false;
	}
	
	public GoogleChrome(ArrayList<String> history) {
		super(history);
	}
	
	public void whoAmI() {
		System.out.println("I am Google Chrome");
	}
	
	public void setPermissions(boolean isLocationAccessible,boolean isCameraAccessible,boolean isMicrophoneAccessible) {
		this.isLocationAccessible = isLocationAccessible;
		this.isCameraAccessible = isCameraAccessible;
		this.isMicrophoneAccessible = isMicrophoneAccessible;
	}
	
	public void setPermissions(boolean status) {
		this.isLocationAccessible = status;
		this.isCameraAccessible = status;
		this.isMicrophoneAccessible = status;
	}
	
	public String getVersionNumber() {
		return this.versionNumber;
	}
	
	public boolean getIsLocationAccessible() {
		return this.isLocationAccessible;
	}
	
	public boolean getIsCameraAccessible() {
		return this.isCameraAccessible;
	}
	
	public boolean getIsMicrophoneAccessible() {
		return this.isMicrophoneAccessible;
	}
	
}


class Firefox extends Browsers implements MultipleAccountContainers{
	List<String> containers = new ArrayList<String>();
	
	public Firefox() {
		super();
	}

	public Firefox(ArrayList<String> history) {
		super(history);
	}
	
	
	public void whoAmI() {
		System.out.println("I am Firefox");
	}
	
	public void addContainer(String container) {
		containers.add(container);
	}
	

	public void leaveContainer(String container) {
//		containers.remove(container);
		containers.removeAll(Collections.singleton(container));
	}
	

	public String[] viewAllContainers() {
		int containerSize = containers.size();
		String[] allContainers = new String[containerSize];
		for(int i = 0; i < containerSize; i++)
			allContainers[i] = containers.get(i);
		
		return allContainers;	
	}
}


/***************************************/


class InvalidURLException extends Exception{
	public InvalidURLException(String errorMessage) {
		super(errorMessage);
	}
}


class NoHistoryFoundException  extends Exception{
	public NoHistoryFoundException(String errorMessage) {
		super(errorMessage);
	}
}



class InvalidPositionException  extends Exception{
	public InvalidPositionException(String errorMessage) {
		super(errorMessage);
	}
}


class BrowserHistory{
	
	ArrayList<String> history = new ArrayList<String>();
	int currentIdx;
	
	public BrowserHistory(String homepage) {
		currentIdx = -1;
		try {
			this.visit(homepage);
		}catch(InvalidURLException iue) {
			System.out.println(iue.getMessage());
		}
	}
	
	public void visit(String url) throws InvalidURLException  {		
		if(!url.endsWith(".com") && !url.endsWith(".in") && !url.endsWith(".org")) {
			throw new InvalidURLException("Invalid url extension"); 
		}
		history.add(url);
		currentIdx++;
	}
	
	public String back(int steps) throws NoHistoryFoundException {
		if((currentIdx - steps) < 0) {
			throw new NoHistoryFoundException("No History Found");
		}
		currentIdx -= steps;
		return history.get(currentIdx);
	}
	
	public String forward(int steps) throws NoHistoryFoundException {
		if((currentIdx + steps) >= history.size()) {
			throw new NoHistoryFoundException("No History Found");
		}
		currentIdx += steps;
		return history.get(currentIdx);
	}
	
	public String get(int position) throws InvalidPositionException {
		if(position < 0) {
			throw new InvalidPositionException("Provide only positive values");
		}
		
		if(position > history.size()) {
			throw new ArrayIndexOutOfBoundsException("Invalid Position");
		}
		return history.get(position - 1);
		
	}
}


/***************************************/





public class OtherBrowsers {

	public static void main(String[] args) {

		int firefoxCount = 0,chromeCount = 0;
		Browsers browsers = new Browsers();
		browsers.WhoAmI();
		
		Firefox firefox = new Firefox();
		firefox.whoAmI();
		
		GoogleChrome googleChrome = new GoogleChrome();
		googleChrome.whoAmI();
		
		
		
		
		
		
		// Exercise 5
		Browsers.Bookmarks bookmarks = browsers.new Bookmarks();
		bookmarks.setBookmarks("www.zoho.com");
		bookmarks.setBookmarks("www.github.com");
		
		
		
		System.out.println("\n-------------------\n");
		String[] bookmarksList = bookmarks.getBookmarks();
		System.out.println("Bookmarks are :");
        for (String url : bookmarksList) {
            System.out.println(url);
        }
		System.out.println("\n-------------------\n");
		
		
		
		Browsers.History history = new Browsers.History();
		history.display();
		
		
		Shortcuts shortcuts1 = new Shortcuts() {
			void display() {
				System.out.println("Inside Anonymous Shortcut class");
			}
		};
		
		shortcuts1.display();
		
		
		
		// Exercise 6
		Scanner scanner = new Scanner(System.in);
		int choice = 0, steps;
		String userUrl, currentUrl;

		
		BrowserHistory browserHistory = new BrowserHistory("www.bing.com");
		System.out.println("-------------------");
		System.out.println("(1): Visit new url\t (2): Go Back steps\t(3): Go Forward steps\t(4): Get\t(5): Exit\t");
		System.out.println("-------------------");
		
		choice = scanner.nextInt();
		do {
			System.out.println("-------------------");
			System.out.println("(1): Visit new url\t (2): Go Back steps\t(3): Go Forward steps\t(4): Get\t(5): Exit\t");
			System.out.println("-------------------");
			choice = scanner.nextInt();
			System.out.println("Selected choice is : " + choice);

			
			switch(choice) {
				case 1:
					System.out.print("\n-- Enter url to visit -- \t");
					userUrl = scanner.next();
					try {
						browserHistory.visit(userUrl);
						System.out.println("Current URL is : " + userUrl);
					}catch(InvalidURLException iue) {
						System.out.println(iue.getMessage());
					}
					break;
					
				case 2:
					System.out.print("\n-- Enter steps to go back -- \t");
					steps = scanner.nextInt();
					try {
						currentUrl = browserHistory.back(steps);
						System.out.println(currentUrl);
					}catch(NoHistoryFoundException nhfe) {
						System.out.println(nhfe.getMessage());
					}
					break;
					
				case 3:
					System.out.print("\n-- Enter steps to go forward -- \t");
					steps = scanner.nextInt();
					try {
						currentUrl = browserHistory.forward(steps);
						System.out.println(currentUrl);
					}catch(NoHistoryFoundException nhfe) {
						System.out.println(nhfe.getMessage());
					}
					break;
					
				case 4:
					System.out.print("\n-- Enter position to visit from history -- \t");
					int position = scanner.nextInt();
					try {
						System.out.println("Browser URL at position : " + position + " : " + browserHistory.get(position));
					}catch(InvalidPositionException ipe) {
						System.out.println(ipe.getMessage());
					}
					break;
					
				default:
					System.out.println("\n-- Invalid Choice !! -- \n");
					break;
					
			}
			
		}while(choice != 5);
		scanner.close();
		
		
		
		
		//4
		//5
		System.out.println("\n-------------------\n");
		System.out.println("Google Chrome details\n");
		System.out.println("Location Access : " + googleChrome.getIsLocationAccessible());
		System.out.println("Camera Access : " + googleChrome.getIsCameraAccessible());
		System.out.println("Micophone Access : " + googleChrome.getIsMicrophoneAccessible());
		
		System.out.println("Version No : " + googleChrome.getVersionNumber());

		
		
		//5
		googleChrome.setPermissions(true);
		googleChrome.setPermissions(true, false, true);
		
		
		//6
		Browsers tabOne = new Firefox();
		Browsers tabTwo = new Firefox();
		Browsers tabThree = new Firefox();
		Browsers tabFour = new GoogleChrome();
		Browsers tabFive = new GoogleChrome();
		
		

		
		Browsers[] allBrowsers = {tabOne,tabTwo,tabThree,tabFour,tabFive};
		
		for(Browsers allBrowser : allBrowsers) {
			firefoxCount = (allBrowser instanceof Firefox) ? ++firefoxCount : firefoxCount;
			chromeCount = (allBrowser instanceof GoogleChrome) ? ++chromeCount : chromeCount;
		
		}
		
		System.out.println("\n-------------------\n");
		System.out.println("GoogleChrome instances count are : " + chromeCount);
		System.out.println("\n-------------------\n");
		
		
		int tabCount = firefoxCount + chromeCount;
		
		Integer openedTabsObject = tabCount;
		int openedTabsPrimitive = openedTabsObject;
		

		System.out.println("\n-------------------\n");
		System.out.println("Browser Tabs opened are : " + openedTabsPrimitive);
		System.out.println("\n-------------------\n");
		
		
		//7 ???
		MultipleAccountContainers mac = new Firefox();
		mac.addContainer("sampleContainer");
		
		
		//7
		Browsers browser= new Firefox();
		((Firefox)browser).addContainer("facebookContainer"); 
		((Firefox)browser).addContainer("Mails"); 
		((Firefox)browser).addContainer("PrivateBrowsing");  
		
		
		
		String[] containers1 = ((Firefox)browser).viewAllContainers();
		System.out.println("View all containers :");
        for (String url : containers1) {
            System.out.println(url);
        }
		System.out.println("\n-------------------\n");

		
		
		((Firefox)browser).leaveContainer("PrivateBrowsing"); 
		String[] containers2 = ((Firefox)browser).viewAllContainers(); 
		System.out.println("View all containers after removing PrivateBrowsing :");
		for (String url : containers2) {
	         System.out.println(url);
	    }
		System.out.println("\n-------------------\n");

		
		
		
		
//		System.exit(0);
		
		
		/*************/
		
		ArrayList<String> urls = new ArrayList<>();
       

        urls.add("www.people.zoho.com");
        urls.add("www.cliq.zoho.com");
        urls.add("www.meeting.zoho.com");


        Browsers browser1 = new Browsers(urls);
        
        ArrayList<String> history1 = browser1.getHistory();
		System.out.println("Browser 1 :");
		browser1.getAllVisitUrlDetails();
		
		
		
        urls.removeAll(urls);
		
        urls.add("www.google.com");
        urls.add("www.facebook.com");
        urls.add("www.twitter.com");
        urls.add("www.twitter.com");

        Browsers browser2 = new Browsers();
        browser2.setHistory(urls);

        
        // Exercise 4
        browser2.visitUrl("www.instagram.com");
        browser2.visitUrl("www.google.com");
        browser2.visitUrl("www.twitter.com");
        
        
        ArrayList<String> history2 = browser2.getHistory();
		System.out.println("Browser 2 :");
		browser2.getAllVisitUrlDetails();

        
        
        ArrayList<String> visitedUrls = Browsers.getVisitedUrls();
        System.out.println("Visited Urls : ");
        for (String url : visitedUrls) {
            System.out.println(url);
        }		
		System.out.println("\n-------------------\n");

	}

}
