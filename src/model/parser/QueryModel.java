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

import parser.L;
import parser.P;
import parser.T;
import translator.DbTableInfo;
import util.StringUtil;

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

/**
 * ����������ģ��QueryModel�����﷨����ʵ����
 *
 */
public class QueryModel {
  private static final String KEYWORDS = "keywords";  //�ؼ��������ļ�����
  private static Map mapKeyword = new HashMap();      //��Źؼ��ֵ�HashMap
  
  private List children = new ArrayList();
  private P parser;
  private L lexer;
  private T tree;
  private DbTableModel dbTableModel = new DbTableModel();
  private String chQuery;
  private String circleType;  //ѭ���������(�����/��������)
  private List antlrExceptions=new ArrayList();
  
  static {
    //��ȡ������Ӣ�Ĺؼ��ֲ��洢��HashMap��mapKeyword��
    ResourceBundle bundle =  ResourceBundle.getBundle(KEYWORDS, Locale.CHINESE);
    Enumeration keys = bundle.getKeys();
    while (keys.hasMoreElements()) {
      String key = keys.nextElement().toString();
      String val = bundle.getString(key);
      mapKeyword.put(key, val);
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
  
  protected QueryModel() {}
  
  /**
   * ����SQL����������������ȷʱ����QueryModel����
   * @param chQuery ����SQL���
   * @return QueryModel ������QueryModel����
   */
  public static QueryModel parseQuery(String chQuery) {
    QueryModel model = null;
    List exs = new ArrayList();
    
    L l = new L(new StringReader(chQuery));
    P p = new P(l);
    try {
      p.statements();
      CommonAST ast = (CommonAST)p.getAST();
      T t = new T();
      //TODO Visible ASTFrame
//      ASTFrame _ASTFrame = new ASTFrame("longtopParser", ast);
//      _ASTFrame.setVisible(true);
      model = t.statement(ast);
    } catch (ANTLRException e) {
      exs.add(e);
    }
    
    if (model == null){
      model = new QueryModel();
    }
    model.setParser(p);
    model.setLexer(l);
    model.setExceptions(exs);
    model.setQuery(chQuery);
    QueryModel[] _paramModelArr = model.getModelsFromAllChildrenByClass(ParamModel.class);
    if (_paramModelArr.length > 0)
      model.setCircleType(((ParamModel) _paramModelArr[0]).getCircleType());
    
    //===SELECT�Ӿ��зǾۺϺ������ʽ������GROUP BY�Ӿ��г���===//
    Map aFunMap = new LinkedHashMap();	//�ۺϺ���Map
    //��ȡ���оۺϺ���Model����
    QueryModel[] _aggregateFunModelArr = model.getModelsFromAllChildrenByClass(AggregateFuncModel.class);
    // ѭ����ȡ���оۺϺ���Model���������Ϣ
		for (int i = 0; i < _aggregateFunModelArr.length; i++) {
			System.out.println(_aggregateFunModelArr[i].getChString());
			aFunMap.put(_aggregateFunModelArr[i].getChString(), "0");
			QueryModel apm = _aggregateFunModelArr[i].getFirstModelByClass(ParametersModel.class);
			// �õ�ÿ�����Ϻ��������в������ʽģ��
			QueryModel expm = apm.getFirstModelByClass(ExpressionModel.class);
			QueryModel[] expms = apm.getModelByClass(ExpressionModel.class);
			// �õ�ÿ�����Ϻ����������ֶ�ģ��
			QueryModel[] fdms = apm.getModelsFromAllChildrenByClass(FieldModel.class);
		}
    
		Map nGroupExprMap = new LinkedHashMap();	//��Ҫ����ı��ʽMap
		//��ȡSELECT�Ӿ��µ����б��ʽ
		QueryModel[] _columnModelArr = model.getModelsFromAllChildrenByClass(ColumnModel.class); 
		for (int i = 0; i < _columnModelArr.length; i++){
			ColumnModel _columnModel = (ColumnModel) _columnModelArr[i];
			QueryModel expm =  _columnModel.getFirstModelByClass(ExpressionModel.class);
			System.out.println(expm.getChString());
			if (aFunMap.containsKey(expm.getChString())){
				aFunMap.put(expm.getChString(), "1");
			}else{
				nGroupExprMap.put(expm.getChString(), "0");
			}
    }
		
		 //GROUP BY�б�
    QueryModel _groupByListModel = model.getFirstModelByClass(AggregateExprListModel.class);
    QueryModel[] _groupByExprModelArr;
    if (_groupByListModel == null){
    	_groupByExprModelArr = new QueryModel[0];
    }else{
    	_groupByExprModelArr = _groupByListModel.getModelByClass(AggregateExprModel.class);
    }
    
    for (int i = 0; i < _groupByExprModelArr.length; i++){
    	if (nGroupExprMap.containsKey(_groupByExprModelArr[i].getChString())){
    		nGroupExprMap.put(_groupByExprModelArr[i].getChString(), "1");
    	}
    }
    
    for (Iterator it = nGroupExprMap.keySet().iterator(); it.hasNext();){
    	String selectExpr = (String) it.next();
    	if (((String)nGroupExprMap.get(selectExpr)).equals("0")){
    		NoGroupExistsException _exception = new NoGroupExistsException(selectExpr);
    		exs.add(_exception);
    	}
    }
    
    // �õ����к���ģ��(����һ�㺯���;ۺϺ���)
    //QueryModel[] _allFunctionModelArr = model.getModelsFromAllChildrenByClass(FunctionModel.class);
    //===SELECT�Ӿ��зǾۺϺ������ʽ������GROUP BY�Ӿ��г���===//
    
    return model;
  }
  
  /**
   * Ƭ���Ӿ��﷨��֤����������ȷ�Ƿ���QueryModel����
   * @param chSegment Ƭ���Ӿ�
   * @return QueryModel ������QueryModel����
   */
  public static QueryModel parseSegment(String chSegment) {
    QueryModel model = null;
    
    List exs = new ArrayList();
    L l = new L(new StringReader(chSegment));
    P p = new P(l);
    try {
      p.segment();
      CommonAST ast = (CommonAST)p.getAST();
      T t = new T();
      model = t.segment(ast);
    } catch (ANTLRException e) {
      exs.add(e);
    }
    
    if (model == null){
      model = new QueryModel();
    }
    model.setParser(p);
    model.setLexer(l);
    model.setExceptions(exs);
    model.setQuery(chSegment);
    
    return model;
  }
  
  /**
   * �������ԭʼҵ�񻯲�ѯ��䱣����QueryModel��chQuery������
   * @param query ҵ�񻯲�ѯ
   */
  public void setQuery(String query) {
    this.chQuery = query;
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
    children.add(new StringModel(op));
  }
  
  public List getChildren() {
    return this.children;
  }
  
  public void setParser(P parser) {
    this.parser = parser;
  }
  
  public void setLexer(L lexer) {
    this.lexer = lexer;
  }
  
  public void setTree(T tree) {
    this.tree = tree;
  }
  
  public void setExceptions(List antlrExceptions) {
    this.antlrExceptions = antlrExceptions;
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
    String expecting = parser.getTokenName(exception.expecting);
    String input = exception.token.getText();
    
    ErrorLexer errorLexer = new ErrorLexer();
    Map lexersMap = errorLexer.getLexersMap();
    for (Iterator it = lexersMap.keySet().iterator(); it.hasNext();){
      String lexerName = (String) it.next();
      String lexerValue = (String) lexersMap.get(lexerName);
      expecting = StringUtil.replace(expecting, lexerName, lexerValue);
    }
    
    String message=
      "�������룬��Ҫ \""+expecting+"\" "+
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
    msg.setMessage("[��ѯ]�Ӿ� \""+ exception.getSelectExpr() + "\" û����[����]�Ӿ��г��֡�");
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
