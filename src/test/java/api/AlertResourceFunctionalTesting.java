package api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import wrappers.AlertWrapperCreate;

public class AlertResourceFunctionalTesting {

    @BeforeClass
    public static void populate() {
        new RestService().populate();
    }

    @Test
    public void testCreateManager() {
        String token = new RestService().loginAdmin();
        for (int i = 0; i < 4; i++) {
            new RestBuilder<Object>(RestService.URL).path(Uris.ALERTS).body(new AlertWrapperCreate(1, 1, 84000001111L + 0))
                    .basicAuth(token, "").post().build();
        }
    }

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
