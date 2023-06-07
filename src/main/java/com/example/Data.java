package com.example;

import java.util.List;

public class Data {
    private List<String> applicable_payment_options;
    private String cart_session_token;
    private String currency;
    private Object custom_attributes;
    private List<Object> discount_details;
    private List<Object> discounts;
    private List<Experiment> experiments;
    private List<Object> fees;
    private List<LineItem> line_items;
    private String merchant_checkout_url;
    private MerchantDetails merchant_details;
    private ShippingLinesResource shipping_lines_resource;
    private String shop_domain;
    private List<Object> taxes;
    private boolean taxes_included;
    private int total_discount;
    private int total_item_price;
    private int total_price;
    private int total_tax;

    public List<String> getApplicable_payment_options() {
        return applicable_payment_options;
    }

    public void setApplicable_payment_options(List<String> applicable_payment_options) {
        this.applicable_payment_options = applicable_payment_options;
    }

    public String getCart_session_token() {
        return cart_session_token;
    }

    public void setCart_session_token(String cart_session_token) {
        this.cart_session_token = cart_session_token;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getCustom_attributes() {
        return custom_attributes;
    }

    public void setCustom_attributes(Object custom_attributes) {
        this.custom_attributes = custom_attributes;
    }

    public List<Object> getDiscount_details() {
        return discount_details;
    }

    public void setDiscount_details(List<Object> discount_details) {
        this.discount_details = discount_details;
    }

    public List<Object> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Object> discounts) {
        this.discounts = discounts;
    }

    public List<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(List<Experiment> experiments) {
        this.experiments = experiments;
    }

    public List<Object> getFees() {
        return fees;
    }

    public void setFees(List<Object> fees) {
        this.fees = fees;
    }

    public List<LineItem> getLine_items() {
        return line_items;
    }

    public void setLine_items(List<LineItem> line_items) {
        this.line_items = line_items;
    }

    public String getMerchant_checkout_url() {
        return merchant_checkout_url;
    }

    public void setMerchant_checkout_url(String merchant_checkout_url) {
        this.merchant_checkout_url = merchant_checkout_url;
    }

    public MerchantDetails getMerchant_details() {
        return merchant_details;
    }

    public void setMerchant_details(MerchantDetails merchant_details) {
        this.merchant_details = merchant_details;
    }

    public ShippingLinesResource getShipping_lines_resource() {
        return shipping_lines_resource;
    }

    public void setShipping_lines_resource(ShippingLinesResource shipping_lines_resource) {
        this.shipping_lines_resource = shipping_lines_resource;
    }

    public String getShop_domain() {
        return shop_domain;
    }

    public void setShop_domain(String shop_domain) {
        this.shop_domain = shop_domain;
    }

    public List<Object> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Object> taxes) {
        this.taxes = taxes;
    }

    public boolean isTaxes_included() {
        return taxes_included;
    }

    public void setTaxes_included(boolean taxes_included) {
        this.taxes_included = taxes_included;
    }

    public int getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(int total_discount) {
        this.total_discount = total_discount;
    }

    public int getTotal_item_price() {
        return total_item_price;
    }

    public void setTotal_item_price(int total_item_price) {
        this.total_item_price = total_item_price;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getTotal_tax() {
        return total_tax;
    }

    public void setTotal_tax(int total_tax) {
        this.total_tax = total_tax;
    }
}
