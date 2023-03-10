import java.util.ArrayList;
import java.util.Scanner;

//Exercise 6


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
		String url = "";
		if(position < 0) {
			throw new InvalidPositionException("Provide only positive values");
		}
		
		try {
			url = history.get(position - 1);
		}catch(IndexOutOfBoundsException aiobe) {
			System.out.println("Invalid Position");
		}

		return url;
		
//		if(position > history.size()) {
//			throw new ArrayIndexOutOfBoundsException("Invalid Position");
//		}
		
		
		
	}
}


public class ExceptionHandling {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice = 1, steps;
		String userUrl, currentUrl;

		
		// Adding Home page to BrowserHistory
		BrowserHistory browserHistory = new BrowserHistory("www.bing.com");

		do {
			System.out.println("-------------------");
			System.out.println("(1): Visit new url\t (2): Go Back steps\t(3): Go Forward steps\t(4): Get\t(5): Exit\t");
			System.out.println("-------------------");
			
			try {
				choice = scanner.nextInt();
			}catch(Exception e) {
				choice = 5;
			}
			
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
					
				case 5:
					System.out.println("\n-- Exited from the program --\n");
					System.exit(0);
					
				default:
					System.out.println("\n-- Invalid Choice !! -- \n");
					break;
					
			}
			
		}while(choice != 5);
		scanner.close();
		
		

	}

}
