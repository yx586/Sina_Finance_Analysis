package com.java.consts;

public class Constant {
    // 本地存储路径
    public static final String STOCK_DATA_PATH = "D:\\company\\DataPurify\\StockData\\";
    public static final String STOCK_JSON_PATH = STOCK_DATA_PATH+"json\\";
    public static final String STOCK_CSV_PATH = STOCK_DATA_PATH+"csv\\";
    public static final String CSV_SUFFIX = ".csv";
    public static final String JSON_SUFFIX = ".json";

    //  请求接口
    public static final String SINA_MAGIC_PATTERN_URL = "http://stock.finance.sina.com.cn/usstock/api/jsonp.php//US_CategoryService.getList?num=60&sort=&asc=0&page=";
    // CSV文件需要的header 列
    public static String CSV_HEADER = "purify_date>name>cname>category>symbol>price>diff>chg>preclose>open>high>low>amplitude>volume>mktcap>pe>market>category_id";
    // 每页请求数量
    public static final int PAGE_SIZE = 60;
    // 首页开始页数
    public static final int ONE_PAGE = 1;
    // 从0开始，页数-1,从1开始 页数-0
    public static final int DIFF_ = 0;
    // 返回信息头部标签
    public static final String HEAD_PREFIX = "((";
    // 返回信息尾部标签
    public static final String TAIL_PREFIX = "));";
    // GBK编码格式
    public static final String CHARSET_GBK = "gbk";
    // utf-8编码格式
    public static final String CHARSET_UTF8 = "utf-8";

}
