package com.dft.bigcommerce.model.product;

import com.dft.bigcommerce.model.product.image.Image;
import com.dft.bigcommerce.model.product.variant.Variant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Product {

    private Integer id;
    private String name;
    private String type;
    private String sku;
    private String description;
    private Double weight;
    private Double width;
    private Double depth;
    private Double height;
    private Double price;
    private Double costPrice;
    private Double retailPrice;
    private Double salePrice;
    private Double mapPrice;
    private Integer taxClassId;
    private String productTaxCode;
    private Double calculatedPrice;
    private List<Integer> categories = new ArrayList<>();
    private Integer brandId;
    private Integer optionSetId;
    private String optionSetDisplay;
    private Integer inventoryLevel;
    private Integer inventoryWarningLevel;
    private String inventoryTracking;
    private Integer reviewsRatingSum;
    private Integer reviewsCount;
    private Integer totalSold;
    private Double fixedCostShippingPrice;
    private Boolean isFreeShipping;
    private Boolean isVisible;
    private Boolean isFeatured;
    private List<Integer> relatedProducts = new ArrayList<>();
    private String warranty;
    private String binPickingNumber;
    private String layoutFile;
    private String upc;
    private String mpn;
    private String gtin;
    private String searchKeywords;
    private String availability;
    private String availabilityDescription;
    private String giftWrappingOptionsType;
    private List<Integer> giftWrappingOptionsList = new ArrayList<>();
    private List<Variant> variants = new ArrayList<>();
    private Integer sortOrder;
    private String condition;
    private Boolean isConditionShown;
    private Integer orderQuantityMinimum;
    private Integer orderQuantityMaximum;
    private String pageTitle;
    private List<String> metaKeywords = new ArrayList<>();
    private String metaDescription;
    private String dateCreated;
    private String dateModified;
    private Integer viewCount;
    private LocalDateTime preorderReleaseDate;
    private String preorderMessage;
    private Boolean isPreorderOnly;
    private Boolean isPriceHidden;
    private String priceHiddenLabel;
    private CustomUrl customUrl;
    private Integer baseVariantId;
    private String openGraphType;
    private String openGraphTitle;
    private String openGraphDescription;
    private Boolean openGraphUseMetaDescription;
    private Boolean openGraphUseProductName;
    private Boolean openGraphUseImage;
    private List<Image> images;
}
