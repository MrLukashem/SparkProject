import javax.annotation.Nonnull;

/**
 * Created by mrlukashem on 14.05.16.
 */
public class CustomFile {
    protected String mName;
    protected String mPath;

    protected String extractName(String path) {
        String[] elements = path.split("/");
        mName = elements[elements.length - 1];
        return mName;
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
