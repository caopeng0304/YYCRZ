package com.sinosoft.ie.booster.yypass.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SmsSendAPI {

	private static final String SMS_USERNAME = "bdbwb";
	private static final String SMS_PASSWORD = "123456amtf";
	private static final String SMS_EPID = "120915";
	private static final String SMS_URL = "http://api.sms.95ytx.com:9088/mxt/send/";

	public static void main(String args[]) throws Exception{
		String sms = smsNotice("19945813773","aaa");
		System.out.println(sms);
	}

	public static String smsNoticeFuntion(String url,
										  String username,
										  String password,
										  String epid,
										  String mobileId, String msgContent) {
		try {
			StringBuffer writerBuf = new StringBuffer();
			if (StringUtils.isNotEmpty(mobileId)) {
				//把请求参数打包成数组
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("username", username);
				sParaTemp.put("password", password);
				sParaTemp.put("phone", mobileId);
				sParaTemp.put("message", msgContent);
				sParaTemp.put("epid", epid); //企业ID
				sParaTemp.put("linkid", null);
				sParaTemp.put("subcode", null);

				/**发送短信*/
				String outputStr = SmsSendAPI.postFormLinkReportWithURLEncode(sParaTemp, "GB2312");
				System.out.println("短信发送内容："+outputStr);
				String requestURL = url + outputStr;
				System.out.println("requestURL:::"+requestURL);
				String resCode = SmsSendAPI.doGet(requestURL);
				System.out.println("短信信息："+resCode);
				String[] errMsg = {"提交成功", "参数不完整", "鉴权失败", "号码数量超过50条", "发送失败", "余额不足", "发送内容含屏蔽词"};
				if (resCode.indexOf("00") >= 0) {
					writerBuf.append("{\"success\":true}");
				} else {
					String msg = "";
					int c = Integer.parseInt(resCode);
					try {
						msg = errMsg[c];
					} catch (Exception e) {
						msg = "代码异常";
					}
					writerBuf.append("{\"success\":false,\"errMsg\":\"错误代码为：" + resCode + "，" + msg + "。\"}");
				}
			} else
				writerBuf.append("{\"success\":false,\"errMsg\":\"参数为空。\"}");

			return writerBuf.toString();
		}catch (Exception e){
			System.out.println("短信异常"+e);
			return e.getMessage();
		}
	}
	
	public static String smsNotice(String mobileId, String msgContent) throws Exception {
		StringBuffer writerBuf = new StringBuffer();
		if(StringUtils.isNotEmpty(mobileId)){
			//把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("username", SMS_USERNAME);
	        sParaTemp.put("password", SMS_PASSWORD);
	        sParaTemp.put("phone", mobileId);
			sParaTemp.put("message", URLEncoder.encode(msgContent, "UTF-8"));
			sParaTemp.put("epid", SMS_EPID); //企业ID
			sParaTemp.put("linkid", "");
			sParaTemp.put("subcode", "");

			/**发送短信*/
			String outputStr = SmsSendAPI.postFormLinkReportWithURLEncode(sParaTemp, "GB2312");
			String requestURL = SMS_URL+outputStr;
			//System.out.println("requestURL:::"+requestURL);
			String resCode = SmsSendAPI.doGet(requestURL);
			String[] errMsg={"提交成功","参数不完整","鉴权失败","号码数量超过50条","发送失败","余额不足","发送内容含屏蔽词"};
            if(resCode.indexOf("00") >= 0){
            	writerBuf.append("{\"success\":true}");     
            }else{
            	String msg="";
            	int c=Integer.parseInt(resCode);
            	try {
					msg=errMsg[c];
				} catch (Exception e) {
					msg="代码异常";
				}
                writerBuf.append("{\"success\":false,\"errMsg\":\"错误代码为："+resCode+"，"+msg+"。\"}");
            }
		}
		else
			writerBuf.append("{\"success\":false,\"errMsg\":\"参数为空。\"}");
	
		return writerBuf.toString();	 
	}
	
    private static String postFormLinkReportWithURLEncode(Map<String,String> dataMap,String charset){
        if(dataMap == null) return "";
        StringBuilder reportBuilder = new StringBuilder();
        List<String> keyList = new ArrayList<String>(dataMap.keySet());
        Collections.sort(keyList);
        for(String key : keyList){
            try{
            	reportBuilder.append(key+"="+ URLEncoder.encode(dataMap.get(key),charset)+"&");
            	//reportBuilder.append(key+"="+ dataMap.get(key)+"&");
            }catch (Exception ex){
                //ignore to continue
                continue;
            }
        }
        reportBuilder.deleteCharAt(reportBuilder.lastIndexOf("&"));
        return reportBuilder.toString();
    }
    
	private static String doGet(String requestURL) throws Exception{
		StringBuffer msg = new StringBuffer();
		HttpURLConnection conn = null;
		OutputStream os = null;
		InputStream is = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferReader = null;
		try{
			URL url = new URL(requestURL);
			conn = (HttpURLConnection)(url.openConnection());
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type" , "text/html;charset=UTF-8" ); 

			is = conn.getInputStream();
			inputStreamReader = new InputStreamReader(is,"utf-8");
			bufferReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while((str=bufferReader.readLine())!=null){
				buffer.append(str);
			}
			msg.append(buffer);
		}catch(Exception e){
			System.out.println("短信异常"+e);
			msg.append(e.getMessage());
		}finally{
			if(null!=os){
				os.close();
			}
			if(null!=bufferReader){
				bufferReader.close();
			}
			if(null!=inputStreamReader){
				inputStreamReader.close();
			}
			if(null!=is){
				is.close();
				is=null;
			}
 		}
		return msg.toString();	
	}
}
