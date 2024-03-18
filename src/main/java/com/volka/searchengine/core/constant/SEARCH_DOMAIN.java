package com.volka.searchengine.core.constant;

public enum SEARCH_DOMAIN {
    
    ACIT("계정과목")
    ,TRDP("거래처");


    private final String domainName;

    SEARCH_DOMAIN(String domainName) {
        this.domainName = domainName;
    }

    public String getName() {
        return domainName;
    }


}
