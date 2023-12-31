import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class Task1 {


    public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromStream(System.in);

        // create a lexer that feeds off of input CharStream
        InfixLexer lexer = new InfixLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        InfixParser parser = new InfixParser(tokens);
        InfixParser.ProgContext tree = parser.prog(); // begin parsing at expr rule

        InfixChecker checker = new InfixChecker();
        try {
            checker.visit(tree);
        } catch (TypeException ex) {
            System.out.println(ex.report());
            return;
        }

        InfixInterpreter interpreter = new InfixInterpreter();
        Integer mainReturnvalue = interpreter.visitProgram(tree, args);
        System.out.println();
        System.out.println("NORMAL_TERMINATION");
        System.out.println(mainReturnvalue);
    }
}
