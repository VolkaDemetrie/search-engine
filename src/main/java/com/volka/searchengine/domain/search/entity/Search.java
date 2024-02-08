package com.volka.searchengine.domain.search.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Search {

    @Id
    private Long id;
    private String word;
    private int rank;

}
