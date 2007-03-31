package model.parser;

import util.StringUtil;

public class TableCompareModel extends QueryModel {
  private int TABLE_NUMBER = 2;
  private static final String compareStr1 = "SELECT _FIELDS_";
  private static final String compareStr2 = " INTO _TABLE_NAME_";
  private static final String compareStr3 = " FROM _TABLE1_ WHERE _METHOD_ (SELECT * FROM _TABLE2_ WHERE _CONDITION_)";
  private String chTable1, chTable2, intoTable, method, equation;
  
  private String compareMethod;
 
  public String getCompareMethod() {
    String rValue = "";
    if (compareMethod.equals("����")){
      rValue = "exist";
    }else if (compareMethod.equals("������")){
      rValue = "not exist";
    }else{
      rValue = compareMethod;
    }
    return rValue;
  }
  
  public String getChCompareMethod() {
    String rValue = "";
    if (compareMethod.equals("exist")){
      rValue = "����";
    }else if (compareMethod.equals("not exist")){
      rValue = "������";
    }else{
      rValue = compareMethod;
    }
    return rValue;
  }
  
  public void setCompareMethod(String compareMethod) {
    this.compareMethod = compareMethod;
  }

  public void addTableModel1(TableModel tableModel){
    addChild(tableModel);
  }
  
  public void addTableModel2(TableModel tableModel){
    addChild(tableModel);
  }
  
  public void setSearchCondition(SearchConditionModel cond){
    addChild(cond);
  }
  
  public String getChString() {
    String ret = "���Ƚ� ";
    QueryModel[] tableModelArr = getModelsFromAllChildrenByClass(TableModel.class);
    SearchConditionModel cond = (SearchConditionModel)getFirstModelByClass(SearchConditionModel.class);
    if (tableModelArr.length == TABLE_NUMBER){
      TableModel tableModel1 = (TableModel) tableModelArr[0];
      TableModel tableModel2 = (TableModel) tableModelArr[1];
      ret += tableModel1.getChString();
      ret += ", ";
      ret += tableModel2.getChString();
      ret += " ���� ";
      ret += getChCompareMethod();
      ret += " " + cond.getChString();
    }
    return ret;
  }

  public String getEnString() {
    String rValue = "";
    QueryModel[] tableModelArr = getModelsFromAllChildrenByClass(TableModel.class);
    SearchConditionModel cond = (SearchConditionModel)getFirstModelByClass(SearchConditionModel.class);
    if (tableModelArr.length == TABLE_NUMBER){
      DbTableModel _dbTableModel = getDbTableModel();
      TableModel tableModel1 = (TableModel) tableModelArr[0];
      TableModel tableModel2 = (TableModel) tableModelArr[1];
      String enFieldStr = _dbTableModel.getFieldsEnStr(tableModel1.getChString());
      return StringUtil.replace(
          (compareStr1 + compareStr3),
          new String[]{"_FIELDS_", "_TABLE1_", "_TABLE2_", "_METHOD_", "_CONDITION_"},
          new String[]{enFieldStr, tableModel1.getEnString(), tableModel2.getEnString(), getCompareMethod(), cond.getEnString()}
      );
    }
    return rValue;
  }
  
  
}