import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Product> productList=new ArrayList<>();
        productList.add(new Product("A1","Legion 5","china",23690000,"GTX1650,Ryzen5,lenovo"));
        productList.add(new Product("A2","Legion 5 pro","china",43000000,"lenovo"));
        writeProduct(productList,"productList.txt");
        addProduct("productList.txt");
        show("productList.txt");
        Product productTest=findProduct("Legion 5","productList.txt");
        System.out.println(productTest.toString());
    }
    public static void show (String link){
        List<Product> productList=readProduct(link);
        for (Product a:productList
             ) {
            System.out.println(a);
        }
    }
    public static List<Product> addProduct(String link){
        List<Product> productList=readProduct("productList.txt");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        System.out.println("Enter Code:");
        String code=scanner.nextLine();
        System.out.println("Enter name:");
        String name=scanner.nextLine();
        System.out.println("Enter manufacturer:");
        String manufacturer=scanner.nextLine();
        System.out.println("Enter price:");
        int price=scanner1.nextInt();
        System.out.println("Enter note");
        String note =scanner.nextLine();
        productList.add(new Product(code,name,manufacturer,price,note));
        writeProduct(productList,link);
        return productList;
    }
    public static Product findProduct(String name,String link){
        List<Product> productList=readProduct(link);
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getName().equals(name)) {
                return productList.get(i);
            }
        }
        return null;
    }
    public static List<Product> readProduct(String link) {
        List<Product> productList1=new ArrayList<>();
        try {
            InputStream is=new FileInputStream(link);
            ObjectInputStream ois= new ObjectInputStream(is);
            productList1=(List<Product>) ois.readObject();
            ois.close();
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return productList1;
    }

    private static void writeProduct(List<Product> productList,String link) {
        try {
            OutputStream os=new FileOutputStream(link);
            ObjectOutputStream oos=new ObjectOutputStream(os);
            oos.writeObject(productList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
