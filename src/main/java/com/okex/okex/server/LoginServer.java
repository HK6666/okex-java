package com.okex.okex.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.okex.dto.Data;
import com.okex.okex.dto.Price;
import com.okex.okex.utils.HmacUtil;
import com.okex.okex.utils.MailContent;
import com.okex.okex.utils.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : huangkai @date : 2021/5/6 : 5:55 下午
 */
@EnableScheduling
@Component
public class LoginServer {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MailServiceImpl mailService;
	
	@Value("${coin.dogecoin.upprice}")
	private double upPrice;
	
	@Value("${coin.dogecoin.downprice}")
	private double downPrice;
	
	@Value("${coin.shibcoin.upprice}")
	private double shibUpPrice;
	
	@Value("${coin.shibcoin.downprice}")
	private double shibDownPrice;
	
	@Value("${email}")
	private String email;
	
	@Value("{okex.key}")
	private String key;
	
	@Value("{okex.secretkey}")
	private String secretkey;
	
	@Value("{okex.passphrase}")
	private String passphrase;
	
	@Autowired
	private MailContent mailContent;
	/**
	 * 对接okex
	 * @return
	 */
	@Scheduled(fixedRate = 50000)
	public void Login() throws Exception {
		final Base64.Encoder encoder = Base64.getEncoder();
		String SIGN = "";
		//	获取对应格式时间戳
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		String TIMESTAMP = df.format(new Date());
		
		//加密sign
		SIGN = HmacUtil.sha256_HMAC(TIMESTAMP+"GET"+"/api/v5/account/balance", secretkey);
		String dogeUrl = "https://www.okex.com/api/v5/account/balance";
		
		//添加请求头
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("OK-ACCESS-KEY", key);
		requestHeaders.add("OK-ACCESS-SIGN", SIGN);
		requestHeaders.add("OK-ACCESS-TIMESTAMP", TIMESTAMP);
		requestHeaders.add("OK-ACCESS-PASSPHRASE", passphrase);
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);


		
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

		ResponseEntity<Price> dogeResponse = restTemplate.exchange(dogeUrl, HttpMethod.GET, requestEntity, Price.class);
		Price sttr = dogeResponse.getBody();
		//json反序列化
		String results = JSONArray.toJSONString(sttr.getData().get(0));
		Data data = JSONObject.parseObject(results, Data.class);
		System.out.println(data);
//		//输出货币价格
//		if (Double.parseDouble(data.getLast())> upPrice){
//			mailService.sendSimpleMail(email, "hk", mailContent.getDogeCoinInfo(data.getLast(), upPrice, downPrice));
//			//mailService.sendSimpleMail("986993998@qq.com", "hk", mailContent.getDogeCoinInfo(data.getLast(), upPrice, downPrice));
//		}
//		Date date = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//		System.out.println(formatter.format(date)+"     狗狗币价格:  "+data.getLast()+"美元");
		
/*
		//shib对接的v3
		String shibUrl = "https://www.okex.com/api/spot/v3/instruments/SHIB-USDT/ticker";
		
		ResponseEntity<Data> shibResponse = restTemplate.exchange(shibUrl, HttpMethod.GET, requestEntity, Data.class);
		Data shib = shibResponse.getBody();
		
		if (Double.parseDouble(shib.getLast())>shibUpPrice){
			mailService.sendSimpleMail(email, "SHIB价格提醒", mailContent.getShibCoinInfo(shib.getLast(), shibUpPrice, shibDownPrice));
		}
		
		System.out.println(formatter.format(date)+"     SHIB币价格: "+shib.getLast()+"美元");
		
	}*/
	}

}
