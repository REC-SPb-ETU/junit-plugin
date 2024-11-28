package hudson.tasks.test;

import edu.hm.hafner.echarts.SeriesBuilder;
import edu.umd.cs.findbugs.annotations.Nullable;
import hudson.tasks.junit.SummarySeriesConverter;
import java.util.Map;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

@Restricted(NoExternalUse.class)
public class TestResultTrendSeriesBuilder extends SeriesBuilder<AbstractTestResultAction> {
    private @Nullable String nodeName;

    public void setNodeName(String nodeId) {
        this.nodeName = nodeId;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    @Override
    protected Map<String, Integer> computeSeries(AbstractTestResultAction testResultAction) {
        if (nodeName == null) {
            return SummarySeriesConverter.convertToSeries(testResultAction.getSummary());
        }

        return SummarySeriesConverter.convertToSeries(testResultAction.getSummary(), nodeName);
    }
}
