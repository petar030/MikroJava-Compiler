// generated with ast extension for cup
// version 0.8
// 29/0/2026 2:53:8


package src/rs/ac.bg.etf.pp1.ast;

public class SimpleConditionOr extends SimpleCondition {

    private SimpleCondTerm SimpleCondTerm;
    private SimpleConditionList SimpleConditionList;

    public SimpleConditionOr (SimpleCondTerm SimpleCondTerm, SimpleConditionList SimpleConditionList) {
        this.SimpleCondTerm=SimpleCondTerm;
        if(SimpleCondTerm!=null) SimpleCondTerm.setParent(this);
        this.SimpleConditionList=SimpleConditionList;
        if(SimpleConditionList!=null) SimpleConditionList.setParent(this);
    }

    public SimpleCondTerm getSimpleCondTerm() {
        return SimpleCondTerm;
    }

    public void setSimpleCondTerm(SimpleCondTerm SimpleCondTerm) {
        this.SimpleCondTerm=SimpleCondTerm;
    }

    public SimpleConditionList getSimpleConditionList() {
        return SimpleConditionList;
    }

    public void setSimpleConditionList(SimpleConditionList SimpleConditionList) {
        this.SimpleConditionList=SimpleConditionList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SimpleCondTerm!=null) SimpleCondTerm.accept(visitor);
        if(SimpleConditionList!=null) SimpleConditionList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SimpleCondTerm!=null) SimpleCondTerm.traverseTopDown(visitor);
        if(SimpleConditionList!=null) SimpleConditionList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SimpleCondTerm!=null) SimpleCondTerm.traverseBottomUp(visitor);
        if(SimpleConditionList!=null) SimpleConditionList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SimpleConditionOr(\n");

        if(SimpleCondTerm!=null)
            buffer.append(SimpleCondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SimpleConditionList!=null)
            buffer.append(SimpleConditionList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleConditionOr]");
        return buffer.toString();
    }
}
