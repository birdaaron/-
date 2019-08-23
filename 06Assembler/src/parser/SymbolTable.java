package parser;

import java.util.HashMap;
import java.util.LinkedList;

public class SymbolTable
{
    private int variableRamAddress = 16;
    private HashMap<String, String> symbolMap = new HashMap<>()
    {
        {
            put("SP","0000000000000000");
            put("LCL","0000000000000001");
            put("ARG","0000000000000010");
            put("THIS","0000000000000011");
            put("THAT","0000000000000100");
            put("SCREEN","1000000000000000");
            put("KBD","1100000000000000");
            put("R0","0000000000000000");
            put("R1","0000000000000001");
            put("R2","0000000000000010");
            put("R3","0000000000000011");
            put("R4","0000000000000100");
            put("R5","0000000000000101");
            put("R6","0000000000000110");
            put("R7","0000000000000111");
            put("R8","0000000000001000");
            put("R9","0000000000001001");
            put("R10","0000000000001010");
            put("R11","0000000000001011");
            put("R12","0000000000001100");
            put("R13","0000000000001101");
            put("R14","0000000000001110");
            put("R15","0000000000001111");
        }
    };
    //在符号表中添加一对键值
    public void addEntry(String symbol,String address) { symbolMap.put(symbol,address);}
    public boolean contatins(String symbol){return symbolMap.containsKey(symbol);}
    public String getAddress(String symbol) {return symbolMap.get(symbol);}
    //获得数字型A指令的二进制地址
    public String getDigitRamAddress(String symbol)
    {
        return toBinaryString(Integer.parseInt(symbol));
    }
    //将int数字转为16位二进制string
    public String toBinaryString(int address)
    {
        String result = Integer.toBinaryString(address);
        while(result.length()!=16)
            result = '0'+result;
        return result;
    }
    //添加变量符号
    public void addVariable(String symbol)
    {
        String address = toBinaryString(variableRamAddress);
        addEntry(symbol,address);
        variableRamAddress++;
    }
}
