package org.example.osahaneatspringboot.constant.message;

public class ProfileErrorMessage {

    public static final String PROFILE_NOT_FOUND = "Hồ sơ không tồn tại";
    public static final String PROFILE_UPDATE_FAILED = "Cập nhật hồ sơ không thành công";
    public static final String INVALID_PROFILE_DATA = "Dữ liệu hồ sơ không hợp lệ";
    public static final String PROFILE_EXISTED = "Hồ sơ đã tồn tại";
    public static final String IMAGE_EMPTY = "Avatar không được để trống";
    public static final String IMAGE_LOAD_FAILED = "Tải ảnh không thành công";
    public static final String IMAGE_TOO_LARGE = "Kích thước ảnh vượt quá giới hạn cho phép (tối đa 10MB)";
    public static final String INVALID_IMAGE_FORMAT =
            "Định dạng file ảnh không hợp lệ. Chỉ chấp nhận JPG, PNG, JPEG, GIF.";

    private ProfileErrorMessage() {}
}
