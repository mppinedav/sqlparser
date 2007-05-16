package model.parser;

import util.StringUtil;

public class TableUnionModel extends QueryModel {
	private static final String UNION_CN_NAME = "��ϲ� ";
  private static final String unionStr = "SELECT _FIELDS_ FROM _FROM_TABLE_NAME_";
  private static final String unionAll = " UNION ALL ";
  private static final String unionIntoStr = "INSERT INTO _INTO_TABLE_NAME_ (_FIELDS_) ";
  private static final String unionEmptyStr = "SELECT _FIELDS_ INTO _INTO_TABLE_NAME_ FROM _FROM_TABLE_NAME_ WHERE 1 = 0";
  
  /**
   * ���ӱ�ģ�Ͷ����б�
   * @param tableListModel
   */
  public void addTableListModel(TableListModel tableListModel){
  	addChild(tableListModel);
  }
  
  /**
   * ��ȡ��ʽ���������SQL���
   */
  public String getChString() {
    QueryModel[] tableModelArr = getModelsFromAllChildrenByClass(TableModel.class);
    
    String ret = UNION_CN_NAME;
    for (int i = 0; i < tableModelArr.length; i++){
      ret += ((TableModel) tableModelArr[i]).getTableName();
      if (i < tableModelArr.length - 1)
        ret += ", ";
    }
    return ret;
  }
  
  /**
   * ��ȡ��׼��Ӣ��SQL���
   */
  public String getEnString() {
    return getExecuteEnString("");
  }
  
  /**
   * ��ȡ��ִ�е�Ӣ��SQL��䣨���Ҫ����ı�����Ϊ�գ���ɽ��в����¼������
   * @param intoTableName Ҫ����ı���
   * @return String ��ִ�е�Ӣ��SQL���
   */
  public String getExecuteEnString(String intoTableName) {
    String rValue = "";
    QueryModel[] tableModelArr = getModelsFromAllChildrenByClass(TableModel.class);
    for (int i = 0; i < tableModelArr.length; i++){
      DbTableModel _dbTableModel = getDbTableModel();
      TableModel tableModel = (TableModel) tableModelArr[i];
      String enFieldStr = _dbTableModel.getFieldsEnStr(tableModel.getChString());
      if (intoTableName == null || intoTableName.equals("") || intoTableName.length() == 0){
      	//����ϲ��ı���Ϊ�ڶ��ż�֮����������"UNION ALL"
      	if (i > 0)
      		rValue += unionAll;
      	
      	//��SQL����滻�ɵ�ǰ�ı���/�ֶ���
        rValue += StringUtil.replace(unionStr,
        		new String[]{"_FIELDS_", "_FROM_TABLE_NAME_"},
        		new String[]{enFieldStr, tableModel.getEnString()}
        	);
      }else{
      	//��ȡҪ�������ʱ��Ӣ��SQL���
      	if (i == 0){
      		rValue = StringUtil.replace(unionIntoStr,
      				new String[]{"_INTO_TABLE_NAME_", "_FIELDS_"},
      				new String[]{intoTableName, enFieldStr}
      			);
      	}
      	
      	//����ϲ��ı���Ϊ�ڶ��ż�֮����������"UNION ALL"
      	if (i > 0)
      		rValue += unionAll;
      	
      	//��SQL����滻�ɵ�ǰ�ı���/�ֶ���
        rValue += StringUtil.replace(unionStr,
        		new String[]{"_FIELDS_", "_FROM_TABLE_NAME_"},
        		new String[]{enFieldStr, tableModel.getEnString()}
        	);
        
      }
    }
    return rValue;
  }
  
  /**
   * ��ȡ�յĿ�ִ�е�Ӣ��SQL��䣨ͨ���˷����ɽ��б�ṹ�Ĵ�����
   * @param intoTableName Ҫ�����ı���
   * @return String �յĿ�ִ�е�Ӣ��SQL���
   */
  public String getEmptyExecuteEnString(String intoTableName) {
    String rValue = "";
    QueryModel[] tableModelArr = getModelsFromAllChildrenByClass(TableModel.class);
    if (tableModelArr.length > 0){
    	DbTableModel _dbTableModel = getDbTableModel();
    	TableModel tableModel = (TableModel) tableModelArr[0];
    	String enFieldStr = _dbTableModel.getFieldsEnStr(tableModel.getChString());
    	rValue = StringUtil.replace(unionEmptyStr,
          new String[]{"_FIELDS_", "_FROM_TABLE_NAME_", "_INTO_TABLE_NAME_"},
          new String[]{enFieldStr, tableModel.getEnString(), intoTableName}
        );
    	
    }
    return rValue;
  }
}
