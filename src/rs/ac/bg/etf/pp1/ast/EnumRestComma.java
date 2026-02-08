// generated with ast extension for cup
// version 0.8
// 8/1/2026 0:51:24


package rs.ac.bg.etf.pp1.ast;

public class EnumRestComma extends EnumRest {

    private EnumField EnumField;
    private EnumRest EnumRest;

    public EnumRestComma (EnumField EnumField, EnumRest EnumRest) {
        this.EnumField=EnumField;
        if(EnumField!=null) EnumField.setParent(this);
        this.EnumRest=EnumRest;
        if(EnumRest!=null) EnumRest.setParent(this);
    }

    public EnumField getEnumField() {
        return EnumField;
    }

    public void setEnumField(EnumField EnumField) {
        this.EnumField=EnumField;
    }

    public EnumRest getEnumRest() {
        return EnumRest;
    }

    public void setEnumRest(EnumRest EnumRest) {
        this.EnumRest=EnumRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumField!=null) EnumField.accept(visitor);
        if(EnumRest!=null) EnumRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumField!=null) EnumField.traverseTopDown(visitor);
        if(EnumRest!=null) EnumRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumField!=null) EnumField.traverseBottomUp(visitor);
        if(EnumRest!=null) EnumRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumRestComma(\n");

        if(EnumField!=null)
            buffer.append(EnumField.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumRest!=null)
            buffer.append(EnumRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumRestComma]");
        return buffer.toString();
    }
}
