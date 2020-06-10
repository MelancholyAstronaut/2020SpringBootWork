package edu.aynu.qiuhaojie.utils;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {
    private static String neturl = "http://bookStore";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号 按照我文章图上的信息填写
    public static String app_id = "2016102400749649";

    // 商户私钥，您的PKCS8格式RSA2私钥  刚刚生成的私钥直接复制填写
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCNSF6xiG7WF3S9+57Ou9cz1sPqXwnwh3I/Y9ZjYI5TH2U9z3NFn4WqRldfmMj3PeObUreWQLWRAwbTkDHtRKHuZQZxNsmUV1xlfRA9llQODRhWrCB0yHB5OWAht19RWCmOC3sRZBAU9lEGxEa77MOljD06aa+LSK+x3X0cbfivC9p6/6cof5nYZj09AStb5zEBhQuH7eNWucYDQjNFh5FEQhnY8r9iyzizEDPRnbVhJ8C1gWA6YGoUxVzNGfDmHPYbNLolpvkhoSpSBpl02pg7tbjIA8Wed2zP4gSsdf2iKC95Ws1P5hwNTggJmQVLhoTmfFRsWmZwn475FvsgNX0zAgMBAAECggEANnhHYeT67nm+nZyMHqJXFvpfMFYR6/UcfAoO1uOcG7oH+2umwpn9G6fg+c9a88+dl/2tIDW0isMC98ApL5P9Zn72m+GGmDWSPuKXx1bU81HjJ09XeA7rJiGTyYv8KM13v/b3LL5KYPqSRKyZpMxkpcfKt2GVt3p/mnCNUs+nSD0Os/Q03ZMNh2Y231nauBAbSGZXqh2NrtIlxFSBdxeAwMzevPaRt+QtUDBAslYhIPXtjuximfP8Nl/nmE0ejDm+wq8i/qi7pV8VHBRTImxEPhKpALe604OZ5SlSYuvv/dl+eNwr+eb+6UacMUuSnSAMeuQkbmScykNctj3ywgMRAQKBgQD44kdVm3yfarZONQXxQu53MtNzQtZHp3QCCI3x1IO/Tyxy7RE8sRa1CMYgF1Ein0CPUxsuYR+2VKsUysStVX1wZCFf9YEHMe5RnSsI3dZnktr1+IwoSASrN/UIc+YgqMUPAXUX/hkyxp1lc651Eme3y3Rv53VlikvQVXV+xaNAswKBgQCRUn9ZbOo5qAnxGjtCWH1Tw9YUKCS7GXxetQuYAF62ynv1GZpBwTz8zBVSbWu75D/SEOO2UYLcb5/o6PdIb1EklmGfp1n9LnpbXUtYwZNyZr7eDQ0Nk0FEth3DpxYe1T4ALLuu6l2BAdGjxaEWqjmVYZ9zbDFa10UNyFoTrsIRgQKBgQCreiwv/Xs10y5cYAgCHF103x31Eh3VLLivFfnGnudPDlvPqyrvKAw/GxZ20dnjAtpWZaoaoGxbEab2Ima0Dmzo3PRpyxrlPC+5J5KY/09T+UjHpEHdFxGwblNzSXdLFT0Ag76Lk1UFOPcFCVkv7Ehtx84ns8+c8FYf6mGTwNQkJQKBgElAh3cBQrPbc194NITF7C6sGZ5Befph52Ip6nUtQpDrUj6DMFGS4XYgPWM2SscET/NIljARuF/7WGTkAboeeo1xzDXY+rB3nC+vwEo2EWfqSK3PcFP2dUD4d9pmv+y82qrl4ZlTA90CmLTv6rptBee5tNFOqTY8Ks++vUEMp0CBAoGBAMtgP5oiNQH0gBfurnwc9IT+QVoBDYzF/IMLuCMICv4xF5AaG8qzYOsXokh8mJmMksMVlusNBHqOlgPny7JWWROn+zrwI54lMNtOa18+U+MVinvw8fQXTIFH3kX1TDtoa/LJAGbq9AyAUKQso6WKMnt4MAQvwdEag3AgmHWIVqss";

    // 支付宝公钥,对应APPID下的支付宝公钥。 按照我文章图上的信息填写支付宝公钥，别填成商户公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlCunkp3JIT9l3xykJcCzvE7fsEpqmab7V7cN9UgnGgN50A6Al6xtLeA10bC97+QaTQkG+28+Hs3NVJ+kGV2+d1wrd+9p8QNaEfBeWH3pQ3sbVusKta+/rXKxR+56hv4o8bbjN9D0siFADk/HRBCrSWrFow/hgI2FrOTwel+R5UwwVUQI65n+9wMy+M9E/hvWG8ZHc4uNPsVYhS5UgfoepC/Dp1vk239W0/rp4Ey5cU4Z01WsNQHpH0xhquESgpCRiggmBBZnOSbNWG070rXw+yzdrL3i/3t1f7rJmTCmnxFrOIQwM7XX3URI1Fc8XUXT4tbmVYJIOVVMJJl4DbbNUQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String notify_url = neturl + "/alipay/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String return_url = "http://localhost:8080/cart/pay";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

