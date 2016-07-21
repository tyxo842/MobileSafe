package tyxo.mobilesafe;

/**
 * 请各位将baseUrl放在一起,便于管理,
 * 自己负责的功能模块接口,再自己管理
 * <p/>
 * ps: 各位将自己的接口,写好备注,没用的就删掉吧...
 */
public class ConstValues {

    /**
     * 首选项(配置)文件名称
     */
    public static final String USER_DATA_FILE = "user_data";
    public static final String SERVER_RESPONSE_EMPTY = "服务器返回结果为空";
    public static final String SERVER_RESPONSE_ERROR = "网络或者服务器异常，请稍候重试";
    public static final String MYPHOTO_URL = "http://b164.photo.store.qq.com/psb?/V11IXfXu1OApUM/bRbBm8FNRXVXb*BGLmN4IM2UtDkHFiAuLRcuGcv7RRQ!/b/dL54w2GxAQAA&bo=IANYAgAAAAABAF4!&rf=viewer_4";

    /**
     * MQTT消息服务器host
     */ //124.193.157.42:8013
    public static final String NOTIFY_MESSAGE_ENTITY = "message_entity";
    public static final String NOTIFY_FLAG_KEY = "notify_flag";
    public static final int NOTIFY_FLAG_MESSAGE = 10;

    //********* 测试地址 start**********************************************************************************/

//    public static final String BASE_URL_USER = "http://124.193.157.42:7067/user_test";                                                    // 1用户登录
//    public static final String BASE_URL_MSG = "http://124.193.157.42:7067/message_test";                                                  // 2消息
//    public static final String WCF_BASE_URL = "http://ms.app.emedchina.cn/services/MobileWCFService.svc";                                 // 3意见反馈
//    public static final String SERVER_UPDATE_URL = "http://124.193.157.51:8050/client_version.xml";                                       // 4版本更新
//    public static final String BASE_URL_CODE = "http://124.193.157.51:8028/TrdOrderServiceForMobile.svc";                                 // 5(企业报码||条码备货||产品查询||条码规则)
//    public static final String BASE_URL_NEWS_LIST = "http://124.193.157.42:7067/datahc_test/api/mobileWebServiceForIOS/getNewsFeed/";     // 6新闻列表
//    public static final String BASE_URL_NEWS_DETAIL = "http://124.193.157.42:7067/datahc_test/mobileWebServiceForIOS/newsViewPage.do";    // 7新闻详情
//    public static final String REGINFO_SERVER_BASE_URL = "http://124.193.157.51:8028/MobileAPP/PlatHCLicenseServiceForMobile.svc";        // 8注册证相关
//    public static final String PRODUCT_SERVER_BASE_URL = "http://124.193.157.51:8028/MobileAPP/PlatHCProductServiceForMobile.svc";        // 9产品相关
//    public static final String SERVICE_BASE_URL = "http://124.193.157.42:7067/datahc_test/api/";                                          // 10电话服务
//    public static final String PDS_ONLINE_URL = "http://124.193.157.42:7067/datahc_test/api/online";                                      // 11在线答疑
//    public static final String ORDER_BASE_QUERY_HOSPITAL_URL = "http://124.193.157.51:8032/MobileService.svc/";                           // 12订单查询
//    public static final String INVOICE_NOTICE = "http://ms.app.emedchina.cn/invoicedesc.html";                                            // 13发票须知

    //********* 正式地址 start**********************************************************************************/

    public static final String BASE_URL_USER = "http://user.emedchina.cn";                                                                  // 1用户登录
    public static final String BASE_URL_MSG = "http://ms.emedchina.cn";                                                                     // 2消息
    public static final String WCF_BASE_URL = "http://ms.app.emedchina.cn/services/MobileWCFService.svc";                                   // 3意见反馈
    public static final String SERVER_UPDATE_URL = "http://ms.app.emedchina.cn/client_version.xml";                                         // 4版本更新
    public static final String BASE_URL_CODE = "http://ifs.smix.emedchina.cn/TrdOrderServiceForMobile.svc";                                 // 5(企业报码||条码备货||产品查询||条码规则)
    public static final String BASE_URL_NEWS_LIST = "http://hc.emedchina.cn/api/mobileWebServiceForIOS/getNewsFeed/";                       // 6新闻列表
    public static final String BASE_URL_NEWS_DETAIL = "http://hc.emedchina.cn/mobileWebServiceForIOS/newsViewPage.do";                      // 7新闻详情
    public static final String REGINFO_SERVER_BASE_URL = "http://ifs.smix.emedchina.cn/MobileAPP/PlatHCLicenseServiceForMobile.svc";        // 8注册证相关
    public static final String PRODUCT_SERVER_BASE_URL = "http://ifs.smix.emedchina.cn/MobileAPP/PlatHCProductServiceForMobile.svc";        // 9产品相关
    public static final String SERVICE_BASE_URL = "http://hc.emedchina.cn/api/";                                                            // 10电话服务
    public static final String PDS_ONLINE_URL = "http://hc.emedchina.cn/api/online";                                                        // 11在线答疑
    public static final String ORDER_BASE_QUERY_HOSPITAL_URL = "http://haihong.eye.ac.cn/MobileService.svc/";                               // 12订单查询
    public static final String INVOICE_NOTICE = "http://ms.app.emedchina.cn/invoicedesc.html";                                              // 13发票须知




    //************ 0用户与消息****************************************************************************************************************************************************/
    public static final String USER_LOGIN_URL = "/api/user/applogin";                                           //登录
    public static final String MESSAGE_LIST_URL = "/api/getmessage/list";                                       //消息列表
    public static final String MESSAGE_LIST_DETAIL_URL = "/api/getmessage/getup";                               //消息
    public static final String MESSAGE_READ_STATUS_URL = "/api/getmessage/get";                                 //消息
    public static final String SERVER_MODIFYPWD_URL = "/api/user/appoper";                                      //修改密码
    public static final String SERVER_FEEDBACK_URL = "/submitFeedback";                                         //意见反馈

    //*********** 1注册证查询*******/
    public static final String PDS_LICENSE_TYPE = "/fetchLicenseType";                                          // 获取证照类型
    public static final String PDS_FETCH_REGINFO = "/fetchRegInfo";                                             // 注册证列表
    public static final String FETCH_REGULATIONS_BYREGID = "/fetchRegulationsByRegId";                          // 关联品规
    public static final String PDS_FETCH_ASSOCIATED_PRODUCT_BYREGID = "/fetchAssociatedProductByRegId";         // 关联产品


    //*********** 2产品查询*******/
    public static final String FETCH_PRODUCT_CATEGORY = "/fetchProductCategory";                                //获取产品分类
    public static final String PDS_FETCH_PRODUCT_INFO = "/fetchProductInfo";                                    //产品查询
    public static final String PDS_FETCH_PRODUCT_DETAIL_INFO = "/fetchProductDetailInfo";                       //产品详情

    //*********** 3快速审核*********/
    public static final String SOON_APPROVE = "payTaskQuickAudit/fetchSoonApproveList";                         //获取快审列表
    public static final String FETCH_REG_FOR_SOONAPPROVE = "payTaskQuickAudit/fetchRegForSoonApprove";          //获取注册证
    public static final String SUBMIT_SOONAPPROVE = "payTaskQuickAudit/submitSoonApproveNew";                   //选择注册证准备支付
    public static final String FETCH_SOONAPPROVE_DETAIL = "payTaskQuickAudit/fetchSoonApproveDetail";           //获取快审任务详情


    //*********** 4电话服务*********/
    public static final int SERVICE_REQUESET_SUCCEED = 200;                                                     //请求成功
    public static final String FETCH_HOTLINE_SERVICE_LIST = "phoneservice/fetchHotLineServiceList";             //获取电话服务列表
    public static final String APPLY_HOTLINE_SERVICE_ORDER = "phoneservice/applyHotLineService";                //提交电话服务
    public static final String CANCEL_SERVICE_APPLYMENT = "paydata/cancelServiceApplyment";                     //取消支付单
    public static final String FETCH_SERVICE_PAYMENT_DETAIL = "phoneservice/fetchServicePaymentDetail";         //电话服务详情
    public static final String CHECK_USER_AUTORITY_FOR_SERVICE = "phoneservice/checkUserAutorityForServiceNew";//判断是否可以添加服务


    //*********** 5在线答疑*********/
    public static final String PDS_FETCH_ONLINE_QALIST = "/fetchOnlineQAList";                                  //在线答疑
    public static final String PDS_FETCH_ALL_ONLINE_QALIST = "/fetchAllAnsweredQAList";                         //在线答疑列表查询
    public static final String PDS_FETCH_ONLINE_QADETAIL = "/fetchOnlineQADetail";                              //答疑详情
    public static final String PDS_ASK_QUESTION = "/askQuestion";                                               //在在线答疑-提问


    //*********** 6订单查询*********/
    public static final String ORDER_MODIFY_READ_STATE = "/TrdUpdateOrderState";                                 //修改订单阅读状态
    public static final String ORDER_LIST_QUERY = "/fetchOrderInfo";                                             // 企业列表
    public static final String ORDER_DETAIL_QUERY = "/fetchOrderDetail";                                         // 企业详情 2.0
    public static final String ORDER_DETAIL_QUERY2 = "/fetchOrderDetailById";                                    // 企业详情 3.0
    public static final String ORDER_DETAIL_CONFIRM_DELIVERY = "/confirmShipment";                               // 企业详情 确认发货
    public static final String ORDER_DETAIL_FRAGMENT_NORMAL = "/fetchBarcodeStockList";                          // 企业备货 普通列表
    public static final String ORDER_DETAIL_FRAGMENT_BARCODE = "/barcodeStockUP";                                // 企业备货 普通列表 提交
    public static final String ORDER_DETAIL_FRAGMENT_UNBARCODE = "/unbarcodeStockUP";                            // 企业备货 非条码列表
    public static final String ORDER_DETAIL_DELIVERY_DETAIL = "/fetchShipmentDetailInfo";                        // 企业发货明细
    public static final String ORDER_DELETE_DELIVERY_DETAIL = "/deleteShipmentDetailInfo";                       // 企业发货明细 删除
    public static final String ORDER_DELETE_BARCODE_STOCKUP = "/scanningBarcodeStockUp";                         // 企业订单明细  扫码备货
    public static final String ORDER_DETAIL_CHECK_NUM = "/validateStockOverstep";                                // 企业订单详情 确认发货 检查Num
    public static final String ORDER_LIST_QUERY_HOSPITAL = "/fetchHospitalOrderInfo";                            // 医院列表
    public static final String ORDER_DETAIL_QUERY_HOSPOTAL = "/fetchHospitalOrderDetail";                        // 医院详情
    public static final String FETCHP_RODUCTINFO_HOSPITAL = "/fetchHospitalProductInfo";                         // 产品详情


    //*********** 7条码使用*********/
    public static final String USE_RECORD_URL = "/fetchBarcodeUsages";                                          //使用记录
    public static final String FETCH_PATIENT_INFO = "/fetchPatientInfo";                                        //患者信息


    //*********** 8企业报码*********/
    public static final String FETCH_STOCK_URL = "/fetchStockInfo";                                             //企业报码
    public static final String STOCK_DETAIL_INFO_URL = "/fetchStockDetailInfo";                                 //条码统计(报码明细)
    public static final String STOCK_SUBMIT_HISTORY = "/fetchStockSubmitHistory";                               //报码记录
    public static final String DELETE_STOCK_SUBMIT_HISTORY = "/deleteStockSubmitHistory";                       //报码记录
    public static final String FETCH_MAINBARCODE_ANDRULES = "/fetchMainBarcodeAndRules";                        //报码记录
    public static final String SUBMITBARCODESTOCK = "/submitBarcodeStock";                                      //报码入库
    public static final String FETCHSTOCKINFOBYID = "/fetchStockInfoById";                                      //根据企业报码Id获取报码(条码备货)
    public static final String FETCHSTOCKSUBMITHISTORYBYID = "/fetchStockSubmitHistoryById";                    //根据企业报码Id获取报码(条码备货)


    //*********** 9条码规则*********/
    public static final String FETCH_BARCODE_RULESLIST = "/fetchBarcodeRulesList";                              //条码规则列表
    public static final String FETCH_BARCODE_RULE_ITEM = "/fetchBarcodeRulesListById";                          //条码规则
    public static final String ADD_BARCODE_RULE = "/addBarcodeRule";                                            //条码规则新增
    public static final String UPDATE_BARCODE_RULE = "/updateBarcodeRule";                                      //条码规则修改
    public static final String DELETE_BARCODE_RULE = "/deleteBarcodeRule";                                      //条码规则删除
    public static final String FETCH_MANUFACTURE_LIST = "/fetchManufactureList";                                //获取生产企业

}
