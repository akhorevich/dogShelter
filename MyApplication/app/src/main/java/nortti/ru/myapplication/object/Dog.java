package nortti.ru.myapplication.object;

import android.graphics.Bitmap;

public class Dog {
    private String name;
    private Bitmap pic;
    private String link;

    public Dog(){};

    public Dog(String name, Bitmap pic, String link) {
        this.name = name;
        this.pic = pic;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
