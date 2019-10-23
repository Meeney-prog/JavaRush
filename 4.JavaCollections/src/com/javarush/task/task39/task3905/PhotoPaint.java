package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int y, int x, Color desiredColor) {
        if (x < 0 || y < 0 || x >= image.length || y >= image[x].length || desiredColor == image[x][y])
            return false;
        Color oldColor = image[x][y];
        image[x][y] = desiredColor;
        if (x - 1 >= 0 && image[x - 1][y] == oldColor) paintFill(image, x - 1, y, desiredColor);
        if (x + 1 < image.length && image[x + 1][y] == oldColor) paintFill(image, x + 1, y, desiredColor);
        if (y - 1 >= 0 && image[x][y - 1] == oldColor) paintFill(image, x, y - 1, desiredColor);
        if (y + 1 < image[x].length && image[x][y + 1] == oldColor) paintFill(image, x, y + 1, desiredColor);
        return true;
    }
}
