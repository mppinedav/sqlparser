package translator.model;

/**
 * ORDER BY字句字段、排序等信息对象
 *
 */
public class OrderByListVO {
  private String cnOrerByEquElem;     //中文字段/字段表达式
  private String cnOrderType;         //排序类型（ASC/DESC）
  
  public String getCnOrderType() {
    return cnOrderType;
  }
  public void setCnOrderType(String cnOrderType) {
    this.cnOrderType = cnOrderType;
  }
  public String getCnOrerByEquElem() {
    return cnOrerByEquElem;
  }
  public void setCnOrerByEquElem(String cnOrerByEquElem) {
    this.cnOrerByEquElem = cnOrerByEquElem;
  }
  
  
}
