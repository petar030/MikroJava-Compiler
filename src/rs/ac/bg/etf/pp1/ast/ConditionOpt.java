// generated with ast extension for cup
// version 0.8
// 17/1/2026 19:20:6


package rs.ac.bg.etf.pp1.ast;

public class ConditionOpt implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private CondOpt CondOpt;

    public ConditionOpt (CondOpt CondOpt) {
        this.CondOpt=CondOpt;
        if(CondOpt!=null) CondOpt.setParent(this);
    }

    public CondOpt getCondOpt() {
        return CondOpt;
    }

    public void setCondOpt(CondOpt CondOpt) {
        this.CondOpt=CondOpt;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondOpt!=null) CondOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondOpt!=null) CondOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondOpt!=null) CondOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionOpt(\n");

        if(CondOpt!=null)
            buffer.append(CondOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionOpt]");
        return buffer.toString();
    }
}
