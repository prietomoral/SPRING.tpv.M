package controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TokenControllerIT.class, ArticleControllerIT.class, EmbroideryControllerIT.class})

public class AllControllersIntegrationTests {

}
