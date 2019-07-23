package parser;

import java.io.*;
import java.util.*;

public class MyParser
{
    private Code codeUtil = Code.getInstance();
    private final static int A_COMMAND = 0,C_COMMAND = 1,L_COMMAND = 2; //final static
    private LinkedList<String> content = new LinkedList<>();
    public String currentLine;

    public LinkedList<String> setSource(String filePath)
    {
        try
        {
            InputStream is = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader mBufferedReader = new BufferedReader(isr);
            String line;
            while ((line= mBufferedReader.readLine())!=null)
                if(isCode(line))
                    content.add(line);
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
            currentLine = content.pollFirst();
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
    public String getBinary()
    {
        advance();
        System.out.println(currentLine);
        String result = "";
        switch (commandType())
        {
            case A_COMMAND:
                result = codeUtil.address(symbol());
                System.out.println(codeUtil.address(symbol()));
                break;
            case L_COMMAND:
                break;
            case C_COMMAND:
                String comp = codeUtil.comp(comp());
                String dest = codeUtil.dest(dest());
                String jump = codeUtil.jump(jump());
                result = comp+dest+jump;
                System.out.println(comp+dest+jump);
                break;
        }

        return result;
    }
}
