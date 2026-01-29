// generated with ast extension for cup
// version 0.8
// 29/0/2026 16:34:35


package rs.ac.bg.etf.pp1.ast;

public class ExprRestDerived2 extends ExprRest {

    public ExprRestDerived2 () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprRestDerived2(\n");

        buffer.append(tab);
        buffer.append(") [ExprRestDerived2]");
        return buffer.toString();
    }
}
