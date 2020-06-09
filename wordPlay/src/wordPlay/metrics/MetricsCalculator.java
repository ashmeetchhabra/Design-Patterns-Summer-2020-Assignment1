package wordPlay.metrics;

public class MetricsCalculator {
	public double calculate(double value1,double value2) {
		return Math.round((value1/value2) * 100.0) / 100.0;
	}

}
