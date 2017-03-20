package controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import api.JobResourceTesting;

@RunWith(Suite.class)
@SuiteClasses({TokenControllerIT.class, ArticleControllerIT.class, EmbroideryControllerIT.class, TextilePrintingControllerIT.class,StatisticControllerIt.class, VoucherControllerIT.class, ProviderControllerIT.class})

public class AllControllersIntegrationTests {

}
