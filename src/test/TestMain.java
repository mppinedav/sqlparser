package test;

import java.io.StringReader;

import parser.GSL;
import parser.GSP;
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
//    main.gettingStart();
    main.testSelect();
//    main.testTranslator();
//    main.testFragment();
//    System.out.println("a"+(char)32+"b");
  }
  
  private void gettingStart() {
    String str="'test string";
    GSL lexer=new GSL(new StringReader(str));
    GSP parser=new GSP(lexer);
    try {
      parser.startRule();
    } catch (RecognitionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (TokenStreamException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private void testSelect() {
    String selectStr=
//      "select *, t1.f1, t1.f2, t2.f1, (t2.f2+t1.f1-20)/(3+3)+4*2, avg(adf.f1, f1.t1), t1.faf " +
//      "from t1, t2, t3 " +
//      "where t1.f1=t2.f1 and t1.f2 like 'adf' or t1.f1=t2.f2 " +
//      "group by t1.f1, t2.f2";
//      "select * from t1 where 1=1 union select * from t2 where 2=2";
      "select *, t1.f1 as f from t1 where 1=1 order by t1.f1";
    
    System.out.println(selectStr);
    L lexer=new L(new StringReader(selectStr));
    P parser=new P(lexer);
    try {
      parser.statement();
      CommonAST t=(CommonAST)parser.getAST();
      System.out.println(t.toStringList());
      T tree=new T();
      System.out.println(tree.statement(t));
      
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
        "��ϲ� ��1����2 �浽 ��ʱ��",
        "��Ƚ� ��1����2 �浽 tt ���� ������ a.�ֶ�6 ���� b.�ֶ�2 ���� a.�ֶ�2 ���� 'abcd' ���� b.�ֶ�1 ���� 20",
        "��ѯ ���� ���� ��1 ���� ��1.�ֶ�1 ���� 1 ���� ��1.�ֶ�2 ���� 'abcd' ���� ��1.�ֶ�2 ���� ��.�ֶ�1",
        "��ѯ AI_94��Ʊ���ձ�.�к�, AI_94��Ʊ���ձ�.������ ���� AI_94��Ʊ���ձ� ���� AI_94��Ʊ���ձ�.�к� ���� '01' ���� AI_94��Ʊ���ձ�.�к� ���� AI_94��Ʊ���ձ�.�к�",
        "��ѯ AI_94��Ʊ���ձ�.�к�, AI_94��Ʊ���ձ�.������, AI_94��Ʊ���ձ�.ҵ�����  ���� AI_94��Ʊ���ձ� ���� AI_94��Ʊ���ձ�.�к� ����",
        "��ѯ [AI_94��Ʊ���ձ�.�к�] ��Ϊ tf ���� [AI_94��Ʊ���ձ�] ���� [AI_94��Ʊ���ձ�.�к�] ���� \"8047\" ���� [AI_94��Ʊ���ձ�.�к�] ���� [AI_94��Ʊ���ձ�.�к�]"
    };
    
    Translator translator=new Translator();
    for (int i=0; i<testStr.length; i++) {
      translator.setChQuery(testStr[i]);
      System.out.println(translator.getChQuery());
      DbTable[] tables=translator.getTables();
//    set table info
      for (int j=0; j<tables.length; j++) {
        tables[j].setEnName("table"+j);
        tables[j].addDbField("�ֶ�1", "field1");
        tables[j].addDbField("�ֶ�2", "field2");
        tables[j].addDbField("�ֶ�3", "field3");
        tables[j].addDbField("�ֶ�4", "field4");
        tables[j].addDbField("�����־", "field5");
        tables[j].addDbField("������", "field6");
        tables[j].addDbField("�к�", "field7");
        tables[j].addDbField("ʡ/�д���", "field8");
        tables[j].addDbField("ҵ�����", "field9");
        tables[j].addDbField("��˾����", "field10");
        tables[j].addDbField("�ʺ�", "field11");
        tables[j].addDbField("���", "field12");
      }
      translator.setTableInfo(tables);
      if (translator.hasError()) {
        ChWrongMessage[] msgs=translator.showWrongMsgs();
        for (int j=0; j<msgs.length; j++)
          System.out.println(msgs[j]);
        continue;
      } else {
        QueryModel model=translator.getQueryModel();
        System.out.println(model.getEnQuery());
      }
    }
  }
  
  private void testFragment() {
    String equation="[a.��/��6] ���� [b.�ֶ�3] ���� [a.�ֶ�2] ���� 'abcd' ���� [b.�ֶ�1] ���� 'abcd'";
    String columnList="[a.��/��6] �� [b.�ֶ�2]+([a.�ֶ�2])��[a.�ֶ�2]��[b.�ֶ�1]";
    String column="[a.��/��6] �� [b.�ֶ�2]+([a.�ֶ�2])";
    Translator translator=new Translator();
    translator.setChSegment(Translator.COLUMN, column);
    System.out.println(translator.getChQuery());
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
      System.out.println(translator.getQueryModel().getEnQuery());
    }
  }
}
