package com.birladeanu.dal.model.embedabble;

import com.birladeanu.dal.model.Item;
import lombok.*;
import org.hibernate.annotations.Parent;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by pavel on 9/24/16.
 */
@Embeddable
@Data
@EqualsAndHashCode(exclude = "item")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class ItemImage {

    @Parent
    protected Item item;

    @Column(nullable = false)
    protected String title;

    @Column(nullable = false)
    protected String filename;

    protected int width;

    protected int height;

}
