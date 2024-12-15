package org.example.osahaneatspringboot.constant.message;

public class DishErrorMessage {
    public static final String DISH_NAME_NOT_BLANK = "Tên món ăn không được để trống.";
    public static final String DISH_NAME_SIZE = "Tên món ăn phải có độ dài từ 1 đến 255 ký tự.";
    public static final String DISH_NAME_INVALID =
            "Tên món ăn chỉ được chứa ký tự chữ cái, số, dấu cách và các ký tự Việt Nam.";
    public static final String DISH_DESCRIPTION_SIZE = "Mô tả phải có độ dài tối đa 255 ký tự.";
    public static final String DISH_ALREADY_EXISTS = "Món ăn đã tồn tại.";
    public static final String DISH_NOT_FOUND = "Món ăn không tồn tại.";
    public static final String DISH_PRICE_NOT_NULL = "Giá món ăn không được để trống.";
    public static final String DISH_PRICE_NEGATIVE = "Giá món ăn không được nhỏ hơn 0.";
    public static final String DISH_ID_NOT_BLANK = "ID món ăn không được để trống.";
}
