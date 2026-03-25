// generated with ast extension for cup
// version 0.8
// 17/1/2026 19:20:6


package rs.ac.bg.etf.pp1.ast;

public class ConditionRestYes extends ConditionRest {

    private OrCond OrCond;
    private CondTerm CondTerm;
    private ConditionRest ConditionRest;

    public ConditionRestYes (OrCond OrCond, CondTerm CondTerm, ConditionRest ConditionRest) {
        this.OrCond=OrCond;
        if(OrCond!=null) OrCond.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
        this.ConditionRest=ConditionRest;
        if(ConditionRest!=null) ConditionRest.setParent(this);
    }

    public OrCond getOrCond() {
        return OrCond;
    }

    public void setOrCond(OrCond OrCond) {
        this.OrCond=OrCond;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public ConditionRest getConditionRest() {
        return ConditionRest;
    }

    public void setConditionRest(ConditionRest ConditionRest) {
        this.ConditionRest=ConditionRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OrCond!=null) OrCond.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
        if(ConditionRest!=null) ConditionRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OrCond!=null) OrCond.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
        if(ConditionRest!=null) ConditionRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OrCond!=null) OrCond.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        if(ConditionRest!=null) ConditionRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionRestYes(\n");

        if(OrCond!=null)
            buffer.append(OrCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionRest!=null)
            buffer.append(ConditionRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionRestYes]");
        return buffer.toString();
    }
}
