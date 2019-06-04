package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A class that implements a compressor for byte arrays
 *  @author aviel avitan,ron shakutai
 */
public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * writes to the file an integer
     * @param b
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        try {
            out.write(b);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * takes a byte array and writes it to the file after compressing it
     * @param b
     * @throws IOException
     */

    public void write(byte[] b) throws IOException {
        byte count=1;
        try {
            for (int i = 1; i < b.length; i++) {
                if (b[i] == b[i - 1]) {
                    count++;
                    if (i == b.length - 1) {
                        out.write(b[i]);
                        out.write(count);
                        break;
                    }
                    continue;
                } else {
                    out.write(b[i - 1]);
                    out.write(count);
                    count = 1;
                    if (i == b.length - 1) {
                        out.write(b[i]);
                        out.write(count);
                    }
                }
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    public OutputStream getOut() {
        return out;
    }
}