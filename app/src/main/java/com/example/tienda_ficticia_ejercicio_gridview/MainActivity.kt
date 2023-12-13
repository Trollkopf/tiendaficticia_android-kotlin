package com.example.tienda_ficticia_ejercicio_gridview

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //VARIABLES
    lateinit var courseView: GridView
    lateinit var courseList: List<GridViewModal>
    var shopTrolley = mutableListOf<String>()

    //CREAMOS LOS ARTÍCULOS DE LA TIENDA
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseView = findViewById(R.id.items_tienda)
        courseList = ArrayList<GridViewModal>()

        courseList += GridViewModal(R.string.abrigo, R.drawable.abrigo, R.string.p_abrigo)
        courseList += GridViewModal(R.string.botas, R.drawable.botas, R.string.p_botas)
        courseList += GridViewModal(
            R.string.calcetines,
            R.drawable.calcetines,
            R.string.p_calcetines
        )
        courseList += GridViewModal(R.string.camisa, R.drawable.camisa, R.string.p_camisa)
        courseList += GridViewModal(R.string.gabardina, R.drawable.gabardina, R.string.p_gabardina)
        courseList += GridViewModal(R.string.gorra, R.drawable.gorra, R.string.p_gorra)
        courseList += GridViewModal(R.string.guantes, R.drawable.guantes, R.string.p_guantes)
        courseList += GridViewModal(
            R.string.pantalones,
            R.drawable.pantalones,
            R.string.p_pantalones
        )
        courseList += GridViewModal(R.string.zapatos, R.drawable.zapatos, R.string.p_zapatos)

        val courseAdapter = GridViewAdapter(courseList = courseList, this@MainActivity)

        registerForContextMenu(courseView)

        courseView.adapter = courseAdapter

        courseView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this@MainActivity, courseList[i].itemName, Toast.LENGTH_SHORT).show()
        }
    }

    // CONTEXT MENU
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menuInflater.inflate(R.menu.context_menu, menu)
    }

    // MENU CARRITO COMPRA
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.shop_menu, menu)
        return true
    }

    // CARGAR CARRITO COMPRA
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.trolleyBtn -> {
                val trolley: AlertDialog.Builder = AlertDialog.Builder(this)
                var infoTrolley: String = ""
                var priceTrolley: Double = 0.00

                trolley.setTitle("Carrito de compra")

                for (i in shopTrolley) {
                    when (i) {
                        "2131755035" -> {
                            infoTrolley += "- Abrigo \n"
                            priceTrolley += 40.00
                        }

                        "2131755185" -> {
                            infoTrolley += "- Botas \n"
                            priceTrolley += 35.00
                        }

                        "2131755047" -> {
                            infoTrolley += "- Calcetines \n"
                            priceTrolley += 5.00
                        }

                        "2131755048" -> {
                            infoTrolley += "- Camisa \n"
                            priceTrolley += 25.00
                        }

                        "2131755186" -> {
                            infoTrolley += "- Gabardina \n"
                            priceTrolley += 50.00
                        }

                        "2131755058" -> {
                            infoTrolley += "- Gorra \n"
                            priceTrolley += 10.00
                        }

                        "2131755059" -> {
                            infoTrolley += "- Guantes \n"
                            priceTrolley += 15.00
                        }

                        "2131755170" -> {
                            infoTrolley += "- Pantalones \n"
                            priceTrolley += 30.00
                        }

                        "2131755184" -> {
                            infoTrolley += "- Zapatos \n"
                            priceTrolley += 20.00
                        }

                    }
                }

                infoTrolley += "\n TOTAL: " + priceTrolley.toString() + "0 €"

                trolley.setMessage(infoTrolley)
                trolley.show()

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // AÑADIR O QUITAR ARTÍCULOS AL CARRITO
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo

        val itemPosition = info.position
        val itemName = courseList[itemPosition].itemName.toString()

        when (item!!.itemId) {
            R.id.add -> {
                shopTrolley.add(itemName)

                for (i in shopTrolley) {
                    println(i)
                }

                Toast.makeText(this@MainActivity, "Añadido al carrito", Toast.LENGTH_SHORT).show()
            }

            R.id.delete -> {

                if (itemName in shopTrolley) {
                    shopTrolley.remove(itemName)

                    for (i in shopTrolley) {
                        println(i)
                    }

                    Toast.makeText(this@MainActivity, "Eliminado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "No tienes ese artículo", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        return super.onContextItemSelected(item)
    }
}