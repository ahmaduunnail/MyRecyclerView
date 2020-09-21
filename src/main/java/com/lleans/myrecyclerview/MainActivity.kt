package com.lleans.myrecyclerview

import Hero
import HeroesData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rvHeroes: RecyclerView
    var list: ArrayList<Hero> = arrayListOf()
    var Mode: String = "ModeList"

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun showSelectedHero(hero: Hero){
        Toast.makeText(this, "Kamu memilih " + hero.name_hero, Toast.LENGTH_SHORT).show()
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            R.id.action_list -> {
                title = "List View"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Grid View"
                showRecyclerGrid()
            }
            R.id.action_card -> {
                title = "Card View"
                showCardView()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(title)

        rvHeroes = findViewById(R.id.rv_hero)
        rvHeroes.setHasFixedSize(true)

        list.addAll(HeroesData.ListData)
        showRecyclerList()
    }

    fun showRecyclerList(){
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdpter = HeroAdapter(list)
        rvHeroes.adapter = listHeroAdpter

        listHeroAdpter.setOnClickCallback(object :
        HeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }
    private fun showRecyclerGrid() {
        rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val grid = GridAdapter(list)
        rvHeroes.adapter = grid

        grid.setOnItemClickCallback(object :
        GridAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun showCardView(){
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val card = CardAdapter(list)
        rvHeroes.adapter = card
    }
    private fun setTitle(title: String){
        if(supportActionBar != null){
            (supportActionBar as ActionBar).title = title
        }
    }
}