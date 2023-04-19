package Hack;

import java.util.*;
import java.io.*;

public class Hacker {



	
	public boolean writeHistoryFile(ArrayList<String> historyList) {
		boolean status = false;
		try {
			String packageName = Hacker.class.getPackage().getName();
            String filePath = packageName.replace(".", "/");
            File file = new File("src/" + filePath + "/History.txt");
            if (file.createNewFile()) 
                System.out.println("Text file created successfully.");
          
            
            
            FileWriter writer = new FileWriter(file);
            
            for (String url : historyList) 
                writer.write(url + "\n");
            
        	status = true;
            writer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
		
		return status;
	}
	

	public ArrayList<String> readHistoryFile() {
		ArrayList<String> modifiedHistoryList = new ArrayList<String>();
		try {
			String packageName = Hacker.class.getPackage().getName();
            String filePath = packageName.replace(".", "/");
            File file = new File("src/" + filePath + "/History.txt");
            
            
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) 
                modifiedHistoryList.add(line);
            
            bufferedReader.close();
		}catch(IOException ioe) {
            System.out.println(ioe.getMessage());
		}catch(Exception e) {
            System.out.println(e.getMessage());
		}
		
		return modifiedHistoryList;
	}

	public void updateHistoryFile() {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Hacker hacker = new Hacker();
		BrowserHistorys browserHistorys = new BrowserHistorys();
		
		
		String fixedUrls[] = {"google.com","twitter.com","wiki.com"};
		for(String url : fixedUrls) {
			try {
				browserHistorys.visit(url);
			}catch(InvalidURLException ire) {
				System.out.println(ire.getMessage());
			}
		}
		
		boolean writeStatus = hacker.writeHistoryFile(browserHistorys.fetchHistory());
		String writeMessage = (writeStatus) ? "Data successfully written to text file" : "Data could not be written";
		System.out.println(writeMessage);
		
		System.out.println("\nUrls saved in txt file are :");
		ArrayList<String> allUrls = hacker.readHistoryFile();
			for(String url : allUrls) 
				System.out.println(url);
		
			
		
	}

}
