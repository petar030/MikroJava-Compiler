package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	Logger log = Logger.getLogger(getClass());


	private int mainPC;

	private final Struct boolType = Tab.find("bool").getType();
	private int printWidth = 0;
	private Obj designatorArrayTmp = null;

	public int getMainPc() {
		return this.mainPC;
	}

	/* HELPER FUNCTION */
	public void visitMethod(MethodNameAndType method) {
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


	/* Method Declaration */

	@Override
	public void visit(MethodDecl methodExit) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(MethodNameAndType_Void method) {
		this.visitMethod(method);
	}

	@Override
	public void visit(MethodNameAndType_RetVal method) {
		this.visitMethod(method);
	}

	/* Statement */

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

	/* Expr */

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
	    if (d instanceof Designator_length) {
	        Designator_length x = (Designator_length) d;
	        Obj arr = SemanticAnalyzer.lengthMap.get(x.obj);
	        log.info(os(arr));
	        Code.load(arr);
	        Code.put(Code.arraylength);
	        return;
	    }

		Obj designator = factor.getDesignator().obj;
		Code.load(designator);
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
		if (factor.getType().obj.getType() == Tab.charType) {
			Code.put(Code.newarray);
			Code.put(0);
		} else {
			Code.put(Code.newarray);
			Code.put(1);
		}

	}
	
//	@Override
//	public void visit(FactorExpr factor) {
//		
//	}
	
	/* Designator */


}
