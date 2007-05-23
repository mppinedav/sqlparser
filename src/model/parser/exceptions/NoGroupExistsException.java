package model.parser.exceptions;

import antlr.ANTLRException;

/**
 * SELECT�Ӿ��зǾۺϺ������ʽ
 * û����GROUP BY�Ӿ��г����쳣
 */
public class NoGroupExistsException extends ANTLRException {
	public static final String EXPR_NOT_EXISTS_ERROR = "1";	//�ǾۺϺ������ʽû���ڷ����г��ִ���
	public static final String EXPR_EXISTS_ERROR = "2";			//�ۺϺ����ڷ����г��ִ���

	private String selectExpr;	//select�Ӿ���ֶα��ʽ
	private String exType;			//exception����
	
	public NoGroupExistsException(String selectExpr) {
		this.selectExpr = selectExpr;
		this.exType = EXPR_NOT_EXISTS_ERROR;
	}
	
	public NoGroupExistsException(String selectExpr, String exType) {
		this.selectExpr = selectExpr;
		this.exType = exType;
	}
	
	public String toString() {
		return "�������룺[��ѯ]�Ӿ� \"" + selectExpr + "\" ������[����]�Ӿ��г��֡�";
	}

	public String getSelectExpr() {
		return selectExpr;
	}

	public void setSelectExpr(String selectExpr) {
		this.selectExpr = selectExpr;
	}

	public String getExType() {
		return exType;
	}

	public void setExType(String exType) {
		this.exType = exType;
	}
	
}
