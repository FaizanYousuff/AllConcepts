package com.example.faizan.allconcepts.common;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    private Utils() {

    }

    public static boolean isEmptyString(CharSequence str) {
        return str == null || (str.toString().trim()).length() == 0;
    }

    public static boolean isEmptyValue(CharSequence str) {
        return str == null || (str.toString().trim()).length() == 0 || "null".equals(str.toString().trim());
    }

    /**
     * validates the given Email with java standard Email pattern
     *
     * @param email
     * @return boolean
     */
    public static boolean isValidEmail(String email) {
        final Pattern emailPattern
                = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    /**
     * validates the given password with specified password pattern
     *
     * @param password
     * @return boolean
     */
    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!.]).{7,50})");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    public static void showProgressDialog(Context context, boolean isShow) {
        showProgressDialog(context, isShow, false);
    }

    public static void showProgressDialog(Context context, boolean isShow, boolean isBottomView) {

//        logger.debug("inside progressDialog : " + progressDialog);
//        try {
//            if (isShow && progressDialog == null) {
//                progressDialog = new Dialog(context);
//                progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                progressDialog.setCancelable(false);
//                progressDialog.setContentView(R.layout.progress_dialog);
//                if (isBottomView) {
//                    progressDialog.findViewById(R.id.progress_view).setVisibility(View.GONE);
//                    progressDialog.findViewById(R.id.progressView).setVisibility(View.VISIBLE);
//                }
//                Window mWindow = progressDialog.getWindow();
//                if (mWindow != null) {
//                    mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                }
//                progressDialog.show();
//            } else if (progressDialog != null && progressDialog.isShowing()) {
//                progressDialog.dismiss();
//                progressDialog = null;
//            }
//        } catch (Exception e) {
//            logger.severe(context.getClass().getName() + e);
//        }
    }



}
