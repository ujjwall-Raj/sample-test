package com.example;

import java.util.List;

public class MerchantDetails {
    private String icon;
    private String id;
    private String integration_type;
    private boolean is_rewards_enabled;
    private String logo;
    private List<MerchantPolicy> merchant_policies;
    private String name;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntegration_type() {
        return integration_type;
    }

    public void setIntegration_type(String integration_type) {
        this.integration_type = integration_type;
    }

    public boolean isIs_rewards_enabled() {
        return is_rewards_enabled;
    }

    public void setIs_rewards_enabled(boolean is_rewards_enabled) {
        this.is_rewards_enabled = is_rewards_enabled;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<MerchantPolicy> getMerchant_policies() {
        return merchant_policies;
    }

    public void setMerchant_policies(List<MerchantPolicy> merchant_policies) {
        this.merchant_policies = merchant_policies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
