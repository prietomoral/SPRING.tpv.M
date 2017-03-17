package services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({StatisticServiceIT.class, SeedServiceIT.class, DataServiceIT.class})
public class AllServicesIntegrationTests {

}
