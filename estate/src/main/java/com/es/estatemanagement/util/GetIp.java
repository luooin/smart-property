package com.es.estatemanagement.util;

import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

/**
 * {@用于抓取ip 地理位置信息}
 *
 * @author ahk
 * 辅助类的jar:jsoup-1.8.3.jar
 */
public class GetIp {


    /**
     * <h2>获取本机的外网ip地址</h2>
     *
     * @return
     */
    public static String getV4OrV6IP() {
        String ip = null;
        // 访问其他网站获取ip
        // 测试网站 https://ipw.cn/
        String test = "http://test.ipw.cn";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(test);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read);
            }
            ip = inputLine.toString();

        } catch (Exception e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (ip == null) {
            // 没有获取到ip 给默认ip
            ip = "120.229.119.86";
            System.out.println("获取网络IP地址异常, 赋值默认ip: 【{}】" + ip);
        }
        return ip;
    }


    /**
     * 获取ip属地
     *
     * @param ip
     * @return
     * @throws Exception
     */
    public static String getCityInfo(String ip) throws Exception {
        //获得文件流时，因为读取的文件是在打好jar文件里面，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("ip2region.db");
        Resource resource = resources[0];
        InputStream is = resource.getInputStream();
        File target = new File("ip2region.db");
        FileUtils.copyInputStreamToFile(is, target);
        is.close();
        if (StringUtils.isEmpty(String.valueOf(target))) {
            System.out.println("Error: Invalid ip2region.db file");
            return null;
        }
        DbConfig config = new DbConfig();
        DbSearcher searcher = new DbSearcher(config, String.valueOf(target));
        //查询算法
        //B-tree, B树搜索（更快）
        int algorithm = DbSearcher.BTREE_ALGORITHM;
        try {
            //define the method
            Method method;
            method = searcher.getClass().getMethod("btreeSearch", String.class);
            DataBlock dataBlock;
            if (!Util.isIpAddress(ip)) {
                System.out.println("Error: Invalid ip address");
            }
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            String ipInfo = dataBlock.getRegion();
            if (!StringUtils.isEmpty(ipInfo)) {
                ipInfo = ipInfo.replace("|0", "");
                ipInfo = ipInfo.replace("0|", "");
            }
            return ipInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 由于 ip 属地在国内的话，只会展示省份，而国外的话，只会展示国家。所以我们还需要对这个方法进行一下封装，得到获取 IP 属地的信息。
     *
     * @param ip
     * @return
     * @throws Exception
     */
    public static String getIpPossession(String ip) throws Exception {
        String cityInfo = GetIp.getCityInfo(ip);
        if (!StringUtils.isEmpty(cityInfo)) {
            cityInfo = cityInfo.replace("|", " ");
            String[] cityList = cityInfo.split(" ");
            if (cityList.length > 0) {
                // 国内的显示到具体的省
                if ("中国".equals(cityList[0])) {
                    if (cityList.length > 1) {
                        return cityList[1];
                    }
                }
                // 国外显示到国家
                return cityList[0];
            }
        }
        return "未知";
    }



}
