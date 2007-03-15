package translator;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import parser.L;
import parser.P;
import parser.T;
import translator.model.ChWrongMessage;
import translator.model.DbField;
import translator.model.DbTable;
import translator.model.EquationModel;
import translator.model.NoSuchFieldException;
import translator.model.NoSuchTableException;
import translator.model.QueryModel;

import antlr.ANTLRException;
import antlr.CharStreamIOException;
import antlr.CommonAST;
import antlr.MismatchedCharException;
import antlr.MismatchedTokenException;
import antlr.NoViableAltException;
import antlr.NoViableAltForCharException;
import antlr.RecognitionException;
import antlr.SemanticException;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.TokenStreamRetryException;

/**
 * 
 * 
 * @author Meatball.Li
 * 
 */
public class Translator {
  private static final String KEYWORD_TRANSLATE_TABLE = "keyword_translate_table";
  private String chQuery;
  private String enQuery;
  private Map mapKeyword = new HashMap();
  private Map mapEn2Ch = new HashMap();
  private Map mapPosEn2Ch = new HashMap();
  private List antlrExceptions = new ArrayList();
  private List chWrongMessages = new ArrayList();
  private P parser;
  private L lexer;
  private T tree;
  private QueryModel queryModel=null;

  public Translator() {
    initKeywordMap();
  }

  private void initKeywordMap() {
    ResourceBundle bundle = 
      ResourceBundle.getBundle(KEYWORD_TRANSLATE_TABLE, Locale.CHINESE);
    Enumeration keys = bundle.getKeys();
    while (keys.hasMoreElements()) {
      String key = keys.nextElement().toString();
      String val = bundle.getString(key);
      mapKeyword.put(val, key);
    }
  }

  public String[] getChKeywords() {
    String[] ret=new String[mapKeyword.size()];
    int i=0;
    for (Iterator it=mapKeyword.keySet().iterator(); it.hasNext();)
      ret[i++]=(String)it.next();
    return ret;
  }
/*
  private String getEnQuery() {
    return enQuery;
  }

  private void setEnQuery(String enQuery) {
    this.enQuery = enQuery;
  }

  private Map getMapEn2Ch() {
    return mapEn2Ch;
  }

  private void setMapEn2Ch(Map mapEn2Ch) {
    this.mapEn2Ch = mapEn2Ch;
  }

  private Map getMapKeyword() {
    return mapKeyword;
  }

  private void setMapKeyword(Map mapKeyword) {
    this.mapKeyword = mapKeyword;
  }
*/

  /**
   * ��ȡ��ǰ��ѯ�����������ݿ������б�
   * ҵ��ϵͳ��Ҫ�����������������ı�����б������ݿ��л�ȡÿ������Ӧ��Ӣ����
   * @return
   */
  public DbTable[] getTables() {
    return tree.getTables();
  }

  /**
   * ��ȡ�����Ϣ��Ӣ�ı������ֶ���������Ϣ���û�queryModel
   * @param tables
   */
  public void setTableInfo(DbTable[] tables) {
    for (int i=0; i<tables.length; i++) {
      if (tables[i].getEnName()==null)
        antlrExceptions.add(new NoSuchTableException(tables[i].getChName()));
      DbField[] fields=tables[i].getFields();
      for (int j=0; j<fields.length; j++) {
        if (fields[j].getEnName()==null)
          antlrExceptions.add(
              new NoSuchFieldException(tables[i].getChName(), fields[j].getChName()));
      }
      if (queryModel!=null)
        queryModel.addTableInfo(tables[i]);
    }
  }

/*
  private List getAntlrExceptions() {
    return antlrExceptions;
  }

  private void setAntlrExceptions(List antlrExceptions) {
    this.antlrExceptions = antlrExceptions;
  }

  private List getChWrongMessages() {
    return chWrongMessages;
  }

  private void setChWrongMessages(List chWrongMessages) {
    this.chWrongMessages = chWrongMessages;
  }

  private L getLexer() {
    return lexer;
  }

  private void setLexer(L lexer) {
    this.lexer = lexer;
  }

  private Map getMapPosEn2Ch() {
    return mapPosEn2Ch;
  }

  private void setMapPosEn2Ch(Map mapPosEn2Ch) {
    this.mapPosEn2Ch = mapPosEn2Ch;
  }

  private P getParser() {
    return parser;
  }

  private void setParser(P parser) {
    this.parser = parser;
  }
*/  
  /**
   * ��ȡ���Ĳ�ѯ��䣬�����Ҫ��������ݿ�
   * @return ���Ĳ�ѯ��� 
   */
  public String getChQuery() {
    // TODO ���chQuery==null�׳��쳣
    return chQuery;
  }

  /**
   * �������Ĳ�ѯ��䣬׼�������﷨��֤�뷭��
   * @param chQuery
   */
  public void setChQuery(String chQuery) {
    this.mapEn2Ch.clear();
    this.mapPosEn2Ch.clear();
    this.antlrExceptions.clear();
    this.chWrongMessages.clear();
    
    this.chQuery = chQuery;
    this.enQuery = "";
    translateQuery();
  }

  /**
   * ��������������䣬׼�������﷨��֤�뷭��
   * @param equation
   */
  public void setChEquation(String equation) {
    this.mapEn2Ch.clear();
    this.mapPosEn2Ch.clear();
    this.antlrExceptions.clear();
    this.chWrongMessages.clear();

    this.chQuery=equation;
    this.enQuery="";
    translateEquation();
    
    queryModel=new EquationModel(enQuery);
  }
  
  private void translateEquation() {
    if (chQuery == null || chQuery.equals("")) {
      enQuery = "";
      return;
    }
    enQuery = chQuery;

    translateKeyword();
    parseEquation();
  }

  private void parseEquation() {
    lexer=new L(new StringReader(enQuery));
    parser=new P(lexer);
    tree=new T();
    
    try {
      parser.equations();
      CommonAST parserTree=(CommonAST)parser.getAST();
      enQuery=tree.equations(parserTree);
    } catch (RecognitionException e) {
      antlrExceptions.add(e);
    } catch (TokenStreamException e) {
      antlrExceptions.add(e);
    }
  }

  public QueryModel getQueryModel() {
    return queryModel;
  }
  
  private void translateQuery() {
    if (chQuery == null || chQuery.equals("")) {
      enQuery = "";
      return;
    }
    enQuery = chQuery;
    translateKeyword();
    translateQuery2Sql();
  }

  private void translateQuery2Sql() {
    lexer=new L(new StringReader(enQuery));
    parser=new P(lexer);
    tree=new T();

    try {
      parser.statement();
      CommonAST parserTree=(CommonAST)parser.getAST();
//      enQuery=tree.statement(parserTree);
      queryModel=tree.statement(parserTree);
    } catch (RecognitionException e) {
      antlrExceptions.add(e);
    } catch (TokenStreamException e) {
      antlrExceptions.add(e);
    }
  }

  private void translateKeyword() {
    for (Iterator it = mapKeyword.keySet().iterator(); it.hasNext();) {
      String chKey = it.next().toString();
      enQuery = replace(enQuery, chKey, mapKeyword.get(chKey).toString());
    }
  }

  /**
   * ��ȡ���Ĵ�����󣬸�ǰ̨���д�����ʾ
   * @return
   */
  public ChWrongMessage[] showWrongMsgs() {
    if (chQuery == null || chQuery.equals(""))
      return new ChWrongMessage[0];
    if (antlrExceptions.size()>0)
      translateExceptions();
    ChWrongMessage[] msgs = new ChWrongMessage[chWrongMessages.size()];
    for (int i = 0; i < msgs.length; i++)
      msgs[i] = (ChWrongMessage) chWrongMessages.get(i);
    return msgs;
  }

  private String replace(String src, String from, String to) {
    String ret = src;
//    while (ret.indexOf(from) != -1) {
//      ret = ret.replace(from, to);
//      mapEn2Ch.put(to, from);
//    }
    int pos=0;
    int offset=to.length()-from.length();
    while (ret.indexOf(from, pos) != -1) {
      pos=chQuery.indexOf(from, pos);
      mapEn2Ch.put(to, from);
      int posTo=ret.indexOf(from);
      mapPosEn2Ch.put(new Integer(posTo), new Integer(offset));

      Map mapNewPos=new HashMap();
      for (Iterator it=mapPosEn2Ch.keySet().iterator(); it.hasNext();) {
        Integer p=(Integer)it.next();
        Integer o=(Integer)mapPosEn2Ch.get(p);
        if (p.intValue()>posTo)
          p=new Integer(p.intValue()+offset);
        mapNewPos.put(p, o);
      }
      mapPosEn2Ch=mapNewPos;
      ret=ret.replaceFirst(from, to);
    }
    return ret;
  }

  private void translateExceptions() {
    for (Iterator it=antlrExceptions.iterator(); it.hasNext();) {
      ChWrongMessage msg=translateException((ANTLRException)it.next());
      if (msg!=null)
        chWrongMessages.add(msg);
    }
  }

  private ChWrongMessage translateException(ANTLRException exception) {
    ChWrongMessage ret=null;
    if (exception instanceof CharStreamIOException)
      ret=translateException((CharStreamIOException)exception);
    if (exception instanceof MismatchedCharException)
      ret=translateException((MismatchedCharException)exception);
    if (exception instanceof MismatchedTokenException)
      ret=translateException((MismatchedTokenException)exception);
    if (exception instanceof NoViableAltException)
      ret=translateException((NoViableAltException)exception);
    if (exception instanceof NoViableAltForCharException)
      ret=translateException((NoViableAltForCharException)exception);
    if (exception instanceof SemanticException)
      ret=translateException((SemanticException)exception);
    if (exception instanceof TokenStreamIOException)
      ret=translateException((TokenStreamIOException)exception);
    if (exception instanceof TokenStreamRecognitionException)
      ret=translateException((TokenStreamRecognitionException)exception);
    if (exception instanceof TokenStreamRetryException)
      ret=translateException((TokenStreamRetryException)exception);
    if (exception instanceof NoSuchTableException)
      ret=translateException((NoSuchTableException)exception);
    if (exception instanceof NoSuchFieldException)
      ret=translateException((NoSuchFieldException)exception);
    return ret;
  }
  
  private ChWrongMessage translateException(CharStreamIOException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    return msg;
  }

  private ChWrongMessage translateException(MismatchedCharException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    return msg;
  }

  private ChWrongMessage translateException(MismatchedTokenException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setLine(exception.line);
    msg.setColumn(getChPos(exception.line, exception.column));
    String expecting=parser.getTokenName(exception.expecting);
    String expectingCh=translateEn2Ch(expecting);
    String input=exception.token.getText();
    String inputCh=translateEn2Ch(input);
    inputCh=inputCh==null?input:inputCh;

    String message=
      "�������룬��Ҫ \""+expectingCh+"\" "+
      " ʵ������ \""+inputCh+"\"";
    msg.setMessage(message);
    msg.setLength(inputCh.length());
    return msg;
  }

  /**
   * 
   * @param ent
   * @return
   */
  private String translateEn2Ch(String en) {
    if (en==null)
      return "";
    String ent=en.toLowerCase();
    String ret=mapEn2Ch.containsKey(ent)?mapEn2Ch.get(ent).toString():ent;
    if (ret.equals(ent) && mapKeyword.values().contains(ent))
      for (Iterator it=mapKeyword.keySet().iterator(); it.hasNext();) {
        String key=(String)it.next();
        String val=(String)mapKeyword.get(key);
        if (ret.equals(val))
          return key;
      }
    return ret;
  }

  private ChWrongMessage translateException(NoViableAltException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    String token=exception.token.getText();
    String tokenCh=translateEn2Ch(token);
    msg.setMessage("�޷�ʶ��Ĺؼ��� '"+tokenCh+"'");
    msg.setLine(exception.line);
    msg.setColumn(getChPos(exception.line, exception.column));
    msg.setLength(token.length());
    return msg;
  }

  private ChWrongMessage translateException(NoViableAltForCharException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(1);
    return msg;
  }

  private ChWrongMessage translateException(SemanticException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(2);
    return msg;
  }

  private ChWrongMessage translateException(TokenStreamIOException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(3);
    return msg;
  }

  private ChWrongMessage translateException(TokenStreamRecognitionException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setLine(exception.recog.line);
    msg.setColumn(getChPos(exception.recog.line, exception.recog.column));
    msg.setLength(-1);
    char ch=0;
    ch=chQuery.charAt(msg.getColumn()-1);
    msg.setMessage("�Ƿ��ַ�'"+ch+"'");
    return msg;
  }

  private ChWrongMessage translateException(TokenStreamRetryException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(5);
    return msg;
  }

  private ChWrongMessage translateException(NoSuchTableException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage("�����ڱ� \""+exception.getTableName()+"\"");
    return msg;
  }
  
  private ChWrongMessage translateException(NoSuchFieldException exception) {
    ChWrongMessage msg=new ChWrongMessage();
    msg.setMessage(
        "�� \""+exception.getTableChName()+"\" �в������ֶ� \""+exception.getFieldChName()+"\"");
    return msg;
  }

  private int getChPos(int line, int enPos) {
    int ret=enPos-2;
    for (int i=ret; i>=0; i--) {
      Integer intg=new Integer(i);
      Integer off=(Integer)mapPosEn2Ch.get(intg);
      ret-=(off==null)?0:off.intValue();
    }
    return ret+1;
  }

  public boolean hasError() {
    return antlrExceptions.size()>0;
  }

}
