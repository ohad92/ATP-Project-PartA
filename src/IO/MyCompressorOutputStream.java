package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) { this.out = out;  }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        try{
            List<String> temp = new ArrayList<String>(); //for check

            // the maze data (row,col,start and goal positions)
            for (int i = 0; i<12; i++){
                out.write(b[i]);
                out.flush();
                temp.add(Integer.toString(b[i]));
            }

            int Length= b.length - 12;
            int modulo = Length % 8;
            String string = "";


            // Every 8 bit's will be represented as a decimal number (up to 256)
            int i =12;
            while ((i < b.length - modulo) && (i + 8 <= b.length)){

                //Loop for 8 bit's
                for (int j = i; j < i+8; j++){
                    string += Integer.toString(b[j]);
                }
                temp.add(string);
                out.write((byte)(Integer.parseInt(string,2)));
                out.flush();
                i += 8;
                string = "";
            }

            // in case there is reminder
            if (modulo != 0){
                for (int j = i; j < b.length; j++){
                    string += Integer.toString(b[j]);
                }
                temp.add(string);
                out.write((byte)Integer.parseInt(string,2));
                out.flush();
            }

            out.close();






            /*
            List<Integer> temp = new ArrayList<Integer>(); //for check
            //b[23] = 1;
            // the maze data (row,col,start and goal positions)
            for (int i = 0; i<12; i++){
                out.write(b[i]);
                out.flush();
                temp.add((int)b[i]);
            }

            int index = 12;
            int len = 0;
            int bytetowrite = 0;
            boolean helper = false;

            while (index != b.length){
                if (b.length-1 - index < 8 && !helper){
                    len = 0;
                    bytetowrite=0;
                    for (;index<b.length;index++){
                        bytetowrite += (b[index] * Math.pow(2,len));
                        len++;
                    }
                    out.write((byte)bytetowrite);
                    out.flush();
                    temp.add(bytetowrite);

                }
                else if (len == 8){
                    out.write((byte)bytetowrite);
                    out.flush();
                    temp.add(bytetowrite);
                    len = 0;
                    bytetowrite=0;
                    helper=false;
                }
                else{
                    bytetowrite += (b[index] * Math.pow(2,len));
                    len++;
                    index++;
                    helper=true;
                }

            }
            out.close();
*/
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
