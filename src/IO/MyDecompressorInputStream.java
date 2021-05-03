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


            // the maze data (row,col,start and goal positions)

            for (int i=0; i < 12; i++) {
                b[i] = (byte) read();
            }
            int numOfRow = (b[0]*255) + (b[1] & 0xFF);
            int numOfCOl = (b[2]*255) + (b[3]& 0xFF);
            String convertToBin;


            int i=12;
            while ((i < ((numOfCOl * numOfRow) + 12) - ((numOfCOl * numOfRow) % 8)) && (i + 8 <= ((numOfCOl * numOfRow) + 12))) {


                // Convert the byte that was read to a binary number
                int num = read();
                convertToBin = String.format("%8s", Integer.toBinaryString(num)).replace(' ', '0');

                for (int j = 0; j < 8; j++) {

                    b[i] = (byte) (convertToBin.charAt(j) - 48);
                    i++;
                }
            }
            convertToBin = "";

            if (((numOfCOl * numOfRow) % 8) > 0) {
                int num = read();
                String strToBin = Integer.toBinaryString(num);

                convertToBin = String.format("%0" + ((((numOfCOl * numOfRow) % 8) + 1) - strToBin.length()) + "d%s", 0, strToBin).substring(1, ((numOfCOl * numOfRow) % 8) + 1);
                for (int j = 0; j < convertToBin.length(); j++) {
                    b[i] = (byte) (convertToBin.charAt(j) - 48);
                    i++;
                }
            }
            in.close();
            return 0;
        }

        catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}



