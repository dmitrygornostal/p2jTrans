package src.main.java;

import java.io.*;

import org.antlr.runtime.*;
import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

public class Main {
    public static StringTemplateGroup templates;

    public static void main(String[] args) throws Exception {
        String templateFileName;
        String inputFile;

        if (args.length == 0) {
            templateFileName = "src/main/resources/Java.stg";
            inputFile = "src/main/resources/input";
        } else if (args.length == 1) {
            templateFileName = "src/main/resources/Java.stg";
            inputFile = args[0];
        } else {
            templateFileName = args[0];
            inputFile = args[1];
        }

        templates = new StringTemplateGroup(new FileReader(templateFileName),
                AngleBracketTemplateLexer.class);

        CharStream input = new ANTLRFileStream(inputFile);
        src.main.java.PLSQL3Lexer lexer = new PLSQL3Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PLSQL3Parser parser = new PLSQL3Parser(tokens);
        parser.setTemplateLib(templates);
        RuleReturnScope r = parser.program();
        System.out.println(r.getTemplate().toString());
    }
}
