package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;
/**
 * A class that implements a server strategy that creates mazes and returns them to the client
 *  @author aviel avitan,ron shakutai
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {
    /**
     * gets maze sizes from the client and returns a maze as an output
     * @param inFromClient
     * @param outToClient
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ByteArrayOutputStream Myyyout=new ByteArrayOutputStream();
            toClient.flush();
            int[] sizes=(int[])fromClient.readObject();
            IMazeGenerator generate=Configurations.getMazeGenerator();
            Maze ans=generate.generate(sizes[0],sizes[1]);
            MyCompressorOutputStream compress=new MyCompressorOutputStream(Myyyout);
            compress.write(ans.toByteArray());
            Myyyout=(ByteArrayOutputStream)compress.getOut();
            toClient.writeObject(Myyyout.toByteArray());
            toClient.flush();
            toClient.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}