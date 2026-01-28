// generated with ast extension for cup
// version 0.8
// 28/0/2026 19:50:52


package src/rs/ac.bg.etf.pp1.ast;

public class CondFactDerived1 extends CondFact {

    private RelExpr RelExpr;

    public CondFactDerived1 (RelExpr RelExpr) {
        this.RelExpr=RelExpr;
        if(RelExpr!=null) RelExpr.setParent(this);
    }

    public RelExpr getRelExpr() {
        return RelExpr;
    }

    public void setRelExpr(RelExpr RelExpr) {
        this.RelExpr=RelExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RelExpr!=null) RelExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RelExpr!=null) RelExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RelExpr!=null) RelExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactDerived1(\n");

        if(RelExpr!=null)
            buffer.append(RelExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactDerived1]");
        return buffer.toString();
    }
}
