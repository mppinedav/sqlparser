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
  WHERE�Ӿ�Ƭ����֤
  	���÷�����ǰ������ƣ�ֻҪ��setChQuery�ĳ�setChEquation�Ϳ�����
ChWrongMessage�ӿ�˵��
	getLine() ��ô���������
	getColumn() ��ô���������
	getMessage() ��ò������к���Ϣ�Ĵ�����Ϣ
	toString() ��ô����кŵĴ�����Ϣ����ʽ���£�"�У�xx �У�xx xxx����"