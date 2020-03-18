package com.cw.osp;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.vip.osp.sdk.context.ClientInvocationContext;
import com.vip.osp.sdk.exception.OspException;
import com.vip.vop.logistics.carrier.service.ReportPacketInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import vipapis.jitx.*;
import vipapis.logistics.CarrierLogisticsService;
import vipapis.logistics.CarrierLogisticsServiceHelper;

import java.util.Date;
import java.util.List;
@Slf4j
public class OspTest {

    JitXServiceHelper.JitXServiceClient client = new JitXServiceHelper.JitXServiceClient();

    @Before
    public void init(){
        ClientInvocationContext clientInvocationContext = new ClientInvocationContext();
        clientInvocationContext.setAppURL("http://sandbox.vipapis.com");
        clientInvocationContext.setAppKey("a876c4cc");
        clientInvocationContext.setAppSecret("77780A5819EC3CFBE648436DB9F95492");
        client.setClientInvocationContext(clientInvocationContext);
    }

    @Test
    public void getDeliveryOrders() throws OspException {
        try{
            GetDeliveryOrdersRequest request = new GetDeliveryOrdersRequest();
            request.setVendor_id(550);
            request.setStart_time(new Date().getTime() / 1000);
            request.setEnd_time(new Date().getTime() / 1000);
            request.setLimit(200);
            request.setPage(1);
            request.setStatus_list(Lists.newArrayList("NEW"));
            GetDeliveryOrdersResponse reuslt = client.getDeliveryOrders(request);
            log.info("reuslt: {}", new Gson().toJson(reuslt));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderLabel() throws OspException {
        try{
            PrintDetail detail = new PrintDetail();
            detail.setBox_no(1);
            detail.setCarrier_code("1");
            detail.setGoods_info(Lists.newArrayList("test"));
            detail.setOrder_sn("11");
            detail.setTotal_package(1);
            detail.setTransport_no("123");
            GetOrderLabelRequest request = new GetOrderLabelRequest();
            request.setVendor_id(550);
            request.setPrint_details(Lists.newArrayList(detail));

            List<OrderLabel> orderLabel = client.getOrderLabel(request);
            log.info("orderLabel: {}", orderLabel.get(0).getOrder_label());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getPrintTemplate() throws OspException {
        try{

            PrintDetail detail = new PrintDetail();
            detail.setBox_no(1);
            detail.setCarrier_code("1");
            detail.setGoods_info(Lists.newArrayList("test"));
            detail.setOrder_sn("11");
            detail.setTotal_package(1);
            detail.setTransport_no("123");
            GetOrderLabelRequest request = new GetOrderLabelRequest();
            request.setVendor_id(550);
            request.setPrint_details(Lists.newArrayList(detail));

            List<OrderLabel> orderLabel = client.getPrintTemplate(request);
            log.info("orderLabel: {}", new Gson().toJson(orderLabel));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getOrdersByOrderSn() throws OspException {
        try{
            GetOrdersByOrderSnRequest request = new GetOrdersByOrderSnRequest();
            request.setVendor_id(550);
            request.setOrder_sns(Lists.newArrayList("1234"));
            GetOrdersResponse orders = client.getOrdersByOrderSn(request);
            log.info("orders: {}", orders);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getWarehouse(){
        try {
            vipapis.jitx.GetWarehousesRequest request1 = new vipapis.jitx.GetWarehousesRequest();
            request1.setVendor_id(1317);
            System.out.println(new Gson().toJson(client.getWarehouses(request1)));
        } catch(com.vip.osp.sdk.exception.OspException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getTransportNo(){
        try {
            vipapis.jitx.GetTransportNosRequest request1 = new vipapis.jitx.GetTransportNosRequest();
            request1.setVendor_id(1558);
            request1.setOrder_sn("19022070451069");
            request1.setCarrier_code("yuantong");
            request1.setLimit(1);
            System.out.println(client.getTransportNos(request1));
        } catch(com.vip.osp.sdk.exception.OspException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getDeliveryOrdersForBelle() throws OspException {
        BelleDeliveryOrderRequest request = new BelleDeliveryOrderRequest();
        request.setVendor_id(550);
        request.setStart_time(new Date().getTime() / 1000);
        request.setEnd_time(new Date().getTime() / 1000);
        request.setLimit(200);
        request.setPage(1);
        request.setStatus_list(Lists.newArrayList("NEW"));
        request.setCooperation_nos(Sets.newHashSet("123456"));
        BelleDeliveryOrderResponse reuslt = client.getDeliveryOrdersForBelle(request);
        log.info("reuslt: {}", new Gson().toJson(reuslt));
    }

    @Test
    public void getOrderLabelForBelle() throws OspException {
        JitXServiceHelper.JitXServiceClient jitXService = new JitXServiceHelper.JitXServiceClient();
        ClientInvocationContext clientInvocationContext = new ClientInvocationContext();
        clientInvocationContext.setAppURL("http://sandbox.vipapis.com");
        clientInvocationContext.setAppKey("a876c4cc");
        clientInvocationContext.setAppSecret("77780A5819EC3CFBE648436DB9F95492");
        jitXService.setClientInvocationContext(clientInvocationContext);

        BellePrintDetail detail = new BellePrintDetail();
        detail.setBox_no(1);
        //detail.setCarrier_code("1");
        detail.setGoods_info(Lists.newArrayList("test"));
        detail.setOrder_sn("11");
        detail.setTotal_package(1);
        //detail.setTransport_no("123");
        detail.setDelivery_warehouse_code("");
        BelleOrderLabelRequest request = new BelleOrderLabelRequest();
        request.setVendor_id(550);

        request.setPrint_details(Lists.newArrayList(detail));

        List<OrderLabel> orderLabel = jitXService.getOrderLabelForBelle(request);
        log.info("orderLabel: {}", orderLabel.get(0).getOrder_label());
    }

    @Test
    public void getOrdersForBelle() throws OspException {
        JitXServiceHelper.JitXServiceClient jitXService = new JitXServiceHelper.JitXServiceClient();
        ClientInvocationContext clientInvocationContext = new ClientInvocationContext();
        clientInvocationContext.setAppURL("http://sandbox.vipapis.com");
        clientInvocationContext.setAppKey("a876c4cc");
        clientInvocationContext.setAppSecret("77780A5819EC3CFBE648436DB9F95492");
        jitXService.setClientInvocationContext(clientInvocationContext);

        BelleOrderRequest request = new BelleOrderRequest();
        request.setVendor_id(550);
        request.setCooperation_nos(Sets.newHashSet("123456"));
        request.setDelivery_warehouse_codes(Sets.newHashSet("1", "2"));
        request.setEnd_time(new Date().getTime() / 1000);
        request.setStart_time(new Date().getTime() / 1000);
        request.setLimit(10);
        request.setPage(1);
        request.setOrder_status(Lists.newArrayList("NEW"));

        BelleOrderResponse reuslt = jitXService.getOrdersForBelle(request);
        log.info("ordersForBelle: {}", new Gson().toJson(reuslt));
    }

    @Test
    public void getJitXOrdersForMutiSys() throws OspException {
        OrderRequest request = new OrderRequest();
        request.setVendor_id(550);
        request.setCooperation_nos(Sets.newHashSet("123456"));
        request.setDelivery_warehouse_codes(Sets.newHashSet("1", "2"));
        request.setEnd_time(new Date().getTime() / 1000);
        request.setStart_time(new Date().getTime() / 1000);
        request.setLimit(10);
        request.setPage(1);
        request.setOrder_status(Lists.newArrayList("NEW"));

        GetOrdersResponse jitXOrdersForMutiSys = client.getJitXOrdersForMutiSys(request);
        log.info("jitXOrdersForMutiSys: {}", new Gson().toJson(jitXOrdersForMutiSys));
    }

    @Test
    public void getDeliveryOrdersForMutiSys() throws OspException {
        MutilWarehousingOrderRequest request = new MutilWarehousingOrderRequest();
        request.setVendor_id(550);
        request.setStart_time(new Date().getTime() / 1000);
        request.setEnd_time(new Date().getTime() / 1000);
        request.setLimit(200);
        request.setPage(1);
        request.setStatus_list(Lists.newArrayList("NEW"));
        request.setCooperation_nos(Sets.newHashSet("123456"));
        MutilWarehousingOrderResponse deliveryOrdersForMutiSys = client.getDeliveryOrdersForMutiSys(request);
        log.info("deliveryOrdersForMutiSys: {}", new Gson().toJson(deliveryOrdersForMutiSys));
    }

    @Test
    public void createChangeWarehouseWorkflow() throws OspException {
        CreateWorkflow flow = new CreateWorkflow();

        CreateChangeWarehouseWorkflowReq request = new CreateChangeWarehouseWorkflowReq();
        request.setVendor_id(550);
        request.setWorkflows(Lists.newArrayList(flow));

        CreateWorkflowResp changeWarehouseWorkflow = client.createChangeWarehouseWorkflow(request);
        log.info("createChangeWarehouseWorkflow: {}", new Gson().toJson(changeWarehouseWorkflow));
    }

    @Test
    public void getChangeWarehouseWorkflows() throws OspException {
        GetChangeWarehouseWorkflowReq request = new GetChangeWarehouseWorkflowReq();
        request.setVendor_id(550);
        request.setStart_time(new Date().getTime() / 1000);
        request.setEnd_time(new Date().getTime() / 1000);
        request.setWorkflow_sns(Lists.newArrayList());
        request.setOrder_sns(Lists.newArrayList());
        request.setWorkflow_states(Lists.newArrayList());

        GetChangeWarehouseWorkflowResp changeWarehouseWorkflows = client.getChangeWarehouseWorkflows(request);
        log.info("getChangeWarehouseWorkflows: {}", new Gson().toJson(changeWarehouseWorkflows));
    }

    @Test
    public void reportPacketInfo() throws OspException {
        com.vip.osp.sdk.context.InvocationContext invocationContext=com.vip.osp.sdk.context.InvocationContext.Factory.getInstance();
        invocationContext.setAppKey("a876c4cc");
        invocationContext.setAppSecret("77780A5819EC3CFBE648436DB9F95492");
        invocationContext.setAppURL("http://vipapis.com/");
        invocationContext.setLanguage("zh");

        CarrierLogisticsService service = new CarrierLogisticsServiceHelper.CarrierLogisticsServiceClient();

        ReportPacketInfoResp a = service.reportPacketInfo("a", Lists.newArrayList());

    }
}
