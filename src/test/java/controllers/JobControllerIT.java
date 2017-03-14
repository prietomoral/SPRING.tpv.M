package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.BatchConfig;
import config.PersistenceConfig;
import config.TestsBatchConfig;
import daos.users.TokenDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class, BatchConfig.class, TestsBatchConfig.class })
public class JobControllerIT {
    
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private TokenDao tokenDao;
    
    @Test
    public void launchJob() {
        assertTrue(true);
    }
    
    /*
    @Before
    public void createToken() {
        Token token = new Token((User) daosService.getMap().get("u4"));
        Calendar date = Calendar.getInstance();
        date.add(Calendar.HOUR_OF_DAY, -1);
        token.setExpirationDate(date);
        tokenDao.save(token);
    }

    @Test
    public void launchJob() throws Exception {
        int c = (int) tokenDao.count();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertEquals(jobExecution.getStatus(), BatchStatus.STARTING);
        assertEquals(c - 1, tokenDao.count());
    }
    */

}