package br.com.emorgado.bootfx;

/**
 * @author emerson.morgado@gmail.com
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

@Service
public class ViewLoader {
    
    private Logger log = LoggerFactory.getLogger( getClass() );

    private static String FXML_PATH = "/fxml/";
    private static String STYLES_PATH = "/styles/";
    private static String I18N_PATH = "i18n/";
    
    protected FXMLLoader fxmlLoader;
    
    private final ApplicationContext applicationContext;
    
    private ResourceBundle appResourceBundle = null;
    
    private URL styleUrl = null;
    
    private BorderPane rootNode;
    
    @Autowired
    public ViewLoader( ApplicationContext applicationContext ){
        log.info("ViewLoader");
        this.applicationContext = applicationContext;
    }    
    
    public Parent getView( Class controllerClassName ) throws IOException{
              
                
        FXMLLoader loader = new FXMLLoader();
        
        // Change the controller factory to get from spring context
        loader.setControllerFactory( new Callback<Class<?>, Object>() {
            
            @Override
            public Object call(Class<?> param) {
                return applicationContext.getBean( param );
                
            }
        });
        
        // Load resource bundle only once, me prefered name is mainController name.
        try {            
            
            // The first bundler will be considered Application Bundler
            if( appResourceBundle == null ){                
                
                appResourceBundle = extractBundle( controllerClassName );
                
                if( appResourceBundle != null ){
                    loader.setResources( appResourceBundle );
                }
                
            } else {
                loader.setResources( appResourceBundle );
            }
            
        } catch( MissingResourceException e ){            
            
            log.error("Error loading ResourceBundle: "+e.getMessage());

        }
        
        
        loader.load( extractFxmlInputStream( controllerClassName ) );
        
        Parent node = loader.getRoot();
        
        if( rootNode == null ){
            rootNode = ( BorderPane ) node;
            
            // Only Load main style file
            styleUrl = getStylesheet( controllerClassName );
            if( styleUrl != null ){
                log.info( "Loading styles "+styleUrl.toExternalForm() );
                rootNode.getStylesheets().add( styleUrl.toExternalForm() );
            }
        }
        
        return node;
    }
    
    public void show( Class controllerClassName ) throws Exception{
        
        checkRootNode();
        
        Parent view = getView( controllerClassName );
        
        this.rootNode.setCenter( view );
    }
    
    public void showLeft( Class controllerClassName ) throws Exception{
        
        checkRootNode();
        
        Parent view = getView( controllerClassName );
        
        this.rootNode.setLeft( view );
            
    }

    public void showRight( Class controllerClassName ) throws Exception{
        
        checkRootNode();
        
        Parent view = getView( controllerClassName );
        
        this.rootNode.setRight( view );
            
    }
    
    public Stage showAsDialog( Class controllerClassName ) throws IOException {
        
        Parent page = (Parent) getView( controllerClassName );
        
        Stage stage = new Stage();
        Scene scene = new Scene( page );
        stage.setScene( scene);
        
        log.debug("StyleUrl: "+styleUrl);
        if( styleUrl != null ){
            log.info( "Loading styles "+styleUrl.toExternalForm() );            
            stage.getScene().getStylesheets().add( styleUrl.toExternalForm() );            
        }
        
        
        return stage;
    }
    
    /**
     * Used to get i18n Text from application configuration
     * @param key
     * @return
     */
    public String i18n( String key ){
        try {
            return appResourceBundle.getString(key);
        } catch(Exception e){
            log.error(e.getMessage());
            return key;
        }
    }
    
    /** 
     * @Deprecated
     * Use i18N instead
     */    
    public String getResourceBundleString(String key){
        try {
            return appResourceBundle.getString(key);
        } catch(Exception e){
            log.error(e.getMessage());
            return key;
        }
    }
    
    private void checkRootNode() throws Exception{
        if( rootNode == null ){
            throw new Exception("No Root Node Defined!");
        }
    }

    private InputStream extractFxmlInputStream( Class controllerClassName ){
        
        String resourceName = String.format("%s%s.fxml", FXML_PATH, getResourceName( controllerClassName ) );
        
        log.debug("URL "+getClass().getResource(resourceName));
        
        return getClass().getResourceAsStream(resourceName);
    }
    
    private String getResourceName( Class controllerClassName ) {
        
        String className = controllerClassName.getSimpleName().replaceAll("Controller$", "");
        
        return className.replaceFirst(className.substring(0,1), className.substring(0,1).toLowerCase());
    }
    
    private ResourceBundle extractBundle( Class controllerClassName ) throws MissingResourceException {
        
        String bundlePath = String.format("%s%s", I18N_PATH, getResourceName( controllerClassName ) );
        
        log.debug("i18n name "+bundlePath);
        
        ResourceBundle bundle = ResourceBundle.getBundle( bundlePath );
        
        return bundle;
    }
    
    private URL getStylesheet( Class controllerClassName ){
        
        String stylesPath = String.format("%s%s.css", STYLES_PATH, getResourceName(controllerClassName));
        
        return getClass().getResource(stylesPath);
        
    }
}
