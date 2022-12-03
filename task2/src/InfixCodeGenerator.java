// write risc-v code for the given expression in Infix.g4


import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class InfixCodeGenerator extends AbstractParseTreeVisitor<String> implements InfixVisitor<String>{

    private int numOfArgs;
    private List<TerminalNode> args;
    private final Map<String, Integer> localVars = new HashMap<>();

    private int labelCounter = 0;
    private final Map<String, InfixParser.DecContext> decContextMap= new HashMap<>();

    @Override
    public String visitProg(InfixParser.ProgContext ctx) {
        InfixParser.DecContext mainDec = null;
        for (int i = 0; i < ctx.dec().size(); i++) {
            decContextMap.put(ctx.dec(i).Idfr().getText(), ctx.dec(i));
            if (ctx.dec(i).Idfr().getText().equals("main")){
                numOfArgs = ctx.dec(i).vardec().Idfr().size();
                args = ctx.dec(i).vardec().Idfr();
                mainDec = ctx.dec(i);
            }
        }
        assert mainDec != null;
        return visit(mainDec.body());
    }

    @Override
    public String visitDec(InfixParser.DecContext ctx) {
        return null;
    }

    @Override
    public String visitVardec(InfixParser.VardecContext ctx) {
        return null;
    }

    @Override
    public String visitBody(InfixParser.BodyContext ctx) {
        if (numOfArgs + ctx.Idfr().size() > 22) {     // x10 - x31
            throw new RuntimeException("Too many local variables.");
        }

        StringBuilder sb = new StringBuilder();

        sb.append("""
                main:
                    lw          ra, 4(sp)
                    addi        sp, sp, 4
                """
        );

        int regOffset = 10;

        for (int i = 0; i < numOfArgs; ++i) {
            localVars.put(args.get(i).getText(), i + regOffset);
            sb.append(
                    String.format("""
                    lw          x%2d, 4(sp)
                    addi        sp, sp, 4
                """,
                            i + regOffset
                    )
            );
        }

        regOffset = regOffset + numOfArgs;

        sb.append("""
                    addi        sp, sp, 4
                """
        );

        for (int i = 0; i < ctx.Idfr().size(); ++i) {
            localVars.put(ctx.Idfr().get(i).getText(), i + regOffset);
            sb.append(visit(ctx.expr().get(i)));
            sb.append(
                    String.format("""
                    lw          x%2d, 4(sp)
                    addi        sp, sp, 4
                """,
                            i + regOffset
                    )
            );
        }

        for (int i = 0; i < ctx.ene().expr().size(); ++i) {
            sb.append(visit(ctx.ene().expr().get(i)));
            if (i != ctx.ene().expr().size() - 1) {
                if (
                        ctx.ene().expr().get(i) instanceof InfixParser.BinOpExprContext
                                || ctx.ene().expr().get(i) instanceof InfixParser.IntExprContext
                                || ctx.ene().expr().get(i) instanceof InfixParser.IdExprContext
                ) {

                    sb.append("""
                                Discard     4
                            """
                    );

                }
            }
        }

        sb.append("""
                    ret
                """
        );

        return sb.toString();
    }

    @Override
    public String visitBlock(InfixParser.BlockContext ctx) {
        return visit(ctx.ene());
    }

    @Override
    public String visitEne(InfixParser.EneContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.expr().size(); ++i) {
            sb.append(visit(ctx.expr().get(i)));

            if (
                    ctx.expr().get(i) instanceof InfixParser.BinOpExprContext
                            || ctx.expr().get(i) instanceof InfixParser.IntExprContext
                            || ctx.expr().get(i) instanceof InfixParser.IdExprContext
            ) {

                sb.append("""
                            Discard     4
                        """
                );

            }
        }
        return sb.toString();
    }

    @Override
    public String visitBoolExpr(InfixParser.BoolExprContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.BoolLit().getText().equals("true")){
            sb.append(
                    String.format("""
                    PushImm     %d
                """,
                        1
                            )
            );
        } else {
            sb.append(
                    String.format("""
                    PushImm     %d
                """,
                        0
                            )
            );
        }
        return sb.toString();
    }

    @Override
    public String visitIdExpr(InfixParser.IdExprContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                String.format("""
                    PushReg     x%2d
                """,
                        localVars.get(ctx.Idfr().getText())
                )
        );
        return sb.toString();
    }

    @Override
    public String visitIntExpr(InfixParser.IntExprContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                String.format("""
                    PushImm     %d
                """,
                        Integer.parseInt(ctx.IntLit().getText())
                )
        );
        return sb.toString();
    }

    @Override
    public String visitAssignExpr(InfixParser.AssignExprContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expr()));
        sb.append(
                String.format("""
                    PopReg      x%2d
                """,
                        localVars.get(ctx.Idfr().getText())
                )
        );
        return sb.toString();
    }

    @Override
    public String visitBinOpExpr(InfixParser.BinOpExprContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expr(1)));
        sb.append(visit(ctx.expr(0)));

        String ctxOp = ctx.op.getText();

        switch (ctxOp) {
            case "+":
                sb.append("""
                    Plus
                """
                );
            case "-":
                sb.append("""
                    Minus
                """
                );
            case "*":
                sb.append("""
                    Times
                """
                );
            case "/":
                sb.append("""
                    Divide
                """
                );
            case "==":
                sb.append("""
                    CompEQ
                """
                );
            case "<":
                sb.append("""
                    CompLT
                """
                );
            case ">":
                sb.append("""
                    CompGT
                """
                );
            case "<=":
                sb.append("""
                    CompLE
                """
                );
            case ">=":
                sb.append("""
                    CompGE
                """
                );
            case "^":
                sb.append("""
                    LogicalXor
                """
                );
            case "&":
                sb.append("""
                    LogicalAnd
                """
                );
            case "|":
                sb.append("""
                    LogicalOr
                """
                );
            default:
                throw new RuntimeException("Shouldn't be here - wrong binary operator.");
        }
    }

    @Override
    public String visitCallFunExpr(InfixParser.CallFunExprContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                String.format("""
                    jal         %s
                """,
                        ctx.Idfr().getText()
                )
        );

        sb.append(visit(decContextMap.get(ctx.Idfr().getText()).body()));
        return sb.toString();

    }

    @Override
    public String visitBlockExpr(InfixParser.BlockExprContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public String visitIfExpr(InfixParser.IfExprContext ctx) {
        StringBuilder sb = new StringBuilder();
        String thenLabel = String.format("label_%d", labelCounter++);
        String elseLabel = String.format("label_%d", labelCounter++);
        String exitLabel = String.format("label_%d", labelCounter++);

        sb.append(
                String.format("""
                %s:
                """,
                        elseLabel)
        );

        sb.append(visit(ctx.expr()));

        sb.append(
                String.format("""
                    PushImm     1
                    LogicalXor
                    JumpTrue    %s
                """,
                        thenLabel)
        );

        sb.append(visit(ctx.block(0)));

        sb.append(
                String.format("""
                    Jump        %s
                """,
                        elseLabel)
        );

        sb.append(
                String.format("""
                    %s:
                    """,
                        exitLabel)
        );


        return sb.toString();
    }

    @Override
    public String visitWhileExpr(InfixParser.WhileExprContext ctx) {
        StringBuilder sb = new StringBuilder();
        String loopLabel = String.format("label_%d", labelCounter++);
        String exitLabel = String.format("label_%d", labelCounter++);

        sb.append(
                String.format("""
                %s:
                """,
                        loopLabel)
        );

        sb.append(visit(ctx.expr()));

        sb.append(
                String.format("""
                    PushImm     1
                    LogicalXor
                    JumpTrue    %s
                """,
                        exitLabel)
        );

        sb.append(visit(ctx.block()));

        sb.append(
                String.format("""
                    Jump        %s
                """,
                        loopLabel)
        );

        sb.append(
                String.format("""
                    %s:
                    """,
                        exitLabel)
        );

        return sb.toString();
    }

    @Override
    public String visitForExpr(InfixParser.ForExprContext ctx) {
        StringBuilder sb = new StringBuilder();

        String loopLabel = String.format("label_%d", labelCounter++);
        String exitLabel = String.format("label_%d", labelCounter++);

        sb.append(visit(ctx.block()));

        sb.append(
                String.format("""
                    Jump        %s
                """,
                        loopLabel)
        );

        sb.append(
                String.format("""
                %s:
                """,
                        loopLabel)
        );

        sb.append(visit(ctx.expr()));

        sb.append(
                String.format("""
                    PushImm     1
                    LogicalXor
                    JumpTrue    %s
                """,
                        exitLabel)
        );



        sb.append(
                String.format("""
                    %s:
                    """,
                        exitLabel)
        );

        return sb.toString();
    }

    @Override
    public String visitPrintExpr(InfixParser.PrintExprContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expr()));
        sb.append("""
            Print
        """
        );
        return sb.toString();
    }

    @Override
    public String visitSpaceExpr(InfixParser.SpaceExprContext ctx) {
        return null;
    }

    @Override
    public String visitNewlineExpr(InfixParser.NewlineExprContext ctx) {
        return null;
    }

    @Override
    public String visitSkipExpr(InfixParser.SkipExprContext ctx) {
        return null;
    }

    @Override
    public String visitArgs(InfixParser.ArgsContext ctx) {
        return null;
    }
}
