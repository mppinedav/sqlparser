package translator;

import org.dom4j.Element;

/**
 * GROUP BY�־��ֶ�/�ֶα��ʽ����Ϣ����
 *
 */
public class GroupByListVO {
  private String cnGroupByEquElem;  //�����ֶ�/�ֶα��ʽ

  public String getCnGroupByEquElem() {
    return cnGroupByEquElem;
  }

  public void setCnGroupByEquElem(String cnGroupByEquElem) {
    this.cnGroupByEquElem = cnGroupByEquElem;
  }
  
  public void getModelElement(Element parent){
    Element elem=parent.addElement("GroupByListVO");
    elem.addAttribute("cnGroupByEquElem", cnGroupByEquElem);
  }
  
}
