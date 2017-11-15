package match;

public class GroupTaskThread implements Runnable {
    private Group group;
    public GroupTaskThread(Group group) {
        this.group = group;
    }

    @Override
    public void run() {
        group.planOnLine();
    }
}
