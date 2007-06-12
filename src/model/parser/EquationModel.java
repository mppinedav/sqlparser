package model.parser;

/**
 * ������WHERE�Ӿ䵥���������ʽģ�Ͷ�����
 * Recent updates by LORD
 * email: jiandeh@sina.com
 * �޸���־��
 * ======================================================
 * 06/11/2007:
 * 	- ����addSelectStatement�����������洢exists/not exists
 * 		���Ӳ�ѯ����
 * ======================================================
 */
public class EquationModel extends QueryModel {
  public void addExpression(ExpressionModel exp) {
    addChild(exp);
  }
  
  public void addSelectStatement(SelectStatementModel stmt){
  	addChild(stmt);
  }
  
	public String getChString() {
		return super.getChString(" ");
  }
  
  public String getEnString() {
    String rValue = "";
    QueryModel[] exprs = getModelByClass(ExpressionModel.class);
    if (exprs.length == 3){
      rValue = exprs[0].getEnQuery() + " between " + 
        exprs[1].getEnQuery() + " and " + exprs[2].getEnQuery();
    }else{
      rValue = super.getEnString(" ");
    }
    return rValue;
  }
}
