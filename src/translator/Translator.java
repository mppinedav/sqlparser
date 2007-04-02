package translator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import model.parser.AggregateExprListModel;
import model.parser.AggregateExprModel;
import model.parser.AliasModel;
import model.parser.ChWrongMessage;
import model.parser.ColumnModel;
import model.parser.EquationModel;
import model.parser.ExpressionModel;
import model.parser.FieldModel;
import model.parser.FunctionModel;
import model.parser.NoSuchFieldException;
import model.parser.NoSuchTableException;
import model.parser.OrderAliasModel;
import model.parser.OrderExpressionListModel;
import model.parser.OrderExpressionModel;
import model.parser.ParamModel;
import model.parser.QueryModel;
import model.parser.SearchConditionModel;
import model.parser.SelectListModel;
import model.parser.StringModel;
import model.parser.TableListModel;
import model.parser.TableModel;
import model.parser.TableNotInFromClause;
import model.parser.TableUnionModel;

/**
 * �������뷭�����������ܵ�ʵ����V1.0
 *
 */
public class Translator {
  //ҵ���ùؼ��֡������������ļ�
  private static final String CN_KEY_WORDS = "cnKeyWords";
  
  //���Ĺؼ�������KEY
  public static final String CNKEY_WORDS = "cnKeyWords";
  public static final String CNKEY_FUNC = "cnFunc";
  public static final String CNKEY_OPERSYMBOL = "operSymbol";
  public static final String CNKEY_NUMBERSYMBOL = "numberSymbol";
  
  //Ӣ�Ĺؼ�����������KEY
  public static final String ENVALUE_SELECT = "select";
  public static final String ENVALUE_ALL = "all";
  public static final String ENVALUE_FROM = "from";
  public static final String ENVALUE_WHERE = "where";
  public static final String ENVALUE_GROUPBY = "group_by";
  public static final String ENVALUE_ORDERBY = "order_by";
  
  //Ƭ���Ӿ�ؼ���
  public static final String SELECT = "select";
  public static final String COLUMN = "column";
  public static final String FROM = "from";
  public static final String WHERE = "where";
  
  public static final String CIRCLE_TYPE_TABLE = "1"; //�����ѭ��
  public static final String CIRCLE_TYPE_WHERE = "2"; //��������ѭ��
  
  private QueryModel model;
  private DbTable[] tables;
  private DbTableInfo info = new DbTableInfo();
  private List appDbTableList = new ArrayList();
  private SelectListVO[] selectListVOArr = new SelectListVO[0];
  private FromListVO[] fromListVOArr = new FromListVO[0];
  private WhereListVO[] whereListVOArr = new WhereListVO[0];
  private GroupByListVO[] groupByListVOArr = new GroupByListVO[0];
  private OrderByListVO[] orderByListVOArr = new OrderByListVO[0];
  private List aliasModelList = new ArrayList();
  private List orderAliasModelList = new ArrayList();
  
  /**
   * ���ݹؼ���ֵ��ȡ��Ӧ���Ĺؼ�������
   * @param keyName �ؼ���ֵ
   * @return String ���Ĺؼ�������
   */
  public static String getCnKeyWordByValue(String _mValue){
    String _rValue = "";
    ResourceBundle bundle = ResourceBundle.getBundle(CN_KEY_WORDS, Locale.CHINESE);
    _rValue = bundle.getString(_mValue);
    return _rValue;
  }
  
  /**
   * ���ݹؼ���/����/������/�������KEY��ȡ��Ӧ���Ĺؼ�������
   * @param keyName �ؼ���/����/������/���������KEY
   * @return String ���Ĺؼ�������
   */
  public static String getCnKeyWords(String keyName) {
    String[] cnKeyWordsArr = new String[]{CNKEY_WORDS, CNKEY_FUNC, CNKEY_OPERSYMBOL, CNKEY_NUMBERSYMBOL};
    
    String cnKeyWords = "";
    ResourceBundle bundle = ResourceBundle.getBundle(CN_KEY_WORDS, Locale.CHINESE);
    if (keyName == null || keyName.equals("")){
      for (int i = 0; i < cnKeyWordsArr.length; i++){
        cnKeyWords +=  bundle.getString(cnKeyWordsArr[i]);
        if (i < cnKeyWordsArr.length - 1)
          cnKeyWords += ",";
      }
    }else{
      cnKeyWords = bundle.getString(keyName);
    }
    return cnKeyWords;
  }
  
  /**
   * �������Ĳ�ѯ��䵽����������֤
   */
  public void setChQuery(String chQuery) {
    model = QueryModel.parseQuery(chQuery);
  }
  
  /**
   * ����Ƭ�β�ѯ�Ӿ䲢������֤
   * @param type Ƭ���Ӿ�����
   * @param segment Ƭ���Ӿ�
   * @return QueryModel ������QueryModelģ�Ͷ���
   */
  public void setChSegment(String type, String chSegment) {
    String cnQuery = type + " " + chSegment;
    model =  QueryModel.parseSegment(cnQuery);
  }
  
  /**
   * ��ȡ��ʽ��������Ĳ�ѯ���
   * @return String ��ʽ��������Ĳ�ѯ���
   */
  public String getChQuery() {
    return model.getChString();
  }
  
  /**
   * ��ȡSELECT�Ӿ�Ĳ�ѯ���
   * @return String SELECT�Ӿ�Ĳ�ѯ���
   */
  public String getChSelectStr(){
    return (model.getFirstModelByClass(SelectListModel.class)).getChString();
  }
  
  /**
   * ��ȡFROM�Ӿ�Ĳ�ѯ���
   * @return String FROM�Ӿ�Ĳ�ѯ���
   */
  public String getChFromStr(){
    return (model.getFirstModelByClass(TableListModel.class)).getChString();
  }
  
  /**
   * ��ȡWHERE�Ӿ�Ĳ�ѯ���
   * @return String WHERE�Ӿ�Ĳ�ѯ���
   */
  public String getChWhereStr(){
    return (model.getFirstModelByClass(SearchConditionModel.class)).getChString();
  }
  
  /**
   * ��ȡGROUP BY�Ӿ�Ĳ�ѯ���
   * @return String GROUP BY�Ӿ�Ĳ�ѯ���
   */
  public String getChGroupByStr(){
    return (model.getFirstModelByClass(AggregateExprListModel.class)).getChString();
  }
  
  /**
   * ��ȡORDER BY�Ӿ�Ĳ�ѯ���
   * @return String ORDER BY�Ӿ�Ĳ�ѯ���
   */
  public String getChOrderByStr(){
    return (model.getFirstModelByClass(OrderExpressionListModel.class)).getChString();
  }
  
  /**
   * ��ȡ��������ı����ֶ�������Ϣ��������
   * @return DbTable[] �����ֶ�������Ϣ��������
   */
  public DbTable[] getTables() {
    List tables = new ArrayList();
    QueryModel[] tms = model.getTables();
    for (int i = 0; i < tms.length; i++){
      TableModel tm = (TableModel) tms[i];
      DbTable t = new DbTable(tm);
      tables.add(t);
    }
    QueryModel[] fms = model.getFields();
    for (int i = 0; i < fms.length; i++){
      FieldModel fm = (FieldModel) fms[i];
      boolean tableNotFound = true;
      for (Iterator it = tables.iterator(); it.hasNext();){
        DbTable t = (DbTable) it.next();
        if (fm.getTableName() != null
            && (fm.getTableName().equals(t.getChName()) || fm.getTableName().equals(t.getAlias()))){
          t.addDbField(fm);
          tableNotFound = false;
          break;
        }
      }
      if (tableNotFound)
        model.addException(new TableNotInFromClause(fm.getChString()));
    }

    DbTable[] ret = new DbTable[tables.size()];
    int i = 0;
    for (Iterator it = tables.iterator(); it.hasNext();)
      ret[i++] = (DbTable) it.next();
    this.tables = ret;  //Add
    return ret;
  }
  
  /**
   * ����Translator����������Ԥ��ı����ֶ�������Ϣ����DbTable������������
   * @param t Translator��������
   * @param ts DbTable��������
   */
  public void updateDbTables(Translator t, DbTable[] ts) {
    if (t.getQueryModel() instanceof TableUnionModel){
      AppDbTable[] _appDbTablesArr = t.info.getDbTableInfoToAppTableArr();
      if (_appDbTablesArr.length == ts.length){
        for (int i = 0; i < ts.length; i++) {
          DbTable _dbTable = ts[i];
          String cnTableName = t.getTableEnName(_dbTable.getChName());
          if (cnTableName == null || cnTableName.equals("")){
            model.addException(new NoSuchTableException(_dbTable.getChName()));
          }
          _dbTable.setEnName(t.getTableEnName(_dbTable.getChName()));
          AppDbField[] _appDbFieldsArr = _appDbTablesArr[i].getFields();
          for (int j = 0; j < _appDbFieldsArr.length; j++){
            String cnFieldName = _appDbFieldsArr[j].getChName();
            String enFieldName = _appDbFieldsArr[j].getEnName();
            if (enFieldName == null || enFieldName.equals("")){
              model.addException(new NoSuchFieldException(_dbTable.getChName(), cnFieldName));
            }
            _dbTable.addToDbField(cnFieldName, enFieldName);
          }
        }
      }
    }else{
      for (int i = 0; i < ts.length; i++) {
        DbTable dbt = ts[i];
        String cnTableName = t.getTableEnName(dbt.getChName());
        if (cnTableName == null || cnTableName.equals("")){
          model.addException(new NoSuchTableException(dbt.getChName()));
        }
        dbt.setEnName(cnTableName);
        for (Iterator it = dbt.getFields().iterator(); it.hasNext();) {
          DbField dbf = (DbField)it.next();
          dbf.setTableEnName(dbt.getEnName());
          String cnFieldName = t.getFieldEnName(dbt.getChName(), dbf.getChName());
          if (cnFieldName == null || cnFieldName.equals("")){
            model.addException(new NoSuchFieldException(dbt.getChName(), dbf.getChName()));
          }
          dbf.setEnName(t.getFieldEnName(dbt.getChName(), dbf.getChName()));
        }
      }
    }
  }
  
  /**
   * ��ȡҵ��ϵͳ�б����ֶ���Ϣ
   * @return DbTableInfo ������HashMap���ֶ����ԣ�
   *                     ÿ�������ֶ�����ҲΪHashMap
   */
  public DbTableInfo getInfo() {
    return info;
  }
  
  /**
   * ����ҵ��ϵͳ�б����ֶ���Ϣ
   * @param info DbTableInfo �����ֶ���Ϣ����
   */
  public void setInfo(DbTableInfo info) {
    this.info = info;
  }

  /**
   * ���ӱ�����
   * @param tableChName ���ı���
   * @param tableEnName Ӣ�ı���
   */
  public void addDbTable(String tableChName, String tableEnName) {
    this.getQueryModel().addDbTable(tableChName, tableEnName);
    info.setTableEnName(tableChName, tableEnName);
  }
  
  /**
   * ���ӱ�����
   * @param tableChName ���ı���
   * @param tableEnName Ӣ�ı���
   * @param flag �Ƿ���ʱ���ʶ
   */
  public void addDbTable(String tableChName, String tableEnName, String flag) {
    this.getQueryModel().addDbTable(tableChName, tableEnName);
    info.setTableEnName(tableChName, tableEnName, flag);
  }
  
  /**
   * ���ӱ�����
   * @param tableChName ���ı���
   * @param tableEnName Ӣ�ı���
   * @param flag �Ƿ���ʱ���ʶ
   * @param tableParam ���������
   */
  public void addDbTable(String tableChName, String tableEnName, String flag, String tableParam) {
    if (tableParam != null && !tableParam.equals(""))
      this.getQueryModel().setCircleType(CIRCLE_TYPE_TABLE);  //����Ϊ��ѭ��
    this.getQueryModel().addDbTable(tableChName, tableEnName);
    info.setTableEnName(tableChName, tableEnName, flag, tableParam);
  }
  
  /**
   * ���ӱ��ֶ����Զ���
   * @param tableChName ���ı���
   * @param fieldChName �����ֶ�
   * @param fieldEnName Ӣ���ֶ�
   */
  public void addDbField(String tableChName, String fieldChName, String fieldEnName) {
    this.getQueryModel().addDbField(tableChName, fieldChName, fieldEnName);
    info.setFieldEnName(tableChName, fieldChName, fieldEnName);
  }
  
  /**
   * ���ӱ��ֶ����Զ���
   * @param tableChName ���ı���
   * @param fieldChName �����ֶ�
   * @param fieldEnName Ӣ���ֶ�
   * @param fieldParam ������������
   */
  public void addDbField(String tableChName, String fieldChName, String fieldEnName, String fieldParam) {
    if ((this.getQueryModel().getCircleType() == null || this.getQueryModel().getCircleType().equals("")) &&
        fieldParam != null && !fieldParam.equals("")){
      this.getQueryModel().setCircleType(CIRCLE_TYPE_WHERE);  //����Ϊ����ѭ��
    }
    this.getQueryModel().addDbField(tableChName, fieldChName, fieldEnName);
    info.setFieldEnName(tableChName, fieldChName, fieldEnName, fieldParam);
  }
  
  /**
   * ���ݱ���������ȡ����Ӣ����
   * @param tableChName ��������
   * @return String ����Ӣ����
   */
  public String getTableEnName(String tableChName) {
    return info.getTableEnName(tableChName);
  }
  
  /**
   * ���ݱ����������ֶε���������ȡ�ֶε�Ӣ����
   * @param tableChName ��������
   * @param fieldChName �ֶε�������
   * @return String �ֶε�Ӣ����
   */
  public String getFieldEnName(String tableChName, String fieldChName) {
    return info.getFieldEnName(tableChName, fieldChName);
  }
  
  /**
   * ���ñ����ֶ�������Ϣ��������ģ�͵�DbTable�У���ʱ�޷�ʹ�ã�
   * @param tables ��������DbTable��������
   * @param appTables Ӧ���е�AppDbTable��������
   */
  public void setTableInfoToModel(DbTable[] tables, AppDbTable[] appTables){
    for (int i = 0; i < tables.length; i++){
      int m = -1;
      for (int j = 0; j < appTables.length; j++){
        if (tables[i].getChName().equals(appTables[j].getTableName())){
          m = j;
          break;
        }
      }
      if (m >= 0){
        tables[i].setEnName(appTables[m].getTableEnName());
        AppDbField[] appDbFields = appTables[m].getFields();
        for (int k = 0; k < appDbFields.length; k++){
          tables[i].addDbField(appDbFields[k].getChName(), appDbFields[k].getEnName());
        }
      }
    }
  }
  
  /**
   * ���ñ����ֶ�������Ϣ�������鵽������
   * @param tables �����ֶ�������Ϣ��������
   */
  public void setTableInfo(DbTable[] tables) {
//    this.tables = tables;
    for (int i = 0; i < tables.length; i++){
      DbTable t = tables[i];
      if (t.getEnName() == null)
        model.addException(new NoSuchTableException(t.getChName()));
      for (Iterator it = t.getFields().iterator(); it.hasNext();){
        DbField f = (DbField) it.next();
        if (f.getEnName() == null)
          model.addException(new NoSuchFieldException(t.getChName(), f.getChName()));
      }
    }
  }

  /**
   * ��ȡ����������ı�����QueryModelģ�Ͷ���
   * @return QueryModel ������QueryModelģ�Ͷ���
   */
  public QueryModel getQueryModel() {
    return model;
  }
  
  /**
   * �﷨У���Ƿ��д���
   * @return boolean��true��ʾ�д���,false��ʾ�޴���
   */
  public boolean hasError() {
    return model.hasError();
  }
  
  /**
   * ��ȡ���д�����Ϣ����
   * @return ChWrongMessage[] ������Ϣ��������
   */
  public ChWrongMessage[] showWrongMsgs() {
    return model.getWrongMessages();
  }
  
  /**
   * ��QueryModelģ����Ϣת����XML����
   * @return String XML����
   */
  public String getXmlString() {
    Document d = DocumentHelper.createDocument();
    Element e = d.addElement("query");
    Element chQueryString = e.addElement("ch_query_string");
    chQueryString.addAttribute("circleType", model.getCircleType());
    chQueryString.addText(model.getChString());
    info.getElement(e); //����������Ϣ���ֶ�������Ϣת����XML����
//    for (int i = 0; i < appDbTableList.size(); i++){
//      AppDbTable appDbTable = (AppDbTable) appDbTableList.get(i);
//      appDbTable.getElement(e);
//    }
//    for (int i = 0; i < tables.length; i++)
//      tables[i].getElement(e);
    
    //SelectListVO������Ϣת��XML����
    Element selectListEquElem = e.addElement("selectListEqu");
    for (int i = 0; i < selectListVOArr.length; i++){
      Element selectListElem = selectListEquElem.addElement("SelectListVO");
      selectListElem.addAttribute("fieldDataType", selectListVOArr[i].getFieldDataType());
    }
    
    //WhereListVO������Ϣת��XML����
    Element whereListEquElem = e.addElement("whereListEqu");
    for (int i = 0; i < whereListVOArr.length; i++){
      Element whereListElem = whereListEquElem.addElement("WhereListVO");
      whereListElem.addAttribute("checkedFlag", whereListVOArr[i].getCheckedFlag());
    }
    
    //�ֶ�/�ֶα��ʽ����AliasModel����ת��XML����
    Element aliasListEquElem = e.addElement("aliasListEqu");
    QueryModel[] aliasModelArr = model.getModelsFromAllChildrenByClass(AliasModel.class);
    for (int i = 0; i < aliasModelArr.length; i++){
      Element aliasListElem = aliasListEquElem.addElement("aliasListVO");
      aliasListElem.addAttribute("alias", ((AliasModel)aliasModelArr[i]).getAlias());
      aliasListElem.addAttribute("enAlias", ((AliasModel)aliasModelArr[i]).getEnAlias());
    }
    
    //�������OrderAliasModel����ת��XML����
    Element orderAliasListEquElem = e.addElement("orderAliasListEqu");
    QueryModel[] orderAliasModelArr = model.getModelsFromAllChildrenByClass(OrderAliasModel.class);
    for (int i = 0; i < orderAliasModelArr.length; i++){
      Element orderAliasListElem = orderAliasListEquElem.addElement("orderAliasListVO");
      orderAliasListElem.addAttribute("alias", ((OrderAliasModel)orderAliasModelArr[i]).getAlias());
      orderAliasListElem.addAttribute("enAlias", ((OrderAliasModel)orderAliasModelArr[i]).getEnAlias());
    }
    return d.asXML();
  }
  
  /**
   * ��XML����ת��QueryModel������Ϣ
   * @param xml XML����
   * @return QueryModel QueryModel����
   * @throws DocumentException
   */
  public QueryModel loadModelFromXML(String xml) throws DocumentException {
    Document document = DocumentHelper.parseText(xml);
    Element root = document.getRootElement();
    String query = root.elementText("ch_query_string");
    model = QueryModel.parseQuery(query);
    
//    DbTable[] tables = getTables();
//    for (int i = 0; i < tables.length; i++){
//      for (Iterator it = root.elementIterator("db_info"); it.hasNext();){
//        Element e = (Element) it.next();
//        if (e.attributeValue("ch_name").equals(tables[i].getChName()))
//          tables[i].initTableFromElement(e);
//      }
//    }
//    setTableInfo(tables);
    
    //���Ȼ�ȡQueryModel�е�
    //SelectListVO��FromListVO��WhereListVO��GroupByListVO��OrderByListVO
    getSelectListVOArr();
    getFromListVOArr();
    getWhereListVOArr();
    getGroupByListVOArr();
    getOrderByListVOArr();
    
    appDbTableList = new ArrayList();
    //���XML�����д�����SelectListVO��Ҫ����Ϣ���������
    for (Iterator it = root.elementIterator(); it.hasNext();) {
      Element elem = (Element)it.next();
      if (elem.getName().equals("ch_query_string")){
        //model.setCircleType(elem.attributeValue("circleType"));
      }
      if (elem.getName().equals("db_info")){
        //AppDbTable appDbTable = new AppDbTable();
        //appDbTable.setTableName(elem.attributeValue("ch_name"));
        //appDbTable.setTableEnName(elem.attributeValue("en_name"));
        addDbTable(elem.attributeValue("ch_name"), elem.attributeValue("en_name"),
            elem.attributeValue("flag"), elem.attributeValue("tableParam"));
        for (Iterator it1 = elem.elementIterator(); it1.hasNext();){
          Element e = (Element)it1.next();
          //appDbTable.addDbField(e.attributeValue("ch_name"), e.attributeValue("en_name"));
          addDbField(elem.attributeValue("ch_name"), e.attributeValue("ch_name"), 
              e.attributeValue("en_name"), e.attributeValue("fieldParam"));
        }
        //appDbTableList.add(appDbTable);
      }
      
      if (elem.getName().equals("selectListEqu")){
        int m = 0;
        for (Iterator it1 = elem.elementIterator(); it1.hasNext();){
          Element e = (Element)it1.next();
          selectListVOArr[m].setFieldDataType(e.attributeValue("fieldDataType"));
          m++;
        }
      }
      
      if (elem.getName().equals("whereListEqu")){
        int m = 0;
        for (Iterator it1 = elem.elementIterator(); it1.hasNext();){
          Element e = (Element)it1.next();
          whereListVOArr[m].setCheckedFlag(e.attributeValue("checkedFlag"));
          m++;
        }
      }
      
      //���XML�����д�����aliasModelList��Ҫ�ı�����Ϣ���������
      if (elem.getName().equals("aliasListEqu")){
        for (Iterator it1 = elem.elementIterator(); it1.hasNext();){
          Element e = (Element)it1.next();
          AliasModel aliasModel = new AliasModel(e.attributeValue("alias"));
          aliasModel.setEnAlias(e.attributeValue("enAlias"));
          aliasModelList.add(aliasModel);
        }
      }
      
      //���XML�����д�����orderAliasModelList��Ҫ�ı�����Ϣ���������
      if (elem.getName().equals("orderAliasListEqu")){
        for (Iterator it1 = elem.elementIterator(); it1.hasNext();){
          Element e = (Element)it1.next();
          OrderAliasModel orderAliasModel = new OrderAliasModel(e.attributeValue("alias"));
          orderAliasModel.setEnAlias(e.attributeValue("enAlias"));
          orderAliasModelList.add(orderAliasModel);
        }
      }
    }
    //updateDbTables(this, this.getTables());
    //DbTable[] _dbTablesArr = this.getTables();
    //setTableInfoToModel(_dbTablesArr, appDbTableList);
    //setTableInfo(_dbTablesArr);
    return model;
  }
  
  /**
   * ��ȡSELECT�Ӿ������б��ʽ��������
   * 
   * @return SelectListVO[] SelectListVO��������
   */
  public SelectListVO[] getSelectListVOArrByModel() {
    
    QueryModel[] columnModelArr = model.getModelsFromAllChildrenByClass(ColumnModel.class);
    SelectListVO[] _selectListVOArr = new SelectListVO[columnModelArr.length];
    for (int i = 0; i < columnModelArr.length; i++){
      SelectListVO _selectListVO = new SelectListVO();
      QueryModel[] expressArr = columnModelArr[i].getModelByClass(ExpressionModel.class);
      AliasModel aliasModel = (AliasModel) columnModelArr[i].getFirstModelByClass(AliasModel.class);
      if (expressArr.length > 0)
        _selectListVO.setCnColumnEquElem(expressArr[0].getChString());
      if (aliasModel != null)
        _selectListVO.setCnFieldAlias(aliasModel.getAlias());
      _selectListVOArr[i] = _selectListVO;
    }
    return _selectListVOArr;
  }
  
  
  /**
   * ��ȡFROM�Ӿ������б��ʽ��������
   * @return FromListVO[] FromListVO��������
   */
  public FromListVO[] getFromListVOArrByModel() {
    QueryModel[] _tableModelArr = model.getModelsFromAllChildrenByClass(TableModel.class);
    FromListVO[] _fromListVOArr = new FromListVO[_tableModelArr.length];
    for (int i = 0; i < _tableModelArr.length; i++){
      FromListVO _fromListVO = new FromListVO();
      _fromListVO.setCnTableName(((TableModel)_tableModelArr[i]).getTableName());
      AliasModel aliasModel = (AliasModel) _tableModelArr[i].getFirstModelByClass(AliasModel.class);
      if (aliasModel != null)
        _fromListVO.setCnTAbleAlias(aliasModel.getAlias());
      _fromListVOArr[i] = _fromListVO;
    }
    return _fromListVOArr;
  }
  
  /**
   * ��ȡWHERE�Ӿ������б��ʽ��������
   * @return WhereListVO[] WhereListVO��������
   */
  public WhereListVO[] getWhereListVOArrByModel() {
    QueryModel[] _equationModelArr = model.getModelsFromAllChildrenByClass(EquationModel.class);
    WhereListVO[] _whereListVOArr = new WhereListVO[_equationModelArr.length];
    for (int i = 0; i < _equationModelArr.length; i++){
      WhereListVO _whereListVO = new WhereListVO();
      _whereListVO.setCnAllWhereStr(_equationModelArr[i].getChString());
      QueryModel[] _expressionModelArr = _equationModelArr[i].getModelByClass(ExpressionModel.class);
      QueryModel _stringModel = _equationModelArr[i].getFirstModelByClass(StringModel.class);
      for (int j = 0; j < _expressionModelArr.length; j++){
        _whereListVO.setCnWhereEquElem(_expressionModelArr[0].getChString());
        if (j > 0){
          _whereListVO.setCnWhereValue(_expressionModelArr[1].getChString());
          _whereListVO.setConstant(((ExpressionModel)_expressionModelArr[1]).hasConstant());
        }
      }
      if (_stringModel != null)
        _whereListVO.setCnComparSymbol(_stringModel.getChString());
      
      _whereListVOArr[i] = _whereListVO;
    }
    return _whereListVOArr;
  }
  
  /**
   * ��ȡGROUP BY�Ӿ������б��ʽ��������
   * @return GroupByListVO[] GroupByListVO��������
   */
  public GroupByListVO[] getGroupByListVOArrByModel() {
    QueryModel _aggregateExprListModel = model.getFirstModelByClass(AggregateExprListModel.class);
    if (_aggregateExprListModel == null){
      GroupByListVO[] _groupByListVOArr = new GroupByListVO[0];
      return _groupByListVOArr;
    }
    QueryModel[] _aggregateExprModelArr = _aggregateExprListModel.getModelsFromAllChildrenByClass(AggregateExprModel.class);
    GroupByListVO[] _groupByListVOArr = new GroupByListVO[_aggregateExprModelArr.length];
    for (int i = 0; i < _aggregateExprModelArr.length; i++){
      GroupByListVO _groupByListVO = new GroupByListVO();
      _groupByListVO.setCnGroupByEquElem(_aggregateExprModelArr[i].getChString());
      _groupByListVOArr[i] = _groupByListVO;
    }
    return _groupByListVOArr;
  }
  
  /**
   * ��ȡORDER BY�Ӿ������б��ʽ��������
   * @return OrderByListVO[] OrderByListVO��������
   */
  public OrderByListVO[] getOrderByListVOArrByModel() {
    QueryModel _orderExpressionListModel = model.getFirstModelByClass(OrderExpressionListModel.class);
    if (_orderExpressionListModel == null){
      OrderByListVO[] _orderByListVOArr = new OrderByListVO[0];
      return _orderByListVOArr;
    }
    QueryModel[] _orderExpressionModelArr = _orderExpressionListModel.getModelsFromAllChildrenByClass(OrderExpressionModel.class);
    OrderByListVO[] _orderByListVOArr = new OrderByListVO[_orderExpressionModelArr.length];
    for (int i = 0; i < _orderExpressionModelArr.length; i++){
      OrderByListVO _orderByListVO = new OrderByListVO();
      List orderEquElemli = _orderExpressionModelArr[i].getChildren();
      if (orderEquElemli.size() > 0){
        Object obj = orderEquElemli.get(0);
        if (obj instanceof FunctionModel){
          _orderByListVO.setCnOrerByEquElem(((FunctionModel)obj).getChString());
        }else if (obj instanceof FieldModel){
          _orderByListVO.setCnOrerByEquElem(((FieldModel)obj).getChString());
        }else if (obj instanceof OrderAliasModel){
          _orderByListVO.setCnOrerByEquElem(((OrderAliasModel)obj).getChString());
        }
      }
      _orderByListVO.setCnOrderType(((OrderExpressionModel)_orderExpressionModelArr[i]).getChSort());
      _orderByListVOArr[i] = _orderByListVO;
    }
    return _orderByListVOArr;
  }
  
  /**
   * ��ȡQueryModelģ�͵������ֶ�/�ֶα��ʽ������������
   * @return  AliasModel[] ������������
   */
  public AliasModel[] getAliasModelListVOArrByModel(){
    QueryModel[] aliasModelArr = model.getModelsFromAllChildrenByClass(AliasModel.class);
    AliasModel[] _aliasModelArr = new AliasModel[aliasModelArr.length];
    for (int i = 0; i < aliasModelArr.length; i++){
      AliasModel aliasModel = (AliasModel) aliasModelArr[i];
      _aliasModelArr[i] = aliasModel;
    }
    return _aliasModelArr;
  }
  
  /**
   * ��ȡXML����ת����������ֶ�/�ֶα��ʽ������������
   * @return AliasModel[] ������������
   */
  public AliasModel[] getAliasModelListVOArrByXML(){
    AliasModel[] _aliasModelArr = new AliasModel[aliasModelList.size()];
    for (int i = 0; i < aliasModelList.size(); i++){
      AliasModel aliasModel = (AliasModel) aliasModelList.get(i);
      _aliasModelArr[i] = aliasModel;
    }
    return _aliasModelArr;
  }
  
  /**
   * ����ȡ��XML���ݵ��ֶ�/�ֶα��ʽ���������������õ�QueryModelģ����
   */
  public void setAliasModelListVOArrByXML(){
    AliasModel[] _aliasModelArr1 = getAliasModelListVOArrByModel();
    AliasModel[] _aliasModelArr2 = getAliasModelListVOArrByXML();
    if (_aliasModelArr1.length != _aliasModelArr2.length)
      return;
    for (int i = 0; i < _aliasModelArr1.length; i++){
      _aliasModelArr1[i].setEnAlias(_aliasModelArr2[i].getEnAlias());
    }
  }
  
  /**
   * ��ȡQueryModelģ�͵��������������������
   * @return  AliasModel[] ������������
   */
  public OrderAliasModel[] getOrderAliasModelListVOArrByModel(){
    QueryModel[] aliasModelArr = model.getModelsFromAllChildrenByClass(OrderAliasModel.class);
    OrderAliasModel[] _aliasModelArr = new OrderAliasModel[aliasModelArr.length];
    for (int i = 0; i < aliasModelArr.length; i++){
      OrderAliasModel aliasModel = (OrderAliasModel) aliasModelArr[i];
      _aliasModelArr[i] = aliasModel;
    }
    return _aliasModelArr;
  }
  
  /**
   * ��ȡXML����ת������������������������
   * @return AliasModel[] ������������
   */
  public OrderAliasModel[] getOrderAliasModelListVOArrByXML(){
    OrderAliasModel[] _aliasModelArr = new OrderAliasModel[orderAliasModelList.size()];
    for (int i = 0; i < orderAliasModelList.size(); i++){
      OrderAliasModel aliasModel = (OrderAliasModel) orderAliasModelList.get(i);
      _aliasModelArr[i] = aliasModel;
    }
    return _aliasModelArr;
  }
  
  /**
   * ����ȡ��XML���ݵ�������������������õ�QueryModelģ����
   */
  public void setOrderAliasModelListVOArrByXML(){
    OrderAliasModel[] _aliasModelArr1 = getOrderAliasModelListVOArrByModel();
    OrderAliasModel[] _aliasModelArr2 = getOrderAliasModelListVOArrByXML();
    if (_aliasModelArr1.length != _aliasModelArr2.length)
      return;
    for (int i = 0; i < _aliasModelArr1.length; i++){
      _aliasModelArr1[i].setEnAlias(_aliasModelArr2[i].getEnAlias());
    }
  }
  
  /**
   * ��ȡSELECT�Ӿ��µ��ֶλ��ֶα��ʽ��Ϣ
   * @return SelectListVO[] �ֶλ��ֶα��ʽ��������
   */
  public SelectListVO[] getSelectListVOArr() {
    if (selectListVOArr.length == 0)
      selectListVOArr = getSelectListVOArrByModel();
    return selectListVOArr;
  }
  
  /**
   * ����SELECT�Ӿ���ֶλ��ֶα��ʽ��Ϣ
   * @param selectListVOArr �ֶλ��ֶα��ʽ��Ϣ��������
   */
  public void setSelectListVOArr(SelectListVO[] selectListVOArr) {
    this.selectListVOArr = selectListVOArr;
  }
  
  /**
   * ��ȡFROM�Ӿ��µı�����Ϣ
   * @return FromListVO[] ������Ϣ��������
   */
  public FromListVO[] getFromListVOArr() {
    if (fromListVOArr.length == 0)
      fromListVOArr = getFromListVOArrByModel();
    return fromListVOArr;
  }
  
  /**
   * ����FROM�Ӿ��µı�����Ϣ
   * @param fromListVOArr ������Ϣ��������
   */
  public void setFromListVOArr(FromListVO[] fromListVOArr) {
    this.fromListVOArr = fromListVOArr;
  }
  
  /**
   * ��ȡWHERE�Ӿ��µ��������������ʽ��Ϣ
   * @return WhereListVO[] �������������ʽ��Ϣ��������
   */
  public WhereListVO[] getWhereListVOArr() {
    if (whereListVOArr.length == 0)
      whereListVOArr = getWhereListVOArrByModel();
    return whereListVOArr;
  }
  
  /**
   * ����WHERE�Ӿ��µ��������������ʽ��Ϣ
   * @param whereListVOArr �������������ʽ��Ϣ��������
   */
  public void setWhereListVOArr(WhereListVO[] whereListVOArr) {
    this.whereListVOArr = whereListVOArr;
  }
  
  /**
   * ��ȡWHERE�Ӿ��µķ����ֶλ������ʽ��Ϣ
   * @return GroupByListVO[] �����ֶλ������ʽ��Ϣ��������
   */
  public GroupByListVO[] getGroupByListVOArr() {
    if (groupByListVOArr.length == 0)
      groupByListVOArr = getGroupByListVOArrByModel();
    return groupByListVOArr;
  }
  
  /**
   * ����WHERE�Ӿ��µķ����ֶλ������ʽ��Ϣ
   * @param groupByListVOArr �����ֶλ������ʽ��Ϣ��������
   */
  public void setGroupByListVOArr(GroupByListVO[] groupByListVOArr) {
    this.groupByListVOArr = groupByListVOArr;
  }
  
  /**
   * ��ȡORDER BY�Ӿ��µ������ֶλ�������ʽ��Ϣ
   * @return OrderByListVO[] �����ֶλ�������ʽ��Ϣ��������
   */
  public OrderByListVO[] getOrderByListVOArr() {
    if (orderByListVOArr.length == 0)
      orderByListVOArr = getOrderByListVOArrByModel();
    return orderByListVOArr;
  }
  
  /**
   * ����ORDER BY�Ӿ��µ������ֶλ�������ʽ��Ϣ
   * @param orderByListVOArr �����ֶλ�������ʽ��Ϣ��������
   */
  public void setOrderByListVOArr(OrderByListVO[] orderByListVOArr) {
    this.orderByListVOArr = orderByListVOArr;
  }
  
}
