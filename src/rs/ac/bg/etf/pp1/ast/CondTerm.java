// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:30:30


package rs.ac.bg.etf.pp1.ast;

public class CondTerm implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private CondFact CondFact;
    private AndStart AndStart;
    private CondTermRest CondTermRest;

    public CondTerm (CondFact CondFact, AndStart AndStart, CondTermRest CondTermRest) {
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.AndStart=AndStart;
        if(AndStart!=null) AndStart.setParent(this);
        this.CondTermRest=CondTermRest;
        if(CondTermRest!=null) CondTermRest.setParent(this);
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public AndStart getAndStart() {
        return AndStart;
    }

    public void setAndStart(AndStart AndStart) {
        this.AndStart=AndStart;
    }

    public CondTermRest getCondTermRest() {
        return CondTermRest;
    }

    public void setCondTermRest(CondTermRest CondTermRest) {
        this.CondTermRest=CondTermRest;
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
        if(CondFact!=null) CondFact.accept(visitor);
        if(AndStart!=null) AndStart.accept(visitor);
        if(CondTermRest!=null) CondTermRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(AndStart!=null) AndStart.traverseTopDown(visitor);
        if(CondTermRest!=null) CondTermRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(AndStart!=null) AndStart.traverseBottomUp(visitor);
        if(CondTermRest!=null) CondTermRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondTerm(\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AndStart!=null)
            buffer.append(AndStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTermRest!=null)
            buffer.append(CondTermRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondTerm]");
        return buffer.toString();
    }
}
