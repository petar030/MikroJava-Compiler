// generated with ast extension for cup
// version 0.8
// 17/1/2026 19:20:6


package rs.ac.bg.etf.pp1.ast;

public class ExprTernary extends Expr {

    private Condition Condition;
    private QMarkM QMarkM;
    private Expr Expr;
    private ColonM ColonM;
    private Expr Expr1;

    public ExprTernary (Condition Condition, QMarkM QMarkM, Expr Expr, ColonM ColonM, Expr Expr1) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.QMarkM=QMarkM;
        if(QMarkM!=null) QMarkM.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.ColonM=ColonM;
        if(ColonM!=null) ColonM.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public QMarkM getQMarkM() {
        return QMarkM;
    }

    public void setQMarkM(QMarkM QMarkM) {
        this.QMarkM=QMarkM;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public ColonM getColonM() {
        return ColonM;
    }

    public void setColonM(ColonM ColonM) {
        this.ColonM=ColonM;
    }

    public Expr getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr Expr1) {
        this.Expr1=Expr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(QMarkM!=null) QMarkM.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(ColonM!=null) ColonM.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(QMarkM!=null) QMarkM.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(ColonM!=null) ColonM.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(QMarkM!=null) QMarkM.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(ColonM!=null) ColonM.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprTernary(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(QMarkM!=null)
            buffer.append(QMarkM.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ColonM!=null)
            buffer.append(ColonM.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprTernary]");
        return buffer.toString();
    }
}
