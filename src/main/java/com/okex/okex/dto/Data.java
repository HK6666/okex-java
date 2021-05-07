package com.okex.okex.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author : huangkai @date : 2021/5/7 : 10:10 上午
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@lombok.Data
public class Data {
	String last; //产品金额
}
