package model.parser;

/**
 * �ֶλ��ֶα��ʽ��������
 */
public class AliasModel extends QueryModel {
  private String alias;
  private String enAlias;
  
  public AliasModel(String alias) {
    this.alias=alias;
  }
  
  public String getAlias() {
    return alias;
  }
  
  public void setAlias(String alias) {
    this.alias = alias;
  }
  
  public void setEnAlias(String enAlias) {
    this.enAlias = enAlias;
  }
  
  public String getEnAlias() {
    return enAlias;
  }

  public String getChString() {
    return " ��Ϊ "+alias;
  }
  
  public String getEnString() {
    if (enAlias != null && !enAlias.equals(""))
      return " as " + enAlias;
    else
      return " as " + alias;
  }
}
