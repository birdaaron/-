package translator;

import java.util.LinkedList;
import java.util.Queue;

public class CodeWriter
{
    private CodeMod code = new CodeMod();
    public void setFileName(String fileName){}

    public LinkedList<String> writeArithmetic(String command)
    {
        LinkedList<String> result = new LinkedList<>();
        switch (command)
        {
            case "add":
                result.addAll(code.codeAdd());
                break;
            case "sub":
                result.addAll(code.codeSub());
                break;
            case "neg":
                result.addAll(code.codeNeg());
                break;
            case "eq":
                result.addAll(code.codeEq());
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
                result.addAll(code.codePush(segment,index));
                break;
            case Parser.C_POP:
                result.addAll(code.codePop(segment));
                break;
        }
        return result;
    }
    public void close() {}

}
