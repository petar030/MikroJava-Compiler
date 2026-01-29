// generated with ast extension for cup
// version 0.8
// 29/0/2026 17:49:50


package rs.ac.bg.etf.pp1.ast;

public class DesRest_e extends DesignatorRest {

    public DesRest_e () {
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
        buffer.append("DesRest_e(\n");

        buffer.append(tab);
        buffer.append(") [DesRest_e]");
        return buffer.toString();
    }
}
