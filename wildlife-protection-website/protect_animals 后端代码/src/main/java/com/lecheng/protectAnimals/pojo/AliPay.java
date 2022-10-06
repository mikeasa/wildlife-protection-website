package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AliPay {
    private Integer id;//志愿活动编号
    private String WIDout_trade_no;//订单编号
    private String WIDtotal_amount = "20";//付款金额，必填
    private String WIDsubject = "志愿活动资金募集";//订单名称，必填
    private String WIDbody = "志愿活动资金募集";//商品描述，可空
}
