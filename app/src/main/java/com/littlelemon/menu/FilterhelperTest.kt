package com.littlelemon.menu

import org.jetbrains.annotations.TestOnly

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
    fun `filterProducts returns all products when FilterType is All`() {
        val result = filterHelper.filterProducts(FilterType.All, sampleProducts)
        assertEquals(sampleProducts, result)
    }

    @Test
    fun `filterProducts filters desserts correctly`() {
        val result = filterHelper.filterProducts(FilterType.Dessert, sampleProducts)
        val expected = sampleProducts.filter { it.category == "Dessert" }
        assertEquals(expected, result)
    }

    @Test
    fun `filterProducts filters drinks correctly`() {
        val result = filterHelper.filterProducts(FilterType.Drinks, sampleProducts)
        val expected = sampleProducts.filter { it.category == "Drinks" }
        assertEquals(expected, result)
    }

}