package model.parser;

public class FieldModel extends QueryModel {
  private String fieldName;
  private String tableName;
  private String fieldEnName;
  private String tableEnName;

  public FieldModel(String fieldName){
    this.fieldName = fieldName;
  }

  public FieldModel(String fieldName, String tableName){
    this.fieldName = fieldName;
    this.tableName = tableName;
  }

  public String getTableName() {
    return tableName;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldEnName(String fieldEnName) {
    this.fieldEnName = fieldEnName;
  }

  public void setTableEnName(String tableEnName) {
    this.tableEnName = tableEnName;
  }

  public String getChString() {
    String ret = fieldName;
    if (tableName != null)
      ret = tableName + "." + ret;
    return ret;
  }

  public String getEnString() {
    String ret = fieldEnName;
    if (ret == null || ret.equals(""))
    	ret = fieldName;
    //�������Ӣ�ı���
    if (tableEnName != null && tableEnName.length() > 0){
      String[] tableEnNameArr = tableEnName.split(" as ");
      //���Ӣ�ı����д��ڱ���(CNF AS CNF2007)����Ӣ�ı���ΪӢ�ı���CNF2007
      if (tableEnNameArr != null && tableEnNameArr.length > 0 && tableEnNameArr.length == 2){
        if (tableEnNameArr[1] != null && !tableEnNameArr[1].equals(""))
          tableEnName = tableEnNameArr[1].trim();
      }
      ret = tableEnName + "." + ret;
    }
    return ret;
  }

  public String toString() {
    return tableName + "." + fieldName;
  }
}
