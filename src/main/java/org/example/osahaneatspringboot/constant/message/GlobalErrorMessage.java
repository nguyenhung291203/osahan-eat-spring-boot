package org.example.osahaneatspringboot.constant.message;

public class GlobalErrorMessage {

    public static final String UNCATEGORIZED_EXCEPTION = "Lỗi hệ thống không xác định";
    public static final String METHOD_NOT_SUPPORTED = "Phương thức không được hỗ trợ";
    public static final String INVALID_DATA_FORMAT = "Định dạng dữ liệu không hợp lệ";
    public static final String UNAUTHENTICATED = "Chưa xác thực: Vui lòng đăng nhập để truy cập tài nguyên";
    public static final String UNAUTHORIZED = "Bạn không có quyền truy cập tài nguyên này";
    public static final String DATA_VALIDATION_FAILED = "Xác thực dữ liệu thất bại";
    public static final String PATH_NOT_FOUND = "Đường dẫn yêu cầu không tồn tại";
    public static final String INVALID_PAGINATION_REQUEST = "Yêu cầu phân trang không hợp lệ";
    public static final String FORBIDDEN = "Bạn không có quyền thực hiện hành động này";

    private GlobalErrorMessage() {}
}
