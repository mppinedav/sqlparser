package test;

import java.io.StringReader;
import java.util.regex.Pattern;

import parser.L;
import parser.P;
import parser.T;
import translator.Translator;
import translator.model.ChWrongMessage;
import translator.model.DbTable;
import translator.model.QueryModel;

import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.TokenStreamException;

public class TestMain {
  public static void main(String[] args) {
    TestMain main=new TestMain();
    main.testSelect();
//    main.testTranslator();
//    main.testEquations();
    
//    String str="a[bc]abcdede";
//    String from=Pattern.quote("[bc]");
//    String to="_[bc]_";
//    System.out.println(from+" "+to+" "+str.replaceAll(from, to));
  }
  
  private void testSelect() {
    String selectStr=
      "select *, t1.f1, t1.f2, t2.f1, (t2.f2+t1.f1-20)/(3+3)+4*2 " +
      "from t1, t2, t3 " +
      "where t1.f1=t2.f1 and t1.f2 like 'asdf' or t1.f1=t2.f2";
//      "select ** from t1 where t1.f1 like 'asdf'";
    System.out.println(selectStr);
    L lexer=new L(new StringReader(selectStr));
    P parser=new P(lexer);
    try {
      parser.statement();
      CommonAST t=(CommonAST)parser.getAST();
      System.out.println(t.toStringList());
      T tree=new T();
      System.out.println(tree.statement(t).toString());
      
    } catch (RecognitionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (TokenStreamException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  private void testTranslator() {
    String[] testStr=new String[] {
        "���� ��1�� ��2 �浽 ��ʱ��",
        "�Ƚ� ��1����2 �浽 tt ���� ���� a.�ֶ�6 ���� b.�ֶ�2 ���� a.�ֶ�2 ���� 'abcd' ���� b.�ֶ�1 ���� 20",
        "��ѯ ���� ���� ��1 ���� ��1.�ֶ�1 ���� 1 ���� ��1.�ֶ�2 ���� 'abcd'"
    };
    
    Translator translator=new Translator();
    translator.getChKeywords();
    for (int i=0; i<testStr.length; i++) {
      translator.setChQuery(testStr[i]);
      DbTable[] tables=translator.getTables();
//    set table info
      for (int j=0; j<tables.length; j++) {
        tables[j].setEnName("table"+j);
        tables[j].addDbField("�ֶ�1", "field1");
        tables[j].addDbField("�ֶ�2", "field2");
        tables[j].addDbField("�ֶ�3", "field3");
        tables[j].addDbField("�ֶ�4", "field4");
        tables[j].addDbField("�ֶ�5", "field5");
        tables[j].addDbField("�ֶ�6", "field6");
      }
      translator.setTableInfo(tables);
      if (translator.hasError()) {
        ChWrongMessage[] msgs=translator.showWrongMsgs();
        for (int j=0; j<msgs.length; j++)
          System.out.println(msgs[j]);
        continue;
      } else {
        System.out.println(translator.getChQuery());
        QueryModel model=translator.getQueryModel();
        System.out.println(model.getEnQuery());
      }
    }
  }
  
  private void testEquations() {
    String equation="a.��/��6 ���� b.�ֶ�3 �� a.�ֶ�2 ���� 'abcd' �� b.�ֶ�1 ���� 'abcd'";
    Translator translator=new Translator();
    translator.setChEquation(equation);
    
    DbTable[] tables=translator.getTables();
//    DbTable[] tables=new DbTable[] {
//        new DbTable("a", null),
//        new DbTable("b", null)
//    };
//  set table info
    for (int j=0; j<tables.length; j++) {
      tables[j].setEnName("table"+j);
      tables[j].addDbField("�ֶ�1", "field1");
      tables[j].addDbField("�ֶ�2", "field2");
      tables[j].addDbField("�ֶ�3", "field3");
      tables[j].addDbField("�ֶ�4", "field4");
      tables[j].addDbField("�ֶ�5", "field5");
      tables[j].addDbField("��/��6", "field6");
    }
    translator.setTableInfo(tables);

    if (translator.hasError()) {
      ChWrongMessage[] msgs=translator.showWrongMsgs();
      for (int j=0; j<msgs.length; j++)
        System.out.println(msgs[j]);
    } else {
      System.out.println(translator.getChQuery());
      System.out.println(translator.getQueryModel().getEnQuery());
    }
  }
}
