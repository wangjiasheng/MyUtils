package com.wjs.extendmenu.extendmenulib;

/**
 * Created by 家胜 on 2015/11/12.
 */
public class MainMenu
{
    private String imageUrl;
    private String buttonText;
    private int buttonUrlId;
    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public int getButtonUrlId() {
        return buttonUrlId;
    }

    public void setButtonUrlId(int buttonUrlId) {
        this.buttonUrlId = buttonUrlId;
    }
}
