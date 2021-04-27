package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;


    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }


    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    /**
     * This method gets a byte array b, compresses its data and write it to the OutputStream.
     * First 12 array elements represents the maze size and locations of start and goal
     * positions. The other array elements from 12 to the end, we represent 8 bytes as
     * the decimal number they can represent. That way we compress the maze byte array by 8.
     * We do the same for the remainder that left from dividing the maze size by 8.
     *
     * @param b - byte array represents the maze
     * @throws IOException - if something went wrong
     */

    @Override
    public void write(byte[] b) throws IOException {
        try{
            /* The first 12 places of the array represents the size of the maze
            and start and goal positions data */
            for (int i = 0; i<12; i++){
                out.write(b[i]);
                out.flush();
            }

            /* We want to put all the other byte array data by dividing by 8
            and every 8 bytes will be saved as their decimal number.*/
            int MazeLen = b.length - 12;
            int mod8 = MazeLen % 8;

            String strToNum = "";
            int i =12;

            // Representing the maze until size divides by 8
            while ((i < b.length - mod8) && (i + 8 <= b.length)){
                /* Every 8 bytes will be represented as a decimal number
                in this for loop we concatenate every 8 bytes to a single string */
                for (int j = i; j < i+8; j++){
                    strToNum += Integer.toString(b[j]);
                }
                /* ParseInt by giving it string and radix 2 - decimal number
                Which we convert to a single byte. */
                out.write((byte)(Integer.parseInt(strToNum,2)));
                out.flush();
                i += 8;
                strToNum = "";
            }

            // We need to represent the remainder that left from maze size dividing by 8
            if (mod8 != 0){
                for (int j = i; j < b.length; j++){
                    strToNum += Integer.toString(b[j]);
                }
                /* ParseInt by giving it string and radix 2 - decimal number
                Which we convert to a single byte.*/
                out.write((byte)Integer.parseInt(strToNum,2));
                out.flush();
            }
            out.close();

        }
        catch (IOException e){
            System.out.println("There has been something wrong with the writing process (byte[])");
        }

    }

}
