// generated with ast extension for cup
// version 0.8
// 7/1/2026 15:58:57


package rs.ac.bg.etf.pp1.ast;

public class ConstRestComma extends ConstRest {

    private ConstDeclItem ConstDeclItem;
    private ConstRest ConstRest;

    public ConstRestComma (ConstDeclItem ConstDeclItem, ConstRest ConstRest) {
        this.ConstDeclItem=ConstDeclItem;
        if(ConstDeclItem!=null) ConstDeclItem.setParent(this);
        this.ConstRest=ConstRest;
        if(ConstRest!=null) ConstRest.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclItem!=null) ConstDeclItem.accept(visitor);
        if(ConstRest!=null) ConstRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclItem!=null) ConstDeclItem.traverseTopDown(visitor);
        if(ConstRest!=null) ConstRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclItem!=null) ConstDeclItem.traverseBottomUp(visitor);
        if(ConstRest!=null) ConstRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstRestComma(\n");

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
        buffer.append(") [ConstRestComma]");
        return buffer.toString();
    }
}
