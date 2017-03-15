package services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import api.JobResourceTesting;

@RunWith(Suite.class)
@SuiteClasses({StatisticServiceIT.class, DataServiceIT.class, SeedServiceIT.class})
public class AllServicesIntegrationTests {

}
