package test;

import parser.MyParser;

public class test
{
    public static void main(String args[]) throws Exception
    {
        MyParser myParser =
                new MyParser("D:\\计算机系统要素\\projects\\06\\add\\Add.asm");
        while(true)
         myParser.getBinary();
    }
}
