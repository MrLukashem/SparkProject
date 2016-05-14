package ui;

import utills.CustomFile;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrlukashem on 14.05.16.
 */
public class MainWindowController {
    protected MainWindow mWindow;
    protected DataProvider mProvider
            = new DataProvider();
    protected List<CustomFile> mFiles
            = new ArrayList<CustomFile>();

    public MainWindowController() {
        mWindow = new MainWindow();
    }

    public MainWindow getWindow() {
        return mWindow;
    }

    public DataProvider getProvider() {
        return mProvider;
    }

    public static class DataProvider {
        protected List<FileListener> mListeners
                = new ArrayList<FileListener>();
        protected MainWindowController mController;

        protected void fire() {
            for(FileListener listener : mListeners) {
                listener.callback(mController.mFiles);
            }
        }

        protected void setController(@Nonnull MainWindowController controller) {
            mController = controller;
        }

        public static interface FileListener {
            public void callback(List<CustomFile> files);
        }

        public void setFileListener(@Nonnull FileListener listener) {
            mListeners.add(listener);
        }
    }
}
