package com.littlelemon.menu

class FilterHelper {//TODO create a FilterHelperTest and write a unit test for filterProducts

    fun filterProducts(type: FilterType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            FilterType.All -> productsList
            FilterType.Dessert -> productsList.filterDeserts()
            FilterType.Drinks -> productsList.filterDrinks()
            FilterType.Food -> productsList.filterFood()
        }
    }
    private fun List<ProductItem>.filterDeserts(): List<ProductItem> {
        return filter { it.category == "Dessert" }
    }
    private fun List<ProductItem>.filterDrinks(): List<ProductItem> {
        return filter { it.category == "Drinks" }
    }
    private fun List<ProductItem>.filterFood(): List<ProductItem> {
        return filter { it.category == "Food" }
    }

}