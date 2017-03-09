package entities.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlertTest {

	@Autowired
	private Article articles;

	@Test
	public void testAlert() {

		Alert alert = new Alert(10, 5, articles);
		Assert.assertEquals(alert.getWarning(), 10);
		Assert.assertEquals(alert.getCritical(), 5);
	}

}
