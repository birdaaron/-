package translator.codeMod;

import java.util.LinkedList;

public class RamMod
{
    public LinkedList<String> codePop(String segment,String index)
    {
        LinkedList<String> result = null;
        String address = getSegmentAddress(segment);
        if(segment.equals("static"))
            result = popWithStatic(address,index);
        else
            result = popWithoutStatic(address,index);
        return result;
    }
    private LinkedList<String> popWithStatic(String address,String index)
    {return null ;}
    private LinkedList<String> popWithoutStatic(String address,String index)
    {
        LinkedList<String> result = new LinkedList<>();
        //get the location
        result.add(address);
        if(address.equals("5"))//temp
            result.add("D=A");
        else
            result.add("D=M");//not temp
        result.add("@"+index);
        result.add("D=D+A");
        result.add("@location");
        result.add("M=D");
        //get the value
        result.addAll(popStack());
        result.add("A=M");
        result.add("D=M");
        //save the value
        result.add("@location");
        result.add("A=M");
        result.add("M=D");
        return result;
    }
    protected LinkedList<String> popStack()
    {
        LinkedList<String> result = new LinkedList<>();
        result.add("@SP");
        result.add("M=M-1");
        return result;
    }

    public LinkedList<String> codePush(String segment,String index)
    {
        LinkedList<String> result ;
        String address = getSegmentAddress(segment);
        if(segment.equals("static"))
            result = pushWithStatic(segment,index,address);
        else
            result = pushWithoutStatic(segment,index,address);
        return result;
    }


    private String getSegmentAddress(String segment)
    {
        switch (segment)
        {
            case "local":
                return "@LCL";
            case "argument":
                return "@ARG";
            case "this":
                return "@THIS";
            case "that":
                return "@THAT";
            case "pointer":
                return "3";
            case "temp":
                return "5";
        }
        return "ERROR_ADDRESS";
    }
    private LinkedList<String> pushWithStatic(String index,String segment,String address)
    {return  null;}
    private LinkedList<String> pushWithoutStatic(String index,String segment,String address)
    {
        LinkedList<String> result = new LinkedList<>();
        //get the value
        result.add(address);
        if(address.equals("5"))    //temp
            result.add("D=A");
        else
            result.add("D=M");    //not temp
        result.add("@"+index);
        result.add("A=D+A");
        result.add("D=M");
        //get the location
        result.addAll(pushStack());
        result.add("A=M-1");
        //save the value
        result.add("M=D");
        return result;
    }
    protected LinkedList<String> pushStack()
    {
        LinkedList<String> result = new LinkedList<>();
        result.add("@SP");
        result.add("M=M+1");
        return result;
    }
}
