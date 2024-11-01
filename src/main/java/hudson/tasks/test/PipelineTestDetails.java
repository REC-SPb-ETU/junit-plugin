package hudson.tasks.test;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for recording additional Pipeline-related arguments needed for test parsing and test results.
 */
public class PipelineTestDetails implements Serializable {
    private String nodeId;
    private String executorNodeName;
    private List<String> enclosingBlocks = new ArrayList<>();
    private List<String> enclosingBlockNames = new ArrayList<>();

    @CheckForNull
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(@NonNull String nodeId) {
        this.nodeId = nodeId;
    }

    @CheckForNull
    public String getExecutorNodeName() {
        return executorNodeName;
    }

    public void setExecutorNodeName(@NonNull String executorNodeName) {
        this.executorNodeName = executorNodeName;
    }

    @NonNull
    public List<String> getEnclosingBlocks() {
        return enclosingBlocks;
    }

    public void setEnclosingBlocks(@NonNull List<String> enclosingBlocks) {
        this.enclosingBlocks.addAll(enclosingBlocks);
    }

    @NonNull
    public List<String> getEnclosingBlockNames() {
        return enclosingBlockNames;
    }

    public void setEnclosingBlockNames(@NonNull List<String> enclosingBlockNames) {
        this.enclosingBlockNames.addAll(enclosingBlockNames);
    }

    private static final long serialVersionUID = 1L;
}
