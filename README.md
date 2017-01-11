# JavaFx Spring Boot Application


This is a simple seed project to allow implement JavaFx Applications with SpringBoot.

# FXML

The main Controller should have a fxml file associated witch must be a BorderPane, the controller property must be set on fxml file. 

The View Name should be the controller name first letter lower and without the "Controller", if the controller name is ConfigurationController, the view name should be "configuration.fxml". 

# i18n

ResourceBunles must be in the *resources/i18n* directory.

ResourceBundles must have the name of main controller without the controller and first letter lower, example main controller is ApplicationController, so the resource bundle will be called application.properties, for other languages, like Portuguese Brazil, use application_pt_BR.properties


# Styles

Styles must be in the *resources/styles* directory

Stylesheet can be set on fxml file, but if you wish, create a stylesheet file where the name will be the main controller without the controller word and first letter lower, example, if your main controller is ApplicationController your file must be application.css


# How to Start

Create a Application class Like.

    @SpringBootApplication
    public class MyApplication 
                        extends SpringBootFxApplication {
                        
       private final Logger log = LoggerFactory.getLogger(JXBootApplication.class);
    
       @Override
        public void initApp(){
            log.info("initialize your appplication here!");
        }
    
        @Override
        public void stopApp(){
            log.info("Close all connections here");
        }
    
        public static void main(String[] args) {
            launchApp( MyApplication.class, MainController.class, args );        
        }                 
                        
    } 
    
    
Pay attention to the main method, you have to set the Application class, and your first controller class, so your controller will be provided by spring context. 

Remember to add '@SpringBootApplication' annotation, and your controllers must have the @Controller Annotation

Your first controller must extend 'BootApplicationController' example:

    @Controller
    public class MainController 
                             extends BootApplicationController {
                       
         @Override
         public void initialize(URL location, ResourceBundle resources) {
         	   // initialization here
         }      
    }
    
On the resources source folder, create tree directories:

* *FXML* for fxml files
* *i18n* for internationalization
* *styles* for css files

     
    
    
# FXML Factories    
    
    
    