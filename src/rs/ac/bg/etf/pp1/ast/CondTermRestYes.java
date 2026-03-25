// generated with ast extension for cup
// version 0.8
// 17/1/2026 19:20:6


package rs.ac.bg.etf.pp1.ast;

public class CondTermRestYes extends CondTermRest {

    private AndTerm AndTerm;
    private CondFact CondFact;
    private CondTermRest CondTermRest;

    public CondTermRestYes (AndTerm AndTerm, CondFact CondFact, CondTermRest CondTermRest) {
        this.AndTerm=AndTerm;
        if(AndTerm!=null) AndTerm.setParent(this);
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.CondTermRest=CondTermRest;
        if(CondTermRest!=null) CondTermRest.setParent(this);
    }

    public AndTerm getAndTerm() {
        return AndTerm;
    }

    public void setAndTerm(AndTerm AndTerm) {
        this.AndTerm=AndTerm;
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public CondTermRest getCondTermRest() {
        return CondTermRest;
    }

    public void setCondTermRest(CondTermRest CondTermRest) {
        this.CondTermRest=CondTermRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AndTerm!=null) AndTerm.accept(visitor);
        if(CondFact!=null) CondFact.accept(visitor);
        if(CondTermRest!=null) CondTermRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AndTerm!=null) AndTerm.traverseTopDown(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(CondTermRest!=null) CondTermRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AndTerm!=null) AndTerm.traverseBottomUp(visitor);
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(CondTermRest!=null) CondTermRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondTermRestYes(\n");

        if(AndTerm!=null)
            buffer.append(AndTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTermRest!=null)
            buffer.append(CondTermRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondTermRestYes]");
        return buffer.toString();
    }
}
