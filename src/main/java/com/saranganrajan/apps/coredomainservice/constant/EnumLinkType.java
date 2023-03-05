package com.saranganrajan.apps.coredomainservice.constant;

public enum EnumLinkType {
    I("INSURED"),
    O("OWNER");

    public String getLinkType() {
        return linkType;
    }

    public final String linkType;

    EnumLinkType(String linkType) {
        this.linkType = linkType;
    }
}
