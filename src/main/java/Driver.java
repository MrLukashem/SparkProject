import org.apache.spark.SparkConf;
import org.apache.spark.SparkFiles;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.ui.JettyUtils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrlukashem on 14.05.16.
 */
public class Driver implements Serializable {
    private Driver() { }

    public static void main(String[] args) throws IOException {
        Driver internalDriverRef =
                Driver.getDriver();
        IOHandler ioHandler = new IOHandler(args[0]);

        Driver.Configurator configurator =
                new Driver.Configurator();
        configurator.mScriptName = ioHandler.getScriptPath().getPath();
        configurator.mAppName = "SampleApp";

        internalDriverRef.configure(configurator);
        internalDriverRef.pushToRemoteMachine(ioHandler.getFileToComputer(), ioHandler.getScriptPath());
    }

    protected Configurator mConfigurator;
    protected List<CustomFile> mFiles
            = new ArrayList<>();

    protected static Driver mMe = new Driver();

    public static Driver getDriver() {
        return mMe;
    }

    public static class Configurator implements Serializable {
        public String mScriptName;
        public String mAppName;
    }

    public void handleFiles(@Nonnull List<CustomFile> files) {
        mFiles = files;
    }

    public JavaSparkContext initialize() throws ExceptionInInitializerError {
        //TODO: What if mAppName is null?
        SparkConf conf = new SparkConf()
                .setAppName(mConfigurator.mAppName);
        JavaSparkContext context = new JavaSparkContext(conf);

        return context;
    }

    public boolean pushToRemoteMachine(List<CustomFile> files, CustomFile script) {
        JavaSparkContext context = initialize();
        for (CustomFile file : files) {
            context.addFile(file.getPath());
        }

        context.addFile("/data/allin/fextorbis.py");
        context.addFile("/data/allin/fextorbis.pyc");
	    context.addFile("/data/allin/fextor/config");
        context.addFile("/data/allin/fextor/fextor_service.py");
        context.addFile("/data/allin/fextor/nkjp360-meaningless-no-prep-freq-above-3500.txt");
        context.addFile("/data/allin/fextor/nkjp500");
        context.addFile("/data/allin/fextor/startFex.py");
        context.addFile("/data/allin/fextor/startFex.pyc");
        context.addFile("/data/allin/fextor/text.ccl");

        List<String> paths = new ArrayList<>();
        context.addFile(mConfigurator.mScriptName);

        for (CustomFile file : files) {
            paths.add(SparkFiles.get(file.getName()));
        }
        List<String> data = paths;

        JavaRDD<String> rdd = context.parallelize(data);
        JavaRDD<String> pipe = rdd.pipe(SparkFiles.get(script.getName()));
        pipe.foreach( new VoidFunction<String>() {
            public void call(String line) {
                System.out.println(line); //this is dummy function call
            }});
        pipe.saveAsTextFile("/root/res");
        return true;
    }

    public void configure(@Nonnull Configurator configurator) {
        mConfigurator = configurator;
    }
}
