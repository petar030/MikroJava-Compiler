// generated with ast extension for cup
// version 0.8
// 4/1/2026 20:0:5


package rs.ac.bg.etf.pp1.ast;

public class DesignatorRest_more_dot_length extends DesignatorRest {

    private DesignatorRest DesignatorRest;

    public DesignatorRest_more_dot_length (DesignatorRest DesignatorRest) {
        this.DesignatorRest=DesignatorRest;
        if(DesignatorRest!=null) DesignatorRest.setParent(this);
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
        if(DesignatorRest!=null) DesignatorRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorRest!=null) DesignatorRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorRest!=null) DesignatorRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorRest_more_dot_length(\n");

        if(DesignatorRest!=null)
            buffer.append(DesignatorRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorRest_more_dot_length]");
        return buffer.toString();
    }
}
