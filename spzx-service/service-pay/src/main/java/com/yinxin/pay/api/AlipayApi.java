package com.yinxin.pay.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.yinxin.pay.appconfig.AlipayProperties;
import com.yinxin.pay.service.AlipayService;
import com.yinxin.pay.service.PaymentInfoService;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author YinXin
 * @date 2024-03-17 10:20
 */
@Tag(name = "购物车接口")
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/order/alipay")
public class AlipayApi {
    private final AlipayService alipayService;
    private final AlipayProperties alipayProperties;
    private final PaymentInfoService paymentInfoService;

    @Operation(summary = "支付宝下单")
    @ResponseBody
    @GetMapping("/submitAlipay/{orderNo}")
    public Result<String> submitAlipay(@PathVariable String orderNo) {
        String form = alipayService.submitAlipay(orderNo);
        return Result.build(form, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "支付宝异步回调")
    @ResponseBody
    @RequestMapping("callback/notify")
    public String alipayNotify(@RequestParam Map<String, String> paramMap, HttpServletRequest request) {
        log.info("AlipayController...alipayNotify方法执行了...");
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(paramMap, alipayProperties.getAlipayPublicKey(), AlipayProperties.charset, AlipayProperties.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        // 交易状态
        String trade_status = paramMap.get("trade_status");
        // true
        if (signVerified) {
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
                // 正常的支付成功，我们应该更新交易记录状态
                paymentInfoService.updatePaymentStatus(paramMap, 2);
                return "success";
            }
        } else {
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            return "failure";
        }
        return "failure";
    }
}
