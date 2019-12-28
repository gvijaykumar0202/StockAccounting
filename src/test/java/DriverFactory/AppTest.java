package DriverFactory;

import org.testng.annotations.Test;

public class AppTest {
  @Test
  public void start() throws Exception 
  {
	  DriverScript d = new DriverScript();
	  
	  d.startTest();
  }
}
