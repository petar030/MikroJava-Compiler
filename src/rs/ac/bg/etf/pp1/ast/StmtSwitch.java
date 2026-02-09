// generated with ast extension for cup
// version 0.8
// 9/1/2026 0:21:20


package rs.ac.bg.etf.pp1.ast;

public class StmtSwitch extends Statement {

    private StartSwitch StartSwitch;
    private Expr Expr;
    private CaseList CaseList;

    public StmtSwitch (StartSwitch StartSwitch, Expr Expr, CaseList CaseList) {
        this.StartSwitch=StartSwitch;
        if(StartSwitch!=null) StartSwitch.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
    }

    public StartSwitch getStartSwitch() {
        return StartSwitch;
    }

    public void setStartSwitch(StartSwitch StartSwitch) {
        this.StartSwitch=StartSwitch;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StartSwitch!=null) StartSwitch.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(CaseList!=null) CaseList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StartSwitch!=null) StartSwitch.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StartSwitch!=null) StartSwitch.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtSwitch(\n");

        if(StartSwitch!=null)
            buffer.append(StartSwitch.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtSwitch]");
        return buffer.toString();
    }
}
