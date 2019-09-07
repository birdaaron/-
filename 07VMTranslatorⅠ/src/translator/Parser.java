package translator;

import java.io.*;
import java.util.LinkedList;

public class Parser
{
    private LinkedList<String> content;
    private String currentLine;
    private final int C_ARITHMETIC = 0,C_PUSH = 1,C_POP = 2,C_LABEL = 3,C_GOTO = 4,
                      C_IF = 5,C_FUNCTION = 6,C_RETURN = 7,C_CALL = 8;
    public Parser()
    {
        String filePath = "";
        try
        {
            InputStream is = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine())!=null)
                if(line!="")
                    content.add(line);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public Boolean hasMoreCommands(){return true;}
    public void advance(){}
    public int commandType(){return 0;}
    public String arg1(){return null;}
    public String arg2(){return null;}

}
