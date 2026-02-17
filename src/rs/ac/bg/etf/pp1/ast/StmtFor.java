// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:30:30


package rs.ac.bg.etf.pp1.ast;

public class StmtFor extends Statement {

    private StartFor StartFor;
    private DesignatorStmtOpt DesignatorStmtOpt;
    private ForLoop ForLoop;

    public StmtFor (StartFor StartFor, DesignatorStmtOpt DesignatorStmtOpt, ForLoop ForLoop) {
        this.StartFor=StartFor;
        if(StartFor!=null) StartFor.setParent(this);
        this.DesignatorStmtOpt=DesignatorStmtOpt;
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.setParent(this);
        this.ForLoop=ForLoop;
        if(ForLoop!=null) ForLoop.setParent(this);
    }

    public StartFor getStartFor() {
        return StartFor;
    }

    public void setStartFor(StartFor StartFor) {
        this.StartFor=StartFor;
    }

    public DesignatorStmtOpt getDesignatorStmtOpt() {
        return DesignatorStmtOpt;
    }

    public void setDesignatorStmtOpt(DesignatorStmtOpt DesignatorStmtOpt) {
        this.DesignatorStmtOpt=DesignatorStmtOpt;
    }

    public ForLoop getForLoop() {
        return ForLoop;
    }

    public void setForLoop(ForLoop ForLoop) {
        this.ForLoop=ForLoop;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StartFor!=null) StartFor.accept(visitor);
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.accept(visitor);
        if(ForLoop!=null) ForLoop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StartFor!=null) StartFor.traverseTopDown(visitor);
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.traverseTopDown(visitor);
        if(ForLoop!=null) ForLoop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StartFor!=null) StartFor.traverseBottomUp(visitor);
        if(DesignatorStmtOpt!=null) DesignatorStmtOpt.traverseBottomUp(visitor);
        if(ForLoop!=null) ForLoop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtFor(\n");

        if(StartFor!=null)
            buffer.append(StartFor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmtOpt!=null)
            buffer.append(DesignatorStmtOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForLoop!=null)
            buffer.append(ForLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtFor]");
        return buffer.toString();
    }
}
