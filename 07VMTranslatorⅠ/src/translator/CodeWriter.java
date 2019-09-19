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
                result.push("@SP");
                result.push("A=M-1");
                result.push("D=M");
                //pop
                result.push("@SP");
                result.push("M=M-1");
                //add
                result.push("@SP");
                result.push("A=M-1");
                result.push("D=M+D");
                //pop
                result.push("@SP");
                result.push("M=M-1");
                //pushResult
                result.push("@SP");
                result.push("A=M");
                result.push("M=D");
                result.push("@SP");
                result.push("M=M+1");
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
    }
    public LinkedList<String> writerPushPop(int commandType,String segment,int index)
    {
        LinkedList<String> result = new LinkedList<>();
        String indexString = String.valueOf(index);
        switch (commandType)
        {
            case Parser.C_PUSH:
                if(segment.equals("constant"))
                {
                    result.push("@"+indexString);
                    result.push("D=A");
                    result.push("@SP");
                    result.push("A=M");
                    result.push("M=D");
                    result.push("@SP");
                    result.push("M=M+1");
                }
                break;
            case Parser.C_POP:
                if(segment.equals("constant"))
                {
                    result.push("@SP");
                    result.push("M=M-1");
                }
                break;
        }
        return result;
    }
    public void close() {}
    public static void main(String args[])
    {
        CodeWriter cw = new CodeWriter();
        LinkedList<String> list = cw.WriterPushPop(Parser.C_PUSH,"constant",7);
        for(String s : list)
            System.out.println(s);
    }
}
