// generated with ast extension for cup
// version 0.8
// 28/0/2026 18:34:1


package src/rs/ac.bg.etf.pp1.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String I2;
    private ArrayOpt ArrayOpt;
    private VarRest VarRest;

    public VarDecl (Type Type, String I2, ArrayOpt ArrayOpt, VarRest VarRest) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I2=I2;
        this.ArrayOpt=ArrayOpt;
        if(ArrayOpt!=null) ArrayOpt.setParent(this);
        this.VarRest=VarRest;
        if(VarRest!=null) VarRest.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
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
        if(ArrayOpt!=null) ArrayOpt.accept(visitor);
        if(VarRest!=null) VarRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ArrayOpt!=null) ArrayOpt.traverseTopDown(visitor);
        if(VarRest!=null) VarRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ArrayOpt!=null) ArrayOpt.traverseBottomUp(visitor);
        if(VarRest!=null) VarRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
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
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
