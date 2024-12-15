package org.example.osahaneatspringboot.constant.message;

public class RestaurantErrorMessage {
    public static final String RESTAURANT_ID_NOT_BLANK = "ID nhà hàng không được để trống.";
    public static final String TITLE_REQUIRED = "Tiêu đề nhà hàng là bắt buộc.";
    public static final String TITLE_INVALID_CHARACTERS = "Tiêu đề không được chứa ký tự đặc biệt.";
    public static final String ADDRESS_REQUIRED = "Địa chỉ nhà hàng là bắt buộc.";
    public static final String TITLE_DUPLICATE = "Tiêu đề nhà hàng đã tồn tại.";
    public static final String ADDRESS_DUPLICATE = "Địa chỉ nhà hàng đã tồn tại.";
    public static final String ADDRESS_LENGTH_INVALID =
            "Độ dài địa chỉ không hợp lệ. Vui lòng nhập từ 10 đến 255 ký tự.";
    public static final String IMAGE_REQUIRED = "Hình ảnh nhà hàng là bắt buộc.";
    public static final String TITLE_LENGTH_INVALID = "Độ dài tiêu đề không hợp lệ. Vui lòng nhập từ 3 đến 50 ký tự.";
    public static final String DESCRIPTION_TOO_LONG = "Mô tả quá dài. Vui lòng nhập không quá 255 ký tự.";
    public static final String IMAGE_SIZE_EXCEEDED =
            "Kích thước hình ảnh vượt quá giới hạn cho phép. Vui lòng tải lên hình ảnh có kích thước nhỏ hơn 5MB.";
    public static final String SUBTITLE_TOO_LONG = "Phụ đề không được vượt quá 255 ký tự.";

    public static final String RESTAURANT_NOT_ACTIVE = "Nhà hàng hiện đang không hoạt động.";
    public static final String RESTAURANT_NOT_FOUND = "Không tìm thấy nhà hàng với ID được cung cấp.";

    public static final String INVALID_OPEN_TIME = "Thời gian mở cửa không hợp lệ.";
    public static final String INVALID_CLOSE_TIME = "Thời gian đóng cửa không hợp lệ.";
    public static final String CLOSE_BEFORE_OPEN = "Thời gian đóng cửa phải sau thời gian mở cửa.";
    public static final String OPEN_TIME_REQUIRED = "Thời gian mở là bắt buộc.";
    public static final String CLOSE_TIME_REQUIRED = "Thời gian đóng là bắt buộc.";

    public static final String LATITUDE_REQUIRED = "Vĩ độ là bắt buộc.";
    public static final String LONGITUDE_REQUIRED = "Kinh độ là bắt buộc.";
    public static final String INVALID_LATITUDE = "Vĩ độ không hợp lệ. Giá trị hợp lệ từ -90 đến 90.";
    public static final String INVALID_LONGITUDE = "Kinh độ không hợp lệ. Giá trị hợp lệ từ -180 đến 180.";

    public static final String UNEXPECTED_ERROR = "Đã xảy ra lỗi không xác định trong quá trình xử lý yêu cầu.";

    private RestaurantErrorMessage() {}
}
