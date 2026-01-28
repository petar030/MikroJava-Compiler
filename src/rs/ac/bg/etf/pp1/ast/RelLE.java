// generated with ast extension for cup
// version 0.8
// 28/0/2026 18:34:1


package src/rs/ac.bg.etf.pp1.ast;

public class RelLE extends Relop {

    public RelLE () {
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
        buffer.append("RelLE(\n");

        buffer.append(tab);
        buffer.append(") [RelLE]");
        return buffer.toString();
    }
}
