package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
/**
 * A class that implements a server strategy that solves mazes and returns their solutions
 *  @author aviel avitan,ron shakutai
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    /**
     * gets a maze as an input from the client and returns a solution to the maze
     * @param inFromClient
     * @param outToClient
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            String SolutionsPath=tempDirectoryPath+"/solutions";
            String MazesPath=tempDirectoryPath+"/mazes";
            new File(SolutionsPath).mkdirs();
            new File(MazesPath).mkdirs();
            File SolutionDir=new File(SolutionsPath);
            File MazesDir=new File(MazesPath);
            File[] solved=MazesDir.listFiles();
            int count=solved.length;
            boolean areMazesEqual=false;
            String MazeFileName="";
            Maze searchable=(Maze)fromClient.readObject();
            for (int i = 0; i <solved.length; i++) {
                MyDecompressorInputStream read=new MyDecompressorInputStream(new FileInputStream(MazesPath+"/"+solved[i].getName()));
                byte[] savedBytes=new byte[searchable.toByteArray().length];
                read.read(savedBytes);
                if(checkEqual(searchable.toByteArray(),savedBytes)) {
                    areMazesEqual = true;
                    MazeFileName=solved[i].getName();
                    break;
                }
            }
            if(areMazesEqual){
                String wanted=SolutionsPath+"/"+MazeFileName;
                FileInputStream readSolution=new FileInputStream(wanted);
                ObjectInputStream objectStream=new ObjectInputStream(readSolution);
                toClient.writeObject(objectStream.readObject());
                return;
            }
            ISearchingAlgorithm solve= Configurations.getSearchingAlgorithm();
            Solution ans=solve.solve(new SearchableMaze(searchable));
            toClient.writeObject(ans);
            FileOutputStream saveSolution=new FileOutputStream(SolutionsPath+"/"+count);
            ObjectOutputStream saveSolution2=new ObjectOutputStream(saveSolution);
            saveSolution2.writeObject(ans);
            saveSolution.flush();
            saveSolution.close();
            MyCompressorOutputStream saveMaze=new MyCompressorOutputStream(new FileOutputStream(MazesPath+"/"+count));
            saveMaze.write(searchable.toByteArray());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * a comparator for byte arrays that checks if they are equal
     * @param searchable
     * @param check
     * @return
     */
    private boolean checkEqual(byte[] searchable,byte[] check){
        int endIndex=searchable.length;
        for (int i = 0; i <searchable.length ; i++) {
            if (searchable[i] != check[i])
                return false;
        }
        return true;
    }
}
