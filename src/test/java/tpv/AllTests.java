package tpv;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controllers.AllControllersIntegrationTests;
import controllers.AllControllersTests;
import daos.AllDaosIntegrationTests;
import entities.AllEntitiesTests;
import services.AllServicesIntegrationTests;
import services.AllServicesTests;

@RunWith(Suite.class)
@SuiteClasses({AllEntitiesTests.class, AllControllersTests.class, AllDaosIntegrationTests.class, AllControllersIntegrationTests.class,
        AllServicesIntegrationTests.class, AllServicesTests.class})
public class AllTests {

}
