import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrlukashem on 06.06.16.
 */
public class IOHandler {
    protected CustomFile mFile;
    protected CustomFile mScriptPath;
    protected List<CustomFile> mFilesToCompute;

    public IOHandler(String filePath) throws IOException {
        mFile = new CustomFile(filePath);

        File handler = new File(mFile.getPath());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(handler));

        mFilesToCompute = new ArrayList<>();
        String line;
        mScriptPath = new CustomFile(bufferedReader.readLine());
        if (mScriptPath.getPath().isEmpty()) {
            throw new IOException();
        }

        while ((line = bufferedReader.readLine()) != null) {
            mFilesToCompute.add(new CustomFile(line));
        }
    }

    public CustomFile getScriptPath() throws IOException {
        return mScriptPath;
    }

    public List<CustomFile> getFileToComputer() {
        return mFilesToCompute;
    }
}
