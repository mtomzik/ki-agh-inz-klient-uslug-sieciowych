package pl.edu.agh.universallib.entitymethods;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateEntityTest.class, DeleteAllEntitiesTest.class,
		DeleteEntityTest.class, GetEntitiesTest.class, GetEntityTest.class,
		UpdateEntityTest.class })
public class AllEntityMethodsTests {

}
