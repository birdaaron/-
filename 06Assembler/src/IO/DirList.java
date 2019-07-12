package IO;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class DirList
{
    public static void main(String args[]) throws Exception
    {
        InputStream is =
                new FileInputStream("C:\\Users\\大鸟先飞\\Desktop\\计算机系统要素\\projects\\06\\add\\Add.asm");
        InputStreamReader isr =
                new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String string = "abc";


            System.out.println(string.substring(0,2));

    }
}


