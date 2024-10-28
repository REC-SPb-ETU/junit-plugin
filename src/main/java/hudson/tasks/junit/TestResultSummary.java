package hudson.tasks.junit;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jenkinsci.plugins.scriptsecurity.sandbox.whitelists.Whitelisted;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.DoNotUse;

/**
 * Summary of test results that can be used in Pipeline scripts.
 */
public class TestResultSummary implements Serializable {
    private int failCount;
    private int skipCount;
    private int passCount;
    private int totalCount;
    private Map<String, NodeSummary> nodeSummaries = new HashMap<>();

    @Deprecated
    @Restricted(DoNotUse.class)
    public TestResultSummary() {}

    public TestResultSummary(int failCount, int skipCount, int passCount, int totalCount) {
        this.failCount = failCount;
        this.skipCount = skipCount;
        this.passCount = passCount;
        this.totalCount = totalCount;
    }

    public TestResultSummary(TestResult result) {
        this.failCount = result.getFailCount();
        this.skipCount = result.getSkipCount();
        this.passCount = result.getPassCount();
        this.totalCount = result.getTotalCount();
        if (totalCount == 0) {
            for (SuiteResult suite : result.getSuites()) {
                if (!suite.getCases().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Attempt to construct TestResultSummary from TestResult without calling tally/freeze");
                }
            }
        }

        summarizePerNode(result);
    }

    @Whitelisted
    public int getFailCount() {
        return failCount;
    }

    @Whitelisted
    public int getSkipCount() {
        return skipCount;
    }

    @Whitelisted
    public int getPassCount() {
        return passCount;
    }

    @Whitelisted
    public int getTotalCount() {
        return totalCount;
    }

    @Whitelisted
    public Collection<String> getNodeIds() {
        return Collections.unmodifiableSet(nodeSummaries.keySet());
    }

    @Whitelisted
    public NodeSummary getNodeSummaryByNodeId(String nodeId) {
        return nodeSummaries.get(nodeId);
    }

    private void summarizePerNode(TestResult result) {
        nodeSummaries.clear();

        for (String nodeId : result.getNodeIds()) {
            nodeSummaries.put(nodeId, new NodeSummary(nodeId, result));
        }
    }

    public class NodeSummary implements Serializable {
        public final String nodeId;
        public final int failCount;
        public final int skipCount;
        public final int passCount;
        public final int totalCount;

        public NodeSummary(String nodeId, TestResult result) {
            TestResult nodeResult = result.getResultByNode(nodeId);

            this.nodeId = nodeId;
            this.failCount = nodeResult.getFailCount();
            this.skipCount = nodeResult.getSkipCount();
            this.passCount = nodeResult.getPassCount();
            this.totalCount = nodeResult.getTotalCount();
        }
    }
}
