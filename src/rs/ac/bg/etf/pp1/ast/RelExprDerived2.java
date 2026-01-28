// generated with ast extension for cup
// version 0.8
// 28/0/2026 19:50:52


package src/rs/ac.bg.etf.pp1.ast;

public class RelExprDerived2 extends RelExpr {

    private SimpleExpr SimpleExpr;

    public RelExprDerived2 (SimpleExpr SimpleExpr) {
        this.SimpleExpr=SimpleExpr;
        if(SimpleExpr!=null) SimpleExpr.setParent(this);
    }

    public SimpleExpr getSimpleExpr() {
        return SimpleExpr;
    }

    public void setSimpleExpr(SimpleExpr SimpleExpr) {
        this.SimpleExpr=SimpleExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SimpleExpr!=null) SimpleExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SimpleExpr!=null) SimpleExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SimpleExpr!=null) SimpleExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RelExprDerived2(\n");

        if(SimpleExpr!=null)
            buffer.append(SimpleExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RelExprDerived2]");
        return buffer.toString();
    }
}
