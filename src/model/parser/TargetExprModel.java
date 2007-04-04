package model.parser;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import parser.ExpressionLexer;
import parser.ExpressionParser;
import parser.ExpressionTreeParser;
import util.StringUtil;

public class TargetExprModel {
  private ExpressionParser parser;
  private ExpressionLexer lexer;
  private Map exprElemMap = new HashMap();
  private List antlrExceptions = new ArrayList();
  private String exprContent;
  private String exprExeContent;

  public static TargetExprModel parseTargetExpr(String exprContent) {
    TargetExprModel _model = null;
    List antlrExs = new ArrayList();
    Map _exprElemLi = new HashMap();
    ExpressionLexer expressionLexer = new ExpressionLexer(new StringReader(exprContent));
    ExpressionParser expressionParser = new ExpressionParser(expressionLexer);
    try{
      expressionParser.expr();
      CommonAST ast = (CommonAST) expressionParser.getAST();
      // TODO Visible ASTFrame
//      ASTFrame _ASTFrame = new ASTFrame("ExprTreeParser", ast);
//      _ASTFrame.setVisible(true);
      ExpressionTreeParser expressionTreeParser = new ExpressionTreeParser();
      exprContent = expressionTreeParser.expr(ast);
      _exprElemLi = expressionTreeParser.getExprElemLi();
    }catch (ANTLRException ex){
      antlrExs.add(ex);
    }

    if (_model == null){
      _model = new TargetExprModel();
    }
    _model.setExprContent(exprContent);
    _model.setExprElemMap(_exprElemLi);
    _model.setLexer(expressionLexer);
    _model.setParser(expressionParser);
    _model.setAntlrExceptions(antlrExs);
    return _model;
  }

  public static BigDecimal ExcuteTargetExpr(TargetExprModel _model) {
    BigDecimal rValue = new BigDecimal("0");
    List antlrExs = new ArrayList();
    ExpressionLexer expressionLexer = new ExpressionLexer(new StringReader(_model.getExprContent()));
    ExpressionParser expressionParser = new ExpressionParser(expressionLexer);
    try{
      expressionParser.expr();
      CommonAST ast = (CommonAST) expressionParser.getAST();
      ExpressionTreeParser expressionTreeParser = new ExpressionTreeParser();
      expressionTreeParser.setExprElemLi(_model.getExprElemMap());
      rValue = expressionTreeParser.exprExe(ast);
    }catch (ANTLRException ex){
      antlrExs.add(ex);
    }
    return rValue;
  }

  /**
   * �жϱ����������﷨����ʱ�Ƿ���ڴ���
   * 
   * @return boolean��true��ʾ���ڴ���false��ʾ�����ڴ���
   */
  public boolean hasError() {
    return antlrExceptions.size() > 0;
  }

  /**
   * ��ȡ���д�����Ϣ��ChWrongMessage[]�����������
   * 
   * @return ChWrongMessage[] �����������
   */
  public ChWrongMessage[] getWrongMessages() {
    ChWrongMessage[] ret = new ChWrongMessage[antlrExceptions.size()];
    int i = 0;
    for (Iterator it = antlrExceptions.iterator(); it.hasNext();){
      ret[i] = (translateException((ANTLRException) it.next()));
      i++;
    }
    return ret;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(ANTLRException exception) {
    ChWrongMessage ret = null;
    if (exception instanceof CharStreamIOException)
      ret = translateException((CharStreamIOException) exception);
    else if (exception instanceof MismatchedCharException)
      ret = translateException((MismatchedCharException) exception);
    else if (exception instanceof MismatchedTokenException)
      ret = translateException((MismatchedTokenException) exception);
    else if (exception instanceof NoViableAltException)
      ret = translateException((NoViableAltException) exception);
    else if (exception instanceof NoViableAltForCharException)
      ret = translateException((NoViableAltForCharException) exception);
    else if (exception instanceof SemanticException)
      ret = translateException((SemanticException) exception);
    else if (exception instanceof TokenStreamIOException)
      ret = translateException((TokenStreamIOException) exception);
    else if (exception instanceof TokenStreamRecognitionException)
      ret = translateException((TokenStreamRecognitionException) exception);
    else if (exception instanceof TokenStreamRetryException)
      ret = translateException((TokenStreamRetryException) exception);
    else
      ret = translateException((Exception) exception);
    return ret;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(CharStreamIOException exception) {
    ChWrongMessage msg = new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(MismatchedCharException exception) {
    ChWrongMessage msg = new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ����Ƿ�����ȷ��
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

    String message = "�������룬��Ҫ \"" + expecting + "\" " + " ʵ������ \"" + input + "\"��";
    msg.setMessage(message);
    if (input != null && input.equals("") && input.length() > 0)
      msg.setLength(input.length());
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ���޷�ʶ��Ĺؼ��֣�
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(NoViableAltException exception) {
    ChWrongMessage msg = new ChWrongMessage();
    String token = exception.token.getText();
    msg.setMessage("�޷�ʶ��Ĺؼ��� \"" + token + "\"��");
    msg.setLine(exception.line);
    msg.setColumn(exception.column);
    msg.setLength((token == null) ? 0 : token.length());
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(NoViableAltForCharException exception) {
    ChWrongMessage msg = new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(1);
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(SemanticException exception) {
    ChWrongMessage msg = new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(2);
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(TokenStreamIOException exception) {
    ChWrongMessage msg = new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(3);
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(TokenStreamRecognitionException exception) {
    ChWrongMessage msg = new ChWrongMessage();
    msg.setLine(exception.recog.line);
    msg.setColumn(exception.recog.column);
    msg.setLength(-1);
    char ch = 0;
    ch = exprContent.charAt(msg.getColumn() - 1);
    msg.setMessage("�Ƿ��ַ� \"" + ch + "\"��");
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(TokenStreamRetryException exception) {
    ChWrongMessage msg = new ChWrongMessage();
    msg.setMessage(exception.getMessage());
    msg.setLine(5);
    return msg;
  }

  /**
   * ����ANTLR�쳣��Ϣ��ҵ�񻯴�����Ϣ����ChWrongMessage
   * 
   * @param exception
   *          ANTLR�쳣��Ϣ������ʶ���쳣��
   * @return ChWrongMessage ������Ϣ����
   */
  private ChWrongMessage translateException(Exception exception) {
    ChWrongMessage msg = new ChWrongMessage();
    msg.setMessage("�﷨���������з�����������ϵͳ����Ա��ϵ��");
    return msg;
  }

  public ExpressionLexer getLexer() {
    return lexer;
  }

  public void setLexer(ExpressionLexer lexer) {
    this.lexer = lexer;
  }

  public ExpressionParser getParser() {
    return parser;
  }

  public void setParser(ExpressionParser parser) {
    this.parser = parser;
  }

  public List getAntlrExceptions() {
    return antlrExceptions;
  }

  public void setAntlrExceptions(List antlrExceptions) {
    this.antlrExceptions = antlrExceptions;
  }

  public Map getExprElemMap() {
    return exprElemMap;
  }

  public void setExprElemMap(Map exprElemMap) {
    this.exprElemMap = exprElemMap;
  }

  public String getExprContent() {
    return exprContent;
  }

  public void setExprContent(String exprContent) {
    this.exprContent = exprContent;
  }

  public String getExprExeContent() {
    return exprExeContent;
  }

  public void setExprExeContent(String exprExeContent) {
    this.exprExeContent = exprExeContent;
  }

}
