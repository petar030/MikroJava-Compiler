// generated with ast extension for cup
// version 0.8
// 29/0/2026 2:53:34


package src/rs/ac.bg.etf.pp1.ast;

public class RelGE extends Relop {

    public RelGE () {
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
        buffer.append("RelGE(\n");

        buffer.append(tab);
        buffer.append(") [RelGE]");
        return buffer.toString();
    }
}
