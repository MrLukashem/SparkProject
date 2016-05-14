package utills;

import javax.annotation.Nonnull;

/**
 * Created by mrlukashem on 14.05.16.
 */
public class CustomFile {
    protected String mName;
    protected String mPath;

    protected String extractName(String path) {
        return null;
    }

    public CustomFile(String path) {
        mPath = path;
        mName = extractName(path);
    }

    public void setName(@Nonnull String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setPath(@Nonnull String path) {
        mPath = path;
    }

    public String getPath() {
        return mPath;
    }
}
