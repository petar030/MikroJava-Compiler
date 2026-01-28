// generated with ast extension for cup
// version 0.8
// 28/0/2026 18:34:1


package src/rs/ac.bg.etf.pp1.ast;

public class ExprTer extends Expr {

    private TernaryOperator TernaryOperator;

    public ExprTer (TernaryOperator TernaryOperator) {
        this.TernaryOperator=TernaryOperator;
        if(TernaryOperator!=null) TernaryOperator.setParent(this);
    }

    public TernaryOperator getTernaryOperator() {
        return TernaryOperator;
    }

    public void setTernaryOperator(TernaryOperator TernaryOperator) {
        this.TernaryOperator=TernaryOperator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TernaryOperator!=null) TernaryOperator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TernaryOperator!=null) TernaryOperator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TernaryOperator!=null) TernaryOperator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprTer(\n");

        if(TernaryOperator!=null)
            buffer.append(TernaryOperator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprTer]");
        return buffer.toString();
    }
}
