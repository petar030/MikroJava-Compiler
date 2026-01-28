// generated with ast extension for cup
// version 0.8
// 28/0/2026 19:50:52


package src/rs/ac.bg.etf.pp1.ast;

public class DesArr extends DesignatorRest {

    private Expr Expr;
    private DesignatorRest DesignatorRest;

    public DesArr (Expr Expr, DesignatorRest DesignatorRest) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.DesignatorRest=DesignatorRest;
        if(DesignatorRest!=null) DesignatorRest.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public DesignatorRest getDesignatorRest() {
        return DesignatorRest;
    }

    public void setDesignatorRest(DesignatorRest DesignatorRest) {
        this.DesignatorRest=DesignatorRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(DesignatorRest!=null) DesignatorRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(DesignatorRest!=null) DesignatorRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(DesignatorRest!=null) DesignatorRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesArr(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorRest!=null)
            buffer.append(DesignatorRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesArr]");
        return buffer.toString();
    }
}
