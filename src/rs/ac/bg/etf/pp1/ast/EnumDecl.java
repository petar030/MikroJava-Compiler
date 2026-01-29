// generated with ast extension for cup
// version 0.8
// 29/0/2026 17:49:50


package rs.ac.bg.etf.pp1.ast;

public class EnumDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private String I2;
    private EnumAssignOpt EnumAssignOpt;
    private EnumRest EnumRest;

    public EnumDecl (String I1, String I2, EnumAssignOpt EnumAssignOpt, EnumRest EnumRest) {
        this.I1=I1;
        this.I2=I2;
        this.EnumAssignOpt=EnumAssignOpt;
        if(EnumAssignOpt!=null) EnumAssignOpt.setParent(this);
        this.EnumRest=EnumRest;
        if(EnumRest!=null) EnumRest.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public EnumAssignOpt getEnumAssignOpt() {
        return EnumAssignOpt;
    }

    public void setEnumAssignOpt(EnumAssignOpt EnumAssignOpt) {
        this.EnumAssignOpt=EnumAssignOpt;
    }

    public EnumRest getEnumRest() {
        return EnumRest;
    }

    public void setEnumRest(EnumRest EnumRest) {
        this.EnumRest=EnumRest;
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
        if(EnumAssignOpt!=null) EnumAssignOpt.accept(visitor);
        if(EnumRest!=null) EnumRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumAssignOpt!=null) EnumAssignOpt.traverseTopDown(visitor);
        if(EnumRest!=null) EnumRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumAssignOpt!=null) EnumAssignOpt.traverseBottomUp(visitor);
        if(EnumRest!=null) EnumRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDecl(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        if(EnumAssignOpt!=null)
            buffer.append(EnumAssignOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumRest!=null)
            buffer.append(EnumRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDecl]");
        return buffer.toString();
    }
}
