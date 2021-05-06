package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.io.*;
import java.util.Properties;

public class Configurations {

    // static variable single_instance of type Singleton
    private static Configurations single_instance = null;

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


    public int getNumOfThreads(){
        return Integer.parseInt(properties.getProperty("threadPoolSize"));
    }


    public IMazeGenerator getMazeGenerator(){
        String prop = properties.getProperty("mazeGeneratingAlgorithm");
        if (prop.equals("MyMazeGenerator")){
            return new MyMazeGenerator();
        }
        else if (prop.equals("SimpleMazeGenerator")){
            return new SimpleMazeGenerator();
        }
        else{
            return new EmptyMazeGenerator();
        }
    }

    public ASearchingAlgorithm getMazeSearchingAlgorithm(){
        String searchingalgorithm = properties.getProperty("mazeSearchingAlgorithm");
        if (searchingalgorithm.equals("BreadthFirstSearch")){
            return new BreadthFirstSearch();
        }
        else if (searchingalgorithm.equals("DepthFirstSearch")){
            return new DepthFirstSearch();
        }
        else {
            return new BestFirstSearch();
        }
    }


}
