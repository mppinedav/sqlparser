package translator;

import org.dom4j.Element;

/**
 * WHERE�־���ʽ�����������Ӧ����������Ϣ����
 *
 */
public class WhereListVO {
  private String cnAllWhereStr;   //�������������о���
  private String cnWhereEquElem;  //���������ֶλ���ʽ
  private String cnComparSymbol;  //���Ĺ�ϵ�����
  private String cnWhereValue;    //����������ֵ
  private String checkedFlag;     //�Ƿ�ѡ�б�ʶ
  private boolean isConstant = false ;     //�Ƿ���
  
  public String getCnComparSymbol() {
    return cnComparSymbol;
  }
  public void setCnComparSymbol(String cnComparSymbol) {
    this.cnComparSymbol = cnComparSymbol;
  }
  public String getCnWhereEquElem() {
    return cnWhereEquElem;
  }
  public void setCnWhereEquElem(String cnWhereEquElem) {
    this.cnWhereEquElem = cnWhereEquElem;
  }
  public String getCnWhereValue() {
    return cnWhereValue;
  }
  public void setCnWhereValue(String cnWhereValue) {
    this.cnWhereValue = cnWhereValue;
  }
  
  public String getCnAllWhereStr() {
    return cnAllWhereStr;
  }
  public void setCnAllWhereStr(String cnAllWhereStr) {
    this.cnAllWhereStr = cnAllWhereStr;
  }
  
  public String getCheckedFlag() {
    return checkedFlag;
  }
  public void setCheckedFlag(String checkedFlag) {
    this.checkedFlag = checkedFlag;
  }

  public boolean isConstant() {
    return isConstant;
  }
  public void setConstant(boolean isConstant) {
    this.isConstant = isConstant;
  }
  public void getModelElement(Element parent) {
    Element elem=parent.addElement("WhereListVO");
    elem.addAttribute("cnAllWhereStr", cnAllWhereStr);
    elem.addAttribute("cnWhereEquElem", cnWhereEquElem);
    elem.addAttribute("cnComparSymbol", cnComparSymbol);
    elem.addAttribute("cnWhereValue", cnWhereValue);
    elem.addAttribute("checkedFlag", checkedFlag);
  }
}
