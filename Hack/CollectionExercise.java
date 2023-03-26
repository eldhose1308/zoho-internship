package Hack;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


class InvalidURLException extends Exception{
	public InvalidURLException(String errorMessage) {
		super(errorMessage);
	}
}



class BrowserHistorys {
	
	List<String> historyList = new ArrayList<String>();
	Set<String> historySet = new HashSet<String>();
	
	HashMap<String,List<String>> historyMap = new HashMap<String,List<String>>();
	
	int currentIdx;
	
	public BrowserHistorys() {
		currentIdx = -1;
	}
	
	public void visit(String url) throws InvalidURLException  {	
		if(!url.endsWith(".com") && !url.endsWith(".in") && !url.endsWith(".org")) {
			throw new InvalidURLException("Invalid url extension"); 
		}

		String urlExtension = url.split("\\.")[url.split("\\.").length - 1];
		
		historyList.add(url);
		
		ArrayList<String> tempUrls = new ArrayList<String>();
		if(historyMap.containsKey(urlExtension)) {
			tempUrls = (ArrayList<String>)historyMap.get(urlExtension);
			tempUrls.add(url);
		}else
			tempUrls = new ArrayList<String>(Arrays.asList(url));
		
		historyMap.put(urlExtension, tempUrls);
		

		currentIdx++;
	}
	
	
	public void sort() {
		Collections.sort(historyList);
	}
	
	
	public String deleteHistory(int index) {
		String url = historyList.get(index);
		String urlExtension = url.split("\\.")[url.split("\\.").length - 1];
		
		historyList.remove(index);
		historyMap.put(urlExtension, historyList);

		return url;
	}
	
	
	public void deleteHistory(String extension) {
		historyList.removeIf(history -> history.endsWith(extension));
		historyMap.put(extension, historyList);
		
	}

	public ArrayList<String> fetchHistory() {
		return (ArrayList<String>) historyList;
	}
	

	public ArrayList<String> fetchHistory(String extension) {
		ArrayList<String> urlsWithExtension = new ArrayList<String>();
		urlsWithExtension = (ArrayList<String>)historyMap.get(extension);
		return urlsWithExtension;
	}
	

	public ArrayList<String> search(String word, String type) {
		ArrayList<String> searchResults = new ArrayList<String>();
		
		if(type.equals("extension"))
			searchResults = this.fetchHistory(word);
		
		else if(type.equals("word"))
			for(List<String> urlList : historyMap.values()) 
				for(String url : urlList)
					if(url.contains(word))
						searchResults.add(url);
			
			/*
			historyMap.forEach((extension, urlList) -> {
				urlList.forEach(url -> {
					if(url.contains(word)) 
						searchResults.add(url);
					
				});
			});
			*/
		
		
		return searchResults;
		
	}
	
	public int size() {
		return historyList.size();
	}

	public int size(String extension) {
		int historySize = 0;
		
		try {
			historySize = historyMap.get(extension).size();
		}catch(NullPointerException npe) {
			System.out.println("No History found on the extension " + extension);
			historySize = 0;
		}
		
		return historySize;
	}
	
	
	public void updateHistory(int index,String url) {
		try {
			historyList.set(index,url);
		}catch(IndexOutOfBoundsException iobe) {
			System.out.println("No index found ");
		}catch(Exception npe) {
			System.out.println("No index found ");
		}
		
		String extension = url.split("\\.")[url.split("\\.").length - 1];
		historyMap.put(extension, historyList);
		
	}
	
	
	/**
	 * 
	 *  Previous exercise methods
	 * 
	 * */
	public String back(int steps) {
		currentIdx -= steps;
		return historyList.get(currentIdx);
	}
	
	public String forward(int steps) {
		currentIdx += steps;
		return historyList.get(currentIdx);
	}
	
	public String get(int position) {
		String url = "";
		url = historyList.get(position - 1);
		return url;
		
	}
}


public class CollectionExercise {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int choice = 1, index;
		String userUrl, tempUrl, tempExtension;
		ArrayList<String> allUrls;
		
		// Adding Home page to BrowserHistory
		BrowserHistorys browserHistorys = new BrowserHistorys();

		do {
			System.out.println("-------------------");
			System.out.println("(1): Visit new url\t (2): Sort\t(3): Delete History with index\t(4): Delete History with extension\n"
					+ "(5): Fetch History\t(6): Fetch History with extension\t(7): Search with extension\n"
					+ "(8): Search word\t(9): Size Total\t(10): Size of extesnion\t(11): Update History\t(12): Exit\n");
			System.out.println("-------------------");
			
			try {
				choice = scanner.nextInt();
			}catch(Exception e) {
				choice = 1;
			}
			switch(choice) {
				case 1:
					System.out.print("\n-- Enter url to visit -- \t");
					userUrl = scanner.next();
					try {
						browserHistorys.visit(userUrl);
					}catch(InvalidURLException ire) {
						System.out.println(ire.getMessage());
					}
					break;
					
				case 2:
					browserHistorys.sort();
					break;
					
				case 3:
					System.out.print("\n-- Enter index of url to delete -- \t");
					index = scanner.nextInt();
					tempUrl = browserHistorys.deleteHistory(index);
					System.out.println("Deleted " + tempUrl + " at position " + index);
					break;
					
				case 4:
					System.out.print("\n-- Enter extension of url to delete -- \t");
					tempExtension = scanner.next();
					browserHistorys.deleteHistory(tempExtension);
					System.out.println("Deleted urls of extension " + tempExtension);
					break;
					
				case 5:
					allUrls = browserHistorys.fetchHistory();
					for(String url : allUrls) 
						System.out.println(url);
					
					break;
					
				case 6:
					System.out.print("\n-- Enter  url to fecth (extension) -- \t");
					tempExtension = scanner.next();
					allUrls = browserHistorys.fetchHistory(tempExtension);
					for(String url : allUrls) 
						System.out.println(url);
					
					break;
					
				case 7:
					System.out.print("\n-- Enter url to search (extension) -- \t");
					tempExtension = scanner.next();
					allUrls = browserHistorys.search(tempExtension,"extension");
					for(String url : allUrls) 
						System.out.println(url);

					break;
					
				case 8:
					System.out.print("\n-- Enter url to search (word) -- \t");
					tempExtension = scanner.next();
					allUrls = browserHistorys.search(tempExtension,"word");
					for(String url : allUrls) 
						System.out.println(url);

					break;
					
				case 9:
					System.out.println(browserHistorys.size());
					break;
					
				case 10:
					System.out.print("\n-- Enter extension to find size of -- \t");
					tempExtension = scanner.next();
					System.out.println(browserHistorys.size(tempExtension));
					break;
					
				case 11:
					System.out.print("\n-- Enter index and url to update comma seperated -- \t");
					index = scanner.nextInt();
					tempUrl = scanner.next();
					browserHistorys.updateHistory(index, tempUrl);
					break;
					
				//case 12:
				//	browserHistorys.writeHistoryFile(browserHistorys.fetchHistory());
				//	break;
					
					
				//case 13:
				//	allUrls = browserHistorys.readHistoryFile();
				//	for(String url : allUrls) 
				//		System.out.println(url);
					
				//	break;
					
				default:
					System.out.println("\n-- Invalid Choice !! -- \n");
					break;
						
			}
			
		}while(choice != 12);
		scanner.close();
	}

}
