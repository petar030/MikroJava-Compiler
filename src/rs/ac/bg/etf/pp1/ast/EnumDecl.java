// generated with ast extension for cup
// version 0.8
// 4/1/2026 15:38:10


package rs.ac.bg.etf.pp1.ast;

public class EnumDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private EnumOpen EnumOpen;
    private EnumField EnumField;
    private EnumRest EnumRest;
    private EnumClose EnumClose;

    public EnumDecl (EnumOpen EnumOpen, EnumField EnumField, EnumRest EnumRest, EnumClose EnumClose) {
        this.EnumOpen=EnumOpen;
        if(EnumOpen!=null) EnumOpen.setParent(this);
        this.EnumField=EnumField;
        if(EnumField!=null) EnumField.setParent(this);
        this.EnumRest=EnumRest;
        if(EnumRest!=null) EnumRest.setParent(this);
        this.EnumClose=EnumClose;
        if(EnumClose!=null) EnumClose.setParent(this);
    }

    public EnumOpen getEnumOpen() {
        return EnumOpen;
    }

    public void setEnumOpen(EnumOpen EnumOpen) {
        this.EnumOpen=EnumOpen;
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

    public EnumClose getEnumClose() {
        return EnumClose;
    }

    public void setEnumClose(EnumClose EnumClose) {
        this.EnumClose=EnumClose;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumOpen!=null) EnumOpen.accept(visitor);
        if(EnumField!=null) EnumField.accept(visitor);
        if(EnumRest!=null) EnumRest.accept(visitor);
        if(EnumClose!=null) EnumClose.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumOpen!=null) EnumOpen.traverseTopDown(visitor);
        if(EnumField!=null) EnumField.traverseTopDown(visitor);
        if(EnumRest!=null) EnumRest.traverseTopDown(visitor);
        if(EnumClose!=null) EnumClose.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumOpen!=null) EnumOpen.traverseBottomUp(visitor);
        if(EnumField!=null) EnumField.traverseBottomUp(visitor);
        if(EnumRest!=null) EnumRest.traverseBottomUp(visitor);
        if(EnumClose!=null) EnumClose.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDecl(\n");

        if(EnumOpen!=null)
            buffer.append(EnumOpen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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

        if(EnumClose!=null)
            buffer.append(EnumClose.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDecl]");
        return buffer.toString();
    }
}
