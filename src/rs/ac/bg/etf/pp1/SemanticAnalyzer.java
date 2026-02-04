package rs.ac.bg.etf.pp1;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.sym;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {
	private boolean errorDetected = false;

	Logger log = Logger.getLogger(getClass());

	private Obj currentProgram;
	private Struct currentType;

	private int currentConstVal = 0;
	private Struct currentConstType;

	private Struct currentEnumStruct;
	private Obj currentEnumObj;
	private Integer lastEnumVal = null;
	private Set<Integer> currentEnumValues = null;

	private boolean mainExists = false;
	private Obj currentMethod;
	private Struct currentReturnType = null;

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
		log.info(msg.toString());
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
			return;
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
		this.currentConstType = Tab.find("bool").getType();

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

		this.currentReturnType = this.currentType;
		this.currentMethod = Tab.insert(Obj.Meth, funcName, this.currentReturnType);
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

		this.currentReturnType = null;
		this.currentMethod = Tab.insert(Obj.Meth, m.getI1(), Tab.noType);
		Tab.openScope();
	}

	@Override
	public void visit(MethodDecl md) {
		Tab.chainLocalSymbols(this.currentMethod);
		Tab.closeScope();
		this.currentMethod = null;
		this.currentReturnType = null;

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
			return;
		}
		if (Tab.currentScope().findSymbol(parName) != null) {
			report_error("Dvostruka definicija za parametar: " + parName, arr);
			return;
		}
		Obj arrObj = Tab.insert(Obj.Var, parName, new Struct(Struct.Array, this.currentType));
		arrObj.setFpPos(1);
		this.currentMethod.setLevel(this.currentMethod.getLevel() + 1);
	}

}
