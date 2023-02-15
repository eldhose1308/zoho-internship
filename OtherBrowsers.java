import java.util.*;



class Browsers {
	private HashMap<String,Integer> history = new HashMap<String,Integer>();
	private static ArrayList<String> visitedUrls = new ArrayList<>();
	
	
	public Browsers() {	
	}
	
	
	public Browsers(ArrayList<String> history) {
		this.setHistory(history);
	}
	
	public void setHistory(ArrayList<String> history) {
		for(String url : history) {
			if(this.history.containsKey(url))
				this.history.put(url,this.history.get(url) + 1);
			else
				this.history.put(url, 1);
			this.getAllVisitUrlDetails();
		}
		
		this.setVisitedUrls(history);		
	}
	
	public void visitUrl(String url) {
		ArrayList<String> newUrl = new ArrayList<String>(Arrays.asList(url));
		this.setHistory(newUrl);
//		this.getVisitUrlDetails(url);
	}
	
	
	public int getVisitUrlCount(String url) {
		return this.history.get(url);
	}
	
	public void getVisitUrlDetails(String url) {
		System.out.println("\nVisited "+url + "    ## " + this.getVisitUrlCount(url)+"\n");
	}
	

	public void getAllVisitUrlDetails() {
		System.out.println("\n-------------------");
		 for (Map.Entry<String,Integer> url : this.history.entrySet()) {
				System.out.println("Visited "+url.getKey() + "    ## " + url.getValue());
	      }
		
		System.out.println("-------------------\n");

	}
	
	
	public HashMap<String,Integer> getHistory(){
		return history;
	}
	
	public void setVisitedUrls(ArrayList<String> new_visitedUrls) {
		for(String newUrls : new_visitedUrls)
			visitedUrls.add(newUrls);
	}
	
	public static ArrayList<String> getVisitedUrls() { 
		return visitedUrls;
	}
	
	
	public void WhoAmI() {
		System.out.println("I am a browser");
	}
	
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
	
	public void new_method() {
		System.out.println("Helloo");
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

public class OtherBrowsers {

	public static void main(String[] args) {

		int firefoxCount = 0,chromeCount = 0;
		Browsers browsers = new Browsers();
		browsers.WhoAmI();
		
		Firefox firefox = new Firefox();
		firefox.whoAmI();
		
		GoogleChrome googleChrome = new GoogleChrome();
		googleChrome.whoAmI();
		
		
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
		
		
		
//		Browsers[] allBrowsers = new Browsers[5];		
		
//		allBrowsers[0] = tabOne;
//		allBrowsers[1] = tabTwo;
//		allBrowsers[2] = tabThree;
//		allBrowsers[3] = tabFour;
//		allBrowsers[4] = tabFive;
		
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
        
        HashMap<String,Integer> history1 = browser1.getHistory();
//		System.out.println("Browser 1 :");
//        for (Map.Entry<String,Integer> url : history1.entrySet()) {
//            System.out.println(url.getKey() + "    ## " + url.getValue());
//        }
//		browser1.getAllVisitUrlDetails();
//		System.out.println("\n-------------------\n");

		
        urls.removeAll(urls);
		
        urls.add("www.google.com");
        urls.add("www.facebook.com");
        urls.add("www.twitter.com");
        urls.add("www.twitter.com");

        Browsers browser2 = new Browsers();
        browser2.setHistory(urls);

        
        // Exercise 4
        browser2.visitUrl("www.instagram.com");
        
        
        HashMap<String,Integer> history2 = browser2.getHistory();
//		System.out.println("Browser 2 :");
//		  for (Map.Entry<String,Integer> url : history2.entrySet()) {
//	            System.out.println(url.getKey() + "    ##   " + url.getValue());
//	        }
//		browser2.getAllVisitUrlDetails();
//		System.out.println("\n-------------------\n");

        
        
        ArrayList<String> visitedUrls = Browsers.getVisitedUrls();
        System.out.println("Visited Urls : ");
        for (String url : visitedUrls) {
            System.out.println(url);
        }		
		System.out.println("\n-------------------\n");

	}

}
