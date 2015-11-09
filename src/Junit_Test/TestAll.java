package Junit_Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   ActionTest.class,
   SUTStorage.class,
   SUTParser_LHY.class,
   SystemTest.class
})
public class TestAll {   
}
