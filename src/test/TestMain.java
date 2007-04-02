package test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import model.parser.ParamModel;
import model.parser.SelectStatementModel;
import model.parser.TableCompareModel;

import parser.*;
import translator.*;
import model.parser.*;

import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.Tool;

public class TestMain {
  public static void main(String[] args) {
    TestMain main = new TestMain();
    main.testTranslator();
//    main.testUnion();
//    main.testCompare();
//    main.testSegment();
  }
  
  public void testSegment(){
    String[] segmentArr = new String[]{"��� (AI_94��Ʊ���ձ�.ʡ/�д���) ��Ϊ c", "(AI_94��Ʊ���ձ�.��� �� AI_94��Ʊ���ձ�.������) �� AI_94��Ʊ�����±�.���"};
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
    
    segmentArr = new String[]{"AI_94��Ʊ���ձ�.ʡ/�д��� + AI_94��Ʊ���ձ�.��� ���� 30", "AI_94��Ʊ�����±�.������ ���� 'abcd'"};
    t = new Translator();
    for (int i = 0; i < segmentArr.length; i++){
      t.setChSegment(t.WHERE, segmentArr[i]);
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
      System.out.println(t.getQueryModel().getChSegment(t.WHERE));
      System.out.println(t.getQueryModel().getEnSegment(t.WHERE));
    }
    
  }
  
  public void testCompare(){
    String str = "��Ƚ� AI_94��Ʊ���ձ�, AI_94��Ʊ�����±� ���� ������ AI_94��Ʊ���ձ�.ʡ/�д��� ���� AI_94��Ʊ�����±�.ʡ/�д��� ���� AI_94��Ʊ���ձ�.�к� ���� 5";
    Translator t = new Translator();
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
    System.out.println(t.getChQuery());
    System.out.println(t.getQueryModel().getEnString());
    String xml = t.getXmlString();
    System.out.println("TO DB XML: " + xml);
    
    Translator t1 = new Translator();
    try{
      QueryModel queryModel = t1.loadModelFromXML(xml);
      if (queryModel instanceof TableCompareModel){
        TableCompareModel tableCompareModel = (TableCompareModel) queryModel;
        System.out.println(tableCompareModel.getChCompareMethod());
        AppDbTable[] appDbTableArr = t1.getInfo().getDbTableInfoToAppTableArr();
        for (int i = 0; i < appDbTableArr.length; i++){
          AppDbTable appDbTable = appDbTableArr[i];
          AppDbField[] appDbFieldArr = appDbTable.getFields();
          System.out.println(appDbTable.getTableName());
        }
        System.out.println(t1.getChWhereStr());
      }
    }catch (DocumentException e){
      e.printStackTrace();
    }
    
  }
  
  public void testUnion(){
    String str = "��ϲ� ��1, ��2";
    
    Translator t = new Translator();
    t.setChQuery(str);
    System.out.println(t.getChQuery());
    t.addDbTable("��1", "CNF");
    t.addDbField("��1", "ʡ/�д���", "CNF01");
    t.addDbField("��1", "�к�", "CNF02");
    t.addDbField("��1", "���", "CNF03");
    t.addDbField("��1", "������", "CNF04");
    t.addDbTable("��2", "CNF_TEST");
    t.addDbField("��2", "ʡ/�д���", "CNF01");
    t.addDbField("��2", "�к�", "CNF02");
    t.addDbField("��2", "���", "CNF03");
    t.addDbField("��2", "������", "CNF04");
    DbTable[] ts = t.getTables();
    t.updateDbTables(t, ts);
    
    System.out.println(t.getChQuery());
    System.out.println(t.getQueryModel().getEnString());
    String xml = t.getXmlString();
    System.out.println("TO DB XML: " + xml);
    Translator t1 = new Translator();
    try{
      QueryModel queryModel = t1.loadModelFromXML(xml);
      if (queryModel instanceof TableUnionModel){
        AppDbTable[] appDbTableArr = t1.getInfo().getDbTableInfoToAppTableArr();
        for (int i = 0; i < appDbTableArr.length; i++){
          AppDbTable appDbTable = appDbTableArr[i];
          AppDbField[] appDbFieldArr = appDbTable.getFields();
          System.out.println(appDbTable.getTableName());
        }
      }
    }catch (DocumentException e){
      e.printStackTrace();
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
    str = " ��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д���, ����.�к� ��Ϊ �к�, ���(AI_94��Ʊ���ձ�.���) ��Ϊ ��� " +
          " ���� AI_94��Ʊ���ձ� ��Ϊ ����" + 
          " ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� {��������} ���� ��ƽ����(AI_94��Ʊ���ձ�.���) ���� 40 " +
          " ���� AI_94��Ʊ���ձ�.ʡ/�д���,AI_94��Ʊ���ձ�.�к�" +
          " ���� ���(AI_94��Ʊ���ձ�.���) ����, AI_94��Ʊ���ձ�.�к� ����";
    str = "��ѯ ���(AI_94��Ʊ���ձ�.���) ���� AI_94��Ʊ���ձ� ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '����' ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� {��������1} ���� AI_94��Ʊ���ձ�.ʡ/�д���, AI_94��Ʊ���ձ�.�к� ���� ���(AI_94��Ʊ���ձ�.���) , AI_94��Ʊ���ձ�.�к� ����";
    
    Translator t = new Translator();
    t.setChQuery(str);
    System.out.println(t.getChQuery());
    t.addDbTable("AI_94��Ʊ���ձ�", "CNF", "casdb2");
    t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
    t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
    t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
    t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
//    t.addDbTable("��2", "CNF_TEST");
//    t.addDbField("��2", "ʡ/�д���", "CNF01");
//    t.addDbField("��2", "�к�", "CNF02");
//    t.addDbField("��2", "���", "CNF03");
//    t.addDbField("��2", "������", "CNF04");
    DbTable[] ts = t.getTables();
    t.updateDbTables(t, ts);
    
    QueryModel[] paramModelArr = t.getQueryModel().getModelsFromAllChildrenByClass(ParamModel.class);
    for (int i = 0; i < paramModelArr.length; i++){
      ParamModel paramModel = (ParamModel) paramModelArr[i];
      System.out.println(paramModelArr[i].getEnString());
      paramModel.setParamValue("01");
      paramModel.setCircleType(t.CIRCLE_TYPE_WHERE);
      System.out.println(paramModelArr[i].getEnString());
    }
    System.out.println(t.getQueryModel().getEnString());
    
//    t.addDbTable("AI_94��Ʊ���ձ�", "CNF", "casdb2");
//    t.addDbField("AI_94��Ʊ���ձ�", "ʡ/�д���", "CNF01");
//    t.addDbField("AI_94��Ʊ���ձ�", "�к�", "CNF02");
//    t.addDbField("AI_94��Ʊ���ձ�", "���", "CNF03");
//    t.addDbField("AI_94��Ʊ���ձ�", "������", "CNF04");
    
    
    if (t.getQueryModel() instanceof TableUnionModel){
      System.out.println("xx");
    }else if (t.getQueryModel() instanceof SelectStatementModel){
      System.out.println(t.getQueryModel().getCircleType());
      System.out.println("yy");
    }
    
//    AppDbTable[] tmpTables = new AppDbTable[1];
//    AppDbTable tmpTable = new AppDbTable();
//    tmpTable.setTableName("AI_94��Ʊ���ձ�");
//    tmpTable.setTableEnName("CNF");
//    tmpTable.addDbField("ʡ/�д���", "CNF01");
//    tmpTable.addDbField("�к�", "CNF02");
//    tmpTable.addDbField("���", "CNF03");
//    tmpTables[0] = tmpTable;
//    tmpTable = new AppDbTable();
//    tmpTable.setTableName("AI_94��Ʊ�����±�");
//    tmpTable.setTableEnName("CNFF");
//    tmpTable.addDbField("ʡ/�д���", "CNF01");
//    tmpTable.addDbField("�к�", "CNF02");
//    tmpTable.addDbField("���", "CNF03");
//    tmpTables[1] = tmpTable;
//    t.setTableInfoToModel(ts, tmpTables);
//    t.setTableInfo(ts);
    
    for (int i = 0; i < ts.length; i++){
      System.out.println(ts[i].getChName());
      System.out.println(ts[i].getEnName());
      
      for (Iterator it = ts[i].getFields().iterator(); it.hasNext();){
        DbField dbField = (DbField) it.next();
        System.out.println(dbField.getChName());
        System.out.println(dbField.getEnName());
      }
    }
    
//    setTableInfo(ts);
    
//    System.out.println(t.getChSelectStr());
//    System.out.println(t.getChFromStr());
//    System.out.println(t.getChWhereStr());
//    System.out.println(t.getChGroupByStr());
//    System.out.println(t.getChOrderByStr());
    
    if (t.hasError()){
      ChWrongMessage[] msgs = t.showWrongMsgs();
      for (int i = 0; i < msgs.length; i++)
        System.out.println(msgs[i]);
    }else{
      AliasModel[] aliasModels = t.getAliasModelListVOArrByModel();
      for (int i = 0; i < aliasModels.length; i++){
        aliasModels[i].setEnAlias("Xalias" + i);
      }
      
      SelectListVO[] _selectListVOArr = t.getSelectListVOArr();
      FromListVO[] _fromListVOArr = t.getFromListVOArr();
      WhereListVO[] _whereListVOArr = t.getWhereListVOArr();
      GroupByListVO[] _groupListVOArr = t.getGroupByListVOArr();
      OrderByListVO[] _orderListVOArr = t.getOrderByListVOArr();

      for (int i = 0; i < _selectListVOArr.length; i++){
        _selectListVOArr[i].setFieldDataType("String");
      }
      t.setSelectListVOArr(_selectListVOArr);

      for (int i = 0; i < _whereListVOArr.length; i++){
        _whereListVOArr[i].setCheckedFlag("1");
      }
      t.setWhereListVOArr(_whereListVOArr);

      SelectListVO[] selectListVOArr = t.getSelectListVOArr();
      QueryModel[] aliasModelArr = t.getQueryModel().getModelsFromAllChildrenByClass(AliasModel.class);
      for (int i = 0; i < aliasModelArr.length; i++){
        AliasModel aliasModel = (AliasModel) aliasModelArr[i];
        aliasModel.setEnAlias("enAlias" + i);
      }

      QueryModel[] orderAliasModelArr = t.getQueryModel().getModelsFromAllChildrenByClass(OrderAliasModel.class);
      for (int i = 0; i < orderAliasModelArr.length; i++){
        OrderAliasModel orderAliasModel = (OrderAliasModel) orderAliasModelArr[i];
        orderAliasModel.setEnAlias("enOrderAlias" + i);
      }
      System.out.println("CN SQL IS: " + t.getQueryModel().getChString());
      System.out.println("EN SQL IS: " + t.getQueryModel().getEnString());
      String xml1 = t.getXmlString();
      System.out.println("TO DB XML: " + xml1);
    }
    
    Translator t1 = new Translator();
    String rXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "<query><ch_query_string>��ѯ Ψһ ���� , ���(e.�ֶ�2) ��Ϊ a , ��1.�ֶ�1 , ( ��2.�ֶ�3 �� ��2.�ֶ�4 ) �� ��2.�ֶ�5 , ��3.�ֶ�1 �� ��3.�ֶ�2 , ��ƽ����(��4.�ֶ�3)  ���� ��1 ��Ϊ e, ��2 ��Ϊ f, ��3, ��4 ���� 1 ���� 1 ���� e.�ֶ�1 + e.�ֶ�2 ���� '30' ���� ��2.�ֶ�3 ���� 'abcd' ���� ��3.�ֶ�1 �ǿ� ���� ��3.�ֶ�2 ��Χ 1 2 ���� ��1.�ֶ�1, �ַ�����ȡ(��1.�ֶ�3, 1, 4), ��ֵת�ַ���(��2.�ֶ�3), ��2.�ֶ�3 �� ��2.�ֶ�4 ����  a, ��1.�ֶ�1 ����, ��ֵת�ַ���(��2.�ֶ�3), ���(��2.�ֶ�4) ����, �ַ�����ȡ(��1.�ֶ�3, 1, 4)</ch_query_string><db_info ch_name=\"��1\" en_name=\"table0\"><db_field ch_name=\"�ֶ�2\" en_name=\"field2\"/><db_field ch_name=\"�ֶ�3\" en_name=\"field3\"/><db_field ch_name=\"�ֶ�1\" en_name=\"field1\"/></db_info><db_info ch_name=\"��2\" en_name=\"table1\"><db_field ch_name=\"�ֶ�4\" en_name=\"field4\"/><db_field ch_name=\"�ֶ�5\" en_name=\"field5\"/><db_field ch_name=\"�ֶ�3\" en_name=\"field3\"/></db_info><db_info ch_name=\"��3\" en_name=\"table2\"><db_field ch_name=\"�ֶ�2\" en_name=\"field2\"/><db_field ch_name=\"�ֶ�1\" en_name=\"field1\"/></db_info><db_info ch_name=\"��4\" en_name=\"table3\"><db_field ch_name=\"�ֶ�3\" en_name=\"field3\"/></db_info><selectListEqu><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/></selectListEqu><whereListEqu><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/></whereListEqu><aliasListEqu><aliasListVO alias=\"a\" enAlias=\"enAlias0\"/><aliasListVO alias=\"e\" enAlias=\"enAlias1\"/><aliasListVO alias=\"f\" enAlias=\"enAlias2\"/></aliasListEqu><orderAliasListEqu><orderAliasListVO alias=\"a\" enAlias=\"enOrderAlias0\"/></orderAliasListEqu></query>";
    rXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
      + "<query><ch_query_string>��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д��� , AI_94��Ʊ���ձ�.�к� ��Ϊ �к� , ���(AI_94��Ʊ���ձ�.���) ��Ϊ ���  ���� AI_94��Ʊ���ձ� ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '0200' ���� AI_94��Ʊ���ձ�.ʡ/�д���, AI_94��Ʊ���ձ�.�к� ���� ���(AI_94��Ʊ���ձ�.���) ����, AI_94��Ʊ���ձ�.�к� ����</ch_query_string><db_info ch_name=\"AI_94��Ʊ���ձ�\" en_name=\"CNF\" flag=\"casdb2\" tableParam=\"\"><db_field ch_name=\"�к�\" en_name=\"CNF02\" fieldParam=\"\"/><db_field ch_name=\"������\" en_name=\"CNF04\" fieldParam=\"\"/><db_field ch_name=\"ʡ/�д���\" en_name=\"CNF01\" fieldParam=\"\"/><db_field ch_name=\"���\" en_name=\"CNF03\" fieldParam=\"\"/></db_info><selectListEqu><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/></selectListEqu><whereListEqu><WhereListVO checkedFlag=\"1\"/></whereListEqu><aliasListEqu><aliasListVO alias=\"ʡ/�д���\" enAlias=\"enAlias0\"/><aliasListVO alias=\"�к�\" enAlias=\"enAlias1\"/><aliasListVO alias=\"���\" enAlias=\"enAlias2\"/></aliasListEqu><orderAliasListEqu/></query>";
    rXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
      + "<query><ch_query_string circleType=\"1\">��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д��� , AI_94��Ʊ���ձ�.�к� ��Ϊ �к� , ���(AI_94��Ʊ���ձ�.���) ��Ϊ ���  ���� AI_94��Ʊ���ձ� ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '0200' ���� AI_94��Ʊ���ձ�.ʡ/�д���, AI_94��Ʊ���ձ�.�к� ���� ���(AI_94��Ʊ���ձ�.���) ����, AI_94��Ʊ���ձ�.�к� ����</ch_query_string><db_info ch_name=\"AI_94��Ʊ���ձ�\" en_name=\"CNF\" flag=\"casdb2\" tableParam=\"CNF_table_Param\"><db_field ch_name=\"�к�\" en_name=\"CNF02\" fieldParam=\"\"/><db_field ch_name=\"������\" en_name=\"CNF04\" fieldParam=\"\"/><db_field ch_name=\"ʡ/�д���\" en_name=\"CNF01\" fieldParam=\"\"/><db_field ch_name=\"���\" en_name=\"CNF03\" fieldParam=\"\"/></db_info><selectListEqu><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/></selectListEqu><whereListEqu><WhereListVO checkedFlag=\"1\"/></whereListEqu><aliasListEqu><aliasListVO alias=\"ʡ/�д���\" enAlias=\"enAlias0\"/><aliasListVO alias=\"�к�\" enAlias=\"enAlias1\"/><aliasListVO alias=\"���\" enAlias=\"enAlias2\"/></aliasListEqu><orderAliasListEqu/></query>";
//    rXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
//       + "<query><ch_query_string circleType=\"2\">��ѯ AI_94��Ʊ���ձ�.ʡ/�д��� ��Ϊ ʡ/�д��� , AI_94��Ʊ���ձ�.�к� ��Ϊ �к� , ���(AI_94��Ʊ���ձ�.���) ��Ϊ ���  ���� AI_94��Ʊ���ձ� ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� '����' ���� AI_94��Ʊ���ձ�.ʡ/�д��� ���� {��������1} ���� AI_94��Ʊ���ձ�.ʡ/�д���, AI_94��Ʊ���ձ�.�к� ���� ���(AI_94��Ʊ���ձ�.���) ����, AI_94��Ʊ���ձ�.�к� ����</ch_query_string><db_info ch_name=\"AI_94��Ʊ���ձ�\" en_name=\"CNF\" flag=\"casdb2\" tableParam=\"\"><db_field ch_name=\"�к�\" en_name=\"CNF02\" fieldParam=\"\"/><db_field ch_name=\"������\" en_name=\"CNF04\" fieldParam=\"\"/><db_field ch_name=\"ʡ/�д���\" en_name=\"CNF01\" fieldParam=\"\"/><db_field ch_name=\"���\" en_name=\"CNF03\" fieldParam=\"\"/></db_info><selectListEqu><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/><SelectListVO fieldDataType=\"String\"/></selectListEqu><whereListEqu><WhereListVO checkedFlag=\"1\"/><WhereListVO checkedFlag=\"1\"/></whereListEqu><aliasListEqu><aliasListVO alias=\"ʡ/�д���\" enAlias=\"enAlias0\"/><aliasListVO alias=\"�к�\" enAlias=\"enAlias1\"/><aliasListVO alias=\"���\" enAlias=\"enAlias2\"/></aliasListEqu><orderAliasListEqu/></query>";
    try{
      System.out.println(rXML);
      QueryModel m = t1.loadModelFromXML(rXML);
      t1.setAliasModelListVOArrByXML();
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
      AliasModel[] aliasModels = t1.getAliasModelListVOArrByModel();
      System.out.println(m.getChString());
      System.out.println(m.getEnString());
      System.out.println(t1.getXmlString());
    }catch (DocumentException e){
      e.printStackTrace();
    }
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
