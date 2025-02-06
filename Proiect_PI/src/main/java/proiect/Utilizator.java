package proiect;

/**
 * Clasa Utilizator care are ca si atribute: nume si parola
 */
public class Utilizator {
    private String nume;
    private String parola;
     
    /**
     * constructor implicit
     */
    public Utilizator() {}
    
    /**
	 * Constructor cu parametrii
	 * @param nume reprezinta numele utilizatorului
	 * @param parola reprezinta parola utilizatorului la contul sau
	 */
    public Utilizator(String nume, String parola) {
        this.nume = nume;
        this.parola = parola;
    }
    
    /**
	 * @return String nume
	 */
    public String getNume() {
        return nume;
    }
    
    /**
	 * @return String parola
	 */
    public String getParola() {
        return parola;
    }
    
    /**
	 * Functia seteaza numele utilizatorului
	 * @param nume
	 */
    public void setNume(String nume) {
    	this.nume = nume;
    }
    
    /**
	 * Functia seteaza parola utilizatorului
	 * @param parola
	 */
    public void setParola(String parola) {
    	this.parola = parola;
    }
}