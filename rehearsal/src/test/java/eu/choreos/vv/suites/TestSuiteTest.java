package eu.choreos.vv.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import eu.choreos.vv.actions.TimeoutSupportTest;
import eu.choreos.vv.servicesimulator.BasicMockFeaturesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BasicMockFeaturesTest.class, TimeoutSupportTest.class })
public class TestSuiteTest {

}
