package com.library.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id ="2021000119662524";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDBG9h7I60QhwjqluXtCtGHK8mjJeRNRIUUxggCw7WF27tRXXGvBy1m4jJOsxfUtZNe6swvZrCu6KDs2QPb3R7mHvdBQrCxZZJk6USzm3p5XhPHBI/7QrbmiimgyccrxYDSIH6XHyq+3E3togfSMHxh3/V2jH1KdqVQdZqhLsw9Hui4f+RpZa0HUYgwF9n+jHUsBnLLeGbRdVcqsLKsYcM1XRwAZGzbKHWuUAYRtyygt7p5njBrdwtLTx5F1gVObHKi9M8YpArKdNLKjIU0UWwm5iHA8OKGGUan+HfNIaHQOYkeWD1UYtO+15dXrSg01i9XQz149gMyNjY6NOcPa/EPAgMBAAECggEAR7UfXhqRWWHLXdFK8ArkY11PMtYPQOIAyQYAU4Z5EYvWsxAP9v6jTWERbUfzwXjrkIN/7tojIdAgcdLkK/CgWLOG/STWmAkvo2k7qbZCdG8KyQxYquTLJjCH5L0IyKg+fcizOUrJhLYp+eQBI3v9tBqF+8+Pduc32/jPwqquDMOYHNaI5bX2pMlYn4y37Dk8V1Lq2gj3LtdcLmnavsTH8O+LYUoipIB8H6v7+Jb2QyzKhbKro6YaeAoXxZA9QgK8AwJCHJBtgzRG89/AebnnjrvNfjnkLlHpF3inykRyfFNbHFbZ3KfVK4c3SZDpDhvWPnjhPg0+CMh0JuLKRMg+gQKBgQDr4RTZWmTzcs4RU8AGdQLIOO9j8pWtjcncuJXfvun4o9cFYwDcrHO7AWjo/Z/APAEhJ//MGh3VlM1tqbdWAh5GJt0Dwro6WelvYswDIysoVbw8N1INaY7nTXu745HvipzEMYmOQMnEsNak+djjfIA3mhNQttIff4yUv38mtziclQKBgQDRlMgO+B/mkv+0XmQIzC0u4Ray0EL8AFxswHlRaAC5rWJx/5/G5ctxy55H8xJi3ISijLmk4eihSGI3xEMClofBtzAlOcdfdxb6oWlyJWfkdmi3UKdkFsD+0B4mwjdeG89aODCTRLoHRu48FAXkdPXRFmag8h/kI7LrivaJ5pCKEwKBgHRY03Xadoz2hdpaGw+US8kC/GGqWFYA6B7tFVAF1VaFShwoF1bB4VvOeDbkUxX5Av2dwzijcS/Wc/WxjvLnnJMxvDaMqFr72n7MlqC9ih4dG/n/t4NSG+DPgUbOGz+yeVz0qkr2fg98woMhke+zi3LhlsFrS/2pZmhAhLFOEqh5AoGAUHTEZHK+lF6kSVLfJj1zrwZ8F0M6I9HjqWAucqDxflZgb5EJfmqjMllPtyFKUqeDR3+smO6cxqWqBuySKQEutqAjnHbU4gIu0SJ7/3T6sEsAojcVO6g8iNXObWSrqrYUYPcnNrHwlHCXbnrlVDHOWGk4Fts8gPFvc1sSkBfyLv8CgYEApPCQJ9IzbMsnZVVE6o28YoYP9dBkGycigUkOZkwASSDBKBkVtw02v/+OAwXX8zFVVNTbP4HE7tmEWT5asapSBm0vUfzd2pbY+UCMnmDej1CgunpXRiZTbxnR538vpvRbDZNXfIpwFuAim8qioD3TAneA53iB7j4QlDUvVBwtu3A=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzlF2N5nvk6YE0z9k8jHShHY+YPjNOnntAynqStlaZsiI1Q7p0Qwo1atDkm28F3pktBskM/vo9+tjji4OofsJxMxZ055LCjZh3okMcXdKnK9OpfcHBAFGmGTSz5Ij+T19V8zNr9BuMC2UKYIv+Km4KaJGwbPV2fGrYndlbVhyc+L2idM5x1JJWvPHJldBDLXCY2VWtskzyDtfGv79C61QXxGjwZFrreFX8iFisRzLUETBVTC3q08c2tZByBC404ZKcM4QbvJI/DKJ2KnE/llYGTvyWPADYaXyhdyCm87d5NtIZG892QoHMc5Kf+rezs5HCxixDWY6+b0oQyYlH3c25wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 需要放在服务器上面使用，现在还没完成，支付成功后服务器无法获得支付成功的信息。
    public static String notify_url = "http://103.46.128.21:29069/result";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问  return_url.jsp
    public static String return_url ="http://103.46.128.21:29069/result";

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
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
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
