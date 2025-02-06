package proiect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;


/**
 * Clasa MainScreen va face legatura catre AddScreen si RemoveScreen si va afisa un mesaj de alerta cand stocul este pe 0
 */
public class MainScreen implements ActionListener{
	
	JFrame frmMain = new JFrame();
	JButton addButton = new JButton("Add");
	JButton afisareButton = new JButton("Afisare");
	JButton LogoutButton = new JButton("Logout");

	JTextArea  alertMessageArea = new JTextArea ();
	JLabel WelcomeMessageLable = new JLabel();

	String userID;
	/**
     * Constructor implicit
     * @param userID ID-ul utilizatorului care s-a autentificat
     */
	public MainScreen(String userID) {
		this.userID = userID;
		frmMain.getContentPane().setLayout(null);
		WelcomeMessageLable.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		
		WelcomeMessageLable.setBounds(159, 11, 250, 25);
		WelcomeMessageLable.setText("Welcome, " + userID + "!");
		
		LogoutButton.setBounds(189, 195, 100, 25);
		LogoutButton.setFocusable(false);
		LogoutButton.addActionListener(this);
		
		
		
		addButton.setBounds(189, 84, 100, 25);
		addButton.setFocusable(false);
		addButton.addActionListener(this);
		
		alertMessageArea.setBounds(25, 250, 450, 150);
        alertMessageArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        alertMessageArea.setEditable(false);
        alertMessageArea.setLineWrap(true);
        alertMessageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(alertMessageArea);
        scrollPane.setBounds(25, 250, 450, 150);
        frmMain.getContentPane().add(scrollPane);
		
		
		afisareButton.setBounds(189, 139, 100, 25);
		afisareButton.setFocusable(false);
		afisareButton.addActionListener(this);
		
		frmMain.getContentPane().add(WelcomeMessageLable);
		frmMain.getContentPane().add(addButton);
		frmMain.getContentPane().add(LogoutButton);
		frmMain.getContentPane().add(afisareButton);
		frmMain.setTitle("Main");
		
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setSize(510, 460);
        frmMain.setVisible(true);
        
        alertaStocRedus();
		
	}
	
	/**
     * Functia cauta alimentele care sunt in stoc redus (<= 2)
     * @param listaAlimente Acesta este primul parametru
     * @return List<Aliment> Returneaza o lista de alimente care sunt in stoc redus
     */
	public static List<Aliment> alertLowStock(List<Aliment> listaAlimente) {
        List<Aliment> alimenteCuStocRedus = new ArrayList<>();
        for (Aliment aliment : listaAlimente) {
            if (aliment.getCantitate() <= 2) {
                alimenteCuStocRedus.add(aliment);
            }
        }
        return alimenteCuStocRedus;
    }

	/**
    * Incarca lista de alimente din fisier
    * @return List<Aliment> Lista de alimente
    * @throws IOException daca apare o eroare la citirea fisierului
    */
   public List<Aliment> incarcareAlimente() throws IOException {
       List<Aliment> listaAlimente = new ArrayList<>();
       try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/listele.txt"))) {
           String linie;
           while ((linie = reader.readLine()) != null) {
               String[] parts = linie.split(" ");
               listaAlimente.add(new Aliment(parts[0], Integer.parseInt(parts[1]), parts[2]));
           }
       } catch (IOException e) {
           System.out.println("Eroare la citirea fisierului: " + e.getMessage());
           throw e;
       }
       return listaAlimente;
   }

   /**
    * Verifica si afiseaza alerta de stoc redus
    */
   private void alertaStocRedus() {
       try {
           List<Aliment> listaAlimente = incarcareAlimente();
           List<Aliment> alimenteCuStocRedus = alertLowStock(listaAlimente);
           
           if (!alimenteCuStocRedus.isEmpty()) {
               StringBuilder mesajAlerta = new StringBuilder("Atentie! Alimente cu stoc redus:\n");
               for (Aliment aliment : alimenteCuStocRedus) {
                   mesajAlerta.append(aliment.getNume()).append(" - ").append(aliment.getCantitate()).append("\n");
               }
               alertMessageArea.setText(mesajAlerta.toString());
           } else {
               alertMessageArea.setText("Toate alimentele au stoc suficient.");
           }
       } catch (IOException e) {
           alertMessageArea.setText("Eroare la încarcarea listei de alimente.");
       }
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == LogoutButton) {
            frmMain.dispose();

            IDandPasswords idandPasswords = new IDandPasswords();
            try {
				new LoginScreen(idandPasswords.getLoginInfo());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
		if (e.getSource() == addButton) {
            frmMain.dispose();
            new AddScreen(userID);
            
        }
		if (e.getSource() == afisareButton) {
            frmMain.dispose();
            new ViewScreen(userID);
        }
		
	}

}