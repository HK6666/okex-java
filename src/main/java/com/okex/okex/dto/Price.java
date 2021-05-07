package com.okex.okex.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

/**
 * @author : huangkai @date : 2021/5/7 : 9:43 上午
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Price {
	List data;
}
