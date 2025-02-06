package proiect;

import java.io.IOException;

/**
 * Clasa App va rula aplicatia in sine
 */
public class App {
	

    public static void main(String[] args) throws IOException {
    	IDandPasswords idandPasswords = new IDandPasswords(); 
    	
    	LoginScreen loginScren = new LoginScreen(idandPasswords.getLoginInfo());
    	
    }
    

}