package Models;

import java.util.Date;

/**
 * Created by sagar on 29/05/17.
 */

public class MenuItem {


    private String itemId,itemName,itemUrl,priceToday,priceTomorrow,priceLater,availableMonday,
                    availableTuesday,availableWedenesday,availableThrusday,availableFriday,availableSaturday,
                    availableSunday,itemCategeory,foodType,itemDescription,displayOrder,objectId;
    private Date created,updated;

    public MenuItem() {
        //Empty constructor
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getPriceToday() {
        return priceToday;
    }

    public void setPriceToday(String priceToday) {
        this.priceToday = priceToday;
    }

    public String getPriceTomorrow() {
        return priceTomorrow;
    }

    public void setPriceTomorrow(String priceTomorrow) {
        this.priceTomorrow = priceTomorrow;
    }

    public String getPriceLater() {
        return priceLater;
    }

    public void setPriceLater(String priceLater) {
        this.priceLater = priceLater;
    }

    public String getAvailableMonday() {
        return availableMonday;
    }

    public void setAvailableMonday(String availableMonday) {
        this.availableMonday = availableMonday;
    }

    public String getAvailableTuesday() {
        return availableTuesday;
    }

    public void setAvailableTuesday(String availableTuesday) {
        this.availableTuesday = availableTuesday;
    }

    public String getAvailableWedenesday() {
        return availableWedenesday;
    }

    public void setAvailableWedenesday(String availableWedenesday) {
        this.availableWedenesday = availableWedenesday;
    }

    public String getAvailableThrusday() {
        return availableThrusday;
    }

    public void setAvailableThrusday(String availableThrusday) {
        this.availableThrusday = availableThrusday;
    }

    public String getAvailableFriday() {
        return availableFriday;
    }

    public void setAvailableFriday(String availableFriday) {
        this.availableFriday = availableFriday;
    }

    public String getAvailableSaturday() {
        return availableSaturday;
    }

    public void setAvailableSaturday(String availableSaturday) {
        this.availableSaturday = availableSaturday;
    }

    public String getAvailableSunday() {
        return availableSunday;
    }

    public void setAvailableSunday(String availableSunday) {
        this.availableSunday = availableSunday;
    }

    public String getItemCategeory() {
        return itemCategeory;
    }

    public void setItemCategeory(String itemCategeory) {
        this.itemCategeory = itemCategeory;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
