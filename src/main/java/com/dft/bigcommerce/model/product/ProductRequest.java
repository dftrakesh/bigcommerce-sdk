package com.dft.bigcommerce.model.product;

import com.dft.bigcommerce.model.product.image.Image;
import com.dft.bigcommerce.model.product.variant.VariantRequest;
import com.dft.bigcommerce.model.product.video.Video;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductRequest {

    private Integer id;
    private String name;
    private String type;
    private String sku;
    private List<VariantRequest> variants = new ArrayList<>();
    private String description;
    private Double weight;
    private Double price;
    private Double width;
    private Double depth;
    private Double height;
    private Double costPrice;
    private Double retailPrice;
    private Double salePrice;
    private Integer mapPrice;
    private Integer taxClassId;
    private String productTaxCode;
    private List<Integer> categories;
    private Integer brandId;
    private Integer inventoryLevel;
    private Integer inventoryWarningLevel;
    private String inventoryTracking;
    private Double fixedCostShippingPrice;
    private Boolean isFreeShipping;
    private Boolean isVisible;
    private Boolean isFeatured;
    private List<Integer> relatedProducts = new ArrayList<>();
    private String warranty;
    private String binPickingNumber;
    private String layoutFile;
    private String upc;
    private String searchKeywords;
    private String availabilityDescription;
    private String availability;
    private Integer sortOrder;
    private String condition;
    private Boolean isConditionShown;
    private Integer orderQuantityMinimum;
    private Integer orderQuantityMaximum;
    private String pageTitle;
    private List<String> metaKeywords = new ArrayList<>();
    private String metaDescription;
    private Integer viewCount;
    private String preorderReleaseDate;
    private String preorderMessage;
    private Boolean isPreorderOnly;
    private Boolean isPriceHidden;
    private String priceHiddenLabel;
    private String openGraphType;
    private String openGraphTitle;
    private String openGraphDescription;
    private Boolean openGraphUseMetaDescription;
    private Boolean openGraphUseProductName;
    private Boolean openGraphUseImage;
    private String brandNameOrBrandId;
    private String gtin;
    private String mpn;
    private Integer reviewsRatingSum;
    private Integer reviewsCount;
    private Integer totalSold;
    private List<Image> images = new ArrayList<>();
    private List<Video> videos = new ArrayList<>();
}