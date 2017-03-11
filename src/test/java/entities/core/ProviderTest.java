package entities.core;

import org.junit.Assert;
import org.junit.Test;

public class ProviderTest {
    @Test
    public void testAlert() {
        Provider provider = new Provider("Company", "Address", 666, 999, "Payment onditions", "Note");
        Assert.assertEquals(provider.getAddress(), "Address");
        Assert.assertEquals(provider.getMobile(), 666);
    }
}
