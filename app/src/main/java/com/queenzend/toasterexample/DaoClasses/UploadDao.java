package com.queenzend.toasterexample.DaoClasses;

/**
 * Created by dell on 12/6/2016.
 */
public class UploadDao {

    String id, image_url;

    private static UploadDao instance = null;


    public static UploadDao getInstance() {
        if (instance == null) {
            instance = new UploadDao();
        }
        return instance;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url(String filePath) {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getStringImage(String image) {
        return image;
    }


    // public void setStringImage(String image) {
       // this.image = image;
    //}
}



