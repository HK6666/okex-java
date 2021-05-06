package com.okex.okex.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author : huangkai @date : 2021/5/6 : 5:55 下午
 */
@Service
public class LoginServer {
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * 对接okex
	 * @return
	 */
	public String Login() throws Exception {
		
		final Base64.Encoder encoder = Base64.getEncoder();
		String KEY = "6e8f8aeb-141e-4af4-b199-5ce626ceb440";
		String SIGN = "";
		//	获取对应格式时间戳
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		String TIMESTAMP = df.format(new Date());

		String PASSPHRASE = "Qq123456789";
		String Secretkey = "81148C26EE2C960993092FC6B5BDD8E5";
		
		//加密sign
		SIGN = sha256_HMAC(TIMESTAMP+"GET"+"/api/v5/market/ticker",Secretkey);
		String url = "https://www.okex.win/api/v5/market/ticker?instId=DOGE-USD-SWAP";
		
		//添加请求头
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("OK-ACCESS-KEY", KEY);
		requestHeaders.add("OK-ACCESS-SIGN", SIGN);
		requestHeaders.add("OK-ACCESS-TIMESTAMP", TIMESTAMP);
		requestHeaders.add("OK-ACCESS-PASSPHRASE", PASSPHRASE);
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);


		
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		String sttr = response.getBody();
		return sttr;
	}
	private static String sha256_HMAC(String message, String secret) {
		String hash = "";
		final Base64.Encoder encoder = Base64.getEncoder();
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
			return encoder.encodeToString(bytes);
		} catch (Exception e) {
			System.out.println("Error HmacSHA256 ===========" + e.getMessage());
		}
		return hash;
	}
	private static String byteArrayToHexString(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toLowerCase();
	}


}
