// generated with ast extension for cup
// version 0.8
// 29/0/2026 2:53:8


package src/rs/ac.bg.etf.pp1.ast;

public class SimpleCond_e extends SimpleConditionList {

    public SimpleCond_e () {
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
        buffer.append("SimpleCond_e(\n");

        buffer.append(tab);
        buffer.append(") [SimpleCond_e]");
        return buffer.toString();
    }
}
