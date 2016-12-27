package br.com.emorgado.bootfx;

/**
 * @author emerson.morgado@gmail.com
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

@Service
public class ViewLoader {
    
    private Logger log = LoggerFactory.getLogger( getClass() );

    private static String FXML_PATH = "/fxml/";
    private static String STYLES_PATH = "/styles/";
    private static String I18N_PATH = "i18n/";
    
    protected FXMLLoader fxmlLoader;
    
    private final ApplicationContext applicationContext;
    
    private ResourceBundle cachedAppResourceBundle = null;
    
    private BorderPane rootNode;
    
    @Autowired
    public ViewLoader(ApplicationContext applicationContext){
        log.info("ViewLoader");
        this.applicationContext = applicationContext;
    }
    
    public Node getView( Class controllerClassName ) throws IOException{
              
                
        FXMLLoader loader = new FXMLLoader();
        
        // Change the controller factory to get from spring context
        loader.setControllerFactory( new Callback<Class<?>, Object>() {
            
            @Override
            public Object call(Class<?> param) {
                return applicationContext.getBean( param );
                
            }
        });
        
        // Load resources if exists.
        try {
            
            loader.setResources( extractBundle( controllerClassName ) );
            
            // The first bundler will be considered Application Bundler
            if( cachedAppResourceBundle == null ){
                cachedAppResourceBundle = loader.getResources();
            }
            
        } catch( MissingResourceException e ){            
            
            log.info("MissingResource "+e.getMessage());
            
            // if no specifc bunler, load Application Bundler
            if( cachedAppResourceBundle != null ){
                loader.setResources(cachedAppResourceBundle);                
            }
        }
        
        
        loader.load( extractFxmlInputStream( controllerClassName ) );
        
        Node node = loader.getRoot();
        
        if( rootNode == null ){
            rootNode = ( BorderPane ) node;
            
            URL stylesUrl = getStylesheet(controllerClassName);
            if( stylesUrl != null ){
                log.info( "Loading styles "+stylesUrl.toExternalForm() );
                rootNode.getStylesheets().add( stylesUrl.toExternalForm() );
            }
        }
        return node;
    }
    
    public void show( Class controllerClassName ) throws Exception{
        
        checkRootNode();
        
        Node view = getView( controllerClassName );
        
        this.rootNode.setCenter( view );
    }
    
    public void showLeft( Class controllerClassName ) throws Exception{
        
        checkRootNode();
        
        Node view = getView( controllerClassName );
        
        this.rootNode.setLeft( view );
            
    }

    public void showRight( Class controllerClassName ) throws Exception{
        
        checkRootNode();
        
        Node view = getView( controllerClassName );
        
        this.rootNode.setRight( view );
            
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
