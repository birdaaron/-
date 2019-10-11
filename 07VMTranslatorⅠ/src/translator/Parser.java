package translator;

import java.io.*;
import java.util.LinkedList;

public class Parser
{
    private LinkedList<String> content = new LinkedList<>();
    private String currentLine;
    private File vmFile;
    public static final int NO_COMMAND = -1,C_ARITHMETIC = 0,C_PUSH = 1,C_POP = 2,C_LABEL = 3,C_GOTO = 4,
                      C_IF = 5,C_FUNCTION = 6,C_RETURN = 7,C_CALL = 8;
    public Parser(File vmFile)
    {
        this.vmFile = vmFile;
        try
        {
            InputStream is = new FileInputStream(vmFile.getPath());
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine())!=null)
                if(!line.equals(""))
                    content.add(line);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public boolean hasMoreCommands()
    {
        return (content.size()!=0);
    }
    public void advance()
    {
        if(hasMoreCommands())
            currentLine = filterLine(content.poll());
        else
            currentLine = null;
    }
    public int commandType()
    {
        if(currentLine.equals(""))
            return NO_COMMAND;
        switch(currentLine.substring(0,2)) //改
        {
            case "pu":
                return C_PUSH;
            case "po":
                return C_POP;
            case "la":
                return C_LABEL;
            case "go":
                return C_GOTO;
            case "if":
                return C_IF;
            case "fu":
                return C_FUNCTION;
            case "ca":
                return C_CALL;
            case "re":
                return C_RETURN;
            default:
                return C_ARITHMETIC;
        }

    }
    public String commandStr()
    {
        switch(commandType())
        {
            case C_PUSH:
                return "push";
            case C_POP:
                return "pop";
            case C_LABEL:
                return "label";
            case C_CALL:
                return "call";
            case C_FUNCTION:
                return "function";
            case C_IF:
                return "if-goto";
            case C_GOTO:
                return "goto";
            default:
                return currentLine;
        }
    }
    public String arg1()
    {
        String arg2 = filterLine(currentLine.substring(currentLine.indexOf(" ")));
        int position_space = arg2.indexOf(" ");
        arg2 = arg2.substring(0,position_space);
        return arg2;
    }
    public String arg2()
    {
        String arg3 = currentLine.replace(commandStr(),"");
        arg3 = filterLine(arg3.replace(arg1(),""));
        return arg3;
    }
    private String filterLine(String line)
    {
        int position_space = 1;
        int position_note = -1;
        if(line.length()==0)
            System.out.println("lalal");
        //过滤空格
        while(line.substring(0,1).equals(" "))
            line = line.substring(position_space);
        //过滤注释
        position_note = line.indexOf("//");
        if(position_note!=-1)
            line = line.substring(0,position_note);
        return line;
    }
    public LinkedList<String> generateCode()
    {
        LinkedList<String> code = new LinkedList<>();
        CodeWriter codeWriter = new CodeWriter(vmFile.getName().replace(".vm",""));
        while(hasMoreCommands())
        {
            advance();
            switch (commandType())
            {
                case C_PUSH:
                    code.addAll(codeWriter.writerPushPop(C_PUSH, arg1(), arg2()));
                    break;
                case C_POP:
                    code.addAll(codeWriter.writerPushPop(C_POP, arg1(), arg2()));
                    break;
                case C_ARITHMETIC:
                    code.addAll(codeWriter.writeArithmetic(currentLine));
                    break;
            }
        }
        return code;
    }

    public boolean createAsmFile()
    {
        try
        {
            String filePath = vmFile.getPath().replace(vmFile.getName(),"");
            String fileName = vmFile.getName().replace("vm","asm");
            File asmFile = new File(filePath+fileName);

            if(!asmFile.exists())
            {
                asmFile.createNewFile();
                writeAsmContent(asmFile,generateCode());
            }
            else
                return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }
    private void writeAsmContent(File file,LinkedList<String> content)
    {
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try
        {
            StringBuffer sb = new StringBuffer();
            for(String code : content)
                sb.append(code+"\r\n");
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(sb.toString().toCharArray());
            pw.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
