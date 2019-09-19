package translator;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Parser
{
    private LinkedList<String> content = new LinkedList<>();
    private String currentLine;
    public static final int C_ARITHMETIC = 0,C_PUSH = 1,C_POP = 2,C_LABEL = 3,C_GOTO = 4,
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
    public boolean hasMoreCommands()
    {
        return (content.size()!=0);
    }
    public void advance()
    {
        if(hasMoreCommands())
            currentLine = filterLine(content.poll());
        else
            currentLine = null;
    }
    public int commandType()
    {
        switch(currentLine.substring(0,2))
        {
            case "pu":
                return C_PUSH;
            case "po":
                return C_POP;
            case "la":
                return C_LABEL;
            case "go":
                return C_GOTO;
            case "if":
                return C_IF;
            case "fu":
                return C_FUNCTION;
            case "ca":
                return C_CALL;
            case "re":
                return C_RETURN;
            default:
                return C_ARITHMETIC;
        }

    }
    public String arg1()
    {
        switch(commandType())
        {
            case C_PUSH:
                return "push";
            case C_POP:
                return "pop";
            case C_LABEL:
                return "label";
            case C_CALL:
                return "call";
            case C_FUNCTION:
                return "function";
            case C_IF:
                return "if-goto";
            case C_GOTO:
                return "goto";
            default:
                return currentLine;
        }
    }
    public String arg2()
    {
        String arg2 = filterLine(currentLine.substring(currentLine.indexOf(" ")));
        int position_space = arg2.indexOf(" ");
        arg2 = arg2.substring(0,position_space);
        return arg2;
    }
    private String filterLine(String line)
    {
        int position_space = 1;
        int position_note = -1;
        //过滤空格
        while(line.substring(0,1).equals(" "))
            line = line.substring(position_space);
        //过滤注释
        position_note = line.indexOf("//");
        if(position_note!=-1)
            line = line.substring(0,position_note);
        return line;
    }
    public String getCode()
    {
        return null;
    }
}
