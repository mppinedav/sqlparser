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
  			"���(AI_94��Ʊ���ձ�.���)",
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
  			"������������(123.45)",
  			"������ֵ(30)",
  			"������ֵ(45)",
  			"������תΪ����(0.5236)",
  			"����ֵ(3)",
  			"������������(123.45)",
  			"����Ȼ����(20)",
  			"��10Ϊ�׵Ķ���(50)",
  			"����(5, 3)",
  			"��Բ����(*)",
  			"�����ֵĴ���ֵ(2, 3)",
  			"������תΪ����(30)",
  			"��0��1��������()",
  			"��0��1��������(4)",
  			"ȡ��(5, 3)",
  			"��ʽ����ֵ(123.453, 2)",
  			"��ֵ�ķ���(-123)",
  			"������ֵ(60)",
  			"��ƽ����(4)",
  			"������ֵ(45)",
  			"����ֵ��ʽ��(123.346, 2)",
  			"ȡ��ʽ����ֵ(123.453, 1)",
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
  			"���ַ����ĳ���('CNF_2007')",
  			"������('CNF', AI_94��Ʊ���ձ�.ʡ/�д���)",
  			"����������������ֵ('test', 'chest')",
  			"�ַ�������(0, 'office', 'ladys')",
  			"������תΪ����(0.5236)",
  			"�ַ���תΪСд('Case')",
  			"�ַ������('abcdefghijk', 4)",
  			"ȡ�ַ����ĳ���('abcd efg')",
  			"�󴮳���λ��('abc def ghijk', 'def', 2)",
  			"���ַ���תΪСд('Case')",
  			"ȥ����ո�(' abc')",
  			"���ַ����Ĵ洢����(AI_94��Ʊ���ձ�.ʡ/�д���)",
  			"���һ�γ���λ��('%hoco%', 'chocolate')",
  			"���ַ�������('abc', 4)",
  			"�ַ����滻('abc def gh', 'abc', '123')",
  			"�ַ�������('def', 3)",
  			"�ַ����ҽ�('abcdefgh', 2)",
  			"ȥ���ҿո�('abc ')",
  			"���ַ������ƶ�('toast', 'coast')",
  			"�ַ�������('coop', 51)",
  			"���ַ�������ֵ('Smith')",
  			"��ֵת�ַ���( 123.45)",
  			"�ַ����ϲ�('abc', 'def', '12')",
  			"�ַ���ɾ���滻('abcdefgh', 1, 4, 'xy')",
  			"�ַ�����ȡ('abcdefgh', 1, 3)",
  			"ȥ���ո�(' abc ')",
  			"�ַ���תΪ��д('case')",
  			"���ַ���תΪ��д('case')"
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
  			"�����ڵķ�������(month, '2007-05-01')",
  			"�����ڵķ���ֵ(month, '2007-05-01')",
  			"תΪ����ʱ��('2007-01-01 12:01:20')",
  			"תΪ����('2007-01-01 12:01:20')",
  			"���Ӧ��������('2007-05-19')",
  			"������('2007-05-11 12:01:20')",
  			"���Ӧ��('2007-05-11 12:01:20')",
  			"���Ӧ����ֵ('2007-05-11 12:01:20')",
  			"��Сʱ��('2007-05-11 12:01:20')",
  			"���ӦСʱ('2007-05-11 12:01:20')",
  			"�������('2007-05-11 12:01:20')",
  			"���Ӧ����('2007-05-11 12:01:20')",
  			"���·�����('2007-05-11 12:01:20')",
  			"������('2007-05-11 12:01:20')",
  			"���Ӧ��('2007-05-11 12:01:20')",
  			"ȡ��ǰ����ʱ��(*)",
  			"���Ӧ����('2007-05-11 12:01:20')",
  			"������('2007-05-11 12:01:20')",
  			"���Ӧ��('2007-05-11 12:01:20')",
  			"��ǰ����(*)",
  			"������('2007-05-11 12:01:20')",
  			"������('2007-05-11 12:01:20')",
  			"���Ӧ��('2007-05-11 12:01:20')",
  			"������(2007, 5, 11)",
  			"��ǰ����ʱ��()",
  			"�������(day, 102, '2007/05/11')",
  			"�������(day, '2007-01-01', '2007-05-11')"
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
  			"�ַ�תΪ����(char(10), AI_94��Ʊ���ձ�.����, 120)",
  			"ʮ������תΪ����('0x00000100')",
  			"����תΪʮ������(120)",
  			"Ϊ������('2007-01-01 12:01:20')",
  			"Ϊ��ֵ��(34.78)"
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
}
