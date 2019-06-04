package Server;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class  Configurations {
    private Configurations(){};

    public static void setSearchingAlgorithm(String SearchingAlgorithm){
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("./resources/config.properties"));
            OutputStream out =new FileOutputStream("./resources/config.properties");
            prop.setProperty("SearchingAlgorithm",SearchingAlgorithm);
            prop.store(out,"the new SearchingAlgorithm");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static ISearchingAlgorithm getSearchingAlgorithm(){
        ISearchingAlgorithm ans=null;
        try(InputStream in=new FileInputStream("./resources/config.properties")){
            Properties prop=new Properties();
            prop.load(in);
            String searcher;
            searcher=prop.getProperty("SearchingAlgorithm");
            if(searcher==null)
                searcher="Best First Search";
            switch (searcher){
                case("Best First Search"):
                    ans=new BestFirstSearch();
                break;
                case("Breadth First Search"):
                    ans=new BreadthFirstSearch();
                break;
                case("Depth First Search"):
                    ans=new DepthFirstSearch();
                break;
            }
            return ans;

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return ans;

    }
    public static void setThreadPoolNum(String ThreadPoolNum){
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("./resources/config.properties"));
            OutputStream out =new FileOutputStream("./resources/config.properties");
            prop.setProperty("ThreadPool",ThreadPoolNum);
            prop.store(out,"Thread Pool num");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static int getThreadPoolNum(){
        ISearchingAlgorithm ans=null;
        try(InputStream in=new FileInputStream("./resources/config.properties")){
            Properties prop=new Properties();
            prop.load(in);
            String num;
            num=prop.getProperty("ThreadPool");
            return parseInt(num);

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return 1;
    }
    public static void setMazeGenerator(String MazeGenerator){
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("./resources/config.properties"));
            OutputStream out =new FileOutputStream("./resources/config.properties");
            prop.setProperty("MazeGenerator",MazeGenerator);
            prop.store(out,"the new Maze generator");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static IMazeGenerator getMazeGenerator(){
        IMazeGenerator ans=null;
        try(InputStream in=new FileInputStream("./resources/config.properties")){
            Properties prop=new Properties();
            prop.load(in);
            String generator;
            generator=prop.getProperty("MazeGenerator");
            if(generator==null)
                generator="My Maze generator";
            switch (generator){
                case("My Maze Generator"):
                    ans=new MyMazeGenerator();
                    break;
                case("Empty Maze Generator"):
                    ans=new EmptyMazeGenerator();
                    break;
                case("Simple Maze Generator"):
                    ans=new SimpleMazeGenerator();
                    break;
            }
            return ans;

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return ans;

    }
}