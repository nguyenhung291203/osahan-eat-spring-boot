package org.example.osahaneatspringboot.constant.regexp;

public class RegexPatterns {
    public static final String NO_SPECIAL_CHARACTERS_PATTERN = "^[a-zA-Z0-9\\s]*$";
    //    public static final String ALPHANUMERIC_WITH_SPACES_AND_VIETNAMESE_ACCENTS_PATTERN =
    // "^[a-zA-Z0-9\\sàáảãạèéẻẽẹìíỉĩịòóỏõọùúủũụÀÁẢÃẠÈÉẺẼẸÌÍỈĨỊÒÓỎÕỌÙÚỦŨỤ]+$";
    public static final String ALPHANUMERIC_WITH_SPACES_AND_VIETNAMESE_ACCENTS_PATTERN = "^[\\p{L}\\s0-9]+$";
}
