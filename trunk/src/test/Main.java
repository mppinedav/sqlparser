package test;

import java.io.StringReader;
import java.util.Iterator;

import model.parser.AliasModel;
import model.parser.ChWrongMessage;
import model.parser.OrderAliasModel;
import model.parser.ParamModel;
import model.parser.QueryModel;
import model.parser.TableAliasModel;
import parser.L;
import parser.P;
import parser.T;
import translator.AppDbField;
import translator.AppDbTable;
import translator.DbField;
import translator.DbTable;
import translator.FromListVO;
import translator.GroupByListVO;
import translator.OrderByListVO;
import translator.SelectListVO;
import translator.Translator;
import translator.WhereListVO;
import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.TokenStreamException;

public class Main {
  public static void main(String[] args) {
  	int mNum = 0;
  	if (args.length > 0){
  		mNum = Integer.parseInt(args[0]);
  	}
  	if (mNum == 1 || mNum == 2 || mNum == 3 || mNum == 4 || mNum == 5){
  		FunctionsTestMain functionsTestMain = new FunctionsTestMain();
    	functionsTestMain.FunctionsTest(mNum);
  	}else{
  		Main main = new Main();
  		main.customQueryTest();
//  		main.testTranslator();
//      main.testUnion();
//      main.testCompare();
//      main.testSegment();
  	}
  }
 
  public void testSegment(){
    String[] segmentArr = new String[]{"���(AI_94��Ʊ���ձ�.ʡ/�д���) ��Ϊ c", "(AI_94��Ʊ���ձ�.��� �� AI_94��Ʊ���ձ�.������) �� AI_94��Ʊ�����±�.���"};
    Translator t = new Translator();
    for (int i = 0; i < segmentArr.length; i++){
      t.setChSegment(t.COLUMN, segmentArr[i]);
      DbTable[] ts = t.getTables();
      t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
      t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
      t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
      t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
      t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
      t.addDbTable("AI_94��Ʊ�����±�", "CNF_TEST");
      t.addDbField("AI_94��Ʊ�����±�", "ʡ/�д���", "CNF01");
      t.addDbField("AI_94��Ʊ�����±�", "�к�", "CNF02");
      t.addDbField("AI_94��Ʊ�����±�", "���", "CNF03");
      t.addDbField("AI_94��Ʊ�����±�", "������", "CNF04");
      t.updateDbTables(t, ts);
      if (t.hasError()){
        ChWrongMessage[] msgs = t.showWrongMsgs();
        for (int j = 0; j < msgs.length; j++){
          System.out.println(msgs[j]);
        }
      }
      System.out.println(t.getQueryModel().getChSegment(t.COLUMN));
      System.out.println(t.getQueryModel().getEnSegment(t.COLUMN));
    }
    
//    segmentArr = new String[]{"AI_94��Ʊ���ձ�.ʡ/�д��� + AI_94��Ʊ���ձ�.��� ���� 30", "AI_94��Ʊ�����±�.������ ���� 'abcd'", "�ַ����ҽ�( �ַ�����ȡ(AI_ͨ�÷ֻ���0701.ʡ/�д���,1,4),1) ���� '1'"};
    segmentArr = new String[]{"�ַ����ҽ�( �ַ�����ȡ(AI_ͨ�÷ֻ���0701.ʡ/�д���,1,4),1) ���� '1'"};
    t = new Translator();
    for (int i = 0; i < segmentArr.length; i++){
      t.setChSegment(t.WHERE, segmentArr[i]);
      DbTable[] ts = t.getTables();
      t.addDbTable("AI_ͨ�÷ֻ���0701", "CNF");
      t.addDbField("AI_ͨ�÷ֻ���0701", "ʡ/�д���", "CNF01");
      t.addDbField("AI_ͨ�÷ֻ���0701", "�к�", "CNF02");
      t.addDbField("AI_ͨ�÷ֻ���0701", "���", "CNF03");
      t.addDbField("AI_ͨ�÷ֻ���0701", "������", "CNF04");
      t.addDbTable("AI_94��Ʊ�����±�", "CNF_TEST");
      t.addDbField("AI_94��Ʊ�����±�", "ʡ/�д���", "CNF01");
      t.addDbField("AI_94��Ʊ�����±�", "�к�", "CNF02");
      t.addDbField("AI_94��Ʊ�����±�", "���", "CNF03");
      t.addDbField("AI_94��Ʊ�����±�", "������", "CNF04");
      t.updateDbTables(t, ts);
      if (t.hasError()){
        ChWrongMessage[] msgs = t.showWrongMsgs();
        for (int j = 0; j < msgs.length; j++){
          System.out.println(msgs[j]);
        }
      }
      System.out.println(t.getQueryModel().getChSegment(t.WHERE));
      System.out.println(t.getQueryModel().getEnSegment(t.WHERE));
    }
    
  }
  
  public void testCompare(){
    String str = "��Ƚ� AI_94��Ʊ���ձ�, AI_94��Ʊ�����±� ���� not exists AI_94��Ʊ���ձ�.ʡ/�д��� ���� AI_94��Ʊ�����±�.ʡ/�д��� ���� AI_94��Ʊ���ձ�.�к� ���� 5 ���� AI_94��Ʊ���ձ�.�к� < 2";
    Translator t = new Translator();
    System.out.println(t.getCnKeyWordByValue(t.ENVALUE_COMPARE));
    System.out.println(t.getCnKeyWords(""));
    t.setChQuery(str);
    t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
    t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
    t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
    t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
    t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
    t.addDbTable("AI_94��Ʊ�����±�", "CNF_TEST");
    t.addDbField("AI_94��Ʊ�����±�", "ʡ/�д���", "CNF01");
    t.addDbField("AI_94��Ʊ�����±�", "�к�", "CNF02");
    t.addDbField("AI_94��Ʊ�����±�", "���", "CNF03");
    t.addDbField("AI_94��Ʊ�����±�", "������", "CNF04");
    DbTable[] ts = t.getTables();
    t.updateDbTables(t, ts);
    if (t.hasError()){
      ChWrongMessage[] msgs = t.showWrongMsgs();
      for (int i = 0; i < msgs.length; i++){
        System.out.println(msgs[i]);
      }
      return;
    }
    
    System.out.println(t.getChFromStr());
    System.out.println(t.getChWhereStr());
    
    AppDbTable[] appDbTableArr = t.getInfo().getDbTableInfoToAppTableArr();
    for (int i = 0; i < appDbTableArr.length; i++){
      AppDbTable appDbTable = appDbTableArr[i];
      AppDbField[] appDbFieldArr = appDbTable.getFields();
      System.out.println(appDbTable.getTableName());
    }
    System.out.println(t.getChQuery());
    System.out.println(t.getQueryModel().getEnString());
    System.out.println(t.getQueryModel().getEmptyExecuteEnString("CNF238494"));
    System.out.println(t.getQueryModel().getExecuteEnString("CNF238494"));
//    String xml = t.getXmlString();
//    System.out.println("TO DB XML: " + xml);
    
    /*
    Translator t1 = new Translator();
    try{
      QueryModel queryModel = t1.loadModelFromXML(xml);
      if (queryModel instanceof TableCompareModel){
        t1.clearInfo();
        t1.addDbTable("AI_94��Ʊ���ձ�", "CNF_HJD_2007");
        t1.addDbTable("AI_94��Ʊ�����±�", "CNF_TEST_HJD_2007");
        t1.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF011");
        t1.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF021");
        t1.addDbField("AI_94��Ʊ���ձ�", "���", "CNF031");
        t1.addDbField("AI_94��Ʊ���ձ�", "������", "CNF041");
        t1.addDbField("AI_94��Ʊ�����±�", "ʡ/�д���", "CNF01_1");
        t1.addDbField("AI_94��Ʊ�����±�", "�к�", "CNF02_1");
        t1.addDbField("AI_94��Ʊ�����±�", "���", "CNF03_1");
        t1.addDbField("AI_94��Ʊ�����±�", "������", "CNF04_1");
        t1.updateDbTables(t1, t1.getTables());
        System.out.println(t1.getQueryModel().getEnString());
        System.out.println(t1.getQueryModel().getExecuteEnString("CNF070403"));
        TableCompareModel tableCompareModel = (TableCompareModel) queryModel;
        System.out.println(tableCompareModel.getChCompareMethod());
        System.out.println(t1.getChWhereStr());
      }
    }catch (DocumentException e){
      e.printStackTrace();
    }
    */
  }
  
  public void testUnion(){
  	String xml = "";
    String str = "��ϲ� ��1, ��2, ��3, ��4";
    
    Translator t = new Translator();
    t.setChQuery(str);
    t.addDbTable("��1", "CNF");
    t.addDbField("��1", "ʡ/�д���", "CNF01");
    t.addDbField("��1", "�к�", "CNF02");
    t.addDbField("��1", "���", "CNF03");
    t.addDbField("��1", "������", "CNF04");
    t.addDbTable("��2", "CNF_TEST");
    t.addDbField("��2", "ʡ/�д���", "CNF011");
    t.addDbField("��2", "�к�", "CNF022");
    t.addDbField("��2", "���", "CNF033");
    t.addDbField("��2", "������", "CNF044");
    t.addDbTable("��3", "CNF_TEST3");
    t.addDbField("��3", "ʡ/�д���", "CNF0111");
    t.addDbField("��3", "�к�", "CNF0222");
    t.addDbField("��3", "���", "CNF0333");
    t.addDbField("��3", "������", "CNF0444");
    t.addDbTable("��4", "CNF_TEST4");
    t.addDbField("��4", "ʡ/�д���", "CNF01111");
    t.addDbField("��4", "�к�", "CNF02222");
    t.addDbField("��4", "���", "CNF03333");
    t.addDbField("��4", "������", "CNF04444");
    DbTable[] ts = t.getTables();
    t.updateDbTables(t, ts);
    if (t.hasError()){
      ChWrongMessage[] msgs = t.showWrongMsgs();
      for (int i = 0; i < msgs.length; i++){
        System.out.println(msgs[i]);
      }
      return;
    }else{
	    System.out.println(t.getChQuery());
	    System.out.println(t.getQueryModel().getEnString());
	    System.out.println(t.getQueryModel().getEmptyExecuteEnString("CNF2087803"));
	    System.out.println(t.getQueryModel().getExecuteEnString("CNF2087803"));
//	    xml = t.getXmlString();
//	    System.out.println("TO DB XML: " + xml);
    }
    
    /*
    Translator t1 = new Translator();
    try{
      QueryModel queryModel = t1.loadModelFromXML(xml);
      if (queryModel instanceof TableUnionModel){
      	t1.addDbTable("��1", "CNF_HJD_2007");
        t1.addDbTable("��2", "CNF_TEST_HJD_2007");
        t1.updateDbTables(t1, t1.getTables());
        System.out.println(t1.getQueryModel().getEnString());
        System.out.println(t1.getQueryModel().getExecuteEnString("CNF070403"));
        AppDbTable[] appDbTableArr = t1.getInfo().getDbTableInfoToAppTableArr();
        String[] tableCnNameArr = t1.getQueryModel().getDbTableModel().getTablesNameArr();
        
        for (int i = 0; i < appDbTableArr.length; i++){
          AppDbTable appDbTable = appDbTableArr[i];
          AppDbField[] appDbFieldArr = appDbTable.getFields();
          System.out.println(appDbTable.getTableName());
        }
      }
    }catch (DocumentException e){
      e.printStackTrace();
    }
    */
  }
  
  public void customQueryTest(){
  	String[] strArr = new String[]{
  			"��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д���, AI_94��Ʊ���ձ�.�к� ��Ϊ �к�, AI_94��Ʊ���ձ�.��� ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� (AI_94��Ʊ���ձ�.����(�ٷֱ�%) ���� 0.123 ���� AI_94��Ʊ���ձ�.��%�� ���� 0.5)" +
  			"	���� (AI_94��Ʊ���ձ�.����% ���� 0.88 ���� AI_94��Ʊ���ձ�.���� С�� 0.99)" +
  			" ���� ʡ/�д���, �к�",
  			
  			"��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д���, AI_94��Ʊ���ձ�.�к� ��Ϊ �к�, AI_94��Ʊ���ձ�.��� ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� �ַ����ҽ�(�ַ�����ȡ(AI_94��Ʊ���ձ�.ʡ/�д���, 1, 4), 1) ���� '1'",
  	};
  	
  	String[] strArr1 = new String[]{
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.ʡ/�д��� as char) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.ʡ/�д��� Ϊ char(10)) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.ʡ/�д��� Ϊ character) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.ʡ/�д��� Ϊ character(10)) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.ʡ/�д��� Ϊ varchar) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.ʡ/�д��� Ϊ varchar(20)) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.ʡ/�д��� Ϊ uniqueidentifierstr) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ bigint) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ int) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ integer) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ smallint) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ tinyint) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ decimal) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ decimal(10)) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ decimal(10, 2)) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��('89.09' Ϊ numeric) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ numeric(10)) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ numeric(10, 2)) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ double) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ float) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ float(12)) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��� Ϊ real) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.���� Ϊ binary) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.���� Ϊ binary(200)) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.���� Ϊ varbinary) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.���� Ϊ varbinary(200)) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��('2007-01-01' Ϊ date) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��('2007-01-01' Ϊ datetime) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��('2007-01-01' Ϊ smalldatetime) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��('2007-01-01' Ϊ time) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��('2007-01-01' Ϊ timestamp) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ��������ת��(AI_94��Ʊ���ձ�.��ʶ Ϊ bit) ��Ϊ ��ʶ ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  	};
  	
  	String[] strArr2 = new String[]{
  			"��ѯ ����������ת��Ϊ(char, AI_94��Ʊ���ձ�.ʡ/�д���) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(char(10), AI_94��Ʊ���ձ�.ʡ/�д���) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(char(10), �ַ����ҽ�(AI_94��Ʊ���ձ�.ʡ/�д���, 10)) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(character, AI_94��Ʊ���ձ�.ʡ/�д���) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(character(10), AI_94��Ʊ���ձ�.ʡ/�д���) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(varchar, AI_94��Ʊ���ձ�.ʡ/�д���) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(varchar(20), AI_94��Ʊ���ձ�.ʡ/�д���) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(uniqueidentifierstr, AI_94��Ʊ���ձ�.ʡ/�д���) ��Ϊ ʡ/�д��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(bigint, AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(int, AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(integer, AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(smallint, AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(tinyint, AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(decimal, AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(decimal(10), AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(decimal(10, 2), AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(numeric, '89.09') ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(numeric(10), AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(numeric(10, 2), AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(double, AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(float, AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(float(12), AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(real, AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(binary, AI_94��Ʊ���ձ�.����) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(binary(200), AI_94��Ʊ���ձ�.����) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(varbinary, AI_94��Ʊ���ձ�.����) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(varbinary(200), AI_94��Ʊ���ձ�.����) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(date, '2007-01-01') ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(date, '2007-01-01', 120) ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(datetime, '2007-01-01 12:01:21') ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(smalldatetime, '2007-01-01') ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(time, '2007-01-01') ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(timestamp, '2007-01-01') ��Ϊ ���� ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ ����������ת��Ϊ(bit, AI_94��Ʊ���ձ�.��ʶ) ��Ϊ ��ʶ ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  			
  			"��ѯ convert(bit, AI_94��Ʊ���ձ�.��ʶ) ��Ϊ ��ʶ ���� AI_94��Ʊ���ձ� ��Ϊ CNF" +
  			" ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '1'",
  		};
  	
  	
  	
  	Translator t = new Translator();
  	
  	System.out.println("==========convert��������========");
    for (int i = 0; i < strArr1.length; i++){
    	System.out.println("convert��������" + (i + 1) + "��");
    	t.setChQuery(strArr1[i]);
    	if (t.hasError()){
        ChWrongMessage[] msgs = t.showWrongMsgs();
        for (int j = 0; j < msgs.length; j++){
          System.out.println("�����Դ���" + msgs[j]);
        }
      }else{
      	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
        t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
        t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
        t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
        t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
        t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF05");
        t.addDbField("AI_94��Ʊ���ձ�", "����1", "CNF05_1");
        t.addDbField("AI_94��Ʊ���ձ�", "����2", "CNF05_2");
        t.addDbField("AI_94��Ʊ���ձ�", "��ʶ", "CNF10");
        t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF50");
        t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF96");
        t.addDbField("AI_94��Ʊ���ձ�", "����%", "CNF97");
        t.addDbField("AI_94��Ʊ���ձ�", "��%��", "CNF98");
        t.addDbField("AI_94��Ʊ���ձ�", "����(�ٷֱ�%)", "CNF99");
        t.updateDbTables(t, t.getTables());
        
        System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
        System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
        //System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
        //System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
      }
    	System.out.println();
    }
    
    System.out.println("==========cast��������========");
    for (int i = 0; i < strArr2.length; i++){
    	System.out.println("cast��������" + (i + 1) + "��");
    	t.setChQuery(strArr2[i]);
    	if (t.hasError()){
        ChWrongMessage[] msgs = t.showWrongMsgs();
        for (int j = 0; j < msgs.length; j++){
          System.out.println("�����Դ���" + msgs[j]);
        }
      }else{
      	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
        t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
        t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
        t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
        t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
        t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF05");
        t.addDbField("AI_94��Ʊ���ձ�", "����1", "CNF05_1");
        t.addDbField("AI_94��Ʊ���ձ�", "����2", "CNF05_2");
        t.addDbField("AI_94��Ʊ���ձ�", "��ʶ", "CNF10");
        t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF50");
        t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF96");
        t.addDbField("AI_94��Ʊ���ձ�", "����%", "CNF97");
        t.addDbField("AI_94��Ʊ���ձ�", "��%��", "CNF98");
        t.addDbField("AI_94��Ʊ���ձ�", "����(�ٷֱ�%)", "CNF99");
        t.updateDbTables(t, t.getTables());
        
        System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
        System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
        //System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
        //System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
      }
    	System.out.println();
    }
    
    System.out.println("============�Զ����ѯ����==========");
    for (int i = 0; i < strArr.length; i++){
    	System.out.println("�Զ����ѯ����" + (i + 1) + "��");
    	t.setChQuery(strArr[i]);
    	if (t.hasError()){
        ChWrongMessage[] msgs = t.showWrongMsgs();
        for (int j = 0; j < msgs.length; j++){
          System.out.println("�����Դ���" + msgs[j]);
        }
      }else{
      	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
        t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
        t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
        t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
        t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
        t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF05");
        t.addDbField("AI_94��Ʊ���ձ�", "����1", "CNF05_1");
        t.addDbField("AI_94��Ʊ���ձ�", "����2", "CNF05_2");
        t.addDbField("AI_94��Ʊ���ձ�", "��ʶ", "CNF10");
        t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF50");
        t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF96");
        t.addDbField("AI_94��Ʊ���ձ�", "����%", "CNF97");
        t.addDbField("AI_94��Ʊ���ձ�", "��%��", "CNF98");
        t.addDbField("AI_94��Ʊ���ձ�", "����(�ٷֱ�%)", "CNF99");
        t.updateDbTables(t, t.getTables());
        
        System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
        System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
        //System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
        //System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
      }
    	System.out.println();
    }
  }
  
  public void testTranslator() {
    String str = "��ѯ Ψһ ����, ���(e.�ֶ�2) ��Ϊ a, ��1.�ֶ�1, (��2.�ֶ�3 �� ��2.�ֶ�4) �� ��2.�ֶ�5, ��3.�ֶ�1 �� ��3.�ֶ�2, ��ƽ����(��4.�ֶ�3) \r\n"
        + "���� ��1 ��Ϊ e, ��2 ��Ϊ f, ��3, ��4 \r\n"
        + "���� 1 ���� 1 ���� e.�ֶ�1+e.�ֶ�2 ���� '30' ���� ��2.�ֶ�3 ���� 'abcd' ���� ��3.�ֶ�1 �ǿ� ���� ��3.�ֶ�2 ��Χ 1 2 \r\n"
        + "���� ��1.�ֶ�1 �� ��2.�ֶ�2, ��2.�ֶ�1 \r\n" + "���� ��1.�ֶ�1 ����, a ����\r\n";
    str = "��ѯ Ψһ ����, ���(e.�ֶ�2) ��Ϊ a, ��1.�ֶ�1, (��2.�ֶ�3 �� ��2.�ֶ�4) �� ��2.�ֶ�5, ��3.�ֶ�1 �� ��3.�ֶ�2, ��ƽ����(��4.�ֶ�3) "
        + "���� ��1 ��Ϊ e, ��2 ��Ϊ f, ��3, ��4 "
        + "���� 1 ���� 1 ���� e.�ֶ�1+e.�ֶ�2 ���� '30' ���� ��2.�ֶ�3 ���� 'abcd' ���� ��3.�ֶ�1 �ǿ� ���� ��3.�ֶ�2 ��Χ 1 2 "
        + "���� ��1.�ֶ�1, �ַ�����ȡ(��1.�ֶ�3, 1, 4), ��ֵת�ַ���(��2.�ֶ�3), ��2.�ֶ�3 �� ��2.�ֶ�4 "
        + "���� a, ��1.�ֶ�1 ����, ��ֵת�ַ���(��2.�ֶ�3), ���(��2.�ֶ�4) ����, �ַ�����ȡ(��1.�ֶ�3, 1, 4)";
    
    
    str = " ��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д���, AI_94��Ʊ���ձ�.�к� ��Ϊ �к�, ���(AI_94��Ʊ���ձ�.���) ��Ϊ ���" +
          " ���� AI_94��Ʊ���ձ� ��Ϊ CNF, AI_94��Ʊ���ձ� ��Ϊ ��2" + 
          " ���� �� (" +
          " (AI_94��Ʊ���ձ�.��� ���� 5000 ���� AI_94��Ʊ���ձ�.��� < -9000)" + 
          " ���� (AI_94��Ʊ���ձ�.�к� ���� '3' ���� AI_94��Ʊ���ձ�.ʡ/�д��� not like '001')" +
          " ���� (AI_94��Ʊ���ձ�.��� �� 50) ���� -50000" +
          " ���� ��ֵת�ַ���(AI_94��Ʊ���ձ�.���) ���� -5" +
          " ���� AI_94��Ʊ���ձ�.�к� ���� '3' ���� AI_94��Ʊ���ձ�.ʡ/�д��� like '%HJD%'" +
          " ���� AI_94��Ʊ���ձ�.ʡ/�д��� �ǿ�" +
          " ���� �ַ�����ȡ(AI_94��Ʊ���ձ�.ʡ/�д���, 1, 20) ���� '355'" + 
          " ���� (AI_94��Ʊ���ձ�.ʡ/�д��� ��Χ 1 3)" + 
          " ���� AI_94��Ʊ���ձ�.��� is not null" +
          " ���� AI_94��Ʊ���ձ�.��� ������ AI_94��Ʊ���ձ�.���" +
          ")" + 
          " ���� ��ֵת�ַ���(AI_94��Ʊ���ձ�.�к�), ��ƽ����(AI_94��Ʊ���ձ�.ʡ/�д���), AI_94��Ʊ���ձ�.�к�, �ַ�����ȡ(AI_94��Ʊ���ձ�.���, 1, 3)" + 
          " ���� AI_94��Ʊ���ձ�.�к� ����, ��� ����, AI_94��Ʊ���ձ�.�к� ����";
    
    str = " select ����" +
			    " ���� AI_94��Ʊ���ձ� ��Ϊ CNF" + 
			    " ���� (" +
			    " �ַ�����ȡ(AI_94��Ʊ���ձ�.����%, 1, 3) = 'XYZ'" +
			    ")";
    
//  str = " ��ѯ now(*)" +
//  " ���� AI_94��Ʊ���ձ� ��Ϊ CNF" + 
//  " ���� �ַ����ҽ�( �ַ�����ȡ(AI_94��Ʊ���ձ�.ʡ/�д���,1,4),1) ���� '1'" +
//  " ���� �ַ�����ȡ(�ַ����ҽ�(AI_ͨ�÷ֻ���0701.ʡ/�д���, 4), 1, 3) ���� '1'" +
//  " ���� �� (" +
//  " (AI_94��Ʊ���ձ�.��� ���� 5000)" + 
//  " ���� (AI_94��Ʊ���ձ�.�к� ���� '3')" +
//  " ���� ��ʽ������(�������(day, 20, ����������ת��Ϊ(char(10), AI_94��Ʊ���ձ�.����, 120)), day) ���� '2007-01-01'" +
//  ")" + 
//  " ���� AI_94��Ʊ���ձ�.ʡ/�д���, ���ַ����ĳ���(�ַ������(AI_94��Ʊ���ձ�.�к�, 4)), AI_94��Ʊ���ձ�.����" + 
//  " ���� AI_94��Ʊ���ձ�.�к� ����, ��� ����, AI_94��Ʊ���ձ�.�к� ����";
    
    

    Translator t = new Translator();
    t.setChQuery(str);
    
    QueryModel[] tableAliasModelArr = t.getQueryModel().getModelsFromAllChildrenByClass(TableAliasModel.class);
    for (int i = 0; i < tableAliasModelArr.length; i++){
      TableAliasModel aliasModel = (TableAliasModel) tableAliasModelArr[i];
      aliasModel.setEnAlias("CNF20070101");
    }
    t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
    t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
    t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
    t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
    t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
    t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF05");
    t.addDbField("AI_94��Ʊ���ձ�", "����1", "CNF05_1");
    t.addDbField("AI_94��Ʊ���ձ�", "����2", "CNF05_2");
    
//  t.addDbTable("AI_94��Ʊ���ձ�", "CNF", "casdb2");
//  t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
//  t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
//  t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
//  t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
//  t.addDbTable("��2", "CNF_TEST");
//  t.addDbField("��2", "ʡ/�д���", "CNF01");
//  t.addDbField("��2", "�к�", "CNF02");
//  t.addDbField("��2", "���", "CNF03");
//  t.addDbField("��2", "������", "CNF04");
    DbTable[] ts = t.getTables();
    t.updateDbTables(t, ts);
    
    /*
    //��ȡѭ�����������������
    QueryModel[] paramModelArr = t.getQueryModel().getModelsFromAllChildrenByClass(ParamModel.class);
    for (int i = 0; i < paramModelArr.length; i++){
      ParamModel paramModel = (ParamModel) paramModelArr[i];
      System.out.println(paramModelArr[i].getEnString());
      paramModel.setParamValue("01");
      System.out.println(paramModelArr[i].getEnString());
    }
    System.out.println(t.getQueryModel().getEnString());
    
    String selectStr = t.getChSelectStr();
    String fromStr = t.getChFromStr();
    String whereStr = t.getChWhereStr();
    String groupByStr = t.getChGroupByStr();
    String orderByStr = t.getChOrderByStr();
    
    //���з�����Ӣ�ı������ֶ���
    System.out.println("============DB Table============");
    for (int i = 0; i < ts.length; i++){
      System.out.println(ts[i].getChName());
      System.out.println(ts[i].getEnName());
      for (Iterator it = ts[i].getFields().iterator(); it.hasNext();){
        DbField dbField = (DbField) it.next();
        System.out.println(dbField.getChName());
        System.out.println(dbField.getEnName());
      }
    }
    
    //���Ӿ�����
    System.out.println("============Segment SQL===========");
    System.out.println(t.getChSelectStr());
    System.out.println(t.getChFromStr());
    System.out.println(t.getChWhereStr());
    System.out.println(t.getChGroupByStr());
    System.out.println(t.getChOrderByStr());
    
    SelectListVO[] _selectListVOArr = t.getSelectListVOArr();
    FromListVO[] _fromListVOArr = t.getFromListVOArr();
    WhereListVO[] _whereListVOArr = t.getWhereListVOArr();
    GroupByListVO[] _groupListVOArr = t.getGroupByListVOArr();
    OrderByListVO[] _orderListVOArr = t.getOrderByListVOArr();
    
    System.out.println("============SELECT EQUEM===========");
    for (int i = 0; i < _selectListVOArr.length; i++){
      System.out.println(_selectListVOArr[i].getCnColumnEquElem());
      _selectListVOArr[i].setCnFieldAlias("����" + i);
      _selectListVOArr[i].setFieldDataType("String");
    }
    t.setSelectListVOArr(_selectListVOArr);
    
//  t.setSelectListVOArrToModel(_selectListVOArr);
    AliasModel[] aliasModels = t.getAliasModelListVOArrByModel();
    for (int i = 0; i < aliasModels.length; i++){
      System.out.println(aliasModels[i].getChString());
    }
    
    
    System.out.println("============WHERE EQUEM===========");
    for (int i = 0; i < _whereListVOArr.length; i++){
      System.out.println(_whereListVOArr[i].getCnAllWhereStr());
      _whereListVOArr[i].setCheckedFlag("1");
    }
    t.setWhereListVOArr(_whereListVOArr);
    
    System.out.println("============COLUNM ALIAS===========");
    QueryModel[] aliasModelArr = t.getQueryModel().getModelsFromAllChildrenByClass(AliasModel.class);
    for (int i = 0; i < aliasModelArr.length; i++){
      System.out.println(aliasModelArr[i].getChString());
      AliasModel aliasModel = (AliasModel) aliasModelArr[i];
      aliasModel.setEnAlias("enAlias" + i);
    }
    aliasModels = t.getAliasModelListVOArrByModel();
    for (int i = 0; i < aliasModels.length; i++){
      System.out.println(aliasModels[i].getChString());
    }
    _selectListVOArr = t.getSelectListVOArr();
    
    System.out.println("============ORDER ALIAS===========");
    QueryModel[] orderAliasModelArr = t.getQueryModel().getModelsFromAllChildrenByClass(OrderAliasModel.class);
    for (int i = 0; i < orderAliasModelArr.length; i++){
      System.out.println(orderAliasModelArr[i].getChString());
      OrderAliasModel orderAliasModel = (OrderAliasModel) orderAliasModelArr[i];
      orderAliasModel.setEnAlias("enOrderAlias" + i);
    }
    */
    
    System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
    System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
    System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
    System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
//    String xml1 = t.getXmlString();
//    System.out.println("TO DB XML: " + xml1);
    
    /*
    Translator t1 = new Translator();
    String rXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "<query><ch_query_string>��ѯ Ψһ ���� , ���(e.�ֶ�2) ��Ϊ a , ��1.�ֶ�1 , ( ��2.�ֶ�3 �� ��2.�ֶ�4 ) �� ��2.�ֶ�5 , ��3.�ֶ�1 �� ��3.�ֶ�2 , ��ƽ����(��4.�ֶ�3)  ���� ��1 ��Ϊ e, ��2 ��Ϊ f, ��3, ��4 ���� 1 ���� 1 ���� e.�ֶ�1 + e.�ֶ�2 ���� '30' ���� ��2.�ֶ�3 ���� 'abcd' ���� ��3.�ֶ�1 �ǿ� ���� ��3.�ֶ�2 ��Χ 1 2 ���� ��1.�ֶ�1, �ַ�����ȡ(��1.�ֶ�3, 1, 4), ��ֵת�ַ���(��2.�ֶ�3), ��2.�ֶ�3 �� ��2.�ֶ�4 ����  a, ��1.�ֶ�1 ����, ��ֵת�ַ���(��2.�ֶ�3), ���(��2.�ֶ�4) ����, �ַ�����ȡ(��1.�ֶ�3, 1, 4)</ch_query_string><db_info ch_name=\"��1\" en_name=\"table0\"><db_field ch_name=\"�ֶ�2\" en_name=\"field2\"/><db_field ch_name=\"�ֶ�3\" en_name=\"field3\"/><db_field ch_name=\"�ֶ�1\" en_name=\"field1\"/></db_info><db_info ch_name=\"��2\" en_name=\"table1\"><db_field ch_name=\"�ֶ�4\" en_name=\"field4\"/><db_field ch_name=\"�ֶ�5\" en_name=\"field5\"/><db_field ch_name=\"�ֶ�3\" en_name=\"field3\"/></db_info><db_info ch_name=\"��3\" en_name=\"table2\"><db_field ch_name=\"�ֶ�2\" en_name=\"field2\"/><db_field ch_name=\"�ֶ�1\" en_name=\"field1\"/></db_info><db_info ch_name=\"��4\" en_name=\"table3\"><db_field ch_name=\"�ֶ�3\" en_name=\"field3\"/></db_info><selectListEqu><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/></selectListEqu><whereListEqu><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/></whereListEqu><aliasListEqu><aliasListVO alias=\"a\" enAlias=\"enAlias0\"/><aliasListVO alias=\"e\" enAlias=\"enAlias1\"/><aliasListVO alias=\"f\" enAlias=\"enAlias2\"/></aliasListEqu><orderAliasListEqu><orderAliasListVO alias=\"a\" enAlias=\"enOrderAlias0\"/></orderAliasListEqu></query>";
    rXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
      + "<query><ch_query_string>��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д��� , AI_94��Ʊ���ձ�.�к� ��Ϊ �к� , ���(AI_94��Ʊ���ձ�.���) ��Ϊ ���  ���� AI_94��Ʊ���ձ� ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '0200' ���� AI_94��Ʊ���ձ�.ʡ/�д���, AI_94��Ʊ���ձ�.�к� ���� ���(AI_94��Ʊ���ձ�.���) ����, AI_94��Ʊ���ձ�.�к� ����</ch_query_string><db_info ch_name=\"AI_94��Ʊ���ձ�\" en_name=\"CNF\" flag=\"casdb2\" tableParam=\"\"><db_field ch_name=\"�к�\" en_name=\"CNF02\" fieldParam=\"\"/><db_field ch_name=\"������\" en_name=\"CNF04\" fieldParam=\"\"/><db_field ch_name=\"ʡ/�д���\" en_name=\"CNF01\" fieldParam=\"\"/><db_field ch_name=\"���\" en_name=\"CNF03\" fieldParam=\"\"/></db_info><selectListEqu><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/></selectListEqu><whereListEqu><WhereListVO checkedFlag=\"1\"/></whereListEqu><aliasListEqu><aliasListVO alias=\"ʡ/�д���\" enAlias=\"enAlias0\"/><aliasListVO alias=\"�к�\" enAlias=\"enAlias1\"/><aliasListVO alias=\"���\" enAlias=\"enAlias2\"/></aliasListEqu><orderAliasListEqu/></query>";
    rXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
      + "<query><ch_query_string circleType=\"1\">��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д��� , AI_94��Ʊ���ձ�.�к� ��Ϊ �к� , ���(AI_94��Ʊ���ձ�.���) ��Ϊ ���  ���� AI_94��Ʊ���ձ� ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '0200' ���� AI_94��Ʊ���ձ�.ʡ/�д���, AI_94��Ʊ���ձ�.�к� ���� ���(AI_94��Ʊ���ձ�.���) ����, AI_94��Ʊ���ձ�.�к� ����</ch_query_string><db_info ch_name=\"AI_94��Ʊ���ձ�\" en_name=\"CNF\" flag=\"casdb2\" tableParam=\"CNF_table_Param\"><db_field ch_name=\"�к�\" en_name=\"CNF02\" fieldParam=\"\"/><db_field ch_name=\"������\" en_name=\"CNF04\" fieldParam=\"\"/><db_field ch_name=\"ʡ/�д���\" en_name=\"CNF01\" fieldParam=\"\"/><db_field ch_name=\"���\" en_name=\"CNF03\" fieldParam=\"\"/></db_info><selectListEqu><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/></selectListEqu><whereListEqu><WhereListVO checkedFlag=\"1\"/></whereListEqu><aliasListEqu><aliasListVO alias=\"ʡ/�д���\" enAlias=\"enAlias0\"/><aliasListVO alias=\"�к�\" enAlias=\"enAlias1\"/><aliasListVO alias=\"���\" enAlias=\"enAlias2\"/></aliasListEqu><orderAliasListEqu/></query>";
    rXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
       + "<query><ch_query_string>��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д���, AI_94��Ʊ���ձ�.�к� ��Ϊ �к�, ���(AI_94��Ʊ���ձ�.���) ��Ϊ ��� ���� AI_94��Ʊ���ձ� ��Ϊ CNF, AI_94��Ʊ���ձ� ��Ϊ ��2 ���� �� ( ( AI_94��Ʊ���ձ�.��� ���� 5000 ���� AI_94��Ʊ���ձ�.��� &lt; -9000 ) ���� ( AI_94��Ʊ���ձ�.�к� ���� '3' ���� AI_94��Ʊ���ձ�.ʡ/�д��� ������ '001' ) ���� ( AI_94��Ʊ���ձ�.��� �� 50 ) ���� -50000 ���� ��ֵת�ַ���(AI_94��Ʊ���ձ�.���) ���� -5 ���� AI_94��Ʊ���ձ�.�к� ���� '3' ���� AI_94��Ʊ���ձ�.ʡ/�д��� ������ '001' ���� AI_94��Ʊ���ձ�.ʡ/�д��� �ǿ� ���� �ַ�����ȡ(AI_94��Ʊ���ձ�.ʡ/�д���, 1, 20) ���� '355' ���� AI_94��Ʊ���ձ�.ʡ/�д��� ��Χ 1 3 ���� AI_94��Ʊ���ձ�.��� ���� (23, 12, 34, 350) ) ���� ��ֵת�ַ���(AI_94��Ʊ���ձ�.�к�), ��ƽ����(AI_94��Ʊ���ձ�.ʡ/�д���), AI_94��Ʊ���ձ�.�к�, �ַ�����ȡ(AI_94��Ʊ���ձ�.���, 1, 3) ���� AI_94��Ʊ���ձ�.�к� ����, ��� ����, AI_94��Ʊ���ձ�.�к� ����</ch_query_string><db_info ch_name=\"AI_94��Ʊ���ձ�\" en_name=\"CNF\" flag=\"\" tableParam=\"\"><db_field ch_name=\"ʡ/�д���\" en_name=\"CNF01\" fieldParam=\"\"/><db_field ch_name=\"�к�\" en_name=\"CNF02\" fieldParam=\"\"/><db_field ch_name=\"���\" en_name=\"CNF03\" fieldParam=\"\"/><db_field ch_name=\"������\" en_name=\"CNF04\" fieldParam=\"\"/></db_info><selectListEqu><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/></selectListEqu><whereListEqu><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/></whereListEqu><aliasListEqu><aliasListVO alias=\"ʡ/�д���\" enAlias=\"enAlias0\"/><aliasListVO alias=\"�к�\" enAlias=\"enAlias1\"/><aliasListVO alias=\"���\" enAlias=\"enAlias2\"/></aliasListEqu><tableAliasListEqu><tableAliasListVO alias=\"CNF\" enAlias=\"CNF20070101\"/><tableAliasListVO alias=\"��2\" enAlias=\"CNF20070101\"/></tableAliasListEqu><orderAliasListEqu><orderAliasListVO alias=\"���\" enAlias=\"enOrderAlias0\"/></orderAliasListEqu></query>";
    try{
      QueryModel m = t1.loadModelFromXML(rXML);
      t1.setAliasModelListVOArrByXML();
      t1.setTableAliasModelListVOArrByXML();
      t1.setOrderAliasModelListVOArrByXML();
      System.out.println(m.getCircleType());
      SelectListVO[] r_selectListVOArr = t1.getSelectListVOArr();
      FromListVO[] r_fromListVOArr = t1.getFromListVOArr();
      WhereListVO[] r_whereListVOArr = t1.getWhereListVOArr();
      GroupByListVO[] r_groupListVOArr = t1.getGroupByListVOArr();
      OrderByListVO[] r_orderListVOArr = t1.getOrderByListVOArr();
      DbTable[] dbTables = t1.getTables();
      t1.updateDbTables(t1, dbTables);
      for (int i = 0; i < dbTables.length; i++){
        System.out.println(dbTables[i].getChName());
        System.out.println(dbTables[i].getEnName());
        for (Iterator it = dbTables[i].getFields().iterator(); it.hasNext();){
          DbField dbField = (DbField) it.next();
          System.out.println(dbField.getChName());
          System.out.println(dbField.getEnName());
        }
      }
      AppDbTable[] appDbTables = t1.getInfo().getDbTableInfoToAppTableArr();
      
//      for (int i = 0; i < r_selectListVOArr.length; i++){
//        r_selectListVOArr[i].setFieldDataType("Date");
//      }
//      t1.setSelectListVOArr(r_selectListVOArr);
     aliasModels = t1.getAliasModelListVOArrByModel();
      TableAliasModel[] tableAliasModels = t1.getTableAliasModelListVOArrByModel();
      tableAliasModels[0].setEnAlias("CNF00000");
      t1.updateDbTables(t1, t1.getTables());
      System.out.println(m.getChString());
      System.out.println(m.getEnString());
      System.out.println(m.getExecuteEnString("xxx"));
      System.out.println(t1.getXmlString());
    }catch (DocumentException e){
      e.printStackTrace();
    }
    */
  }
  
  public void testGettingStart() {
    String str = "��ѯ �ֶ� ���� ��";
    GSL l = new GSL(new StringReader(str));
    GSP p = new GSP(l);
    try{
      p.startRule();
      CommonAST ast = (CommonAST) p.getAST();
      System.out.println(ast.toStringList());
    }catch (RecognitionException e){
      e.printStackTrace();
    }catch (TokenStreamException e){
      e.printStackTrace();
    }
  }

  public void testQueryParser() {
    String str = "select distinct *, ���(��1.�ֶ�2) ��Ϊ a, ��1.�ֶ�1, (��2.�ֶ�3 �� ��2.�ֶ�4) * ��2.�ֶ�5, ��3.�ֶ�1 �� ��3.�ֶ�2, ��ƽ����(��4.�ֶ�3) "
        + "from ��1 ��Ϊ e, ��2 ��Ϊ f, ��3, ��4 "
        + "where 1 ���� 1 ���� e.�ֶ�1+e.�ֶ�2 ���� '30' ���� ��2.�ֶ�3 ���� 'abcd' ���� ��3.�ֶ�1 �ǿ� ���� ��3.�ֶ�2 ��Χ 1 2 "
        + "group by ��1.�ֶ�1 �� ��2.�ֶ�2, ��2.�ֶ�1 " + "order by ��1.�ֶ�1 ����, a ����";
    System.out.println(str);
    L lexer = new L(new StringReader(str));
    P parser = new P(lexer);
    try{
      parser.select_statement();
      CommonAST ast = (CommonAST) parser.getAST();
      System.out.println(ast.toStringList());
      T t = new T();
      QueryModel m = t.select_statement(ast);
      System.out.println(m.getChString());
    }catch (RecognitionException e){
      e.printStackTrace();
    }catch (TokenStreamException e){
      e.printStackTrace();
    }
  }

  private static void setTableInfo(DbTable[] tables) {
    for (int j = 0; j < tables.length; j++){
      System.out.println(tables[j].getChName());
      tables[j].setEnName("table" + j);
      tables[j].addDbField("�ֶ�1", "field1");
      tables[j].addDbField("�ֶ�2", "field2");
      tables[j].addDbField("�ֶ�3", "field3");
      tables[j].addDbField("�ֶ�4", "field4");
      tables[j].addDbField("�ֶ�5", "field5");
      tables[j].addDbField("�ֶ�6", "field6");
      tables[j].addDbField("���", "field5");
      tables[j].addDbField("ʡ/�д���", "field6");
      tables[j].addDbField("�к�", "line_num");
      tables[j].addDbField("������", "code");
    }
  }
}
