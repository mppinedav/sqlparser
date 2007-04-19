package translator;

import java.util.Iterator;

import org.dom4j.Element;

/**
 * SELECT�־���ֶ���/���ʽ����������Ϣ����
 *
 */
public class SelectListVO {
  private String cnColumnEquElem; //�����ֶλ���ʽ
  private String cnFieldAlias;    //�����ֶλ���ʽ�����ı���
  private String enFieldAlias;    //�����ֶλ���ʽ��Ӣ�ı���
  private String fieldDataType;   //�ֶ���������
  
  public String getCnColumnEquElem() {
    return cnColumnEquElem;
  }
  public void setCnColumnEquElem(String cnColumnEquElem) {
    this.cnColumnEquElem = cnColumnEquElem;
  }
  public String getCnFieldAlias() {
    return cnFieldAlias;
  }
  public void setCnFieldAlias(String cnFieldAlias) {
    this.cnFieldAlias = cnFieldAlias;
  }
  
  public String getEnFieldAlias() {
    return enFieldAlias;
  }
  public void setEnFieldAlias(String enFieldAlias) {
    this.enFieldAlias = enFieldAlias;
  }
  
  public String getFieldDataType() {
    return fieldDataType;
  }
  public void setFieldDataType(String fieldDataType) {
    this.fieldDataType = fieldDataType;
  }
  public void getModelElement(Element parent) {
    Element elem=parent.addElement("SelectListVO");
    elem.addAttribute("cnColumnEquElem", cnColumnEquElem);
    elem.addAttribute("cnFieldAlias", cnFieldAlias);
    elem.addAttribute("enFieldAlias", enFieldAlias);
    elem.addAttribute("fieldDataType", fieldDataType);
  }
}
