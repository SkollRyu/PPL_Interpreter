// Generated from /home/skollryu/OneDrive/Uni/Year 2/Semester 1/Compilers/Assignment/src/Infix.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link InfixParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface InfixVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link InfixParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(InfixParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link InfixParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDec(InfixParser.DecContext ctx);
	/**
	 * Visit a parse tree produced by {@link InfixParser#vardec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardec(InfixParser.VardecContext ctx);
	/**
	 * Visit a parse tree produced by {@link InfixParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(InfixParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link InfixParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(InfixParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link InfixParser#ene}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEne(InfixParser.EneContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(InfixParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(InfixParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(InfixParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(InfixParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinOpExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOpExpr(InfixParser.BinOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallFunExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallFunExpr(InfixParser.CallFunExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlockExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockExpr(InfixParser.BlockExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpr(InfixParser.IfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileExpr(InfixParser.WhileExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForExpr(InfixParser.ForExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(InfixParser.PrintExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SpaceExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceExpr(InfixParser.SpaceExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewlineExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewlineExpr(InfixParser.NewlineExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SkipExpr}
	 * labeled alternative in {@link InfixParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkipExpr(InfixParser.SkipExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link InfixParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(InfixParser.ArgsContext ctx);
}