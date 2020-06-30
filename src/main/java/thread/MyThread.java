package thread;

import db.MyDatabase;
import java.util.List;

public class MyThread extends Thread {
    private final MyDatabase myDatabase = MyDatabase.getInstance();
    private final int fromIndex;
    private final int toIndex;
    private final String format;

    public MyThread(int fromIndex, int toIndex, String format) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.format = format;
    }

    @Override
    public void run() {
        List<String> content = myDatabase.getContent();
        for (int i = fromIndex; i < toIndex; i++) {
            String line = content.get(i);
            if (line.toLowerCase().matches(format)) {
                myDatabase.getSearchedResult().add(line);
            }
        }
    }
}
