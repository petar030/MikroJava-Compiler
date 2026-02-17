// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:30:30


package rs.ac.bg.etf.pp1.ast;

public class CaseListBase extends CaseList {

    private CaseVal CaseVal;
    private StatementList StatementList;
    private CaseEnd CaseEnd;

    public CaseListBase (CaseVal CaseVal, StatementList StatementList, CaseEnd CaseEnd) {
        this.CaseVal=CaseVal;
        if(CaseVal!=null) CaseVal.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
        this.CaseEnd=CaseEnd;
        if(CaseEnd!=null) CaseEnd.setParent(this);
    }

    public CaseVal getCaseVal() {
        return CaseVal;
    }

    public void setCaseVal(CaseVal CaseVal) {
        this.CaseVal=CaseVal;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public CaseEnd getCaseEnd() {
        return CaseEnd;
    }

    public void setCaseEnd(CaseEnd CaseEnd) {
        this.CaseEnd=CaseEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseVal!=null) CaseVal.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
        if(CaseEnd!=null) CaseEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseVal!=null) CaseVal.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
        if(CaseEnd!=null) CaseEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseVal!=null) CaseVal.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        if(CaseEnd!=null) CaseEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseListBase(\n");

        if(CaseVal!=null)
            buffer.append(CaseVal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseEnd!=null)
            buffer.append(CaseEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseListBase]");
        return buffer.toString();
    }
}
