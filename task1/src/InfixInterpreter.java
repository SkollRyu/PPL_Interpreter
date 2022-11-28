import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;


public class InfixInterpreter extends AbstractParseTreeVisitor<Integer> implements InfixVisitor<Integer> {

    // use Object, so we can store bool and int
    private final Map<String, Map<String, Object>> functionVars = new HashMap<>();

    // pass in command line arguments
    public Integer VisitProgram(InfixParser.ProgContext ctx, String[] args){

        // pass the args into main args
        Map<String, Object> localVars = new HashMap<>();

        for (int i = 0; i < ctx.dec().size(); i++) {
            if (ctx.dec(i).Idfr().getText().equals("main")){
                for (int j = 0; j < args.length; j++) {
                    if (args[j].equals("true")) {
                        localVars.put(ctx.dec(i).vardec().Idfr(j).getText(), "true");
                    } else if (args[j].equals("false")) {
                        localVars.put(ctx.dec(i).vardec().Idfr(j).getText(), "false");
                    } else {
                        localVars.put(ctx.dec(i).vardec().Idfr(j).getText(), Integer.parseInt(args[j]));
                    }
                }
            }
        }

        functionVars.put("main", localVars);
        return null;
    }

    @Override
    public Integer visitProg(InfixParser.ProgContext ctx) {
        return null;
    }

    @Override
    public Integer visitDec(InfixParser.DecContext ctx) {
        return null;
    }

    @Override
    public Integer visitVardec(InfixParser.VardecContext ctx) {
        return null;
    }

    @Override
    public Integer visitBody(InfixParser.BodyContext ctx) {
        return null;
    }

    @Override
    public Integer visitBlock(InfixParser.BlockContext ctx) {
        return null;
    }

    @Override
    public Integer visitEne(InfixParser.EneContext ctx) {
        return null;
    }

    @Override
    public Integer visitBoolExpr(InfixParser.BoolExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitIdExpr(InfixParser.IdExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitIntExpr(InfixParser.IntExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitAssignExpr(InfixParser.AssignExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitBinOpExpr(InfixParser.BinOpExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitCallFunExpr(InfixParser.CallFunExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitBlockExpr(InfixParser.BlockExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitIfExpr(InfixParser.IfExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitWhileExpr(InfixParser.WhileExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitForExpr(InfixParser.ForExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitPrintExpr(InfixParser.PrintExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitSpaceExpr(InfixParser.SpaceExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitNewlineExpr(InfixParser.NewlineExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitSkipExpr(InfixParser.SkipExprContext ctx) {
        return null;
    }

    @Override
    public Integer visitArgs(InfixParser.ArgsContext ctx) {
        return null;
    }
}
