package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    public void write(byte[] b) throws IOException {
        try{
            //List<Integer> temp = new ArrayList<Integer>(); //for check

            // the maze data (row,col,start and goal positions)
            for (int i = 0; i<12; i++){
                out.write(b[i]);
                out.flush();
                //temp.add((int)b[i]);
            }


            int len = 1;  //num of occurrences 0 or 1
            int index = 12; // start from index 12


            //in case that the maze start with "1"
            if (b[index] == 1){
                //temp.add(0);
                out.write((byte)0);
                out.flush();
            }

            // loop over the maze board
            for (int i=index;i<b.length;i++){
                if (i < b.length-1 && b[i] == b[i+1]){
                    len++;
                    // if 0 or 1 reapet more then 255 times
                    if (len==256){
                        out.write((byte)len-1);
                        out.flush();
                        //temp.add(len-1);
                        out.write((byte)0);
                        out.flush();
                        //temp.add(0);
                        len=1;
                    }
                }
                else{
                    out.write((byte)len);
                    out.flush();
                    //temp.add(len);
                    len=1;
                }
                index++;
            }

//            while (index < b.length){
//                if (b[index] == 0){
//                    while (index < b.length-1 && b[index+1] == 0){
//                        len++;
//                        index++;
//                        if (len == 255){
//                            temp.add(len);
//
//                            if (index < b.length-1 && b[index+1] == 0){
//                                temp.add(0);
//                                len=0;
//                            }
//
//                        }
//                    }
//                    temp.add(len);
//                    index++;
//                    len=1;
//                }
//
//                else if (b[index] == 1){
//                    while (index < b.length-1 && b[index+1] == 1){
//                        len++;
//                        index++;
//                    }
//                    temp.add(len);
//                    index++;
//                    len=1;
//                }
//
//            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
