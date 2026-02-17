// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:30:30


package rs.ac.bg.etf.pp1.ast;

public class FormPars implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private FormParsItem FormParsItem;
    private FormParsRest FormParsRest;

    public FormPars (FormParsItem FormParsItem, FormParsRest FormParsRest) {
        this.FormParsItem=FormParsItem;
        if(FormParsItem!=null) FormParsItem.setParent(this);
        this.FormParsRest=FormParsRest;
        if(FormParsRest!=null) FormParsRest.setParent(this);
    }

    public FormParsItem getFormParsItem() {
        return FormParsItem;
    }

    public void setFormParsItem(FormParsItem FormParsItem) {
        this.FormParsItem=FormParsItem;
    }

    public FormParsRest getFormParsRest() {
        return FormParsRest;
    }

    public void setFormParsRest(FormParsRest FormParsRest) {
        this.FormParsRest=FormParsRest;
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
        if(FormParsItem!=null) FormParsItem.accept(visitor);
        if(FormParsRest!=null) FormParsRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsItem!=null) FormParsItem.traverseTopDown(visitor);
        if(FormParsRest!=null) FormParsRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsItem!=null) FormParsItem.traverseBottomUp(visitor);
        if(FormParsRest!=null) FormParsRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormPars(\n");

        if(FormParsItem!=null)
            buffer.append(FormParsItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsRest!=null)
            buffer.append(FormParsRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormPars]");
        return buffer.toString();
    }
}
