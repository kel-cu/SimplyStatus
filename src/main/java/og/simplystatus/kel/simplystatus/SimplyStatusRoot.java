package og.simplystatus.kel.simplystatus;

public class SimplyStatusRoot {
    private boolean viewIP;
    private boolean viewStatic;

    public boolean getViewIP() { return viewIP; }
    public boolean getViewStatic() { return viewStatic; }

    public void setViewIP(boolean stat) {
        this.viewIP = stat;
    }
    public void setViewStatic(boolean stat) {
        this.viewStatic = stat;
    }
}
