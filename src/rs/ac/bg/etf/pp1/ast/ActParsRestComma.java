// generated with ast extension for cup
// version 0.8
// 29/0/2026 2:53:34


package src/rs/ac.bg.etf.pp1.ast;

public class ActParsRestComma extends ActParsRest {

    private Expr Expr;
    private ActParsRest ActParsRest;

    public ActParsRestComma (Expr Expr, ActParsRest ActParsRest) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.ActParsRest=ActParsRest;
        if(ActParsRest!=null) ActParsRest.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public ActParsRest getActParsRest() {
        return ActParsRest;
    }

    public void setActParsRest(ActParsRest ActParsRest) {
        this.ActParsRest=ActParsRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(ActParsRest!=null) ActParsRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(ActParsRest!=null) ActParsRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(ActParsRest!=null) ActParsRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActParsRestComma(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsRest!=null)
            buffer.append(ActParsRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActParsRestComma]");
        return buffer.toString();
    }
}
