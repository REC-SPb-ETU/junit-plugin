package hudson.tasks.test;

import edu.hm.hafner.echarts.SeriesBuilder;
import edu.umd.cs.findbugs.annotations.Nullable;
import hudson.tasks.junit.TestResultSummary;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

@Restricted(NoExternalUse.class)
public class TestResultTrendSeriesBuilder extends SeriesBuilder<AbstractTestResultAction> {
    public static final String TOTALS_KEY = "total";
    public static final String PASSED_KEY = "passed";
    public static final String FAILED_KEY = "failed";
    public static final String SKIPPED_KEY = "skipped";

    private @Nullable String nodeId;

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    @Override
    protected Map<String, Integer> computeSeries(AbstractTestResultAction testResultAction) {
        if (nodeId == null) {
            return computeTotalSeries(testResultAction);
        }

        return computeSeriesForNode(testResultAction, nodeId);
    }

    private Map<String, Integer> computeTotalSeries(AbstractTestResultAction testResultAction) {
        int totalCount = testResultAction.getTotalCount();
        int failCount = testResultAction.getFailCount();
        int skipCount = testResultAction.getSkipCount();

        return gatherMap(totalCount, failCount, skipCount);
    }

    private Map<String, Integer> computeSeriesForNode(AbstractTestResultAction testResultAction, String nodeId) {
        TestResultSummary.NodeSummary nodeSummary =
                testResultAction.getSummary().getNodeSummaryByNodeId(nodeId);
        if (nodeSummary == null) {
            return Collections.emptyMap();
        }

        int totalCount = nodeSummary.totalCount;
        int failCount = nodeSummary.failCount;
        int skipCount = nodeSummary.skipCount;

        return gatherMap(totalCount, failCount, skipCount);
    }

    private Map<String, Integer> gatherMap(int totalCount, int failCount, int skipCount) {
        Map<String, Integer> series = new HashMap<>();

        series.put(TOTALS_KEY, totalCount);
        series.put(PASSED_KEY, totalCount - failCount - skipCount);
        series.put(FAILED_KEY, failCount);
        series.put(SKIPPED_KEY, skipCount);

        return series;
    }
}
