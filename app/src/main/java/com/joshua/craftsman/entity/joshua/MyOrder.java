package com.joshua.craftsman.entity.joshua;
/**
 * ============================================================
 * <p>
 * 版 权 ： 袁健炜  (c) 2017
 * <p>
 * 作 者 :  袁健炜 
 * <p>
 * 版 本 ： 1.0
 * <p>
 * 创建日期 ： 2017/5/13 08:06
 * <p>
 * 描 述 ：订单类
 * <p>
 * ============================================================
 **/

public class MyOrder {
	private String Id;//序列号
	private String OrderId;//订单编号
    private String UserId; //注册手机号
    private String GoodsId;//视频id/音频id
    private String Price;//购买价格
     
  // private String OrderStatus;//订单状态
    private String PayType;//支付方式（需不需要?）
    private String PayStatus;//支付 | 订单状态（已支付或未支付）
    private String BuyType;//购买类型：
    private String CommentStatsu;//评论 0/1 
    private String DeleteOrder;//删除订单 0/1 :订单关闭？
    private String BuyTims;//购买日期
    private String PayResult;//返回结果
    private String PayMono;//返回mono
    
    public MyOrder(){
    	super();
    }
    
    

	public MyOrder(String orderId, String userId, String goodsId, String price, String payType,
			String payStatus, String buyType, String commentStatsu, String deleteOrder, String buyTims,String payResult,String payMono) {
		Id = null;
		OrderId = orderId;
		UserId = userId;
		GoodsId = goodsId;
		Price = price;
		PayType = payType;
		PayStatus = payStatus;
		BuyType = buyType;
		CommentStatsu = commentStatsu;
		DeleteOrder = deleteOrder;
		BuyTims = buyTims;
		PayResult = payResult;
		PayMono = payMono;
	}



	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getGoodsId() {
		return GoodsId;
	}

	public void setGoodsId(String goodsId) {
		GoodsId = goodsId;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getPayType() {
		return PayType;
	}

	public void setPayType(String payType) {
		PayType = payType;
	}

	public String getPayStatus() {
		return PayStatus;
	}

	public void setPayStatus(String payStatus) {
		PayStatus = payStatus;
	}

	public String getBuyType() {
		return BuyType;
	}

	public void setBuyType(String buyType) {
		BuyType = buyType;
	}

	public String getCommentStatsu() {
		return CommentStatsu;
	}

	public void setCommentStatsu(String commentStatsu) {
		CommentStatsu = commentStatsu;
	}

	public String getDeleteOrder() {
		return DeleteOrder;
	}

	public void setDeleteOrder(String deleteOrder) {
		DeleteOrder = deleteOrder;
	}

	public String getBuyTims() {
		return BuyTims;
	}

	public void setBuyTims(String buyTims) {
		BuyTims = buyTims;
	}

	


	public String getPayResult() {
		return PayResult;
	}



	public void setPayResult(String payResult) {
		PayResult = payResult;
	}



	public String getPayMono() {
		return PayMono;
	}



	public void setPayMono(String payMono) {
		PayMono = payMono;
	}



	@Override
	public String toString() {
		return "MyOrder [Id=" + Id + ", OrderId=" + OrderId + ", UserId=" + UserId + ", GoodsId=" + GoodsId + ", Price="
				+ Price + ", PayType=" + PayType + ", PayStatus=" + PayStatus + ", BuyType=" + BuyType
				+ ", CommentStatsu=" + CommentStatsu + ", DeleteOrder=" + DeleteOrder + ", BuyTims=" + BuyTims
				+ ", PayResult=" + PayResult + ", PayMono=" + PayMono + "]";
	}
   
    
}
