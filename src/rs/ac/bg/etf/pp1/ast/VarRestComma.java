// generated with ast extension for cup
// version 0.8
// 4/1/2026 15:38:10


package rs.ac.bg.etf.pp1.ast;

public class VarRestComma extends VarRest {

    private VarDeclItem VarDeclItem;
    private VarRest VarRest;

    public VarRestComma (VarDeclItem VarDeclItem, VarRest VarRest) {
        this.VarDeclItem=VarDeclItem;
        if(VarDeclItem!=null) VarDeclItem.setParent(this);
        this.VarRest=VarRest;
        if(VarRest!=null) VarRest.setParent(this);
    }

    public VarDeclItem getVarDeclItem() {
        return VarDeclItem;
    }

    public void setVarDeclItem(VarDeclItem VarDeclItem) {
        this.VarDeclItem=VarDeclItem;
    }

    public VarRest getVarRest() {
        return VarRest;
    }

    public void setVarRest(VarRest VarRest) {
        this.VarRest=VarRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclItem!=null) VarDeclItem.accept(visitor);
        if(VarRest!=null) VarRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclItem!=null) VarDeclItem.traverseTopDown(visitor);
        if(VarRest!=null) VarRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclItem!=null) VarDeclItem.traverseBottomUp(visitor);
        if(VarRest!=null) VarRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarRestComma(\n");

        if(VarDeclItem!=null)
            buffer.append(VarDeclItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarRest!=null)
            buffer.append(VarRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarRestComma]");
        return buffer.toString();
    }
}
