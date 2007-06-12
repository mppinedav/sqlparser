package model.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.oracle.*;
import parser.sybase.*;
import antlr.ANTLRException;
import antlr.CharStreamIOException;
import antlr.CommonAST;
import antlr.MismatchedCharException;
import antlr.MismatchedTokenException;
import antlr.NoViableAltException;
import antlr.NoViableAltForCharException;
import antlr.SemanticException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.TokenStreamRetryException;
import antlr.debug.misc.ASTFrame;

import util.StringUtil;
import model.parser.common.DataBaseType;
import model.parser.exceptions.NoGroupExistsException;
import model.parser.exceptions.NoSuchFieldException;
import model.parser.exceptions.NoSuchTableException;
import model.parser.exceptions.TableNotInFromClause;
import model.parser.exceptions.TableNumberException;
import model.parser.exceptions.common.ErrorLexer;

/**
 * ����������ģ��QueryModel�����﷨����ʵ����
 * �޸���־��
 * ======================================================
 * 06/12/2007��
 * 	- ȡ�����������Ӣ�Ĺؼ��ֵĻ�ȡ������setKeyWordsProp��ȡ��
 * 		�������ݲ�ͬ�����ݿ����ͻ�ȡ��Ӧ��Ӣ�Ĺؼ���
 * 
 * ======================================================
 */
public class QueryModel {
  private static final String KEYWORDS = "keywords";  							//�ؼ��������ļ�����
  private static final String KEYWORDS_ORACLE = "keywords_oracle";  //�ؼ��������ļ�����
  private static final String KEYWORDS_SYBASE = "keywords";  				//�ؼ��������ļ�����
  
  private static Map mapKeyword = new HashMap();      //��Źؼ��ֵ�HashMap
  
  private SybaseIQ12Parser sybaseIQ12Parser;
  private SybaseIQ12Lexer sybaseIQ12Lexer;
  private Oracle9iParser oracle9iParser;
  private Oracle9iLexer oracle9iLexer;
  
  private String chQuery;
  private String databaseType;
  private List children = new ArrayList();
  private DbTableModel dbTableModel = new DbTableModel();
  private List antlrExceptions = new ArrayList();
  
  private String circleType;  //ѭ���������(�����/��������)
  
  static {
  	
  }
  
  private static void setKeyWordsProp(String _databaseType){
  	//��ȡ������Ӣ�Ĺؼ��ֲ��洢��HashMap��mapKeyword��

  	if (_databaseType != null && mapKeyword.size() == 0){
  		String rKeyWordsName = "";
  		if (_databaseType.equals(DataBaseType.ORACLE8i) || _databaseType.equals(DataBaseType.ORACLE9i)){
  			rKeyWordsName = KEYWORDS_ORACLE;
  		}else if (_databaseType.equals(DataBaseType.SYBASE_IQ_12) || _databaseType.equals(DataBaseType.SYBASE_ASE_12)){
  			rKeyWordsName = KEYWORDS_SYBASE;
  		}else{
  			rKeyWordsName = KEYWORDS;
  		}
	    ResourceBundle bundle =  ResourceBundle.getBundle(rKeyWordsName, Locale.CHINESE);
	    Enumeration keys = bundle.getKeys();
	    while (keys.hasMoreElements()) {
	      String key = keys.nextElement().toString();
	      String val = bundle.getString(key);
	      mapKeyword.put(key, val);
	    }
  	}
  }
  
  /**
   * �����ĵĹؼ��֣��ؼ���/����/�����/������...������ɱ�׼�Ĺؼ���
   * @param str ҵ�񻯵Ĺؼ���
   * @return String ��׼�Ĺؼ���
   */
  protected static String translateStringCh2En(String str) {
    String en=(String)mapKeyword.get(str);
    return (en==null)?str:en;
  }
  
  /**
   * ��Ӣ�ĵĹؼ��֣��ؼ���/����/�����/������...����������ĵĹؼ���
   * @param str Ӣ�ĵĹؼ���
   * @return String ���ĵĹؼ���
   */
  protected static String translateStringEn2Ch(String str) {
  	String ch = "";
  	Set set = mapKeyword.entrySet();
  	Iterator it=set.iterator();
  	while (it.hasNext()) {
  		Map.Entry entry = (Map.Entry) it.next();
  		if (entry.getValue().equals(str)) {
  			ch = (String) entry.getKey();
  		}
  	}
    return (ch == null || ch.equals("")) ? str : ch;
  }
  
  protected QueryModel() {
  	
  }
  
  /**
   * ����SQL����������������ȷʱ����QueryModel����
   * @param chQuery ����SQL���
   * @return QueryModel ������QueryModel����
   */
  public static QueryModel parseQuery(String chQuery){
  	return parseQuery(chQuery, DataBaseType.DEFAULT_DATABASE_TYPE, true);
  }
  
  /**
   * ����SQL����������������ȷʱ����QueryModel����
   * @param chQuery ����SQL���
   * @param isGroupByValid �Ƿ��GROUP BY�ۺϺ���������֤
   * @return QueryModel ������QueryModel����
   */
  public static QueryModel parseQuery(String chQuery, boolean isGroupByValid){
  	return parseQuery(chQuery, DataBaseType.DEFAULT_DATABASE_TYPE, isGroupByValid);
  }
  
  /**
   * ����SQL����������������ȷʱ����QueryModel����
   * @param chQuery ����SQL���
   * @param databaseType ���ݿ�����
   * @return QueryModel ������QueryModel����
   */
  public static QueryModel parseQuery(String chQuery, String databaseType){
  	return parseQuery(chQuery, databaseType, true);
  }
  
  /**
   * ����SQL����������������ȷʱ����QueryModel����
   * @param chQuery ����SQL���
   * @param databaseType ���ݿ�����
   * @param isGroupByValid �Ƿ��GROUP BY�ۺϺ���������֤
   * @return QueryModel ������QueryModel����
   */
  public static QueryModel parseQuery(String chQuery, String databaseType, boolean isGroupByValid) {
  	String IS_NOT_EXISTS = "0";
  	String IS_EXISTS = "1";
    String IS_EXISTS_ERROR = "2";
    
    if (databaseType == null || databaseType.equals(""))
    	databaseType = DataBaseType.DEFAULT_DATABASE_TYPE;
    setKeyWordsProp(databaseType);	//���������ļ����ö�Ӧ��Ӣ�Ĺؼ���
    
  	QueryModel model = null;
    List exs = new ArrayList();
    if (databaseType.equals(DataBaseType.SYBASE_IQ_12) || databaseType.equals(DataBaseType.SYBASE_ASE_12)){
    	SybaseIQ12Lexer rSybaseIQ12Lexer = new SybaseIQ12Lexer(new StringReader(chQuery));
      SybaseIQ12Parser rSybaseIQ12Parser = new SybaseIQ12Parser(rSybaseIQ12Lexer);
    	try {
	      rSybaseIQ12Parser.statements();
	      CommonAST ast = (CommonAST) rSybaseIQ12Parser.getAST();
	      // TODO Visible ASTFrame
  	    //ASTFrame _ASTFrame = new ASTFrame("longtopParser", ast);
  	    //_ASTFrame.setVisible(true);
		    SybaseIQ12TreeParser rSybaseIQ12TreeParser = new SybaseIQ12TreeParser();
	      model = rSybaseIQ12TreeParser.statement(ast);
    	}catch (ANTLRException e) {
        exs.add(e);
      }
    	
    	if (model == null){
        model = new QueryModel();
      }
      model.setSybaseIQ12Lexer(rSybaseIQ12Lexer);
      model.setSybaseIQ12Parser(rSybaseIQ12Parser);
  	}else if (databaseType.equals(DataBaseType.ORACLE8i) || databaseType.equals(DataBaseType.ORACLE9i)){
  		Oracle9iLexer rOracle9iLexer = new Oracle9iLexer(new StringReader(chQuery));
  		Oracle9iParser rOracle9iParser = new Oracle9iParser(rOracle9iLexer);
  		try {
  			rOracle9iParser.statements();
	      CommonAST ast = (CommonAST) rOracle9iParser.getAST();
	      // TODO Visible ASTFrame
  	    //ASTFrame _ASTFrame = new ASTFrame("longtopParser", ast);
  	    //_ASTFrame.setVisible(true);
	      Oracle9iTreeParser rOracle9iTreeParser = new Oracle9iTreeParser();
	      model = rOracle9iTreeParser.statement(ast);
    	}catch (ANTLRException e) {
        exs.add(e);
      }
    	if (model == null){
        model = new QueryModel();
      }
      model.setOracle9iLexer(rOracle9iLexer);
      model.setOracle9iParser(rOracle9iParser);
  	}else{
  		if (model == null){
        model = new QueryModel();
      }
  	}
    model.setDatabaseType(databaseType);
    
    QueryModel[] _paramModelArr = model.getModelsFromAllChildrenByClass(ParamModel.class);
    if (_paramModelArr.length > 0)
      model.setCircleType(((ParamModel) _paramModelArr[0]).getCircleType());
    
    if (isGroupByValid) {
	    //==== SELECT�Ӿ��зǾۺϺ������ʽ������GROUP BY�Ӿ��г��� BEGIN ====//
	    Map aFunMap = new LinkedHashMap();	//�ۺϺ���Map
	    
	    //��ȡ���оۺϺ���Model����
	    QueryModel[] _aggregateFunModelArr = model.getModelsFromAllChildrenByClass(AggregateFuncModel.class);
	    
	    if (_aggregateFunModelArr.length > 0){	//������ھۺϺ���
		    // ѭ����ȡ���оۺϺ���Model���������Ϣ
				for (int i = 0; i < _aggregateFunModelArr.length; i++) {
					aFunMap.put(_aggregateFunModelArr[i].getChString(), IS_NOT_EXISTS);	//���ۺϺ�������Map�У���ʶΪIS_NOT_EXISTS
					//QueryModel apm = _aggregateFunModelArr[i].getFirstModelByClass(ParametersModel.class);
					// �õ�ÿ�����Ϻ��������в������ʽģ��
					//QueryModel expm = apm.getFirstModelByClass(ExpressionModel.class);
					//QueryModel[] expms = apm.getModelByClass(ExpressionModel.class);
					// �õ�ÿ�����Ϻ����������ֶ�ģ��
					//QueryModel[] fdms = apm.getModelsFromAllChildrenByClass(FieldModel.class);
				}
				
				Map nGroupExprMap = new LinkedHashMap();				//��Ҫ�ڷ�����ֵı��ʽMap
				Map mGroupSingleExprMap = new LinkedHashMap();	//�ɷ�����ֵĵ������ʽMap
				
				//��ȡSELECT�Ӿ��µ����б��ʽ
				QueryModel[] _columnModelArr = model.getModelsFromAllChildrenByClass(ColumnModel.class); 
				for (int i = 0; i < _columnModelArr.length; i++){
					ColumnModel _columnModel = (ColumnModel) _columnModelArr[i];
					QueryModel expm =  _columnModel.getFirstModelByClass(ExpressionModel.class);	//��ȡColumnModel�ı��ʽ
					
					if (!((ExpressionModel)expm).hasConstant()){	//������ǳ���������оۺϺ����ı��ʽ���бȽϣ�Ŀǰabs(-900)��Ϊ���ǳ�����
						if (aFunMap.containsKey(expm.getChString())){
							aFunMap.put(expm.getChString(), IS_EXISTS);						//��ʾ�˾ۺϺ�������SELECT�Ӿ����ҵ�
						}else{
							//��ȡ�˱��ʽ�ĵ����ֶΣ����Ϊ1���������mGroupSingleExprMap��
							QueryModel[] fmArr = expm.getModelsFromAllChildrenByClass(FieldModel.class);
							if (fmArr.length == 1){
								UnAggregateExpVO unAggregateExpVO = new UnAggregateExpVO();
								unAggregateExpVO.setUnAggregateExp(expm.getChString());
								unAggregateExpVO.setSingleExp(fmArr[0].getChString());
								unAggregateExpVO.setExistsFlag(IS_NOT_EXISTS);
								mGroupSingleExprMap.put(fmArr[0].getChString(), unAggregateExpVO);
								nGroupExprMap.put(expm.getChString(), IS_NOT_EXISTS);	//��ʾ��KEY��Ҫ�ڷ����г���
							}else if (fmArr.length == 0){
								QueryModel[] smArr = expm.getModelsFromAllChildrenByClass(StringModel.class);
								if (smArr.length != 1){	//������ʽ�����ڵ�����������: abs(-9000))
									nGroupExprMap.put(expm.getChString(), IS_NOT_EXISTS);	//��ʾ��KEY��Ҫ�ڷ����г���
								}
							}else{
								nGroupExprMap.put(expm.getChString(), IS_NOT_EXISTS);	//��ʾ��KEY��Ҫ�ڷ����г���
							}
						}
					}
					
		    }
				
				//��ȡGROUP BY�б�ģ�Ͷ���
		    QueryModel _groupByListModel = model.getFirstModelByClass(AggregateExprListModel.class);
		    //��ȡGROUP BY�б������б��ʽ����
		    QueryModel[] _groupByExprModelArr;
		    if (_groupByListModel == null){
		    	_groupByExprModelArr = new QueryModel[0];
		    }else{
		    	_groupByExprModelArr = _groupByListModel.getModelByClass(AggregateExprModel.class);
		    }
		    
		    //���GROUP BY�б��еı��ʽ��SELECT�־��������Map(nGroupExprMap)�д��ڣ������ô��ڱ�ʶ
		    for (int i = 0; i < _groupByExprModelArr.length; i++){
		    	if (nGroupExprMap.containsKey(_groupByExprModelArr[i].getChString())){
		    		nGroupExprMap.put(_groupByExprModelArr[i].getChString(), IS_EXISTS);
		    	}else if (mGroupSingleExprMap.containsKey(_groupByExprModelArr[i].getChString())){
		    		//�жϴ˱��ʽ�ĵ����ֶ��Ƿ���֣�����ڷ����г��֣������ô��ڱ�ʶ
		    		UnAggregateExpVO unAggregateExpVO = (UnAggregateExpVO) mGroupSingleExprMap.get(_groupByExprModelArr[i].getChString());
		    		nGroupExprMap.put(unAggregateExpVO.getUnAggregateExp(), IS_EXISTS);
		    	}else if (aFunMap.containsKey(_groupByExprModelArr[i].getChString())){
		    		NoGroupExistsException _exception = new NoGroupExistsException(_groupByExprModelArr[i].getChString(), NoGroupExistsException.EXPR_EXISTS_ERROR);
		    		exs.add(_exception);
		    	}
		    }
		    
		    //ѭ����ȡ������Map(nGroupExprMap)�У���GROUP BYû���ֵ�SELECT���ʽ�����������쳣������
		    for (Iterator it = nGroupExprMap.keySet().iterator(); it.hasNext();){
		    	String selectExpr = (String) it.next();
		    	if (((String)nGroupExprMap.get(selectExpr)).equals(IS_NOT_EXISTS)){
		    		NoGroupExistsException _exception = new NoGroupExistsException(selectExpr);
		    		exs.add(_exception);
		    	}
		    }
		    
		    
		    // �õ����к���ģ��(����һ�㺯���;ۺϺ���)--�˺��������GROUP BY�еĺ���
		    //QueryModel[] _allFunctionModelArr = model.getModelsFromAllChildrenByClass(FunctionModel.class);
	    }
	    //====SELECT�Ӿ��зǾۺϺ������ʽ������GROUP BY�Ӿ��г��� END ===//
    }
    
    model.setExceptions(exs);
    model.setChQuery(chQuery);
    
    return model;
  }
  
  public static QueryModel parseSegment(String chSegment){
  	return parseSegment(chSegment, DataBaseType.DEFAULT_DATABASE_TYPE);
  }
  /**
   * Ƭ���Ӿ��﷨��֤����������ȷ�Ƿ���QueryModel����
   * @param chSegment Ƭ���Ӿ�
   * @return QueryModel ������QueryModel����
   */
  public static QueryModel parseSegment(String chSegment, String databaseType) {
    QueryModel model = null;
    
    List exs = new ArrayList();
    if (databaseType.equals(DataBaseType.SYBASE_IQ_12) || databaseType.equals(DataBaseType.SYBASE_ASE_12)){
    	SybaseIQ12Lexer rSybaseIQ12Lexer = new SybaseIQ12Lexer(new StringReader(chSegment));
      SybaseIQ12Parser rSybaseIQ12Parser = new SybaseIQ12Parser(rSybaseIQ12Lexer);
      
      try {
	      rSybaseIQ12Parser.segment();
	      CommonAST ast = (CommonAST) rSybaseIQ12Parser.getAST();
	      // TODO Visible ASTFrame
//		    ASTFrame _ASTFrame = new ASTFrame("longtopParser", ast);
//		    _ASTFrame.setVisible(true);
		    SybaseIQ12TreeParser rSybaseIQ12TreeParser = new SybaseIQ12TreeParser();
	      model = rSybaseIQ12TreeParser.segment(ast);
      }catch (ANTLRException e) {
        exs.add(e);
      }
      
      if (model == null){
        model = new QueryModel();
      }
      model.setSybaseIQ12Lexer(rSybaseIQ12Lexer);
      model.setSybaseIQ12Parser(rSybaseIQ12Parser);
    }else if (databaseType.equals(DataBaseType.ORACLE8i) || databaseType.equals(DataBaseType.ORACLE9i)){
  		Oracle9iLexer rOracle9iLexer = new Oracle9iLexer(new StringReader(chSegment));
  		Oracle9iParser rOracle9iParser = new Oracle9iParser(rOracle9iLexer);
  		
  		try {
  			rOracle9iParser.statements();
	      CommonAST ast = (CommonAST) rOracle9iParser.getAST();
	      // TODO Visible ASTFrame
//	  	    ASTFrame _ASTFrame = new ASTFrame("longtopParser", ast);
//	  	    _ASTFrame.setVisible(true);
	      Oracle9iTreeParser rOracle9iTreeParser = new Oracle9iTreeParser();
	      model = rOracle9iTreeParser.statement(ast);
    	}catch (ANTLRException e) {
        exs.add(e);
      }
    	
    	if (model == null){
        model = new QueryModel();
      }
      model.setDatabaseType(databaseType);
      model.setOracle9iLexer(rOracle9iLexer);
      model.setOracle9iParser(rOracle9iParser);
  	}else{
  		if (model == null){
        model = new QueryModel();
      }
  	}
    
    model.setExceptions(exs);
    model.setChQuery(chSegment);
    
    return model;
  }
  
  /**
   * ��ȡԭʼ��ҵ�񻯲�ѯ���
   * @return String ԭʼ��ҵ�񻯲�ѯ���
   */
  public String getChQuery() {
		return chQuery;
	}
  
  /**
   * �������ԭʼҵ�񻯲�ѯ��䱣����QueryModel��chQuery������
   * @param query ҵ�񻯲�ѯ
   */
	public void setChQuery(String chQuery) {
		this.chQuery = chQuery;
	}

	/**
   * ȡ��ѭ��������ͣ�1Ϊ�������2Ϊ����������
   * @return String ѭ���������
   */
  public String getCircleType() {
    return circleType;
  }
  
  /**
   * ����ȡ��ѭ���������
   * @param circleType ��1Ϊ�������2Ϊ����������
   */
  public void setCircleType(String circleType) {
    this.circleType = circleType;
  }
  
  public String getDatabaseType() {
		return this.databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	/**
   * ���ģ���б��ֶ���Ϣ
   *
   */
  public void clearDbTableModel(){
    this.dbTableModel = new DbTableModel();
  }
  
  /**
   * ���ӱ�����
   * @param tableChName ���ı���
   * @param tableEnName Ӣ�ı���
   */
  public void addDbTable(String tableChName, String tableEnName) {
    dbTableModel.setTableEnName(tableChName, tableEnName);
  }
  
  /**
   * ���ӱ��ֶ����Զ���
   * @param tableChName ���ı���
   * @param fieldChName �����ֶ�
   * @param fieldEnName Ӣ���ֶ�
   */
  public void addDbField(String tableChName, String fieldChName, String fieldEnName) {
    dbTableModel.setFieldEnName(tableChName, fieldChName, fieldEnName);
  }
  
  /**
   * ��ȡ�������б������ֶ�����ģ�Ͷ���
   * @return DbTableModel �������ֶ�����ģ�Ͷ���
   */
  public DbTableModel getDbTableModel() {
    return dbTableModel;
  }
  
  /**
   * ����QueryModel����children��List������
   * @param model QueryModel����
   */
  public void addChild(QueryModel model) {
    if (model.getClass() == getClass()){
      for (Iterator it = model.children.iterator(); it.hasNext();) {
        QueryModel m = (QueryModel)it.next();
        addChild(m);
      }
    } else {
      children.add(model);
    }
  }
  
  /**
   * ���Ӳ���������QueryModel��children��List�����У��ڱ����﷨��ʱ���룩
   * @param op ������
   */
  public void addOperator(String op) {
    addOperator(op, false);
  }
  
  /**
   * ���Ӳ���������QueryModel��children��List�����У��ڱ����﷨��ʱ���룩
   * @param op ������
   * @param isEn2Ch �Ƿ�Ӣ�Ĺؼ���ת������
   */
  public void addOperator(String op, boolean isEn2Ch) {
	  /*
		 int count=0;
		 String regEx = "[\\u4e00-\\u9fa5]";
		 Pattern p = Pattern.compile(regEx);
		 Matcher m = p.matcher(op);
		 while (m.find()) {
	  	 for (int i = 0; i <= m.groupCount(); i++) {
	  		 count=count+1;
	  	 }
		 }
		 */
  	
  	String rValue = op.toLowerCase();
  	if (isEn2Ch)
  		rValue = translateStringEn2Ch(rValue);
  	children.add(new StringModel(rValue));
  }
  
  /**
   * ��ȡ��һ���﷨�ṹ���ݼ���
   * @return List �﷨�ṹ���ݼ���
   */
  public List getChildren() {
    return this.children;
  }
  
  /**
   * �����쳣��Ϣ����
   * @param antlrExceptions �쳣��Ϣ����
   */
  public void setExceptions(List antlrExceptions) {
    this.antlrExceptions = antlrExceptions;
  }
  
  
  
  public SybaseIQ12Lexer getSybaseIQ12Lexer() {
		return sybaseIQ12Lexer;
	}

	public void setSybaseIQ12Lexer(SybaseIQ12Lexer sybaseIQ12Lexer) {
		this.sybaseIQ12Lexer = sybaseIQ12Lexer;
	}

	public SybaseIQ12Parser getSybaseIQ12Parser() {
		return sybaseIQ12Parser;
	}

	public void setSybaseIQ12Parser(SybaseIQ12Parser sybaseIQ12Parser) {
		this.sybaseIQ12Parser = sybaseIQ12Parser;
	}

  public Oracle9iLexer getOracle9iLexer() {
		return oracle9iLexer;
	}

	public void setOracle9iLexer(Oracle9iLexer oracle9iLexer) {
		this.oracle9iLexer = oracle9iLexer;
	}

	public Oracle9iParser getOracle9iParser() {
		return oracle9iParser;
	}

	public void setOracle9iParser(Oracle9iParser oracle9iParser) {
		this.oracle9iParser = oracle9iParser;
	}

	/**
   * ��QueryModel��children List�����л�ȡ����Ϊc�����ж����ŵ�QueryModel[]��
   * @param c ָ����ģ�Ͷ���(StatementsModel��SelectStatementModel...��
   * @return QueryModel[] ��������Ϊָ����ģ�Ͷ����������Ϣ����
   */
  public QueryModel[] getModelByClass(Class c) {
    List models = new ArrayList();
    for (Iterator it = children.iterator(); it.hasNext();) {
      QueryModel q = (QueryModel)it.next();
      if (q.getClass() == c)
        models.add(q);
    }
    QueryModel[] ret=new QueryModel[models.size()];
    int i=0;
    for (Iterator it=models.iterator(); it.hasNext();) {
      QueryModel q=(QueryModel)it.next();
      ret[i++]=q;
    }
    return ret;
  }
  
  /**
   * ��QueryModel��children List�����л�ȡ����Ϊc�ĵ�һ������
   * @param c ָ����ģ�Ͷ���(StatementsModel��SelectStatementModel...��
   * @return QueryModel ��������Ϊָ����ģ�Ͷ���ĵ�һ��QueryModelQueryModel
   */
  public QueryModel getFirstModelByClass(Class c) {
    QueryModel[] models = getModelByClass(c);
    if (models.length > 0)
      return models[0];
    return null;
  }
  
  /**
   * ��QueryModel��children List�����л�ȡ����Ϊc�ĳ��˵�һ��������ж�������
   * ���children��List�����ж���Ϊc��ֻ��һ�����򷵻ؿ�����
   * @param c ָ����ģ�Ͷ���(StatementsModel��SelectStatementModel...��
   * @return QueryModel ��������Ϊָ����ģ�Ͷ���ĵ�һ��QueryModelQueryModel
   */
  public QueryModel[] getNextModelsByClass(Class c) {
    QueryModel[] models = getModelByClass(c);
    if (models.length < 2)
      return new QueryModel[0];
    
    QueryModel[] ret = new QueryModel[models.length - 1];
    for (int i = 1; i < models.length; i++)
      ret[i-1] = models[i];
    return ret;
  }
  
  /**
   * ��QueryModel������children List�����л�ȡ����Ϊc�����ж���<br>
   * @param c ָ����ģ�Ͷ���(StatementsModel��SelectStatementModel...��
   * @return QueryModel[] ��������Ϊָ����ģ�Ͷ����������Ϣ����<br>
   */
  public QueryModel[] getModelsFromAllChildrenByClass(Class c) {
    List models = new ArrayList();
    searchChildren(models, children, c);
    QueryModel[] ret = new QueryModel[models.size()];
    int i = 0;
    for (Iterator it = models.iterator(); it.hasNext();)
      ret[i++] = (QueryModel)it.next();
    return ret;
  }
  
  /**
   * ��children��List�����в���Ϊc�Ķ��󣬴����models��
   * @param models ģ�Ͷ���Ϊc�����м�����Ϣ 
   * @param children �������е�children List����
   * @param c ָ����ģ�Ͷ���
   */
  private void searchChildren(List models, List children, Class c) {
    for (Iterator it = children.iterator(); it.hasNext();) {
      QueryModel m = (QueryModel)it.next();
      searchChildren(models, m.getChildren(), c);
      if (m.getClass() == c){
        models.add(m);
      }
    }
  }

  /**
   * ��ȡƬ���Ӿ������SQL���
   * @param segmentType Ƭ���Ӿ�����
   * @return String ����SQL���
   */
  public String getChSegment(String segmentType) {
    String ret = "";
    if (children.size() > 0)
      ret += ((QueryModel)children.get(0)).getChSegment(segmentType);
    
    if (children.size() > 1)
      for (int i = 1; i < children.size(); i++)
        ret += ((QueryModel)children.get(i)).getChSegment(segmentType);
    return ret;
  }
  
  /**
   * ��ȡƬ���Ӿ��Ӣ��SQL���
   * @param segmentType Ƭ���Ӿ�����
   * @return String Ӣ��SQL���
   */
  public String getEnSegment(String segmentType) {
    String ret = "";
    if (children.size() > 0)
      ret += ((QueryModel)children.get(0)).getEnSegment(segmentType);
    
    if (children.size() > 1)
      for (int i = 1; i < children.size(); i++)
        ret += ((QueryModel)children.get(i)).getEnSegment(segmentType);
    return ret;
  }
  
  /**
   * ��ȡ������������SQL��䣨����зָ��������Ӿ���÷ָ���������
   * @param split �ָ���
   * @return String ����SQL���
   */
  public String getChString(String split) {
    String ret = "";
    if (children.size() > 0)
      ret += ((QueryModel)children.get(0)).getChString();
    
    if (children.size() > 1)
      for (int i = 1; i < children.size(); i++)
        ret += split + ((QueryModel)children.get(i)).getChString();
    return ret;
  }
  
  /**
   * ��ȡ������������SQL���
   * @return String ����SQL���
   */
  public String getChString() {
    return getChString(" ");
  }
  
  /**
   * ��ȡ�����Ŀ�ִ��Ӣ��SQL��䣨������INTO�ı�����
   * @param intoTableName INTO������
   * @return String ��ִ��Ӣ��SQL���
   */
  public String getExecuteEnString(String intoTableName) {
    return getExecuteEnString(intoTableName);
  }
  
  /**
   * ��ȡ�յĿ�ִ��Ӣ��SQL��䣨���������ݲ��������������INTO�ı�����
   * @param intoTableName INTO������
   * @return String �յĿ�ִ��Ӣ��SQL���
   */
  public String getEmptyExecuteEnString(String intoTableName) {
    return getEmptyExecuteEnString(intoTableName);
  }
  
  /**
   * ��ȡ��������Ӣ��SQL��䣨����зָ��������Ӿ���÷ָ���������
   * @param split �ָ���
   * @return String Ӣ��SQL���
   */
  public String getEnString(String split) {
    String ret = "";
    if (children.size() > 0)
      ret += ((QueryModel)children.get(0)).getEnString();
    if (children.size()>1)
      for (int i=1; i<children.size(); i++)
        ret+=split+((QueryModel)children.get(i)).getEnString();
    return ret;
  }
  
  /**
   * ��ȡ��������Ӣ��SQL���
   * @return String Ӣ��SQL���
   */
  public String getEnString() {
    return getEnString(" ");
  }

  /**
   * ��ȡ��ѯ����Ӣ��SQL���
   * @return String Ӣ��SQL���
   */
  public String getEnQuery() {
    return getEnString();
  }
  
  /**
   * ��ȡ��ѯ��������еı�����Ϣ�����ر���Ϣ��������
   * @return TableModel[] ����Ϣ��������
   */
  public TableModel[] getTables() {
    QueryModel[] models = getModelsFromAllChildrenByClass(TableModel.class);
    TableModel[] ret = new TableModel[models.length];
    for (int i = 0; i < models.length; i++)
        ret[i] = (TableModel)models[i];
    return ret;
  }
  
  /**
   * ��ȡ��ѯ��������е��ֶ���Ϣ�������ֶ�������Ϣ��������
   * @return FieldModel[] �ֶ�������Ϣ��������
   */
  public FieldModel[] getFields() {
    QueryModel[] models = getModelsFromAllChildrenByClass(FieldModel.class);
    
    FieldModel[] _fieldModelArr = new FieldModel[models.length]; //HJD Add
    //QueryModel[] aliases=getModelsFromAllChildrenByClass(AliasModel.class);
    boolean isAlias = false;
    List fields = new ArrayList();
    for (int i = 0; i < models.length; i++) {
      //for (int j=0; j<aliases.length; j++) {
        FieldModel f = (FieldModel) models[i];
        _fieldModelArr[i] = f;
        //AliasModel a=(AliasModel)aliases[j];
        //if (f.getFieldName().equals(a.getAlias())) {
          //f.setFieldEnName(f.getFieldName());
          //isAlias=true;
        //}
      //}
      //if (!isAlias)
        //fields.add(models[i]);
    }
    return _fieldModelArr;
    /*
    int i=0;
    FieldModel[] ret=new FieldModel[fields.size()];
    for (Iterator it=fields.iterator(); it.hasNext(); )
      ret[i++]=(FieldModel)it.next();
    return ret;
    */
  }
  
  /**
   * �жϱ����������﷨����ʱ�Ƿ���ڴ���
   * @return boolean��true��ʾ���ڴ���false��ʾ�����ڴ���
   */
  public boolean hasError() {
    return antlrExceptions.size() > 0;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(ANTLRException exception) {
    ChWrongMessage ret=null;
    if (exception instanceof CharStreamIOException)
      ret = translateException((CharStreamIOException)exception);
    else if (exception instanceof MismatchedCharException)
      ret = translateException((MismatchedCharException)exception);
    else if (exception instanceof MismatchedTokenException)
      ret = translateException((MismatchedTokenException)exception);
    else if (exception instanceof NoViableAltException)
      ret = translateException((NoViableAltException)exception);
    else if (exception instanceof NoViableAltForCharException)
      ret = translateException((NoViableAltForCharException)exception);
    else if (exception instanceof SemanticException)
      ret = translateException((SemanticException)exception);
    else if (exception instanceof TokenStreamIOException)
      ret = translateException((TokenStreamIOException)exception);
    else if (exception instanceof TokenStreamRecognitionException)
      ret = translateException((TokenStreamRecognitionException)exception);
    else if (exception instanceof TokenStreamRetryException)
      ret = translateException((TokenStreamRetryException)exception);
    else if (exception instanceof TableNumberException)
    	ret = translateException((TableNumberException)exception);
    else if (exception instanceof NoSuchTableException)
      ret = translateException((NoSuchTableException)exception);
    else if (exception instanceof NoSuchFieldException)
      ret = translateException((NoSuchFieldException)exception);
    else if (exception instanceof TableNotInFromClause)
      ret = translateException((TableNotInFromClause)exception);
    else if (exception instanceof NoGroupExistsException)
    	ret = translateException((NoGroupExistsException)exception);
    else
      ret = translateException((Exception)exception);
    return ret;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(CharStreamIOException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(MismatchedCharException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ����Ƿ�����ȷ��
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(MismatchedTokenException exception) {
    ChWrongMessage msg = new ChWrongMessage();
    msg.setLine(exception.line);
    msg.setColumn(exception.column);
    String expecting = "";
    
    if (databaseType != null) {
    	if (databaseType.equals(DataBaseType.SYBASE_ASE_12) || databaseType.equals(DataBaseType.SYBASE_IQ_12)){
    		expecting = sybaseIQ12Parser.getTokenName(exception.expecting);
    	} else if (databaseType.equals(DataBaseType.ORACLE8i) || databaseType.equals(DataBaseType.ORACLE9i)){
    		expecting = oracle9iParser.getTokenName(exception.expecting);
    	}else{
    		expecting = sybaseIQ12Parser.getTokenName(exception.expecting);
    	}
  	}
    
    String input = exception.token.getText();
    
    ErrorLexer errorLexer = new ErrorLexer();
    Map lexersMap = errorLexer.getLexersMap();
    for (Iterator it = lexersMap.keySet().iterator(); it.hasNext();){
      String lexerName = (String) it.next();
      String lexerValue = (String) lexersMap.get(lexerName);
      expecting = StringUtil.replace(expecting, lexerName, lexerValue);
    }
    
    String message=
      "��Ҫ \""+expecting+"\" "+
      " ʵ������ \""+input+"\"��";
    msg.setMessage(message);
    if (input != null && input.equals("") && input.length() > 0)
      msg.setLength(input.length());
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ���޷�ʶ��Ĺؼ��֣�
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(NoViableAltException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    String token=exception.token.getText();
    msg.setMessage("�޷�ʶ��Ĺؼ��� \""+token+"\"��");
    msg.setLine(exception.line);
    msg.setColumn(exception.column);
    msg.setLength((token==null)?0:token.length());
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(NoViableAltForCharException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(1);
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(SemanticException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(2);
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(TokenStreamIOException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(3);
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(TokenStreamRecognitionException exception) {
    
    ChWrongMessage msg=new ChWrongMessage();
    msg.setLine(exception.recog.line);
    msg.setColumn(exception.recog.column);
    msg.setLength(-1);
    
    char ch=0;
    int m = 1;
    boolean isChFlag = false;
    while (!isChFlag){
      try {
        ch = chQuery.charAt(msg.getColumn() - m);
        isChFlag = true;
      }catch(Exception ex){
        m++;
      }
    }
    
    msg.setMessage("�Ƿ��ַ� \""+ch+"\"��");
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(TokenStreamRetryException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(5);
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ����ĸ��������ڣ�
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(TableNumberException exception) {
  	ChWrongMessage msg = new ChWrongMessage();
    msg.setMessage("��ĸ�����һ�£���Ҫ�ı�ĸ���Ϊ"+ exception.getExistTableNumber() + 
    		"����ʵ�ʴ���ı�ĸ���Ϊ" + exception.getInputTableNumber() + "��");
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ������ı����ڣ�
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(NoSuchTableException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage("�����ڱ� \""+exception.getTableName()+"\"��");
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ�����в�������������ֶΣ�
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(NoSuchFieldException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(
        "�� \""+exception.getTableChName()+"\" �в������ֶ� \""+exception.getFieldChName()+"\"��");
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ��SELECT���ֶ��еı�û����FROM�г��֣�
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(TableNotInFromClause exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(
        "�� \""+exception.getTableName()+"\" û���� [����] �γ��֡�");
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ����ĸ��������ڣ�
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(NoGroupExistsException exception) {
  	ChWrongMessage msg = new ChWrongMessage();
  	if (exception.getExType() != null && !exception.getExType().equals("") && 
  			exception.getExType().equals(exception.EXPR_EXISTS_ERROR))
  		msg.setMessage("������[����]�Ӿ������б�ı��ʽ�У�����ʹ�þۺϻ��Ӳ�ѯ��\""+ exception.getSelectExpr() + "\" ���ܳ��֡�");
  	else
  		msg.setMessage("[��ѯ]�Ӿ� \""+ exception.getSelectExpr() + "\" ��ѡ���б�����Ч����Ϊδ�����ھۺϺ����У�����û����[����]�Ӿ��г��֡�");
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * @param exception ANTLR�쳣��Ϣ������ʶ���쳣��
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(Exception exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage("�﷨���������з�����������ϵͳ����Ա��ϵ��");
    return msg;
  }
  
  /**
   * ����ANTLR�쳣��Ϣ��������antlrExceptions��List������
   * @param e ANTLRException ANTLR�쳣��Ϣ
   */
  public void addException(ANTLRException e) {
    antlrExceptions.add(e);
  }
  
  /**
   * ��ȡ���д�����Ϣ��ChWrongMessage[]�����������
   * @return ChWrongMessage[] �����������
   */
  public ChWrongMessage[] getWrongMessages() {
    ChWrongMessage[] ret = new ChWrongMessage[antlrExceptions.size()];
    int i = 0;
    for (Iterator it = antlrExceptions.iterator(); it.hasNext();){
      ret[i] = (translateException((ANTLRException)it.next()));
      i++;
    }
    return ret;
  }

}
