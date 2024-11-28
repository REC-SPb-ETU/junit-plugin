package hudson.tasks.test;

import edu.hm.hafner.echarts.SeriesBuilder;
import hudson.tasks.junit.SummarySeriesConverter;
import java.util.HashMap;
import java.util.Map;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

@Restricted(NoExternalUse.class)
public class TestObjectTrendSeriesBuilder extends SeriesBuilder<TestObject> {

    @Override
    protected Map<String, Integer> computeSeries(TestObject testObject) {
        Map<String, Integer> series = new HashMap<>();

        int totalCount = testObject.getTotalCount();
        int failCount = testObject.getFailCount();
        int skipCount = testObject.getSkipCount();
        series.put(SummarySeriesConverter.TOTALS_KEY, totalCount);
        series.put(SummarySeriesConverter.PASSED_KEY, totalCount - failCount - skipCount);
        series.put(SummarySeriesConverter.FAILED_KEY, failCount);
        series.put(SummarySeriesConverter.SKIPPED_KEY, skipCount);
        return series;
    }
}
