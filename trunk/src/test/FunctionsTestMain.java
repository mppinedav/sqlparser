package test;

import model.parser.ChWrongMessage;
import translator.Translator;
import junit.framework.TestCase;

/**
 * ������������
 */
public class FunctionsTestMain {
	
	/**
	 * �����������
	 * @param args
	 */
	public static void main(String[] args) {
		FunctionsTestMain test = new FunctionsTestMain();
		for (int i = 0; i < args.length; i++){
			test.FunctionsTest(Integer.parseInt(args[0]));
		}
	}
	
	/**
	 * �������Է���
	 */
	public void FunctionsTest(int funNum) {
		if (funNum == 1)
			AggregateFunctionsTest();
		else if (funNum == 2)
			NumericFunctionsTest();
		else if (funNum == 3)
			StringFunctionsTest();
		else if (funNum == 4)
			DateTimeFunctionsTest();
		else if (funNum == 5)
			ConversionFunctionsTest();
		else if (funNum == 6)
			AnalyticalFunctionsTest();
		else if (funNum == 7)
			MiscellaneousFunctionsTest();
	}
	
	/**
	 * �ۺϺ������Է��� 
	 */
	public void AggregateFunctionsTest(){
  	String strHead = "��ѯ ";
  	String[] functionsArr = new String[]{
  			"��ƽ����(AI_94��Ʊ���ձ�.���)", 
  			"���¼����(*)",
  			"���¼����(AI_94��Ʊ���ձ�.�к�)",
  			"�����ֵ(AI_94��Ʊ���ձ�.���)",
  			"����Сֵ(AI_94��Ʊ���ձ�.���)",
  			"�󷽲�(AI_94��Ʊ���ձ�.���)",
  			"���ܺ�(AI_94��Ʊ���ձ�.���)",
  			"��ͳ�Ʒ���(AI_94��Ʊ���ձ�.���)"
  		};
  	String strEnd = " ���� AI_94��Ʊ���ձ�";
  	Translator t = new Translator();
  	for (int i = 0; i < functionsArr.length; i++){
  		System.out.println("�ۺϺ�������" + (i + 1) + "��");
	  	t.setChQuery(strHead + functionsArr[i] + strEnd);
	    if (t.hasError()){
	      ChWrongMessage[] msgs = t.showWrongMsgs();
	      for (int j = 0; j < msgs.length; j++){
	        System.out.println("�����Դ��󡿣�" + msgs[j]);
	      }
	    }else{
	    	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
	      t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
	      t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
	      t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
	      t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
	      t.updateDbTables(t, t.getTables());
	      System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
	      System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
//	      System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
//	      System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
//	      String rXML = t.getXmlString();
//	      System.out.println("TO DB XML: " + rXML);
	    }
	    System.out.println();
  	}
  }
  
	/**
	 * ��ѧ�������Է���
	 */
  public void NumericFunctionsTest(){
  	String strHead = "��ѯ ";
  	String[] functionsArr = new String[]{
  			"ȡ����ֵ(-980)", 
  			"������ֵ(0.56)",
  			"������ֵ(0.56)",
  			"������ֵ(0.56)",
  			"��������ķ�����ֵ(0.78, 0.56)",
  			"ȡ��������(123.45)",
  			"������ֵ(30)",
  			"������ֵ(45)",
  			"����ת����(0.5236)",
  			"����ֵ(3)",
  			"ȡ��������(123.45)",
  			"����Ȼ����(20)",
  			"��10Ϊ�׵Ķ���(50)",
  			"��ģ(5, 3)",
  			"��Բ����(*)",
  			"����(2, 3)",
  			"����ת����(30)",
  			"ȡ�����()",
  			"ȡ�����(4)",
  			"����(5, 3)",
  			"��ʽ����ֵ(123.453, 2)",
  			"��ֵ�ķ���(-123)",
  			"������ֵ(60)",
  			"��ƽ����(4)",
  			"������ֵ(45)",
  			"��ʽ����ֵ3(123.346, 2)",
  			"Nλ���㴦��(123.453, 1)",
  		};
  	String strEnd = " ���� AI_94��Ʊ���ձ�";
  	Translator t = new Translator();
  	for (int i = 0; i < functionsArr.length; i++){
  		System.out.println("��ѧ��������" + (i + 1) + "��");
	  	t.setChQuery(strHead + functionsArr[i] + strEnd);
	    if (t.hasError()){
	      ChWrongMessage[] msgs = t.showWrongMsgs();
	      for (int j = 0; j < msgs.length; j++){
	        System.out.println("�����Դ��󡿣�" + msgs[j]);
	      }
	    }else{
	    	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
	      t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
	      t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
	      t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
	      t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
	      t.updateDbTables(t, t.getTables());
	      System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
	      System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
//	      System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
//	      System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
//	      String rXML = t.getXmlString();
//	      System.out.println("TO DB XML: " + rXML);
	    }
	    System.out.println();
  	}
  }
  
  /**
   * �ַ����������Է���
   */
  public void StringFunctionsTest(){
  	String strHead = "��ѯ ";
  	String[] functionsArr = new String[]{
  			"��ASCII��('Z')", 
  			"���ַ����Ķ����Ƴ���(AI_94��Ʊ���ձ�.ʡ/�д���)",
  			"���ַ������ֽ���(AI_94��Ʊ���ձ�.ʡ/�д���)",
  			"���ֵ���ַ�(87)",
  			"���ַ�������1('CNF_2007')",
  			"������('CNF', AI_94��Ʊ���ձ�.ʡ/�д���)",
  			"����������������ֵ('test', 'chest')",
  			"�����ַ���(0, 'office', 'ladys')",
  			"תΪСд��ĸ1('Case')",
  			"����ַ���('abcdefghijk', 4)",
  			"���ַ�������2('abcd efg')",
  			"��λ��1('abc def ghijk', 'def', 2)",
  			"תΪСд��ĸ2('Case')",
  			"ȥ����ո�(' abc')",
  			"���ַ����Ĵ洢����(AI_94��Ʊ���ձ�.ʡ/�д���)",
  			"��λ��2('%hoco%', 'chocolate')",
  			"�ַ���ѭ������1('abc', 4)",
  			"�滻�ַ���('abc def gh', 'abc', '123')",
  			"�ַ���ѭ������2('def', 3)",
  			"�ҽ��ַ���('abcdefgh', 2)",
  			"ȥ���ҿո�('abc ')",
  			"���ַ������ƶ�('toast', 'coast')",
  			"�ַ�������('coop', 51)",
  			"���ַ�������ֵ('Smith')",
  			"��ո�(10)",
  			"��ֵת�ַ���( 123.45)",
  			"�ַ����ϲ�('abc', 'def', '12')",
  			"�ַ���ɾ���滻('abcdefgh', 1, 4, 'xy')",
  			"�ַ�����ȡ('abcdefgh', 1, 3)",
  			"ȥ���ҿո�(' abc ')",
  			"תΪ��д��ĸ1('case')",
  			"תΪ��д��ĸ2('case')"
  		};
  	String strEnd = " ���� AI_94��Ʊ���ձ�";
  	Translator t = new Translator();
  	for (int i = 0; i < functionsArr.length; i++){
  		System.out.println("�ַ�����������" + (i + 1) + "��");
	  	t.setChQuery(strHead + functionsArr[i] + strEnd);
	    if (t.hasError()){
	      ChWrongMessage[] msgs = t.showWrongMsgs();
	      for (int j = 0; j < msgs.length; j++){
	        System.out.println("�����Դ��󡿣�" + msgs[j]);
	      }
	    }else{
	    	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
	      t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
	      t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
	      t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
	      t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
	      t.updateDbTables(t, t.getTables());
	      System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
	      System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
//	      System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
//	      System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
//	      String rXML = t.getXmlString();
//	      System.out.println("TO DB XML: " + rXML);
	    }
	    System.out.println();
  	}
  }
  
  /**
   * ����ʱ�亯�����Է���
   */
  public void DateTimeFunctionsTest(){
  	String strHead = "��ѯ ";
  	String[] functionsArr = new String[]{
  			"��ʽ������('2007-01-01', 'dd mm,yyyy')", 
  			"�����ڷ���Ӣ����(MONTH, '2007-05-01')",
  			"�����ڵķ���ֵ(month, '2007-05-01')",
  			"תΪ����ʱ��('2007-01-01 12:01:20')",
  			"תΪ����('2007-01-01 12:01:20')",
  			"������Ӣ����('2007-05-19')",
  			"������('2007-05-11 12:01:20')",
  			"���������('2007-05-11 12:01:20')",
  			"���������('2007-05-11 12:01:20')",
  			"��Сʱ��('2007-05-11 12:01:20')",
  			"�����Сʱ('2007-05-11 12:01:20')",
  			"�������('2007-05-11 12:01:20')",
  			"��������('2007-05-11 12:01:20')",
  			"���·�Ӣ����('2007-05-11 12:01:20')",
  			"������('2007-05-11 12:01:20')",
  			"���������('2007-05-11 12:01:20')",
  			"ȡ��ǰ����ʱ��1(*)",
  			"����弾��('2007-05-11 12:01:20')",
  			"������('2007-05-11 12:01:20')",
  			"�������('2007-05-11 12:01:20')",
  			"��ǰ����(*)",
  			"������('2007-05-11 12:01:20')",
  			"������('2007-05-11 12:01:20')",
  			"��������('2007-05-11 12:01:20')",
  			"��ֵת����(2007, 5, 11)",
  			"ȡ��ǰ����ʱ��2()",
  			"��������(day, 102, '2007/05/11')",
  			"�������ڲ�ֵ(day, '2007-01-01', '2007-05-11')"
  		};
  	String strEnd = " ���� AI_94��Ʊ���ձ�";
  	Translator t = new Translator();
  	for (int i = 0; i < functionsArr.length; i++){
  		System.out.println("����ʱ�亯������" + (i + 1) + "��");
	  	t.setChQuery(strHead + functionsArr[i] + strEnd);
	    if (t.hasError()){
	      ChWrongMessage[] msgs = t.showWrongMsgs();
	      for (int j = 0; j < msgs.length; j++){
	        System.out.println("�����Դ��󡿣�" + msgs[j]);
	      }
	    }else{
	    	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
	      t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
	      t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
	      t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
	      t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
	      t.updateDbTables(t, t.getTables());
	      System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
	      System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
//	      System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
//	      System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
//	      String rXML = t.getXmlString();
//	      System.out.println("TO DB XML: " + rXML);
	    }
	    System.out.println();
  	}
  }
  
  /**
   * ��������ת���������Է���
   */
  public void ConversionFunctionsTest(){
  	String strHead = "��ѯ ";
  	String[] functionsArr = new String[]{
  			"��������ת��('Surname' as char)", 
  			"��������ת��(char(10), AI_94��Ʊ���ձ�.����, 120)",
  			"ʮ������תΪ����('0x00000100')",
  			"����תΪʮ������(120)",
  			"�Ƿ�������('2007-01-01 12:01:20')",
  			"�Ƿ���ֵ��(34.78)"
  		};
  	String strEnd = " ���� AI_94��Ʊ���ձ�";
  	Translator t = new Translator();
  	for (int i = 0; i < functionsArr.length; i++){
  		System.out.println("��������ת����������" + (i + 1) + "��");
	  	t.setChQuery(strHead + functionsArr[i] + strEnd);
	    if (t.hasError()){
	      ChWrongMessage[] msgs = t.showWrongMsgs();
	      for (int j = 0; j < msgs.length; j++){
	        System.out.println("�����Դ��󡿣�" + msgs[j]);
	      }
	    }else{
	    	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
	      t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
	      t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
	      t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
	      t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
	      t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF05");
	      t.updateDbTables(t, t.getTables());
	      System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
	      System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
//	      System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
//	      System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
//	      String rXML = t.getXmlString();
//	      System.out.println("TO DB XML: " + rXML);
	    }
	    System.out.println();
  	}
  }
  
  /**
   * Analytical�������Է���
   */
  public void AnalyticalFunctionsTest(){
  	String strHead = "��ѯ ";
  	String[] functionsArr = new String[]{
  			"dense_rank()", 
  			"ntile(20)",
  			"percent_rank()",
  			"percentile_count(AI_94��Ʊ���ձ�.���)",
  			"percentile_desc(230+900)",
  			"rank()"
  		};
  	String strEnd = " ���� AI_94��Ʊ���ձ�";
  	Translator t = new Translator();
  	for (int i = 0; i < functionsArr.length; i++){
  		System.out.println("Analytical Functions ����" + (i + 1) + "��");
	  	t.setChQuery(strHead + functionsArr[i] + strEnd);
	    if (t.hasError()){
	      ChWrongMessage[] msgs = t.showWrongMsgs();
	      for (int j = 0; j < msgs.length; j++){
	        System.out.println("�����Դ��󡿣�" + msgs[j]);
	      }
	    }else{
	    	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
	      t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
	      t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
	      t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
	      t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
	      t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF05");
	      t.updateDbTables(t, t.getTables());
	      System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
	      System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
//	      System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
//	      System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
//	      String rXML = t.getXmlString();
//	      System.out.println("TO DB XML: " + rXML);
	    }
	    System.out.println();
  	}
  }
  
  /**
   * Analytical�������Է���
   */
  public void MiscellaneousFunctionsTest(){
  	String strHead = "��ѯ ";
  	String[] functionsArr = new String[]{
  			"ARGN( 6, 1,2,3,4,5,6 )", 
  			"COALESCE( NULL, 34, 13, 0 )",
  			"IFNULL( NULL, -66 )",
  			"ISNULL( NULL ,-66, 55, 45, NULL, 16 )",
  			"ISNULL( AI_94��Ʊ���ձ�.���, 90 )",
  			"NULLIF( 'a', 'b' )",
  			"NUMBER ( * )",
  		};
  	String strEnd = " ���� AI_94��Ʊ���ձ�";
  	Translator t = new Translator();
  	for (int i = 0; i < functionsArr.length; i++){
  		System.out.println("Analytical Functions ����" + (i + 1) + "��");
	  	t.setChQuery(strHead + functionsArr[i] + strEnd);
	    if (t.hasError()){
	      ChWrongMessage[] msgs = t.showWrongMsgs();
	      for (int j = 0; j < msgs.length; j++){
	        System.out.println("�����Դ��󡿣�" + msgs[j]);
	      }
	    }else{
	    	t.addDbTable("AI_94��Ʊ���ձ�", "CNF");
	      t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
	      t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
	      t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
	      t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
	      t.addDbField("AI_94��Ʊ���ձ�", "����", "CNF05");
	      t.updateDbTables(t, t.getTables());
	      System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
	      System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
//	      System.out.println("EMPTY EXE SQL IS: " + t.getQueryModel().getEmptyExecuteEnString("S001"));
//	      System.out.println("EXE SQL IS: " + t.getQueryModel().getExecuteEnString("S001"));
//	      String rXML = t.getXmlString();
//	      System.out.println("TO DB XML: " + rXML);
	    }
	    System.out.println();
  	}
  }
}
