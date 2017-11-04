package kr.wonjun.electhon.utils;

import android.graphics.Color;

import kr.wonjun.electhon.views.CartaTagConfiguration;

public class CartaTagThemeHelper {

    public static CartaTagConfiguration normalButton = new CartaTagConfiguration()
            .setFullMode(true)
            .setThemeColor(Color.BLACK);
    public static CartaTagConfiguration selectedMainTab = new CartaTagConfiguration()
            .setFullMode(true)
            .setGradientMode(true)
            .setGradientColor(Color.parseColor("#5b8bf4"), Color.parseColor("#3dc9bd"));
    public static CartaTagConfiguration nonSelectedMainTab = new CartaTagConfiguration()
            .setFullMode(false)
            .setThemeColor(Color.parseColor("#787878"));

    public static CartaTagConfiguration selectedTutorialTab = new CartaTagConfiguration()
            .setFullMode(true)
            .setThemeColor(Color.WHITE)
            .setTextColorEnabled(true)
            .setTextColor(Color.TRANSPARENT);
    public static CartaTagConfiguration nonSelectedTutorialTab = new CartaTagConfiguration()
            .setFullMode(false)
            .setThemeColor(Color.WHITE);

    public static CartaTagConfiguration EDYSendingMsg = new CartaTagConfiguration()
            .setFullMode(false)
            .setGradientMode(true)
            .setGradientColor(Color.parseColor("#5b8bf4"), Color.parseColor("#3dc9bd"))
            .setTextColorEnabled(true)
            .setTextColor(Color.BLACK);
}
