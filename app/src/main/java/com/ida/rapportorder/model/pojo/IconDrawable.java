package com.ida.rapportorder.model.pojo;

import android.graphics.drawable.Drawable;

import com.ida.rapportorder.R;

public class IconDrawable {

    private final int[] drawables = {
            R.drawable.green_drawable,
            R.drawable.red_drawable,
            R.drawable.blue_drawable,
            R.drawable.yellow_drawable,
            R.drawable.pink_drawable,
            R.drawable.teal_drawable,
            R.drawable.orange_drawable
    };

    public int getUserAvatar(User user){
        int drawable = 0;
        switch (user.getFirstname()){
            case "Ida":
                drawable = drawables[0];
                break;
            case "David":
                drawable = drawables[1];
                break;
            case "Niklas":
                drawable = drawables[2];
                break;
            case "Lars-Olof":
                drawable = drawables[3];
                break;
        }
        return drawable;
    }
    public int getOrderRowDrawable(int day){

        int drawable = 0;
        switch (day){
            case 0:
                drawable = drawables[0];
                break;
            case 1:
                drawable = drawables[1];
                break;
            case 2:
                drawable = drawables[2];
                break;
            case 3:
                drawable = drawables[3];
                break;
            case 4:
                drawable = drawables[4];
                break;
            case 5:
                drawable = drawables[5];
                break;
            case 6:
                drawable = drawables[6];
                break;
        }
        return drawable;
    }
}
