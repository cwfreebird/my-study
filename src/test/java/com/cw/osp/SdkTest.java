package com.cw.osp;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.vip.osp.sdk.context.ClientInvocationContext;
import com.vip.osp.sdk.exception.OspException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import vipapis.address.AddressService;
import vipapis.address.AddressServiceHelper;
import vipapis.address.FullAddress;
import vipapis.address.Is_Show_GAT;
import vipapis.jitx.GetDeliveryOrdersRequest;
import vipapis.jitx.GetDeliveryOrdersResponse;
import vipapis.jitx.JitXServiceHelper;

import java.util.Date;

/**
 * @author david.cai
 * @date 2020/3/18
 */
@Slf4j
public class SdkTest {
    //JitXServiceHelper.JitXServiceClient client = new JitXServiceHelper.JitXServiceClient();

    @Before
    public void init(){

    }

    @Test
    public void getDeliveryOrders() throws OspException {
        for (int i = 0; i < 10; i++){
            log.error("===================i : {}" , i);
            new Thread(new TestRunnable()).start();
        }

        try {
            Thread.sleep(20 * 60 * 1000);
            log.info("thread name {} , id {} runs", Thread.currentThread().getName(), Thread.currentThread().getId());
        } catch (Exception e) {
            log.error("error : {}", e);
        }
    }

    static class TestRunnable implements Runnable{
        @Override
        public void run() {
            JitXServiceHelper.JitXServiceClient client = new JitXServiceHelper.JitXServiceClient();

            ClientInvocationContext clientInvocationContext = new ClientInvocationContext();
            clientInvocationContext.setAppURL("http://sandbox.vipapis.com");
            clientInvocationContext.setAppKey("a876c4cc");
            clientInvocationContext.setAppSecret("77780A5819EC3CFBE648436DB9F95492");
            client.setClientInvocationContext(clientInvocationContext);

            GetDeliveryOrdersRequest request = new GetDeliveryOrdersRequest();
            request.setVendor_id(550);
            request.setStart_time(new Date().getTime() / 1000);
            request.setEnd_time(new Date().getTime() / 1000);
            request.setLimit(200);
            request.setPage(1);
            request.setStatus_list(Lists.newArrayList("NEW"));


            try {
                for (int i = 0; i < 1000000; i++) {
                    GetDeliveryOrdersResponse result = client.getDeliveryOrders(request);
                    log.info("result: {}", new Gson().toJson(result));
                }
            } catch (Exception e) {
                log.error("error : {}", e);
            }
        }
    }

    @Test
    public void getFullAddress() throws OspException {
        for (int i = 0; i < 150; i++){
            log.error("===================getFullAddress : {}" , i);
            new Thread(new AddressTestRunnable()).start();
        }

        try {
            Thread.sleep(20 * 60 * 1000);
            log.info("thread name {} , id {} runs", Thread.currentThread().getName(), Thread.currentThread().getId());
        } catch (Exception e) {
            log.error("error : {}", e);
        }
    }

    static class AddressTestRunnable implements Runnable{
        @Override
        public void run() {
            AddressServiceHelper.AddressServiceClient client = new AddressServiceHelper.AddressServiceClient();

            ClientInvocationContext clientInvocationContext = new ClientInvocationContext();
            clientInvocationContext.setAppURL("https://gw.vipapis.com");
            clientInvocationContext.setAppKey("da2f39f2");
            clientInvocationContext.setAppSecret("F8B240BFD609CFBAC9A78C5B67A77BB0");
            client.setClientInvocationContext(clientInvocationContext);

            /*try {
                for (int i = 0; i < 100; i++) {
                    FullAddress fullAddress = client.getFullAddress("914101101101", Is_Show_GAT.SHOW_GAT, false);
                    log.info("fullAddress: {}", new Gson().toJson(fullAddress));
                }
            } catch (Exception e) {
                log.error("error : {}", e);
            }*/
        }
    }
}
