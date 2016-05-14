package main;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import utills.CustomFile;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mrlukashem on 14.05.16.
 */
public class Driver implements Serializable {
    private Driver() { }

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

    public boolean pushToRemoteMachine() {
        JavaSparkContext context = initialize();
        // temporary disabled.
     /*   if (mFiles.isEmpty()) {
            // better to trigger exception?
            return false;
        } */

        // temporary test commends.
        List<String> data = Arrays.asList("hi", "fucking", "mother", "fucker", "mrruuu");
        JavaRDD<String> rdd = context.parallelize(data);
        JavaRDD<String> pipe = rdd.pipe(mConfigurator.mScriptName);
        pipe.foreach( new VoidFunction<String>() {
            public void call(String line) {
                System.out.println(line); //this is dummy function call
            }});

        return true;
    }

    public void configure(@Nonnull Configurator configurator) {
        mConfigurator = configurator;
    }
}
