package og.simplystatus.kel.simplystatus;

import java.util.List;

public class SimplyStatusRoot {
    private boolean viewIP;
    private boolean viewStatic;

    public boolean getViewIP() { return viewIP; }
    public boolean getViewStatic() { return viewStatic; }

    public void setViewIP(boolean stat) {
        this.viewIP = stat;
    }
    public void setViewStatic(boolean stat) {
        this.viewIP = stat;
    }

    @Override
    public String toString() {
        return "{" + "viewIP:" + viewIP + ",viewStatic" + viewStatic + "}";
    }
}
