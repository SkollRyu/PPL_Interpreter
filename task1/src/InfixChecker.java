// TODO

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfixChecker extends InfixBaseVisitor<Types>
{
    private final Map<String, Types> gloFunction = new HashMap<>();
    private final Map<String, Integer> functionNumArgs = new HashMap<>();
    private final Map<String, List<Types>> fucntionTypeArgs = new HashMap<>();
    private final Map<String, Types> localVars = new HashMap<>();
    @Override public Types visitProg(InfixParser.ProgContext ctx) {
        for (int i = 0; i < ctx.dec().size(); ++i) {
            if (gloFunction.containsKey(ctx.dec(i).Idfr().getText())) {
                throw new TypeException().duplicatedFuncError();
            }
            if (ctx.dec(i).Type().getText().equals("Unit")){
                gloFunction.put(ctx.dec(i).Idfr().getText(), Types.UNIT);
            } else if (ctx.dec(i).Type().getText().equals("Int")){
                gloFunction.put(ctx.dec(i).Idfr().getText(), Types.INT);
            } else if (ctx.dec(i).Type().getText().equals("Bool")){
                gloFunction.put(ctx.dec(i).Idfr().getText(), Types.BOOL);
            } else {
                throw new TypeException("Invalid type");
            }
        }

        gloFunction.forEach(
                (key, value)
                        -> System.out.println(key + " = " + value));



        if (!gloFunction.containsKey("main")){
            throw new TypeException().noMainFuncError();
        }

        if (!gloFunction.get("main").equals(Types.INT)){
            throw new TypeException().mainReturnTypeError();
        }


        // return type should be the type of main method`
        Types returnType = null; // need to visit all dec to check inside - todo
        for (int i = 0; i < ctx.dec().size(); i++) {
            if (ctx.dec(i).Idfr().getText().equals("main")){
                returnType = visit(ctx.dec(i).body());
            } else {
                visit(ctx.dec(i).body());
            }
        }
        if (returnType == Types.INT){
            return returnType;
        } else {
            throw new TypeException().mainReturnTypeError();
        }
    }
    @Override public Types visitDec(InfixParser.DecContext ctx) {
        // we define the variable name and type here argsssss
        // make use of local var
        List <Types> functionTypeList = new ArrayList<>();
        for (int i = 0; i < ctx.vardec().Idfr().size(); i++) {
            // variable checking and init
            if (localVars.containsKey(ctx.vardec().Idfr(i).getText())) {
                throw new TypeException().duplicatedVarError();
            }
            if (ctx.vardec().Type(i).getText().equals("Unit")){
                throw new TypeException().unitVarError();
            } else if (ctx.vardec().Type(i).getText().equals("Int")){
                localVars.put(ctx.vardec().Idfr(i).getText(), Types.INT);
                functionTypeList.add(Types.INT);
            } else if (ctx.vardec().Type(i).getText().equals("Bool")){
                localVars.put(ctx.vardec().Idfr(i).getText(), Types.BOOL);
                functionTypeList.add(Types.BOOL);
            } else {
                throw new TypeException("Invalid type");
            }
        }
        // <function name, type of args>
        fucntionTypeArgs.put(ctx.Idfr().getText(), functionTypeList);
        // <function name, number of args>
        functionNumArgs.put(ctx.Idfr().getText(), ctx.vardec().Idfr().size());
        return visit(ctx.body());
    }

    @Override public Types visitVardec(InfixParser.VardecContext ctx) {
        // should visit here?
        return null;
    }

    @Override public Types visitBody(InfixParser.BodyContext ctx) {
        // todo - check if assignment type is same as the expr
        for (int i = 0; i < ctx.Idfr().size(); i++) {
            // variable checking and init
            if (!localVars.containsKey(ctx.Idfr(i).getText())) {
                throw new TypeException().undefinedVarError();
            }
            if (ctx.Type(i).getText().equals("Unit")){
                // var cannot be unit type
                throw new TypeException().unitVarError();
            } else if (ctx.Type(i).getText().equals("Int")){
                localVars.put(ctx.Idfr(i).getText(), Types.INT);
            } else if (ctx.Type(i).getText().equals("Bool")){
                localVars.put(ctx.Idfr(i).getText(), Types.BOOL);
            } else {
                throw new TypeException("Invalid type");
            }
        }
        return null;
    }

    @Override public Types visitBlock(InfixParser.BlockContext ctx) {
        // the block expr should be at expr > block
        return visit(ctx.ene());
        // return Types.UNIT;
    }

    @Override public Types visitEne(InfixParser.EneContext ctx) {
        // todo - need to double check - return the type of last expr
        Types lastExprType = null;
        for (int i = 0; i < ctx.expr().size(); i++) {
            lastExprType = visit(ctx.expr(i));
            // visit(ctx.expr().get(i);
        }
        return lastExprType;
    }

    @Override public Types visitIdExpr(InfixParser.IdExprContext ctx) {
        if (!gloFunction.containsKey(ctx.Idfr().getText())) {
            throw new TypeException().undefinedVarError();
        }
        return gloFunction.get(ctx.getText()); // either bool / unit or int
    }

    @Override public Types visitIntExpr(InfixParser.IntExprContext ctx) {
        return Types.INT;
    }

    @Override public Types visitBoolExpr(InfixParser.BoolExprContext ctx) {
        return Types.BOOL;
    }


    @Override public Types visitAssignExpr(InfixParser.AssignExprContext ctx) {
        // IDFR need to be same type as EXPR
        if (!localVars.containsKey(ctx.Idfr().getText())) {
            throw new TypeException().undefinedVarError();
        }
        // like if we assign a bool to int -> throw exception
        if (visit(ctx.expr()) != localVars.get(ctx.Idfr().getText())) {
            throw new TypeException().assignmentError();
        }
        return null;
    }

    @Override public Types visitBinOpExpr(InfixParser.BinOpExprContext ctx) {
        String ctxOp = ctx.op.getText();

        switch (ctxOp) {
            case "+":
            case "-":
            case "*":
            case "/":
                // Numerical
                if (visit(ctx.expr(0)) == Types.INT && visit(ctx.expr(1)) == Types.INT){
                    return Types.INT;
                } else {
                    throw new TypeException().arithmeticError();
                }
                // shall we have break? TODO
            case "==":
            case "<":
            case ">":
            case "<=":
            case ">=":
                // Comparison
                if (visit(ctx.expr(0)) == Types.INT && visit(ctx.expr(1)) == Types.INT){
                    return Types.BOOL;
                } else {
                    throw new TypeException().comparisonError();
                }
            case "^":
            case "&":
            case "|":
                // Boolean Operations
                if (visit(ctx.expr(0)) == Types.BOOL && visit(ctx.expr(1)) == Types.BOOL){
                    return Types.BOOL;
                } else {
                    throw new TypeException().logicalError();
                }
            default:
                throw new RuntimeException("Shouldn't be here - wrong binary operator.");
        }
    }

    @Override public Types visitCallFunExpr(InfixParser.CallFunExprContext ctx) {
        if (!gloFunction.containsKey(ctx.Idfr().getText())){
            throw new TypeException().undefinedFuncError();
        }
        if (functionNumArgs.get(ctx.Idfr().getText()) != ctx.args().expr().size()){
            throw new TypeException().argumentNumberError();
        }

        for (int i = 0; i < ctx.args().expr().size(); i++) {
            // if each arg expr match with the functions arg types
            if (visit(ctx.args().expr(i)) != fucntionTypeArgs.get(ctx.Idfr().getText()).get(i)){
                throw new TypeException().argumentError();
            }
        }
        return visit(ctx.args());
    }

    @Override public Types visitBlockExpr(InfixParser.BlockExprContext ctx) {
        // todo - double check
        // todo - shall we return the last type of the expr in block?
        visit(ctx.block());
        return Types.UNIT;
    }

    @Override public Types visitIfExpr(InfixParser.IfExprContext ctx) {
        // todo
        if (visit(ctx.expr()) != Types.BOOL) {
            throw new TypeException().conditionError();
        }
        if (visit(ctx.block(0)) != visit(ctx.block(1))){
            throw new TypeException().branchMismatchError();
        }
        return Types.UNIT;
    }

    @Override public Types visitWhileExpr(InfixParser.WhileExprContext ctx) {
        // todo
        if (visit(ctx.expr()) != Types.BOOL) {
            throw new TypeException().conditionError();
        }
        if (visit(ctx.block()) != Types.UNIT){
            throw new TypeException().loopBodyError();
        }
        return Types.UNIT;
    }

    @Override public Types visitForExpr(InfixParser.ForExprContext ctx) {
        if (visit(ctx.expr()) == Types.BOOL & visit(ctx.block()) == Types.UNIT){
            return Types.UNIT;
        } else {
            throw new TypeException().loopBodyError();
        }
    }

    @Override public Types visitPrintExpr(InfixParser.PrintExprContext ctx){
        if (visit(ctx.expr()) == Types.INT || ctx.expr().getText().equals("space") ||
                ctx.expr().getText().equals("newline")) {
            return Types.UNIT;
        } else {
            throw new TypeException().printError();
        }
    }

    @Override public Types visitSpaceExpr(InfixParser.SpaceExprContext ctx){
        return Types.UNIT;
    }

    @Override public Types visitNewlineExpr(InfixParser.NewlineExprContext ctx){
        return Types.UNIT;
    }

    @Override public Types visitSkipExpr(InfixParser.SkipExprContext ctx){
        return Types.UNIT;
    }

    @Override public Types visitArgs(InfixParser.ArgsContext ctx) {
        return visitChildren(ctx);
    }
}
