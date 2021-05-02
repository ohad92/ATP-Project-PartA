package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    public int read() throws IOException {

        return in.read();
    }

    public int read(byte[] b) throws IOException {

        try {


        int numOfCOl = (b[3]& 0xFF) + (b[2]*255) ;
        int numOfRow = (b[1] & 0xFF) + (b[0]*255);

        int lenOfMaze = numOfCOl * numOfRow;
        String convertToNum = "";
        String convertToBin ;
        int i=0;

        for (; i < 12; i++) {
            b[i] = (byte) read();
        }
        boolean bol = false;
        while (!bol) {

            // Convert the byte that was read to a binary number
            int num = read();
            convertToBin = String.format("%8s", Integer.toBinaryString(num)).replace(' ', '0');

            for (int j = 0; j < 8; j++) {
                // setting b[i] to be the j pos of curBinary string
                // charAt() func is giving ASCII value so we decrease by 48
                b[i] = (byte) (convertToBin.charAt(j) - 48);
                i++;
            }
            if ((i < (lenOfMaze + 12) - (lenOfMaze % 8)) && (i + 8 <= (lenOfMaze + 12))) {
                bol = true;
            }
        }
        convertToBin = "";

        // Needs to represent the remainder from 8
        if ((lenOfMaze % 8) != 0) {
            int num = read();
            String strToBin = Integer.toBinaryString(num);

            // format the binary number to the size of mod8 filling with leading zeros
            convertToBin = String.format("%0" + (((lenOfMaze % 8) + 1) - strToBin.length()) + "d%s", 0, strToBin).substring(1, (lenOfMaze % 8) + 1);
            for (int j = 0; j < convertToBin.length(); j++) {
                b[i] = (byte) (convertToBin.charAt(j) - 48);
                i++;
            }
        }
        in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;

    }
}



