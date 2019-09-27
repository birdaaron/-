package parser;

import java.io.*;
import java.util.*;

public class MyParser
{
    private Code codeUtil = new Code();
    private final static int A_COMMAND = 0,C_COMMAND = 1,L_COMMAND = 2,NO_COMMAND = 3; //final static
    private LinkedList<String> content = new LinkedList<>();
    private SymbolTable symbolTable;
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
                if(!line.equals(""))
                    content.add(line);
            //第一次编译
            if(hasMoreCommands())
                initSymbolTable();

        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
        return content;
    }

    public boolean hasMoreCommands()
    {
       return content.size()!=0;
    }
    public int contentSize(){ return content.size();}
    private void advance()
    {
        if(hasMoreCommands())
            currentLine = filterLine(content.pollFirst());
        else
            currentLine = null;
    }
    public int commandType()
    {
        if(currentLine.equals(""))
            return NO_COMMAND;
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
        int position_equal = currentLine.indexOf('=');
        if(position_equal==-1)
            return "null";
        else
            return currentLine.substring(0,position_equal);
    }
    public String comp()
    {
        int position = currentLine.indexOf('=');
        if(position!=-1)
            return currentLine.substring(position+1);
        else
            return "0";
    }
    public String jump()
    {
        int position = currentLine.indexOf(';');
        if(position!=-1)
            return currentLine.substring(position+1);
        else
            return "null";
    }
    private void initSymbolTable()
    {
        symbolTable = new SymbolTable();
        @SuppressWarnings("unchecked")
        LinkedList<String> contentCopy = (LinkedList<String>)this.content.clone();
        int romAddress = 0;
        do
        {
            advance();
            if(commandType()==L_COMMAND)
            {
                int position = currentLine.indexOf(')');
                String symbol = currentLine.substring(1,position);
                String address = symbolTable.toBinaryString(romAddress);
                symbolTable.addEntry(symbol,address);
            }
            else if(commandType()!=NO_COMMAND)
                romAddress++;
        } while(hasMoreCommands());
        content = contentCopy;
        currentLine = "";
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
    public String getBinary()
    {
        advance();
        String result = "";
        switch (commandType())
        {
            case A_COMMAND:
                String symbol = currentLine.substring(1);
                if(symbol.charAt(0)<=57 && symbol.charAt(0)>=48)    //判断是否为数字
                    result = symbolTable.getDigitRamAddress(symbol);
                else if(symbolTable.contatins(symbol))
                    result = symbolTable.getAddress(symbol);
                else
                {
                    symbolTable.addVariable(symbol);
                    result = symbolTable.getAddress(symbol);
                }
                break;
            case C_COMMAND:
                String comp = codeUtil.comp(comp());
                String dest = codeUtil.dest(dest());
                String jump = codeUtil.jump(jump());
                result = comp+dest+jump;
                break;
        }

        return result;
    }
}
