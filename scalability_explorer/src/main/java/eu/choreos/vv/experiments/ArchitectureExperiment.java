package eu.choreos.vv.experiments;

import java.util.ArrayList;
import java.util.List;

import eu.choreos.vv.ScaleCaster;
import eu.choreos.vv.analysis.Analyzer;
import eu.choreos.vv.data.ScalabilityReport;
import eu.choreos.vv.deployment.Deployer;
import eu.choreos.vv.increasefunctions.LinearIncrease;
import eu.choreos.vv.increasefunctions.ScalabilityFunction;
import eu.choreos.vv.loadgenerator.LoadGenerator;
import eu.choreos.vv.loadgenerator.DegeneratedLoadGenerator;
import eu.choreos.vv.loadgenerator.executable.LatencyMeasurementExecutable;

/**
 * This class implements a skeleton of a infrastructure experiment consisted on
 * many steps. In each test battery, the frequency of requests and the quantity
 * of resources will be increased according to a ScalabilityFunction. A request
 * is executed a number of times, in each step, and some metrics are collected
 * for analysis. The steps will be executed up to a determined number of
 * executions (or until one's aggregated return value surpasses a defined
 * limit).
 * 
 */
public abstract class ArchitectureExperiment extends Experiment {

	private Number inititalResoucesQuantity;
	private int requestsPerMinute;


	/**
	 * Creates a new ScalabilityTester that uses UniformLoadGenarator, Mean and
	 * LinearIncrease
	 */
	public ArchitectureExperiment() {
		super();
		init();
	}

	/**
	 * Creates a new ScalabilityTester
	 * 
	 * @param loadGenerator
	 *            load generator to run the tests
	 * @param aggregator
	 *            aggregation function
	 * @param function
	 *            scalability function
	 */
	public ArchitectureExperiment(LoadGenerator loadGenerator, ScalabilityFunction function) {
		super(loadGenerator, function);
		init();
	}

	private void init() {
		this.inititalResoucesQuantity = 1;
	}

	public Number getInititalResoucesQuantity() {
		return inititalResoucesQuantity;
	}

	public void setInititalResoucesQuantity(Number inititalResoucesQuantity) {
		this.inititalResoucesQuantity = inititalResoucesQuantity;
	}
	
	public int getRequestsPerMinute() {
		return requestsPerMinute;
	}

	public void setRequestsPerMinute(int requestsPerMinute) {
		this.requestsPerMinute = requestsPerMinute;
	}

	@Override
	protected Number[] setInitialParameterValues() {
		Number[] values = new Number[1];
		values[0] = inititalResoucesQuantity;
		return values;
	}
	
	@Override
	protected void getParameterValues(Number... values) {
		super.setNumberOfRequestsPerMinute(requestsPerMinute);
		super.setScaleSize(values[0].intValue());
	}
	
	@Override
	protected List<String> getParameterLabels() {
		List<String> labels = new ArrayList<String>();
		labels.add("size");
		return labels;
	}
	
}
