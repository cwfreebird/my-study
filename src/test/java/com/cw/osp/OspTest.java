package com.cw.osp;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.vip.osp.sdk.context.ClientInvocationContext;
import com.vip.osp.sdk.exception.OspException;
import com.vip.vop.cup.api.customs.QueryCustomsClearanceReq;
import com.vip.vop.cup.api.order.CreateOrderSnReq;
import com.vip.vop.cup.api.order.CreateOrderSnResponse;
import com.vip.vop.cup.api.order.DeliveryByUserReq;
import com.vip.vop.cup.api.order.DeliveryOrder;
import com.vip.vop.cup.api.pay.PayReq;
import com.vip.vop.cup.api.pay.QueryPayReq;
import com.vip.vop.logistics.ReportTraceResult;
import com.vip.vop.logistics.ShipmentTrace;
import com.vip.vop.logistics.ShipmentTraceList;
import com.vip.vop.logistics.carrier.service.ReportPacketInfoResp;
import com.vip.wpc.ospservice.vop.WpcVopOspServiceHelper;
import com.vip.wpc.ospservice.vop.request.WpcBrandListRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import vipapis.activity.ActivityServiceHelper;
import vipapis.activity.CheckInResult;
import vipapis.cup.customs.CustomsClearanceServiceHelper;
import vipapis.cup.order.OrderServiceHelper;
import vipapis.cup.pay.PayServiceHelper;
import vipapis.delivery.CreatePoDeliveryReq;
import vipapis.delivery.JitDeliveryServiceHelper;
import vipapis.jitx.*;
import vipapis.logistics.CarrierLogisticsService;
import vipapis.logistics.CarrierLogisticsServiceHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
@Slf4j
public class OspTest {

    JitXServiceHelper.JitXServiceClient client = new JitXServiceHelper.JitXServiceClient();
    OrderServiceHelper.OrderServiceClient cup = new OrderServiceHelper.OrderServiceClient();
    PayServiceHelper.PayServiceClient cupPay = new PayServiceHelper.PayServiceClient();
    CustomsClearanceServiceHelper.CustomsClearanceServiceClient customs = new CustomsClearanceServiceHelper.CustomsClearanceServiceClient();
    CarrierLogisticsServiceHelper.CarrierLogisticsServiceClient carrierLogisticsService = new CarrierLogisticsServiceHelper.CarrierLogisticsServiceClient();
    ActivityServiceHelper.ActivityServiceClient activityServiceClient = new ActivityServiceHelper.ActivityServiceClient();
    JitDeliveryServiceHelper.JitDeliveryServiceClient jitDeliveryServiceClient = new JitDeliveryServiceHelper.JitDeliveryServiceClient();
    WpcVopOspServiceHelper.WpcVopOspServiceClient wpcClient = new WpcVopOspServiceHelper.WpcVopOspServiceClient();
    @Before
    public void init(){
        ClientInvocationContext clientInvocationContext = new ClientInvocationContext();
        //clientInvocationContext.setAppURL("http://localhost:8080");
        //clientInvocationContext.setAppURL("http://sandbox.vipapis.com");
        clientInvocationContext.setAppURL("http://vipapis.com");
        clientInvocationContext.setAppKey("a876c4cc");
        clientInvocationContext.setAppSecret("77780A5819EC3CFBE648436DB9F95492");
        client.setClientInvocationContext(clientInvocationContext);
        cup.setClientInvocationContext(clientInvocationContext);
        cupPay.setClientInvocationContext(clientInvocationContext);
        customs.setClientInvocationContext(clientInvocationContext);
        carrierLogisticsService.setClientInvocationContext(clientInvocationContext);
        activityServiceClient.setClientInvocationContext(clientInvocationContext);
        jitDeliveryServiceClient.setClientInvocationContext(clientInvocationContext);
        wpcClient.setClientInvocationContext(clientInvocationContext);
    }

    @Test
    public void getDeliveryOrders() throws OspException {
        try{
            GetDeliveryOrdersRequest request = new GetDeliveryOrdersRequest();
            request.setVendor_id(550);
            request.setStart_time((new Date().getTime() - 60 * 1000) / 1000);
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
            request1.setVendor_id(550);
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

    @Test
    public void reportTrace() throws OspException, IOException {

        String carrierCode = "ems";
        String json = readInputJson();
        Gson gson = new Gson();
        TraceTest trace = gson.fromJson(json, TraceTest.class);
        List<ReportTraceResult> a = carrierLogisticsService.reportTrace(carrierCode, trace.getTraces());
        //List<ReportTraceResult> a = carrierLogisticsService.reportTrace(carrierCode, initShipmentTraceList());

    }

    private String readInputJson() throws IOException {
        String file = "D:\\others\\lib\\json.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String content = "";
        String str = "";
        while ((str = reader.readLine()) != null){
            content += str;
        }
        return content;
    }

    @Data
    class TraceTest{
        List<ShipmentTraceList> traces;
    }

    private List<ShipmentTraceList> initShipmentTraceList() {
        ShipmentTraceList traceList = new ShipmentTraceList();

        traceList.setLogistics_no("SF1882760748674");
        traceList.setTraces(initTraces());

        ShipmentTraceList traceList1 = new ShipmentTraceList();

        traceList1.setLogistics_no("SF1882759401120");
        traceList1.setTraces(initTraces1());

        ShipmentTraceList traceList2 = new ShipmentTraceList();

        traceList2.setLogistics_no("SF1882791036321");
        traceList2.setTraces(initTraces2());
        return Lists.newArrayList(traceList, traceList1, traceList2);
    }

    private List<ShipmentTrace> initTraces() {
        ShipmentTrace trace = new ShipmentTrace();

        trace.setTrace_code("36");
        trace.setAction("TRANSIT");
        trace.setRemark("快件已发车");
        trace.setOp_time(new Date());
        trace.setSite_code("029X");
        trace.setSite_name("西北枢纽分拨中心");
        trace.setLongtitude("109.018");
        trace.setLatitude("34.520");
        trace.setCoordinate("1");

        return Lists.newArrayList(trace);
    }

    private List<ShipmentTrace> initTraces1() {

        ShipmentTrace trace = new ShipmentTrace();

        trace.setTrace_code("204");
        trace.setAction("DELIVERY");
        trace.setRemark("快件交给高栋,正在派送途中（联系电话：13335351216,顺丰已开启“安全呼叫”保护您的电话隐私,请放心接听！）");
        trace.setOp_time(new Date());
        trace.setSite_code("913HB");
        trace.setSite_name("渭南韩城太史街速运营业点");
        trace.setOperator("高栋");
        trace.setOperator_tel("13335351216");
        trace.setLongtitude("110.456599");
        trace.setLatitude("35.499373");
        trace.setCoordinate("1");

        return Lists.newArrayList(trace);
    }

    private List<ShipmentTrace> initTraces2() {
        ShipmentTrace trace = new ShipmentTrace();

        trace.setTrace_code("8000");
        trace.setAction("RECEIVER_ACCEPT");
        trace.setRemark("在官网\"运单资料&签收图\",可查看签收人信息");
        trace.setOp_time(new Date());
        trace.setSite_code("028CF");
        trace.setSite_name("资阳市简阳市丰和园营业点");
        trace.setLongtitude("104.554729");
        trace.setLatitude("30.368415");
        trace.setCoordinate("1");

        return Lists.newArrayList(trace);
    }

    @Test
    public void createOrderSn() throws OspException {

        CreateOrderSnReq req = new CreateOrderSnReq();
        req.setQuantity(1);

        CreateOrderSnResponse orderSn = cup.createOrderSn(req);

        log.info("orderSn: {}", new Gson().toJson(orderSn));

    }

    @Test
    public void queryPay() throws OspException {

        QueryPayReq req = new QueryPayReq();
        req.setPre_pay_id("1");

        log.info("queryPay: {}", new Gson().toJson(cupPay.queryPay(req)));

    }

    @Test
    public void pay() throws OspException {

        PayReq req = new PayReq();
        req.setPre_pay_id("1");
        req.setVip_order_sn("12341234");
        req.setAmount("10");
        req.setAuth_Code("121212");
        req.setDevice("device");
        req.setExt("");
        req.setScene("asdf");
        req.setTime_expire("2020-08-12 00:00:00");
        req.setSubject("subject");
        log.info("queryPay: {}", new Gson().toJson(cupPay.pay(req)));

    }

    @Test
    public void queryCustomsClearance() throws OspException {

        QueryCustomsClearanceReq req = new QueryCustomsClearanceReq();
        req.setStart_time("2020-01-01 00:00:00");
        req.setEnd_time("2020-02-01 00:00:00");

        log.info("queryCustomsClearance: {}", new Gson().toJson(customs.queryCustomsClearance(req)));
    }

    @Test
    public void deliveryByUser() throws OspException {
        DeliveryOrder order = new DeliveryOrder();
        order.setOrder_sn("11111");
        order.setUser_code("1");

        List<DeliveryOrder> orders = Lists.newArrayList();

        DeliveryByUserReq req = new DeliveryByUserReq();
        req.setOrders(orders);

        log.info("deliveryByUser: {}", new Gson().toJson(cup.deliveryByUser(req)));

    }


    @Test
    public void reportTraces() throws OspException {
        List<ShipmentTrace> traces = Lists.newArrayList();
        ShipmentTrace trace = new ShipmentTrace();
        trace.setCoordinate("1234");
        trace.setTrace_code("1");
        trace.setAction("30");
        trace.setRemark("remark");
        trace.setOp_time(new Date());
        trace.setOperator_tel("123");
        trace.setOperator("test");

        traces.add(trace);

        ShipmentTraceList traceList = new ShipmentTraceList();
        traceList.setLogistics_no("12341234");
        traceList.setTraces(traces);
        List<ShipmentTraceList> lists = Lists.newArrayList();
        lists.add(traceList);
        carrierLogisticsService.reportTrace("ems", lists);
    }

    @Test
    public void checkInV3() throws OspException {
        CheckInResult checkInResult = activityServiceClient.checkInV3("1", "1");
        log.info("{}", checkInResult);
    }

    @Test
    public void createPoDeliveryV2() throws OspException {
        CreatePoDeliveryReq req = new CreatePoDeliveryReq();
        req.setArrival_time("2020-09-01 16:00:00");
        req.setCarrier_code("120000831");
        req.setDelivery_method("2");
        req.setDelivery_time("2020-08-31 20:00:00");
        req.setDelivery_warehouse("SPD0002109");
        req.setIs_air_embargo(0);
        req.setLogistics_no("MD1988951358752955702");
        req.setPo_nos(Lists.newArrayList("2102354370"));
        req.setVendor_id(28849);
        req.setWarehouse("VIP_HZ");
        jitDeliveryServiceClient.createPoDeliveryV2(req);
    }

    @Test
    public void wpcTest() throws OspException {
        WpcBrandListRequest req = new WpcBrandListRequest();
        req.setPage(1);
        req.setPageSize(1);
        wpcClient.getBrandList(req);
    }
}
