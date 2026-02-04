// generated with ast extension for cup
// version 0.8
// 4/1/2026 15:38:10


package rs.ac.bg.etf.pp1.ast;

public class Designator_rest extends Designator {

    private DesignatorName DesignatorName;
    private DesignatorRest DesignatorRest;

    public Designator_rest (DesignatorName DesignatorName, DesignatorRest DesignatorRest) {
        this.DesignatorName=DesignatorName;
        if(DesignatorName!=null) DesignatorName.setParent(this);
        this.DesignatorRest=DesignatorRest;
        if(DesignatorRest!=null) DesignatorRest.setParent(this);
    }

    public DesignatorName getDesignatorName() {
        return DesignatorName;
    }

    public void setDesignatorName(DesignatorName DesignatorName) {
        this.DesignatorName=DesignatorName;
    }

    public DesignatorRest getDesignatorRest() {
        return DesignatorRest;
    }

    public void setDesignatorRest(DesignatorRest DesignatorRest) {
        this.DesignatorRest=DesignatorRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.accept(visitor);
        if(DesignatorRest!=null) DesignatorRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorName!=null) DesignatorName.traverseTopDown(visitor);
        if(DesignatorRest!=null) DesignatorRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.traverseBottomUp(visitor);
        if(DesignatorRest!=null) DesignatorRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator_rest(\n");

        if(DesignatorName!=null)
            buffer.append(DesignatorName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorRest!=null)
            buffer.append(DesignatorRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator_rest]");
        return buffer.toString();
    }
}
