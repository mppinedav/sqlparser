Translator�ӿ�˵��
  ������֤�뷭��	
	// �ȴ������������󣬽���ҳ���ȡ�Ĳ�ѯ�ַ���queryChStr���õ��������н��н���
	Translator translator=new Translator();
	translator.setChQuery(queryChStr);
	
    // ���ڲ�ѯ����г��ֹ���ÿ���������ݿ��л�ȡ��Ӣ�������ֶΣ������õ��������
	DbTable[] tables=translator.getTables();
    for (int j=0; j<tables.length; j++) {
		tables[j].setEnName("table"+j);
		// ��ÿ������ֶ���������������ֶΣ�������û���ڲ�ѯ����г��ֹ�����
		// ����DbTable��addDbField��������Ϊ�������ֶ�
        tables[j].addDbField("�ֶ�1", "field1");
        tables[j].addDbField("�ֶ�2", "field2");
        tables[j].addDbField("�ֶ�3", "field3");
        tables[j].addDbField("�ֶ�4", "field4");
        tables[j].addDbField("�ֶ�5", "field5");
        tables[j].addDbField("�ֶ�6", "field6");
    }
    
    // �ٽ��õ���Ӣ����Ϣ���ֶ���Ϣ�ı�����б����ø�translator
    translator.setTableInfo(tables);
    
    // ����﷨�д����߱������ֶ����д�����ʾ����
	if (translator.hasError()) {
        ChWrongMessage[] msgs=translator.showWrongMsgs();
        for (int j=0; j<msgs.length; j++)
			System.out.println(msgs[j]);
	} else {	// ����õ����ɵ�Ӣ�Ĳ�ѯ���
        QueryModel model=translator.getQueryModel();
        System.out.println(model.getEnQuery());
	}
	DbTable�����е�String getFlag()��setFlag(String flag)������ȡ���������ݿ��־,
	ҵ��ϵͳ���ݲ�ͬ��־���ɲ�ͬ��Ӣ�����ݿ���
	
  WHERE�Ӿ�Ƭ����֤
    ���÷�����ǰ������ƣ�ֻҪ��setChQuery�ĳ�setChEquation�Ϳ�����
    
  �������ݿ�������ݿ�ָ�����
    �ڵ���translator.getQueryModel()�õ�QueryModel�����, ����getXmlString()�����õ���������
    XML�ĵ��ַ���, Ȼ������ַ������浽���ݿ�.
    
    ��ȡ����ʱ���ȴ����ݿ�ȡ�����������XML�ַ���String xml=read from db, Ȼ�����
    QueryModel model=QueryModel.createModelFromXml(xml)��ԭ��ѯmodel
    Ȼ��Ϳ��Ե���model�ķ�����getEnQuery()�õ�����Ӣ�Ĳ�ѯ���; 
    getChQuery()�õ��������Ĳ�ѯ���.
    ����mode��DbTable[] getDbTables()�����õ���ѯ����õ��ı�����б�, 
    ҵ��ϵͳ������Ӣ������������Ժ�ͨ��model��setDbTables(DbTable[] tables)
    �������õ�model����������Ӣ�Ĳ�ѯ���
    
  ��ȡÿ���׶ε�����
    ��ʱֻ��SELECT��ѯ���԰��׶λ�ȡ����, ��ȡ��������:
    SelectModel model=(SelectModel)QueryModel.createModelFromXml(xml)
    ����
    SelectModel model=(SelectModel)translator.getQueryModel()
    Ȼ��������º���:
      model.getChColumnList() �õ�����Select�ֶ��б�Ƭ��
      model.getChTableList() �õ�����From���б�
      model.getChEquation() �õ�����Where���ʽ
      model.getChGroupBy() �õ�����Group by�ֶ��б�
      model.getChOrderBy() �õ�����Order by�ֶ��б�

      model.getEnColumnList() �õ�Ӣ��Select�ֶ��б�Ƭ��
      model.getEnTableList() �õ�Ӣ��From���б�
      model.getEnEquation() �õ�Ӣ��Where���ʽ
      model.getEnGroupBy() �õ�Ӣ��Group by�ֶ��б�
      model.getEnOrderBy() �õ�Ӣ��Order by�ֶ��б�
    
    
ChWrongMessage�ӿ�˵��
	getLine() ��ô���������
	getColumn() ��ô���������
	getMessage() ��ò������к���Ϣ�Ĵ�����Ϣ
	toString() ��ô����кŵĴ�����Ϣ����ʽ���£�"�У�xx �У�xx xxx����"