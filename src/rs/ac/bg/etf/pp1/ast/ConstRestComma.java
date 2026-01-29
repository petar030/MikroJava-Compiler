// generated with ast extension for cup
// version 0.8
// 29/0/2026 16:34:35


package rs.ac.bg.etf.pp1.ast;

public class ConstRestComma extends ConstRest {

    private String I1;
    private ConstValue ConstValue;
    private ConstRest ConstRest;

    public ConstRestComma (String I1, ConstValue ConstValue, ConstRest ConstRest) {
        this.I1=I1;
        this.ConstValue=ConstValue;
        if(ConstValue!=null) ConstValue.setParent(this);
        this.ConstRest=ConstRest;
        if(ConstRest!=null) ConstRest.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ConstValue getConstValue() {
        return ConstValue;
    }

    public void setConstValue(ConstValue ConstValue) {
        this.ConstValue=ConstValue;
    }

    public ConstRest getConstRest() {
        return ConstRest;
    }

    public void setConstRest(ConstRest ConstRest) {
        this.ConstRest=ConstRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstValue!=null) ConstValue.accept(visitor);
        if(ConstRest!=null) ConstRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstValue!=null) ConstValue.traverseTopDown(visitor);
        if(ConstRest!=null) ConstRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstValue!=null) ConstValue.traverseBottomUp(visitor);
        if(ConstRest!=null) ConstRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstRestComma(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ConstValue!=null)
            buffer.append(ConstValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstRest!=null)
            buffer.append(ConstRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstRestComma]");
        return buffer.toString();
    }
}
