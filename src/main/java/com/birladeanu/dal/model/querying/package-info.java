/**
 * Created by pavel on 8/28/16.
 */
@org.hibernate.annotations.NamedQueries({
    @org.hibernate.annotations.NamedQuery(
        name = "findItemsOrderByName",
        query = "select i from Item i order by i.name asc"
    )
    ,
    @org.hibernate.annotations.NamedQuery(
            name = "findItemBuyInitialPriceWithCurrencyGreaterThan",
            query = "select i from Item i where i.initialPriceWithCurrency.value > :price",
            timeout = 60,
            comment = "Custom SQL comment"
    )
})

package com.birladeanu.dal.model.querying;