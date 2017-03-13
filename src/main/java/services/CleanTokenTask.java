package services;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import daos.users.TokenDao;

@Service
public class CleanTokenTask {
    
    @Autowired
    private Environment environment;
    
    @Autowired
    private TokenDao tokenDao;
    
    public void run(){
        //tokenDao.deleteByCreationDateLessThan(new Date(new Date().getTime() - Integer.parseInt(environment.getProperty("tokenTime.user"))));
        LogManager.getLogger().info(" ++++ Ejecutando task de limpieza de tokens ++++ ");
        //System.out.println("EJECUTADO TASK");
    }

}
