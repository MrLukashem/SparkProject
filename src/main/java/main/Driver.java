package main;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import utills.CustomFile;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mrlukashem on 14.05.16.
 */
public class Driver implements Serializable {
    private Driver() { }

    protected static Driver mMe = new Driver();

    public static Driver getDriver() {
        return mMe;
    }

    public void handleFiles(List<CustomFile> files) {

    }

    public void pushToRemoteMachine() {
        SparkConf conf = new SparkConf().setAppName("Simple Application");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> data = Arrays.asList("hi", "fucking", "mother", "fucker", "mrruuu");
        JavaRDD<String> rdd = sc.parallelize(data);
        JavaRDD<String> pipe = rdd.pipe("/home/mrlukashem/bin/spark/SparkProject/sample.sh");
        pipe
                .foreach( new VoidFunction<String>(){
                    public void call(String line) {
                        System.out.println(line); //this is dummy function call
                    }});
    }

    public void configure() {
        //TODO: have to be implemented.
    }
}
