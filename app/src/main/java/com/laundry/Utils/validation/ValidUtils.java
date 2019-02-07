package com.laundry.Utils.validation;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;



public class ValidUtils {
    public static final int MIN_PHONE_NUMBER_RANGE = 9;
    public static final int MAX_PHONE_NUMBER_RANGE = 13;
    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int index = start; index < end; index++) {
                /*int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE) {
                    return "";
                }*/
                if (!Character.isLetter(source.charAt(index))) {
                    return "";
                }
            }
            return null;
        }
    };
    public static InputFilter USERNAME_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int index = start; index < end; index++) {
                /*int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE) {
                    return "";
                }*/
                if (!Character.isLetterOrDigit(source.charAt(index)) && source.charAt(index) != '_' && source.charAt(index) != '-' && source.charAt(index) != '.') {
                    return "";
                }
            }
            return null;
        }
    };
    static String emailpattern = "[a-zA-Z0-9._-]+@[a-z0-9]+\\.+[a-z]+";
    static String emailpattern2 = "[a-zA-Z0-9._-]+@[a-z0-9]+\\.+[a-z]+\\.+[a-z]+";

    public static boolean validateEmptyEditTexts(TextView... testObj) {
        for (int i = 0; i < testObj.length; i++) {
            if (testObj[i].getText().toString().trim().equals(""))
                return false;
        }
        return true;
    }

    public static boolean validateMobileNumber(int rangeStart, int rangeEnd, EditText... testObj) {
        for (int i = 0; i < testObj.length; i++) {
            if (!(testObj[i].getText().toString().length() >= rangeStart) || !(testObj[i].getText().toString().length() <= rangeEnd))
                return false;
        }
        return true;
    }

    public static boolean validateEmail(EditText... testObj) {
        for (int i = 0; i < testObj.length; i++) {
            if (!testObj[i].getText().toString().trim().matches(emailpattern) && !testObj[i].getText().toString().trim().matches(emailpattern2))
                return false;
        }
        return true;
    }

    public static boolean validateForDigits(EditText testObj, int noOfDigits) {

        return testObj.getText().toString().length() == noOfDigits;
    }

    public static boolean validateForRange(EditText testObj, int start, int end) {

        return !(!(Integer.parseInt(testObj.getText().toString().trim()) >= start) && !(Integer.parseInt(testObj.getText().toString().trim()) <= end));
    }

    public static boolean validateIsEqual(EditText testObjOne, EditText testObjTwo) {
        return TextUtils.equals(testObjOne.getText().toString(), testObjTwo.getText().toString());
    }

    public static boolean validateForMinDigits(EditText testObj, int noOfDigits) {
        return testObj.getText().toString().length() > noOfDigits;
    }

    public static boolean isBlank(TextView tv, String msg) {
        if (TextUtils.isEmpty(tv.getText().toString().trim())) {
            tv.setError(msg);
            return true;
        }
        return false;
    }

    public static boolean isBlank(TextView tv, ScrollView scrollView, String msg) {
        if (TextUtils.isEmpty(tv.getText().toString().trim())) {
            tv.setError(msg);
            scrollView.post(() -> scrollView.smoothScrollTo(0, tv.getBottom()));
            return true;
        }
        return false;
    }

//    public static boolean validateUsername(EditText loginEmailUsernameTet) {
//        Pattern pattern = Pattern.compile("[A-Za-z0-9_.-]{" + AppConstants.MIN_USERNAME_RANGE + "," + AppConstants.MAX_USERNAME_RANGE + "}");
//        return !loginEmailUsernameTet.getText().toString().isEmpty() && pattern.matcher(loginEmailUsernameTet.getText().toString().trim()).matches();
//    }

//    public static boolean isBlank(String str, View anyView, String msg) {
//        if (TextUtils.isEmpty(str)) {
//            DialogUtil.showSnackBar(anyView, msg);
//            return true;
//        }
//        return false;
//    }

//    public static boolean isBlankWithAlert(TextView tv, String string) {
//        if (TextUtils.isEmpty(tv.getText())) {
//            DialogUtil.showSnackBar(tv, string);
//            return true;
//        }
//        return false;
//    }

    public static boolean validatePhNo(EditText loginEmailUsernameTet) {
        String regexStr = "^[965][0-9]{5,8}$";
        return !loginEmailUsernameTet.getText().toString().isEmpty() && loginEmailUsernameTet.getText().toString().trim().matches(regexStr);
        /* return valid && validateMobileNumber(MIN_PHONE_NUMBER_RANGE, MAX_PHONE_NUMBER_RANGE, loginEmailUsernameTet);*/
    }

    public static boolean validateName(EditText registerFirstNameEdt) {
        return !validateNonWords(registerFirstNameEdt) && validateNonNumber(registerFirstNameEdt);
    }

    public static boolean validateNonNumber(EditText registerFirstNameEdt) {
        String regexStr = "[^0-9]";
        return !registerFirstNameEdt.getText().toString().isEmpty() && registerFirstNameEdt.getText().toString().trim().matches(regexStr);
    }

    public static boolean validateNonWords(EditText registerFirstNameEdt) {
        String regexStr = "[^\\w]";
        return !registerFirstNameEdt.getText().toString().isEmpty() && registerFirstNameEdt.getText().toString().trim().matches(regexStr);
    }

    public static boolean validatePswd(EditText registerPasswordEdt) {
        return !registerPasswordEdt.getText().toString().isEmpty() && registerPasswordEdt.getText().toString().trim().length() >= 6;
    }

    public static boolean validatePhoneLength(EditText mobileNoEdittext) {
        Editable phone = mobileNoEdittext.getText();
        int length = phone.length();
        if (length < 10 && length > 6)
            return true;
        else
            return false;
    }

    public static boolean validateMinLength(EditText editText, int minLength) {
        Editable data = editText.getText();
        int length = data.length();
        if (length < minLength)
            return false;
        else
            return true;
    }

    public static void showSnackBar(View parentLayout, String msg) {
        final Snackbar snackBar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT);
        snackBar.setActionTextColor(Color.WHITE);
        View view = snackBar.getView();
        TextView tv = (TextView)
                view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);

        snackBar.show();

    }
}