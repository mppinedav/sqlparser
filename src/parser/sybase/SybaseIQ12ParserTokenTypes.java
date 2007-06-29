// $ANTLR 2.7.7 (2006-11-01): "src/parser/sybase/parser_sybase.g" -> "SybaseIQ12Parser.java"$

	package parser.sybase;

public interface SybaseIQ12ParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int SELECT_STATEMENT = 4;
	int SEARCH_NOT_CONDITION = 5;
	int SUBQUERY = 6;
	int GROUP_BY = 7;
	int ORDER_BY = 8;
	int ALIAS_EQU = 9;
	int FUNCTION = 10;
	int FUNCTION_ROWID = 11;
	int FUNCTION_NOTHING = 12;
	int FUNCTION_EMPTY_PARAM = 13;
	int FUNCTION_STAR_PARAM = 14;
	int FUNCTION_STAR_COUNT = 15;
	int FUNCTION_DATA_TYPE = 16;
	int FUNCTION_AS_DATA_TYPE = 17;
	int LOGIC_OP = 18;
	int LOGICAL_NULL = 19;
	int LOGICAL_NOT_NULL = 20;
	int LOGICAL_IN = 21;
	int LOGICAL_NOT_IN = 22;
	int LOGICAL_LIKE = 23;
	int LOGICAL_NOT_LIKE = 24;
	int LOGICAL_EXISTS = 25;
	int LOGICAL_NOT_EXISTS = 26;
	int LOGICAL_BETWEEN = 27;
	int SUBCONTAIN_OP = 28;
	int ALL_FIELDS = 29;
	int PAREN_FIELD = 30;
	int PAREN_DATA_TYPE = 31;
	int PAREN_CHAR_DATA_TYPE = 32;
	int LOGIC_BLOCK = 33;
	int COLUMN = 34;
	int WHERE = 35;
	int SEMI = 36;
	int TABLE_UNION_EN = 37;
	int TABLE_UNION_CN = 38;
	int COMMA = 39;
	int TABLE_COMPARE_EN = 40;
	int TABLE_COMPARE_CN = 41;
	int WHERE_EN = 42;
	int WHERE_CN = 43;
	int EXISTS_EN = 44;
	int EXISTS_CN = 45;
	int NOT_EXISTS_CN = 46;
	int NOT_EN = 47;
	int SELECT_EN = 48;
	int SELECT_CN = 49;
	int DISTINCT_EN = 50;
	int DISTINCT_CN = 51;
	int TOP_EN = 52;
	int TOP_CN = 53;
	int FROM_EN = 54;
	int FROM_CN = 55;
	int GROUP_EN = 56;
	int BY_EN = 57;
	int GROUP_BY_CN = 58;
	int ORDER_EN = 59;
	int ORDER_BY_CN = 60;
	int REAL_NUM = 61;
	int NOT_CN = 62;
	int AND_EN = 63;
	int OR_EN = 64;
	int AND_CN = 65;
	int OR_CN = 66;
	int LPAREN = 67;
	int RPAREN = 68;
	int AS_EN = 69;
	int AS_CN = 70;
	// "=" = 71
	// "\u7b49\u4e8e" = 72
	int SELECT_ALL_CN = 73;
	int STAR = 74;
	int ASC_EN = 75;
	int ASC_CN = 76;
	int DESC_EN = 77;
	int DESC_CN = 78;
	int LIKE_EN = 79;
	int LIKE_CN = 80;
	int NOT_LIKE_CN = 81;
	int IS_EN = 82;
	int NULL_EN = 83;
	int NULL_CN = 84;
	int NOT_NULL_CN = 85;
	int BETWEEN_CN = 86;
	int BETWEEN_EN = 87;
	int IN_EN = 88;
	int IN_CN = 89;
	int NOT_IN_CN = 90;
	int COUNT_EN = 91;
	int COUNT_CN = 92;
	int ALL_EN = 93;
	int ALL_CN = 94;
	int DATA_TYPE_AS_CN = 95;
	int CHAR = 96;
	int DATA_TYPE_STRING = 97;
	int ID = 98;
	int PARAM_ID = 99;
	int QUOTED_STRING = 100;
	int POINT = 101;
	int NEGATIVE_DIGIT_ELEMENT = 102;
	int LITERAL_avg = 103;
	// "\u6c42\u5e73\u5747\u6570" = 104
	int LITERAL_max = 105;
	// "\u6c42\u6700\u5927\u503c" = 106
	int LITERAL_min = 107;
	// "\u6c42\u6700\u5c0f\u503c" = 108
	int LITERAL_stddev = 109;
	// "\u6c42\u65b9\u5dee" = 110
	int LITERAL_sum = 111;
	// "\u6c42\u603b\u548c" = 112
	int LITERAL_variance = 113;
	// "\u6c42\u7edf\u8ba1\u65b9\u5dee" = 114
	int SYSDATE_EN = 115;
	int SYSDATE_CN = 116;
	int LITERAL_getdate = 117;
	// "\u53d6\u5f53\u524d\u65e5\u671f\u65f6\u95f42" = 118
	int LITERAL_rand = 119;
	// "\u53d6\u968f\u673a\u6570" = 120
	int LITERAL_pi = 121;
	// "\u6c42\u5706\u5468\u7387" = 122
	int LITERAL_now = 123;
	// "\u53d6\u5f53\u524d\u65e5\u671f\u65f6\u95f41" = 124
	int LITERAL_today = 125;
	// "\u6c42\u5f53\u524d\u65e5\u671f" = 126
	int LITERAL_convert = 127;
	// "\u6570\u636e\u7c7b\u578b\u8f6c\u6362" = 128
	int LITERAL_cast = 129;
	// "\u6570\u636e\u7c7b\u578b\u8f6c\u5316" = 130
	int LITERAL_abs = 131;
	// "\u53d6\u7edd\u5bf9\u503c" = 132
	int LITERAL_acos = 133;
	// "\u6c42\u53cd\u4f59\u5f26\u503c" = 134
	int LITERAL_asin = 135;
	// "\u6c42\u53cd\u6b63\u5f26\u503c" = 136
	int LITERAL_atan = 137;
	// "\u6c42\u53cd\u6b63\u5207\u503c" = 138
	// "atin2" = 139
	// "\u6c42\u4e8c\u4e2a\u6570\u7684\u53cd\u6b63\u5207\u503c" = 140
	int LITERAL_ceiling = 141;
	// "\u53d6\u4e0a\u9650\u6574\u6570" = 142
	int LITERAL_cos = 143;
	// "\u6c42\u4f59\u5f26\u503c" = 144
	int LITERAL_cot = 145;
	// "\u6c42\u4f59\u5207\u503c" = 146
	int LITERAL_degrees = 147;
	// "\u5f27\u5ea6\u8f6c\u5ea6\u6570" = 148
	int LITERAL_exp = 149;
	// "\u6c42\u5e42\u503c" = 150
	int LITERAL_floor = 151;
	// "\u53d6\u4e0b\u9650\u6574\u6570" = 152
	int LITERAL_log = 153;
	// "\u6c42\u81ea\u7136\u5bf9\u6570" = 154
	// "log10" = 155
	// "\u6c4210\u4e3a\u5e95\u7684\u5bf9\u6570" = 156
	int LITERAL_mod = 157;
	// "\u6c42\u6a21" = 158
	int LITERAL_power = 159;
	// "\u6c42\u5e42" = 160
	int LITERAL_radians = 161;
	// "\u5ea6\u6570\u8f6c\u5f27\u5ea6" = 162
	int LITERAL_remainder = 163;
	// "\u6c42\u4f59" = 164
	int LITERAL_round = 165;
	// "\u683c\u5f0f\u5316\u6570\u503c" = 166
	int LITERAL_sign = 167;
	// "\u6c42\u503c\u7684\u7b26\u53f7" = 168
	int LITERAL_sin = 169;
	// "\u6c42\u6b63\u5f26\u503c" = 170
	int LITERAL_sqrt = 171;
	// "\u6c42\u5e73\u65b9\u6839" = 172
	int LITERAL_tan = 173;
	// "\u6c42\u6b63\u5207\u503c" = 174
	// "\u683c\u5f0f\u5316\u6570\u503c3" = 175
	int LITERAL_truncnum = 176;
	// "N\u4f4d\u7f6e\u96f6\u5904\u7406" = 177
	int LITERAL_ascii = 178;
	// "\u6c42ASCII\u7801" = 179
	int LITERAL_bit_length = 180;
	// "\u6c42\u5b57\u7b26\u4e32\u7684\u4e8c\u8fdb\u5236\u957f\u5ea6" = 181
	int LITERAL_byte_length = 182;
	// "\u6c42\u5b57\u7b26\u4e32\u7684\u5b57\u8282\u6570" = 183
	// "\u6c42\u7b49\u503c\u7684\u5b57\u7b26" = 184
	int LITERAL_char_length = 185;
	// "\u6c42\u5b57\u7b26\u4e32\u957f\u5ea61" = 186
	int LITERAL_charindex = 187;
	// "\u5b58\u5728\u4e8e" = 188
	int LITERAL_difference = 189;
	// "\u6c42\u4e24\u4e2a\u4e32\u7684\u58f0\u97f3\u5dee\u503c" = 190
	int LITERAL_insertstr = 191;
	// "\u63d2\u5165\u5b57\u7b26\u4e32" = 192
	int LITERAL_lcase = 193;
	// "\u8f6c\u4e3a\u5c0f\u5199\u5b57\u6bcd1" = 194
	int LITERAL_left = 195;
	// "\u5de6\u622a\u5b57\u7b26\u4e32" = 196
	int LITERAL_length = 197;
	// "\u6c42\u5b57\u7b26\u4e32\u957f\u5ea62" = 198
	int LITERAL_locate = 199;
	// "\u6c42\u4e32\u4f4d\u7f6e1" = 200
	int LITERAL_lower = 201;
	// "\u8f6c\u4e3a\u5c0f\u5199\u5b57\u6bcd2" = 202
	int LITERAL_ltrim = 203;
	// "\u53bb\u6389\u5de6\u7a7a\u683c" = 204
	int LITERAL_octet_length = 205;
	// "\u6c42\u5b57\u7b26\u4e32\u7684\u5b58\u50a8\u957f\u5ea6" = 206
	int LITERAL_patindex = 207;
	// "\u6c42\u4e32\u4f4d\u7f6e2" = 208
	int LITERAL_repeat = 209;
	// "\u5b57\u7b26\u4e32\u5faa\u73af\u8fde\u63a51" = 210
	int LITERAL_replace = 211;
	// "\u66ff\u6362\u5b57\u7b26\u4e32" = 212
	int LITERAL_replicate = 213;
	// "\u5b57\u7b26\u4e32\u5faa\u73af\u8fde\u63a52" = 214
	int LITERAL_right = 215;
	// "\u53f3\u622a\u5b57\u7b26\u4e32" = 216
	int LITERAL_rtrim = 217;
	// "\u53bb\u6389\u53f3\u7a7a\u683c" = 218
	int LITERAL_similar = 219;
	// "\u6c42\u5b57\u7b26\u4e32\u76f8\u4f3c\u5ea6" = 220
	int LITERAL_sortkey = 221;
	// "\u5b57\u7b26\u4e32\u6392\u5e8f" = 222
	int LITERAL_soundex = 223;
	// "\u6c42\u5b57\u7b26\u4e32\u58f0\u97f3\u503c" = 224
	int LITERAL_space = 225;
	// "\u586b\u7a7a\u683c" = 226
	int LITERAL_str = 227;
	// "\u6570\u503c\u8f6c\u5b57\u7b26\u4e32" = 228
	int LITERAL_string = 229;
	// "\u5b57\u7b26\u4e32\u5408\u5e76" = 230
	int LITERAL_stuff = 231;
	// "\u5b57\u7b26\u4e32\u5220\u9664\u66ff\u6362" = 232
	int LITERAL_substring = 233;
	// "\u5b57\u7b26\u4e32\u622a\u53d6" = 234
	int LITERAL_trim = 235;
	// "\u53bb\u5de6\u53f3\u7a7a\u683c" = 236
	int LITERAL_ucase = 237;
	// "\u8f6c\u4e3a\u5927\u5199\u5b57\u6bcd1" = 238
	int LITERAL_upper = 239;
	// "\u8f6c\u4e3a\u5927\u5199\u5b57\u6bcd2" = 240
	int LITERAL_dateformat = 241;
	// "\u683c\u5f0f\u5316\u65e5\u671f" = 242
	int LITERAL_datename = 243;
	// "\u6c42\u65e5\u671f\u5206\u91cf\u82f1\u6587\u540d" = 244
	int LITERAL_datepart = 245;
	// "\u6c42\u65e5\u671f\u7684\u5206\u91cf\u503c" = 246
	int LITERAL_datetime = 247;
	// "\u8f6c\u4e3a\u65e5\u671f\u65f6\u95f4" = 248
	int LITERAL_date = 249;
	// "\u8f6c\u4e3a\u65e5\u671f" = 250
	int LITERAL_dayname = 251;
	// "\u6c42\u661f\u671f\u82f1\u6587\u540d" = 252
	int LITERAL_days = 253;
	// "\u6c42\u5929\u6570" = 254
	int LITERAL_day = 255;
	// "\u6c42\u5177\u4f53\u65e5\u671f" = 256
	int LITERAL_dow = 257;
	// "\u6c42\u5177\u4f53\u661f\u671f" = 258
	int LITERAL_hours = 259;
	// "\u6c42\u5c0f\u65f6\u6570" = 260
	int LITERAL_hour = 261;
	// "\u6c42\u5177\u4f53\u5c0f\u65f6" = 262
	int LITERAL_minutes = 263;
	// "\u6c42\u5206\u949f\u6570" = 264
	int LITERAL_minute = 265;
	// "\u6c42\u5177\u4f53\u5206\u949f" = 266
	int LITERAL_monthname = 267;
	// "\u6c42\u6708\u4efd\u82f1\u6587\u540d" = 268
	int LITERAL_months = 269;
	// "\u6c42\u6708\u6570" = 270
	int LITERAL_month = 271;
	// "\u6c42\u5177\u4f53\u6708\u6570" = 272
	int LITERAL_quarter = 273;
	// "\u6c42\u5177\u4f53\u5b63\u5ea6" = 274
	int LITERAL_seconds = 275;
	// "\u6c42\u79d2\u6570" = 276
	int LITERAL_second = 277;
	// "\u6c42\u5177\u4f53\u79d2" = 278
	int LITERAL_weeks = 279;
	// "\u6c42\u5468\u6570" = 280
	int LITERAL_years = 281;
	// "\u6c42\u5e74\u6570" = 282
	int LITERAL_year = 283;
	// "\u6c42\u5177\u4f53\u5e74\u4efd" = 284
	int LITERAL_ymd = 285;
	// "\u6570\u503c\u8f6c\u65e5\u671f" = 286
	int LITERAL_dateadd = 287;
	// "\u65e5\u671f\u8fd0\u7b97" = 288
	int LITERAL_datediff = 289;
	// "\u6c42\u4e24\u65e5\u671f\u5dee\u503c" = 290
	int LITERAL_hextoint = 291;
	// "\u5341\u516d\u8fdb\u5236\u8f6c\u4e3a\u6574\u6570" = 292
	int LITERAL_inttohex = 293;
	// "\u6574\u6570\u8f6c\u4e3a\u5341\u516d\u8fdb\u5236" = 294
	int LITERAL_isdate = 295;
	// "\u662f\u5426\u65e5\u671f\u578b" = 296
	int LITERAL_isnumeric = 297;
	// "\u662f\u5426\u6570\u503c\u578b" = 298
	int LITERAL_suser_id = 299;
	int LITERAL_suser_name = 300;
	int LITERAL_user_id = 301;
	int LITERAL_user_name = 302;
	int LITERAL_argn = 303;
	int LITERAL_rowid = 304;
	// "\u6c42\u884c\u53f7" = 305
	int TILDE = 306;
	// "\u975e\u8fd0\u7b97" = 307
	// "\u4e0e" = 308
	// "\u6216" = 309
	// "\u5f02\u6216" = 310
	// "\u52a0" = 311
	// "\u51cf" = 312
	// "\u4e58" = 313
	// "\u9664" = 314
	int PLUS = 315;
	int MINUS = 316;
	int DIVIDE = 317;
	int MOD = 318;
	int AMPERSAND = 319;
	int BITWISEOR = 320;
	int BITWISEXOR = 321;
	int ASSIGNEQUAL = 322;
	// "\u5927\u4e8e\u7b49\u4e8e" = 323
	// "\u5c0f\u4e8e\u7b49\u4e8e" = 324
	// "\u5927\u4e8e" = 325
	// "\u5c0f\u4e8e" = 326
	// "\u4e0d\u7b49\u4e8e" = 327
	// "\u5de6\u8fde\u63a5" = 328
	int LEFT_JOIN = 329;
	int NOTEQUAL1 = 330;
	int NOTEQUAL2 = 331;
	int LESSTHANOREQUALTO1 = 332;
	int LESSTHANOREQUALTO2 = 333;
	int LESSTHAN = 334;
	int GREATERTHANOREQUALTO1 = 335;
	int GREATERTHANOREQUALTO2 = 336;
	int GREATERTHAN = 337;
	int LITERAL_yy = 338;
	int LITERAL_mm = 339;
	int LITERAL_dd = 340;
	int LITERAL_qq = 341;
	int LITERAL_week = 342;
	int LITERAL_wk = 343;
	int LITERAL_dayofyear = 344;
	int LITERAL_dy = 345;
	int LITERAL_weekday = 346;
	int LITERAL_dw = 347;
	int LITERAL_hh = 348;
	int LITERAL_mi = 349;
	int LITERAL_ss = 350;
	int LITERAL_millisecond = 351;
	int LITERAL_ms = 352;
	int LITERAL_calweekofyear = 353;
	int LITERAL_cwk = 354;
	int LITERAL_calyearofweek = 355;
	int LITERAL_cyr = 356;
	int LITERAL_caldayofweek = 357;
	int LITERAL_cdw = 358;
	int LITERAL_uniqueidentifierstr = 359;
	int LITERAL_bigint = 360;
	int LITERAL_int = 361;
	int LITERAL_integer = 362;
	int LITERAL_smallint = 363;
	int LITERAL_tinyint = 364;
	int LITERAL_double = 365;
	int LITERAL_real = 366;
	int LITERAL_smalldatetime = 367;
	int LITERAL_time = 368;
	int LITERAL_timestamp = 369;
	int LITERAL_bit = 370;
	int DOT_STAR = 371;
	int PARAM_LPAREN = 372;
	int PARAM_RPAREN = 373;
	int FROM = 374;
	int WS = 375;
	int ESC = 376;
	int ID_START_LETTER = 377;
	int ID_LETTER = 378;
	int NUM = 379;
	int DOT_NUM = 380;
	int NUM_START = 381;
	int NUM_LETTER = 382;
	int ML_COMMENT = 383;
	int COMPARE_OP = 384;
	int TWO_ARG_OP = 385;
	int ONE_ARG_OP = 386;
}