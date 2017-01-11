package br.com.emorgado.bootfx;

/**
 * @author emerson.morgado@gmail.com
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@ComponentScan({"br.com.emorgado.bootfx"})
public abstract class SpringBootFxApplication 
                            extends Application {
    
    private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);
    
    private static String[] savedArgs;

    private static Class mainControllerClass;
    
    protected ConfigurableApplicationContext applicationContext;
    
    private Stage primaryStage;
    
    private Scene primaryScene;
    
    private ViewLoader viewLoader;
    
    
    @Override
    public void init() throws Exception {
        
        log.info("init");
        
        applicationContext = SpringApplication.run(getClass(), savedArgs);
        
        viewLoader = applicationContext.getBean( ViewLoader.class );
        
        initApp();
    }
    
    public void initApp(){
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("start");
        
        this.primaryStage = primaryStage;
        
        BorderPane mainScreen = (BorderPane) viewLoader.getView( mainControllerClass );
        
        this.primaryScene = new Scene( mainScreen );
        
        primaryStage.setScene( primaryScene );
        
        primaryStage.setTitle( viewLoader.getResourceBundleString("app.title") );
        
        primaryStage.show();
    }   
    

    @Override
    public void stop() throws Exception {
        
        log.info("stop");
        
        stopApp();
        
        applicationContext.close();
    }
    
    /**
     * Hook Method to be overrided
     */
    public void stopApp(){
        
    }

    protected static void launchApp( final Class<? extends SpringBootFxApplication> clazz, 
            final Class mainControllerClass,            
            final String[] args ) {
            launchApp(clazz, mainControllerClass, null, args);
    
    }
    
    protected static void launchApp( final Class<? extends SpringBootFxApplication> applicationClass, 
                                     final Class mainControllerClass, 
                                     final Class<? extends Preloader> preloaderClass,
                                     final String[] args ) {
        
        log.info("     Launching class: "+applicationClass);
        log.debug(" MainControllerClass: "+mainControllerClass);
        log.debug("      PreloaderClass: "+preloaderClass);
        log.debug("           Arguments: "+args);
        
        SpringBootFxApplication.mainControllerClass = mainControllerClass;
        
        SpringBootFxApplication.savedArgs = args;
        
//        if( preloaderClass != null ){
//            Application.launchApplicationWithArgs(applicationClass, preloaderClass, args);
//        } else {
            Application.launch(applicationClass, args);
//        }
    }

}
