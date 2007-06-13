package model.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.parser.common.Constants;

/**
 * ����������ģ�Ͷ�����
 * Recent updates by LORD
 * email: jiandeh@sina.com
 * �޸���־��
 * ======================================================
 * 06/11/2007:
 * 	- ����isNothing���ԣ������ж��Ƿ�Ϊ�����κζ����ĺ���
 * 	- �޸�����ӦgetChString��getEnString����	
 * 06/13/2007:
 * 	- �޸�FunctionModel�����Ժ���Ӣ�Ļ�Ϲؼ��ֵ�ת����
 *    �磺��ASCII�룬�������κ�һ������ʱ����ԭ���뺯����
 * ======================================================
 */
public class FunctionModel extends QueryModel {
  public static final int ALL = 1;
  public static final int DISTINCT = 2;
  
  private String functionName;
  private boolean isNothing = false;
  private int filter;

  public FunctionModel(String functionName) {
    this.functionName = functionName;
  }
  
  public FunctionModel(String functionName, boolean isEn2Ch) {
  	String rValue = functionName.toLowerCase();
  	if (isEn2Ch){
  		int chCount = 0;
			String regEx = "[\\u4e00-\\u9fa5]";
			Pattern _pattern = Pattern.compile(regEx);
			Matcher _matcher = _pattern.matcher(rValue);
			while (_matcher.find()) {
				for (int i = 0; i <= _matcher.groupCount(); i++) {
					chCount++;	//��������������������1
					break;
				}
				
				if (chCount > 0){
					break;
				}
			}
			if (chCount == 0)	//���ΪӢ�ĺ���������ת�����ĺ�����
				rValue = QueryModel.translateStringEn2Ch(rValue);
			else	//�������κ�һ������ʱ����ԭ���뺯����
				rValue = functionName;
  	}
    this.functionName = rValue;
  }
  
  public void setParameters(ParametersModel model) {
    addChild(model);
  }

  protected void setFilter(int filter) {
    this.filter = filter;
  }
  
  public void setNothing(boolean isNothing) {
		this.isNothing = isNothing;
	}

	public String getChString() {
  	String rValue = "";
    String f = "";
    if (filter == ALL)
      f = Constants.ALL_CN + " ";
    else if (filter == DISTINCT)
      f = Constants.DISTINCT_CN + " ";
    ParametersModel para=(ParametersModel)getFirstModelByClass(ParametersModel.class);
    if (para != null)
    	rValue = functionName + "(" + f + para.getChString() + ")";
    else if (isNothing)
    	rValue = functionName;
    else
    	rValue = functionName + "(" + ")";
    return rValue;
  }
  
  public String getEnString() {
  	String rValue = "";
    String f = "";
    if (filter == ALL)
      f = Constants.ALL_EN + " ";
    else if (filter == DISTINCT)
      f = Constants.DISTINCT_EN + " ";
    ParametersModel para=(ParametersModel)getFirstModelByClass(ParametersModel.class);
    if (para != null)
    	rValue = translateStringCh2En(functionName) + "(" + f + para.getEnString() + ")";
    else if (isNothing)
    	rValue = translateStringCh2En(functionName);
    else
    	rValue = translateStringCh2En(functionName) + "(" + f + ")";
    return rValue;
  }
}
