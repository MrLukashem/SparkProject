package main;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import ui.MainWindowController;
import utills.CustomFile;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mrlukashem on 14.05.16.
 */
public class Runner {
    public static void main(String[] args) {
        Driver internalDriverRef =
                Driver.getDriver();

 //       MainWindowController controller
  //              = new MainWindowController();
  //      controller.getWindow().showMe();
  //      controller.getProvider().setFileListener((List<CustomFile> files) -> {
   //         internalDriverRef.handleFiles(files);
   //         internalDriverRef.pushToRemoteMachine();
   //     });
        internalDriverRef.pushToRemoteMachine();
    }
}
