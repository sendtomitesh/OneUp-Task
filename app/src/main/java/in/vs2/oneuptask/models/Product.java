package in.vs2.oneuptask.models;

import java.io.Serializable;
import java.util.ArrayList;

import in.vs2.oneuptask.R;

/**
 * Created by antarix on 07/09/15.
 */
public class Product implements Serializable{
    String name;
    String price;
    int imageResource;

    public Product(){

    }
    public Product(String name, String price, int imageResource) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
    }


    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getPrice() {
        return "$"+price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Product> getProducts(){

        ArrayList<Product> products = new ArrayList<Product>();

        products.add(new Product("Camera","150", R.drawable.camera));
        products.add(new Product("Chromecast","10", R.drawable.chrome));
        products.add(new Product("Headset","150", R.drawable.headset));
        products.add(new Product("iPhone","499", R.drawable.iphone));
        products.add(new Product("Grocery","15", R.drawable.kitchen));
        products.add(new Product("Computer","250", R.drawable.pc));
        products.add(new Product("Rubik's cube","10", R.drawable.rubiks));
        products.add(new Product("Shampoo","10", R.drawable.shampoo));
        products.add(new Product("Tablet","299", R.drawable.tab));
        products.add(new Product("Watch","150", R.drawable.watch));



        return products;

    }


}
