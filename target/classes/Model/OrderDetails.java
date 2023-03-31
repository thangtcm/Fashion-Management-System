/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author couni
 */
public class OrderDetails {

    private int ID;
    private ProductVariants productVariant;
    private Order order;
    private int Stock;
    private double Price;

    
    
    public OrderDetails(OrderDetails orderDetails)
    {
        this.ID = orderDetails.ID;
        this.productVariant = orderDetails.productVariant;
        this.order = orderDetails.order;
        this.Stock = orderDetails.Stock;
        this.Price = orderDetails.Price;
    }
    
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the productVariant
     */
    public ProductVariants getProductVariant() {
        return productVariant;
    }

    /**
     * @param productVariant the productVariant to set
     */
    public void setProductVariant(ProductVariants productVariant) {
        this.productVariant = productVariant;
    }

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the Stock
     */
    public int getStock() {
        return Stock;
    }

    /**
     * @param Stock the Stock to set
     */
    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    /**
     * @return the Price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    
}
