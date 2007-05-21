package model.parser;

import antlr.ANTLRException;

/**
 * SELECT�Ӿ��зǾۺϺ������ʽ
 * û����GROUP BY�Ӿ��г����쳣
 */
public class NoGroupExistsException extends ANTLRException {
	private String selectExpr;

	public NoGroupExistsException(String selectExpr) {
		this.selectExpr = selectExpr;
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

}
