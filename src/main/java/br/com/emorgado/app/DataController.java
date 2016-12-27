package br.com.emorgado.app;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

@Controller
public class DataController implements Initializable {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @FXML
    private ListView<String> lstVwDados;
    
    
    private ObservableList<String> nomes = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("initialize");
        
        lstVwDados.setItems(nomes);
        nomes.clear();
        nomes.add("Emerson");
        nomes.add("Adriano");
        nomes.add("Edson");
        nomes.add("Clodoaldo");
    }

    
}
