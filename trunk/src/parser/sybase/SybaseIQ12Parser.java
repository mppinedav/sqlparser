// $ANTLR 2.7.7 (2006-11-01): "src/parser/sybase/parser_sybase.g" -> "SybaseIQ12Parser.java"$

	package parser.sybase;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class SybaseIQ12Parser extends antlr.LLkParser       implements SybaseIQ12ParserTokenTypes
 {

protected SybaseIQ12Parser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public SybaseIQ12Parser(TokenBuffer tokenBuf) {
  this(tokenBuf,5);
}

protected SybaseIQ12Parser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public SybaseIQ12Parser(TokenStream lexer) {
  this(lexer,5);
}

public SybaseIQ12Parser(ParserSharedInputState state) {
  super(state,5);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void segment() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST segment_AST = null;
		
		{
		switch ( LA(1)) {
		case COLUMN:
		{
			AST tmp363_AST = null;
			tmp363_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp363_AST);
			match(COLUMN);
			column();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case WHERE:
		{
			AST tmp364_AST = null;
			tmp364_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp364_AST);
			match(WHERE);
			search_condition();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(Token.EOF_TYPE);
		segment_AST = (AST)currentAST.root;
		returnAST = segment_AST;
	}
	
	public final void column() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_AST = null;
		Token  all = null;
		AST all_AST = null;
		
		switch ( LA(1)) {
		case 70:
		{
			all = LT(1);
			all_AST = astFactory.create(all);
			astFactory.addASTChild(currentAST, all_AST);
			match(70);
			if ( inputState.guessing==0 ) {
				column_AST = (AST)currentAST.root;
				column_AST=(AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(ALL_FIELDS,all.getText())));
				currentAST.root = column_AST;
				currentAST.child = column_AST!=null &&column_AST.getFirstChild()!=null ?
					column_AST.getFirstChild() : column_AST;
				currentAST.advanceChildToEnd();
			}
			column_AST = (AST)currentAST.root;
			break;
		}
		case STAR:
		{
			AST tmp366_AST = null;
			tmp366_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp366_AST);
			match(STAR);
			if ( inputState.guessing==0 ) {
				column_AST = (AST)currentAST.root;
				column_AST=(AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(ALL_FIELDS,"*")));
				currentAST.root = column_AST;
				currentAST.child = column_AST!=null &&column_AST.getFirstChild()!=null ?
					column_AST.getFirstChild() : column_AST;
				currentAST.advanceChildToEnd();
			}
			column_AST = (AST)currentAST.root;
			break;
		}
		default:
			if ((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2)))) {
				expression_with_aggr_func();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_as:
				case 67:
				{
					{
					switch ( LA(1)) {
					case LITERAL_as:
					{
						AST tmp367_AST = null;
						tmp367_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp367_AST);
						match(LITERAL_as);
						break;
					}
					case 67:
					{
						AST tmp368_AST = null;
						tmp368_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp368_AST);
						match(67);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					alias();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case SEMI:
				case COMMA:
				case LITERAL_where:
				case 42:
				case LITERAL_from:
				case 52:
				case LITERAL_group:
				case 55:
				case LITERAL_order:
				case 57:
				case RPAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				column_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==ID||LA(1)==QUOTED_STRING) && (LA(2)==68||LA(2)==69)) {
				alias();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case 68:
				{
					AST tmp369_AST = null;
					tmp369_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp369_AST);
					match(68);
					break;
				}
				case 69:
				{
					AST tmp370_AST = null;
					tmp370_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp370_AST);
					match(69);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				expression_with_aggr_func();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					column_AST = (AST)currentAST.root;
					column_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ALIAS_EQU,"=")).add(column_AST));
					currentAST.root = column_AST;
					currentAST.child = column_AST!=null &&column_AST.getFirstChild()!=null ?
						column_AST.getFirstChild() : column_AST;
					currentAST.advanceChildToEnd();
				}
				column_AST = (AST)currentAST.root;
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = column_AST;
	}
	
	public final void search_condition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST search_condition_AST = null;
		
		if ((_tokenSet_2.member(LA(1)))) {
			bool_exp();
			astFactory.addASTChild(currentAST, returnAST);
			search_condition_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==LITERAL_not||LA(1)==59)) {
			{
			switch ( LA(1)) {
			case LITERAL_not:
			{
				AST tmp371_AST = null;
				tmp371_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp371_AST);
				match(LITERAL_not);
				if ( inputState.guessing==0 ) {
					search_condition_AST = (AST)currentAST.root;
					search_condition_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SEARCH_NOT_CONDITION,"search_not_condition")).add(search_condition_AST));
					currentAST.root = search_condition_AST;
					currentAST.child = search_condition_AST!=null &&search_condition_AST.getFirstChild()!=null ?
						search_condition_AST.getFirstChild() : search_condition_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			case 59:
			{
				AST tmp372_AST = null;
				tmp372_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp372_AST);
				match(59);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			search_condition();
			astFactory.addASTChild(currentAST, returnAST);
			search_condition_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = search_condition_AST;
	}
	
	public final void statements() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST statements_AST = null;
		
		statement();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5:
		do {
			if ((LA(1)==SEMI)) {
				AST tmp373_AST = null;
				tmp373_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp373_AST);
				match(SEMI);
				statement();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5;
			}
			
		} while (true);
		}
		match(Token.EOF_TYPE);
		statements_AST = (AST)currentAST.root;
		returnAST = statements_AST;
	}
	
	public final void statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST statement_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_t_union:
		case 37:
		{
			tableUnion();
			astFactory.addASTChild(currentAST, returnAST);
			statement_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_t_compare:
		case 40:
		{
			tableCompare();
			astFactory.addASTChild(currentAST, returnAST);
			statement_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_select:
		case 46:
		{
			select_statement();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				statement_AST = (AST)currentAST.root;
				statement_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SELECT_STATEMENT)).add(statement_AST));
				currentAST.root = statement_AST;
				currentAST.child = statement_AST!=null &&statement_AST.getFirstChild()!=null ?
					statement_AST.getFirstChild() : statement_AST;
				currentAST.advanceChildToEnd();
			}
			statement_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = statement_AST;
	}
	
	public final void tableUnion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST tableUnion_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_t_union:
		{
			AST tmp375_AST = null;
			tmp375_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp375_AST);
			match(LITERAL_t_union);
			break;
		}
		case 37:
		{
			AST tmp376_AST = null;
			tmp376_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp376_AST);
			match(37);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		table_lists();
		astFactory.addASTChild(currentAST, returnAST);
		tableUnion_AST = (AST)currentAST.root;
		returnAST = tableUnion_AST;
	}
	
	public final void tableCompare() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST tableCompare_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_t_compare:
		{
			AST tmp377_AST = null;
			tmp377_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp377_AST);
			match(LITERAL_t_compare);
			break;
		}
		case 40:
		{
			AST tmp378_AST = null;
			tmp378_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp378_AST);
			match(40);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		table_name();
		astFactory.addASTChild(currentAST, returnAST);
		match(COMMA);
		table_name();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case LITERAL_where:
		{
			match(LITERAL_where);
			break;
		}
		case 42:
		{
			match(42);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		compare_method();
		astFactory.addASTChild(currentAST, returnAST);
		search_condition();
		astFactory.addASTChild(currentAST, returnAST);
		tableCompare_AST = (AST)currentAST.root;
		returnAST = tableCompare_AST;
	}
	
	public final void select_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST select_statement_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_select:
		{
			AST tmp382_AST = null;
			tmp382_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp382_AST);
			match(LITERAL_select);
			break;
		}
		case 46:
		{
			AST tmp383_AST = null;
			tmp383_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp383_AST);
			match(46);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_distinct:
		{
			AST tmp384_AST = null;
			tmp384_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp384_AST);
			match(LITERAL_distinct);
			break;
		}
		case 48:
		{
			AST tmp385_AST = null;
			tmp385_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp385_AST);
			match(48);
			break;
		}
		default:
			if ((_tokenSet_3.member(LA(1)))) {
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		if ((LA(1)==LITERAL_top||LA(1)==50)) {
			{
			switch ( LA(1)) {
			case LITERAL_top:
			{
				AST tmp386_AST = null;
				tmp386_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp386_AST);
				match(LITERAL_top);
				break;
			}
			case 50:
			{
				AST tmp387_AST = null;
				tmp387_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp387_AST);
				match(50);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			integer();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_4.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		select_list();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case LITERAL_from:
		case 52:
		{
			{
			switch ( LA(1)) {
			case LITERAL_from:
			{
				AST tmp388_AST = null;
				tmp388_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp388_AST);
				match(LITERAL_from);
				break;
			}
			case 52:
			{
				AST tmp389_AST = null;
				tmp389_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp389_AST);
				match(52);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			table_list();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case EOF:
		case SEMI:
		case LITERAL_where:
		case 42:
		case LITERAL_group:
		case 55:
		case LITERAL_order:
		case 57:
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_where:
		case 42:
		{
			{
			switch ( LA(1)) {
			case LITERAL_where:
			{
				AST tmp390_AST = null;
				tmp390_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp390_AST);
				match(LITERAL_where);
				break;
			}
			case 42:
			{
				AST tmp391_AST = null;
				tmp391_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp391_AST);
				match(42);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			search_condition();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case EOF:
		case SEMI:
		case LITERAL_group:
		case 55:
		case LITERAL_order:
		case 57:
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_group:
		case 55:
		{
			{
			switch ( LA(1)) {
			case LITERAL_group:
			{
				AST tmp392_AST = null;
				tmp392_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp392_AST);
				match(LITERAL_group);
				match(LITERAL_by);
				break;
			}
			case 55:
			{
				AST tmp394_AST = null;
				tmp394_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp394_AST);
				match(55);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			aggregate_expression_list();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case EOF:
		case SEMI:
		case LITERAL_order:
		case 57:
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_order:
		case 57:
		{
			{
			switch ( LA(1)) {
			case LITERAL_order:
			{
				AST tmp395_AST = null;
				tmp395_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp395_AST);
				match(LITERAL_order);
				match(LITERAL_by);
				break;
			}
			case 57:
			{
				AST tmp397_AST = null;
				tmp397_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp397_AST);
				match(57);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			order_expression_list();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case EOF:
		case SEMI:
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		select_statement_AST = (AST)currentAST.root;
		returnAST = select_statement_AST;
	}
	
	public final void table_lists() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_lists_AST = null;
		
		table_name();
		astFactory.addASTChild(currentAST, returnAST);
		{
		int _cnt11=0;
		_loop11:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp398_AST = null;
				tmp398_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp398_AST);
				match(COMMA);
				table_name();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				if ( _cnt11>=1 ) { break _loop11; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt11++;
		} while (true);
		}
		table_lists_AST = (AST)currentAST.root;
		returnAST = table_lists_AST;
	}
	
	public final void table_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_name_AST = null;
		
		AST tmp399_AST = null;
		tmp399_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp399_AST);
		match(ID);
		{
		switch ( LA(1)) {
		case LITERAL_as:
		case 67:
		{
			{
			switch ( LA(1)) {
			case LITERAL_as:
			{
				AST tmp400_AST = null;
				tmp400_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp400_AST);
				match(LITERAL_as);
				break;
			}
			case 67:
			{
				AST tmp401_AST = null;
				tmp401_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp401_AST);
				match(67);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			alias();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case EOF:
		case SEMI:
		case COMMA:
		case LITERAL_where:
		case 42:
		case LITERAL_group:
		case 55:
		case LITERAL_order:
		case 57:
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		table_name_AST = (AST)currentAST.root;
		returnAST = table_name_AST;
	}
	
	public final void compare_method() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST compare_method_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_exists:
		case 318:
		case 319:
		{
			comparemethod_name();
			astFactory.addASTChild(currentAST, returnAST);
			compare_method_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_not:
		{
			AST tmp402_AST = null;
			tmp402_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp402_AST);
			match(LITERAL_not);
			AST tmp403_AST = null;
			tmp403_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp403_AST);
			match(LITERAL_exists);
			if ( inputState.guessing==0 ) {
				compare_method_AST = (AST)currentAST.root;
				compare_method_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LOGICAL_NOT_EXISTS,"logic_not_exists")).add(compare_method_AST));
				currentAST.root = compare_method_AST;
				currentAST.child = compare_method_AST!=null &&compare_method_AST.getFirstChild()!=null ?
					compare_method_AST.getFirstChild() : compare_method_AST;
				currentAST.advanceChildToEnd();
			}
			compare_method_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = compare_method_AST;
	}
	
	public final void comparemethod_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST comparemethod_name_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_exists:
		{
			AST tmp404_AST = null;
			tmp404_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp404_AST);
			match(LITERAL_exists);
			comparemethod_name_AST = (AST)currentAST.root;
			break;
		}
		case 318:
		{
			AST tmp405_AST = null;
			tmp405_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp405_AST);
			match(318);
			comparemethod_name_AST = (AST)currentAST.root;
			break;
		}
		case 319:
		{
			AST tmp406_AST = null;
			tmp406_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp406_AST);
			match(319);
			comparemethod_name_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = comparemethod_name_AST;
	}
	
	public final void integer() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST integer_AST = null;
		
		AST tmp407_AST = null;
		tmp407_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp407_AST);
		match(REAL_NUM);
		integer_AST = (AST)currentAST.root;
		returnAST = integer_AST;
	}
	
	public final void select_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST select_list_AST = null;
		
		column();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop32:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp408_AST = null;
				tmp408_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp408_AST);
				match(COMMA);
				column();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop32;
			}
			
		} while (true);
		}
		select_list_AST = (AST)currentAST.root;
		returnAST = select_list_AST;
	}
	
	public final void table_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_list_AST = null;
		
		table_name();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop35:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp409_AST = null;
				tmp409_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp409_AST);
				match(COMMA);
				table_name();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop35;
			}
			
		} while (true);
		}
		table_list_AST = (AST)currentAST.root;
		returnAST = table_list_AST;
	}
	
	public final void aggregate_expression_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST aggregate_expression_list_AST = null;
		
		aggregate_expr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop47:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp410_AST = null;
				tmp410_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp410_AST);
				match(COMMA);
				aggregate_expr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop47;
			}
			
		} while (true);
		}
		aggregate_expression_list_AST = (AST)currentAST.root;
		returnAST = aggregate_expression_list_AST;
	}
	
	public final void order_expression_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST order_expression_list_AST = null;
		
		order_expression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop50:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp411_AST = null;
				tmp411_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp411_AST);
				match(COMMA);
				order_expression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop50;
			}
			
		} while (true);
		}
		order_expression_list_AST = (AST)currentAST.root;
		returnAST = order_expression_list_AST;
	}
	
	public final void bool_exp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST bool_exp_AST = null;
		
		bool_term();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop41:
		do {
			if (((LA(1) >= LITERAL_and && LA(1) <= 63))) {
				{
				switch ( LA(1)) {
				case LITERAL_and:
				{
					AST tmp412_AST = null;
					tmp412_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp412_AST);
					match(LITERAL_and);
					break;
				}
				case LITERAL_or:
				{
					AST tmp413_AST = null;
					tmp413_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp413_AST);
					match(LITERAL_or);
					break;
				}
				case 62:
				{
					AST tmp414_AST = null;
					tmp414_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp414_AST);
					match(62);
					break;
				}
				case 63:
				{
					AST tmp415_AST = null;
					tmp415_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp415_AST);
					match(63);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				bool_term();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop41;
			}
			
		} while (true);
		}
		bool_exp_AST = (AST)currentAST.root;
		returnAST = bool_exp_AST;
	}
	
	public final void bool_term() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST bool_term_AST = null;
		AST exp_AST = null;
		
		boolean synPredMatched44 = false;
		if (((LA(1)==LPAREN) && (_tokenSet_2.member(LA(2))) && (_tokenSet_5.member(LA(3))) && (_tokenSet_6.member(LA(4))) && (_tokenSet_7.member(LA(5))))) {
			int _m44 = mark();
			synPredMatched44 = true;
			inputState.guessing++;
			try {
				{
				match(LPAREN);
				bool_exp();
				match(RPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched44 = false;
			}
			rewind(_m44);
inputState.guessing--;
		}
		if ( synPredMatched44 ) {
			match(LPAREN);
			bool_exp();
			exp_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				bool_term_AST = (AST)currentAST.root;
				bool_term_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LOGIC_BLOCK,"log_block")).add(bool_term_AST));
				currentAST.root = bool_term_AST;
				currentAST.child = bool_term_AST!=null &&bool_term_AST.getFirstChild()!=null ?
					bool_term_AST.getFirstChild() : bool_term_AST;
				currentAST.advanceChildToEnd();
			}
			bool_term_AST = (AST)currentAST.root;
		}
		else if ((_tokenSet_2.member(LA(1))) && (_tokenSet_5.member(LA(2))) && (_tokenSet_8.member(LA(3))) && (_tokenSet_9.member(LA(4))) && (_tokenSet_10.member(LA(5)))) {
			equation();
			astFactory.addASTChild(currentAST, returnAST);
			bool_term_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = bool_term_AST;
	}
	
	public final void equation() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST equation_AST = null;
		
		expression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case 68:
		case 69:
		case LITERAL_like:
		case COMPARE_OP:
		case 309:
		case 310:
		case 311:
		case 312:
		case 313:
		case 314:
		case 315:
		case 316:
		case LEFT_JOIN:
		{
			{
			switch ( LA(1)) {
			case 68:
			{
				AST tmp418_AST = null;
				tmp418_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp418_AST);
				match(68);
				break;
			}
			case 69:
			case LITERAL_like:
			case COMPARE_OP:
			case 309:
			case 310:
			case 311:
			case 312:
			case 313:
			case 314:
			case 315:
			case 316:
			case LEFT_JOIN:
			{
				compare_op();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				equation_AST = (AST)currentAST.root;
				equation_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(COMPARE_OP,"comp_op")).add(equation_AST));
				currentAST.root = equation_AST;
				currentAST.child = equation_AST!=null &&equation_AST.getFirstChild()!=null ?
					equation_AST.getFirstChild() : equation_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		case LITERAL_is:
		case 79:
		case 80:
		{
			{
			switch ( LA(1)) {
			case 79:
			{
				AST tmp419_AST = null;
				tmp419_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp419_AST);
				match(79);
				break;
			}
			case 80:
			{
				AST tmp420_AST = null;
				tmp420_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp420_AST);
				match(80);
				break;
			}
			default:
				if ((LA(1)==LITERAL_is) && (LA(2)==LITERAL_null)) {
					AST tmp421_AST = null;
					tmp421_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp421_AST);
					match(LITERAL_is);
					AST tmp422_AST = null;
					tmp422_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp422_AST);
					match(LITERAL_null);
					if ( inputState.guessing==0 ) {
						equation_AST = (AST)currentAST.root;
						equation_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LOGICAL_NULL,"logic_null")).add(equation_AST));
						currentAST.root = equation_AST;
						currentAST.child = equation_AST!=null &&equation_AST.getFirstChild()!=null ?
							equation_AST.getFirstChild() : equation_AST;
						currentAST.advanceChildToEnd();
					}
				}
				else if ((LA(1)==LITERAL_is) && (LA(2)==LITERAL_not)) {
					AST tmp423_AST = null;
					tmp423_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp423_AST);
					match(LITERAL_is);
					AST tmp424_AST = null;
					tmp424_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp424_AST);
					match(LITERAL_not);
					AST tmp425_AST = null;
					tmp425_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp425_AST);
					match(LITERAL_null);
					if ( inputState.guessing==0 ) {
						equation_AST = (AST)currentAST.root;
						equation_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LOGICAL_NOT_NULL,"logic_not_null")).add(equation_AST));
						currentAST.root = equation_AST;
						currentAST.child = equation_AST!=null &&equation_AST.getFirstChild()!=null ?
							equation_AST.getFirstChild() : equation_AST;
						currentAST.advanceChildToEnd();
					}
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case LITERAL_between:
		case 82:
		{
			{
			switch ( LA(1)) {
			case LITERAL_between:
			{
				AST tmp426_AST = null;
				tmp426_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp426_AST);
				match(LITERAL_between);
				break;
			}
			case 82:
			{
				AST tmp427_AST = null;
				tmp427_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp427_AST);
				match(82);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==LITERAL_and)) {
				match(LITERAL_and);
			}
			else if ((_tokenSet_2.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
			if ((LA(1)==LITERAL_not) && (LA(2)==LITERAL_like)) {
				{
				AST tmp429_AST = null;
				tmp429_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp429_AST);
				match(LITERAL_not);
				AST tmp430_AST = null;
				tmp430_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp430_AST);
				match(LITERAL_like);
				}
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					equation_AST = (AST)currentAST.root;
					equation_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LOGICAL_NOT_LIKE,"logic_not_like")).add(equation_AST));
					currentAST.root = equation_AST;
					currentAST.child = equation_AST!=null &&equation_AST.getFirstChild()!=null ?
						equation_AST.getFirstChild() : equation_AST;
					currentAST.advanceChildToEnd();
				}
			}
			else if ((_tokenSet_11.member(LA(1))) && (LA(2)==LPAREN||LA(2)==LITERAL_in)) {
				{
				switch ( LA(1)) {
				case LITERAL_in:
				{
					AST tmp431_AST = null;
					tmp431_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp431_AST);
					match(LITERAL_in);
					if ( inputState.guessing==0 ) {
						equation_AST = (AST)currentAST.root;
						equation_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LOGICAL_IN,"logic_in")).add(equation_AST));
						currentAST.root = equation_AST;
						currentAST.child = equation_AST!=null &&equation_AST.getFirstChild()!=null ?
							equation_AST.getFirstChild() : equation_AST;
						currentAST.advanceChildToEnd();
					}
					break;
				}
				case LITERAL_not:
				{
					AST tmp432_AST = null;
					tmp432_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp432_AST);
					match(LITERAL_not);
					AST tmp433_AST = null;
					tmp433_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp433_AST);
					match(LITERAL_in);
					if ( inputState.guessing==0 ) {
						equation_AST = (AST)currentAST.root;
						equation_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LOGICAL_NOT_IN,"logic_not_in")).add(equation_AST));
						currentAST.root = equation_AST;
						currentAST.child = equation_AST!=null &&equation_AST.getFirstChild()!=null ?
							equation_AST.getFirstChild() : equation_AST;
						currentAST.advanceChildToEnd();
					}
					break;
				}
				case 84:
				{
					AST tmp434_AST = null;
					tmp434_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp434_AST);
					match(84);
					break;
				}
				case 85:
				{
					AST tmp435_AST = null;
					tmp435_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp435_AST);
					match(85);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				exp_set();
				astFactory.addASTChild(currentAST, returnAST);
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		equation_AST = (AST)currentAST.root;
		returnAST = equation_AST;
	}
	
	public final void aggregate_expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST aggregate_expr_AST = null;
		
		if ((LA(1)==LPAREN) && (_tokenSet_12.member(LA(2))) && (_tokenSet_13.member(LA(3))) && (_tokenSet_14.member(LA(4))) && (_tokenSet_15.member(LA(5)))) {
			AST tmp436_AST = null;
			tmp436_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp436_AST);
			match(LPAREN);
			aggregate_expr();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp437_AST = null;
			tmp437_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp437_AST);
			match(RPAREN);
			aggregate_expr_AST = (AST)currentAST.root;
		}
		else if ((_tokenSet_12.member(LA(1))) && (_tokenSet_16.member(LA(2))) && (_tokenSet_17.member(LA(3))) && (_tokenSet_18.member(LA(4))) && (_tokenSet_19.member(LA(5)))) {
			{
			if ((LA(1)==ID)) {
				field_name();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_20.member(LA(1))) && (_tokenSet_2.member(LA(2))) && (_tokenSet_21.member(LA(3))) && (_tokenSet_22.member(LA(4))) && (_tokenSet_15.member(LA(5)))) {
				function();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_23.member(LA(1))) && (_tokenSet_24.member(LA(2))) && (_tokenSet_25.member(LA(3))) && (_tokenSet_26.member(LA(4))) && (_tokenSet_27.member(LA(5)))) {
				constant();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case STAR:
			case 151:
			case TWO_ARG_OP:
			case MINUS:
			case 301:
			case 302:
			case 303:
			case 304:
			case 305:
			case 306:
			case 307:
			{
				two_arg_op();
				astFactory.addASTChild(currentAST, returnAST);
				aggregate_expr();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					aggregate_expr_AST = (AST)currentAST.root;
					aggregate_expr_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(TWO_ARG_OP,"two_arg_op")).add(aggregate_expr_AST));
					currentAST.root = aggregate_expr_AST;
					currentAST.child = aggregate_expr_AST!=null &&aggregate_expr_AST.getFirstChild()!=null ?
						aggregate_expr_AST.getFirstChild() : aggregate_expr_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			case EOF:
			case SEMI:
			case COMMA:
			case LITERAL_order:
			case 57:
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			aggregate_expr_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = aggregate_expr_AST;
	}
	
	public final void order_expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST order_expression_AST = null;
		
		{
		if ((LA(1)==ID||LA(1)==QUOTED_STRING) && (_tokenSet_28.member(LA(2)))) {
			alias();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((LA(1)==ID) && (LA(2)==POINT)) {
			field_name();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_29.member(LA(1)))) {
			aggregate_func();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_20.member(LA(1)))) {
			function();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		switch ( LA(1)) {
		case 72:
		{
			AST tmp438_AST = null;
			tmp438_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp438_AST);
			match(72);
			break;
		}
		case 73:
		{
			AST tmp439_AST = null;
			tmp439_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp439_AST);
			match(73);
			break;
		}
		case LITERAL_asc:
		{
			AST tmp440_AST = null;
			tmp440_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp440_AST);
			match(LITERAL_asc);
			break;
		}
		case LITERAL_desc:
		{
			AST tmp441_AST = null;
			tmp441_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp441_AST);
			match(LITERAL_desc);
			break;
		}
		case EOF:
		case SEMI:
		case COMMA:
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		order_expression_AST = (AST)currentAST.root;
		returnAST = order_expression_AST;
	}
	
	public final void expression_with_aggr_func() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expression_with_aggr_func_AST = null;
		
		if ((LA(1)==LPAREN) && (_tokenSet_0.member(LA(2))) && (_tokenSet_30.member(LA(3))) && (_tokenSet_31.member(LA(4))) && (_tokenSet_32.member(LA(5)))) {
			AST tmp442_AST = null;
			tmp442_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp442_AST);
			match(LPAREN);
			expression_with_aggr_func();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp443_AST = null;
			tmp443_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp443_AST);
			match(RPAREN);
			{
			switch ( LA(1)) {
			case STAR:
			case 151:
			case TWO_ARG_OP:
			case MINUS:
			case 301:
			case 302:
			case 303:
			case 304:
			case 305:
			case 306:
			case 307:
			{
				two_arg_op();
				astFactory.addASTChild(currentAST, returnAST);
				expression_with_aggr_func();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					expression_with_aggr_func_AST = (AST)currentAST.root;
					expression_with_aggr_func_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(TWO_ARG_OP,"two_arg_op")).add(expression_with_aggr_func_AST));
					currentAST.root = expression_with_aggr_func_AST;
					currentAST.child = expression_with_aggr_func_AST!=null &&expression_with_aggr_func_AST.getFirstChild()!=null ?
						expression_with_aggr_func_AST.getFirstChild() : expression_with_aggr_func_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			case EOF:
			case SEMI:
			case COMMA:
			case LITERAL_where:
			case 42:
			case LITERAL_from:
			case 52:
			case LITERAL_group:
			case 55:
			case LITERAL_order:
			case 57:
			case RPAREN:
			case LITERAL_as:
			case 67:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expression_with_aggr_func_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==ONE_ARG_OP)) {
			one_arg_op();
			astFactory.addASTChild(currentAST, returnAST);
			expression_with_aggr_func();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				expression_with_aggr_func_AST = (AST)currentAST.root;
				expression_with_aggr_func_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ONE_ARG_OP,"one_arg_op")).add(expression_with_aggr_func_AST));
				currentAST.root = expression_with_aggr_func_AST;
				currentAST.child = expression_with_aggr_func_AST!=null &&expression_with_aggr_func_AST.getFirstChild()!=null ?
					expression_with_aggr_func_AST.getFirstChild() : expression_with_aggr_func_AST;
				currentAST.advanceChildToEnd();
			}
			expression_with_aggr_func_AST = (AST)currentAST.root;
		}
		else if ((_tokenSet_33.member(LA(1))) && (_tokenSet_34.member(LA(2))) && (_tokenSet_35.member(LA(3))) && (_tokenSet_36.member(LA(4))) && (_tokenSet_37.member(LA(5)))) {
			{
			switch ( LA(1)) {
			case ID:
			{
				field_name();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case 86:
			case LITERAL_count:
			case LITERAL_avg:
			case 99:
			case LITERAL_max:
			case 101:
			case LITERAL_min:
			case 103:
			case LITERAL_stddev:
			case 105:
			case LITERAL_sum:
			case 107:
			case LITERAL_variance:
			case 109:
			{
				aggregate_func();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
				if ((_tokenSet_23.member(LA(1))) && (_tokenSet_38.member(LA(2))) && (_tokenSet_39.member(LA(3))) && (_tokenSet_40.member(LA(4))) && (_tokenSet_41.member(LA(5)))) {
					constant();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_20.member(LA(1))) && (_tokenSet_2.member(LA(2))) && (_tokenSet_21.member(LA(3))) && (_tokenSet_42.member(LA(4))) && (_tokenSet_43.member(LA(5)))) {
					function();
					astFactory.addASTChild(currentAST, returnAST);
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case STAR:
			case 151:
			case TWO_ARG_OP:
			case MINUS:
			case 301:
			case 302:
			case 303:
			case 304:
			case 305:
			case 306:
			case 307:
			{
				two_arg_op();
				astFactory.addASTChild(currentAST, returnAST);
				expression_with_aggr_func();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					expression_with_aggr_func_AST = (AST)currentAST.root;
					expression_with_aggr_func_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(TWO_ARG_OP,"two_arg_op")).add(expression_with_aggr_func_AST));
					currentAST.root = expression_with_aggr_func_AST;
					currentAST.child = expression_with_aggr_func_AST!=null &&expression_with_aggr_func_AST.getFirstChild()!=null ?
						expression_with_aggr_func_AST.getFirstChild() : expression_with_aggr_func_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			case EOF:
			case SEMI:
			case COMMA:
			case LITERAL_where:
			case 42:
			case LITERAL_from:
			case 52:
			case LITERAL_group:
			case 55:
			case LITERAL_order:
			case 57:
			case RPAREN:
			case LITERAL_as:
			case 67:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expression_with_aggr_func_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = expression_with_aggr_func_AST;
	}
	
	public final void alias() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST alias_AST = null;
		
		switch ( LA(1)) {
		case ID:
		{
			AST tmp444_AST = null;
			tmp444_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp444_AST);
			match(ID);
			alias_AST = (AST)currentAST.root;
			break;
		}
		case QUOTED_STRING:
		{
			AST tmp445_AST = null;
			tmp445_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp445_AST);
			match(QUOTED_STRING);
			alias_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = alias_AST;
	}
	
	public final void field_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST field_name_AST = null;
		
		AST tmp446_AST = null;
		tmp446_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp446_AST);
		match(ID);
		AST tmp447_AST = null;
		tmp447_AST = astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp447_AST);
		match(POINT);
		sfield_name();
		astFactory.addASTChild(currentAST, returnAST);
		field_name_AST = (AST)currentAST.root;
		returnAST = field_name_AST;
	}
	
	public final void function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_pi:
		case 115:
		case LITERAL_now:
		case 117:
		case LITERAL_today:
		case 119:
		{
			star_function();
			astFactory.addASTChild(currentAST, returnAST);
			match(LPAREN);
			match(STAR);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				function_AST = (AST)currentAST.root;
				function_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FUNCTION_STAR_PARAM,"function_star_param")).add(function_AST));
				currentAST.root = function_AST;
				currentAST.child = function_AST!=null &&function_AST.getFirstChild()!=null ?
					function_AST.getFirstChild() : function_AST;
				currentAST.advanceChildToEnd();
			}
			function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_convert:
		case 121:
		{
			datatype_function();
			astFactory.addASTChild(currentAST, returnAST);
			match(LPAREN);
			data_type_parameter();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				function_AST = (AST)currentAST.root;
				function_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FUNCTION_DATA_TYPE,"function_data_type")).add(function_AST));
				currentAST.root = function_AST;
				currentAST.child = function_AST!=null &&function_AST.getFirstChild()!=null ?
					function_AST.getFirstChild() : function_AST;
				currentAST.advanceChildToEnd();
			}
			function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_cast:
		case 123:
		{
			asdatatype_function();
			astFactory.addASTChild(currentAST, returnAST);
			match(LPAREN);
			as_data_type_parameter();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				function_AST = (AST)currentAST.root;
				function_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FUNCTION_AS_DATA_TYPE,"function_as_data_type")).add(function_AST));
				currentAST.root = function_AST;
				currentAST.child = function_AST!=null &&function_AST.getFirstChild()!=null ?
					function_AST.getFirstChild() : function_AST;
				currentAST.advanceChildToEnd();
			}
			function_AST = (AST)currentAST.root;
			break;
		}
		default:
			if (((LA(1) >= LITERAL_getdate && LA(1) <= 113)) && (LA(2)==LPAREN) && (LA(3)==RPAREN) && (_tokenSet_44.member(LA(4))) && (_tokenSet_45.member(LA(5)))) {
				empty_function();
				astFactory.addASTChild(currentAST, returnAST);
				match(LPAREN);
				match(RPAREN);
				if ( inputState.guessing==0 ) {
					function_AST = (AST)currentAST.root;
					function_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FUNCTION_EMPTY_PARAM,"function_empty_param")).add(function_AST));
					currentAST.root = function_AST;
					currentAST.child = function_AST!=null &&function_AST.getFirstChild()!=null ?
						function_AST.getFirstChild() : function_AST;
					currentAST.advanceChildToEnd();
				}
				function_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_46.member(LA(1))) && (_tokenSet_2.member(LA(2))) && (_tokenSet_47.member(LA(3))) && (_tokenSet_48.member(LA(4))) && (_tokenSet_45.member(LA(5)))) {
				function_name();
				astFactory.addASTChild(currentAST, returnAST);
				match(LPAREN);
				parameters();
				astFactory.addASTChild(currentAST, returnAST);
				match(RPAREN);
				if ( inputState.guessing==0 ) {
					function_AST = (AST)currentAST.root;
					function_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FUNCTION,"function_block")).add(function_AST));
					currentAST.root = function_AST;
					currentAST.child = function_AST!=null &&function_AST.getFirstChild()!=null ?
						function_AST.getFirstChild() : function_AST;
					currentAST.advanceChildToEnd();
				}
				function_AST = (AST)currentAST.root;
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = function_AST;
	}
	
	public final void constant() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constant_AST = null;
		
		switch ( LA(1)) {
		case REAL_NUM:
		{
			AST tmp459_AST = null;
			tmp459_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp459_AST);
			match(REAL_NUM);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case NEGATIVE_DIGIT_ELEMENT:
		{
			AST tmp460_AST = null;
			tmp460_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp460_AST);
			match(NEGATIVE_DIGIT_ELEMENT);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case QUOTED_STRING:
		{
			AST tmp461_AST = null;
			tmp461_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp461_AST);
			match(QUOTED_STRING);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_day:
		case LITERAL_hour:
		case LITERAL_minute:
		case LITERAL_month:
		case LITERAL_quarter:
		case LITERAL_second:
		case LITERAL_year:
		case LITERAL_yy:
		case LITERAL_mm:
		case LITERAL_dd:
		case LITERAL_qq:
		case LITERAL_week:
		case LITERAL_wk:
		case LITERAL_dayofyear:
		case LITERAL_dy:
		case LITERAL_weekday:
		case LITERAL_dw:
		case LITERAL_hh:
		case LITERAL_mi:
		case LITERAL_ss:
		case LITERAL_millisecond:
		case LITERAL_ms:
		case LITERAL_calweekofyear:
		case LITERAL_cwk:
		case LITERAL_calyearofweek:
		case LITERAL_cyr:
		case LITERAL_caldayofweek:
		case LITERAL_cdw:
		{
			date_key_word();
			astFactory.addASTChild(currentAST, returnAST);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_null:
		{
			AST tmp462_AST = null;
			tmp462_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp462_AST);
			match(LITERAL_null);
			constant_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = constant_AST;
	}
	
	public final void two_arg_op() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST two_arg_op_AST = null;
		
		switch ( LA(1)) {
		case TWO_ARG_OP:
		{
			AST tmp463_AST = null;
			tmp463_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp463_AST);
			match(TWO_ARG_OP);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case STAR:
		{
			AST tmp464_AST = null;
			tmp464_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp464_AST);
			match(STAR);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case MINUS:
		{
			AST tmp465_AST = null;
			tmp465_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp465_AST);
			match(MINUS);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case 301:
		{
			AST tmp466_AST = null;
			tmp466_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp466_AST);
			match(301);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case 302:
		{
			AST tmp467_AST = null;
			tmp467_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp467_AST);
			match(302);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case 303:
		{
			AST tmp468_AST = null;
			tmp468_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp468_AST);
			match(303);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case 304:
		{
			AST tmp469_AST = null;
			tmp469_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp469_AST);
			match(304);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case 305:
		{
			AST tmp470_AST = null;
			tmp470_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp470_AST);
			match(305);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case 306:
		{
			AST tmp471_AST = null;
			tmp471_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp471_AST);
			match(306);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case 307:
		{
			AST tmp472_AST = null;
			tmp472_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp472_AST);
			match(307);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		case 151:
		{
			AST tmp473_AST = null;
			tmp473_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp473_AST);
			match(151);
			two_arg_op_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = two_arg_op_AST;
	}
	
	public final void aggregate_func() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST aggregate_func_AST = null;
		
		if ((LA(1)==86||LA(1)==LITERAL_count) && (LA(2)==LPAREN) && (LA(3)==STAR)) {
			{
			switch ( LA(1)) {
			case 86:
			{
				AST tmp474_AST = null;
				tmp474_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp474_AST);
				match(86);
				break;
			}
			case LITERAL_count:
			{
				AST tmp475_AST = null;
				tmp475_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp475_AST);
				match(LITERAL_count);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LPAREN);
			match(STAR);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				aggregate_func_AST = (AST)currentAST.root;
				aggregate_func_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FUNCTION_STAR_COUNT,"function_star_count")).add(aggregate_func_AST));
				currentAST.root = aggregate_func_AST;
				currentAST.child = aggregate_func_AST!=null &&aggregate_func_AST.getFirstChild()!=null ?
					aggregate_func_AST.getFirstChild() : aggregate_func_AST;
				currentAST.advanceChildToEnd();
			}
			aggregate_func_AST = (AST)currentAST.root;
		}
		else if ((_tokenSet_29.member(LA(1))) && (LA(2)==LPAREN) && (_tokenSet_49.member(LA(3)))) {
			aggregate_func_name();
			astFactory.addASTChild(currentAST, returnAST);
			match(LPAREN);
			{
			switch ( LA(1)) {
			case LITERAL_all:
			{
				AST tmp480_AST = null;
				tmp480_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp480_AST);
				match(LITERAL_all);
				break;
			}
			case 89:
			{
				AST tmp481_AST = null;
				tmp481_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp481_AST);
				match(89);
				break;
			}
			case LITERAL_distinct:
			{
				AST tmp482_AST = null;
				tmp482_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp482_AST);
				match(LITERAL_distinct);
				break;
			}
			case 48:
			{
				AST tmp483_AST = null;
				tmp483_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp483_AST);
				match(48);
				break;
			}
			default:
				if ((_tokenSet_2.member(LA(1)))) {
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			parameters();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			aggregate_func_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = aggregate_func_AST;
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expression_AST = null;
		
		if ((LA(1)==LPAREN) && (_tokenSet_2.member(LA(2))) && (_tokenSet_13.member(LA(3))) && (_tokenSet_7.member(LA(4))) && (_tokenSet_50.member(LA(5)))) {
			AST tmp485_AST = null;
			tmp485_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp485_AST);
			match(LPAREN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp486_AST = null;
			tmp486_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp486_AST);
			match(RPAREN);
			{
			if ((_tokenSet_51.member(LA(1))) && (_tokenSet_2.member(LA(2))) && (_tokenSet_52.member(LA(3))) && (_tokenSet_50.member(LA(4))) && (_tokenSet_53.member(LA(5)))) {
				two_arg_op();
				astFactory.addASTChild(currentAST, returnAST);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					expression_AST = (AST)currentAST.root;
					expression_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(TWO_ARG_OP,"two_arg_op")).add(expression_AST));
					currentAST.root = expression_AST;
					currentAST.child = expression_AST!=null &&expression_AST.getFirstChild()!=null ?
						expression_AST.getFirstChild() : expression_AST;
					currentAST.advanceChildToEnd();
				}
			}
			else if ((_tokenSet_54.member(LA(1))) && (_tokenSet_50.member(LA(2))) && (_tokenSet_53.member(LA(3))) && (_tokenSet_37.member(LA(4))) && (_tokenSet_37.member(LA(5)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			expression_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==ONE_ARG_OP)) {
			one_arg_op();
			astFactory.addASTChild(currentAST, returnAST);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				expression_AST = (AST)currentAST.root;
				expression_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ONE_ARG_OP,"one_arg_op")).add(expression_AST));
				currentAST.root = expression_AST;
				currentAST.child = expression_AST!=null &&expression_AST.getFirstChild()!=null ?
					expression_AST.getFirstChild() : expression_AST;
				currentAST.advanceChildToEnd();
			}
			expression_AST = (AST)currentAST.root;
		}
		else if ((_tokenSet_55.member(LA(1))) && (_tokenSet_52.member(LA(2))) && (_tokenSet_50.member(LA(3))) && (_tokenSet_53.member(LA(4))) && (_tokenSet_37.member(LA(5)))) {
			{
			switch ( LA(1)) {
			case ID:
			{
				field_name();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case PARAM_ID:
			{
				param_equ();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
				if ((_tokenSet_23.member(LA(1))) && (_tokenSet_56.member(LA(2))) && (_tokenSet_50.member(LA(3))) && (_tokenSet_53.member(LA(4))) && (_tokenSet_37.member(LA(5)))) {
					constant();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_tokenSet_20.member(LA(1))) && (_tokenSet_2.member(LA(2))) && (_tokenSet_21.member(LA(3))) && (_tokenSet_7.member(LA(4))) && (_tokenSet_50.member(LA(5)))) {
					function();
					astFactory.addASTChild(currentAST, returnAST);
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			if ((_tokenSet_51.member(LA(1))) && (_tokenSet_2.member(LA(2))) && (_tokenSet_52.member(LA(3))) && (_tokenSet_50.member(LA(4))) && (_tokenSet_53.member(LA(5)))) {
				two_arg_op();
				astFactory.addASTChild(currentAST, returnAST);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					expression_AST = (AST)currentAST.root;
					expression_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(TWO_ARG_OP,"two_arg_op")).add(expression_AST));
					currentAST.root = expression_AST;
					currentAST.child = expression_AST!=null &&expression_AST.getFirstChild()!=null ?
						expression_AST.getFirstChild() : expression_AST;
					currentAST.advanceChildToEnd();
				}
			}
			else if ((_tokenSet_54.member(LA(1))) && (_tokenSet_50.member(LA(2))) && (_tokenSet_53.member(LA(3))) && (_tokenSet_37.member(LA(4))) && (_tokenSet_37.member(LA(5)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			expression_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = expression_AST;
	}
	
	public final void one_arg_op() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST one_arg_op_AST = null;
		
		AST tmp487_AST = null;
		tmp487_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp487_AST);
		match(ONE_ARG_OP);
		one_arg_op_AST = (AST)currentAST.root;
		returnAST = one_arg_op_AST;
	}
	
	public final void param_equ() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST param_equ_AST = null;
		
		AST tmp488_AST = null;
		tmp488_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp488_AST);
		match(PARAM_ID);
		param_equ_AST = (AST)currentAST.root;
		returnAST = param_equ_AST;
	}
	
	public final void compare_op() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST compare_op_AST = null;
		
		switch ( LA(1)) {
		case COMPARE_OP:
		{
			AST tmp489_AST = null;
			tmp489_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp489_AST);
			match(COMPARE_OP);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case 69:
		{
			AST tmp490_AST = null;
			tmp490_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp490_AST);
			match(69);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case 309:
		{
			AST tmp491_AST = null;
			tmp491_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp491_AST);
			match(309);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case 310:
		{
			AST tmp492_AST = null;
			tmp492_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp492_AST);
			match(310);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case 311:
		{
			AST tmp493_AST = null;
			tmp493_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp493_AST);
			match(311);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case 312:
		{
			AST tmp494_AST = null;
			tmp494_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp494_AST);
			match(312);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case 313:
		{
			AST tmp495_AST = null;
			tmp495_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp495_AST);
			match(313);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case 314:
		{
			AST tmp496_AST = null;
			tmp496_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp496_AST);
			match(314);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case 315:
		{
			AST tmp497_AST = null;
			tmp497_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp497_AST);
			match(315);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_like:
		{
			AST tmp498_AST = null;
			tmp498_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp498_AST);
			match(LITERAL_like);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case 316:
		{
			AST tmp499_AST = null;
			tmp499_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp499_AST);
			match(316);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		case LEFT_JOIN:
		{
			AST tmp500_AST = null;
			tmp500_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp500_AST);
			match(LEFT_JOIN);
			compare_op_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = compare_op_AST;
	}
	
	public final void exp_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exp_set_AST = null;
		
		AST tmp501_AST = null;
		tmp501_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp501_AST);
		match(LPAREN);
		constexpset();
		astFactory.addASTChild(currentAST, returnAST);
		AST tmp502_AST = null;
		tmp502_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp502_AST);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			exp_set_AST = (AST)currentAST.root;
			exp_set_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SUBCONTAIN_OP,"subcontain_op")).add(exp_set_AST));
			currentAST.root = exp_set_AST;
			currentAST.child = exp_set_AST!=null &&exp_set_AST.getFirstChild()!=null ?
				exp_set_AST.getFirstChild() : exp_set_AST;
			currentAST.advanceChildToEnd();
		}
		exp_set_AST = (AST)currentAST.root;
		returnAST = exp_set_AST;
	}
	
	public final void empty_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST empty_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_getdate:
		{
			AST tmp503_AST = null;
			tmp503_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp503_AST);
			match(LITERAL_getdate);
			empty_function_AST = (AST)currentAST.root;
			break;
		}
		case 111:
		{
			AST tmp504_AST = null;
			tmp504_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp504_AST);
			match(111);
			empty_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_rand:
		{
			AST tmp505_AST = null;
			tmp505_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp505_AST);
			match(LITERAL_rand);
			empty_function_AST = (AST)currentAST.root;
			break;
		}
		case 113:
		{
			AST tmp506_AST = null;
			tmp506_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp506_AST);
			match(113);
			empty_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = empty_function_AST;
	}
	
	public final void star_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST star_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_pi:
		{
			AST tmp507_AST = null;
			tmp507_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp507_AST);
			match(LITERAL_pi);
			star_function_AST = (AST)currentAST.root;
			break;
		}
		case 115:
		{
			AST tmp508_AST = null;
			tmp508_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp508_AST);
			match(115);
			star_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_now:
		{
			AST tmp509_AST = null;
			tmp509_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp509_AST);
			match(LITERAL_now);
			star_function_AST = (AST)currentAST.root;
			break;
		}
		case 117:
		{
			AST tmp510_AST = null;
			tmp510_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp510_AST);
			match(117);
			star_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_today:
		{
			AST tmp511_AST = null;
			tmp511_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp511_AST);
			match(LITERAL_today);
			star_function_AST = (AST)currentAST.root;
			break;
		}
		case 119:
		{
			AST tmp512_AST = null;
			tmp512_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp512_AST);
			match(119);
			star_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = star_function_AST;
	}
	
	public final void datatype_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST datatype_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_convert:
		{
			AST tmp513_AST = null;
			tmp513_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp513_AST);
			match(LITERAL_convert);
			datatype_function_AST = (AST)currentAST.root;
			break;
		}
		case 121:
		{
			AST tmp514_AST = null;
			tmp514_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp514_AST);
			match(121);
			datatype_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = datatype_function_AST;
	}
	
	public final void data_type_parameter() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST data_type_parameter_AST = null;
		
		datatype_constant();
		astFactory.addASTChild(currentAST, returnAST);
		{
		int _cnt89=0;
		_loop89:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp515_AST = null;
				tmp515_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp515_AST);
				match(COMMA);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				if ( _cnt89>=1 ) { break _loop89; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt89++;
		} while (true);
		}
		data_type_parameter_AST = (AST)currentAST.root;
		returnAST = data_type_parameter_AST;
	}
	
	public final void asdatatype_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST asdatatype_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_cast:
		{
			AST tmp516_AST = null;
			tmp516_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp516_AST);
			match(LITERAL_cast);
			asdatatype_function_AST = (AST)currentAST.root;
			break;
		}
		case 123:
		{
			AST tmp517_AST = null;
			tmp517_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp517_AST);
			match(123);
			asdatatype_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = asdatatype_function_AST;
	}
	
	public final void as_data_type_parameter() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST as_data_type_parameter_AST = null;
		
		expression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case LITERAL_as:
		{
			match(LITERAL_as);
			break;
		}
		case 90:
		{
			match(90);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		datatype_constant();
		astFactory.addASTChild(currentAST, returnAST);
		}
		as_data_type_parameter_AST = (AST)currentAST.root;
		returnAST = as_data_type_parameter_AST;
	}
	
	public final void function_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST function_name_AST = null;
		
		switch ( LA(1)) {
		case LPAREN:
		{
			function_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_rand:
		case 113:
		case LITERAL_abs:
		case 125:
		case LITERAL_acos:
		case 127:
		case LITERAL_asin:
		case 129:
		case LITERAL_atan:
		case 131:
		case 132:
		case 133:
		case LITERAL_ceiling:
		case 135:
		case LITERAL_cos:
		case 137:
		case LITERAL_cot:
		case 139:
		case LITERAL_degrees:
		case 141:
		case LITERAL_exp:
		case 143:
		case LITERAL_floor:
		case 145:
		case LITERAL_log:
		case 147:
		case 148:
		case 149:
		case LITERAL_mod:
		case 151:
		case LITERAL_power:
		case 153:
		case LITERAL_radians:
		case 155:
		case LITERAL_remainder:
		case 157:
		case LITERAL_round:
		case 159:
		case LITERAL_sign:
		case 161:
		case LITERAL_sin:
		case 163:
		case LITERAL_sqrt:
		case 165:
		case LITERAL_tan:
		case 167:
		case 168:
		case LITERAL_truncnum:
		case 170:
		{
			number_function();
			astFactory.addASTChild(currentAST, returnAST);
			function_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_char:
		case LITERAL_ascii:
		case 172:
		case LITERAL_bit_length:
		case 174:
		case LITERAL_byte_length:
		case 176:
		case 177:
		case LITERAL_char_length:
		case 179:
		case LITERAL_charindex:
		case 181:
		case LITERAL_difference:
		case 183:
		case LITERAL_insertstr:
		case 185:
		case LITERAL_lcase:
		case 187:
		case LITERAL_left:
		case 189:
		case LITERAL_length:
		case 191:
		case LITERAL_locate:
		case 193:
		case LITERAL_lower:
		case 195:
		case LITERAL_ltrim:
		case 197:
		case LITERAL_octet_length:
		case 199:
		case LITERAL_patindex:
		case 201:
		case LITERAL_repeat:
		case 203:
		case LITERAL_replace:
		case 205:
		case LITERAL_replicate:
		case 207:
		case LITERAL_right:
		case 209:
		case LITERAL_rtrim:
		case 211:
		case LITERAL_similar:
		case 213:
		case LITERAL_sortkey:
		case 215:
		case LITERAL_soundex:
		case 217:
		case LITERAL_space:
		case 219:
		case LITERAL_str:
		case 221:
		case LITERAL_string:
		case 223:
		case LITERAL_stuff:
		case 225:
		case LITERAL_substring:
		case 227:
		case LITERAL_trim:
		case 229:
		case LITERAL_ucase:
		case 231:
		case LITERAL_upper:
		case 233:
		{
			string_function();
			astFactory.addASTChild(currentAST, returnAST);
			function_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_dateformat:
		case 235:
		case LITERAL_datename:
		case 237:
		case LITERAL_datepart:
		case 239:
		case LITERAL_datetime:
		case 241:
		case LITERAL_date:
		case 243:
		case LITERAL_dayname:
		case 245:
		case LITERAL_days:
		case 247:
		case LITERAL_day:
		case 249:
		case LITERAL_dow:
		case 251:
		case LITERAL_hours:
		case 253:
		case LITERAL_hour:
		case 255:
		case LITERAL_minutes:
		case 257:
		case LITERAL_minute:
		case 259:
		case LITERAL_monthname:
		case 261:
		case LITERAL_months:
		case 263:
		case LITERAL_month:
		case 265:
		case LITERAL_quarter:
		case 267:
		case LITERAL_seconds:
		case 269:
		case LITERAL_second:
		case 271:
		case LITERAL_weeks:
		case 273:
		case LITERAL_years:
		case 275:
		case LITERAL_year:
		case 277:
		case LITERAL_ymd:
		case 279:
		case LITERAL_dateadd:
		case 281:
		case LITERAL_datediff:
		case 283:
		{
			datetime_function();
			astFactory.addASTChild(currentAST, returnAST);
			function_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_hextoint:
		case 285:
		case LITERAL_inttohex:
		case 287:
		case LITERAL_isdate:
		case 289:
		case LITERAL_isnumeric:
		case 291:
		{
			conversion_function();
			astFactory.addASTChild(currentAST, returnAST);
			function_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_suser_id:
		case LITERAL_suser_name:
		case LITERAL_user_id:
		case LITERAL_user_name:
		{
			system_function();
			astFactory.addASTChild(currentAST, returnAST);
			function_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_argn:
		case LITERAL_rowid:
		{
			other_function();
			astFactory.addASTChild(currentAST, returnAST);
			function_name_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = function_name_AST;
	}
	
	public final void parameters() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parameters_AST = null;
		
		expression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop83:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp520_AST = null;
				tmp520_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp520_AST);
				match(COMMA);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop83;
			}
			
		} while (true);
		}
		parameters_AST = (AST)currentAST.root;
		returnAST = parameters_AST;
	}
	
	public final void aggregate_func_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST aggregate_func_name_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_avg:
		{
			AST tmp521_AST = null;
			tmp521_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp521_AST);
			match(LITERAL_avg);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case 99:
		{
			AST tmp522_AST = null;
			tmp522_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp522_AST);
			match(99);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_count:
		{
			AST tmp523_AST = null;
			tmp523_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp523_AST);
			match(LITERAL_count);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case 86:
		{
			AST tmp524_AST = null;
			tmp524_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp524_AST);
			match(86);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_max:
		{
			AST tmp525_AST = null;
			tmp525_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp525_AST);
			match(LITERAL_max);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case 101:
		{
			AST tmp526_AST = null;
			tmp526_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp526_AST);
			match(101);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_min:
		{
			AST tmp527_AST = null;
			tmp527_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp527_AST);
			match(LITERAL_min);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case 103:
		{
			AST tmp528_AST = null;
			tmp528_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp528_AST);
			match(103);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_stddev:
		{
			AST tmp529_AST = null;
			tmp529_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp529_AST);
			match(LITERAL_stddev);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case 105:
		{
			AST tmp530_AST = null;
			tmp530_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp530_AST);
			match(105);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_sum:
		{
			AST tmp531_AST = null;
			tmp531_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp531_AST);
			match(LITERAL_sum);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case 107:
		{
			AST tmp532_AST = null;
			tmp532_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp532_AST);
			match(107);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_variance:
		{
			AST tmp533_AST = null;
			tmp533_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp533_AST);
			match(LITERAL_variance);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		case 109:
		{
			AST tmp534_AST = null;
			tmp534_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp534_AST);
			match(109);
			aggregate_func_name_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = aggregate_func_name_AST;
	}
	
	public final void datatype_constant() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST datatype_constant_AST = null;
		
		if ((_tokenSet_57.member(LA(1)))) {
			data_type_word();
			astFactory.addASTChild(currentAST, returnAST);
			datatype_constant_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==LITERAL_char) && (LA(2)==COMMA||LA(2)==RPAREN)) {
			AST tmp535_AST = null;
			tmp535_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp535_AST);
			match(LITERAL_char);
			datatype_constant_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==LITERAL_char) && (LA(2)==LPAREN)) {
			AST tmp536_AST = null;
			tmp536_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp536_AST);
			match(LITERAL_char);
			match(LPAREN);
			datatype_precision_or_scale_or_maxlength();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				datatype_constant_AST = (AST)currentAST.root;
				datatype_constant_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PAREN_CHAR_DATA_TYPE,"paren_char_data_type")).add(datatype_constant_AST));
				currentAST.root = datatype_constant_AST;
				currentAST.child = datatype_constant_AST!=null &&datatype_constant_AST.getFirstChild()!=null ?
					datatype_constant_AST.getFirstChild() : datatype_constant_AST;
				currentAST.advanceChildToEnd();
			}
			datatype_constant_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==DATA_TYPE_STRING) && (LA(2)==LPAREN)) {
			AST tmp539_AST = null;
			tmp539_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp539_AST);
			match(DATA_TYPE_STRING);
			match(LPAREN);
			datatype_precision_or_scale_or_maxlength();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				datatype_constant_AST = (AST)currentAST.root;
				datatype_constant_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PAREN_DATA_TYPE,"paren_data_type")).add(datatype_constant_AST));
				currentAST.root = datatype_constant_AST;
				currentAST.child = datatype_constant_AST!=null &&datatype_constant_AST.getFirstChild()!=null ?
					datatype_constant_AST.getFirstChild() : datatype_constant_AST;
				currentAST.advanceChildToEnd();
			}
			datatype_constant_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==DATA_TYPE_STRING) && (LA(2)==COMMA||LA(2)==RPAREN)) {
			AST tmp542_AST = null;
			tmp542_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp542_AST);
			match(DATA_TYPE_STRING);
			datatype_constant_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = datatype_constant_AST;
	}
	
	public final void data_type_word() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST data_type_word_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_uniqueidentifierstr:
		{
			AST tmp543_AST = null;
			tmp543_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp543_AST);
			match(LITERAL_uniqueidentifierstr);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_bigint:
		{
			AST tmp544_AST = null;
			tmp544_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp544_AST);
			match(LITERAL_bigint);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_int:
		{
			AST tmp545_AST = null;
			tmp545_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp545_AST);
			match(LITERAL_int);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_integer:
		{
			AST tmp546_AST = null;
			tmp546_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp546_AST);
			match(LITERAL_integer);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_smallint:
		{
			AST tmp547_AST = null;
			tmp547_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp547_AST);
			match(LITERAL_smallint);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_tinyint:
		{
			AST tmp548_AST = null;
			tmp548_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp548_AST);
			match(LITERAL_tinyint);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_double:
		{
			AST tmp549_AST = null;
			tmp549_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp549_AST);
			match(LITERAL_double);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_real:
		{
			AST tmp550_AST = null;
			tmp550_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp550_AST);
			match(LITERAL_real);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_date:
		{
			AST tmp551_AST = null;
			tmp551_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp551_AST);
			match(LITERAL_date);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_datetime:
		{
			AST tmp552_AST = null;
			tmp552_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp552_AST);
			match(LITERAL_datetime);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_smalldatetime:
		{
			AST tmp553_AST = null;
			tmp553_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp553_AST);
			match(LITERAL_smalldatetime);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_time:
		{
			AST tmp554_AST = null;
			tmp554_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp554_AST);
			match(LITERAL_time);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_timestamp:
		{
			AST tmp555_AST = null;
			tmp555_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp555_AST);
			match(LITERAL_timestamp);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_bit:
		{
			AST tmp556_AST = null;
			tmp556_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp556_AST);
			match(LITERAL_bit);
			data_type_word_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = data_type_word_AST;
	}
	
	public final void datatype_precision_or_scale_or_maxlength() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST datatype_precision_or_scale_or_maxlength_AST = null;
		
		if ((LA(1)==REAL_NUM) && (LA(2)==COMMA)) {
			AST tmp557_AST = null;
			tmp557_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp557_AST);
			match(REAL_NUM);
			AST tmp558_AST = null;
			tmp558_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp558_AST);
			match(COMMA);
			AST tmp559_AST = null;
			tmp559_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp559_AST);
			match(REAL_NUM);
			datatype_precision_or_scale_or_maxlength_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==REAL_NUM) && (LA(2)==RPAREN)) {
			AST tmp560_AST = null;
			tmp560_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp560_AST);
			match(REAL_NUM);
			datatype_precision_or_scale_or_maxlength_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = datatype_precision_or_scale_or_maxlength_AST;
	}
	
	public final void constexpset() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constexpset_AST = null;
		
		constant();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop98:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp561_AST = null;
				tmp561_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp561_AST);
				match(COMMA);
				constant();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop98;
			}
			
		} while (true);
		}
		constexpset_AST = (AST)currentAST.root;
		returnAST = constexpset_AST;
	}
	
	public final void subquery() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST subquery_AST = null;
		
		AST tmp562_AST = null;
		tmp562_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp562_AST);
		match(LPAREN);
		select_statement();
		astFactory.addASTChild(currentAST, returnAST);
		AST tmp563_AST = null;
		tmp563_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp563_AST);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			subquery_AST = (AST)currentAST.root;
			subquery_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SUBQUERY,"subquery")).add(subquery_AST));
			currentAST.root = subquery_AST;
			currentAST.child = subquery_AST!=null &&subquery_AST.getFirstChild()!=null ?
				subquery_AST.getFirstChild() : subquery_AST;
			currentAST.advanceChildToEnd();
		}
		subquery_AST = (AST)currentAST.root;
		returnAST = subquery_AST;
	}
	
	public final void sfield_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sfield_name_AST = null;
		
		if ((LA(1)==ID) && (LA(2)==LPAREN) && (LA(3)==ID) && (LA(4)==RPAREN) && (_tokenSet_44.member(LA(5)))) {
			AST tmp564_AST = null;
			tmp564_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp564_AST);
			match(ID);
			match(LPAREN);
			AST tmp566_AST = null;
			tmp566_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp566_AST);
			match(ID);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				sfield_name_AST = (AST)currentAST.root;
				sfield_name_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PAREN_FIELD,"paren_field")).add(sfield_name_AST));
				currentAST.root = sfield_name_AST;
				currentAST.child = sfield_name_AST!=null &&sfield_name_AST.getFirstChild()!=null ?
					sfield_name_AST.getFirstChild() : sfield_name_AST;
				currentAST.advanceChildToEnd();
			}
			sfield_name_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==ID) && (_tokenSet_44.member(LA(2))) && (_tokenSet_45.member(LA(3))) && (_tokenSet_53.member(LA(4))) && (_tokenSet_37.member(LA(5)))) {
			AST tmp568_AST = null;
			tmp568_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp568_AST);
			match(ID);
			sfield_name_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = sfield_name_AST;
	}
	
	public final void date_key_word() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST date_key_word_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_year:
		{
			AST tmp569_AST = null;
			tmp569_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp569_AST);
			match(LITERAL_year);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_yy:
		{
			AST tmp570_AST = null;
			tmp570_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp570_AST);
			match(LITERAL_yy);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_month:
		{
			AST tmp571_AST = null;
			tmp571_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp571_AST);
			match(LITERAL_month);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_mm:
		{
			AST tmp572_AST = null;
			tmp572_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp572_AST);
			match(LITERAL_mm);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_day:
		{
			AST tmp573_AST = null;
			tmp573_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp573_AST);
			match(LITERAL_day);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_dd:
		{
			AST tmp574_AST = null;
			tmp574_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp574_AST);
			match(LITERAL_dd);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_quarter:
		{
			AST tmp575_AST = null;
			tmp575_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp575_AST);
			match(LITERAL_quarter);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_qq:
		{
			AST tmp576_AST = null;
			tmp576_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp576_AST);
			match(LITERAL_qq);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_week:
		{
			AST tmp577_AST = null;
			tmp577_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp577_AST);
			match(LITERAL_week);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_wk:
		{
			AST tmp578_AST = null;
			tmp578_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp578_AST);
			match(LITERAL_wk);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_dayofyear:
		{
			AST tmp579_AST = null;
			tmp579_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp579_AST);
			match(LITERAL_dayofyear);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_dy:
		{
			AST tmp580_AST = null;
			tmp580_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp580_AST);
			match(LITERAL_dy);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_weekday:
		{
			AST tmp581_AST = null;
			tmp581_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp581_AST);
			match(LITERAL_weekday);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_dw:
		{
			AST tmp582_AST = null;
			tmp582_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp582_AST);
			match(LITERAL_dw);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_hour:
		{
			AST tmp583_AST = null;
			tmp583_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp583_AST);
			match(LITERAL_hour);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_hh:
		{
			AST tmp584_AST = null;
			tmp584_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp584_AST);
			match(LITERAL_hh);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_minute:
		{
			AST tmp585_AST = null;
			tmp585_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp585_AST);
			match(LITERAL_minute);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_mi:
		{
			AST tmp586_AST = null;
			tmp586_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp586_AST);
			match(LITERAL_mi);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_second:
		{
			AST tmp587_AST = null;
			tmp587_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp587_AST);
			match(LITERAL_second);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_ss:
		{
			AST tmp588_AST = null;
			tmp588_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp588_AST);
			match(LITERAL_ss);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_millisecond:
		{
			AST tmp589_AST = null;
			tmp589_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp589_AST);
			match(LITERAL_millisecond);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_ms:
		{
			AST tmp590_AST = null;
			tmp590_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp590_AST);
			match(LITERAL_ms);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_calweekofyear:
		{
			AST tmp591_AST = null;
			tmp591_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp591_AST);
			match(LITERAL_calweekofyear);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_cwk:
		{
			AST tmp592_AST = null;
			tmp592_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp592_AST);
			match(LITERAL_cwk);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_calyearofweek:
		{
			AST tmp593_AST = null;
			tmp593_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp593_AST);
			match(LITERAL_calyearofweek);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_cyr:
		{
			AST tmp594_AST = null;
			tmp594_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp594_AST);
			match(LITERAL_cyr);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_caldayofweek:
		{
			AST tmp595_AST = null;
			tmp595_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp595_AST);
			match(LITERAL_caldayofweek);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_cdw:
		{
			AST tmp596_AST = null;
			tmp596_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp596_AST);
			match(LITERAL_cdw);
			date_key_word_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = date_key_word_AST;
	}
	
	public final void number_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST number_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_abs:
		{
			AST tmp597_AST = null;
			tmp597_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp597_AST);
			match(LITERAL_abs);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 125:
		{
			AST tmp598_AST = null;
			tmp598_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp598_AST);
			match(125);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_acos:
		{
			AST tmp599_AST = null;
			tmp599_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp599_AST);
			match(LITERAL_acos);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 127:
		{
			AST tmp600_AST = null;
			tmp600_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp600_AST);
			match(127);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_asin:
		{
			AST tmp601_AST = null;
			tmp601_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp601_AST);
			match(LITERAL_asin);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 129:
		{
			AST tmp602_AST = null;
			tmp602_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp602_AST);
			match(129);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_atan:
		{
			AST tmp603_AST = null;
			tmp603_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp603_AST);
			match(LITERAL_atan);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 131:
		{
			AST tmp604_AST = null;
			tmp604_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp604_AST);
			match(131);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 132:
		{
			AST tmp605_AST = null;
			tmp605_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp605_AST);
			match(132);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 133:
		{
			AST tmp606_AST = null;
			tmp606_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp606_AST);
			match(133);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_ceiling:
		{
			AST tmp607_AST = null;
			tmp607_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp607_AST);
			match(LITERAL_ceiling);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 135:
		{
			AST tmp608_AST = null;
			tmp608_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp608_AST);
			match(135);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_cos:
		{
			AST tmp609_AST = null;
			tmp609_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp609_AST);
			match(LITERAL_cos);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 137:
		{
			AST tmp610_AST = null;
			tmp610_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp610_AST);
			match(137);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_cot:
		{
			AST tmp611_AST = null;
			tmp611_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp611_AST);
			match(LITERAL_cot);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 139:
		{
			AST tmp612_AST = null;
			tmp612_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp612_AST);
			match(139);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_degrees:
		{
			AST tmp613_AST = null;
			tmp613_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp613_AST);
			match(LITERAL_degrees);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 141:
		{
			AST tmp614_AST = null;
			tmp614_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp614_AST);
			match(141);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_exp:
		{
			AST tmp615_AST = null;
			tmp615_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp615_AST);
			match(LITERAL_exp);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 143:
		{
			AST tmp616_AST = null;
			tmp616_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp616_AST);
			match(143);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_floor:
		{
			AST tmp617_AST = null;
			tmp617_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp617_AST);
			match(LITERAL_floor);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 145:
		{
			AST tmp618_AST = null;
			tmp618_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp618_AST);
			match(145);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_log:
		{
			AST tmp619_AST = null;
			tmp619_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp619_AST);
			match(LITERAL_log);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 147:
		{
			AST tmp620_AST = null;
			tmp620_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp620_AST);
			match(147);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 148:
		{
			AST tmp621_AST = null;
			tmp621_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp621_AST);
			match(148);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 149:
		{
			AST tmp622_AST = null;
			tmp622_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp622_AST);
			match(149);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_mod:
		{
			AST tmp623_AST = null;
			tmp623_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp623_AST);
			match(LITERAL_mod);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 151:
		{
			AST tmp624_AST = null;
			tmp624_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp624_AST);
			match(151);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_power:
		{
			AST tmp625_AST = null;
			tmp625_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp625_AST);
			match(LITERAL_power);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 153:
		{
			AST tmp626_AST = null;
			tmp626_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp626_AST);
			match(153);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_radians:
		{
			AST tmp627_AST = null;
			tmp627_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp627_AST);
			match(LITERAL_radians);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 155:
		{
			AST tmp628_AST = null;
			tmp628_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp628_AST);
			match(155);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_rand:
		{
			AST tmp629_AST = null;
			tmp629_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp629_AST);
			match(LITERAL_rand);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 113:
		{
			AST tmp630_AST = null;
			tmp630_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp630_AST);
			match(113);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_remainder:
		{
			AST tmp631_AST = null;
			tmp631_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp631_AST);
			match(LITERAL_remainder);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 157:
		{
			AST tmp632_AST = null;
			tmp632_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp632_AST);
			match(157);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_round:
		{
			AST tmp633_AST = null;
			tmp633_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp633_AST);
			match(LITERAL_round);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 159:
		{
			AST tmp634_AST = null;
			tmp634_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp634_AST);
			match(159);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_sign:
		{
			AST tmp635_AST = null;
			tmp635_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp635_AST);
			match(LITERAL_sign);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 161:
		{
			AST tmp636_AST = null;
			tmp636_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp636_AST);
			match(161);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_sin:
		{
			AST tmp637_AST = null;
			tmp637_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp637_AST);
			match(LITERAL_sin);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 163:
		{
			AST tmp638_AST = null;
			tmp638_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp638_AST);
			match(163);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_sqrt:
		{
			AST tmp639_AST = null;
			tmp639_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp639_AST);
			match(LITERAL_sqrt);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 165:
		{
			AST tmp640_AST = null;
			tmp640_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp640_AST);
			match(165);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_tan:
		{
			AST tmp641_AST = null;
			tmp641_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp641_AST);
			match(LITERAL_tan);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 167:
		{
			AST tmp642_AST = null;
			tmp642_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp642_AST);
			match(167);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 168:
		{
			AST tmp643_AST = null;
			tmp643_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp643_AST);
			match(168);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_truncnum:
		{
			AST tmp644_AST = null;
			tmp644_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp644_AST);
			match(LITERAL_truncnum);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		case 170:
		{
			AST tmp645_AST = null;
			tmp645_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp645_AST);
			match(170);
			number_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = number_function_AST;
	}
	
	public final void string_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST string_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_ascii:
		{
			AST tmp646_AST = null;
			tmp646_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp646_AST);
			match(LITERAL_ascii);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 172:
		{
			AST tmp647_AST = null;
			tmp647_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp647_AST);
			match(172);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_bit_length:
		{
			AST tmp648_AST = null;
			tmp648_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp648_AST);
			match(LITERAL_bit_length);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 174:
		{
			AST tmp649_AST = null;
			tmp649_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp649_AST);
			match(174);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_byte_length:
		{
			AST tmp650_AST = null;
			tmp650_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp650_AST);
			match(LITERAL_byte_length);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 176:
		{
			AST tmp651_AST = null;
			tmp651_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp651_AST);
			match(176);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_char:
		{
			AST tmp652_AST = null;
			tmp652_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp652_AST);
			match(LITERAL_char);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 177:
		{
			AST tmp653_AST = null;
			tmp653_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp653_AST);
			match(177);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_char_length:
		{
			AST tmp654_AST = null;
			tmp654_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp654_AST);
			match(LITERAL_char_length);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 179:
		{
			AST tmp655_AST = null;
			tmp655_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp655_AST);
			match(179);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_charindex:
		{
			AST tmp656_AST = null;
			tmp656_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp656_AST);
			match(LITERAL_charindex);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 181:
		{
			AST tmp657_AST = null;
			tmp657_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp657_AST);
			match(181);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_difference:
		{
			AST tmp658_AST = null;
			tmp658_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp658_AST);
			match(LITERAL_difference);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 183:
		{
			AST tmp659_AST = null;
			tmp659_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp659_AST);
			match(183);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_insertstr:
		{
			AST tmp660_AST = null;
			tmp660_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp660_AST);
			match(LITERAL_insertstr);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 185:
		{
			AST tmp661_AST = null;
			tmp661_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp661_AST);
			match(185);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_lcase:
		{
			AST tmp662_AST = null;
			tmp662_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp662_AST);
			match(LITERAL_lcase);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 187:
		{
			AST tmp663_AST = null;
			tmp663_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp663_AST);
			match(187);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_left:
		{
			AST tmp664_AST = null;
			tmp664_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp664_AST);
			match(LITERAL_left);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 189:
		{
			AST tmp665_AST = null;
			tmp665_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp665_AST);
			match(189);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_length:
		{
			AST tmp666_AST = null;
			tmp666_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp666_AST);
			match(LITERAL_length);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 191:
		{
			AST tmp667_AST = null;
			tmp667_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp667_AST);
			match(191);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_locate:
		{
			AST tmp668_AST = null;
			tmp668_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp668_AST);
			match(LITERAL_locate);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 193:
		{
			AST tmp669_AST = null;
			tmp669_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp669_AST);
			match(193);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_lower:
		{
			AST tmp670_AST = null;
			tmp670_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp670_AST);
			match(LITERAL_lower);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 195:
		{
			AST tmp671_AST = null;
			tmp671_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp671_AST);
			match(195);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_ltrim:
		{
			AST tmp672_AST = null;
			tmp672_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp672_AST);
			match(LITERAL_ltrim);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 197:
		{
			AST tmp673_AST = null;
			tmp673_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp673_AST);
			match(197);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_octet_length:
		{
			AST tmp674_AST = null;
			tmp674_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp674_AST);
			match(LITERAL_octet_length);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 199:
		{
			AST tmp675_AST = null;
			tmp675_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp675_AST);
			match(199);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_patindex:
		{
			AST tmp676_AST = null;
			tmp676_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp676_AST);
			match(LITERAL_patindex);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 201:
		{
			AST tmp677_AST = null;
			tmp677_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp677_AST);
			match(201);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_repeat:
		{
			AST tmp678_AST = null;
			tmp678_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp678_AST);
			match(LITERAL_repeat);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 203:
		{
			AST tmp679_AST = null;
			tmp679_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp679_AST);
			match(203);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_replace:
		{
			AST tmp680_AST = null;
			tmp680_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp680_AST);
			match(LITERAL_replace);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 205:
		{
			AST tmp681_AST = null;
			tmp681_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp681_AST);
			match(205);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_replicate:
		{
			AST tmp682_AST = null;
			tmp682_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp682_AST);
			match(LITERAL_replicate);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 207:
		{
			AST tmp683_AST = null;
			tmp683_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp683_AST);
			match(207);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_right:
		{
			AST tmp684_AST = null;
			tmp684_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp684_AST);
			match(LITERAL_right);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 209:
		{
			AST tmp685_AST = null;
			tmp685_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp685_AST);
			match(209);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_rtrim:
		{
			AST tmp686_AST = null;
			tmp686_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp686_AST);
			match(LITERAL_rtrim);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 211:
		{
			AST tmp687_AST = null;
			tmp687_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp687_AST);
			match(211);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_similar:
		{
			AST tmp688_AST = null;
			tmp688_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp688_AST);
			match(LITERAL_similar);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 213:
		{
			AST tmp689_AST = null;
			tmp689_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp689_AST);
			match(213);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_sortkey:
		{
			AST tmp690_AST = null;
			tmp690_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp690_AST);
			match(LITERAL_sortkey);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 215:
		{
			AST tmp691_AST = null;
			tmp691_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp691_AST);
			match(215);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_soundex:
		{
			AST tmp692_AST = null;
			tmp692_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp692_AST);
			match(LITERAL_soundex);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 217:
		{
			AST tmp693_AST = null;
			tmp693_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp693_AST);
			match(217);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_space:
		{
			AST tmp694_AST = null;
			tmp694_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp694_AST);
			match(LITERAL_space);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 219:
		{
			AST tmp695_AST = null;
			tmp695_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp695_AST);
			match(219);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_str:
		{
			AST tmp696_AST = null;
			tmp696_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp696_AST);
			match(LITERAL_str);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 221:
		{
			AST tmp697_AST = null;
			tmp697_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp697_AST);
			match(221);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_string:
		{
			AST tmp698_AST = null;
			tmp698_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp698_AST);
			match(LITERAL_string);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 223:
		{
			AST tmp699_AST = null;
			tmp699_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp699_AST);
			match(223);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_stuff:
		{
			AST tmp700_AST = null;
			tmp700_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp700_AST);
			match(LITERAL_stuff);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 225:
		{
			AST tmp701_AST = null;
			tmp701_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp701_AST);
			match(225);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_substring:
		{
			AST tmp702_AST = null;
			tmp702_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp702_AST);
			match(LITERAL_substring);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 227:
		{
			AST tmp703_AST = null;
			tmp703_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp703_AST);
			match(227);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_trim:
		{
			AST tmp704_AST = null;
			tmp704_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp704_AST);
			match(LITERAL_trim);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 229:
		{
			AST tmp705_AST = null;
			tmp705_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp705_AST);
			match(229);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_ucase:
		{
			AST tmp706_AST = null;
			tmp706_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp706_AST);
			match(LITERAL_ucase);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 231:
		{
			AST tmp707_AST = null;
			tmp707_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp707_AST);
			match(231);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_upper:
		{
			AST tmp708_AST = null;
			tmp708_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp708_AST);
			match(LITERAL_upper);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		case 233:
		{
			AST tmp709_AST = null;
			tmp709_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp709_AST);
			match(233);
			string_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = string_function_AST;
	}
	
	public final void datetime_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST datetime_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_dateformat:
		{
			AST tmp710_AST = null;
			tmp710_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp710_AST);
			match(LITERAL_dateformat);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 235:
		{
			AST tmp711_AST = null;
			tmp711_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp711_AST);
			match(235);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_datename:
		{
			AST tmp712_AST = null;
			tmp712_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp712_AST);
			match(LITERAL_datename);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 237:
		{
			AST tmp713_AST = null;
			tmp713_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp713_AST);
			match(237);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_datepart:
		{
			AST tmp714_AST = null;
			tmp714_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp714_AST);
			match(LITERAL_datepart);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 239:
		{
			AST tmp715_AST = null;
			tmp715_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp715_AST);
			match(239);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_datetime:
		{
			AST tmp716_AST = null;
			tmp716_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp716_AST);
			match(LITERAL_datetime);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 241:
		{
			AST tmp717_AST = null;
			tmp717_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp717_AST);
			match(241);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_date:
		{
			AST tmp718_AST = null;
			tmp718_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp718_AST);
			match(LITERAL_date);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 243:
		{
			AST tmp719_AST = null;
			tmp719_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp719_AST);
			match(243);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_dayname:
		{
			AST tmp720_AST = null;
			tmp720_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp720_AST);
			match(LITERAL_dayname);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 245:
		{
			AST tmp721_AST = null;
			tmp721_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp721_AST);
			match(245);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_days:
		{
			AST tmp722_AST = null;
			tmp722_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp722_AST);
			match(LITERAL_days);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 247:
		{
			AST tmp723_AST = null;
			tmp723_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp723_AST);
			match(247);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_day:
		{
			AST tmp724_AST = null;
			tmp724_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp724_AST);
			match(LITERAL_day);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 249:
		{
			AST tmp725_AST = null;
			tmp725_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp725_AST);
			match(249);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_dow:
		{
			AST tmp726_AST = null;
			tmp726_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp726_AST);
			match(LITERAL_dow);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 251:
		{
			AST tmp727_AST = null;
			tmp727_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp727_AST);
			match(251);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_hours:
		{
			AST tmp728_AST = null;
			tmp728_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp728_AST);
			match(LITERAL_hours);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 253:
		{
			AST tmp729_AST = null;
			tmp729_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp729_AST);
			match(253);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_hour:
		{
			AST tmp730_AST = null;
			tmp730_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp730_AST);
			match(LITERAL_hour);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 255:
		{
			AST tmp731_AST = null;
			tmp731_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp731_AST);
			match(255);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_minutes:
		{
			AST tmp732_AST = null;
			tmp732_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp732_AST);
			match(LITERAL_minutes);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 257:
		{
			AST tmp733_AST = null;
			tmp733_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp733_AST);
			match(257);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_minute:
		{
			AST tmp734_AST = null;
			tmp734_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp734_AST);
			match(LITERAL_minute);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 259:
		{
			AST tmp735_AST = null;
			tmp735_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp735_AST);
			match(259);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_monthname:
		{
			AST tmp736_AST = null;
			tmp736_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp736_AST);
			match(LITERAL_monthname);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 261:
		{
			AST tmp737_AST = null;
			tmp737_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp737_AST);
			match(261);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_months:
		{
			AST tmp738_AST = null;
			tmp738_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp738_AST);
			match(LITERAL_months);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 263:
		{
			AST tmp739_AST = null;
			tmp739_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp739_AST);
			match(263);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_month:
		{
			AST tmp740_AST = null;
			tmp740_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp740_AST);
			match(LITERAL_month);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 265:
		{
			AST tmp741_AST = null;
			tmp741_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp741_AST);
			match(265);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_quarter:
		{
			AST tmp742_AST = null;
			tmp742_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp742_AST);
			match(LITERAL_quarter);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 267:
		{
			AST tmp743_AST = null;
			tmp743_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp743_AST);
			match(267);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_seconds:
		{
			AST tmp744_AST = null;
			tmp744_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp744_AST);
			match(LITERAL_seconds);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 269:
		{
			AST tmp745_AST = null;
			tmp745_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp745_AST);
			match(269);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_second:
		{
			AST tmp746_AST = null;
			tmp746_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp746_AST);
			match(LITERAL_second);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 271:
		{
			AST tmp747_AST = null;
			tmp747_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp747_AST);
			match(271);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_weeks:
		{
			AST tmp748_AST = null;
			tmp748_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp748_AST);
			match(LITERAL_weeks);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 273:
		{
			AST tmp749_AST = null;
			tmp749_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp749_AST);
			match(273);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_years:
		{
			AST tmp750_AST = null;
			tmp750_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp750_AST);
			match(LITERAL_years);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 275:
		{
			AST tmp751_AST = null;
			tmp751_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp751_AST);
			match(275);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_year:
		{
			AST tmp752_AST = null;
			tmp752_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp752_AST);
			match(LITERAL_year);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 277:
		{
			AST tmp753_AST = null;
			tmp753_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp753_AST);
			match(277);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_ymd:
		{
			AST tmp754_AST = null;
			tmp754_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp754_AST);
			match(LITERAL_ymd);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 279:
		{
			AST tmp755_AST = null;
			tmp755_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp755_AST);
			match(279);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_dateadd:
		{
			AST tmp756_AST = null;
			tmp756_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp756_AST);
			match(LITERAL_dateadd);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 281:
		{
			AST tmp757_AST = null;
			tmp757_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp757_AST);
			match(281);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_datediff:
		{
			AST tmp758_AST = null;
			tmp758_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp758_AST);
			match(LITERAL_datediff);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		case 283:
		{
			AST tmp759_AST = null;
			tmp759_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp759_AST);
			match(283);
			datetime_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = datetime_function_AST;
	}
	
	public final void conversion_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST conversion_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_hextoint:
		{
			AST tmp760_AST = null;
			tmp760_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp760_AST);
			match(LITERAL_hextoint);
			conversion_function_AST = (AST)currentAST.root;
			break;
		}
		case 285:
		{
			AST tmp761_AST = null;
			tmp761_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp761_AST);
			match(285);
			conversion_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_inttohex:
		{
			AST tmp762_AST = null;
			tmp762_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp762_AST);
			match(LITERAL_inttohex);
			conversion_function_AST = (AST)currentAST.root;
			break;
		}
		case 287:
		{
			AST tmp763_AST = null;
			tmp763_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp763_AST);
			match(287);
			conversion_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_isdate:
		{
			AST tmp764_AST = null;
			tmp764_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp764_AST);
			match(LITERAL_isdate);
			conversion_function_AST = (AST)currentAST.root;
			break;
		}
		case 289:
		{
			AST tmp765_AST = null;
			tmp765_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp765_AST);
			match(289);
			conversion_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_isnumeric:
		{
			AST tmp766_AST = null;
			tmp766_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp766_AST);
			match(LITERAL_isnumeric);
			conversion_function_AST = (AST)currentAST.root;
			break;
		}
		case 291:
		{
			AST tmp767_AST = null;
			tmp767_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp767_AST);
			match(291);
			conversion_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = conversion_function_AST;
	}
	
	public final void system_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST system_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_suser_id:
		{
			AST tmp768_AST = null;
			tmp768_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp768_AST);
			match(LITERAL_suser_id);
			system_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_suser_name:
		{
			AST tmp769_AST = null;
			tmp769_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp769_AST);
			match(LITERAL_suser_name);
			system_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_user_id:
		{
			AST tmp770_AST = null;
			tmp770_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp770_AST);
			match(LITERAL_user_id);
			system_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_user_name:
		{
			AST tmp771_AST = null;
			tmp771_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp771_AST);
			match(LITERAL_user_name);
			system_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = system_function_AST;
	}
	
	public final void other_function() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST other_function_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_argn:
		{
			AST tmp772_AST = null;
			tmp772_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp772_AST);
			match(LITERAL_argn);
			other_function_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_rowid:
		{
			AST tmp773_AST = null;
			tmp773_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp773_AST);
			match(LITERAL_rowid);
			other_function_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = other_function_AST;
	}
	
	public final void logic_op() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST logic_op_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_and:
		{
			AST tmp774_AST = null;
			tmp774_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp774_AST);
			match(LITERAL_and);
			logic_op_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_or:
		{
			AST tmp775_AST = null;
			tmp775_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp775_AST);
			match(LITERAL_or);
			logic_op_AST = (AST)currentAST.root;
			break;
		}
		case 62:
		{
			AST tmp776_AST = null;
			tmp776_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp776_AST);
			match(62);
			logic_op_AST = (AST)currentAST.root;
			break;
		}
		case 63:
		{
			AST tmp777_AST = null;
			tmp777_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp777_AST);
			match(63);
			logic_op_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = logic_op_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"SELECT_STATEMENT",
		"SELECT_TOKEN",
		"SEARCH_NOT_CONDITION",
		"SUBQUERY",
		"GROUP_BY",
		"ORDER_BY",
		"ALIAS_EQU",
		"FUNCTION",
		"FUNCTION_EMPTY_PARAM",
		"FUNCTION_STAR_PARAM",
		"FUNCTION_STAR_COUNT",
		"FUNCTION_DATA_TYPE",
		"FUNCTION_AS_DATA_TYPE",
		"LOGIC_OP",
		"LOGICAL_NULL",
		"LOGICAL_NOT_NULL",
		"LOGICAL_IN",
		"LOGICAL_NOT_IN",
		"LOGICAL_LIKE",
		"LOGICAL_NOT_LIKE",
		"LOGICAL_EXISTS",
		"LOGICAL_NOT_EXISTS",
		"LOGICAL_BETWEEN",
		"SUBCONTAIN_OP",
		"ALL_FIELDS",
		"PAREN_FIELD",
		"PAREN_DATA_TYPE",
		"PAREN_CHAR_DATA_TYPE",
		"LOGIC_BLOCK",
		"COLUMN",
		"WHERE",
		"SEMI",
		"\"t_union\"",
		"\"\\u8868\\u5408\\u5e76\"",
		"COMMA",
		"\"t_compare\"",
		"\"\\u8868\\u6bd4\\u8f83\"",
		"\"where\"",
		"\"\\u6761\\u4ef6\"",
		"\"not\"",
		"\"exists\"",
		"\"select\"",
		"\"\\u67e5\\u8be2\"",
		"\"distinct\"",
		"\"\\u552f\\u4e00\"",
		"\"top\"",
		"\"\\u524dN\\u6761\"",
		"\"from\"",
		"\"\\u6765\\u81ea\"",
		"\"group\"",
		"\"by\"",
		"\"\\u5206\\u7ec4\"",
		"\"order\"",
		"\"\\u6392\\u5e8f\"",
		"REAL_NUM",
		"\"\\u975e\"",
		"\"and\"",
		"\"or\"",
		"\"\\u5e76\\u4e14\"",
		"\"\\u6216\\u8005\"",
		"LPAREN",
		"RPAREN",
		"\"as\"",
		"\"\\u4f5c\\u4e3a\"",
		"\"=\"",
		"\"\\u7b49\\u4e8e\"",
		"\"\\u6240\\u6709\"",
		"STAR",
		"\"\\u5347\\u5e8f\"",
		"\"\\u964d\\u5e8f\"",
		"\"asc\"",
		"\"desc\"",
		"\"like\"",
		"\"is\"",
		"\"null\"",
		"\"\\u4e3a\\u7a7a\"",
		"\"\\u975e\\u7a7a\"",
		"\"between\"",
		"\"\\u8303\\u56f4\"",
		"\"in\"",
		"\"\\u5728\\u4e8e\"",
		"\"\\u4e0d\\u5728\\u4e8e\"",
		"\"\\u6c42\\u8bb0\\u5f55\\u603b\\u6570\"",
		"\"count\"",
		"\"all\"",
		"\"\\u5168\\u90e8\"",
		"\"\\u4e3a\"",
		"\"char\"",
		"DATA_TYPE_STRING",
		"ID",
		"PARAM_ID",
		"QUOTED_STRING",
		"POINT",
		"NEGATIVE_DIGIT_ELEMENT",
		"\"avg\"",
		"\"\\u6c42\\u5e73\\u5747\\u6570\"",
		"\"max\"",
		"\"\\u6c42\\u6700\\u5927\\u503c\"",
		"\"min\"",
		"\"\\u6c42\\u6700\\u5c0f\\u503c\"",
		"\"stddev\"",
		"\"\\u6c42\\u65b9\\u5dee\"",
		"\"sum\"",
		"\"\\u6c42\\u603b\\u548c\"",
		"\"variance\"",
		"\"\\u6c42\\u7edf\\u8ba1\\u65b9\\u5dee\"",
		"\"getdate\"",
		"\"\\u53d6\\u5f53\\u524d\\u65e5\\u671f\\u65f6\\u95f42\"",
		"\"rand\"",
		"\"\\u53d6\\u968f\\u673a\\u6570\"",
		"\"pi\"",
		"\"\\u6c42\\u5706\\u5468\\u7387\"",
		"\"now\"",
		"\"\\u53d6\\u5f53\\u524d\\u65e5\\u671f\\u65f6\\u95f41\"",
		"\"today\"",
		"\"\\u6c42\\u5f53\\u524d\\u65e5\\u671f\"",
		"\"convert\"",
		"\"\\u6570\\u636e\\u7c7b\\u578b\\u8f6c\\u6362\"",
		"\"cast\"",
		"\"\\u6570\\u636e\\u7c7b\\u578b\\u8f6c\\u5316\"",
		"\"abs\"",
		"\"\\u53d6\\u7edd\\u5bf9\\u503c\"",
		"\"acos\"",
		"\"\\u6c42\\u53cd\\u4f59\\u5f26\\u503c\"",
		"\"asin\"",
		"\"\\u6c42\\u53cd\\u6b63\\u5f26\\u503c\"",
		"\"atan\"",
		"\"\\u6c42\\u53cd\\u6b63\\u5207\\u503c\"",
		"\"atin2\"",
		"\"\\u6c42\\u4e8c\\u4e2a\\u6570\\u7684\\u53cd\\u6b63\\u5207\\u503c\"",
		"\"ceiling\"",
		"\"\\u53d6\\u4e0a\\u9650\\u6574\\u6570\"",
		"\"cos\"",
		"\"\\u6c42\\u4f59\\u5f26\\u503c\"",
		"\"cot\"",
		"\"\\u6c42\\u4f59\\u5207\\u503c\"",
		"\"degrees\"",
		"\"\\u5f27\\u5ea6\\u8f6c\\u5ea6\\u6570\"",
		"\"exp\"",
		"\"\\u6c42\\u5e42\\u503c\"",
		"\"floor\"",
		"\"\\u53d6\\u4e0b\\u9650\\u6574\\u6570\"",
		"\"log\"",
		"\"\\u6c42\\u81ea\\u7136\\u5bf9\\u6570\"",
		"\"log10\"",
		"\"\\u6c4210\\u4e3a\\u5e95\\u7684\\u5bf9\\u6570\"",
		"\"mod\"",
		"\"\\u6c42\\u6a21\"",
		"\"power\"",
		"\"\\u6c42\\u5e42\"",
		"\"radians\"",
		"\"\\u5ea6\\u6570\\u8f6c\\u5f27\\u5ea6\"",
		"\"remainder\"",
		"\"\\u6c42\\u4f59\"",
		"\"round\"",
		"\"\\u683c\\u5f0f\\u5316\\u6570\\u503c\"",
		"\"sign\"",
		"\"\\u6c42\\u503c\\u7684\\u7b26\\u53f7\"",
		"\"sin\"",
		"\"\\u6c42\\u6b63\\u5f26\\u503c\"",
		"\"sqrt\"",
		"\"\\u6c42\\u5e73\\u65b9\\u6839\"",
		"\"tan\"",
		"\"\\u6c42\\u6b63\\u5207\\u503c\"",
		"\"\\u683c\\u5f0f\\u5316\\u6570\\u503c3\"",
		"\"truncnum\"",
		"\"N\\u4f4d\\u7f6e\\u96f6\\u5904\\u7406\"",
		"\"ascii\"",
		"\"\\u6c42ASCII\\u7801\"",
		"\"bit_length\"",
		"\"\\u6c42\\u5b57\\u7b26\\u4e32\\u7684\\u4e8c\\u8fdb\\u5236\\u957f\\u5ea6\"",
		"\"byte_length\"",
		"\"\\u6c42\\u5b57\\u7b26\\u4e32\\u7684\\u5b57\\u8282\\u6570\"",
		"\"\\u6c42\\u7b49\\u503c\\u7684\\u5b57\\u7b26\"",
		"\"char_length\"",
		"\"\\u6c42\\u5b57\\u7b26\\u4e32\\u957f\\u5ea61\"",
		"\"charindex\"",
		"\"\\u5b58\\u5728\\u4e8e\"",
		"\"difference\"",
		"\"\\u6c42\\u4e24\\u4e2a\\u4e32\\u7684\\u58f0\\u97f3\\u5dee\\u503c\"",
		"\"insertstr\"",
		"\"\\u63d2\\u5165\\u5b57\\u7b26\\u4e32\"",
		"\"lcase\"",
		"\"\\u8f6c\\u4e3a\\u5c0f\\u5199\\u5b57\\u6bcd1\"",
		"\"left\"",
		"\"\\u5de6\\u622a\\u5b57\\u7b26\\u4e32\"",
		"\"length\"",
		"\"\\u6c42\\u5b57\\u7b26\\u4e32\\u957f\\u5ea62\"",
		"\"locate\"",
		"\"\\u6c42\\u4e32\\u4f4d\\u7f6e1\"",
		"\"lower\"",
		"\"\\u8f6c\\u4e3a\\u5c0f\\u5199\\u5b57\\u6bcd2\"",
		"\"ltrim\"",
		"\"\\u53bb\\u6389\\u5de6\\u7a7a\\u683c\"",
		"\"octet_length\"",
		"\"\\u6c42\\u5b57\\u7b26\\u4e32\\u7684\\u5b58\\u50a8\\u957f\\u5ea6\"",
		"\"patindex\"",
		"\"\\u6c42\\u4e32\\u4f4d\\u7f6e2\"",
		"\"repeat\"",
		"\"\\u5b57\\u7b26\\u4e32\\u5faa\\u73af\\u8fde\\u63a51\"",
		"\"replace\"",
		"\"\\u66ff\\u6362\\u5b57\\u7b26\\u4e32\"",
		"\"replicate\"",
		"\"\\u5b57\\u7b26\\u4e32\\u5faa\\u73af\\u8fde\\u63a52\"",
		"\"right\"",
		"\"\\u53f3\\u622a\\u5b57\\u7b26\\u4e32\"",
		"\"rtrim\"",
		"\"\\u53bb\\u6389\\u53f3\\u7a7a\\u683c\"",
		"\"similar\"",
		"\"\\u6c42\\u5b57\\u7b26\\u4e32\\u76f8\\u4f3c\\u5ea6\"",
		"\"sortkey\"",
		"\"\\u5b57\\u7b26\\u4e32\\u6392\\u5e8f\"",
		"\"soundex\"",
		"\"\\u6c42\\u5b57\\u7b26\\u4e32\\u58f0\\u97f3\\u503c\"",
		"\"space\"",
		"\"\\u586b\\u7a7a\\u683c\"",
		"\"str\"",
		"\"\\u6570\\u503c\\u8f6c\\u5b57\\u7b26\\u4e32\"",
		"\"string\"",
		"\"\\u5b57\\u7b26\\u4e32\\u5408\\u5e76\"",
		"\"stuff\"",
		"\"\\u5b57\\u7b26\\u4e32\\u5220\\u9664\\u66ff\\u6362\"",
		"\"substring\"",
		"\"\\u5b57\\u7b26\\u4e32\\u622a\\u53d6\"",
		"\"trim\"",
		"\"\\u53bb\\u5de6\\u53f3\\u7a7a\\u683c\"",
		"\"ucase\"",
		"\"\\u8f6c\\u4e3a\\u5927\\u5199\\u5b57\\u6bcd1\"",
		"\"upper\"",
		"\"\\u8f6c\\u4e3a\\u5927\\u5199\\u5b57\\u6bcd2\"",
		"\"dateformat\"",
		"\"\\u683c\\u5f0f\\u5316\\u65e5\\u671f\"",
		"\"datename\"",
		"\"\\u6c42\\u65e5\\u671f\\u5206\\u91cf\\u82f1\\u6587\\u540d\"",
		"\"datepart\"",
		"\"\\u6c42\\u65e5\\u671f\\u7684\\u5206\\u91cf\\u503c\"",
		"\"datetime\"",
		"\"\\u8f6c\\u4e3a\\u65e5\\u671f\\u65f6\\u95f4\"",
		"\"date\"",
		"\"\\u8f6c\\u4e3a\\u65e5\\u671f\"",
		"\"dayname\"",
		"\"\\u6c42\\u661f\\u671f\\u82f1\\u6587\\u540d\"",
		"\"days\"",
		"\"\\u6c42\\u5929\\u6570\"",
		"\"day\"",
		"\"\\u6c42\\u5177\\u4f53\\u65e5\\u671f\"",
		"\"dow\"",
		"\"\\u6c42\\u5177\\u4f53\\u661f\\u671f\"",
		"\"hours\"",
		"\"\\u6c42\\u5c0f\\u65f6\\u6570\"",
		"\"hour\"",
		"\"\\u6c42\\u5177\\u4f53\\u5c0f\\u65f6\"",
		"\"minutes\"",
		"\"\\u6c42\\u5206\\u949f\\u6570\"",
		"\"minute\"",
		"\"\\u6c42\\u5177\\u4f53\\u5206\\u949f\"",
		"\"monthname\"",
		"\"\\u6c42\\u6708\\u4efd\\u82f1\\u6587\\u540d\"",
		"\"months\"",
		"\"\\u6c42\\u6708\\u6570\"",
		"\"month\"",
		"\"\\u6c42\\u5177\\u4f53\\u6708\\u6570\"",
		"\"quarter\"",
		"\"\\u6c42\\u5177\\u4f53\\u5b63\\u5ea6\"",
		"\"seconds\"",
		"\"\\u6c42\\u79d2\\u6570\"",
		"\"second\"",
		"\"\\u6c42\\u5177\\u4f53\\u79d2\"",
		"\"weeks\"",
		"\"\\u6c42\\u5468\\u6570\"",
		"\"years\"",
		"\"\\u6c42\\u5e74\\u6570\"",
		"\"year\"",
		"\"\\u6c42\\u5177\\u4f53\\u5e74\\u4efd\"",
		"\"ymd\"",
		"\"\\u6570\\u503c\\u8f6c\\u65e5\\u671f\"",
		"\"dateadd\"",
		"\"\\u65e5\\u671f\\u8fd0\\u7b97\"",
		"\"datediff\"",
		"\"\\u6c42\\u4e24\\u65e5\\u671f\\u5dee\\u503c\"",
		"\"hextoint\"",
		"\"\\u5341\\u516d\\u8fdb\\u5236\\u8f6c\\u4e3a\\u6574\\u6570\"",
		"\"inttohex\"",
		"\"\\u6574\\u6570\\u8f6c\\u4e3a\\u5341\\u516d\\u8fdb\\u5236\"",
		"\"isdate\"",
		"\"\\u662f\\u5426\\u65e5\\u671f\\u578b\"",
		"\"isnumeric\"",
		"\"\\u662f\\u5426\\u6570\\u503c\\u578b\"",
		"\"suser_id\"",
		"\"suser_name\"",
		"\"user_id\"",
		"\"user_name\"",
		"\"argn\"",
		"\"rowid\"",
		"ONE_ARG_OP",
		"TWO_ARG_OP",
		"MINUS",
		"\"\\u4e0e\"",
		"\"\\u6216\"",
		"\"\\u5f02\\u6216\"",
		"\"\\u52a0\"",
		"\"\\u51cf\"",
		"\"\\u4e58\"",
		"\"\\u9664\"",
		"COMPARE_OP",
		"\"\\u5927\\u4e8e\\u7b49\\u4e8e\"",
		"\"\\u5c0f\\u4e8e\\u7b49\\u4e8e\"",
		"\"\\u5927\\u4e8e\"",
		"\"\\u5c0f\\u4e8e\"",
		"\"\\u4e0d\\u7b49\\u4e8e\"",
		"\"\\u5305\\u542b\"",
		"\"\\u4e0d\\u5305\\u542b\"",
		"\"\\u5de6\\u8fde\\u63a5\"",
		"LEFT_JOIN",
		"\"\\u5b58\\u5728\"",
		"\"\\u4e0d\\u5b58\\u5728\"",
		"\"yy\"",
		"\"mm\"",
		"\"dd\"",
		"\"qq\"",
		"\"week\"",
		"\"wk\"",
		"\"dayofyear\"",
		"\"dy\"",
		"\"weekday\"",
		"\"dw\"",
		"\"hh\"",
		"\"mi\"",
		"\"ss\"",
		"\"millisecond\"",
		"\"ms\"",
		"\"calweekofyear\"",
		"\"cwk\"",
		"\"calyearofweek\"",
		"\"cyr\"",
		"\"caldayofweek\"",
		"\"cdw\"",
		"\"uniqueidentifierstr\"",
		"\"bigint\"",
		"\"int\"",
		"\"integer\"",
		"\"smallint\"",
		"\"tinyint\"",
		"\"double\"",
		"\"real\"",
		"\"smalldatetime\"",
		"\"time\"",
		"\"timestamp\"",
		"\"bit\"",
		"PARAM_LPAREN",
		"PARAM_RPAREN",
		"FROM",
		"WS",
		"ESC",
		"ID_START_LETTER",
		"ID_LETTER",
		"NUM",
		"DOT_NUM",
		"NUM_START",
		"NUM_LETTER",
		"ML_COMMENT"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[12];
		data[0]=288230376151711744L;
		data[1]=-5758763007L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=8796093022207L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[12];
		data[0]=556201460287668226L;
		data[1]=-390053745L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[12];
		data[0]=288230376151711744L;
		data[1]=-70356261912575L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=8796093022207L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[12];
		data[0]=289919226011975680L;
		data[1]=-5758762815L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=8796093022207L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = new long[12];
		data[0]=288230376151711744L;
		data[1]=-5758762815L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=8796093022207L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = new long[12];
		data[0]=288239172244733952L;
		data[1]=-70351962771279L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = new long[12];
		data[0]=-864682057484206080L;
		data[1]=-70351694335821L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = new long[12];
		data[0]=-603473244736978942L;
		data[1]=-70351627226953L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = new long[12];
		data[0]=-603473244736978942L;
		data[1]=-70351694335821L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = new long[12];
		data[0]=-585351437685358590L;
		data[1]=-50335561L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = new long[12];
		data[0]=-583240375360028670L;
		data[1]=-50331657L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 8796093022208L, 3670016L, 0L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = new long[12];
		data[0]=288230376151711744L;
		data[1]=-70357335654399L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4398046511103L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = new long[12];
		data[0]=288230376151711744L;
		data[1]=-70351966945149L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = new long[12];
		data[0]=504403467503140866L;
		data[1]=-70351698509693L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = new long[12];
		data[0]=522525274554761218L;
		data[1]=-54509433L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = new long[12];
		data[0]=504403467503140866L;
		data[1]=-70351966945149L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = new long[12];
		data[0]=522525274554761218L;
		data[1]=-121618301L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = new long[12];
		data[0]=524636336880091138L;
		data[1]=-54505529L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = new long[12];
		data[0]=576434329664618498L;
		data[1]=-4173825L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = new long[10];
		data[1]=-70368609959935L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4398046511103L;
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = new long[12];
		data[0]=288230651029618688L;
		data[1]=-70351698509693L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = new long[12];
		data[0]=504403467503140866L;
		data[1]=-70351631400825L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = new long[12];
		data[0]=288230376151711744L;
		data[1]=10737434624L;
		data[3]=4683743612465315840L;
		data[4]=1066244L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = new long[10];
		data[0]=216173091351429122L;
		data[1]=130L;
		data[2]=8388608L;
		data[4]=4494803534348288L;
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = new long[12];
		data[0]=522525274554761218L;
		data[1]=-5758763005L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4398046511103L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	private static final long[] mk_tokenSet_26() {
		long[] data = new long[12];
		data[0]=524636336880091138L;
		data[1]=-390049853L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
	private static final long[] mk_tokenSet_27() {
		long[] data = new long[12];
		data[0]=576434329664618498L;
		data[1]=-71282689L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
	private static final long[] mk_tokenSet_28() {
		long[] data = { 309237645314L, 3842L, 0L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
	private static final long[] mk_tokenSet_29() {
		long[] data = { 0L, 70351576891392L, 0L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());
	private static final long[] mk_tokenSet_30() {
		long[] data = new long[12];
		data[0]=288230376151711744L;
		data[1]=-390053757L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());
	private static final long[] mk_tokenSet_31() {
		long[] data = new long[12];
		data[0]=556623672752734210L;
		data[1]=-71286641L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());
	private static final long[] mk_tokenSet_32() {
		long[] data = new long[12];
		data[0]=1151215028200800258L;
		data[1]=-4177713L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());
	private static final long[] mk_tokenSet_33() {
		long[] data = new long[12];
		data[0]=288230376151711744L;
		data[1]=-5758763007L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4398046511103L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());
	private static final long[] mk_tokenSet_34() {
		long[] data = new long[12];
		data[0]=556201460287668226L;
		data[1]=-70351966945137L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());
	private static final long[] mk_tokenSet_35() {
		long[] data = new long[12];
		data[0]=1151215028200800258L;
		data[1]=-71286577L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());
	private static final long[] mk_tokenSet_36() {
		long[] data = new long[12];
		data[0]=1152903878061064194L;
		data[1]=-50331649L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());
	private static final long[] mk_tokenSet_37() {
		long[] data = new long[12];
		data[0]=-17626545782782L;
		for (int i = 1; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());
	private static final long[] mk_tokenSet_38() {
		long[] data = new long[10];
		data[0]=267971084135956482L;
		data[1]=142L;
		data[2]=8388608L;
		data[4]=4494803534348288L;
		return data;
	}
	public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());
	private static final long[] mk_tokenSet_39() {
		long[] data = new long[12];
		data[0]=1150792815735734274L;
		data[1]=-4685020977L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());
	private static final long[] mk_tokenSet_40() {
		long[] data = new long[12];
		data[0]=1152903878061064194L;
		data[1]=-385875969L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());
	private static final long[] mk_tokenSet_41() {
		long[] data = new long[12];
		data[0]=-17626545782782L;
		data[1]=-67108865L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());
	private static final long[] mk_tokenSet_42() {
		long[] data = new long[12];
		data[0]=556201460287668226L;
		data[1]=-70351631400817L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());
	private static final long[] mk_tokenSet_43() {
		long[] data = new long[12];
		data[0]=1150792815735734274L;
		data[1]=-54509361L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());
	private static final long[] mk_tokenSet_44() {
		long[] data = new long[12];
		data[0]=-596711248226156542L;
		data[1]=-70356190625857L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());
	private static final long[] mk_tokenSet_45() {
		long[] data = new long[12];
		data[0]=-2128688871112702L;
		data[1]=-50331649L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());
	private static final long[] mk_tokenSet_46() {
		long[] data = new long[10];
		data[1]=-1152077079542497279L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4398046511103L;
		return data;
	}
	public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());
	private static final long[] mk_tokenSet_47() {
		long[] data = new long[12];
		data[0]=288230651029618688L;
		data[1]=-70351966945149L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4503599627370495L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());
	private static final long[] mk_tokenSet_48() {
		long[] data = new long[12];
		data[0]=-596711248226156542L;
		data[1]=-70351627223105L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());
	private static final long[] mk_tokenSet_49() {
		long[] data = new long[12];
		data[0]=288652588616777728L;
		data[1]=-70356211580927L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=8796093022207L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());
	private static final long[] mk_tokenSet_50() {
		long[] data = new long[12];
		data[0]=-578589441174536190L;
		data[1]=-50331713L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());
	private static final long[] mk_tokenSet_51() {
		long[] data = new long[10];
		data[1]=128L;
		data[2]=8388608L;
		data[4]=4494803534348288L;
		return data;
	}
	public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());
	private static final long[] mk_tokenSet_52() {
		long[] data = new long[12];
		data[0]=-603473244736978942L;
		data[1]=-70351895662409L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());
	private static final long[] mk_tokenSet_53() {
		long[] data = new long[12];
		data[0]=-17626545782782L;
		data[1]=-50331649L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=8589934591L;
		return data;
	}
	public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());
	private static final long[] mk_tokenSet_54() {
		long[] data = new long[12];
		data[0]=-603473244736978942L;
		data[1]=-70356190629833L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4607191214893039615L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());
	private static final long[] mk_tokenSet_55() {
		long[] data = new long[12];
		data[0]=288230376151711744L;
		data[1]=-70356261912575L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4398046511103L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());
	private static final long[] mk_tokenSet_56() {
		long[] data = new long[12];
		data[0]=-603473244736978942L;
		data[1]=-70356190629705L;
		for (int i = 2; i<=3; i++) { data[i]=-1L; }
		data[4]=4611686018427387903L;
		data[5]=2097151L;
		return data;
	}
	public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());
	private static final long[] mk_tokenSet_57() {
		long[] data = new long[12];
		data[3]=1407374883553280L;
		data[5]=8587837440L;
		return data;
	}
	public static final BitSet _tokenSet_57 = new BitSet(mk_tokenSet_57());
	
	}