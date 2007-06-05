package test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import model.parser.ChWrongMessage;
import model.parser.ExprElemModel;
import model.parser.TargetExprModel;

public class ExprMain {
  public static void main(String[] args) {
  	
    String[] exprStrArr = new String[]{
  			"-1 + 1 �� (��Բ����)", 
  			"-1 + 1 �� PI",
  			
  			"-1 + 1 �� ����Ȼ��������", 
  			"-1 + 1 �� E",
  			
  			"-1 + 900 �� (ȡ����ֵ(-901) + (-900))",
  			"-1 + 900 �� (abs(-901) + (-900))",
  			
  			"������ֵ(-9)",
  			"1 + (6/(acos(0.34)+2))",
  			
  			"4 * 900 �� 90 + (7/(������ֵ(0.24)+2) + 4)",
  			"4 * 900 �� 90 + (7/(asin(0.24)+2) + 4)",
  			
  			"4 * 20 �� 90 + ������ֵ(0.876) + 4)",
  			"4 * 20 �� 90 + atan(0.876) + 4)",
  			
  			"-78 �� 90 + ��������ķ�����ֵ(0.876, 0.998) + 4)",
  			"-78 �� 90 + atan2(0.876, 0.998) + 4)",
  			
  			"-78 �� 90 + ȡ��������(168.12) * 2",
  			"-78 �� 90 + ceil(168.12) * 2",
  			
  			"900 - 123 + ������ֵ(30)*200",
  			"900 - 123 + cos(30)*200",
    		
				"900/123 + ����ֵ(5)",
				"900 - 123 + exp(9)",
				
				"90 - 78 + ȡ��������(-21.23)",
				"90 - 78 + floor(-21.23)",
				
				"(90 - 78)/2 + ����Ȼ����(4)",
				"90 - 78 + log(4)",
				
				"(90 - 78)/2 + �����ֵ(78.45, 80.5)",
				"(90 - 78)/2 + max(78.45, 80.5)",
				
				"-60/2 + ����Сֵ(30.5, 80.5)",
				"-60/2 + min(F, 80.5)",
				
				"����(3, 3)",
				"-60/2 + pow(F, G)",
				
				"-5 + ����(5, 3)",
				"-5 + IEEEremainder(5, 3)",
				
				"100 * ȡ�����()",
				"100 * random()",
				
				"100 + ȡ��(90.87)",
				"100 + rint(90.17)",
				
    			"100 + ��������(90.87)",
    			"100 + round(90.87)",
    			
    			"87 - 90 + ������ֵ(30)",
    			"87 - 90 + sin(30)",
    			
    			"87 - 90 + ��ƽ����(16)",
    			"87 - 90 + sqrt(4)",
    			
    			"87 - 90 + ������ֵ(90)",
    			"87 - 90 + tan(45)",
    			
    			"90 + ����ת����(0.523)",
    			"90 + toDegrees(0.345)",
    			
    			"90 + ����ת����(30)",
    			"90 + toRadians(30)"
  		};
    
    double rV = java.lang.Math.acos(-9);
    System.out.println(rV);
    
    TargetExprModel targetExprModel = new TargetExprModel();
    for (int i = 0;  i < exprStrArr.length; i++){
    	System.out.println("ָ�깫ʽ����" + (i + 1) + "��");
    	//��ʽ��֤
	    targetExprModel = targetExprModel.parseTargetExpr(exprStrArr[i]);
	    if (targetExprModel.hasError()){
	      ChWrongMessage[] msgs = targetExprModel.getWrongMessages();
	      for (int j = 0; j < msgs.length; j++){
	        System.out.println("�����Դ���" + msgs[j]);
	      }
	    }else{
	    	System.out.println(targetExprModel.getExprContent());
	    	
	    	//������ֵ
	    	Map elemMap = targetExprModel.getExprElemMap();
	      int m = 0;
	      for (Iterator it = elemMap.keySet().iterator(); it.hasNext();){
	        String elemName = (String) it.next();
	        ExprElemModel exprElemModel = (ExprElemModel) elemMap.get(elemName);
	        if (!exprElemModel.isConstant()){
	          exprElemModel.setElemValue(String.valueOf(m));
	          System.out.println(elemName + " Value is: " + m);
	        }
	        m++;
	      }
	    	
	      //��ʽ����
	      BigDecimal rValue = targetExprModel.ExcuteTargetExpr(targetExprModel);
	      if (targetExprModel.hasError()){
	        ChWrongMessage[] msgs = targetExprModel.getWrongMessages();
	        for (int j = 0; j < msgs.length; j++){
	          System.out.println("�����Դ���" + msgs[j]);
	        }
	      }else{
	      	System.out.println("�����" + rValue.doubleValue() + "\n");
	      }
	    }
    }
  }
}