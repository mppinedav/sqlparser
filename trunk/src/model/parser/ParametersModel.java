package model.parser;

/**
 * ����ģ�Ͷ���ParametersModel
 * 
 * �޸���־��
 * ======================================
 * 05/25/2007��
 * 	- ����filter���ԣ�����������в�����ķָ���
 *
 */
public class ParametersModel extends QueryModel {
	private String filter = "";
  public void addParameter(ExpressionModel e) {
    addChild(e);
  }
  
  public void addFilter(String filter){
  	this.filter = filter;
  }
  
  public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getChString() {
		if (filter.equals(""))
			return getChString(", ");
		else
			return getChString(filter);
  }
  
  public String getEnString() {
  	if (filter.equals(""))
  		return getEnString(", ");
  	else
  		return getEnString(filter);
  }
}
