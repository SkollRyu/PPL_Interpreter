// Generated from /home/skollryu/OneDrive/Uni/Year 2/Semester 1/Compilers/Assignment/src/Infix.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InfixParser}.
 */
public interface InfixListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InfixParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(InfixParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link InfixParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(InfixParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link InfixParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDec(InfixParser.DecContext ctx);
	/**
	 * Exit a parse tree produced by {@link InfixParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDec(InfixParser.DecContext ctx);
	/**
	 * Enter a parse tree produced by {@link InfixParser#vardec}.
	 * @param ctx the parse tree
	 */
	void enterVardec(InfixParser.VardecContext ctx);
	/**
	 * Exit a parse tree produced by {@link InfixParser#vardec}.
	 * @param ctx the parse tree
	 */
	void exitVardec(InfixParser.VardecContext ctx);
	/**
	 * Enter a parse tree produced by {@link InfixParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(InfixParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link InfixParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(InfixParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link InfixParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(InfixParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link InfixParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(InfixParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link InfixParser#ene}.
	 * @param ctx the parse tree
	 */
	void enterEne(InfixParser.EneContext ctx);
	/**
	 * Exit a parse tree produced by {@link InfixParser#ene}.
	 * @param ctx the parse tree
	 */
	void exitEne(InfixParser.EneContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(InfixParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(InfixParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(InfixParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(InfixParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(InfixParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(InfixParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(InfixParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(InfixParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinOpExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinOpExpr(InfixParser.BinOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinOpExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinOpExpr(InfixParser.BinOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallFunExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCallFunExpr(InfixParser.CallFunExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallFunExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCallFunExpr(InfixParser.CallFunExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBlockExpr(InfixParser.BlockExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBlockExpr(InfixParser.BlockExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIfExpr(InfixParser.IfExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIfExpr(InfixParser.IfExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWhileExpr(InfixParser.WhileExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWhileExpr(InfixParser.WhileExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterForExpr(InfixParser.ForExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitForExpr(InfixParser.ForExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(InfixParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(InfixParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SpaceExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSpaceExpr(InfixParser.SpaceExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SpaceExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSpaceExpr(InfixParser.SpaceExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewlineExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNewlineExpr(InfixParser.NewlineExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewlineExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNewlineExpr(InfixParser.NewlineExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SkipExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSkipExpr(InfixParser.SkipExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SkipExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSkipExpr(InfixParser.SkipExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link InfixParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(InfixParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link InfixParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(InfixParser.ArgsContext ctx);
}