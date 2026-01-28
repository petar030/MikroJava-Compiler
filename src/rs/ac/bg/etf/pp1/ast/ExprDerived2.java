// generated with ast extension for cup
// version 0.8
// 28/0/2026 19:50:52


package src/rs/ac.bg.etf.pp1.ast;

public class ExprDerived2 extends Expr {

    private Term Term;
    private ExprRest ExprRest;

    public ExprDerived2 (Term Term, ExprRest ExprRest) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.ExprRest=ExprRest;
        if(ExprRest!=null) ExprRest.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public ExprRest getExprRest() {
        return ExprRest;
    }

    public void setExprRest(ExprRest ExprRest) {
        this.ExprRest=ExprRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(ExprRest!=null) ExprRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(ExprRest!=null) ExprRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(ExprRest!=null) ExprRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprDerived2(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprRest!=null)
            buffer.append(ExprRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprDerived2]");
        return buffer.toString();
    }
}
