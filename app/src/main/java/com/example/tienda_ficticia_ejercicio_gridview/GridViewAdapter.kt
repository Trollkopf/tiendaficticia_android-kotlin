package com.example.tienda_ficticia_ejercicio_gridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner

internal class GridViewAdapter(
    private val courseList: List<GridViewModal>,
    private val context: Context
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var courseShopItem: TextView
    private lateinit var courseShopPrice: TextView
    private lateinit var courseShopImage: ImageView

    override fun getCount(): Int {
        return courseList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.shop_item, null)
        }

        courseShopImage = convertView!!.findViewById(R.id.shop_item_image)
        courseShopItem = convertView!!.findViewById(R.id.shop_item_name)
        courseShopPrice = convertView!!.findViewById(R.id.shop_item_price)

        courseShopImage.setImageResource(courseList.get(position).itemImage)
        courseShopItem.setText(courseList.get(position).itemName)
        courseShopPrice.setText(courseList.get(position).itemPrice)

        return convertView

    }

}