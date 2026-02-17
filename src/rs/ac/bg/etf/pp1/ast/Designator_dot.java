// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:30:30


package rs.ac.bg.etf.pp1.ast;

public class Designator_dot extends Designator {

    private Name Name;
    private DesignatorRest DesignatorRest;

    public Designator_dot (Name Name, DesignatorRest DesignatorRest) {
        this.Name=Name;
        if(Name!=null) Name.setParent(this);
        this.DesignatorRest=DesignatorRest;
        if(DesignatorRest!=null) DesignatorRest.setParent(this);
    }

    public Name getName() {
        return Name;
    }

    public void setName(Name Name) {
        this.Name=Name;
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
        if(Name!=null) Name.accept(visitor);
        if(DesignatorRest!=null) DesignatorRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Name!=null) Name.traverseTopDown(visitor);
        if(DesignatorRest!=null) DesignatorRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Name!=null) Name.traverseBottomUp(visitor);
        if(DesignatorRest!=null) DesignatorRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator_dot(\n");

        if(Name!=null)
            buffer.append(Name.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorRest!=null)
            buffer.append(DesignatorRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator_dot]");
        return buffer.toString();
    }
}
