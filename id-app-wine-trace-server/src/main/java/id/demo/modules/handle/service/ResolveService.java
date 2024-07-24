package id.demo.modules.handle.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.teleinfo.idhub.manage.doip.server.domain.DoipReturn;
import cn.teleinfo.idhub.manage.doip.server.enums.DoipOp;
import cn.teleinfo.idhub.sdk.client.OpenApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Service
@RequiredArgsConstructor
public class ResolveService {

    /**
     * 企业节点地址
     */
    private static String url = "https://idhub-manage-a-qa.idx.space/";

    /**
     * 应用标识身份
     */
    private static String handleUser = "88.608.24061301/App_12306";

    /**
     * 应用身份私钥
     */
    private static String privateKeyPem = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDKAZKtrFnsokJCA7urFQATZC3t\n" +
            "uWzWTcEJmfDeguGGsGSaNUnzFWKg0OpCydqQK429S2CJj1yMiYTPc81AISPCK9Qe9T7KJX0d0FM2\n" +
            "Y5R4qSrnQ+NmbpHRO4xiUzXvizNxJ84GhFLvn776gZWhfNtJKXN9/Grx6r53qJgRgy8APTsicCn8\n" +
            "28iQxPtSufiGQVKdPnypEOmtA4SkfXbQ7hJSponvLKkr+u5NxBqaP+hzdIDQS8j+ypn5XpEt/fH6\n" +
            "TrTOpDndok6aeFg6s7a0LG1A9fkyP8OVTvVKNVFbKj01VkY/XNssTtxHLEB3L+P2O8fRO03619KO\n" +
            "Qxe8Iak+aMAXAgMBAAECggEBAKA/MTGOuONMMI2X0oc8KIw5e2nLH7i9g7XvJbIOV4P2aEggn+Iz\n" +
            "A8OZF00WPckCk/UnV5SoYn2mFfJP5fHKmEhPZn2MhVvs81z0Rx6tTjmN0VgWdc1rR0eZGpthcTbu\n" +
            "0UchkYF/9R7cNa8Zny61EmvucwztcZUpaCEaYE/8JgvkHjMTUvHlZO6cvbYxVjeWxefswYUfVvhU\n" +
            "44U1qSF7/COMWRRgEx+xT5IFY1Iyje0sE5m83HsMbF/ZrpJiPoAqhGTz5fIezmTS5gsF80/Wlfd9\n" +
            "aKvrwyDU/zH/2+YF9WJ8443ChxXsEP4cDghu7rTpfOSVHCe5R4d1nidgU0nfiQkCgYEA5ivYgTuh\n" +
            "8gUP5QAQb0Z5WPkryruZ2bk7hGor6Pq+YKzJcoiNc+FqWGVpb1r2Zm4r+KbZyHi9kaylM+xx4g5t\n" +
            "nVkyn0EP46kR861crEBIoD+5p6Fo3kNPs5tkLXEgvV8SdAQ9FPiIwPwjuVLBttl9fF6454sO8tFG\n" +
            "TlgDZZxFVMsCgYEA4Kyf6XOiilrVCcgQFrbqUsihoA7+fWmAutx70i2D1Y6PcJzL2kou+TXwVLZ5\n" +
            "3fcSgq/rtJqhF/oMP/jUIdVU5jNTgNk5OYL13NByKANiU7VphW9QW9iIguXeSwly4iKMS0/UYwDv\n" +
            "vtE5o+5krUmwknjx95m55gNJ0k3D6cbtZGUCgYAnnN4rB6dMbsCYidVKQVe7D08RGg5VYUKz/E3I\n" +
            "T/cZzkLxOj0V93KE+hcWOhJz+HwK1DRhAx6lBpB8CjJjVFZbITHMJLdmV/+l720mm/2jzrPV/Lp+\n" +
            "nhkGcTTheTnIPaeAXgZhKJDonbxMNcju/ilRkT/DYbRV59xwuUcEYsuv1wKBgHzSKv2OCs2G7TjD\n" +
            "93xHHyPi5zh/XA/c33HxMLFyK/4nd7V+xxp9gF9gAiduQYJ508Tba74o8jrZ5phb667oUIScG1ZI\n" +
            "WKyE/4cwH1vvlV8gOIGzeBeu0xi2EKvrkfHkc8EblslyKb//iWBz/2buu7SEkRmYRFlvlkHKhn+I\n" +
            "zzapAoGBANoFIhR6flrYu4UHd7xOjwZURfeG1lWzN/mPLRqqVC62GXmRb5M4eFVqy8/OV7eNjER8\n" +
            "ynMjbPMMKswFJyloDh2X5INsieRW/amVNf3SMgUXMJPyLAzqQRw1RHUehN2cp3BPgFvlH2A9jdPe\n" +
            "tnq3UP4o0i0gZp/xY2ZKgihdnFSP\n" +
            "-----END PRIVATE KEY-----";


    private volatile static Map<Integer,String> metaHandleMap = new ConcurrentHashMap<>();

    private static Map<Integer,String> initMap = new ConcurrentHashMap<>();

    private static Map<String, Map<Integer, DoipReturn>> handleMap = new ConcurrentHashMap<>();


    public DoipReturn resolveEx(String handle,Integer id) {
        if (id == 8) {
            // 异步执行 resolve 方法
            OpenApiClient openApiClient = new OpenApiClient(url, handleUser, privateKeyPem);
            CompletableFuture.runAsync(() -> resolve(handle, id));
            DoipReturn doipReturn = openApiClient.getIntanceApi().get(handle, DoipOp.RETRIEVE.getName());
            return doipReturn;
        } else {
            // 正常同步执行 resolve 方法
            return resolve(handle, id);
        }
    }

    /**
     * 标识解析
     *
     * @param handle
     * @return
     */
    public DoipReturn resolve(String handle,Integer id)  {
        OpenApiClient openApiClient = new OpenApiClient(url, handleUser, privateKeyPem);
        Map<Integer,DoipReturn> doipReturnMap = new ConcurrentHashMap<>();
        try {
            if (ObjectUtil.isNotEmpty(handleMap.get(handle))){
                Map<Integer, DoipReturn> integerDoipReturnMap = handleMap.get(handle);
                DoipReturn doipReturn = integerDoipReturnMap.get(id);
                return initDoip(integerDoipReturnMap.get(id));
            }
            initMap();
            BeanUtil.copyProperties(initMap,metaHandleMap);
            // OpenApiClient openApiClient = new OpenApiClient(url, handleUser, privateKeyPem);
            // 执行递归方法
            retrieveAndProcess(handle,8,openApiClient,doipReturnMap);
            if (ObjectUtil.isEmpty(doipReturnMap))
                return new DoipReturn();
            handleMap.put(handle,doipReturnMap);
            return initDoip(doipReturnMap.get(id));
        }catch (Exception e) {
            initMap();
            BeanUtil.copyProperties(initMap,metaHandleMap);
            // OpenApiClient openApiClient = new OpenApiClient(url, handleUser, privateKeyPem);
            // 执行递归方法
            retrieveAndProcess(handle,8,openApiClient,doipReturnMap);
            if (ObjectUtil.isEmpty(doipReturnMap))
                return new DoipReturn();
            handleMap.put(handle,doipReturnMap);
            return initDoip(doipReturnMap.get(id));
        }

    }



    public static void retrieveAndProcess(String handleName,Integer key,OpenApiClient openApiClient,Map<Integer,DoipReturn> doipReturnMap) {
        try {
            if (key == 1 || key == 2 || key == 3 || key ==4 )
                System.out.println("");
            DoipReturn doipReturn = openApiClient.getIntanceApi().get(handleName, DoipOp.RETRIEVE.getName());
            System.out.println("*****************************");
            System.out.println(key + ":" + handleName);
            doipReturnMap.put(key, doipReturn);
            Object data = doipReturn.getData();
            metaHandleMap.remove(ObjectUtil.toString(key));
            if (ObjectUtil.isEmpty(data))
                return;
            JSONObject entries = JSONUtil.parseObj(data);
            String type = entries.getStr("type");
            JSONArray jsonArray = entries.getJSONArray("values");
            jsonArray.forEach(item -> {
                JSONObject temp = JSONUtil.parseObj(item);
                String value = temp.getStr("value").replace("[\"", "").replace("\"]", "");
                Object keyByValue = getKeyByValue(metaHandleMap, value);
                if (keyByValue != null) {
                    Integer keyd = Integer.parseInt(ObjectUtil.toString(keyByValue));
                    if (keyd == 1 || keyd == 2 || keyd == 3 || keyd ==4 )
                        System.out.println(doipReturn);
                    retrieveAndProcess(value, Integer.parseInt(ObjectUtil.toString(keyByValue)), openApiClient, doipReturnMap);

                }
            });
        }catch (Exception e) {

        }
    }


    public static Object getKeyByValue(Map<Integer, String> map, String value) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (value.contains(entry.getValue())) {
                return  entry.getKey();
            }
        }
        return null;
    }

    public DoipReturn initDoip(DoipReturn doipReturn) {

        JSONObject entries = JSONUtil.parseObj(doipReturn.getData());
        JSONArray jsonArray = entries.getJSONArray("values");
        Iterator<Object> iterator = jsonArray.iterator();
        initMap();
        BeanUtil.copyProperties(initMap,metaHandleMap);
        while (iterator.hasNext()) {
            JSONObject temp = JSONUtil.parseObj(iterator.next());
            String value = temp.getStr("value").replace("[\"", "").replace("\"]", "");
            Object keyByValue = getKeyByValue(metaHandleMap, value);
            if (keyByValue != null) {
                iterator.remove();
            }
        }
        entries.set("values",jsonArray);
        doipReturn.setData(entries);
        return doipReturn;
    }

    /**
     * 解析标识数据
     *
     * @param handle
     * @return
     */
    public Object resolve(String handle) {
        // 身份认证
        OpenApiClient apiClient = new OpenApiClient(url, handleUser, privateKeyPem);
        // 示例，解析标识 88.608.24061301/test
        DoipReturn doipReturn = apiClient.getIntanceApi().get(handle, DoipOp.RETRIEVE.getName());
        // 获取数据
        Object data = doipReturn.getData();
        // 打印
        System.out.println(data);

        return data;
    }

    public static void initMap(){
        initMap.put(1,"88.608.5288/META_5065a74efa");
        initMap.put(2,"88.608.5288/META_2c35ef1ff6");
        initMap.put(3,"88.608.5288/META_929769198b");
        initMap.put(4,"88.608.5288/META_2f23b2b600");
        initMap.put(5,"88.608.24061301/META_1390098d6e");
        initMap.put(6,"88.608.24061301/META_a424420589");
        initMap.put(7,"88.608.24061301/META_7b19f91810");
        initMap.put(8,"88.608.24061301/META_577949a1a3");
        initMap.put(9,"88.608.24061301/META_2b152a33d6");
        initMap.put(10,"88.608.24061301/META_136c8fbfba");
        initMap.put(11,"88.608.24061301/META_c07b5be71c");
        initMap.put(12,"88.608.24061301/META_ddad158660");
        initMap.put(13,"88.608.24061301/META_dd57da1bef");
        initMap.put(14,"88.608.24061301/META_b7576330a0");


    }

    public static Map<Integer,String> testMap(){
        Map<Integer,String> data = new HashMap<>();
        data.put(1,"88.608.5288/META_5065a74efa");
        data.put(2,"88.608.5288/META_2c35ef1ff6");
        data.put(3,"88.608.5288/META_929769198b");
        data.put(4,"88.608.5288/META_2f23b2b600");
        return data;
    }

    //    1   消费者扫码  88.608.5288/META_5065a74efa
//	   2   门店签收    88.608.5288/META_2c35ef1ff6
//	  3   经销商发货   88.608.5288/META_929769198b
//	   4  经销商收货    88.608.5288/META_2f23b2b600

//    1   消费者扫码  88.608.24061301/META_88171d43f9
//	   2   门店签收    88.608.24061301/META_459206e3f8
//	  3   经销商发货   88.608.24061301/META_f14efd94fa
//	   4  经销商收货    88.608.24061301/META_7adcf0dd44
//	   5  销售出库    88.608.24061301/META_1390098d6e
//	   6  生产入库   88.608.24061301/META_a424420589
//	   7 箱酒   88.608.24061301/META_7b19f91810
//	   8 瓶酒   88.608.24061301/META_577949a1a3
//	   9 包装过程管控  88.608.24061301/META_2b152a33d6
//	   10 酒体设计  88.608.24061301/META_136c8fbfba
//	   11 酿造   88.608.24061301/META_c07b5be71c
//	   12 制曲 88.608.24061301/META_ddad158660
//	   13 原料批次 88.608.24061301/META_dd57da1bef
//	   14 供应商 88.608.24061301/META_b7576330a0

}
