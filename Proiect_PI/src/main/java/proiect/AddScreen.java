package proiect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.Color;

/**
 * clasa AddScreen se ocupa cu adaugarea alimentelor 
 */
public class AddScreen implements ActionListener{
	JFrame frmAddScreen = new JFrame();
	JButton AddButton = new JButton("Add");
	JButton ExitButton = new JButton("Exit");
	JTextField numeField = new JTextField();
	JTextField categorieField = new JTextField();
	JLabel numeLable = new JLabel("Nume aliment:");
	JLabel cantitateLable = new JLabel("Cantitate aliment:");
	JLabel categorieLable = new JLabel("Categorie aliment:");
	JLabel messageLable = new JLabel();
	JSpinner cantitateSpinner = new JSpinner();
	String userID;
	
	/**
	 * Se ocupa cu adaugarea alimentelor
	 * @param userID
	 */
	public AddScreen(String userID) {
		this.userID = userID;
		
		frmAddScreen.setTitle("Add");
		frmAddScreen.getContentPane().setLayout(null);
		frmAddScreen.setVisible(true);
		frmAddScreen.setSize(450, 400);
		
		numeLable.setBounds(50, 40, 100, 25);
		frmAddScreen.getContentPane().add(numeLable);

		numeField.setBounds(166,40,100,25);
		frmAddScreen.getContentPane().add(numeField);
		
		cantitateLable.setBounds(50, 95, 100, 25);
		frmAddScreen.getContentPane().add(cantitateLable);
		
		cantitateSpinner.setBounds(166, 95, 50, 25);
		frmAddScreen.getContentPane().add(cantitateSpinner);
		
		categorieLable.setBounds(50, 150, 115, 25);
		frmAddScreen.getContentPane().add(categorieLable);
		
		categorieField.setBounds(166,150,100,25);
		frmAddScreen.getContentPane().add(categorieField);
		
		
		AddButton.setBounds(89,209,100,25);
		frmAddScreen.getContentPane().add(AddButton);
		
		ExitButton.setBounds(250,209,100,25);
		frmAddScreen.getContentPane().add(ExitButton);
		
		AddButton.addActionListener(this);
        ExitButton.addActionListener(this);
        
        messageLable.setForeground(new Color(0, 153, 51));
        messageLable.setFont(new Font("Tahoma", Font.BOLD, 17));
		
        messageLable.setText("");
        messageLable.setBounds(90, 268, 300, 25);
        frmAddScreen.getContentPane().add(messageLable);
		
		
	}

	/**
	 * Adauga un aliment in fișier sau actualizeaza cantitatea acestuia daca alimentul deja exista
	 * 
	 * @param aliment Numele alimentului
	 * @param cantitate Noua cantitate de adaugat
	 * @param categorie Categoria alimentului
	 * @throws IOException daca apare o eroare la citirea sau scrierea in fișier
	 */
	public void addAliment(Aliment aliment) throws IOException {
	    String filePath = "src/main/resources/listele.txt";
	    List<String> alimenteLinie = new ArrayList<>();
	    boolean alimentGasit = false;

	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String linie;
	        while ((linie = reader.readLine()) != null) {
	            String[] parts = linie.split(" ");
	            String alimentFisier = parts[0];
	            int cantitateFisier = Integer.parseInt(parts[1]);
	            String categorieFisier = parts[2];

	            if (alimentFisier.equals(aliment.getNume())) {
	                cantitateFisier += aliment.getCantitate();
	                if (cantitateFisier < 0) {
	                	cantitateFisier = 0;
	                }
	                linie = alimentFisier + " " + cantitateFisier + " " + categorieFisier;
	                alimentGasit = true;
	            }
	            alimenteLinie.add(linie);
	        }
	    } catch (IOException e) {
	        System.out.println("Eroare la citirea fisierului: " + e.getMessage());
	    }

	    if (!alimentGasit) {
	        alimenteLinie.add(aliment.getNume() + " " + aliment.getCantitate() + " " + aliment.getCategorie());
	    }

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        for (String linie : alimenteLinie) {
	            writer.write(linie);
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        System.out.println("Eroare la scrierea fisierului: " + e.getMessage());
	        throw e;
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == AddButton) {
			String nume = numeField.getText();
			int cantitate = (Integer) cantitateSpinner.getValue();
            String categorie = categorieField.getText();
            Aliment aliment = new Aliment(nume, cantitate, categorie);
            try {
                addAliment(aliment);
                messageLable.setText("Aliment adaugat cu succes!");
                frmAddScreen.getContentPane().add(messageLable);
                frmAddScreen.getContentPane().repaint();
            } catch (IOException ex) {
                messageLable.setText("Eroare la adaugarea alimentului!");
                messageLable.setForeground(new Color(255, 0, 0));
                frmAddScreen.getContentPane().add(messageLable);
                frmAddScreen.getContentPane().repaint();
                ex.printStackTrace();
            }
			
			
		}
		if(e.getSource() == ExitButton) {
			frmAddScreen.dispose();
			new MainScreen(userID);
		}
	}

}
