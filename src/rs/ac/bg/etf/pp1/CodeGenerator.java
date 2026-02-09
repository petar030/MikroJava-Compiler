package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	Logger log = Logger.getLogger(getClass());

	private int mainPc;

	private final Struct boolType = Tab.find("bool").getType();
	private int printWidth = 0;
	private Obj designatorArrayTmp = null;

	private java.util.ArrayDeque<Integer> ifFalse = new java.util.ArrayDeque<>();
	private java.util.ArrayDeque<Integer> ifEnd = new java.util.ArrayDeque<>();
	private java.util.ArrayDeque<Integer> ternFalse = new java.util.ArrayDeque<>();
	private java.util.ArrayDeque<Integer> ternEnd = new java.util.ArrayDeque<>();

	public int getMainPc() {
		return this.mainPc;
	}

	/* HELPER FUNCTIONS */
	public void visitMethod(MethodNameAndType method) {
		method.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(method.obj.getLevel());
		Code.put(method.obj.getLocalSymbols().size());

	}

	private String ts(Struct s) {
		if (s == null)
			return "null";
		switch (s.getKind()) {
		case Struct.Int:
			return "int";
		case Struct.Char:
			return "char";
		case Struct.Bool:
			return "bool";
		case Struct.Array:
			return ts(s.getElemType()) + "[]";
		default:
			return "kind=" + s.getKind();
		}
	}

	private String os(Obj o) {
		if (o == null)
			return "null";
		String k;
		switch (o.getKind()) {
		case Obj.Con:
			k = "Con";
			break;
		case Obj.Var:
			k = "Var";
			break;
		case Obj.Type:
			k = "Type";
			break;
		case Obj.Meth:
			k = "Meth";
			break;
		case Obj.Fld:
			k = "Fld";
			break;
		case Obj.Elem:
			k = "Elem";
			break;
		case Obj.Prog:
			k = "Prog";
			break;
		default:
			k = "Kind(" + o.getKind() + ")";
		}
		String t = ts(o.getType());
		String fp = (o.getFpPos() > 0) ? (", fpPos=" + o.getFpPos()) : "";
		return k + " " + o.getName() + ": " + t + ", adr=" + o.getAdr() + ", level=" + o.getLevel() + fp;
	}

	private int relopCode(Relop r) {
		if (r instanceof RelEQ)
			return Code.eq;
		if (r instanceof RelNE)
			return Code.ne;
		if (r instanceof RelLT)
			return Code.lt;
		if (r instanceof RelLE)
			return Code.le;
		if (r instanceof RelGT)
			return Code.gt;
		return Code.ge;
	}

	/* Method Declaration */

	@Override
	public void visit(MethodDecl methodExit) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(MethodNameAndType_Void method) {
		if (method.getI1().equalsIgnoreCase("main")) {
			this.mainPc = Code.pc;
		}
		this.visitMethod(method);
	}

	@Override
	public void visit(MethodNameAndType_RetVal method) {
		this.visitMethod(method);
	}

	/* Statement */

	@Override
	public void visit(ThenStart n) {
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		ifFalse.push(Code.pc - 2);
	}

	@Override
	public void visit(ElseStart n) {
		Code.put(Code.jmp);
		Code.put2(0);
		ifEnd.push(Code.pc - 2);
		Code.fixup(ifFalse.pop());
	}

	@Override
	public void visit(ElseNo n) {
		Code.fixup(ifFalse.pop());
	}

	@Override
	public void visit(StmtIf n) {
		if (!ifEnd.isEmpty())
			Code.fixup(ifEnd.pop());
	}

	@Override
	public void visit(StmtReturn stmt) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(StmtRead stmt) {
		if (stmt.getDesignator().obj.getType().equals(Tab.charType))
			Code.put(Code.bread);
		else
			Code.put(Code.read);
		Code.store(stmt.getDesignator().obj);

	}

	@Override
	public void visit(StmtPrint stmt) {
		Code.loadConst(this.printWidth);
		Struct t = stmt.getExpr().struct;
		switch (t.getKind()) {
		case Struct.Char:
			Code.put(Code.bprint);
			break;
		default:
			Code.put(Code.print);
			break;
		}
	}

	@Override
	public void visit(PrintOptYes width) {
		this.printWidth = width.getN1();
	}

	@Override
	public void visit(PrintOptNo width) {
		this.printWidth = 0;
	}

	/* DesignatorStatement */

	@Override
	public void visit(DstAssign dst) {
		Obj designator = dst.getDesignator().obj;
		Code.store(designator);
	}

	@Override
	public void visit(DstCall dst) {
		Obj m = dst.getDesignator().obj;
		String name = m.getName();
		if ("len".equals(name)) {
			Code.put(Code.arraylength);
			if (m.getType() != Tab.noType)
				Code.put(Code.pop);
			return;
		}
		if ("chr".equals(name) || "ord".equals(name)) {
			Code.put(Code.pop);
			return;
		}
		int off = m.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(off);
		if (m.getType() != Tab.noType)
			Code.put(Code.pop);
	}

	@Override
	public void visit(DstInc dst) {
		if (dst.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(dst.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(dst.getDesignator().obj);
	}

	@Override
	public void visit(DstDec dst) {
		if (dst.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(dst.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(dst.getDesignator().obj);
	}

	/* Condition */

	@Override
	public void visit(ConditionRec condition) {

		Code.put(Code.add);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		int jf = Code.pc - 2;
		Code.loadConst(1);
		Code.put(Code.jmp);
		Code.put2(0);
		int je = Code.pc - 2;
		Code.fixup(jf);
		Code.loadConst(0);
		Code.fixup(je);

	}

	@Override
	public void visit(CondTermRec condTerm) {
		Code.put(Code.mul);
	}

	@Override
	public void visit(CondFactRelop condFact) {
		int op = relopCode(condFact.getRelop());
		Code.putFalseJump(op, 0);
		int jf = Code.pc - 2;
		Code.loadConst(1);
		Code.put(Code.jmp);
		Code.put2(0);
		int je = Code.pc - 2;
		Code.fixup(jf);
		Code.loadConst(0);
		Code.fixup(je);

	}

	/* Expr */

	@Override
	public void visit(QMarkM n) {
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		ternFalse.push(Code.pc - 2);
	}

	@Override
	public void visit(ColonM n) {
		Code.put(Code.jmp);
		Code.put2(0);
		ternEnd.push(Code.pc - 2);
		Code.fixup(ternFalse.pop());
	}

	@Override
	public void visit(ExprTernary n) {
		Code.fixup(ternEnd.pop());
	}

	@Override
	public void visit(IfCondTernary n) {
		Code.fixup(ternEnd.pop());
	}

	@Override
	public void visit(CondTernaryOptYes n) {
		Code.fixup(ternEnd.pop());
	}

	@Override
	public void visit(SimpleExprNeg expr) {
		Code.put(Code.neg);
	}

	@Override
	public void visit(AddopRec expr) {
		if (expr.getAddop() instanceof AddPlus)
			Code.put(Code.add);
		if (expr.getAddop() instanceof AddMinus)
			Code.put(Code.sub);
	}

	/* Term */
	@Override
	public void visit(TermRec term) {
		if (term.getMulop() instanceof MulMul)
			Code.put(Code.mul);
		if (term.getMulop() instanceof MulDiv)
			Code.put(Code.div);
		if (term.getMulop() instanceof MulMod)
			Code.put(Code.rem);

	}

	/* Factor */

	@Override
	public void visit(FactorDesignator factor) {
		Designator d = factor.getDesignator();
		if (d instanceof Designator_dot) {
			Designator_dot m = (Designator_dot) d;
			DesignatorRest r = m.getDesignatorRest();
			if (r instanceof DrLength) {
				Obj arr = m.getName().obj;
				Code.load(arr);
				Code.put(Code.arraylength);
				return;
			}
		}
		Code.load(d.obj);
	}


	@Override
	public void visit(FactorMeth factor) {
	    Obj m = factor.getDesignator().obj;
	    String name = m.getName();
	    if ("len".equals(name)) {
	        Code.put(Code.arraylength);
	        return;
	    }
	    if ("chr".equals(name) || "ord".equals(name)) {
	        return;
	    }
	    int off = m.getAdr() - Code.pc;
	    Code.put(Code.call);
	    Code.put2(off);
	}


	@Override
	public void visit(FactorNum factor) {
		int num = factor.getN1();
		Code.loadConst(num);
	}

	@Override
	public void visit(FactorChar factor) {
		Code.loadConst((int) factor.getC1());
	}

	@Override
	public void visit(FactorBool factor) {
		Code.loadConst(factor.getB1());
	}

	@Override
	public void visit(FactorNew factor) {
		Code.put(Code.newarray);

		if (factor.getType().obj.getType() == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}

	}

	/* Designator */

	@Override
	public void visit(ArrayName arr) {
		Code.load(arr.obj);
	}

}
