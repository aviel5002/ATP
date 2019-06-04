package IO;

import java.io.IOException;
import java.io.InputStream;
/**
 * A class that implements a decompressor for byte arrays
 *  @author aviel avitan,ron shakutai
 */

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * reads from file one byte
     * @return
     * @throws IOException
     */
    @Override
    public int read() throws IOException {
        int i=0;
        try {
            i=in.read();
        }
        catch(IOException e){
            System.out.println(e);
        }
        return i;
    }

    /**
     * reads from file and fills a byte array after decompressing its content
     * @param maze
     * @return
     * @throws IOException
     */
    public int read(byte[] maze) throws IOException {
        try {
            byte curr, count;
            int j = 0;
            while (true) {
                curr = (byte) in.read();
                count = (byte) in.read();
                if (curr == -1)
                    break;
                while (count > 0) {
                    if(j>=maze.length)
                        break;
                    maze[j] = curr;
                    count--;
                    j++;
                }
                if(j>=maze.length)
                    break;
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return 0;
    }
}