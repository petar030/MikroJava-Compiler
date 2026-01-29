// generated with ast extension for cup
// version 0.8
// 29/0/2026 2:53:8


package src/rs/ac.bg.etf.pp1.ast;

public class SimpleCondTermAnd extends SimpleCondTerm {

    private SimpleCondTerm SimpleCondTerm;
    private SimpleCondFact SimpleCondFact;

    public SimpleCondTermAnd (SimpleCondTerm SimpleCondTerm, SimpleCondFact SimpleCondFact) {
        this.SimpleCondTerm=SimpleCondTerm;
        if(SimpleCondTerm!=null) SimpleCondTerm.setParent(this);
        this.SimpleCondFact=SimpleCondFact;
        if(SimpleCondFact!=null) SimpleCondFact.setParent(this);
    }

    public SimpleCondTerm getSimpleCondTerm() {
        return SimpleCondTerm;
    }

    public void setSimpleCondTerm(SimpleCondTerm SimpleCondTerm) {
        this.SimpleCondTerm=SimpleCondTerm;
    }

    public SimpleCondFact getSimpleCondFact() {
        return SimpleCondFact;
    }

    public void setSimpleCondFact(SimpleCondFact SimpleCondFact) {
        this.SimpleCondFact=SimpleCondFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SimpleCondTerm!=null) SimpleCondTerm.accept(visitor);
        if(SimpleCondFact!=null) SimpleCondFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SimpleCondTerm!=null) SimpleCondTerm.traverseTopDown(visitor);
        if(SimpleCondFact!=null) SimpleCondFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SimpleCondTerm!=null) SimpleCondTerm.traverseBottomUp(visitor);
        if(SimpleCondFact!=null) SimpleCondFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SimpleCondTermAnd(\n");

        if(SimpleCondTerm!=null)
            buffer.append(SimpleCondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SimpleCondFact!=null)
            buffer.append(SimpleCondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleCondTermAnd]");
        return buffer.toString();
    }
}
