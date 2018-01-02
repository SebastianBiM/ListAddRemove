package test;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;


public abstract class TestFXBase extends ApplicationTest {

	
	@After
	public void afterEachTest() throws TimeoutException {
		FxToolkit.cleanupStages();
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}
    
}
