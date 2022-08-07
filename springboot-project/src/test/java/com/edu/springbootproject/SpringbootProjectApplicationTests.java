package com.edu.springbootproject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edu.springbootproject.dao.UserDao;
import com.edu.springbootproject.entity.User;
import com.edu.springbootproject.util.MD5;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class SpringbootProjectApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {

        ArrayList<User> list = new ArrayList<>();
        list.add(new User("1", "张三", "就"));
        list.add(new User("1", "张三", "是"));
        list.add(new User("1", "张三", "你"));
        list.add(new User("1", "张三", "了"));
        list.add(new User("2", "李四", "444"));

        ArrayList<User> usersList = new ArrayList<>();

        HashMap<User, User> hashMap = new HashMap<>();

        for (User user : list) {
            User temp = new User();
//            User1 user1 = new User1();

            temp.setUserId(user.getUserId());
            temp.setUserName(user.getUserName());
            if (hashMap.containsKey(temp)) {
                user.setUserPwd(hashMap.get(temp).getUserPwd() + "," + (user.getUserPwd()));
                hashMap.put(temp, user);
            } else {
                hashMap.put(temp, user);
            }
        }

        for (Map.Entry<User, User> entry : hashMap.entrySet()) {
            usersList.add(entry.getValue());
        }

        System.out.println("usersList = " + usersList);
    }

    @Test
    public void test() throws IOException {
        //合同编号是唯一
        String contractid = "026";

        JSONObject json = test2(contractid);
        System.out.println("合同发起返回结果：" + json);

        if (json.getInteger("code") == 1) {
            //下载合同文件到本地
            downFiles(json.getJSONArray("doc_last_urls"), "d:/" + contractid);
        }

    }

    public final void downFiles(JSONArray urls, String savePath) throws IOException {
        for (Object url : urls) {
            String s = (String) url;
            int index = s.lastIndexOf('/');
            String subUrl = s.substring(0, index);
            String documentId = s.substring(index + 1, s.length());
            downFile(subUrl, savePath, documentId);
        }
    }

    public final void downFile(String subUrl, String savePath, String documentId) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody body = new FormBody.Builder().add("documentId", documentId).build();


        String time = System.currentTimeMillis() + "";
        Request request = new Request.Builder().url(subUrl)
                .addHeader("appid", "pms").addHeader("time", time)
                .addHeader("sign", MD5.toMD5("pms" + "." + time + '.' + "450619cee49bab14"))
                .post(body).build();

        FileOutputStream fos = null;
        InputStream inputStream = null;
        try {
            Response execute = okHttpClient.newCall(request).execute();
            String header = execute.header("content-disposition");
            int res = header.indexOf("=");
            String downFileName = header.substring(res + 2, header.length() - 1);
            String fileName = savePath;
            if (!(savePath.endsWith("/") || savePath.endsWith("\\"))) {
                fileName += "/";
            }
            fileName += downFileName;
            File file = new File(fileName);
            file.getParentFile().mkdirs();
            fos = new FileOutputStream(file);
            System.out.println("保存文件至：" + fileName);
            inputStream = execute.body().byteStream();

            MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);

            int readLen = 0;
            byte[] buffer = new byte[2048];
            while ((readLen = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, readLen);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            fos.close();
        }
    }

    public void result(OutputStream os, InputStream inputStream) throws IOException {


    }


    @Test
    public JSONObject test2(String contractid) {

        OkHttpClient okHttpClient = new OkHttpClient();

//        MultipartBody.Builder builder = new MultipartBody.Builder();

        JSONObject xml_metadata = new JSONObject(true);
        xml_metadata.put("姓名", "小智");
        xml_metadata.put("cardname_ID", "360111198708080899");
        xml_metadata.put("year", "2018");
        xml_metadata.put("month", "01");
        xml_metadata.put("day", "01");

        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("contract_tlp_code", "987456");
        formBody.add("doc_metadata", xml_metadata.toJSONString());
        //设置合同ID
        formBody.add("contract_id", contractid);
        //设置合同名称参数
        formBody.add("contract_name", "测试合同");
        formBody.add("signer_code", "hngcfinance");

        //设置业务编码参数
        formBody.add("biz_id", UUID.randomUUID().toString().replaceAll("-", ""));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置业务时间参数
        formBody.add("biz_time", sdf.format(new Date()));
        FormBody body = formBody.build();

//        RequestBody requestBody = RequestBody.create(MediaType.parse(null),"\"requestBody数据体\"");

//        Request.Builder builder = new Request.Builder();
//        builder.url("http://10.213.120.211:8080/tosignserver/api/contract/start");
//        builder.addHeader("appid","pms");
//        String  time = System.currentTimeMillis()+"";
//        builder.addHeader("time", time);
//        builder.addHeader("sign", MD5.toMD5("pms"+"."+time+'.'+"450619cee49bab14"));
//        builder.post(body);
//        builder.build();


        String time = System.currentTimeMillis() + "";
        Request request = new Request.Builder().url("http://10.213.120.211:8080/tosignserver/api/contract/start")
                .addHeader("appid", "pms").addHeader("time", time)
                .addHeader("sign", MD5.toMD5("pms" + "." + time + '.' + "450619cee49bab14"))
                .post(body).build();

        Response execute = null;
        String str = null;
        try {
            execute = okHttpClient.newCall(request).execute();
            str = execute.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(str);
    }

    @Test
    public void test5() {

        String s = "http://10.213.120.211:8080/tosignserver/api/contract/start";
        System.out.println("截取 = " + s.substring(s.lastIndexOf("/")));
        String substring = s.substring(s.indexOf("/"));
        System.out.println("substring = " + substring);

        int index = s.lastIndexOf('/');
        System.out.println("s.substring(0,index) = " + s.substring(0, index));

        System.out.println("s.substring(0,index) = " + s.substring(index + 1, s.length()));

    }

    @Test
    public void test7() {
//        File file = new File("d:\\aaa.txt");
//        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
//        System.out.println("file.getParent() = " + file.getParent());
//        System.out.println("file.getPath() = " + file.getPath());
//        System.out.println("file.getName() = " + file.getName());
//        System.out.println("文件大小 = " + file.length());
//        System.out.println("file.isFile() = " + file.isFile());
//        if (file.exists()){
//            boolean delete = file.delete();
//            System.out.println("delete = " + delete);
//            System.out.println("文件已删除");
//            }


        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("a", "hello");
        hashMap1.put("c", "hello");
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("b", "world");
        hashMap1.putAll(hashMap2);
        System.out.println("hashMap1 = " + hashMap1);


    }

    @Test
    public void test8() {
        String str = "{\n" +
                "\t\"cmAccountInfo\": {\n" +
                "\t\t\"zjid\": \"100005600149942646\",\n" +
                "\t\t\"yhzh\": \"100005600149942646\",\n" +
                "\t\t\"instName\": \"1\"\n" +
                "\t}\n" +
                "}";

        JSONObject object = JSON.parseObject(str);
        String data = object.getString("cmAccountInfo");
        System.out.println("cmAccountInfo = " + data);


        String s = "{\n" +
                "\t\"zjid\": [\n" +
                "\t\t\"100005600149942646\",\n" +
                "\t\t\"100005600149942645\"\n" +
                "\t]\n" +
                "}";
        JSONObject json = JSON.parseObject(s);
        Integer integer = json.getInteger("code");
        JSONArray zjid = json.getJSONArray("zjid");
        System.out.println("zjid = " + zjid);
    }


    @Test
    public void test0() {
        String filePath = "D:\\demo\\a\\b\\";
        File file = new File(filePath);

        if (!file.exists()) {
            file.mkdirs();
        }

        String name = "aaa.txt";
        String urlName = filePath + name;
        file = new File(urlName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean delete = file.delete();
        System.out.println("delete = " + delete);
    }

    @Test
    public void test10() {

//        boolean mkdirs = file.getParentFile().mkdirs();
        String filePath = "D:\\demo\\a\\b";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            file.createNewFile();
            System.out.println("创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if (file.exists() && file.isFile()){
//            file.delete();
//        }
    }

    @Test
    public void test11() {

        Date date = new Date();
        String year1 = String.format("%tY", date);
        System.out.println("year = " + year1);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        System.out.println("year = " + year);
        int mon = calendar.get(Calendar.MONTH) + 1;
        System.out.println("mon = " + mon);
        int day = calendar.get(Calendar.DATE);
        System.out.println("day = " + day);


        Calendar calendar2 = Calendar.getInstance();
        String s = Integer.toString(calendar2.get(Calendar.YEAR));
    }

    @Test
    public void test12() {
        Calendar calendar = Calendar.getInstance();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("YEAR", Integer.toString(calendar.get(Calendar.YEAR)));
        hashMap.put("MONTH", Integer.toString(calendar.get(Calendar.MONTH) + 1));
        hashMap.put("DAY", Integer.toString(calendar.get(Calendar.DATE)));

        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("YEAR", Integer.toString(calendar.get(Calendar.YEAR)));
        hashMap1.put("MONTH", Integer.toString(calendar.get(Calendar.MONTH) + 1));
        hashMap1.put("DAY", Integer.toString(calendar.get(Calendar.DATE)));
//        JSONObject object = new JSONObject();
//        String s1 = object.toJSONString(hashMap1);
//        System.out.println("s1 = " + s1);

        JSONObject object2 = new JSONObject();
        object2.putAll(hashMap);
        object2.put("spouse_condition", JSONObject.toJSONString(hashMap1));
        System.out.println("object2.toJSONString() = " + object2.toJSONString());
    }

    @Test
    public void test13() {
        Calendar calendar = Calendar.getInstance();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("YEAR", Integer.toString(calendar.get(Calendar.YEAR)));
        hashMap.put("MONTH", Integer.toString(calendar.get(Calendar.MONTH) + 1));
        hashMap.put("DAY", Integer.toString(calendar.get(Calendar.DATE)));

        JSONObject object1 = new JSONObject();
        object1.put("YEAR", Integer.toString(calendar.get(Calendar.YEAR)));
        object1.put("MONTH", Integer.toString(calendar.get(Calendar.MONTH) + 1));
        object1.put("DAY", Integer.toString(calendar.get(Calendar.DATE) + 1));

        JSONObject object2 = new JSONObject();
        object2.putAll(hashMap);
        object2.put("spouse_condition", object1);
        String s = object2.toJSONString();
        System.out.println("s = " + s);
    }

    @Test
    public void test14() {
        // 构建一个测试list 里面存放了一些map 这些map有相同key 也有不相同的key key的数量也不相等
        // 期望 将list里的每个map中的键值对放到一个大的map里 如果key相同 key的名称自增加1
        List<Map<String, Object>> list = buildTestList();
        // 定义一个大的map 用来存放这些键值对
        Map<String, Object> bigMap = new HashMap<>();
        // 定义一个map 用来计数 用来统计相同的key出现的次数
        Map<String, Integer> countMap = new HashMap<>();
        // 开始遍历列表
        for (Map<String, Object> map : list) {
            // 遍历map
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey(); // (String) object3
                Object value = entry.getValue(); // reportset.get(object3)
                String newKey = "";
                // 将该 key value放入到 大的map中
                // 关键点 先去countMap中查下 有没有这个key 没有这个key就放到countMap中并将值设为1 如果有这个key 就将值加1
                if (countMap.containsKey(key)) {
                    Integer count = countMap.get(key) + 1;
                    countMap.put(key, count);
                    newKey = key + count;
                } else {
                    countMap.put(key, 1);
                    newKey = key + 1;
                }
                bigMap.put(newKey, value);
            }
        }
        for (Map.Entry<String, Object> entry : bigMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }

    public static List<Map<String, Object>> buildTestList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("a", "1");
        map1.put("b", "2");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("a", "1");
        map2.put("b", "2");
        map2.put("c", "3");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("a", "1");
        map3.put("b", "2");
        Map<String, Object> map4 = new HashMap<>();
        map4.put("a", "1");
        map4.put("b", "2");
        map4.put("c", "3");
        map4.put("d", "4");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        return list;
    }

    @Test
    public void test15() {
        // 构建一个测试list 里面存放了一些map 这些map有相同key 也有不相同的key key的数量也不相等
        // 期望 将list里的每个map中的键值对放到一个大的map里 如果key相同 key的名称自增加1
        List<Map<String, Object>> list = TestList();
        // 定义一个大的map 用来存放这些键值对
        Map<String, Object> bigMap = new HashMap<>();
        // 定义一个map 用来计数 用来统计相同的key出现的次数
        Map<String, Integer> countMap = new HashMap<>();
        // 开始遍历列表
        int i = 0;
        for (Map<String, Object> map : list) {
            i++;

            // 遍历map
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey(); // (String) object3
                Object value = entry.getValue(); // reportset.get(object3)
                String newKey = "";
                if (i != 1) {
                    // 将该 key value放入到 大的map中
                    // 关键点 先去countMap中查下 有没有这个key 没有这个key就放到countMap中并将值设为1 如果有这个key 就将值加1
                    if (countMap.containsKey(key)) {
                        Integer count = countMap.get(key) + 1;
                        countMap.put(key, count);
                        newKey = key + count;
                    } else {
                        countMap.put(key, 1);
                        newKey = key + 1;
                    }
                    bigMap.put(newKey, value);
                } else {
                    bigMap.put(key, value);
                }
            }
        }
        for (Map.Entry<String, Object> entry : bigMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }

    public static List<Map<String, Object>> TestList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map4 = new HashMap<>();
        map4.put("a", "1");
        map4.put("b", "2");
        map4.put("c", "3");
        map4.put("d", "4");
        list.add(map4);
        Map<String, Object> map5 = new HashMap<>();
        map5.put("a", "5");
        map5.put("b", "6");
        map5.put("c", "7");
        map5.put("d", "8");
        list.add(map5);
        Map<String, Object> map6 = new HashMap<>();
        map6.put("a", "9");
        map6.put("b", "10");
        map6.put("c", "11");
        map6.put("d", "12");
        list.add(map6);
        Map<String, Object> map7 = new HashMap<>();
        map7.put("a", "9");
        map7.put("b", "10");
        map7.put("c", "11");
        map7.put("d", "12");
        list.add(map7);
        return list;
    }

    @Test
    public void test16() {
        ArrayList<User> list = new ArrayList<User>();
        HashMap<String, String> returnMap = new HashMap();

        // 定义一个大的map 用来存放这些键值对
        Map<String, Object> bigMap = new HashMap<>();
        // 定义一个map 用来计数 用来统计相同的key出现的次数
        Map<String, Integer> countMap = new HashMap<>();
        User user = new User("1001", "大王", "1998");
        User user1 = new User("1002", "二王", "1997");
        User user2 = new User("1003", "小王", "1996");
        list.add(user);
        list.add(user1);
        list.add(user2);
        int i = 0;
        for (User user3 : list) {
            i++;
            returnMap.put("WTRXM", user3.getUserName());
            returnMap.put("ZJLX", user3.getUserId());
            returnMap.put("ZJBH", user3.getUserPwd());
            for (Map.Entry<String, String> entry : returnMap.entrySet()) {
                String key = entry.getKey(); // (String) object3
                Object value = entry.getValue(); // reportset.get(object3)
                String newKey = "";
                if (i != 1) {
                    // 将该 key value放入到 大的map中
                    // 关键点 先去countMap中查下 有没有这个key 没有这个key就放到countMap中并将值设为1 如果有这个key 就将值加1
                    if (countMap.containsKey(key)) {
                        Integer count = countMap.get(key) + 1;
                        countMap.put(key, count);
                        newKey = key + count;
                    } else {
                        countMap.put(key, 1);
                        newKey = key + 1;
                    }
                    bigMap.put(newKey, value);
                } else {
                    bigMap.put(key, value);
                }
            }
        }
        for (Map.Entry<String, Object> entry : bigMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }


    @Test
    public void test17() {
        Map<String, Object> map = userDao.selectMap(2);
        System.out.println("map = " + map);
        Object realname = map.get("user_realname");
        Object xtzxmbh = map.get("XTZXMBH");
        System.out.println("xtzxmbh = " + xtzxmbh);
        System.out.println("realname = " + realname);
    }

    @Test
    public void test18() {
        String a1 = "aaa";
        String a2 = "111";
        String result1 = StringUtils.isBlank(a1) ? a2 : " ";
        System.out.println("result1 = " + result1);
    }

    @Test
    public void test19() {
        Map<String, Integer> countMap = new HashMap<>();

        HashMap<String, String> returnMap = new HashMap();

        HashMap<String, Object> bigMap = new HashMap<>();

        HashMap<String, Integer> newCountMap = new HashMap<>();
        countMap.put("WTRXM", 0);
        countMap.put("ZJLX", 0);
        countMap.put("ZJBH", 0);
        String str = " ";
        int i = 1;
        for (int j = 0; j < 3 - i; j++) {
            for (Map.Entry<String, Integer> countEntry : countMap.entrySet()) {
                String key = countEntry.getKey();
                Integer value = countEntry.getValue();
                returnMap.put(key + value, str);
                if (j == 0) {
                    newCountMap.put(key + value, value);
                }
            }
            // 处理多个受益人,动态设置key
            for (Map.Entry<String, String> entry : returnMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                String newKey = "";
                // 将该key value放入到总的map中
                // 先去countMap中查下有没有这个key,没有这个key就放到countMap中并将值设为1,如果有这个key,就将值加1
                if (newCountMap.containsKey(key)) {
                    Integer count = newCountMap.get(key) + 1;
                    newCountMap.put(key, count);
                    String substring = key.substring(0, key.length() - 1);
                    newKey = substring + count;
                } else {
                    newCountMap.put(key, 1);
                    newKey = key + 1;
                }
                bigMap.put(newKey, value);
            }
        }
        System.out.println("bigMap = " + bigMap);
    }

    @Test
    public void test20() {

        int i = 0;

        for (int j = 0; j < 3 - i; j++) {

            System.out.println("第几次" + j);

        }

    }

    @Test
    public void test21() {
        double tepValue = 0.21;
        double tepValue2 = 1.21;
        double tepValue3 = 1.21222;

        DecimalFormat df = new DecimalFormat("0.00%");
        String value = df.format(tepValue);
        System.out.println("0.21转换后的结果 = " + value);

        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(3);
        numberFormat.setMaximumIntegerDigits(2);
    }

    @Test
    public void test22() {
        ArrayList<User> list = new ArrayList<User>();
        HashMap<String, String> returnMap = new HashMap();

        // 定义一个大的map 用来存放这些键值对
        Map<String, Object> bigMap = new HashMap<>();
        // 定义一个map 用来计数 用来统计相同的key出现的次数
        Map<String, Integer> countMap = new HashMap<>();
        User user = new User("1001", "大王", "1998");
        User user1 = new User("1002", "二王", "1997");
        User user2 = new User("1003", "小王", "1996");
        list.add(user);
        list.add(user1);
        list.add(user2);
        int i = 0;
        String str = " ";
        for (User user3 : list) {
            i++;
            returnMap.put("WTRXM", user3.getUserName());
            returnMap.put("ZJLX", user3.getUserId());
            returnMap.put("ZJBH", user3.getUserPwd());
            for (Map.Entry<String, String> entry : returnMap.entrySet()) {
                String key = entry.getKey(); // (String) object3
                Object value = entry.getValue(); // reportset.get(object3)
                String newKey = "";
                // 将该 key value放入到 大的map中
                // 关键点 先去countMap中查下 有没有这个key 没有这个key就放到countMap中并将值设为1 如果有这个key 就将值加1
                if (countMap.containsKey(key)) {
                    Integer count = countMap.get(key) + 1;
                    countMap.put(key, count);
                    newKey = key + count;
                } else {
                    countMap.put(key, 0);
                    newKey = key;
                }
                bigMap.put(newKey, value);
            }
        }

        HashMap<String, String> newCountMap = new HashMap<>();

        if (i < 3) {
            for (Map.Entry<String, Integer> countEntry : countMap.entrySet()) {
                String key = countEntry.getKey();
                Integer value = countEntry.getValue();
                newCountMap.put(key, str);
//                if (j == 0) {
//                    newCountMap.put(key + value, value);
//                }
            }
            for (int j = 0; j < 3 - i; j++) {
                // 处理多个受益人,动态设置key
                for (Map.Entry<String, String> entry : newCountMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    String newKey = "";
                    // 将该key value放入到总的map中
                    // 先去countMap中查下有没有这个key,没有这个key就放到countMap中并将值设为1,如果有这个key,就将值加1
                    if (countMap.containsKey(key)) {
                        Integer count = countMap.get(key) + 1;
                        countMap.put(key, count);
//                    String substring = key.substring(0, key.length() - 1);
                        newKey = key + count;
                    } else {
                        countMap.put(key, 1);
                        newKey = key + 1;
                    }
                    bigMap.put(newKey, value);
                }
            }
        }



        for (Map.Entry<String, Object> entry : bigMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }

    @Test
    public void test23() {
        JSONObject object = new JSONObject();
        object.put("id","1001");
        object.put("idProject","1002");

        System.out.println("object.getString(\"id\") = " + object.getString("id"));
        System.out.println("object = " + object);
    }

    @Test
    public void test24() {
        String a = "111*222";
        if (!a.contains("3")){
            System.out.println("contains = " + true);
        }else {
            System.out.println("contains = " + false);
        }

        BigDecimal bi1 = new BigDecimal(1);
        BigDecimal bi2 = new BigDecimal(4);
        BigDecimal divide = bi1.divide(bi2, 4, RoundingMode.HALF_UP);
        System.out.println("divide = " + divide);
    }

    @Test
    public void test25(){
        int num = 100;
        System.out.println("num = " + num);
        int arr[] = {1,2,3};
        arr[0] = 4;

        test26();
        System.out.println("结束");
    }

    public static void test26(){
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i);
        }
    }
}
