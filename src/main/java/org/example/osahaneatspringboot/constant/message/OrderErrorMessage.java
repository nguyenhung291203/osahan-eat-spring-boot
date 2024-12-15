package org.example.osahaneatspringboot.constant.message;

public class OrderErrorMessage {
    public static final String ORDER_NOT_FOUND = "Đơn hàng không tồn tại";
    public static final String ORDER_CREATION_FAILED = "Không thể tạo đơn hàng";
    public static final String ORDER_UPDATE_FAILED = "Không thể cập nhật đơn hàng";
    public static final String ORDER_PAYMENT_FAILED = "Thanh toán đơn hàng không thành công";
    public static final String ORDER_CANCEL_FAILED = "Không thể hủy đơn hàng";
    public static final String ORDER_ITEM_QUANTITY_EXCEEDED =
            "Số lượng sản phẩm trong đơn hàng vượt quá số lượng trong kho";
    public static final String ORDER_STATUS_INVALID = "Trạng thái đơn hàng không hợp lệ";
    public static final String ORDER_ADDRESS_INVALID = "Địa chỉ giao hàng không hợp lệ";
    public static final String ORDER_ALREADY_PROCESSED = "Đơn hàng đã được xử lý, không thể thực hiện thao tác này";
    public static final String ORDER_OPERATION_FAILED = "Xử lý đơn hàng không thành công";
}
