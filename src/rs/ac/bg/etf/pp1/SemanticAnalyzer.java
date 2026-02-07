package rs.ac.bg.etf.pp1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.sym;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {
	private boolean errorDetected = false;

	Logger log = Logger.getLogger(getClass());

	private final Struct boolType = Tab.find("bool").getType();
	private final Struct intType = Tab.find("int").getType();

	private Obj currentProgram;
	private Struct currentType;

	private int currentConstVal = 0;
	private Struct currentConstType;

	private Struct currentEnumStruct;
	private Obj currentEnumObj;
	private Integer lastEnumVal = null;
	private Set<Integer> currentEnumValues = null;

	private boolean mainExists = false;
	private boolean returnExists = false;
	private Obj currentMethod;
	private Struct currentReturnType = null;

	private Obj DesignatorBase;

	private Set<Integer> currentCaseValues = null;

	private int forDepth = 0;
	private int switchDepth = 0;

	private Obj currentDesignator = null;

	private Deque<List<Struct>> actParsStack = new ArrayDeque<>();

	/* HELPER METHODS */

	private boolean isGlobal(Obj o) {

		if (o == null)
			return false;

		if (o.getKind() == Obj.Var) {
			if (o.getFpPos() > 0)
				return false; // formali nisu globalni
			if (o.getLevel() == 0)
				return true; // globalna var
		}

		return false;

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

	private Struct num(Struct s) {
		return (s != null && s.getKind() == Struct.Enum) ? Tab.intType : s;
	}

	public List<Obj> getFormalParameters(Obj designatorObj) {
		List<Obj> formals = new ArrayList<>();

		if (designatorObj == null)
			return formals;

		if (designatorObj.getKind() != Obj.Meth) {
			return formals;
		}
		int i = 0;
		List<String> default_funs = new ArrayList<>();
		default_funs.add("chr");
		default_funs.add("ord");
		default_funs.add("len");
		for (Obj o : designatorObj.getLocalSymbols()) {
			// log.info("FormPar " + i + ": " + o.getName());
			if (o.getFpPos() == 1 || default_funs.contains(designatorObj.getName())) {
				formals.add(o);
				// log.info("FormPar " + i + ": " + o.getName());
				i++;
			}
		}

		return formals;
	}

	/* LOG MESSAGES */
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		// log.info(msg.toString());
	}

	public boolean errorDetected() {
		return errorDetected;
	}

	/* NODE VISITOR METHODS */

	/* Program */

	@Override
	public void visit(ProgName programName) {
		currentProgram = Tab.insert(Obj.Prog, programName.getI1(), Tab.noType);
		Tab.openScope();
	}

	@Override
	public void visit(Program program) {
		Tab.chainLocalSymbols(currentProgram);
		Tab.closeScope();
		currentProgram = null;
		if (!this.mainExists) {
			report_error("Ne postoji main metoda", program);

		}

	}

	/* Type */
	@Override
	public void visit(Type type) {
		Obj typeObj = Tab.find(type.getI1());
		if (typeObj == Tab.noObj) {
			report_error("Nepostojeci tip podatka: " + type.getI1(), type);
			currentType = Tab.noType;
		} else if (typeObj.getKind() != Obj.Type) {
			report_error("Nepostojeci tip podatka: " + type.getI1(), type);
			currentType = Tab.noType;
		} else {
			currentType = typeObj.getType();
		}
		type.obj = typeObj;
	}

	/* ConstDecl */

	// Tip koji se koristi u obilasku trenutnog podstabla (ConstDecl, VarDecl,
	// MethodDecl ...)

	@Override
	public void visit(ConstDeclItem constDeclItem) {
		if (Tab.find(constDeclItem.getI1()) != Tab.noObj) {
			report_error("Dvostruka definicija konstante: " + constDeclItem.getI1(), constDeclItem);
			return;
		}
		if (!this.currentConstType.assignableTo(this.currentType)) {
			report_error("Nekompatibilan tip u deklaraciji konstante: " + constDeclItem.getI1(), constDeclItem);
			return;

		}
		Obj curr = Tab.insert(Obj.Con, constDeclItem.getI1(), currentType);
		curr.setAdr(this.currentConstVal);
	}

	@Override
	public void visit(ConstNum constNum) {
		this.currentConstVal = constNum.getN1();
		this.currentConstType = Tab.intType;
	}

	@Override
	public void visit(ConstChar constChar) {
		this.currentConstVal = constChar.getC1();
		this.currentConstType = Tab.charType;

	}

	@Override
	public void visit(ConstBool constBool) {
		this.currentConstVal = constBool.getB1();
		this.currentConstType = this.boolType;

	}

	/* VarDecl */

	@Override
	public void visit(VarDeclItem_var varDecl) {
		String name = varDecl.getI1();
		Obj exists = (currentMethod == null) ? Tab.find(name) : Tab.currentScope().findSymbol(name);

		if (exists != Tab.noObj && exists != null) {
			report_error("Dvostruka definicija konstante: " + name, varDecl);
			return;
		}

		Tab.insert(Obj.Var, name, currentType);
	}

	@Override
	public void visit(VarDeclItem_arr arrDecl) {
		String name = arrDecl.getI1();
		Obj exists = (currentMethod == null) ? Tab.find(name) : Tab.currentScope().findSymbol(name);

		if (exists != Tab.noObj && exists != null) {
			report_error("Dvostruka definicija konstante: " + name, arrDecl);
			return;
		}

		Tab.insert(Obj.Var, name, new Struct(Struct.Array, currentType));
	}

	/* EnumDecl */

	@Override
	public void visit(EnumOpen enumOpen) {
		this.currentEnumStruct = new Struct(Struct.Enum);
		if (Tab.find(enumOpen.getI1()) != Tab.noObj) {
			report_error("Dvostruka definicija ENUM-a: " + enumOpen.getI1(), enumOpen);
			return;
		}
		this.currentEnumObj = Tab.insert(Obj.Type, enumOpen.getI1(), this.currentEnumStruct);
		this.currentEnumValues = new HashSet<Integer>();
		Tab.openScope();
		// log.info("Otvoren enum scope\n");

	}

	@Override
	public void visit(EnumField_assign field) {
		if (Tab.currentScope().findSymbol(field.getI1()) != null) {
			report_error("Dvostruka definicija polja unutar ENUM-a: " + field.getI1(), field);
			return;
		}

		if (this.currentEnumValues.contains(field.getN2())) {
			report_error("Dupla vrednost u ENUM-u: " + field.getN2(), field);
			return;
		}

		Obj curr = Tab.insert(Obj.Con, field.getI1(), Tab.intType);
		curr.setAdr(field.getN2());

		this.currentEnumValues.add(field.getN2());

		this.lastEnumVal = field.getN2();

		// log.info("Ubacen element " + field.getI1());

	}

	@Override
	public void visit(EnumField_default field) {
		if (Tab.currentScope.findSymbol(field.getI1()) != null) {
			report_error("Dvostruka definicija polja unutar ENUM-a: " + field.getI1(), field);
			return;
		}

		int val = (this.lastEnumVal == null) ? 0 : this.lastEnumVal + 1;

		while (currentEnumValues.contains(val)) {
			val++;
		}

		currentEnumValues.add(val);

		Obj curr = Tab.insert(Obj.Con, field.getI1(), Tab.intType);
		curr.setAdr(val);

		this.lastEnumVal = val;

		// log.info("Ubacen element " + field.getI1());

	}

	@Override
	public void visit(EnumClose enumClose) {
		this.lastEnumVal = null;
		this.currentEnumValues = null;
		Tab.chainLocalSymbols(this.currentEnumStruct);
		Tab.closeScope();

		// log.info("Zatvoren enum scope\n");

	}

	/* MethodDecl */

	@Override
	public void visit(MethodDecl md) {
		if (md.getMethodNameAndType() instanceof MethodNameAndType_RetVal && !this.returnExists) {
			report_error("Metoda ne sadrzi return statement", md);
		}
		Tab.chainLocalSymbols(this.currentMethod);
		Tab.closeScope();
		this.currentMethod = null;
		this.currentReturnType = null;
		this.returnExists = false;

	}

	@Override
	public void visit(MethodNameAndType_RetVal m) {

		String funcName = m.getI2();
		if (funcName.equalsIgnoreCase("main")) {
			report_error("Nevalidan povratni tip funkcije main", m);
			return;
		}
		if (Tab.find(funcName) != Tab.noObj) {
			report_error("Dvostruka deklaracija funkcije " + funcName, m);
			return;
		}
		this.currentReturnType = m.getType().obj.getType();
		this.currentMethod = Tab.insert(Obj.Meth, funcName, this.currentType);
		Tab.openScope();
	}

	@Override
	public void visit(MethodNameAndType_Void m) {
		String funcName = m.getI1();

		if (funcName.equalsIgnoreCase("main")) {
			if (this.mainExists) {
				report_error("Dvostruka deklaracija funkcije main", m);
				return;
			} else
				this.mainExists = true;
		}
		if (Tab.find(funcName) != Tab.noObj) {
			report_error("Dvostruka deklaracija funkcije + " + funcName, m);
			return;
		}

		this.currentReturnType = Tab.noType;
		this.currentMethod = Tab.insert(Obj.Meth, m.getI1(), Tab.noType);
		Tab.openScope();
	}

	@Override
	public void visit(FormParsOptYes pars) {
		if (this.currentMethod.getName().equalsIgnoreCase("main")) {
			report_error("Main ne sme da sadrzi parametre.", pars);
			return;
		}
	}

	@Override
	public void visit(FormPars_var var) {
		String parName = var.getI2();
		if (this.currentMethod == null) {
			report_error("Semanticka greska [FormPars_var] ", var);
			return;
		}

		if (Tab.currentScope().findSymbol(parName) != null) {
			report_error("Dvostruka definicija za parametar: " + parName, var);
			return;
		}
		Obj varObj = Tab.insert(Obj.Var, parName, this.currentType);
		varObj.setFpPos(1);
		this.currentMethod.setLevel(this.currentMethod.getLevel() + 1);

	}

	@Override
	public void visit(FormPars_arr arr) {
		String parName = arr.getI2();
		if (this.currentMethod == null) {
			report_error("Semanticka greska [FormPars_arr] ", arr);
		}
		if (Tab.currentScope().findSymbol(parName) != null) {
			report_error("Dvostruka definicija za parametar: " + parName, arr);

		}
		Obj arrObj = Tab.insert(Obj.Var, parName, new Struct(Struct.Array, this.currentType));
		arrObj.setFpPos(1);
		this.currentMethod.setLevel(this.currentMethod.getLevel() + 1);
	}

	/* KONTEKSNI USLOVI */

	/* Designator */

	@Override
	public void visit(Designator_var des) {
		Obj obj = Tab.find(des.getI1());
		if (obj == Tab.noObj) {
			report_error("Promenljiva '" + des.getI1() + "' nije definisana", des);
			des.obj = Tab.noObj;
			return;
		}
		if (obj.getKind() == Obj.Type || obj.getKind() == Obj.Prog) {
			report_error("Identifikator '" + des.getI1() + "' nije promenljiva/konstanta/parametar", des);
			des.obj = Tab.noObj;
			return;
		}
		if (obj.getKind() == Obj.Con) {
			report_info("Upotreba simboličke konstante: " + obj.getName(), des);
		} else if (obj.getKind() == Obj.Var) {
			if (obj.getFpPos() == 1) {
				report_info("Korišćenje formalnog argumenta: " + obj.getName(), des);
			} else if (isGlobal(obj)) {
				report_info("Korišćenje globalne promenljive: " + obj.getName(), des);
			} else {
				report_info("Korišćenje lokalne promenljive: " + obj.getName(), des);
			}
		}

		des.obj = obj;
	}

	@Override
	public void visit(Designator_length des) {
		Obj obj = Tab.find(des.getI1());
		if (obj == Tab.noObj) {
			report_error("Promenljiva '" + des.getI1() + "' nije definisana", des);
			des.obj = Tab.noObj;
			return;
		}

		if (obj.getType().getKind() != Struct.Array) {
			report_error("Operator '.length' je dozvoljen samo nad nizovima", des);
			des.obj = Tab.noObj;
			return;
		}

		// length vraća int
		des.obj = new Obj(Obj.Con, "length", Tab.intType);
	}

	@Override
	public void visit(Designator_enum des) {
		Obj enumObj = Tab.find(des.getI1());
		if (enumObj == Tab.noObj) {
			report_error("Enum '" + des.getI1() + "' nije definisan", des);
			des.obj = Tab.noObj;
			return;
		}

		Struct t = enumObj.getType();
		if (enumObj.getKind() != Obj.Type || t.getKind() != Struct.Enum) {
			report_error("Identifikator '" + des.getI1() + "' nije enum tip", des);
			des.obj = Tab.noObj;
			return;
		}

		String memberName = des.getI2();

		Obj member = Tab.noObj;
		for (Obj o : t.getMembers()) {
			if (o.getName().equals(memberName)) {
				member = o;
				break;
			}
		}

		if (member == Tab.noObj) {
			report_error("Enum '" + des.getI1() + "' nema element '" + memberName + "'", des);
			des.obj = Tab.noObj;
			return;
		}

		des.obj = member; // enum član je konstanta
		report_info("Upotreba simboličke konstante (enum): " + des.getI1() + "." + des.getI2(), des);
	}

	@Override
	public void visit(Designator_elem des) {
		Obj arrObj = Tab.find(des.getI1());
		if (arrObj == Tab.noObj) {
			report_error("Niz '" + des.getI1() + "' nije definisan", des);
			des.obj = Tab.noObj;
			return;
		}

		if (arrObj.getType().getKind() != Struct.Array) {
			report_error("Identifikator '" + des.getI1() + "' ne označava niz", des);
			des.obj = Tab.noObj;
			return;
		}

		if (des.getExpr().struct != Tab.intType) {
			report_error("Indeks niza mora biti tipa int", des);
			des.obj = Tab.noObj;
			return;
		}

		// element niza je Elem objekat
		des.obj = new Obj(Obj.Elem, arrObj.getName(), arrObj.getType().getElemType());
		report_info("Pristup elementu niza: " + arrObj.getName(), des);
	}

	/* Factor */
	@Override
	public void visit(FactorNum factor) {
		factor.struct = Tab.intType;
	}

	@Override
	public void visit(FactorChar factor) {
		factor.struct = Tab.charType;
	}

	@Override
	public void visit(FactorBool factor) {
		factor.struct = this.boolType;
	}

	@Override
	public void visit(FactorDesignator factor) {

		Obj d = factor.getDesignator().obj;
		int k = d.getKind();

		if (k != Obj.Var && k != Obj.Con && k != Obj.Fld && k != Obj.Elem) {

			report_error("Simbol nije odgovarajuce vrste", factor);
			factor.struct = Tab.noType;
			return;
		}

		factor.struct = d.getType();
	}

	@Override
	public void visit(FactorMeth factor) {
		Obj m = factor.getDesignator().obj;
		if (m.getKind() != Obj.Meth) {
			report_error("Simbol nije metoda", factor);
			factor.struct = Tab.noType;
			return;
		}

		List<Obj> formalPars = getFormalParameters(m);
		List<Struct> actuals = actParsStack.isEmpty() ? Collections.emptyList() : actParsStack.pop();

		if (actuals.size() != formalPars.size()) {
			report_error("pogresan broj argumenata", factor);
			factor.struct = Tab.noType;
			return;
		}

		int n = actuals.size();
		for (int i = 0; i < n; i++) {

			Struct expected = formalPars.get(i).getType();
			Struct actual = actuals.get(i);
			// log.info("arg " + (i+1) + ": expected=" + ts(expected) + ", actual=" +
			// ts(actual));
			if (!actual.assignableTo(expected)) {
				report_error("argument " + (i + 1) + " nije kompatibilan", factor);
			}
		}
		report_info("Poziv globalne funkcije: " + m.getName(), factor);

		factor.struct = m.getType();
	}

	@Override
	public void visit(FactorNew factor) {
		if (!factor.getExpr().struct.equals(Tab.intType)) {
			report_error("Velicina niza mora biti tipa int", factor);
			factor.struct = Tab.noType;
			return;
		}
		factor.struct = new Struct(Struct.Array, this.currentType);
	}

	@Override
	public void visit(FactorExpr factor) {
		factor.struct = factor.getExpr().struct;
	}

	/* Term */

	@Override
	public void visit(TermBase term) {
		term.struct = num(term.getFactor().struct);
	}

	

	@Override
	public void visit(TermRec term) {

		Struct leftType  = term.getTerm().struct;
		Struct rightType = num(term.getFactor().struct);

		if (!leftType.equals(Tab.intType) || !rightType.equals(Tab.intType)) {
			report_error("Mulop operacija nije dozvoljena za ne-int tipove ", term);
			term.struct = Tab.noType;
			return;
		}
		term.struct = Tab.intType;

	}

	/* Expr */

	@Override
	public void visit(AddopBase expr) {
		expr.struct = expr.getTerm().struct;
	}

	@Override
	public void visit(AddopRec expr) {
		Struct leftType = expr.getTerm().struct;
		Struct rightType = expr.getExprRest().struct;
		if (!leftType.equals(Tab.intType) || !rightType.equals(Tab.intType)) {
			log.info(ts(leftType) + " " + ts(rightType));
			report_error("Addop operacija nije dozvoljena za ne-int tipove ", expr);
			expr.struct = Tab.noType;
			return;
		}
		expr.struct = Tab.intType;

	}

	@Override
	public void visit(SimpleExprNeg expr) {
		Struct type = expr.getExprRest().struct;
		if (!type.equals(Tab.intType)) {
			report_error("Negiranje nije dozvoljeno za ne-int tipove ", expr);
			expr.struct = Tab.noType;
			return;
		}
		expr.struct = type;
	}

	@Override
	public void visit(SimpleExprPos expr) {
		expr.struct = expr.getExprRest().struct;
	}

	@Override
	public void visit(ExprSimple expr) {
		expr.struct = expr.getSimpleExpr().struct;
	}

	@Override
	public void visit(ExprTernary expr) {
		Struct exprTrueType = expr.getExpr().struct;
		Struct exprFalseType = expr.getExpr1().struct;
		if (!exprTrueType.equals(exprFalseType)) {
			report_error("Rezultati ternarnog operatora nisu istog tipa ", expr);
			expr.struct = Tab.noType;
			return;
		}
		expr.struct = exprTrueType;
	}

	@Override
	public void visit(ExprOptYes expr) {
		expr.struct = expr.getExpr().struct;
	}

	@Override
	public void visit(ExprOptNo expr) {
		expr.struct = Tab.noType;
	}

	/* Condition */

	@Override
	public void visit(ConditionRec cond) {
		if (!cond.getCondition().struct.equals(this.boolType) || !cond.getCondTerm().struct.equals(this.boolType)) {

			cond.struct = Tab.noType;
			report_error("CondTerm nije tipa bool", cond);
			return;
		}
		cond.struct = this.boolType;
	}

	@Override
	public void visit(ConditionBase cond) {
		if (!cond.getCondTerm().struct.equals(this.boolType)) {
			cond.struct = Tab.noType;
			report_error("CondTerm nije tipa bool", cond);
			return;
		}
		cond.struct = this.boolType;
	}

	@Override
	public void visit(CondTermRec condTerm) {
		if (!condTerm.getCondFact().struct.equals(this.boolType)) {
			condTerm.struct = Tab.noType;
			report_error("CondFact nije tipa bool ", condTerm);
			return;
		}
		condTerm.struct = this.boolType;
	}

	@Override
	public void visit(CondTermBase condTerm) {
		if (!condTerm.getCondFact().struct.equals(this.boolType)) {
			condTerm.struct = Tab.noType;
			report_error("CondFact nije tipa bool ", condTerm);
			return;
		}
		condTerm.struct = this.boolType;
	}

	@Override
	public void visit(CondFactRelop condFact) {
		Struct leftType = condFact.getSimpleExpr().struct;
		Struct rightType = condFact.getSimpleExpr1().struct;
		if (!leftType.compatibleWith(rightType)) {
			report_error("Poredjenje razlicitih tipova ", condFact);
			condFact.struct = Tab.noType;
			return;
		}
		Relop rel = condFact.getRelop();

		boolean isEqNe = (rel instanceof RelEQ) || (rel instanceof RelNE);

		if (leftType.getKind() == Struct.Array && !isEqNe) {
			report_error("Nedozvoljeno poredjenje za Array tip ", condFact);
			condFact.struct = Tab.noType;
			return;

		}

		condFact.struct = this.boolType;

	}

	@Override
	public void visit(CondFactSimple condFact) {
		if (!condFact.getSimpleExpr().struct.equals(this.boolType)) {
			condFact.struct = Tab.noType;
			report_error("Expr nije tipa bool ", condFact);
			return;
		}
		condFact.struct = this.boolType;
	}

	@Override
	public void visit(CondOptYes n) {
		if (!n.getCondition().struct.equals(boolType)) {
			report_error("Uslov mora biti tipa bool", n);
			n.struct = Tab.noType;
			return;
		}
		n.struct = boolType;
	}

	@Override
	public void visit(CondTernaryOptYes n) {
		if (!n.getCondition().struct.equals(boolType)) {
			report_error("Prvi operand u ?: mora biti bool", n);
			n.struct = Tab.noType;
			return;
		}
		Struct t2 = n.getExpr().struct;
		Struct t3 = n.getExpr1().struct;

		if (!t2.equals(boolType) || !t3.equals(boolType)) {
			report_error("Oba izraza u ternarnom moraju biti tipa bool", n);
			n.struct = Tab.noType;
			return;
		}

		n.struct = boolType;
	}

	@Override
	public void visit(CondOptNo n) {
		n.struct = boolType;
	}

	@Override
	public void visit(IfCondBase n) {
		if (!n.getCondition().struct.equals(boolType)) {
			report_error("If uslov mora biti tipa bool", n);
			n.struct = Tab.noType;
			return;
		}
		n.struct = boolType;
	}

	@Override
	public void visit(IfCondTernary n) {
		if (!n.getCondition().struct.equals(boolType)) {
			report_error("Prvi operand u ?: mora biti bool", n);
			n.struct = Tab.noType;
			return;
		}
		Struct t2 = n.getExpr().struct;
		Struct t3 = n.getExpr1().struct;
		if (!t2.equals(boolType) || !t3.equals(boolType)) {
			report_error("Oba izraza u ternarnom operatoru moraju biti tipa bool za if uslov", n);
			n.struct = Tab.noType;
			return;
		}
		n.struct = boolType;
	}

	/* Statement */

	@Override
	public void visit(StmtBreak stmt) {
		if (this.forDepth < 0) {
			report_error("Greska pri semantickoj obradi ", stmt);
			return;
		}
		if (this.switchDepth < 0) {
			report_error("Greska pri semantickoj obradi ", stmt);
		}

		if (this.switchDepth == 0 && this.forDepth == 0) {
			report_error("Break iskaz je dozvoljen samo unutar for i switch blokova", stmt);
			return;
		}
	}

	@Override
	public void visit(StartSwitch s) {
		this.switchDepth++;
	}

	@Override
	public void visit(StmtContinue stmt) {
		if (this.forDepth < 0) {
			report_error("Greska pri semantickoj obradi ", stmt);
			return;
		}
		if (this.forDepth == 0) {
			report_error("Continue iskaz van for petlje", stmt);
			return;
		}

	}

	@Override
	public void visit(StmtReturn stmt) {
		if (this.currentReturnType != null) {
			Struct retExprType = stmt.getExprOpt().struct;
			if (this.currentReturnType == Tab.noType) {
				if (retExprType != Tab.noType) {
					report_error("return u void metodi ne sme imati izraz", stmt);
				}
			} else {
				if (retExprType == Tab.noType || !retExprType.assignableTo(this.currentReturnType)) {
					report_error("tip povratne vrednosti nije validan", stmt);
				}
			}
		}
		this.returnExists = true;
	}

	@Override
	public void visit(StartFor f) {
		this.forDepth++;
	}

	@Override
	public void visit(StmtRead stmt) {
		Obj o = stmt.getDesignator().obj;
		int k = o.getKind();
		if (!(k == Obj.Var || k == Obj.Elem || k == Obj.Fld)) {
			report_error("READ zahteva promenljivu, element niza ili polje objekta", stmt);
			return;
		}
		Struct t = o.getType();
		if (!(t.equals(Tab.intType) || t.equals(Tab.charType) || t.equals(this.boolType))) {
			report_error("READ podržava samo tipove int, char ili bool", stmt);
			return;
		}
	}

	@Override
	public void visit(StmtPrint stmt) {
		Struct t = stmt.getExpr().struct;
		if (!(t.equals(Tab.intType) || t.equals(Tab.charType) || t.equals(this.boolType))) {
			report_error("Print izraz mora biti tipa int, char ili bool", stmt);
			return;
		}
	}

	@Override
	public void visit(StmtIf stmt) {
		if (!stmt.getIfCond().struct.equals(boolType)) {
			report_error("If uslov mora biti tipa bool", stmt);
		}
	}

	@Override
	public void visit(StmtSwitch stmt) {
		this.switchDepth--;
		if (!stmt.getExpr().struct.equals(Tab.intType)) {
			report_error("Switch izraz mora biti tipa int", stmt);
			return;
		}
	}

	@Override
	public void visit(CaseListBase n) {
		currentCaseValues = new HashSet<>();
		int v = n.getN1();
		if (!currentCaseValues.add(v)) {
			report_error("Duplikat case vrednosti: " + v, n);
			return;
		}
	}

	@Override
	public void visit(CaseListRec n) {
		int v = n.getN2();
		if (!currentCaseValues.add(v)) {
			report_error("Duplikat case vrednosti: " + v, n);
			return;
		}
	}

	@Override
	public void visit(StmtFor stmt) {
		this.forDepth--;
		if (!stmt.getConditionOpt().struct.equals(boolType)) {
			report_error("Uslovni izraz u for mora biti tipa bool", stmt);
			return;
		}
	}

	/* DesignatorStatement */

	@Override
	public void visit(DstAssign dst) {
		Obj desObj = dst.getDesignator().obj;
		if (desObj.getKind() != Obj.Var && desObj.getKind() != Obj.Elem /* && desObj.getKind() != Obj.Fld */) {
			report_error("Designator nije promenljiva, element niza ili polje objekta.", dst);
			return;
		}

		Struct left = desObj.getType();
		Struct right = dst.getExpr().struct;

		if (left.getKind() == Struct.Enum) {
			Expr e = dst.getExpr();
			if (e instanceof ExprSimple) {
				SimpleExpr se = ((ExprSimple) e).getSimpleExpr();
				if (se instanceof SimpleExprPos) {
					ExprRest r = ((SimpleExprPos) se).getExprRest();
					if (r instanceof AddopBase) {
						Term t = ((AddopBase) r).getTerm();
						if (t instanceof TermBase) {
							Factor f = ((TermBase) t).getFactor();
							if (f instanceof FactorDesignator) {
								Designator d = ((FactorDesignator) f).getDesignator();
								if (d instanceof Designator_enum) {
									Designator_enum den = (Designator_enum) d;
									String enumName = den.getI1();
									String memberName = den.getI2();

									Obj enumTypeObj = Tab.find(enumName);
									if (enumTypeObj == Tab.noObj || enumTypeObj.getKind() != Obj.Type
											|| enumTypeObj.getType().getKind() != Struct.Enum) {
										report_error("Identifikator '" + enumName + "' nije enum tip", dst);
										return;
									}

									if (enumTypeObj.getType() != left) {
										report_error("Dodela iz drugog enum tipa (" + enumName + ")", dst);
										return;
									}

									boolean memberExists = false;
									for (Obj o : left.getMembers()) {
										if (o.getKind() == Obj.Con && o.getName().equals(memberName)) {
											memberExists = true;
											break;
										}
									}
									if (!memberExists) {
										report_error("Enum '" + enumName + "' nema element '" + memberName + "'", dst);
										return;
									}

									return;
								}
							}
						}
					}
				}
			}
		}

		if (!right.assignableTo(left)) {
			report_error("Neuskladjeni tipovi u dodeli", dst);
			return;
		}
	}

	@Override
	public void visit(DstInc dst) {

		Obj desObj = dst.getDesignator().obj;
		if (desObj.getKind() != Obj.Var && desObj.getKind() != Obj.Elem /* && desObj.getKind() != Obj.Fld */) {
			report_error("Designator nije promenljiva, element niza ili polje objekta ", dst);
			return;
		}
		Struct leftType = desObj.getType();
		if (leftType != Tab.intType) {
			report_error("Nevalidan tip za operaciju ++ ", dst);
			return;
		}

	}

	@Override
	public void visit(DstDec dst) {

		Obj desObj = dst.getDesignator().obj;
		if (desObj.getKind() != Obj.Var && desObj.getKind() != Obj.Elem /* && desObj.getKind() != Obj.Fld */) {
			report_error("Designator nije promenljiva, element niza ili polje objekta ", dst);
			return;
		}
		Struct leftType = desObj.getType();
		if (leftType != Tab.intType) {
			report_error("Nevalidan tip za operaciju -- ", dst);
			return;
		}

	}

	@Override
	public void visit(DstCall dst) {
		Obj m = dst.getDesignator().obj;
		if (m.getKind() != Obj.Meth) {
			report_error("Simbol nije metoda", dst);
			return;
		}

		List<Obj> formalPars = getFormalParameters(m);
		List<Struct> actuals = actParsStack.isEmpty() ? Collections.emptyList() : actParsStack.pop();

		if (actuals.size() != formalPars.size()) {
			report_error("pogresan broj argumenata", dst);
			return;
		}

		int n = actuals.size();
		for (int i = 0; i < n; i++) {
			Struct expected = formalPars.get(i).getType();
			Struct actual = actuals.get(i);
			// log.info("arg " + (i+1) + ": expected=" + ts(expected) + ", actual=" +
			// ts(actual));
			if (!actual.assignableTo(expected)) {
				report_error("argument " + (i + 1) + " nije kompatibilan", dst);
				return;
			}
		}
		report_info("Poziv globalne funkcije: " + m.getName(), dst);

	}

	/* ActPars */

	@Override
	public void visit(ActParsOptNo n) {
		actParsStack.push(new ArrayList<>());
	}

	@Override
	public void visit(ActParsBase n) {
		List<Struct> list = new ArrayList<>();
		actParsStack.push(list);
		list.add(n.getExpr().struct);
	}

	@Override
	public void visit(ActParsRec n) {
		actParsStack.peek().add(n.getExpr().struct);
	}

}
