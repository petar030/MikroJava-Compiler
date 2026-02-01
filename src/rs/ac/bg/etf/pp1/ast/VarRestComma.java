// generated with ast extension for cup
// version 0.8
// 31/0/2026 15:52:35


package rs.ac.bg.etf.pp1.ast;

public class VarRestComma extends VarRest {

    private String I1;
    private ArrayOpt ArrayOpt;
    private VarRest VarRest;

    public VarRestComma (String I1, ArrayOpt ArrayOpt, VarRest VarRest) {
        this.I1=I1;
        this.ArrayOpt=ArrayOpt;
        if(ArrayOpt!=null) ArrayOpt.setParent(this);
        this.VarRest=VarRest;
        if(VarRest!=null) VarRest.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ArrayOpt getArrayOpt() {
        return ArrayOpt;
    }

    public void setArrayOpt(ArrayOpt ArrayOpt) {
        this.ArrayOpt=ArrayOpt;
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
        if(ArrayOpt!=null) ArrayOpt.accept(visitor);
        if(VarRest!=null) VarRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayOpt!=null) ArrayOpt.traverseTopDown(visitor);
        if(VarRest!=null) VarRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayOpt!=null) ArrayOpt.traverseBottomUp(visitor);
        if(VarRest!=null) VarRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarRestComma(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ArrayOpt!=null)
            buffer.append(ArrayOpt.toString("  "+tab));
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
