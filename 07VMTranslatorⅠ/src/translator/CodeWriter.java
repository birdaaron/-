package translator;

import java.util.LinkedList;
import java.util.Queue;

public class CodeWriter
{
    public void setFileName(String fileName){}

    public LinkedList<String> writeArithmetic(String command)
    {
        LinkedList<String> result = new LinkedList<>();
        switch (command)
        {
            case "add":
                result.addAll(codeAdd());
                break;
            case "sub":
                break;
            case "neg":
                break;
            case "eq":
                break;
            case "gt":
                break;
            case "lt":
                break;
            case "and":
                break;
            case "or":
                break;
            case "not":
                break;
        }
        return result;
    }
    public LinkedList<String> writerPushPop(int commandType,String segment,String index)
    {
        LinkedList<String> result = new LinkedList<>();
        switch (commandType)
        {
            case Parser.C_PUSH:
                result.addAll(codePush(segment,index));
                break;
            case Parser.C_POP:
                result.addAll(codePop(segment));
                break;
        }
        return result;
    }
    public void close() {}
    private LinkedList<String> codePop(String segment)
    {
        LinkedList<String> result = new LinkedList<>();
        result.add("@SP");
        result.add("M=M-1");
        switch (segment)
        {

        }
        return result;
    }
    private LinkedList<String> codePush(String segment,String index)
    {
        LinkedList<String> result = new LinkedList<>();
        switch (segment)
        {
            case "constant":
                result.add("@"+index);
                result.add("D=A");
                result.add("@SP");
                result.add("A=M");
                result.add("M=D");
                result.add("@SP");
                result.add("M=M+1");
                break;
        }
        return result;
    }
    private LinkedList<String> codeAdd()
    {
        LinkedList<String> result = new LinkedList<>();
        //get first num
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M");
        //pop
        result.addAll(codePop("NO_SEGMENT"));
        //get second num
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M+D");
        //pop
        result.addAll(codePop("NO_SEGMENT"));
        //pushResult
        result.add("@SP");
        result.add("A=M");
        result.add("M=D");
        result.add("@SP");
        result.add("M=M+1");
        return result;
    }

}
