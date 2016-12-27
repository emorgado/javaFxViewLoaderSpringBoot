package br.com.emorgado.app;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.emorgado.bootfx.BootApplicationController;
import br.com.emorgado.bootfx.ViewLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

@Controller
public class MainController
                extends BootApplicationController {

    private final Logger log = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    private ViewLoader viewLoader;    
    
    @FXML
    private MenuItem mnItmDados;
    
    @FXML
    private MenuItem mnitmConfiguration;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Initialize");
        Platform.runLater( () -> {
            handleShowData();            
        });
    }    
    
    @FXML
    void handleShowConfiguration() {
        log.info("handleShowConfiguration");
        
        try {
            viewLoader.show( ConfigurationController.class );
            //setCenter(ConfiguracaoController.class);
        } catch ( Exception e ) {
            
            e.printStackTrace();
        }
    }

    @FXML
    void handleShowData() {
        log.info("handleShowData");
        try {
            
            viewLoader.show( DataController.class );
            
        } catch ( Exception e ) {
            
            e.printStackTrace();
        }
    }
    
    @FXML
    void handleExit() {
        log.info("handleExit");
        // Close usedResources
        System.exit(0);
    }
    
    
}
