package model.parser;

/**
 *	�ۺϺ���ģ�Ͷ��� 
 */
public class AggregateFuncModel extends FunctionModel {

	public static final int NO_FILTER = 0;

	public static final int ALL = 1;

	public static final int DISTINCT = 2;

	public AggregateFuncModel(String functionName, int filter) {

		super(functionName);

		setFilter(filter);

	}

}
