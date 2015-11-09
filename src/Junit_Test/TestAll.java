package Junit_Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//import controller.LogicTest;
//import view.UiTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   //UiTest.class,
   ActionTest.class,
   SUTStorage.class,
   SUTParser_LHY.class,
   SystemTest.class
})
public class TestAll {   
}
