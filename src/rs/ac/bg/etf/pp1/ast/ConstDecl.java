// generated with ast extension for cup
// version 0.8
// 7/1/2026 15:58:57


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private ConstDeclItem ConstDeclItem;
    private ConstRest ConstRest;

    public ConstDecl (Type Type, ConstDeclItem ConstDeclItem, ConstRest ConstRest) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstDeclItem=ConstDeclItem;
        if(ConstDeclItem!=null) ConstDeclItem.setParent(this);
        this.ConstRest=ConstRest;
        if(ConstRest!=null) ConstRest.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstDeclItem getConstDeclItem() {
        return ConstDeclItem;
    }

    public void setConstDeclItem(ConstDeclItem ConstDeclItem) {
        this.ConstDeclItem=ConstDeclItem;
    }

    public ConstRest getConstRest() {
        return ConstRest;
    }

    public void setConstRest(ConstRest ConstRest) {
        this.ConstRest=ConstRest;
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
        if(Type!=null) Type.accept(visitor);
        if(ConstDeclItem!=null) ConstDeclItem.accept(visitor);
        if(ConstRest!=null) ConstRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstDeclItem!=null) ConstDeclItem.traverseTopDown(visitor);
        if(ConstRest!=null) ConstRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstDeclItem!=null) ConstDeclItem.traverseBottomUp(visitor);
        if(ConstRest!=null) ConstRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclItem!=null)
            buffer.append(ConstDeclItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstRest!=null)
            buffer.append(ConstRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
