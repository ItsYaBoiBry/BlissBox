package asia.blissbox.blissboxpteltd;

/**
 * Created by bryan on 10/1/2018.
 */

import java.io.Serializable;


public class ObjectBoxes implements Serializable {


    private int giftbox_id;
    private int universe_id;
    private String initial;
    private String thumbnail;
    private String name;
    private double price;
    private String description;
    private String pdf_url;
    private int review;
    private String deleted_at;
    private String created_at;
    private String updated_at;

    public ObjectBoxes(){
    }


    public ObjectBoxes(int giftbox_id,
                       int universe_id,
                       String initial,
                       String thumbnail,
                       String name,
                       double price,
                       String description,
                       String pdf_url,
                       int review,
                       String deleted_at,
                       String created_at,
                       String updated_at) {

        this.giftbox_id = giftbox_id;
        this.universe_id = universe_id;
        this.initial = initial;
        this.thumbnail = thumbnail;
        this.name = name;
        this.price = price / 100;
        this.description = description;
        this.pdf_url = pdf_url;
        this.review = review;
        this.deleted_at = deleted_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }


    public int getGiftboxId() {
        return giftbox_id;
    }

    public void setGiftboxId(int giftbox_id) {
        this.giftbox_id = giftbox_id;
    }

    public int getUniverseId() {
        return universe_id;
    }

    public void setUniverseId(int universe_id) {
        this.universe_id = universe_id;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price / 100;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String toString() {
        String allItems = "Universe_id: " + getUniverseId()
                + "\nInitial: " + getInitial()
                + "\nThumbnail: " + getThumbnail()
                + "\nName: " + getName()
                + "\nPrice: " + getPrice()
                + "\nDescription: " + getDescription()
                + "\nDeleted_at: " + getDeleted_at()
                + "\nCreated_at: " + getCreated_at()
                + "\nUpdated_at: " + getUpdated_at();
        return allItems;
    }
}
