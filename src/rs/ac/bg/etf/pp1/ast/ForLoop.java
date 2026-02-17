// generated with ast extension for cup
// version 0.8
// 16/1/2026 23:39:35


package rs.ac.bg.etf.pp1.ast;

public class ForLoop implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private StartLoop StartLoop;
    private ConditionOpt ConditionOpt;
    private DstStart DstStart;
    private DesignatorStmtOpt DesignatorStmtOpt;
    private DstEnd DstEnd;
    private StartForBody StartForBody;
    private Statement Statement;

    public ForLoop (StartLoop StartLoop, ConditionOpt ConditionOpt, DstStart DstStart, DesignatorStmtOpt DesignatorStmtOpt, DstEnd DstEnd, StartForBody StartForBody, Statement Statement) {
        this.StartLoop=StartLoop;
        if(StartLoop!=null) StartLoop.setParent(this);
        this.ConditionOpt=ConditionOpt;
        if(ConditionOpt!=null) ConditionOpt.setParent(this);
        this.DstStart=DstStart;
        if(DstStart!=null) DstStart.setParent(this);
        this.DesignatorStmtOpt=DesignatorStmtOpt;
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.setParent(this);
        this.DstEnd=DstEnd;
        if(DstEnd!=null) DstEnd.setParent(this);
        this.StartForBody=StartForBody;
        if(StartForBody!=null) StartForBody.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public StartLoop getStartLoop() {
        return StartLoop;
    }

    public void setStartLoop(StartLoop StartLoop) {
        this.StartLoop=StartLoop;
    }

    public ConditionOpt getConditionOpt() {
        return ConditionOpt;
    }

    public void setConditionOpt(ConditionOpt ConditionOpt) {
        this.ConditionOpt=ConditionOpt;
    }

    public DstStart getDstStart() {
        return DstStart;
    }

    public void setDstStart(DstStart DstStart) {
        this.DstStart=DstStart;
    }

    public DesignatorStmtOpt getDesignatorStmtOpt() {
        return DesignatorStmtOpt;
    }

    public void setDesignatorStmtOpt(DesignatorStmtOpt DesignatorStmtOpt) {
        this.DesignatorStmtOpt=DesignatorStmtOpt;
    }

    public DstEnd getDstEnd() {
        return DstEnd;
    }

    public void setDstEnd(DstEnd DstEnd) {
        this.DstEnd=DstEnd;
    }

    public StartForBody getStartForBody() {
        return StartForBody;
    }

    public void setStartForBody(StartForBody StartForBody) {
        this.StartForBody=StartForBody;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
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
        if(StartLoop!=null) StartLoop.accept(visitor);
        if(ConditionOpt!=null) ConditionOpt.accept(visitor);
        if(DstStart!=null) DstStart.accept(visitor);
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.accept(visitor);
        if(DstEnd!=null) DstEnd.accept(visitor);
        if(StartForBody!=null) StartForBody.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StartLoop!=null) StartLoop.traverseTopDown(visitor);
        if(ConditionOpt!=null) ConditionOpt.traverseTopDown(visitor);
        if(DstStart!=null) DstStart.traverseTopDown(visitor);
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.traverseTopDown(visitor);
        if(DstEnd!=null) DstEnd.traverseTopDown(visitor);
        if(StartForBody!=null) StartForBody.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StartLoop!=null) StartLoop.traverseBottomUp(visitor);
        if(ConditionOpt!=null) ConditionOpt.traverseBottomUp(visitor);
        if(DstStart!=null) DstStart.traverseBottomUp(visitor);
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.traverseBottomUp(visitor);
        if(DstEnd!=null) DstEnd.traverseBottomUp(visitor);
        if(StartForBody!=null) StartForBody.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForLoop(\n");

        if(StartLoop!=null)
            buffer.append(StartLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOpt!=null)
            buffer.append(ConditionOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DstStart!=null)
            buffer.append(DstStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmtOpt!=null)
            buffer.append(DesignatorStmtOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DstEnd!=null)
            buffer.append(DstEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StartForBody!=null)
            buffer.append(StartForBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForLoop]");
        return buffer.toString();
    }
}
