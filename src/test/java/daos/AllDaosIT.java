package daos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import daos.core.AllDaosCoreIT;
import daos.users.AllDaosUsersIT;

@RunWith(Suite.class)
@SuiteClasses({
    AllDaosCoreIT.class,
    AllDaosUsersIT.class
})
public class AllDaosIT {

}
