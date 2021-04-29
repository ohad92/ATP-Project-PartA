package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    public int read(byte[] b) throws IOException {

        // the maze data (row,col,start and goal positions)
        for (int i=0;i<12;i++){
            b[i] = (byte)read();
        }

        //List<Integer> temp = new ArrayList<Integer>();

        boolean done = false;
        int len = read(); //num of occurrences 0 or 1
        int index = 12; // start from index 12
        int wallorpass = 1;

        // first place is for 0 occurrences
        for (int i=0;i<len;i++){
            b[index++] = (byte)0;
            //temp.add(0);
        }

        // loop over the maze board
        while (index < b.length && !done) {
            if (in.available() == 0)
                done = true;

            else{
                len = read();
                for (int i=0;i<len;i++){
                    b[index++] = (byte)wallorpass;
                    //temp.add(wallorpass);
                }
                if (wallorpass == 0)
                    wallorpass = 1;
                else if (wallorpass == 1)
                    wallorpass = 0;
            }

        }

        return 0;
    }


}
