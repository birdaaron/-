package test;

public class test
{
    public static void main(String args[])
    {
        String a = "  //aaa";
        System.out.println(filterLine(a));

    }
    private static String filterLine(String line)
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

}
