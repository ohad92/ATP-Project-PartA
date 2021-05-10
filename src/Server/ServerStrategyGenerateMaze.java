package Server;

import java.io.*;
import java.nio.channels.Channels;
import java.util.Properties;

import algorithms.mazeGenerators.*;
import IO.SimpleCompressorOutputStream;


public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            int[] mazesize = (int[]) fromClient.readObject();
            //Properties prop = Configurations.setproperties();
            AMazeGenerator mazegenerator = new MyMazeGenerator();
            Maze maze = mazegenerator.generate(mazesize[0],mazesize[1]);

            ByteArrayOutputStream bytearrayoutput = new ByteArrayOutputStream();
            SimpleCompressorOutputStream outputcompressor = new SimpleCompressorOutputStream(bytearrayoutput);

            outputcompressor.write(maze.toByteArray());
            outputcompressor.flush();

            toClient.writeObject(bytearrayoutput.toByteArray());
            toClient.flush();

        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
