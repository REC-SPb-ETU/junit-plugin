package hudson.tasks.junit;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.Serializable;
import java.util.Map;

public class TrendTestResultSummary implements Serializable {

    private int buildNumber;
    private TestResultSummary testResultSummary;

    public TrendTestResultSummary(int buildNumber, TestResultSummary testResultSummary) {
        this.buildNumber = buildNumber;
        this.testResultSummary = testResultSummary;
    }

    public Map<String, Integer> toMap() {
        return SummarySeriesConverter.convertToSeries(testResultSummary);
    }

    public Map<String, Integer> toMap(@NonNull String executorNodeName) {
        return SummarySeriesConverter.convertToSeries(testResultSummary, executorNodeName);
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public String getDisplayName() {
        return "#" + buildNumber;
    }

    public TestResultSummary getTestResultSummary() {
        return testResultSummary;
    }
}
