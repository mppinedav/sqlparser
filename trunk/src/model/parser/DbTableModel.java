package model.parser;

import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * �������ֶ�������ģ��
 */
public class DbTableModel {
  private Map tablesLi = new LinkedHashMap();	//�������ֶ�����ϢMap���ϣ�����һ�����ı���KEY������Ϣ����TableVO��
  
  //����Ϣ����TableVO
  class TableVO {
    String cnTableName;		//���ı���
    String enTableName;		//Ӣ�ı���
    Map fieldsLi = new LinkedHashMap();	//�ֶ���ϢMap���ϣ�����һ�������ֶ���KEY���ֶ���Ϣ����FieldVO��
  }
  
  //�ֶ���Ϣ����FieldVO
  class FieldVO {
    String chFieldName;		//�����ֶ���
    String enFieldName;		//Ӣ���ֶ���
  }
  
  public Map getTablesLi() {
    return tablesLi;
  }

  public void setTablesLi(Map tablesLi) {
    this.tablesLi = tablesLi;
  }
  
  /**
   * ���ñ���Ϣ��Ӣ����
   * @param cnTableName	������
   * @param enTableName	Ӣ����
   */
  public void setTableEnName(String cnTableName, String enTableName) {
    TableVO _tableVO = new TableVO();
    _tableVO.cnTableName = cnTableName;
    _tableVO.enTableName = enTableName;
    if (!tablesLi.containsKey(cnTableName)){
      tablesLi.put(cnTableName, _tableVO);
    }
  }
  
  /**
   * ��ȡ���ĵı���������
   * @return String[] ���ĵı���������
   */
  public String[] getTablesNameArr(){
  	int i = 0;
  	String[] tablesNameArr = new String[tablesLi.size()];
  	for (Iterator it = tablesLi.keySet().iterator(); it.hasNext();){
  		tablesNameArr[i] = (String) it.next();
  		i++;
  	}
    return tablesNameArr;
  }
  
  /**
   * �������ĵı�����ȡ��Ӧ���ֶ�����������
   * @param tableChName ���ĵı���
   * @return	�ֶ�����������
   */
  public String[] getFieldsNameArrByTableName(String tableChName){
    String[] fieldsNameArr = new String[0];
    
    try {
	    TableVO tableVO = (TableVO) tablesLi.get(tableChName);
	    Map _fieldLi = tableVO.fieldsLi;
	    fieldsNameArr = new String[_fieldLi.size()];
	    int i = 0;
	    for (Iterator it = _fieldLi.keySet().iterator(); it.hasNext();){
	    	fieldsNameArr[i] = (String) it.next();
	    	i++;
	    }
    }catch(Exception ex){}
    
    return fieldsNameArr;
  }
  
  /**
   * ���ñ���Ϣ��Ӣ���ֶ���
   * @param tableChName	���ı���
   * @param fieldChName	�����ֶ���
   * @param fieldEnName	Ӣ���ֶ���
   */
  public void setFieldEnName(String tableChName, String fieldChName, String fieldEnName) {
  	//���tablesLi�в��������ı��������޷���������
  	if (!tablesLi.containsKey(tableChName))
      return;
  	
  	TableVO tableVO = (TableVO) tablesLi.get(tableChName);	//��ȡKEYΪ��Ӧ���ı�����TableVO��Ϣ
  	Map _fieldLi = tableVO.fieldsLi;												//��ȡ�˱�TableVO��Ϣ�µ��ֶ�����
  	
  	if (_fieldLi.containsKey(fieldChName)){	//������ڶ�Ӧ�������ֶ�
  		FieldVO fieldVO = (FieldVO) _fieldLi.get(fieldChName);	//��ȡKEYΪ�������ֶε�FieldVO�ֶζ�����Ϣ
  		fieldVO.chFieldName = fieldChName;											//���������ֶ�
  		fieldVO.enFieldName = fieldEnName;											//����Ӣ���ֶ�
  	}else{
  		FieldVO fieldVO = new FieldVO();
  		fieldVO.chFieldName = fieldChName;											//���������ֶ�
  		fieldVO.enFieldName = fieldEnName;											//����Ӣ���ֶ�
  		_fieldLi.put(fieldChName, fieldVO);											//����Ӧ�ֶ���Ϣ����FieldVO�赽tableVO.fieldsLi��Map��
  	}
  }
  
  /**
   * ���ݱ�����ȡӢ���ֶδ�(f1, f2, ...)
   * @param tableChName ���ı���
   * @return String Ӣ���ֶδ�
   */
  public String getFieldsEnStr(String tableChName){
  	String rValue = "";
  	TableVO tableVO = (TableVO) tablesLi.get(tableChName);	//��ȡKEYΪ��Ӧ���ı�����TableVO��Ϣ
  	if (tableVO != null){	//������ڶ�Ӧ�������ֶ�
  		Map _fieldLi = tableVO.fieldsLi;
  		int i = 0;
  		for (Iterator it = _fieldLi.keySet().iterator(); it.hasNext();){
  			String fieldChName = (String) it.next();								//��ȡ�ֶ���Ϣ�����KEY	�������ֶ�����
  			FieldVO fieldVO = (FieldVO) _fieldLi.get(fieldChName);	//��ȡ�ֶ���Ϣ����FieldVO
  			if (i > 0)
  				rValue += ", ";
	    	rValue += fieldVO.enFieldName;
	    	i++;
	    }
  	}
  	return rValue;
  }
}
