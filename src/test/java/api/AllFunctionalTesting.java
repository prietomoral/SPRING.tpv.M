package api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UserResourceFunctionalTesting.class, TokenResourceFunctionalTesting.class,
        ArticleResourceFunctionalTesting.class,

		EmbroideryResourceFunctionalTesting.class, TextilePrintingResourceFunctionalTesting.class,
		AlertResourceFunctionalTesting.class, ProviderResourceFunctionalTesting.class,
		InvoiceResourceFunctionalTesting.class, VoucherResourceFunctionalTesting.class,
		StatisticResourceFunctionalTesting.class, CashierBalanceResourceFunctionalTesting.class,
		SeedResourceFunctionalTesting.class, JobResourceTesting.class

})

public class AllFunctionalTesting {

}
