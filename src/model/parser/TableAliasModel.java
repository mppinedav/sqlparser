package model.parser;

/**
 * ��������ģ�Ͷ���
 */
public class TableAliasModel extends QueryModel {
  private String alias;
  private String enAlias;
  
  public TableAliasModel(String alias) {
    this.alias=alias;
  }
  
  public String getAlias() {
    return alias;
  }
  
  public String getEnAlias() {
    return enAlias;
  }

  public void setEnAlias(String enAlias) {
    this.enAlias = enAlias;
  }

  public String getChString() {
    return " ��Ϊ " + alias;
  }
  
  public String getEnString() {
    if (enAlias != null && !enAlias.equals(""))
      return " as " + enAlias;
    else
      return " as " + alias;
  }
}
