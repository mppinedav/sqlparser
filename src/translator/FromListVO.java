package translator;

import org.dom4j.Element;

/**
 * FROM�־�ı�������������Ϣ����
 *
 */
public class FromListVO {
  private String cnTableName;     //���ı���
  private String cnTAbleAlias;    //���ı����ı���
  
  public String getCnTAbleAlias() {
    return cnTAbleAlias;
  }
  public void setCnTAbleAlias(String cnTAbleAlias) {
    this.cnTAbleAlias = cnTAbleAlias;
  }
  public String getCnTableName() {
    return cnTableName;
  }
  public void setCnTableName(String cnTableName) {
    this.cnTableName = cnTableName;
  }
  
  public void getModelElement(Element parent) {
    Element elem=parent.addElement("FromListVO");
    elem.addAttribute("cnTableName", cnTableName);
    elem.addAttribute("cnTAbleAlias", cnTAbleAlias);
  }
  
}
