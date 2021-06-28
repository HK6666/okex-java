package com.okex.okex.utils;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : huangkai @date : 2021/5/8 : 9:30 上午
 */
@Service
public class MailContent {
	//狗币涨跌提醒模版
	public String getDogeCoinInfo(String last, Double upPrice, Double downPrice){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		if (Double.parseDouble(last)>upPrice){
			String res =formatter.format(date)+"  已超过您设置的 "+upPrice+"美元警戒  "+"当前狗狗币价格：  "+ last+"美元";
			return res;
		}
		if (Double.parseDouble(last)<downPrice){
			String res =formatter.format(date)+"  已跌破您设置的 "+downPrice+"美元警戒  "+"当前狗狗币价格：  "+ last+"美元";
			return res;
		}
		return "系统错误";
	}
	//shib提醒模版
	public String getShibCoinInfo(String last, Double upPrice, Double downPrice){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		if (Double.parseDouble(last)>upPrice){
			String res =formatter.format(date)+"  已超过您设置的 "+upPrice+"美元警戒  "+"当前Shib币价格：  "+ last+"美元";
			return res;
		}
		if (Double.parseDouble(last)<downPrice){
			String res =formatter.format(date)+"  已跌破您设置的 "+downPrice+"美元警戒  "+"当前Shib币价格：  "+ last+"美元";
			return res;
		}
		return "系统错误";
	}
}
