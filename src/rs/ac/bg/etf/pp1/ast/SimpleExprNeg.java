// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:30:30


package rs.ac.bg.etf.pp1.ast;

public class SimpleExprNeg extends SimpleExpr {

    private ExprRest ExprRest;

    public SimpleExprNeg (ExprRest ExprRest) {
        this.ExprRest=ExprRest;
        if(ExprRest!=null) ExprRest.setParent(this);
    }

    public ExprRest getExprRest() {
        return ExprRest;
    }

    public void setExprRest(ExprRest ExprRest) {
        this.ExprRest=ExprRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprRest!=null) ExprRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprRest!=null) ExprRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprRest!=null) ExprRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SimpleExprNeg(\n");

        if(ExprRest!=null)
            buffer.append(ExprRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleExprNeg]");
        return buffer.toString();
    }
}
