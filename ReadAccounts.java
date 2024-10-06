package coursework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadAccounts {
    private String URL;

    public ReadAccounts(String url) {
        this.URL = url;
    }

    public LinkedList<String> getFirstNames() throws IOException {
        LinkedList<String> firstNames = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(URL));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            firstNames.add(data[0]);
        }
        br.close();
        return firstNames;
    }

    public LinkedList<String> getLastNames() throws IOException {
        LinkedList<String> lastNames = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(URL));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            lastNames.add(data[1]);
        }
        br.close();
        return lastNames;
    }
    public LinkedList<Integer> getAccounts() throws IOException {
    	LinkedList<Integer> balances = new LinkedList<>();
    	BufferedReader br = new BufferedReader(new FileReader(URL));
    	String line;
    	while ((line = br.readLine()) != null) {
    		String[] data = line.split(",");
    		balances.add(Integer.parseInt(data[2]));
    	}
    	br.close();
    	return balances;
    }

    public LinkedList<Integer> getBalances() throws IOException {
        LinkedList<Integer> accountNumbers = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(URL));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            accountNumbers.add(Integer.parseInt(data[3]));
        }
        br.close();
        return accountNumbers;
    }

}