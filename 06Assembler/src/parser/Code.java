package parser;

import java.util.HashMap;
import java.util.Map;

public class Code
{

    private Map<String,String> compMap = new HashMap<String, String>()
    {
        {//?
            put("0",  "101010");
            put("1",  "111111");
            put("-1", "111010");
            put("D",  "001100");
            put("A",  "110000");
            put("!D", "001101");
            put("!A", "110001");
            put("-D", "001111");
            put("-A", "110011");
            put("D+1","011111");
            put("A+1","110111");
            put("D-1","001110");
            put("A-1","110010");
            put("D+A","000010");
            put("D-A","010011");
            put("A-D","000111");
            put("D&A","000000");
            put("D|A","010101");
        }
    };
    private Map<String,String> destMap = new HashMap<String, String>()
    {
        {
            put("null","000");
            put("M","001");
            put("D","010");
            put("MD","011");
            put("A","100");
            put("AM","101");
            put("AD","110");
            put("AMD","111");
        }
    };
    private Map<String,String> jumpMap = new HashMap<String, String>()
    {
        {
            put("null","000");
            put("JGT","001");
            put("JEQ","010");
            put("JGE","011");
            put("JLT","100");
            put("JNE","101");
            put("JLE","110");
            put("JMP","111");
        }
    };
    public String dest(String symbol)
    {
        return destMap.get(symbol);
    }
    public String comp(String symbol)
    {
        String result;
        if(symbol.indexOf('M')!=-1)
            result = "1111"+compMap.get(symbol.replace('M','A'));
        else
            result = "1110"+compMap.get(symbol);
        return result;
    }
    public String jump(String symbol)
    {
        return jumpMap.get(symbol);
    }

}
