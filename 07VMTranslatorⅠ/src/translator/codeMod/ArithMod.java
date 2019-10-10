package translator.codeMod;

import java.util.LinkedList;

public class ArithMod
{
    private RamMod ramCode = new RamMod();
    public LinkedList<String> codeAdd()
    {
        LinkedList<String> result = new LinkedList<>();
        //get first num
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M");
        //pop
        result.addAll(ramCode.popStack());
        //get second num & add
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M+D");
        //pop
        result.addAll(ramCode.popStack());
        //pushResult
        result.add("@SP");
        result.add("A=M");
        result.add("M=D");
        result.add("@SP");
        result.add("M=M+1");
        return result;
    }
    public LinkedList<String> codeSub()
    {
        LinkedList<String> result = new LinkedList<>();
        //get first num
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M");
        //pop
        result.addAll(ramCode.popStack());
        //get second num & sub
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=D-M");
        //pop
        result.addAll(ramCode.popStack());
        //pushResult
        result.add("@SP");
        result.add("A=M");
        result.add("M=D");
        result.add("@SP");
        result.add("M=M+1");
        return result;
    }
    public LinkedList<String> codeNeg()
    {
        LinkedList<String> result = new LinkedList<>();
        //get num & neg
        result.add("@SP");
        result.add("A=M-1");
        result.add("M=-M");
        return result;
    }
    public LinkedList<String> codeEq()
    {
        LinkedList<String> result = new LinkedList<>();
        //get first num
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M");
        //pop
        result.addAll(ramCode.popStack());
        //get second num & sub
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=D-M");
        //pop
        result.addAll(ramCode.popStack());
        //pushResult
        result.add("@TRUE");
        result.add("D;JEQ");   //if D=0 then jump to (TRUE)
        result.add("D=0");     //else D = 0 (False
        result.add("@END");
        result.add("0;JMP");
        result.add("(TRUE)");
        result.add("D=-1");
        result.add("(END)");
        result.add("@SP");
        result.add("A=M-1");
        result.add("M=D");
        return result;
    }
    public LinkedList<String> codeGt()
    {
        LinkedList<String> result = new LinkedList<>();
        //get first num
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M");
        //pop
        result.addAll(ramCode.popStack());
        //get second num & sub
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=D-M");
        //pop
        result.addAll(ramCode.popStack());
        //pushResult
        result.add("@TRUE");
        result.add("D;JGT");   //if D>0 then jump to (TRUE)
        result.add("D=0");     //else D = 0 (False
        result.add("@END");
        result.add("0;JMP");
        result.add("(TRUE)");
        result.add("D=-1");
        result.add("(END)");
        result.add("@SP");
        result.add("A=M-1");
        result.add("M=D");
        return result;
    }
    public LinkedList<String> codeLt()
    {
        LinkedList<String> result = new LinkedList<>();
        //get first num
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M");
        //pop
        result.addAll(ramCode.popStack());
        //get second num & sub
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=D-M");
        //pop
        result.addAll(ramCode.popStack());
        //pushResult
        result.add("@TRUE");
        result.add("D;JLT");   //if D<0 then jump to (TRUE)
        result.add("D=0");     //else D = 0 (False
        result.add("@END");
        result.add("0;JMP");
        result.add("(TRUE)");
        result.add("D=-1");
        result.add("(END)");
        result.add("@SP");
        result.add("A=M-1");
        result.add("M=D");
        return result;
    }
    public LinkedList<String> codeAnd()
    {
        LinkedList<String> result = new LinkedList<>();
        //get first num
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M");
        //pop
        result.addAll(ramCode.popStack());
        //get second num & and
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=D&M");
        //pop
        result.addAll(ramCode.popStack());
        //pushResult
        result.add("@SP");
        result.add("A=M");
        result.add("M=D");
        result.add("@SP");
        result.add("M=M+1");
        return result;
    }
    public LinkedList<String> codeOr()
    {
        LinkedList<String> result = new LinkedList<>();
        //get first num
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=M");
        //pop
        result.addAll(ramCode.popStack());
        //get second num & or
        result.add("@SP");
        result.add("A=M-1");
        result.add("D=D|M");
        //pop
        result.addAll(ramCode.popStack());
        //pushResult
        result.add("@SP");
        result.add("A=M");
        result.add("M=D");
        result.add("@SP");
        result.add("M=M+1");
        return result;
    }
    public LinkedList<String> codeNot()
    {
        LinkedList<String> result = new LinkedList<>();
        //get num & not
        result.add("@SP");
        result.add("A=M-1");
        result.add("M=!M");
        return result;
    }
}
