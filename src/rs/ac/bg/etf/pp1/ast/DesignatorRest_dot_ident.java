// generated with ast extension for cup
// version 0.8
// 4/1/2026 20:0:5


package rs.ac.bg.etf.pp1.ast;

public class DesignatorRest_dot_ident extends DesignatorRest {

    private FieldName FieldName;

    public DesignatorRest_dot_ident (FieldName FieldName) {
        this.FieldName=FieldName;
        if(FieldName!=null) FieldName.setParent(this);
    }

    public FieldName getFieldName() {
        return FieldName;
    }

    public void setFieldName(FieldName FieldName) {
        this.FieldName=FieldName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FieldName!=null) FieldName.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FieldName!=null) FieldName.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FieldName!=null) FieldName.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorRest_dot_ident(\n");

        if(FieldName!=null)
            buffer.append(FieldName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorRest_dot_ident]");
        return buffer.toString();
    }
}
