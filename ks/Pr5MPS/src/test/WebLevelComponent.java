package test;

import java.util.ArrayList;
import java.util.List;

public class WebLevelComponent {
    private String url;
    private int level;
    private String pageName;
    private List<WebLevelComponent> nextLevel;
    private boolean isBroken, isIDUMA, failed;

    public WebLevelComponent() {
        super();
        nextLevel = new ArrayList<>();
        this.isBroken = false;
        this.isIDUMA = false;
        this.failed = false;
    }

    public WebLevelComponent(String url, int level) {
        this();
        this.url = url;
        this.level = level;
    }

    public void addChild(WebLevelComponent element) {
        this.nextLevel.add(element);
    }

    public List<WebLevelComponent> getNextLevel() {
        return this.nextLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void setIsBroken() {
        this.isBroken = true;
    }

    public boolean isIDUMA() {
        return isIDUMA;
    }

    public void setIDUMA() {
        isIDUMA = true;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.level; i++) {
            sb.append("\t");
        }
        if (isBroken) {
            sb.append(String.format("\u001B[31m %s: (BROKEN LINK!!)\u001B[0m\n", url));
        } else if (isIDUMA) {
            sb.append(String.format("\u001B[34m %s: (IDUMA)\u001B[0m\n", url));
        } else if (failed) {
            sb.append(String.format("\u001B[31m %s: (INCIDENCIA)\u001B[0m\n", url));
        } else {
            sb.append(String.format("[ %s: %s\n", url, pageName));
        }
        for (
                WebLevelComponent wlc : nextLevel) {
            sb.append(wlc.toString());
        }
        return sb.toString();
    }

}
