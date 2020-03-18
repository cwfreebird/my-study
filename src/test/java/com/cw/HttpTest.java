package com.cw;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Closeables;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.junit.Test;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author david.cai
 * @date 2019/12/25
 */
@Slf4j
public class HttpTest {
    HttpClient client = HttpClients.getDefault();

    @Test
    public void getIpProvince() throws Exception {
        DemoData ipProvince = getIpProvince("116.236.115.226");
    }

    public DemoData getIpProvince(String ip) throws Exception {

        HttpGet request = new HttpGet("http://m.ip138.com/ip.asp?ip=" + ip);
        DemoData demo = null;
        HttpResponse response = null;
        try {
            response = client.execute(request);
            StatusLine sl = response.getStatusLine();
            if (HttpStatus.SC_OK == sl.getStatusCode()) {
                HttpEntity entity = response.getEntity();

                java.io.BufferedReader rd = new java.io.BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                String sCurrentLine = "";
                String sTotalString = "";
                while ((sCurrentLine = rd.readLine()) != null) {
                    sTotalString += sCurrentLine + "\r\n";
                }
                //System.out.println(sTotalString);

                demo = extractText(ip, sTotalString);
                //log.info("ip:{}, province:{}", demo.getIp(), demo.getProvince());

                //return province;
            }
        }finally {
                if (response instanceof Closeable) {
                    try {
                        Closeables.close((Closeable)response, false);
                    } catch (IOException ioe) {
                        log.error("{}", ioe);
                    }
                }
            }
            return demo;
    }

    public static DemoData extractText(String ip, String inputHtml) throws Exception {
        StringBuffer text = new StringBuffer();
        Parser parser = Parser.createParser(new String(inputHtml.getBytes(),"UTF-8"), "UTF-8");

        String filterStr = "p";
        NodeFilter filter = new TagNameFilter(filterStr);
        // 遍历所有的节点
        NodeList nodes = parser.extractAllNodesThatMatch(filter);
        DemoData demo = new DemoData();
        //System.out.println(nodes.size()); //打印节点的数量
        for (int i=0 ;i < nodes.size();i++){
            Node nodet = nodes.elementAt(i);
            //System.out.println(nodet.getText());

            if (i == 1) {
                String html = new String(nodet.getChildren().asString().getBytes("UTF-8"));
                String province = html.substring(6, 9);
                String[] tmp = html.split(" ");
                String line = tmp[tmp.length - 1];

                demo.setIp(ip);
                demo.setProvince(province);
                demo.setLine(line);

                //return new String(nodet.getChildren().asString().getBytes("UTF-8")).substring(6, 9);
                //text.append(new String(nodet.getChildren().asString().getBytes("GBK")) + "\r\n");
            }
        }
        return demo;
    }

    @Test
    public void synchronousRead() throws Exception {
        String fileName = "D:\\Users\\david.cai\\Downloads\\IP.xlsx";
        String outFileName = "D:\\Users\\david.cai\\Downloads\\IP_PROVINCE.xlsx";
        List<DemoData> result = Lists.newArrayList();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<Object> list = EasyExcel.read(fileName).head(DemoData.class).sheet().doReadSync();
        int total = list.size();
        int i = 1;
        for (Object data : list) {
            DemoData demo = (DemoData) data;

            String ip = StringUtils.trimToEmpty(demo.getIp());
            log.info("{}/{},读取到数据:{}", i, total, demo.getIp());

            if (StringUtils.isBlank(ip)){
                break;
            }

            demo = getIpProvince(ip);
            result.add(demo);

            i++;
        }

        EasyExcel.write(outFileName, DemoData.class).sheet("模板").doWrite(result);

        // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
        /*List<Object> objects = EasyExcel.read(fileName).sheet().doReadSync();
        Map<String, String> mapping = Maps.newHashMap();
        for (Object data : objects) {
            Map<Integer, String> map = (Map) data;
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            String ip = StringUtils.trimToEmpty(map.get(0));
            log.info("读取到数据:{}", map.get(0));

            if (StringUtils.isBlank(ip)){
                break;
            }

            String ipProvince = getIpProvince(ip);
            mapping.put(ip, ipProvince);
        }*/
    }
}


