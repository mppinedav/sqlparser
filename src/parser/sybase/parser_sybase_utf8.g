/*==========================================================
//	LongtopParser Of Sybase
// 	Sybase 12.5.3/Sybase IQ 12.6 SQL Grammar
//
//	http://www.longtop.com
//
//  Recent updates by jiandeh@sina.com
//
//	修改日志:
//	========================================================
//	05/01/2007:
//		- 修改了WHERE条件多个逻辑关系AND/OR	之间括号的嵌套问题
//		- 修改了整个WHERE条件逻辑非(NOT)的问题
//	05/18/2007：
//		- 修改了逻辑非整个条件，用SEARCH_NOT_CONDITION TOKEN
//		- 修改了IS NULL/IS NOT NULL; NOT EXISTS;
//        NOT LIKE; IN/NOT IN TOKEN及语法树遍历，可以用验证英文
//		- 增加FUNCTION_EMPTY_PARAM TOKEN，可以对空函数验证
//		- 增加FUNCTION_COUNT TOKEN，允许对聚合函数COUNT(*)验证
//		- 增加左连接(*=)语法验证
//	05/19/2007:
//		- 增加了日期date-part保留字的处理，可以使用dateadd(day,
//		  10, getdate())函数及day保留字
//		- 增加empty_function、star_function，用来对getdate();
//		  pi(*)、now(*)、today(*)的验证
//	05/22/2007：
//		- 修改了SELECT子句非聚合函数表达式必须在GROUP BY出现问题
//	05/25/2007：
//		- 修改了field_name定义，允许使用%及()结尾的表名/字段名
//		  如： AI_94传票对照表.利率(百分比%)
//		- 修改了数据类型函数convert、cast无法使用数据类型保留字的
//		  问题,增加data_type_word保留字及DATA_TYPE_STRING词法
//		  convert使用FUNCTION_DATA_TYPE TOKEN
//		  cast使用FUNCTION_AS_DATA_TYPE TOKEN
//	05/29/2007:
//		- expression、expression_with_aggr_func语法定义将括号
//		  ()判断提前，可对如"(t1.f1 加 t1.f2) 乘 t1.f3"语句遍历
//		- 按照最新要求对部分函数的中文名进行了修改
//		- caseSensitiveLiterals设为false,大小写不敏感
//	06/01/2007：
//		- 增加对TOP N的验证及语法树遍历,增加topNum属性记录前几条
//==========================================================*/

header {
	package parser.sybase;
}

/*==========================================================
//
//					Parser Define
//
//==========================================================*/
class SybaseIQ12Parser extends Parser;

options {
	k=5;
	buildAST = true;
	defaultErrorHandler = false;
}

tokens {
	SELECT_STATEMENT;
	SELECT_TOKEN;
	SEARCH_NOT_CONDITION;	//非整个条件 TOKEN
	SUBQUERY;				//子查询 TOKEN
	GROUP_BY;				//GROUP BY TOKEN
	ORDER_BY;				//ORDER BY TOKEN
	ALIAS_EQU;				//别名 TOKEN
	
	FUNCTION;				//函数 TOKEN
	FUNCTION_EMPTY_PARAM;	//空参数函数 TOKEN [getdate()]
	FUNCTION_STAR_PARAM;	//参数为*函数 TOKEN [now(*);today(*)]
	FUNCTION_STAR_COUNT;	//函数COUNT(*) TOKEN
	
	FUNCTION_DATA_TYPE;		//带数据类型的函数 TOKEN
	FUNCTION_AS_DATA_TYPE;	//带AS及数据类型的函数 TOKEN

	LOGIC_OP;				//逻辑操作符TOKEN
	LOGICAL_NULL;			//逻辑IS NULL TOKEN
	LOGICAL_NOT_NULL;		//逻辑IS NOT NULL TOKEN
	LOGICAL_IN;				//逻辑IN TOKEN
	LOGICAL_NOT_IN;			//逻辑NOT IN TOKEN
	LOGICAL_LIKE;			//逻辑LIKE TOKEN
	LOGICAL_NOT_LIKE;		//逻辑LIKE TOKEN
	LOGICAL_EXISTS;			//逻辑EXISTS TOKEN
	LOGICAL_NOT_EXISTS;		//逻辑NOT EXISTS TOKEN
	LOGICAL_BETWEEN;		//逻辑BETWEEN AND TOKEN
	
	SUBCONTAIN_OP;			//关系IN/NOT IN TOKEN
	ALL_FIELDS;				//字段所有(*) TOKEN
	PAREN_FIELD;			//带括号的字段[利率(百分比%)] TOKEN
	PAREN_DATA_TYPE;		//带括号的数据类型 TOKEN
	PAREN_CHAR_DATA_TYPE;	//带括号的保留字char数据类型 TOKEN
	LOGIC_BLOCK;			//WHERE条件逻辑块 TOKEN
}

segment
	:	(COLUMN^ column
	|	WHERE^ search_condition)
	EOF!
	;

statements
	:	statement (SEMI^ statement)* EOF!;

statement
	:	tableUnion
	|	tableCompare
	|	select_statement
	{#statement=#([SELECT_STATEMENT], #statement);}
	;

tableUnion
	:	("t_union"^|"表合并"^) table_lists
	;

table_lists
	:	table_name (COMMA^ table_name)+
	;

tableCompare
	:	("t_compare"^|"表比较"^) table_name COMMA! table_name ("where"!|"条件"!) compare_method search_condition
	;

compare_method
	:	comparemethod_name
	|	"not" "exists"
		{#compare_method = #([LOGICAL_NOT_EXISTS, "logic_not_exists"], compare_method);}
	;

select_statement
	:	
		//CUSTOM SQL Sentence
		("select"^ | "查询"^) ("distinct"^ | "唯一"^)? (("top"^ | "前N条"^) integer)? select_list
		(("from"^ | "来自"^) table_list)?
		(("where"^ | "条件"^) search_condition)?
		(("group"^ "by"!|"分组"^) aggregate_expression_list)?
		(("order"^ "by"!|"排序"^) order_expression_list)?
	;

integer
	: REAL_NUM
	;

select_list
	:	column (COMMA^ column)*
	;

table_list
	:	table_name (COMMA^ table_name)*
	;

//search_condition
//	:	equation
//		(logic_op search_condition {#search_condition=#([LOGIC_OP, "logic_op"], #search_condition);})?
//	;

search_condition
	:	bool_exp
	|	( "not"
		{#search_condition = #([SEARCH_NOT_CONDITION, "search_not_condition"], search_condition);}
		| "非"^
		) search_condition
	;

bool_exp
	:	bool_term 
		(("and"^ | "or"^ | "并且"^ | "或者"^) bool_term)*
	;

bool_term
	:	(LPAREN bool_exp RPAREN) => LPAREN! exp:bool_exp RPAREN!
	{#bool_term=#([LOGIC_BLOCK, "log_block"], bool_term);}
	|	equation
	;

aggregate_expression_list
	:	aggregate_expr (COMMA^ aggregate_expr)*
	;

order_expression_list
	:	order_expression (COMMA^ order_expression)*
	;

column
	:	expression_with_aggr_func (("as"^|"作为"^) alias)?
	|	alias ("="|"等于")! expression_with_aggr_func {#column=#([ALIAS_EQU, "="], #column);}
	|	all:"所有" {#column=#([ALL_FIELDS, all.getText()]);}
	|	STAR {#column=#([ALL_FIELDS, "*"]);}
	;

aggregate_expr
	:	LPAREN aggregate_expr RPAREN
	|	(field_name|function|constant) (
		two_arg_op aggregate_expr {#aggregate_expr=#([TWO_ARG_OP, "two_arg_op"], #aggregate_expr);})?
	;

order_expression
	:	(alias|field_name|aggregate_func|function) ("升序"^|"降序"^|"asc"^|"desc"^)?
	;

expression
	:	LPAREN expression RPAREN
		(two_arg_op expression {#expression=#([TWO_ARG_OP, "two_arg_op"], #expression);})?
	|	one_arg_op expression {#expression=#([ONE_ARG_OP, "one_arg_op"], #expression);}
	|	(field_name|constant|function|param_equ)
		(two_arg_op expression {#expression=#([TWO_ARG_OP, "two_arg_op"], #expression);})?
	;

expression_with_aggr_func
	:	
		LPAREN expression_with_aggr_func RPAREN 
		(two_arg_op expression_with_aggr_func 
		{#expression_with_aggr_func=#([TWO_ARG_OP, "two_arg_op"], #expression_with_aggr_func);})?
	|	one_arg_op expression_with_aggr_func 
		{#expression_with_aggr_func=#([ONE_ARG_OP, "one_arg_op"], #expression_with_aggr_func);}
	|	(field_name|constant|function|aggregate_func) 
		(two_arg_op expression_with_aggr_func 
		{#expression_with_aggr_func=#([TWO_ARG_OP, "two_arg_op"], #expression_with_aggr_func);})?
	;

equation
	:	expression (
		
		//关系运算符(+ - * /) 表达式
		("=" | compare_op) expression
	  	{#equation=#([COMPARE_OP, "comp_op"], #equation);}
	  	
		//关系运算符NOT LIKE 表达式
	|	("not" "like") expression
		{#equation=#([LOGICAL_NOT_LIKE, "logic_not_like"], #equation);}	

		//关系运算符IS NULL/IS NOT NULL
	|	( "is" "null"
		  {#equation = #([LOGICAL_NULL, "logic_null"], #equation);}
		| "is" "not" "null"
		  {#equation = #([LOGICAL_NOT_NULL, "logic_not_null"], #equation);}
		| "为空"^ | "非空"^
		)
	
		//关系运算符BETWEEN AND
	| 	("between"^ | "范围"^) expression ("and"!)? expression
	
		//关系运算符IN/NOT IN
	|	( "in"
		  {#equation = #([LOGICAL_IN, "logic_in"], #equation);}
		| "not" "in"
		  {#equation = #([LOGICAL_NOT_IN, "logic_not_in"], #equation);}
		| "在于"^ | "不在于"^
		) exp_set
	)
	;

function
	:	empty_function LPAREN! RPAREN!
	{#function = #([FUNCTION_EMPTY_PARAM, "function_empty_param"], #function);}
	|	star_function LPAREN! STAR! RPAREN!
	{#function = #([FUNCTION_STAR_PARAM, "function_star_param"], #function);}
	|	datatype_function LPAREN! data_type_parameter RPAREN!
	{#function = #([FUNCTION_DATA_TYPE, "function_data_type"], #function);}
	|	asdatatype_function LPAREN! as_data_type_parameter RPAREN!
	{#function = #([FUNCTION_AS_DATA_TYPE, "function_as_data_type"], #function);}
	|	function_name LPAREN! parameters RPAREN!
	{#function = #([FUNCTION, "function_block"], #function);}
	;

aggregate_func
	:	("求记录总数" | "count") LPAREN! STAR! RPAREN!
		{#aggregate_func = #([FUNCTION_STAR_COUNT, "function_star_count"], #aggregate_func);}
	|	aggregate_func_name LPAREN! ("all"^ | "全部"^ | "distinct"^ |"唯一"^)? parameters RPAREN!
	;

parameters
	:	expression (COMMA^ expression)*
	;

//==========数据类型参数 BEGIN==========//
as_data_type_parameter
	: expression ("as"! | "为"!) (datatype_constant)
	;

data_type_parameter
	:	datatype_constant (COMMA^ expression)+
	;

datatype_constant
	:	//"character" "varying"
		data_type_word
	|	"char"
	|	"char" LPAREN! datatype_precision_or_scale_or_maxlength RPAREN!
		{#datatype_constant = #([PAREN_CHAR_DATA_TYPE, "paren_char_data_type"], #datatype_constant);}
	|	DATA_TYPE_STRING LPAREN! datatype_precision_or_scale_or_maxlength RPAREN!
		{#datatype_constant = #([PAREN_DATA_TYPE, "paren_data_type"], #datatype_constant);}
	|	DATA_TYPE_STRING
	;

datatype_precision_or_scale_or_maxlength
	:	REAL_NUM COMMA^ REAL_NUM
	|	REAL_NUM
	;

//==========数据类型参数  END===========//


table_name
	:	ID (("as"^|"作为"^) alias)?
	;

exp_set
	: 	LPAREN constexpset RPAREN
	{#exp_set = #([SUBCONTAIN_OP, "subcontain_op"], #exp_set);}
//	| subquery
	;

constexpset
	:	constant (COMMA^ constant)*
	;

subquery
	:	LPAREN select_statement RPAREN
		{#subquery = #([SUBQUERY, "subquery"], #subquery);}
	;

param_equ
	:	PARAM_ID
	;

alias
	:	ID | QUOTED_STRING;



field_name
	:	ID POINT^ sfield_name
	;	
//field_name
//	:	ID POINT^ ID
//	;

sfield_name
	:	//如：利率(百分比%)
		ID LPAREN! ID RPAREN!
		{#sfield_name = #([PAREN_FIELD, "paren_field"], #sfield_name);}
	|	ID
	;

constant
	:	REAL_NUM
	|	NEGATIVE_DIGIT_ELEMENT
	|	QUOTED_STRING
	|	date_key_word
	|	"null"
	;

//=======================================//
//聚合函数
aggregate_func_name
	:	"avg" 		| 	"求平均数"
	|	"count" 	| 	"求记录总数"
	|	"max" 		| 	"求最大值"
	|	"min" 		| 	"求最小值"
	|	"stddev" 	| 	"求方差"
	|	"sum" 		|	"求总和"
	|	"variance" 	| 	"求统计方差"
	;

//function_name
//	:	"sqrt" 		| 	"求平方根"
//	|	"getdate" 	| 	"求当前日期时间"
//	|	"abs" 		| 	"取绝对值"
//	|	"acos"		|	"求余弦值"
//	|	"substring" | 	"字符串截取"
//	|	"round"		|	"格式化数值"
//	|	"right" 	| 	"字符串右截"
//	|	"ltrim"		|	"去掉左空格"
//	|	"rtrim"		|	"去掉右空格"
//	|	"char_length" | "求字符串的长度"
//	|	"floor"		|	"求四舍后的整数"
//	|	"ceiling"	|	"求五入后的整数"
//	|	"lower" 	| 	"将字符串转为小写"
//	|	"charindex"	|	"存在于"
//	|	"str" 		| 	"数值转字符串"
//	;

//空参数函数
empty_function
	: 	"getdate"	| 	"取当前日期时间2"
	|	"rand"		|	"取随机数"
	;

//参数为*函数、
star_function
	:  	"pi"	|	"求圆周率"
	|	"now"	|	"取当前日期时间1"
	|	"today"	|	"求当前日期"
	;

//带数据类型函数
datatype_function
	:	"convert"	|	"数据类型转换"
	;

//带数据类型函数
asdatatype_function
	:	"cast"		|	"数据类型转化"
	;

//普通函数(数学函数、字符串函数、日期时间函数、系统函数、数据类型转化函数、其他函数)
function_name
	:
	|	number_function
	|	string_function
	|	datetime_function
	|	conversion_function
	|	system_function
	|	other_function
	;

//数学函数
number_function
	:	"abs" 		| 	"取绝对值"
	|	"acos"		|	"求反余弦值"
	|	"asin"		|	"求反正弦值"
	|	"atan"		|	"求反正切值"
	|	"atin2"		|	"求二个数的反正切值"
	|	"ceiling"	|	"取上限整数"
	|	"cos"		|	"求余弦值"
	|	"cot"		|	"求余切值"
	|	"degrees"	|	"弧度转度数"
	|	"exp"		|	"求幂值"
	|	"floor"		|	"取下限整数"
	|	"log"		|	"求自然对数"
	|	"log10"		|	"求10为底的对数"
	|	"mod"		|	"求模"
//	|	"pi"		|	"求圆周率"
	|	"power"		|	"求幂"
	|	"radians"	|	"度数转弧度"
	|	"rand"		|	"取随机数"
	|	"remainder"	|	"求余"
	|	"round"		|	"格式化数值"
	|	"sign"		|	"求值的符号"
	|	"sin"		|	"求正弦值"
	|	"sqrt" 		| 	"求平方根"
	|	"tan"		|	"求正切值"
	|	"格式化数值3"	//"\"truncate\""
	|	"truncnum"	|	"N位置零处理"
	;

//字符串函数
string_function
	:	"ascii"			|	"求ASCII码"
	|	"bit_length"	|	"求字符串的二进制长度"
	|	"byte_length" 	| 	"求字符串的字节数"
	|	"char"			|	"求等值的字符"
	|	"char_length" 	| 	"求字符串长度1"
	|	"charindex" 	|	"存在于"
	|	"difference" 	|	"求两个串的声音差值"
	|	"insertstr"		|	"插入字符串"
	|	"lcase"			|	"转为小写字母1"
	|	"left"			|	"左截字符串"
	|	"length"		|	"求字符串长度2"
	|	"locate"		|	"求串位置1"
	|	"lower" 		| 	"转为小写字母2"
	|	"ltrim"			|	"去掉左空格"
	|	"octet_length"	|	"求字符串的存储长度"
	|	"patindex"		|	"求串位置2"
	|	"repeat"		|	"字符串循环连接1"
	|	"replace"		|	"替换字符串"
	|	"replicate"		|	"字符串循环连接2"
	|	"right"			|	"右截字符串"
	|	"rtrim"			|	"去掉右空格"
	|	"similar"		|	"求字符串相似度"
	|	"sortkey"		|	"字符串排序"
	|	"soundex"		|	"求字符串声音值"
	|	"space"			|	"填空格"
	|	"str"			|	"数值转字符串"
	|	"string"		|	"字符串合并"
	|	"stuff"			|	"字符串删除替换"
	|	"substring"		|	"字符串截取"
	|	"trim"			|	"去左右空格"
	|	"ucase"			|	"转为大写字母1"
	|	"upper"			|	"转为大写字母2"
	;

//日期时间函数
datetime_function
	:	"dateformat" 	|	"格式化日期"
	|	"datename"		|	"求日期分量英文名"
	|	"datepart"		|	"求日期的分量值"
	|	"datetime"		|	"转为日期时间"
	|	"date"			|	"转为日期"
	|	"dayname"		|	"求星期英文名"
	|	"days"			|	"求天数"
	|	"day"			|	"求具体日期"
	|	"dow"			|	"求具体星期"
	|	"hours"			|	"求小时数"
	|	"hour"			|	"求具体小时"
	|	"minutes"		|	"求分钟数"
	|	"minute"		|	"求具体分钟"
	|	"monthname"		|	"求月份英文名"
	|	"months"		|	"求月数"
	|	"month"			|	"求具体月数"
//	|	"now"			|	"取当前日期时间1"
	|	"quarter"		|	"求具体季度"
	|	"seconds"		|	"求秒数"
	|	"second"		|	"求具体秒"
//	|	"today"			|	"求当前日期"
	|	"weeks"			|	"求周数"
	|	"years"			|	"求年数"
	|	"year"			|	"求具体年份"
	|	"ymd"			|	"数值转日期"
//	|	"getdate"		|	"取当前日期时间2"
	|	"dateadd"		|	"日期运算"
	|	"datediff"		|	"求两日期差值"
	;

//数据类型转化函数
conversion_function
	:	"hextoint"	|	"十六进制转为整数"
	|	"inttohex"	|	"整数转为十六进制"
	|	"isdate"	|	"是否日期型"
	|	"isnumeric"	|	"是否数值型"
//	|	"cast"		|	"数据类型转化"
//	|	"convert"	|	"数据类型转换"
	;

//系统函数
system_function
	:	"suser_id"
	|	"suser_name"
	|	"user_id"
	|	"user_name"
	;

//其他函数
other_function
	:	"argn"
	| 	"rowid"
	;

one_arg_op
	:	ONE_ARG_OP;

two_arg_op
	:	TWO_ARG_OP | STAR | MINUS
	|	"与" | "或" | "异或" | "加" | "减" | "乘" | "除" | "求模";

compare_op
	:	COMPARE_OP
	|	"等于" | "大于等于" | "小于等于" | "大于" | "小于" | "不等于"
	|	"包含" | "不包含" | "like"
	|	"左连接"	| LEFT_JOIN
	;

logic_op
	:	"and" | "or" | "并且" | "或者";

comparemethod_name
	:	"exists" | "存在" | "不存在";

//日期date-part保留字
date_key_word
	: "year" | "yy" | "month" | "mm" | "day" | "dd"
	| "quarter" | "qq" | "week" | "wk" | "dayofyear" | "dy"
	| "weekday" | "dw" | "hour" | "hh" | "minute" | "mi" | "second" | "ss" | "millisecond" | "ms"
	| "calweekofyear" | "cwk" | "calyearofweek" | "cyr" | "caldayofweek" | "cdw"
	;

//数据类型保留字
data_type_word
	: "uniqueidentifierstr" 
	| "bigint" | "int" | "integer" | "smallint" | "tinyint" | "double" | "real"
	| "date" | "datetime" | "smalldatetime" | "time" | "timestamp"
	| "bit"
	;

/*==========================================================
//
//					   Lexer Define
//
//==========================================================*/
class SybaseIQ12Lexer extends Lexer;

options {
	k=5;
	charVocabulary = '\u0000' .. '\uFFFE';
	testLiterals=false;
	caseSensitive = false;
	caseSensitiveLiterals = false;
}


ONE_ARG_OP
	:	'~';

//TWO_ARG_OP
//	:	'&' | '|' | '^' | '+' | '/' | '%';

TWO_ARG_OP
	:	'&' | '|' | '^' | '+' | '/';

MINUS 
	: 	'-' ;

STAR
	:	'*';

COMPARE_OP
	:	'>' | '<' | ">=" | "<=" | "!=" | "<>" | "=";
LEFT_JOIN
	: "*=";

COMMA
	:	',';

SEMI:	';';

POINT
	:	'.';

LPAREN
	:	'(';

RPAREN
	:	')';

PARAM_LPAREN
	:	'{';

PARAM_RPAREN
	:	'}';

COLUMN
	:	"seg_column";
FROM
	:	"seg_from";	
WHERE
	:	"seg_where";


WS	:	(' '|'\n'|'\r'|'\t')+ {$setType(Token.SKIP);}
    ;


QUOTED_STRING
	:	('"'|'\'') (ESC|~('\''|'"'|'\\'|'\n'|'\r'))* ('"'|'\'')
	;

protected
ESC
	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	'0'..'3'
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	'0'..'7'
				(
					options {
						warnWhenFollowAmbig = false;
					}
				:	'0'..'7'
				)?
			)?
		|	'4'..'7'
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	'0'..'7'
			)?
		)
	;

PARAM_ID
	: PARAM_LPAREN ID PARAM_RPAREN
	;

ID	options {testLiterals=true;}
	:	ID_START_LETTER ( ID_LETTER )*
	;
	
protected
ID_START_LETTER
    :    'a'..'z'
    |	'_'
    |    '\u0080'..'\ufffe'
    ;
protected
ID_LETTER
    :	ID_START_LETTER
    |	'0'..'9'
    |	'/'
    |	'%'
    ;

REAL_NUM
	:	NUM (POINT DOT_NUM)?
	;

//negative digit element
NEGATIVE_DIGIT_ELEMENT
	: 	MINUS NUM (POINT DOT_NUM)?
	;
	
protected
NUM	:	'0'
	|	NUM_START (NUM_LETTER)*
	;
protected
DOT_NUM
	:	(NUM_LETTER)+
	;
protected
NUM_START
	:	'1'..'9'
	;
protected
NUM_LETTER
	:	'0'..'9'
	;

DATA_TYPE_STRING options {testLiterals=true;}
    : "character" | "varchar"
    | "decimal" | "numeric" | "float"
    | "binary" | "varbinary"
    ;

//DATA_TYPE_STRING options {testLiterals=true;}
//	: "character" | "varchar" | "char" | "uniqueidentifierstr"
//	| "bigint" | "int" | "integer" | "smallint" | "tinyint" | "double" | "float" | "real" | "decimal" | "numeric"
//	| "date" | "datetime" | "smalldatetime" | "time" | "timestamp"
//	| "bit" | "binary" | "varbinary"
//	;

ML_COMMENT
	:	"/*"
		(	/*	'\r' '\n' can be matched in one alternative or by matching
				'\r' in one iteration and '\n' in another.  I am trying to
				handle any flavor of newline that comes in, but the language
				that allows both "\r\n" and "\r" and "\n" to all be valid
				newline is ambiguous.  Consequently, the resulting grammar
				must be ambiguous.  I'm shutting this warning off.
			 */
			options {
				generateAmbigWarnings=false;
			}
		:
			{ LA(2)!='/' }? '*'
		|	'\r' '\n'		{newline();}
		|	'\r'			{newline();}
		|	'\n'			{newline();}
		|	~('*'|'\n'|'\r')
		)*
		"*/"
		{$setType(Token.SKIP);}
	;

/*==========================================================
//
//					TreeParser Define
//
//==========================================================*/	
{
	import java.util.*;

	import model.parser.*;
}
class SybaseIQ12TreeParser extends TreeParser;
{
	Map tables = new HashMap();
	
	private TableModel addTableByChName(String chName) {
		TableModel table = (TableModel) tables.get(chName);
		if (table == null) {
			table = new TableModel(chName);
			tables.put(chName, table);
		}
		return table;
	}
	
	public Map getTables(){
		return tables;
	}
}

segment returns[QueryModel model]
{
	model=null;
	
	SelectStatementModel stmt = new SelectStatementModel();
	SelectListModel selList = new SelectListModel();
	TableListModel t1 = new TableListModel();
	ColumnModel c1 = new ColumnModel();
	SearchConditionModel cond = new SearchConditionModel();
}
	: 	#(COLUMN c1 = column)
		{
			for (Iterator it = getTables().keySet().iterator(); it.hasNext();){
	        	t1.addTable( (TableModel)getTables().get((String)it.next()));
	        }
			selList.addColumn(c1);
			stmt.setTableList(t1);
			stmt.setSelectList(selList);
			model = stmt;
		}
	|	#(WHERE cond = search_condition)
		{
			for (Iterator it = getTables().keySet().iterator(); it.hasNext();){
	        	t1.addTable( (TableModel)getTables().get((String)it.next()));
	        }
			stmt.setTableList(t1);
			stmt.setSearchCondition(cond);
			model = stmt;	
		}
	;

statements returns [StatementsModel model]
{StatementsModel s1, s2; QueryModel s; model=new StatementsModel();}
	:	#(SEMI s1=statements s2=statements)
	{model.addChild(s1); model.addChild(s2);}
	|	s=statement
	{model.addStatement(s);}
	;
	
statement returns [QueryModel model]
{	model = null;
	String method;
	SelectStatementModel select; 
	TableUnionModel union = new TableUnionModel(); 
	TableModel tableModel1, tableModel2;
	TableCompareModel tableCompare = new TableCompareModel();
	TableListModel t1;
	SearchConditionModel cond;
}
		//表合并语句
	:	#("表合并" t1=tableUnionList)
		{
			union.addTableListModel(t1);
			model = union;
		}

		//表比较语句
	|	#("表比较" tableModel1=table_name tableModel2=table_name method=compare_method cond=search_condition)
		{	
			tableCompare.addTableModel1(tableModel1);
			tableCompare.addTableModel2(tableModel2);
			tableCompare.setCompareMethod(method);
			tableCompare.setSearchCondition(cond);
			model = tableCompare;
		}
		//自定义查询语句
	|	#(SELECT_STATEMENT model=select_statement)
	;

tableUnionList returns [TableListModel model]
{
	model = new TableListModel();
	TableModel t;
	TableListModel m1, m2;
	
}
	: 	#(COMMA m1=tableUnionList m2=tableUnionList)
		{model.addChild(m1); model.addChild(m2);}
	|	t = table_name
		{model.addTable(t);}
	;


compare_method returns [String rValue]
{rValue = "";}
	:	v1: comparemethod_name
		{rValue = v1.getText();}
	|	#(LOGICAL_NOT_EXISTS "not" "exists")
		{rValue = "not exists";}
	;

select_statement returns [SelectStatementModel model]
{
	OrderExpressionListModel order;
	AggregateExprListModel group;
	SearchConditionModel cond;
	TableListModel tl;
	SelectListModel sl;
	SelectStatementModel s;
	model=new SelectStatementModel();
	boolean isdst = false;
}
	:	#("唯一" select sl=select_list)
	{sl.setDistinct(true); model.setSelectList(sl);}
	|	#("distinct" select sl=select_list)
	{sl.setDistinct(true); model.setSelectList(sl);}

	|	#("前N条" isdst = distinct_select_op int1:REAL_NUM sl=select_list)
	{sl.setTopNum(int1.getText()); sl.setDistinct(isdst); model.setSelectList(sl);}
	|	#("top" isdst = distinct_select_op  int2:REAL_NUM sl=select_list)
	{sl.setTopNum(int2.getText()); sl.setDistinct(isdst); model.setSelectList(sl);}

	|	#("查询" sl=select_list)
	{model.setSelectList(sl);}
	|	#("select" sl=select_list)
	{model.setSelectList(sl);}

	|	#("来自" s=select_statement tl=table_list)
	{model.addChild(s); model.setTableList(tl);}
	|	#("from" s=select_statement tl=table_list)
	{model.addChild(s); model.setTableList(tl);}

	|	#("条件" s=select_statement cond=search_condition)
	{model.addChild(s); model.setSearchCondition(cond);}
	|	#("where" s=select_statement cond=search_condition)
	{model.addChild(s); model.setSearchCondition(cond);}

	|	#("分组" s=select_statement group=aggregate_expression_list)
	{model.addChild(s); model.setGroupExpressionList(group);}
	|	#("group" s=select_statement group=aggregate_expression_list)
	{model.addChild(s); model.setGroupExpressionList(group);}
	
	|	#("排序" s=select_statement order=order_expression_list)
	{model.addChild(s); model.setOrderExpressionList(order);}
	|	#("order" s=select_statement order=order_expression_list)
	{model.addChild(s); model.setOrderExpressionList(order);}
	;

distinct_select_op returns [boolean rValue]
{
	rValue = false;
}
	:	#("distinct" select)
		{rValue = true;}
	|	#("唯一" select)
		{rValue = true;}
	|	select
		{rValue = false;}
	;
	
select_list returns [SelectListModel model]
{
	SelectListModel m1, m2; 
	ColumnModel c;
	model=new SelectListModel();
}
	:	#(COMMA m1=select_list m2=select_list)
	{model.addChild(m1); model.addChild(m2);}
	|	c=column
	{model.addColumn(c);}
	;

table_list returns [TableListModel model]
{TableModel t; TableListModel m1, m2; model=new TableListModel();}
	:	#(COMMA m1=table_list m2=table_list)
	{model.addChild(m1); model.addChild(m2);}
	|	t=table_name
	{model.addTable(t);}
	;

//search_condition returns [SearchConditionModel model]
//{SearchConditionModel m1, m2; EquationModel equ; model=new SearchConditionModel();}
//	:	#(LOGIC_OP m1=search_condition op:logic_op m2=search_condition)
//	{model.addChild(m1); model.addOperator(op.getText()); model.addChild(m2);}
//	|	equ=equation
//	{model.addEquation(equ);}
//	;
search_condition returns [SearchConditionModel model]
{SearchConditionModel m1, m2, m3, m4, m5; EquationModel equ; model=new SearchConditionModel();}
	:	#(o1:"and" m1=search_condition m2=search_condition)
	{model.addChild(m1); model.addOperator(o1.getText()); model.addChild(m2);}
	|	#(o2:"or" m1=search_condition m2=search_condition)
	{model.addChild(m1); model.addOperator(o2.getText()); model.addChild(m2);}
	|	#(o3:"并且" m1=search_condition m2=search_condition)
	{model.addChild(m1); model.addOperator(o3.getText()); model.addChild(m2);}
	|	#(o4:"或者" m1=search_condition m2=search_condition)
	{model.addChild(m1); model.addOperator(o4.getText()); model.addChild(m2);}

	|	#(LOGIC_BLOCK m3=search_condition)
	{model.addOperator("("); model.addChild(m3); model.addOperator(")");}

	|	#(SEARCH_NOT_CONDITION o11:"not" m4=search_condition)
	{model.addOperator(o11.getText()); model.addChild(m4);}
	|	#(o12:"非" m5=search_condition)
	{model.addOperator(o12.getText()); model.addChild(m5);}
//	|	#(o11:"not" m4=search_condition)
//	{model.addOperator(o11.getText()); model.addChild(m4);}
//	|	#(o12:"非" m5=search_condition)
//	{model.addOperator(o12.getText()); model.addChild(m5);}

	|	equ=equation
	{model.addEquation(equ);}
	;

aggregate_expression_list returns [AggregateExprListModel model]
{AggregateExprListModel m1, m2; AggregateExprModel expr; model=new AggregateExprListModel();}
	:	#(COMMA m1=aggregate_expression_list m2=aggregate_expression_list)
	{model.addChild(m1); model.addChild(m2);}
	|	expr=aggregate_expression
	{model.addAggregateExpression(expr);}
	;

order_expression_list returns [OrderExpressionListModel model]
{OrderExpressionModel e; OrderExpressionListModel m1, m2; model=new OrderExpressionListModel();}
	:	#(COMMA m1=order_expression_list m2=order_expression_list)
	{model.addChild(m1); model.addChild(m2);}
	|	e=order_expression
	{model.addOrderExpression(e);}
	;

column returns [ColumnModel model]
{ExpressionModel e; AliasModel a; model=new ColumnModel();}
	:	#("as" e=expression a=alias)
	{model.addExpression(e); model.addAlias(a);}
	|	#("作为" e=expression a=alias)
	{model.addExpression(e); model.addAlias(a);}
	|	#(ALIAS_EQU a=alias e=expression)
	{model.addExpression(e); model.addAlias(a);}
	|	e=expression
	{model.addExpression(e);}
	;

equation returns [EquationModel model]
{
	ExpressionModel e1, e2, e3;
	EquationModel equation;
	model=new EquationModel();
	String nullStr = "";
}
	:	#(COMPARE_OP e1=expression op:compare_op e2=expression)
	{model.addExpression(e1); model.addOperator(op.getText()); model.addExpression(e2);}
	
	|	#(LOGICAL_NOT_LIKE e1=expression "not" "like" e2=expression)
	{model.addExpression(e1); model.addOperator("not like"); model.addExpression(e2);}
	
	|	#(LOGICAL_NULL e1=expression "is" "null")
	{model.addExpression(e1); model.addOperator("is null");}
	|	#(n:"为空" e1=expression)
	{model.addExpression(e1); model.addOperator(n.getText());}
	|	#(LOGICAL_NOT_NULL e1=expression "is" "not" "null")
	{model.addExpression(e1); model.addOperator("is not null");}
	|	#(nn:"非空" e1=expression)
	{model.addExpression(e1); model.addOperator(nn.getText());}

//	|	#(n:"为空" e1=expression)
//	{model.addExpression(e1); model.addOperator(n.getText());}
//	|	#("null" e1=expression)
//	{model.addExpression(e1); model.addOperator("is null");}
//	|	#(nn:"非空" e1=expression)
//	{model.addExpression(e1); model.addOperator(nn.getText());}
//	|	#(nn_en:"not" e1=expression)
//	{model.addExpression(e1); model.addOperator("is not null");}

	|	#("between" e1=expression e2=expression e3=expression)
	{model.addExpression(e1); model.addOperator("between");
	 model.addExpression(e2); model.addExpression(e3);}
	|	#(btw:"范围" e1=expression e2=expression e3=expression)
	{model.addExpression(e1); model.addOperator(btw.getText());
	 model.addExpression(e2); model.addExpression(e3);
	}
	
	|	#(LOGICAL_IN e1=expression "in" e2=exp_set)
	{model.addExpression(e1); model.addOperator("in"); model.addExpression(e2);}
	|	#(ct1:"在于" e1=expression e2=exp_set)
	{model.addExpression(e1); model.addOperator(ct1.getText()); model.addExpression(e2);}
	|	#(LOGICAL_NOT_IN e1=expression "not" "in" e2=exp_set)
	{model.addExpression(e1); model.addOperator("not in"); model.addExpression(e2);}
	|	#(ct2:"不在于" e1=expression e2=exp_set)
	{model.addExpression(e1); model.addOperator(ct2.getText()); model.addExpression(e2);}

//	|	#("in" e1=expression e2=exp_set)
//	{model.addExpression(e1); model.addOperator("in"); model.addExpression(e2);}
//	|	#(ct1:"在于" e1=expression e2=exp_set)
//	{model.addExpression(e1); model.addOperator(ct1.getText()); model.addExpression(e2);}
//	|	#("not in" e1=expression e2=exp_set)
//	{model.addExpression(e1); model.addOperator("not in"); model.addExpression(e2);}
//	|	#(ct2:"不在于" e1=expression e2=exp_set)
//	{model.addExpression(e1); model.addOperator(ct2.getText()); model.addExpression(e2);}

	;

exp_set returns [ExpressionModel model]
{model = new ExpressionModel(); ExprContainModel expr;}
	: 	#(SUBCONTAIN_OP LPAREN expr=constexpset RPAREN)
		{
			model.addExprContainModel(expr);
		}
	;

constexpset returns [ExprContainModel model]
{
	model = new ExprContainModel();
	ExprContainModel cep1, cep2;
	String ce, ce1, ce2;
}
	:	#(COMMA cep1=constexpset cep2=constexpset)
		{model.addChild(cep1); model.addChild(cep2);}
	|	ce=constant_expr
		{model.addConstant(ce);}
	;

constant_expr returns [String rValue]
{rValue = "";}
	:	rn:REAL_NUM
	{rValue = rn.getText();}
	|	qs:QUOTED_STRING
	{rValue = qs.getText();}
	;

aggregate_expression returns [AggregateExprModel model]
{AggregateExprModel a1, a2; FieldModel field; FunctionModel func; model=new AggregateExprModel();}
	:	#(TWO_ARG_OP a1=aggregate_expression op:two_arg_op a2=aggregate_expression)
	{model.addChild(a1); model.addOperator(op.getText()); model.addChild(a2);}
	|	field=field_name
	{model.addField(field);}
	|	func=function
	{model.addFunction(func);}
	|	nrn:NEGATIVE_DIGIT_ELEMENT
	{model.addConstant(nrn.getText());}
	|	rn:REAL_NUM
	{model.addConstant(rn.getText());}
	|	qs:QUOTED_STRING
	{model.addConstant(qs.getText());}	
	;
order_expression returns [OrderExpressionModel model]
{FunctionModel func; FieldModel field; OrderAliasModel alias; OrderExpressionModel o; model=new OrderExpressionModel();}
	:	alias = orderAlias
	{model.addOrderAlias(alias);}
	|	field=field_name
	{model.addField(field);}
	|	func=function
	{model.addFunction(func);}
	|	#("asc" o=order_expression)
	{model.addChild(o); model.setSort(OrderExpressionModel.ASC);}
	|	#("升序" o=order_expression)
	{model.addChild(o); model.setSort(OrderExpressionModel.ASC);}
	|	#("desc" o=order_expression)
	{model.addChild(o); model.setSort(OrderExpressionModel.DESC);}
	|	#("降序" o=order_expression)
	{model.addChild(o); model.setSort(OrderExpressionModel.DESC);}
	;
orderAlias returns [OrderAliasModel model]
{model=null;}
	:	a1:QUOTED_STRING
	{model=new OrderAliasModel(a1.getText());}
	|	a2:ID
	{model=new OrderAliasModel(a2.getText());}
	;
alias returns [AliasModel model]
{model=null;}
	:	a1:QUOTED_STRING
	{model=new AliasModel(a1.getText());}
	|	a2:ID
	{model=new AliasModel(a2.getText());}
	;

expression returns [ExpressionModel model]
{FieldModel f; FunctionModel func; ParamModel param; ExpressionModel e1, e2; model=new ExpressionModel();}
	:	#(TWO_ARG_OP e1=expression op:two_arg_op e2=expression)
	{model.addChild(e1); model.addOperator(op.getText()); model.addChild(e2);}
	|	#(ONE_ARG_OP op1:one_arg_op e1=expression)
	{model.addOperator(op1.getText()); model.addChild(e1);}
	|	lp:LPAREN e1=expression rp:RPAREN
	{model.addOperator(lp.getText()); model.addChild(e1); model.addOperator(rp.getText());}
	|	dkw:date_key_word
	{model.addConstant(dkw.getText());}
	|	param=param_equ
	{model.addParam(param);}
	|	f=field_name
	{model.addField(f);}
	|	func=function
	{model.addFunction(func);}
	|	nrn:NEGATIVE_DIGIT_ELEMENT
	{model.addConstant(nrn.getText());}
	|	rn:REAL_NUM
	{model.addConstant(rn.getText());}
	|	qs:QUOTED_STRING
	{model.addConstant(qs.getText());}
	|	allf:ALL_FIELDS
	{model.addOperator(allf.getText());}
	;

param_equ returns [ParamModel model]
	{model = null;}
	:	paramName:PARAM_ID
		{model = new ParamModel(paramName.getText(), "{", "}");}
//	|	#(paramName:ID lp:PARAM_LPAREN rp:PARAM_RPAREN)
//		{model = new ParamModel(paramName.getText(), lp.getText(), rp.getText());}
	;
	
//field_name returns [FieldModel model]
//{model=null;}
//	:	f:ID
//	{model=new FieldModel(f.getText());}
//	|	#(POINT t:ID f1:ID)
//	{
//		model=new FieldModel(f1.getText(), t.getText());
//		addTableByChName(t.getText());
//	}
//	;

field_name returns [FieldModel model]
{
	model=null;
	String tStr = "";
	String fStr = "";
}
	:	#(POINT tStr = sfield_name fStr = sfield_name)
		{
			model = new FieldModel(fStr, tStr);
			addTableByChName(tStr);
		}
	|	fStr = sfield_name
		{
			model=new FieldModel(fStr);
		}
	;

sfield_name returns [String rValue]
{rValue = "";}
	:	#(PAREN_FIELD f1:ID f2:ID)
		{rValue = f1.getText() + "(" + f2.getText() + ")";}
	|	f:ID
		{rValue = f.getText();}
	;

function returns [FunctionModel model]
{
	model=null;
	ParametersModel p, dtp1, dtp2; 
	ExpressionModel express1 = new ExpressionModel();
}
	:	//Aggregate functions聚合函数
		af:aggregate_func_name p=parameters
		{
			model = new AggregateFuncModel(af.getText(), AggregateFuncModel.NO_FILTER); 
			model.setParameters(p);
		}
		
		//Normal functions普通函数
//	|	f:function_name p=parameters
	|	#(FUNCTION f:function_name p=parameters)
		{
			model = new FunctionModel(f.getText());
			model.setParameters(p);
		}
		
		//Normal functions参数为空的普通函数[getdate()]
	|	#(FUNCTION_EMPTY_PARAM fun1:function_name)
		{
			model = new FunctionModel(fun1.getText());
		}
		
		//Normal functions参数为*的普通函数[now(*)...]
	|	#(FUNCTION_STAR_PARAM funStar:function_name)
		{
			model = new FunctionModel(funStar.getText());
			express1.addOperator("*");
			p = new ParametersModel();
			p.addParameter(express1);
			model.setParameters(p);
		}
	
		//Normal functions参数为DATA TYPE的普通函数[convert(char(10), '2007-01-01', 120)]
	|	#(FUNCTION_DATA_TYPE dtf1:function_name dtp1=data_type_parameters)
		{
			model = new FunctionModel(dtf1.getText());
			model.setParameters(dtp1);
		}
	
		//Normal functions参数为AS及DATA TYPE的普通函数[cast('2007-01-01' as char(10))]
	|	#(FUNCTION_AS_DATA_TYPE dtf2:function_name dtp2=as_data_type_parameters)
		{
			model = new FunctionModel(dtf2.getText());
			model.setParameters(dtp2);
		}
		
		//Aggregate functions参数为*的COUNT函数，聚合函数[count(*)]
//	|	#(FUNCTION_STAR_COUNT fun2:function_name)
	|	#(FUNCTION_STAR_COUNT countStr:"求记录总数")
		{	
			//model = new AggregateFuncModel(fun2.getText(), AggregateFuncModel.NO_FILTER);
			model = new AggregateFuncModel(countStr.getText(), AggregateFuncModel.NO_FILTER);
			express1.addOperator("*");
			p = new ParametersModel();
			p.addParameter(express1);
			model.setParameters(p);
		}
	|	#(FUNCTION_STAR_COUNT "count")
		{	
			//model = new AggregateFuncModel(fun2.getText(), AggregateFuncModel.NO_FILTER);
			model = new AggregateFuncModel("count", AggregateFuncModel.NO_FILTER);
			express1.addOperator("*");
			p = new ParametersModel();
			p.addParameter(express1);
			model.setParameters(p);
		}

		//Aggregate functions参数为全部、all的聚合函数
	|	#(all:"全部" af11:function_name p=parameters)
		{
			model = new AggregateFuncModel(af11.getText(), AggregateFuncModel.ALL);
			model.setParameters(p);
		}
	|	#("all" af12:function_name p=parameters)
		{
			model = new AggregateFuncModel(af12.getText(), AggregateFuncModel.ALL);
			model.setParameters(p);
		}
		
		//Aggregate functions参数为唯一、distinct的聚合函数
	|	#(dist:"唯一" af21:function_name p=parameters)
		{
			model = new AggregateFuncModel(af21.getText(), AggregateFuncModel.DISTINCT);
			model.setParameters(p);
		}
	|	#("distinct" af22:function_name p=parameters)
		{
			model=new AggregateFuncModel(af22.getText(), AggregateFuncModel.DISTINCT);
			model.setParameters(p);
		}
	;

//普通参数语法树遍历	
parameters returns [ParametersModel model]
{ParametersModel p1, p2; ExpressionModel e; model=new ParametersModel();}
	:	#(COMMA p1=parameters p2=parameters)
	{model.addChild(p1); model.addChild(p2);}
	|	e=expression
	{model.addParameter(e);}
	;

//数据类型参数语法树遍历	
data_type_parameters returns [ParametersModel model]
{ParametersModel p1, p2; ExpressionModel dtc, e; model=new ParametersModel();}
	: 	#(COMMA p1=data_type_parameters p2=data_type_parameters)
		{model.addChild(p1);model.addChild(p2);}
	|	dtc=datatype_constant
		{model.addParameter(dtc);}
	|	e=expression
		{model.addParameter(e);}
	;

//带AS数据类型参数语法树遍历
as_data_type_parameters returns [ParametersModel model]
{ParametersModel p1, p2; ExpressionModel adtc, e, re; model=new ParametersModel();}
	:	e=expression adtc=datatype_constant
	   	{
	   		model.addParameter(e);
	   		model.addParameter(adtc);
	   		model.addFilter(" as ");
	   	}
	;

//数据类型常量遍历
datatype_constant returns [ExpressionModel model]
{model=new ExpressionModel(); String rValue = ""; String rp = "";}
	:	c1:"char"
		{
			//返回char保留字
			rValue = c1.getText();
			model.addConstant(rValue);
		}
	|	#(PAREN_CHAR_DATA_TYPE c2:"char" rp=datatype_precision_or_scale_or_maxlength)
		{
			//返回带参数char保留
			rValue = c2.getText() + "(" + rp + ")";
			model.addConstant(rValue);
		}
	|	dtw:data_type_word
		{
			//返回数据类型保留字(date、datetime...)
			rValue = dtw.getText();
			model.addConstant(rValue);
		}
	|	#(PAREN_DATA_TYPE dts:DATA_TYPE_STRING rp=datatype_precision_or_scale_or_maxlength)
		{
			//返回数据类型加其参数,如char(10)、numeric(20, 2)]
			rValue = dts.getText() + "(" + rp + ")";
			model.addConstant(rValue);
		}
	|	sdts:DATA_TYPE_STRING
		{
			//返回数据类型为可不带参数]
			rValue = sdts.getText();
			model.addConstant(rValue);
		}
	;

datatype_precision_or_scale_or_maxlength returns [String rValue]
{rValue = "";}
	:	#(COMMA rn1:REAL_NUM rn2:REAL_NUM)
		{rValue = rn1.getText() + ", " + rn2.getText();}
	|	rn:REAL_NUM
		{rValue = rn.getText();}
	;

table_name returns [TableModel model]
{AliasModel a; model=null; TableAliasModel ta;}
	:	t:ID
	{model=new TableModel(t.getText());}
//	|	#("as" t1:ID a=alias)
//	{model=new TableModel(t1.getText()); model.setAlias(a);}
//	|	#("作为" t2:ID a = alias)
//	{model = new TableModel(t2.getText()); model.setAlias(a);}
	|	#("as" t1:ID ta = tableAlias)
	{model = new TableModel(t1.getText()); model.setAlias(ta);}
	|	#("作为" t2:ID ta = tableAlias)
	{model = new TableModel(t2.getText()); model.setAlias(ta);}
	;

tableAlias returns [TableAliasModel model]
{model=null;}
	:	a1:QUOTED_STRING
	{model = new TableAliasModel(a1.getText());}
	|	a2:ID
	{model = new TableAliasModel(a2.getText());}
	;

//////////////////////////////////////////////////////////////
// 常量
select : "查询" | "select";

distinct : "唯一" | "distinct";

logic_op : "and" | "or" | "并且" | "或者";

compare_op
	:	COMPARE_OP
	| "等于" | "大于等于" | "小于等于" | "大于" | "小于" | "不等于"
	| "包含" | "不包含" | "like"
	| "左连接" | LEFT_JOIN
	;

one_arg_op
	:	ONE_ARG_OP;

two_arg_op
	:	TWO_ARG_OP | STAR | MINUS
	|	"与" | "或" | "异或" | "加" | "减" | "乘" | "除" | "求模";

//比较运算符
comparemethod_name
	:	"exists" | "存在" | "不存在"
	;

//日期date-part保留字
date_key_word
	: "year" | "yy" | "month" | "mm" | "day" | "dd"
	| "quarter" | "qq" | "week" | "wk" | "dayofyear" | "dy"
	| "weekday" | "dw" | "hour" | "hh" | "minute" | "mi" | "second" | "ss" | "millisecond" | "ms"
	| "calweekofyear" | "cwk" | "calyearofweek" | "cyr" | "caldayofweek" | "cdw"
	;

//聚合函数
aggregate_func_name
	:	"avg" 		| 	"求平均数"
	|	"count" 	| 	"求记录总数"
	|	"max" 		| 	"求最大值"
	|	"min" 		| 	"求最小值"
	|	"stddev" 	| 	"求方差"
	|	"sum" 		|	"求总和"
	|	"variance" 	| 	"求统计方差"
	;

//普通函数(数学函数、字符串函数、日期时间函数、系统函数、数据类型转化函数、其他函数)
function_name
	:	number_function
	|	string_function
	|	datetime_function
	|	conversion_function
	|	system_function
	|	other_function
	;

//数学函数
number_function
	:	"abs" 		| 	"取绝对值"
	|	"acos"		|	"求反余弦值"
	|	"asin"		|	"求反正弦值"
	|	"atan"		|	"求反正切值"
	|	"atin2"		|	"求二个数的反正切值"
	|	"ceiling"	|	"取上限整数"
	|	"cos"		|	"求余弦值"
	|	"cot"		|	"求余切值"
	|	"degrees"	|	"弧度转度数"
	|	"exp"		|	"求幂值"
	|	"floor"		|	"取下限整数"
	|	"log"		|	"求自然对数"
	|	"log10"		|	"求10为底的对数"
	|	"mod"		|	"求模"
	|	"pi"		|	"求圆周率"
	|	"power"		|	"求幂"
	|	"radians"	|	"度数转弧度"
	|	"rand"		|	"取随机数"
	|	"remainder"	|	"求余"
	|	"round"		|	"格式化数值"
	|	"sign"		|	"求值的符号"
	|	"sin"		|	"求正弦值"
	|	"sqrt" 		| 	"求平方根"
	|	"tan"		|	"求正切值"
	|	"格式化数值3"	//"\"truncate\""
	|	"truncnum"	|	"N位置零处理"
	;

//字符串函数
string_function
	:	"ascii"			|	"求ASCII码"
	|	"bit_length"	|	"求字符串的二进制长度"
	|	"byte_length" 	| 	"求字符串的字节数"
	|	"char"			|	"求等值的字符"
	|	"char_length" 	| 	"求字符串长度1"
	|	"charindex" 	|	"存在于"
	|	"difference" 	|	"求两个串的声音差值"
	|	"insertstr"		|	"插入字符串"
	|	"lcase"			|	"转为小写字母1"
	|	"left"			|	"左截字符串"
	|	"length"		|	"求字符串长度2"
	|	"locate"		|	"求串位置1"
	|	"lower" 		| 	"转为小写字母2"
	|	"ltrim"			|	"去掉左空格"
	|	"octet_length"	|	"求字符串的存储长度"
	|	"patindex"		|	"求串位置2"
	|	"repeat"		|	"字符串循环连接1"
	|	"replace"		|	"替换字符串"
	|	"replicate"		|	"字符串循环连接2"
	|	"right"			|	"右截字符串"
	|	"rtrim"			|	"去掉右空格"
	|	"similar"		|	"求字符串相似度"
	|	"sortkey"		|	"字符串排序"
	|	"soundex"		|	"求字符串声音值"
	|	"space"			|	"填空格"
	|	"str"			|	"数值转字符串"
	|	"string"		|	"字符串合并"
	|	"stuff"			|	"字符串删除替换"
	|	"substring"		|	"字符串截取"
	|	"trim"			|	"去左右空格"
	|	"ucase"			|	"转为大写字母1"
	|	"upper"			|	"转为大写字母2"
	;

//日期时间函数
datetime_function
	:	"dateformat" 	|	"格式化日期"
	|	"datename"		|	"求日期分量英文名"
	|	"datepart"		|	"求日期的分量值"
	|	"datetime"		|	"转为日期时间"
	|	"date"			|	"转为日期"
	|	"dayname"		|	"求星期英文名"
	|	"days"			|	"求天数"
	|	"day"			|	"求具体日期"
	|	"dow"			|	"求具体星期"
	|	"hours"			|	"求小时数"
	|	"hour"			|	"求具体小时"
	|	"minutes"		|	"求分钟数"
	|	"minute"		|	"求具体分钟"
	|	"monthname"		|	"求月份英文名"
	|	"months"		|	"求月数"
	|	"month"			|	"求具体月数"
	|	"now"			|	"取当前日期时间1"
	|	"quarter"		|	"求具体季度"
	|	"seconds"		|	"求秒数"
	|	"second"		|	"求具体秒"
	|	"today"			|	"求当前日期"
	|	"weeks"			|	"求周数"
	|	"years"			|	"求年数"
	|	"year"			|	"求具体年份"
	|	"ymd"			|	"数值转日期"
	|	"getdate"		|	"取当前日期时间2"
	|	"dateadd"		|	"日期运算"
	|	"datediff"		|	"求两日期差值"
	;

//数据类型转化函数
conversion_function
	:	"hextoint"	|	"十六进制转为整数"
	|	"inttohex"	|	"整数转为十六进制"
	|	"isdate"	|	"是否日期型"
	|	"isnumeric"	|	"是否数值型"
	|	"cast"		|	"数据类型转化"
	|	"convert"	|	"数据类型转换"
	;

//系统函数
system_function
	:	"suser_id"
	|	"suser_name"
	|	"user_id"
	|	"user_name"
	;

//其他函数
other_function
	:	"argn"
	| 	"rowid"
	;

//数据类型保留字
data_type_word
	: "uniqueidentifierstr" 
	| "bigint" | "int" | "integer" | "smallint" | "tinyint" | "double" | "real"
	| "date" | "datetime" | "smalldatetime" | "time" | "timestamp"
	| "bit"
	;