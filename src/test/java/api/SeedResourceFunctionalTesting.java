package api;

import static config.ResourceNames.DEFAULT_SEED_FILE;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

public class SeedResourceFunctionalTesting {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUpOnce() {
        new RestService().deleteAll();
    }

    @Test
    public void testNonexistentFile() {
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<Object>(RestService.URL).path(Uris.SEED).body("nonexistent.yml").post().build();
    }

    @Test
    public void testSeedDatabase() {
        new RestBuilder<Object>(RestService.URL).path(Uris.SEED).body(DEFAULT_SEED_FILE).post().build();
    }

    @After
    public void tearDownOnce() {
        new RestService().deleteAll();
    }
}
