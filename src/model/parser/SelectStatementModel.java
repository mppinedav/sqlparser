package model.parser;

public class SelectStatementModel extends QueryModel {
  
  /**
   * ����SELECT�Ӿ�SELECT�б�ģ�Ͷ���
   * @param model
   */
  public void setSelectList(SelectListModel model) {
    addChild(model);
  }
  
  /**
   * ����FROM�Ӿ����б�ģ�Ͷ���
   * @param model
   */
  public void setTableList(TableListModel model) {
    addChild(model);
  }
  
  /**
   * ����WHERE�Ӿ�����ģ�Ͷ���
   * @param model
   */
  public void setSearchCondition(SearchConditionModel model) {
    addChild(model);
  }
  
  /**
   * ����GROUP BY�Ӿ�����б�ģ��
   * @param model
   */
  public void setGroupExpressionList(AggregateExprListModel model) {
    addChild(model);
  }
  
  /**
   * ����ORDER�Ӿ������б�ģ��
   * @param model
   */
  public void setOrderExpressionList(OrderExpressionListModel model) {
    addChild(model);
  }
  
  /**
   * ��ȡƬ���Ӿ�ĸ�ʽ������SQL���
   * @param segmentType Ƭ���Ӿ�����
   * @return String ��ʽ������SQL���
   */
  public String getChSegment(String segmentType) {
    String rValue = "";
    SelectListModel sl = (SelectListModel) getFirstModelByClass(SelectListModel.class);
    SearchConditionModel cond = (SearchConditionModel) getFirstModelByClass(SearchConditionModel.class);
    if (segmentType.equals("column")){
      rValue = sl.getChString();
    }else if (segmentType.equals("where")){
      rValue = cond.getChString();
    }
    return rValue;
  }
  
  /**
   * ��ȡƬ���Ӿ�ı�׼Ӣ��SQL���
   * @param segmentType Ƭ���Ӿ�����
   * @return ��׼Ӣ��SQL���
   */
  public String getEnSegment(String segmentType) {
    String rValue = "";
    SelectListModel sl = (SelectListModel) getFirstModelByClass(SelectListModel.class);
    SearchConditionModel cond = (SearchConditionModel) getFirstModelByClass(SearchConditionModel.class);
    if (segmentType.equals("column")){
      rValue = sl.getEnString();
    }else if (segmentType.equals("where")){
      rValue = cond.getEnString();
    }
    return rValue;
  }
  
  /**
   * ��ȡ������ѯ���ĸ�ʽ������SQL���
   * @return String ��ʽ������SQL���
   */
  public String getChString() {
    SelectListModel sl = (SelectListModel) getFirstModelByClass(SelectListModel.class);
    TableListModel tl = (TableListModel) getFirstModelByClass(TableListModel.class);
    SearchConditionModel cond = (SearchConditionModel) getFirstModelByClass(SearchConditionModel.class);
    AggregateExprListModel aggr = (AggregateExprListModel) getFirstModelByClass(AggregateExprListModel.class);
    OrderExpressionListModel order = (OrderExpressionListModel) getFirstModelByClass(OrderExpressionListModel.class);
    String ret = "��ѯ " + sl.getChString();
    if (tl != null)
      ret += " ���� " + tl.getChString();
    if (cond != null)
      ret += " ���� " + cond.getChString();
    if (aggr != null)
      ret += " ���� " + aggr.getChString();
    if (order != null)
      ret += " ���� " + order.getChString();
    return ret;
  }
  
  /**
   * ��ñ�׼��Ӣ��SQL���
   */
  public String getEnString() {
   return getExecuteEnString("");
  }
  
  /**
   * ��ÿ�ִ�е�Ӣ��SQL���
   * @param intoTableName INTO�ı�����Ϊ��ʱ��ʾ��׼��SQL���
   * @return String ��ִ�е�Ӣ��SQL���
   */
   public String getExecuteEnString(String intoTableName) {
    SelectListModel sl = (SelectListModel) getFirstModelByClass(SelectListModel.class);
    TableListModel tl = (TableListModel) getFirstModelByClass(TableListModel.class);
    SearchConditionModel cond = (SearchConditionModel) getFirstModelByClass(SearchConditionModel.class);
    AggregateExprListModel aggr = (AggregateExprListModel) getFirstModelByClass(AggregateExprListModel.class);
    OrderExpressionListModel order = (OrderExpressionListModel) getFirstModelByClass(OrderExpressionListModel.class);
    
    String ret = "SELECT " + sl.getEnString();
    if (intoTableName != null && !intoTableName.equals("")){
      ret += " INTO " + intoTableName;
      if (tl != null)
        ret += " FROM " + tl.getEnString();
    }else{
      if (tl != null)
        ret += " FROM " + tl.getEnString();
    }
    if (cond != null)
      ret += " WHERE " + cond.getEnString();
    if (aggr != null)
      ret += " GROUP BY " + aggr.getEnString();
    if (order != null)
      ret += " ORDER BY " + order.getEnString();
    
    return ret;
  }
  
   /**
    * ��ÿյĿ�ִ��Ӣ��SQL���
    * @param intoTableName INTO�ı���
    * @return ���ؿ�ִ�е�Ӣ��SQL��䣬ֻ�пյı�ṹ
    */
  public String getEmptyExecuteEnString(String intoTableName){
    SelectListModel sl = (SelectListModel) getFirstModelByClass(SelectListModel.class);
    TableListModel tl = (TableListModel) getFirstModelByClass(TableListModel.class);
    String ret = "SELECT " + sl.getEnString();
    if (intoTableName != null && !intoTableName.equals(""))
      ret += " INTO " + intoTableName;
    if (tl != null)
      ret += " FROM " + tl.getEnString();
    ret += " WHERE 1 = 0";
    return ret;
  }
}
