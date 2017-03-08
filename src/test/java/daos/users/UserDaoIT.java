package daos.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.users.Token;
import entities.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class UserDaoIT {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;
    
    @Autowired
    private Environment environment;

    @Test
    public void testCreate() {
        assertTrue(userDao.count() >= 4);
    }

    @Test
    public void testFindByTokenValue() {
        User user = userDao.findByMobile(666000000);
        Token token = tokenDao.findByUser(user);
        assertEquals(user, userDao.findByTokenValue(token.getValue(), new Date(new Date().getTime() - Integer.parseInt(environment.getProperty("tokenTime.user")) + 1000)));
        assertEquals(null, userDao.findByTokenValue(token.getValue(), new Date(new Date().getTime() - Integer.parseInt(environment.getProperty("tokenTime.user")) + Integer.parseInt(environment.getProperty("tokenTime.user")) + 1000)));
        assertNull(userDao.findByTokenValue("kk", new Date()));
    }
}
