import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private final Map<String, Types> localVars = new HashMap<>();
    public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromStream(System.in);

        // create a lexer that feeds off of input CharStream
        InfixLexer lexer = new InfixLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        InfixParser parser = new InfixParser(tokens);
        ParseTree tree = parser.expr(); // begin parsing at expr rule

        InfixChecker checker = new InfixChecker();
        try {
            checker.visit(tree);
        } catch (TypeException ex) {
            System.out.println(ex.report());
            return;
        }
        System.out.println("NORMAL_TERMINATION");
    }
}
