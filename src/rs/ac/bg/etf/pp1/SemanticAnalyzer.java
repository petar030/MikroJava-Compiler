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

	private Obj DesignatorBase;

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
		Obj varObj = Tab.find(des.getI1());
		if (varObj == Tab.noObj) {
			report_error("Promenljiva " + " " + des.getI1() + " nije definisana ", des);
			des.obj = Tab.noObj;
		} else if (varObj.getKind() == Obj.Type || varObj.getKind() == Obj.Prog) {
			report_error("Neadekvatna promenljiva", des);
			des.obj = Tab.noObj;
		} else {
			des.obj = varObj;
		}
	}

	@Override
	public void visit(Designator_elem des) {
		Obj arrObj = Tab.find(des.getDesignatorArrayName().getI1());
		if (arrObj == Tab.noObj) {
			report_error("Promenljiva " + des.getDesignatorArrayName().getI1() + " nije definisana", des);
			des.obj = Tab.noObj;
			return;
		}
		if (arrObj.getKind() != Obj.Var || arrObj.getType().getKind() != Struct.Array) {
			report_error("Ime " + arrObj.getName() + "= ne označava niz", des);
			des.obj = Tab.noObj;
			return;
		}
		if (des.getExpr().struct != Tab.intType) {
			report_error("Indeks niza mora biti tipa int", des);
			des.obj = Tab.noObj;
			return;
		}
		des.obj = new Obj(Obj.Elem, arrObj.getName(), arrObj.getType().getElemType());

	}

	@Override
	public void visit(Designator_rest des) {
		des.obj = des.getDesignatorRest().obj;
	}

	@Override
	public void visit(DesignatorName desName) {
		Obj varObj = Tab.find(desName.getI1());
		if (varObj == Tab.noObj) {
			report_error("Promenljiva " + " " + desName.getI1() + " nije definisana ", desName);
			this.DesignatorBase = Tab.noObj;
			return;
		}
		this.DesignatorBase = varObj;

	}

	@Override
	public void visit(Designator_elem_rest des) {
		if (des.getExpr().struct != Tab.intType) {
			report_error("Indeks niza mora biti tipa int", des);
			des.obj = Tab.noObj;
			return;
		}
		des.obj = des.getDesignatorRest().obj;

	}

	@Override
	public void visit(DesignatorArrayName desArrayName) {
		Obj arrObj = Tab.find(desArrayName.getI1());
		if (arrObj == Tab.noObj) {
			report_error("Promenljiva " + desArrayName.getI1() + " nije definisana", desArrayName);
			this.DesignatorBase = Tab.noObj;
			return;
		}
		if (arrObj.getKind() != Obj.Var || arrObj.getType().getKind() != Struct.Array) {
			report_error("Ime " + arrObj.getName() + "= ne označava niz", desArrayName);
			this.DesignatorBase = Tab.noObj;
			return;
		}

		this.DesignatorBase = new Obj(Obj.Elem, arrObj.getName(), arrObj.getType().getElemType());

	}

	/* DesignatorRest */
	@Override
	public void visit(DesignatorRest_dot_ident des) {
	    Obj base = this.DesignatorBase;
	    if (base == null || base == Tab.noObj) {
	        report_error("Neadekvatan operand levo od '.'", des);
	        des.obj = Tab.noObj;
	        return;
	    }

	    String fieldName = des.getFieldName().getI1();
	    Struct t = base.getType();

	    if (base.getKind() == Obj.Type && t.getKind() == Struct.Enum) {
	        Obj member = Tab.noObj;
	        for (Obj o : t.getMembers()) {
	            if (o.getName().equals(fieldName)) {
	                member = o;
	                break;
	            }
	        }
	        if (member == Tab.noObj) {
	            report_error("Enum '" + base.getName() + "' nema polje '" + fieldName + "'.", des);
	            des.obj = Tab.noObj;
	            return;
	        }
	        des.obj = member;
	        this.DesignatorBase = member;
	        return;
	    }



	    report_error("Operator '.' je dozvoljen samo nad enum tipovima.", des);
	    des.obj = Tab.noObj;
	}
	
	@Override
	public void visit(DesignatorRest_dot_length des) {
	    Obj base = this.DesignatorBase;
	    if (base == null || base == Tab.noObj || base.getType().getKind() != Struct.Array) {
	        report_error("length je dozvoljen samo nad nizovima ", des);
	        des.obj = Tab.noObj;
	        return;
	    }
	    Obj len = new Obj(Obj.Con, "length", Tab.intType);
	    des.obj = len;
	    this.DesignatorBase = len;
	}
	
	@Override
	public void visit(DesignatorRest_more_dot_ident des) {
	    Obj base = this.DesignatorBase;
	    if (base == null || base == Tab.noObj) {
	        report_error("Neadekvatan operand levo od '.'", des);
	        des.obj = Tab.noObj;
	        return;
	    }

	    String fieldName = des.getFieldName().getI1();
	    Struct t = base.getType();

	    if (base.getKind() == Obj.Type && t.getKind() == Struct.Enum) {
	        Obj member = Tab.noObj;
	        for (Obj o : t.getMembers()) {
	            if (o.getName().equals(fieldName)) { member = o; break; }
	        }
	        if (member == Tab.noObj) {
	            report_error("Enum '" + base.getName() + "' nema polje '" + fieldName + "'.", des);
	            des.obj = Tab.noObj;
	            return;
	        }
	        des.obj = member;
	        this.DesignatorBase = member;
	        return;
	    }

	    report_error("Operator '.' je dozvoljen samo nad enum tipovima.", des);
	    des.obj = Tab.noObj;
	}

	@Override
	public void visit(DesignatorRest_more_dot_length des) {
	    Obj base = this.DesignatorBase;
	    if (base == null || base == Tab.noObj || base.getType().getKind() != Struct.Array) {
	        report_error("length je dozvoljen samo nad nizovima ", des);
	        des.obj = Tab.noObj;
	        return;
	    }
	    Obj len = new Obj(Obj.Con, "length", Tab.intType);
	    des.obj = len;
	    this.DesignatorBase = len;
	}

	@Override
	public void visit(DesignatorRest_more_index des) {
	    Obj base = this.DesignatorBase;
	    if (base == null || base == Tab.noObj) {
	        report_error("Neadekvatan operand levo od indeksiranja.", des);
	        des.obj = Tab.noObj;
	        return;
	    }
	    if (des.getExpr().struct != Tab.intType) {
	        report_error("Indeks niza mora biti tipa int.", des);
	        des.obj = Tab.noObj;
	        return;
	    }
	    Struct t = base.getType();
	    if (t.getKind() != Struct.Array) {
	        report_error("Indeksiranje je dozvoljeno samo nad nizovima.", des);
	        des.obj = Tab.noObj;
	        return;
	    }
	    Obj elem = new Obj(Obj.Elem, base.getName(), t.getElemType());
	    des.obj = elem;
	    this.DesignatorBase = elem;
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
		factor.struct = Tab.find("bool").getType();
	}

	@Override
	public void visit(FactorDesignator factor) {
		factor.struct = factor.getDesignator().obj.getType();
	}

	@Override
	public void visit(FactorMeth factor) {
		Obj designator = factor.getDesignator().obj;

		if (designator.getKind() != Obj.Meth) {
			report_error("Simbol nije metoda ", factor);
			factor.struct = Tab.noType;
			return;
		}
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

}
