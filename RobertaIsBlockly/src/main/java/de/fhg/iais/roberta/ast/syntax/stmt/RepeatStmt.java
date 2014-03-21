package de.fhg.iais.roberta.ast.syntax.stmt;

import de.fhg.iais.roberta.ast.syntax.expr.Expr;
import de.fhg.iais.roberta.dbc.Assert;

public class RepeatStmt extends Stmt {
    private final Expr expr;
    private final StmtList list;

    private RepeatStmt(Expr expr, StmtList list) {
        Assert.isTrue(expr.isReadOnly() && list.isReadOnly());
        this.expr = expr;
        this.list = list;
        setReadOnly();
    }

    public static RepeatStmt make(Expr expr, StmtList list) {
        return new RepeatStmt(expr, list);
    }

    public final Expr getExpr() {
        return this.expr;
    }

    public final StmtList getlist() {
        return this.list;
    }

    @Override
    public Kind getKind() {
        return Kind.Repeat;
    }

    @Override
    public void toStringBuilder(StringBuilder sb, int indentation) {
        int next = indentation + 3;
        appendNewLine(sb, indentation, null);
        sb.append("(repeat ").append(this.expr);
        this.list.toStringBuilder(sb, next);
        appendNewLine(sb, indentation, ")");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringBuilder(sb, 0);
        return sb.toString();
    }

}
