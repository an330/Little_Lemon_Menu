package com.littlelemon.menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.MenuCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object ProductsWarehouse {
    val productsList = mutableListOf(
        ProductItem("Black tea", 3.00, "Drinks", R.drawable.black_tea),
        ProductItem("Green tea", 3.00, "Drinks", R.drawable.green_tea),
        ProductItem("Espresso", 5.00, "Drinks", R.drawable.espresso),
        ProductItem("Cappuccino", 8.00, "Drinks", R.drawable.cappuccino),
        ProductItem("Latte", 8.00, "Drinks", R.drawable.latte),
        ProductItem("Mocha", 10.00, "Drinks", R.drawable.mocha),
        ProductItem("Boeuf bourguignon", 15.00, "Food", R.drawable.boeuf_bourguignon),
        ProductItem("Bouillabaisse", 20.00, "Food", R.drawable.bouillabaisse),
        ProductItem("Lasagna", 15.00, "Food", R.drawable.lasagna),
        ProductItem("Onion soup", 12.00, "Food", R.drawable.onion_soup),
        ProductItem("Salmon en papillote", 25.00, "Food", R.drawable.salmon_en_papillote),
        ProductItem("Quiche Lorraine", 17.00, "Dessert", R.drawable.quiche_lorraine),
        ProductItem("Custard tart", 14.00, "Dessert", R.drawable.custard_tart),
        ProductItem("Croissant", 7.00, "Dessert", R.drawable.croissant),
    )
}

class MainActivity : ComponentActivity() {

    /*val productsList = mutableListOf(
        ProductItem("Black tea", 3.00, "Drinks", R.drawable.black_tea),
        ProductItem("Green tea", 3.00, "Drinks", R.drawable.green_tea),
        ProductItem("Espresso", 5.00, "Drinks", R.drawable.espresso),
        ProductItem("Cappuccino", 8.00, "Drinks", R.drawable.cappuccino),
        ProductItem("Latte", 8.00, "Drinks", R.drawable.latte),
        ProductItem("Mocha", 10.00, "Drinks", R.drawable.mocha),
        ProductItem("Boeuf bourguignon", 15.00, "Food", R.drawable.boeuf_bourguignon),
        ProductItem("Bouillabaisse", 20.00, "Food", R.drawable.bouillabaisse),
        ProductItem("Lasagna", 15.00, "Food", R.drawable.lasagna),
        ProductItem("Onion soup", 12.00, "Food", R.drawable.onion_soup),
        ProductItem("Salmon en papillote", 25.00, "Food", R.drawable.salmon_en_papillote),
        ProductItem("Quiche Lorraine", 17.00, "Dessert", R.drawable.quiche_lorraine),
        ProductItem("Custard tart", 14.00, "Dessert", R.drawable.custard_tart),
        ProductItem("Croissant",  7.00, "Dessert", R.drawable.croissant),
    )*/

    private val productsState: MutableStateFlow<Products> =
        MutableStateFlow(Products(ProductsWarehouse.productsList))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InitUI() }
        Log.d("checkSize","${ProductsWarehouse.productsList.size}")
        //productsList.add(ProductItem("Espresso", 5.00, "Drinks", R.drawable.espresso))
        //ProductsWarehouse.productsList.add(ProductItem("Espresso", 5.00, "Drinks", R.drawable.espresso))
    }

    @Composable
    fun InitUI() {
        val products by productsState.collectAsState()
        ProductsGrid(products = products,onProductClick ={product ->
            startProductActivity(product)
            //ProductsWarehouse.productsList.add(ProductItem("Espresso", 5.00, "Drinks", R.drawable.espresso))


        })
    }

   /* private fun startProductActivity(productItem: ProductItem) {

        val intent = Intent(this,ProductActivity::class.java)
        intent.putExtra("product_item",productItem)
        startActivity(intent)
    }*/
   private fun startProductActivity(productItem: ProductItem) {
       val intent = Intent(this, ProductActivity::class.java).apply {
           putExtra(ProductActivity.KEY_TITLE, productItem.title)
           putExtra(ProductActivity.KEY_PRICE, productItem.price)
           putExtra(ProductActivity.KEY_IMAGE, productItem.image)
           putExtra(ProductActivity.KEY_CATEGORY, productItem.category)
       }
       startActivity(intent)
   }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.products_menu, menu)
        MenuCompat.setGroupDividerEnabled(menu, true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.sorting) {
            val type = when (item.itemId) {
                R.id.sort_a_z -> SortType.Alphabetically
                R.id.sort_price_asc -> SortType.PriceAsc
                R.id.sort_price_desc -> SortType.PriceDesc
                else -> SortType.Alphabetically
            }
            productsState.update {
                Products(
                    SortHelper().sortProducts(
                        type,
                        ProductsWarehouse.productsList
                    )
                )
            }
        } else if (item.groupId == R.id.filter) {
            val type = when (item.itemId) {
                R.id.filter_all -> FilterType.All
                R.id.filter_drinks -> FilterType.Drinks
                R.id.filter_food -> FilterType.Food
                R.id.filter_dessert -> FilterType.Dessert
                else -> FilterType.All
            }
            productsState.update {
                Products(
                    FilterHelper().filterProducts(
                        type,
                        ProductsWarehouse.productsList
                    )
                )
            }
        }
        return true
    }
}