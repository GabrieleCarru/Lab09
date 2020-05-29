
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="cmbScelta"
    private ComboBox<Country> cmbScelta; // Value injected by FXMLLoader

    @FXML // fx:id="btnVicini"
    private Button btnVicini; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	
    	int anno = Integer.parseInt(txtAnno.getText());
    	
    	// Verifica sulla validità dell'anno
    	if(anno < 1816 || anno > 2016) {
    		txtResult.setText("Please enter a year between 1816 and 2016.");
    		return;
    	}
    	
    	model.creaGrafo(anno);
    	List<Country> countries = model.getCountries();
    	
    	txtResult.appendText(String.format("Number of connected components: %d \n", 
    									model.getNumberOfConnectedComponents()));
    	
    	Map<Country, Integer> stats = model.getCountryCounts();
    	for(Country c : stats.keySet()) {
    		txtResult.appendText(String.format("%s %d \n", c, stats.get(c)));
    	}
    	
    	//Riempio la ComboBox per la scelta successiva:
    	cmbScelta.getItems().addAll(countries);
    	
    }

    @FXML
    void trovaTuttiVicini(ActionEvent event) {
    	
    	if(cmbScelta.getItems().isEmpty()) {
    		txtResult.setText("Graph is empty. Create a graph, or selected another year.");
    		return;
    	}
    	
    	//Country selectedCountry = cmbScelta.getSelectionModel().getSelectedItem();
    	Country selectedName = cmbScelta.getValue();
    	if(selectedName == null) {
    		txtResult.setText("Select a country first.");
    		return;
    	}
    	
    	txtResult.appendText("Searching for countries adjacent to " 
				+ selectedName.getStateName() + " in progress. \n");
    	
    	List<Country> vicini = model.getNeighboursCointries(selectedName);
    	for(Country c : vicini) {
    		txtResult.appendText(String.format("%s \n", c));
    	}
    	
    	txtResult.appendText("Find all countries adjacent to " + selectedName.getStateName());
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbScelta != null : "fx:id=\"cmbScelta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}

