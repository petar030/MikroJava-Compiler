// generated with ast extension for cup
// version 0.8
// 4/1/2026 20:0:5


package rs.ac.bg.etf.pp1.ast;

public class DesignatorRest_dot_length extends DesignatorRest {

    public DesignatorRest_dot_length () {
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
        buffer.append("DesignatorRest_dot_length(\n");

        buffer.append(tab);
        buffer.append(") [DesignatorRest_dot_length]");
        return buffer.toString();
    }
}
