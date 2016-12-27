package br.com.emorgado.bootfx;

/**
 * @author emerson.morgado@gmail.com
 */

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

@Controller
public abstract class BootApplicationController implements Initializable {

    @FXML
    protected BorderPane rootNode;
  
}
