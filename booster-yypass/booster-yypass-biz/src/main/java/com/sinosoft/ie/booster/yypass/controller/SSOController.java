package com.sinosoft.ie.booster.yypass.controller;


import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.service.FileService;
import com.sinosoft.ie.booster.yypass.service.PassBasicInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.List;

import net.sf.json.JSONObject;

/**
 *
 * aps_system
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Slf4j
@RestController
@Api(tags = "单点登录")
@RequestMapping("/SSOYypass")
public class SSOController {

	//系统id
	@Value("${appId}")
	private String appId;
	//系统key
	@Value("${key}")
	private String key;
	//重定向3A地址
	@Value("${redirectUrl}")
	private String redirectUrl;
	// 获取前端地址
	@Value("${frontUrl}")
	private String frontUrl;
	//分支应用token与3a认证 url地址
	@Value("${branchTokenUrl}")
	private String branchTokenUrl;
	@Autowired
	private PassBasicInfoService passBasicInfoService;


	@GetMapping("/login_yypass")
	@ResponseBody
	@ApiOperation(value = "单点登录，传入token。login_yypass?Token=XXXXX", notes = "单点登录，传入token。login_yypass?Token=XXXXX")
	public R gatewayToken(String token) throws IOException {
		//获取分支系统的url
//		String fzUrl = request.getParameter("url");
		//获取门户传过来的token
		//System.out.println(token);
		// R.ok("1006580073", "操作成功");
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			//获取服务器ip
			String address = token;
			URL realUrl = new URL(branchTokenUrl + address);
			System.out.println(realUrl);
			// ???URL?????
			URLConnection conn = realUrl.openConnection();
			// ?????????
			//conn.setRequestProperty("accept", "*/*");
			//conn.setRequestProperty("connection", "Keep-Alive");
			//conn.setRequestProperty("Content-Type", "application/json");
			//conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ??POST??????????
			//conn.setDoOutput(true);
			//conn.setDoInput(true);
			// ??URLConnection????????
			//out = new PrintWriter(conn.getOutputStream());
			// ??????
			//out.print(URLEncoder.encode("UTF-8"));
			// flush??????
			//out.flush();
			// ??BufferedReader??????URL???
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println(result + "rrrrr");
			//获取json里面的信息
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONObject userInfo = jsonObject.getJSONObject("userInfo");
			if ("0".equals(userInfo.getString("errCode"))) {
				JSONObject userInfos = userInfo.getJSONObject("userInfo");
				String identityId = userInfos.getString("identityId");
				String url = frontUrl + "identityId=" + identityId;
				// 从redis中取值
				//Cache cache = cacheManager.getCache(CacheConstants.AAA_USER_DETAILS);
				// 存储到redis中
				//cache.put(identityId, result);
				//将返回的参数重定向前端界面
				//response.sendRedirect(url);
				return R.ok(identityId, "操作成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(null, "认证失败");
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return R.failed(null, "认证失败");
	}


	/*
	 * 获取Linux系统IP地址
	 *
	 * @return IP
	 * @throws Exception
	 */
	private static String getLinuxLocalIp() throws Exception {
		String ip = "";
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
				NetworkInterface intf = en.nextElement();
				String name = intf.getName();
				if (!name.contains("docker") && !name.contains("lo")) {
					for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							String ipaddress = inetAddress.getHostAddress().toString();
							if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
								ip = ipaddress;
								System.out.println(ipaddress);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ip;
	}
	// md5加密
	public static String backMD5(String inStr) {

		MessageDigest md5 = null;

		try {

			md5 = MessageDigest.getInstance("MD5");

		} catch (Exception e) {

			System.out.println(e.toString());
			e.printStackTrace();

			return "";
		}

		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {

			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();

	}


	@PostMapping("/getList")
	@ResponseBody
	@ApiOperation(value = "查询出入证列表信息", notes = "查询出入证列表信息")
	public R getList(@RequestBody @Valid PassBasicInfoEntity passBasicInfoEntity){
		try {
			QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
			queryWrapper.eq("is_delete","1");

			if(org.apache.commons.lang.StringUtils.isNotEmpty(passBasicInfoEntity.getNames())){
				queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getNames,passBasicInfoEntity.getNames()));
			}
			if(org.apache.commons.lang.StringUtils.isNotEmpty(passBasicInfoEntity.getPhone())){
				queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getPhone,passBasicInfoEntity.getPhone()));
			}
			if(StringUtils.isNotEmpty(passBasicInfoEntity.getIdCard())){
				queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getIdCard,passBasicInfoEntity.getIdCard()));
			}
			if(StringUtils.isNotEmpty(passBasicInfoEntity.getCardNumber())){
				queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getCardNumber,passBasicInfoEntity.getCardNumber()));
			}
			List<PassBasicInfoEntity> list = passBasicInfoService.list(queryWrapper);
			return R.ok(list,"操作成功");
		}catch (Exception e){
			return R.failed(null,"操作失败");
		}
	}


}
