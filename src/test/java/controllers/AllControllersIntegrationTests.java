package controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TokenControllerIT.class, ArticleControllerIT.class, EmbroideryControllerIT.class, TextilePrintingControllerIT.class,StatisticControllerIt.class, VoucherControllerIT.class, ProviderControllerIT.class, CashierBalanceControllerIT.class})

public class AllControllersIntegrationTests {

}
