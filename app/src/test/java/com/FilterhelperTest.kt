package com

import com.littlelemon.menu.FilterHelper
import com.littlelemon.menu.FilterType
import com.littlelemon.menu.ProductItem
import org.junit.Test
import org.junit.Assert.assertEquals


class FilterhelperTest {
    private val filterHelper = FilterHelper()
    private val sampleProducts = listOf(
        ProductItem("Cake", 5.99, "Dessert", 0),
        ProductItem("Coke", 1.99, "Drinks", 0),
        ProductItem("Burger", 8.99, "Food", 0),
        ProductItem("Pie", 6.49, "Dessert", 0),
        ProductItem("Juice", 2.99, "Drinks", 0)
    )
    @Test
    fun testFilterAllProducts() {
        // Act
        val result = filterHelper.filterProducts(FilterType.All, sampleProducts)

        // Assert
        assertEquals(sampleProducts, result)
    }





}