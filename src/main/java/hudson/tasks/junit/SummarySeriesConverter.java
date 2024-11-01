package hudson.tasks.junit;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Converts TestResultSummary to Map used in plots.
 *
 * @see TestResultSummary
 */
public class SummarySeriesConverter {
    public static final String TOTALS_KEY = "total";
    public static final String PASSED_KEY = "passed";
    public static final String FAILED_KEY = "failed";
    public static final String SKIPPED_KEY = "skipped";

    private SummarySeriesConverter() {}

    /**
     * Converts given summary into a map, paying no attention to nodes where
     * tests were performed.
     */
    @NonNull
    public static Map<String, Integer> convertToSeries(@NonNull TestResultSummary summary) {
        int totalCount = summary.getTotalCount();
        int failCount = summary.getFailCount();
        int skipCount = summary.getSkipCount();

        return gatherMap(totalCount, failCount, skipCount);
    }

    /**
     * Converts given summary into a map, using only part describing tests performed
     * on the node with given name.
     */
    @NonNull
    public static Map<String, Integer> convertToSeries(
            @NonNull TestResultSummary summary, @NonNull String executorNodeName) {
        TestResultSummary.NodeSummary nodeSummary = summary.getNodeSummaryByExecutorNodeName(executorNodeName);
        if (nodeSummary == null) {
            return Collections.emptyMap();
        }

        int totalCount = nodeSummary.totalCount;
        int failCount = nodeSummary.failCount;
        int skipCount = nodeSummary.skipCount;

        return gatherMap(totalCount, failCount, skipCount);
    }

    private static Map<String, Integer> gatherMap(int totalCount, int failCount, int skipCount) {
        Map<String, Integer> series = new HashMap<>();

        series.put(TOTALS_KEY, totalCount);
        series.put(PASSED_KEY, totalCount - failCount - skipCount);
        series.put(FAILED_KEY, failCount);
        series.put(SKIPPED_KEY, skipCount);

        return series;
    }
}
