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