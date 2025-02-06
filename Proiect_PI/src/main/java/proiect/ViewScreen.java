package proiect;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 * Clasa ViewScreen se ocupa cu citirea produselor din baza de date.
 */
public class ViewScreen  implements ActionListener{
	JFrame frmViewScreen = new JFrame();
    JTable table;
    JButton exitButton = new JButton("Exit");
    JButton searchButton = new JButton("Search");
    JTextField searchField = new JTextField(15);
    JComboBox<String> searchCritComboBox = new JComboBox<>(new String[]{"Nume", "Categorie"});
    String userID;
	

	public ViewScreen(String userID) {
		this.userID = userID;
        frmViewScreen.setTitle("View Alimente");
        frmViewScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmViewScreen.setSize(600, 400);
        frmViewScreen.getContentPane().setLayout(new BorderLayout());

        String[] colNames = {"Nume", "Cantitate", "Categorie"};
        DefaultTableModel model = new DefaultTableModel(colNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchCritComboBox);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchButton.addActionListener(this);
        
        exitButton.addActionListener(this);
        frmViewScreen.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frmViewScreen.getContentPane().add(searchPanel, BorderLayout.NORTH);
        frmViewScreen.getContentPane().add(exitButton, BorderLayout.SOUTH);

        try {
            List<Aliment> listaAlimente = incarcareAlimente();
            listaAlimente.sort(Comparator.comparing(Aliment::getCategorie));
            for (Aliment aliment : listaAlimente) {
                model.addRow(new Object[]{aliment.getNume(), aliment.getCantitate(), aliment.getCategorie()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        frmViewScreen.setVisible(true);
	
	}
	/**
	 * Functia citeste alimentele din fisierul liste.txt si le adauga intr-o lista de Aliment
	 * @return List<Aliment> listaAlimente , o lista cu toare alimentele din fisier
	 * @throws IOException
	 */
	public List<Aliment> incarcareAlimente() throws IOException {
	    List<Aliment> listaAlimente = new ArrayList<>();
	    Aliment alimentData;

	    try {
	        FileReader f = new FileReader("src/main/resources/listele.txt");
	        BufferedReader in = new BufferedReader(f);
	        String linie;
	        while ((linie = in.readLine()) != null) {
	        	String[] parts = linie.split(" ");
	        	alimentData = new Aliment(parts[0], Integer.parseInt(parts[1]), parts[2]);
	        	

	            listaAlimente.add(alimentData);
	        }
	        f.close();
	    } catch (IOException e) {
	    	
	    	
	        System.out.println("Eroare la accesarea fișierului");
	    }

	    return listaAlimente;
	}
	 @Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == exitButton) {
	            frmViewScreen.dispose();
	            new MainScreen(userID);
	            
	        } else if (e.getSource() == searchButton) {
	            String searchQuery = searchField.getText().trim().toLowerCase();
	            
	            String searchCriteria = (String) searchCritComboBox.getSelectedItem();
	            try {
	                List<Aliment> listaAlimente = incarcareAlimente();
	                List<Aliment> filteredAlimente;
	                if ("Nume".equals(searchCriteria)) {
	                    filteredAlimente = listaAlimente.stream()
	                    		
	                            .filter(aliment -> aliment.getNume().toLowerCase().contains(searchQuery))
	                            .collect(Collectors.toList());
	                } else {
	                    filteredAlimente = listaAlimente.stream()
	                            .filter(aliment -> aliment.getCategorie().toLowerCase().contains(searchQuery))
	                            .collect(Collectors.toList());
	                }
	                DefaultTableModel model = (DefaultTableModel) table.getModel();
	                model.setRowCount(0);
	                for (Aliment aliment : filteredAlimente) {
	                    model.addRow(new Object[]{aliment.getNume(), aliment.getCantitate(), aliment.getCategorie()});
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
}
