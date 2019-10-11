package translator;

import translator.codeMod.ArithMod;
import translator.codeMod.RamMod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;

public class CodeWriter
{
    private RamMod ramCode = new RamMod();
    private ArithMod arithMod = new ArithMod();
    public CodeWriter(String fileName)
    {
        ramCode.setFileName(fileName);
    }
    public LinkedList<String> writeArithmetic(String command)
    {
        LinkedList<String> result = new LinkedList<>();
        switch (command)
        {
            case "add":
                result.addAll(arithMod.codeAdd());
                break;
            case "sub":
                result.addAll(arithMod.codeSub());
                break;
            case "neg":
                result.addAll(arithMod.codeNeg());
                break;
            case "eq":
                result.addAll(arithMod.codeEq());
                break;
            case "gt":
                result.addAll(arithMod.codeGt());
                break;
            case "lt":
                result.addAll(arithMod.codeLt());
                break;
            case "and":
                result.addAll(arithMod.codeAnd());
                break;
            case "or":
                result.addAll(arithMod.codeOr());
                break;
            case "not":
                result.addAll(arithMod.codeNot());
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
                result.addAll(ramCode.codePush(segment,index));
                break;
            case Parser.C_POP:
                result.addAll(ramCode.codePop(segment,index));
                break;
        }
        return result;
    }
    public void close() {}

}
