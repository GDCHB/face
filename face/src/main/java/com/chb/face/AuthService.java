package com.chb.face;

/**
 * Created by bingege on 2018/4/14.
 */

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 获取token类
 */
public class AuthService {

    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * {"access_token":"24.4b503213cd3183637e9b426f73440cbe.2592000.1526309464.282335-11097687",
     * "session_key":"9mzdD0aLTEFi+yhPT\/3pko3NB3XStIBovUKF+Y0XcfJjv4\/mMTBBsvoBQONvMWxMOFosrATOHX48tBX3StGXc0UmUFSEmQ==",
     * "scope":"public vis-faceverify_faceverify vis-faceattribute_faceattribute vis-faceverify_faceverify_v2 vis-faceverify_faceverify_match_v2 brain_all_scope vis-faceverify_vis-faceverify-detect wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test\u6743\u9650 vis-classify_flower lpq_\u5f00\u653e cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base",
     * "refresh_token":"25.0718b6c17dde2918250c1ecc4d7913fe.315360000.1839077464.282335-11097687","session_secret":"c01990ef7f15209bc0673ffad2d01b79",
     * "expires_in":2592000}
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "o7GexAXD74kGvDdrLQelFagw";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "YfqGTCnNQVBhnoa9bkL4MG1dn9u28sU6";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

}
