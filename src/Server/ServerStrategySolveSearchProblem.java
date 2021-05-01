

package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;
import java.time.chrono.IsoChronology;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void applyStrategy(InputStream InFromClient, OutputStream outToClient) {
        try{
            ObjectInputStream fromClient = new ObjectInputStream(InFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            ISearchable searchablemaze = new SearchableMaze((Maze)fromClient.readObject());

            String mazepath = tempDirectoryPath + "\\" + searchablemaze.hashCode();

            File solutionfile = new File(mazepath);
            Solution sol;
            boolean alreadysolved = solutionfile.exists();

            //if the solution already solved before
            if (alreadysolved){
                sol = (Solution)objectFromFile(mazepath);
                toClient.writeObject(sol);
                toClient.flush();
            }

            //else, solve it and save it in the file
            else{
                //Properties prop = Configurations.setproperties();
                ISearchingAlgorithm searchalgo = new BestFirstSearch();
                sol = searchalgo.solve(searchablemaze);
                File mazesolutionfile = new File(mazepath);
                FileOutputStream fileoutput = new FileOutputStream(mazesolutionfile);
                ObjectOutputStream objoutput = new ObjectOutputStream(fileoutput);
                objoutput.writeObject(sol);
                objoutput.flush();
                toClient.writeObject(sol);
                toClient.flush();
                objoutput.close();
            }
            toClient.close();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }



    public Object objectFromFile(String path){
        try{
            FileInputStream filein = new FileInputStream(path);
            ObjectInputStream objin = new ObjectInputStream(filein);
            Object object = objin.readObject();
            objin.close();
            return object;

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}



