package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.*;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.util.Log4JUtils;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;



public class Compiler {
	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}

	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(Compiler.class);
		Reader br = null;

		try {
			String progName = "test302";
			File sourceCode = new File("test/" + progName+".mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());

			br = new BufferedReader(new FileReader(sourceCode));

			Yylex lexer = new Yylex(br);
			MJParser p = new MJParser(lexer);
			Symbol  s = p.parse();
			
			Program prog = (Program)(s.value);
			
			
			//AST LOG
			log.info(prog.toString(""));
			log.info("=================================");
			
			
			//SYM TABLE
			Tab.init();
			Struct boolType = new Struct(Struct.Bool);
			Obj boolObj = Tab.insert(Obj.Type, "bool", boolType);
			if(boolObj != null) {
				boolObj.setAdr(-1);
				boolObj.setLevel(-1);
			}
			else {
				log.error("Bool object neuspesno kreiran");
				return;
			
			}

			
			//SEMANTIC ANALYSIS
			SemanticAnalyzer analyzer = new SemanticAnalyzer();
			prog.traverseBottomUp(analyzer);
			
			//SYM TABLE LOG
			log.info("=================================");
			Tab.dump();
			
			
			
			if(p.errorDetected || analyzer.errorDetected()) {
				log.error("Parsiranje NIJE uspesno zavrseno");
				return;
			}
			log.info("Parsiranje uspesno zavrseno!");
			
			//CODE GENERATION
			File objFile = new File("test/" + progName+".obj");
			if(objFile.exists()) objFile.delete();
			
			CodeGenerator cg = new CodeGenerator();
			prog.traverseBottomUp(cg);
			Code.dataSize = analyzer.nVars;
			Code.mainPc = cg.getMainPc();
			Code.write(new FileOutputStream(objFile));
			
			log.info("Generisanje koda uspesno zavrseno");

			
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e1) {
					log.error(e1.getMessage(), e1);
				}
		}

	}
}
