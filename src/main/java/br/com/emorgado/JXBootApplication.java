package br.com.emorgado;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.emorgado.app.MainController;
import br.com.emorgado.bootfx.SpringBootFxApplication;

@SpringBootApplication
public class JXBootApplication 
                    extends SpringBootFxApplication {

    private final Logger log = LoggerFactory.getLogger(JXBootApplication.class);
    
    @Override
    public void initApp(){
        log.info("init App");
    }
    
    @Override
    public void stopApp(){
        log.info("Stop App");
    }
    
    public static void main(String[] args) {
        launchApp( JXBootApplication.class, MainController.class, args );        
    }
    
}
