import java.util.ArrayList;  

class Browsers {
	private ArrayList<String> history = new ArrayList<>();
	private static ArrayList<String> visitedUrls = new ArrayList<>();
	
	public Browsers() {	
	}
	
	public Browsers(ArrayList<String> history) {
		this.setHistory(history);
	}
	
	public void setHistory(ArrayList<String> history) {
		this.history.addAll(history);
		setVisitedUrls(this.history);
	}
	
	public ArrayList<String> getHistory(){
		return history;
	}
	
	public void setVisitedUrls(ArrayList<String> new_visitedUrls) {
		visitedUrls.addAll(new_visitedUrls);
		
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
	
}


class Firefox extends Browsers implements MultipleAccountContainers{
	ArrayList<String> containers = new ArrayList<String>();
	public Firefox() {
		super();
	}
	
	public void whoAmI() {
		System.out.println("I am Firefox");
	}
	
	public void addContainer(String container) {
		containers.add(container);
	}
	

	public void leaveContainer(String container) {
		containers.remove(container);
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
		
		
		//6
		Browsers tabOne = new Firefox();
		Browsers tabTwo = new Firefox();
		Browsers tabThree = new Firefox();
		Browsers tabFour = new GoogleChrome();
		Browsers tabFive = new GoogleChrome();
		
		Browsers[] allBrowsers = new Browsers[5];
		
		allBrowsers[0] = tabOne;
		allBrowsers[1] = tabTwo;
		allBrowsers[2] = tabThree;
		allBrowsers[3] = tabFour;
		allBrowsers[4] = tabFive;
		
		
		for(int i = 0; i < 5; i++) {
			firefoxCount = (allBrowsers[i] instanceof Firefox) ? ++firefoxCount : firefoxCount;
			chromeCount = (allBrowsers[i] instanceof GoogleChrome) ? ++chromeCount : chromeCount;

		}
		
		System.out.println("\n-------------------\n");
		System.out.println("GoogleChrome instances count are : " + chromeCount);
		System.out.println("\n-------------------\n");
		
		
		
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

		
		
		
		
		System.exit(0);
		
		
		/*************/
		
		ArrayList<String> urls = new ArrayList<>();
       

        urls.add("www.people.zoho.com");
        urls.add("www.cliq.zoho.com");
        urls.add("www.meeting.zoho.com");


        Browsers browser1 = new Browsers(urls);
        
        ArrayList<String> history1 = browser1.getHistory();
		System.out.println("Browser 1 :");
        for (String url : history1) {
            System.out.println(url);
        }
		System.out.println("\n-------------------\n");

        urls.removeAll(urls);
		
        urls.add("www.google.com");
        urls.add("www.facebook.com");
        urls.add("www.twitter.com");

        Browsers browser2 = new Browsers();
        browser2.setHistory(urls);

        
        
        ArrayList<String> history2 = browser2.getHistory();
		System.out.println("Browser 2 :");
        for (String url : history2) {
            System.out.println(url);
        }
		System.out.println("\n-------------------\n");

        
        
        ArrayList<String> visitedUrls = Browsers.getVisitedUrls();
        System.out.println("Visited Urls : ");
        for (String url : visitedUrls) {
            System.out.println(url);
        }		
		System.out.println("\n-------------------\n");

	}

}
