package com.cw.osp;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.vip.osp.sdk.context.ClientInvocationContext;
import com.vip.osp.sdk.exception.OspException;
import com.vip.vop.vcloud.order.FindSourceRes;
import com.vip.vop.vcloud.order.OccupiedOrderDo;
import com.vip.vop.vcloud.order.OccupiedOrderItemDo;
import com.vip.vop.vcloud.order.VdgOrderServiceHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author david.cai
 * @date 2019/11/5
 */
@Slf4j
public class VcloudTest {

    VdgOrderServiceHelper.VdgOrderServiceClient client = new VdgOrderServiceHelper.VdgOrderServiceClient();

    @Before
    public void init(){
        ClientInvocationContext clientInvocationContext = new ClientInvocationContext();
        clientInvocationContext.setAppURL("http://vp-gw.vip.com");
        clientInvocationContext.setAppKey("84ece1df");
        clientInvocationContext.setAppSecret("E083CAE756FDC6F4B1D3E3B3C9E451F3");
        client.setClientInvocationContext(clientInvocationContext);
    }

    @Test
    public void orderFindSource() throws OspException {
        List<OccupiedOrderItemDo> items = Lists.newArrayList();
        OccupiedOrderItemDo item = new OccupiedOrderItemDo();
        item.setBarcode("erpBarcode001");
        item.setQuantity(1);
        item.setSkuId(17720181225001L);

        items.add(item);

        OccupiedOrderDo order = new OccupiedOrderDo();
        order.setChannelId(10001L);
        order.setCityCode("104104101");
        order.setDistrictCode("104104101103");
        order.setParnterId(10000L);
        order.setProvinceCode("104104");
        order.setStreetCode("944101103101");
        order.setTransId(System.currentTimeMillis() + "");
        order.setItems(items);
        FindSourceRes findSourceRes = client.orderFindSource(order);

        log.info("findSourceRes:{}", new Gson().toJson(findSourceRes));
    }
}
