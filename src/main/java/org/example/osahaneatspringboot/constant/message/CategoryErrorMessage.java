package org.example.osahaneatspringboot.constant.message;

public class CategoryErrorMessage {
    public static final String CATEGORY_ID_NOT_BLANK = "ID thể loại không được để trống.";
    public static final String CATEGORY_NAME_NOT_BLANK = "Tên danh mục không được để trống.";
    public static final String CATEGORY_NAME_SIZE = "Tên danh mục phải có độ dài từ 1 đến 255 ký tự.";
    public static final String CATEGORY_NAME_INVALID = "Tên danh mục không được chứa ký tự đặc biệt.";
    public static final String CATEGORY_DESCRIPTION_SIZE = "Mô tả phải có độ dài tối đa 255 ký tự.";
    public static final String CATEGORY_NOT_FOUND = "Không tìm thấy danh mục với ID đã cho.";
    public static final String CATEGORY_ALREADY_EXISTS = "Danh mục đã tồn tại.";
}
