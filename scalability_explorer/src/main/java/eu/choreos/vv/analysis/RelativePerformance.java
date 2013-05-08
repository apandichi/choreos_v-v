package eu.choreos.vv.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jfree.chart.PlotData;

import eu.choreos.vv.aggregations.AggregationFunction;
import eu.choreos.vv.chart.ReportChart;
import eu.choreos.vv.data.ScalabilityReport;

public class RelativePerformance extends Analyzer {

	private String title;
	private AggregationFunction function;

	public RelativePerformance(String title, AggregationFunction function) {
		this.title = title;
		this.function = function;
	}

	@Override
	public void analyse(ScalabilityReport report) throws Exception {
		List<PlotData> plotData = new ArrayList<PlotData>();
		ReportChart chart = new ReportChart(title, report, 0, function);
		PlotData aggregation = new PlotData();
		aggregation.setName(report.getName().toString());
		Number firstKey = Collections.min(report.keySet());// report.keySet().iterator().next();
		Double basePerformance = function.aggregate(report.get(firstKey)
				.getMeasurements());
		for (Number index : report.keySet()) {
			aggregation.put(
					report.get(index).getParameters().get(0).doubleValue(),
					basePerformance
							/ function.aggregate(report.get(index)
									.getMeasurements()));
		}
		plotData.add(aggregation);

		chart.createChart(plotData);
	}

}