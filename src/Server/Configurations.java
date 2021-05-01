package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configurations {

    // static variable single_instance of type Singleton
    private static Configurations single_instance = null;
    // variable of type String
    public Properties properties;

    // private constructor restricted to this class itself
    private Configurations()
    {
        properties = setproperties();
    }

    // static method to create instance of Singleton class
    public static Configurations getInstance()
    {
        if (single_instance == null)
            single_instance = new Configurations();
        return single_instance;
    }



    public static Properties setproperties() {
        try{

            Properties prop = new Properties();
            OutputStream output = new FileOutputStream("resources/config.propertis");

            // set key and value properties
            prop.setProperty("threadPoolSize","2");
            prop.setProperty("mazeGeneratingAlgorithm","MyMazeGenerator");
            prop.setProperty("mazeSearchingAlgorithm","BestFirstSearch");

            // save a properties file
            prop.store(output, null);

            // load a properties file
            prop.load(new FileInputStream("resources/config.propertis"));
            return prop;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }









}
