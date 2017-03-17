package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import api.wrappersForTest.AlertListWrapper;
import wrappers.AlertWrapper;
import wrappers.AlertWrapperCreate;

public class AlertResourceFunctionalTesting {

    @BeforeClass
    public static void populate() {
        new RestService().populate();
        new RestService().registerAndLoginManager();
    }

    @Test
    public void testGetAllAlerts() {
        String token = new RestService().loginManager();
        AlertListWrapper alerts = new RestBuilder<AlertListWrapper>(RestService.URL).path(Uris.ALERTS).basicAuth(token, "")
                .clazz(AlertListWrapper.class).get().build();
        assertEquals(7, alerts.size());
    }

    @Test
    public void testGetOneAlert() {
        String token = new RestService().loginManager();
        AlertListWrapper alerts = new RestBuilder<AlertListWrapper>(RestService.URL).path(Uris.ALERTS).basicAuth(token, "")
                .clazz(AlertListWrapper.class).get().build();
        AlertWrapper alert = new RestBuilder<AlertWrapper>(RestService.URL).path(Uris.ALERTS + "/" + alerts.get(0).getId())
                .basicAuth(token, "").clazz(AlertWrapper.class).get().build();
        assertNotNull(alert);
    }

    @Test
    public void testUpdateAlert() {
        String token = new RestService().loginManager();
        AlertListWrapper alerts = new RestBuilder<AlertListWrapper>(RestService.URL).path(Uris.ALERTS).basicAuth(token, "")
                .clazz(AlertListWrapper.class).get().build();
        new RestBuilder<Object>(RestService.URL).path(Uris.ALERTS + '/' + alerts.get(0).getId())
                .body(new AlertWrapperCreate(666, 666, 84000001111L + 0)).basicAuth(token, "").put().build();
        AlertWrapper alert = new RestBuilder<AlertWrapper>(RestService.URL).path(Uris.ALERTS + "/" + alerts.get(0).getId())
                .basicAuth(token, "").clazz(AlertWrapper.class).get().build();
        assertEquals(666, alert.getWarning());
    }

    @Test
    public void testDeleteAlert() {
        String token = new RestService().loginManager();
        AlertListWrapper alerts = new RestBuilder<AlertListWrapper>(RestService.URL).path(Uris.ALERTS).basicAuth(token, "")
                .clazz(AlertListWrapper.class).get().build();
        assertEquals(4, alerts.size());
        new RestBuilder<Object>(RestService.URL).path(Uris.ALERTS + '/' + alerts.get(0).getId()).basicAuth(token, "").delete().build();
        AlertListWrapper alertsUpdated = new RestBuilder<AlertListWrapper>(RestService.URL).path(Uris.ALERTS).basicAuth(token, "")
                .clazz(AlertListWrapper.class).get().build();
        assertEquals(3, alertsUpdated.size());
    }

    @Test
    public void testCreateAlert() {
        String token = new RestService().loginManager();
        for (int i = 0; i < 4; i++) {
            new RestBuilder<Object>(RestService.URL).path(Uris.ALERTS).body(new AlertWrapperCreate(5, 2, 84000001111L + 0))
                    .basicAuth(token, "").post().build();
        }
    }

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
