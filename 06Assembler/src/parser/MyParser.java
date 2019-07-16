package parser;

import java.io.*;
import java.util.Stack;

public class MyParser
{
    private Code codeUtil = Code.getInstance();
    private final static int A_COMMAND = 0,C_COMMAND = 1,L_COMMAND = 2; //final static
    private Stack<String> content = new Stack<>();
    public String currentLine;

    public Stack<String> setSource(String filePath)
    {
        try
        {
            InputStream is = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader mBufferedReader = new BufferedReader(isr);
            String line;
            while ((line= mBufferedReader.readLine())!=null)
                if(isCode(line))
                    content.push(line);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }
    public boolean isCode(String line)
    {
        return (!line.equals("")&&!line.substring(0,2).equals("//"));
    }
    private boolean hasMoreCommands()
    {
       return content.size()!=0;
    }
    public void advance()
    {
        if(hasMoreCommands())
            currentLine = content.pop();
        else
            currentLine = null;
    }
    public int commandType()
    {
        switch (currentLine.charAt(0))
        {
            case '@':
                return A_COMMAND;
            case '(':
                return L_COMMAND;
            default:
                return C_COMMAND;
        }
    }
    public String symbol()
    {
        if(commandType()==A_COMMAND||commandType()==L_COMMAND)
            return currentLine;
        else
            return null;
    }
    public String dest()
    {
        int position = currentLine.indexOf('=');
        if(position!=-1)
            return currentLine.substring(0,position);
        else
            return "null";
    }
    public String comp()
    {
        int position = currentLine.indexOf('=');
        return currentLine.substring(position+1);
    }
    public String jump()
    {
        int position = currentLine.indexOf(';');
        if(position!=-1)
            return currentLine.substring(position+1);
        else
            return "null";
    }
    public void getBinary()
    {
        advance();
        switch (commandType())
        {
            case A_COMMAND:
                System.out.println(codeUtil.address(symbol()));
                break;
            case L_COMMAND:
                break;
            case C_COMMAND:
                String comp = codeUtil.comp(comp());
                String dest = codeUtil.dest(dest());
                String jump = codeUtil.jump(jump());
                System.out.println(comp+dest+jump);
        }

    }
}
