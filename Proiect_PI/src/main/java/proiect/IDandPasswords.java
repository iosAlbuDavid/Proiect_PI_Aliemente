package proiect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class IDandPasswords {

	List<List<String>> listaUtilizatori = new ArrayList<>();
	
    /**
     * Incarca lista utilizatorilor si parolelor acestora dintr-un fisier.
     * Fisierul trebuie sa contina numele de utilizator si parolele separate printr-un spatiu, fiecare utilizator fiind pe o linie noua.
     * @return 
     * 
     * @return O lista de liste cu nume de utilizator si parola asociata.
     * @throws IOException daca apare o eroare la accesarea fisierului.
     */	
    public void incarcareUtilizatori() throws IOException{
    	
    	List<String> userData = new ArrayList<>();
    	
    	try {
    		FileReader f = new FileReader("src/main/resources/useriSiParole.txt");
    		BufferedReader in = new BufferedReader(f);
    		String linie = in.readLine();
    		while(linie != null) {
    			userData.add(linie.split(" ")[0]); //username
    			userData.add(linie.split(" ")[1]); //parola
    			
    			listaUtilizatori.add(userData);
    			
    			linie = in.readLine();
    			userData = new ArrayList<>();
    		}
    		f.close();

    	}
    	catch (IOException e) {
    		System.out.println("Eroare la accesarea fisierului");
    	}
    	}
    
    public List<List<String>> getLoginInfo() throws IOException {
    	incarcareUtilizatori();
        return listaUtilizatori;
    }

    
    
}