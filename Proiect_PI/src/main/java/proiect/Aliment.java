package proiect;

/**
 * Clasa Aliment care are ca si atribute: nume, cantitate si categorie
 */
public class Aliment {
	private String nume;
	private int cantitate;
	private String categorie;
/**
 * constructor implicit
 */
	public Aliment() {}
	/**
	 * Constructor cu parametrii
	 * @param nume reprezinta numele alimentului
	 * @param cantitate reprezinta cate bucati sunt din alimentul respectiv
	 * @param categorie reprezinta categoria de alimente din care face parte
	 */
	public Aliment(String nume, int cantitate, String categorie) {
		this.nume = nume;
		this.cantitate = cantitate ;
		this.categorie = categorie ;
	}
	/**
	 * @return String nume
	 */
	public String getNume() {
		return nume;
	} 
	/**
	 * @return int cantitate
	 */
	public int getCantitate() {
		return cantitate;
	}
	/**
	 * 
	 * @return String categorie
	 */
	public String getCategorie() {
		return categorie;
	}
	/**
	 * Functia seteaza numele
	 * @param nume
	 */
	public void setNume(String nume) {
		this.nume = nume ;
	}
	/**
	 * Functia seteaza cantitatea
	 * @param cantitate
	 */
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate ;
	}
	/**
	 * Functia seteaza categoria
	 * @param categorie
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie ;
	}
	
	 @Override
	public String toString() {
	    return   nume + " " + cantitate + " " + categorie + "\n" ;
	}
	 
	 
	 
	 


}
