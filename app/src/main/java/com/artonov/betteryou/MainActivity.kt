package com.artonov.betteryou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.artonov.betteryou.adapter.ListBookAdapter
import com.artonov.betteryou.databinding.ActivityMainBinding
import com.artonov.betteryou.model.Book

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val list = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvBooks.setHasFixedSize(true)
        list.addAll(getListBooks())
        showRecyclerList()
    }

    private fun getListBooks(): ArrayList<Book> {
        val dataName = resources.getStringArray(R.array.book_title)
        val dataAuthor = resources.getStringArray(R.array.book_author)
        val dataYear = resources.getStringArray(R.array.book_year)
        val dataDescription = resources.getStringArray(R.array.book_description)
        val dataPhoto = resources.getStringArray(R.array.book_photo)
        val listBook = ArrayList<Book>()

        for (i in dataName.indices) {
            val book = Book(dataName[i], dataAuthor[i], dataYear[i], dataDescription[i], dataPhoto[i])
            listBook.add(book)
        }
        return listBook
    }

    private fun showRecyclerList() {
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        val listBookAdapter = ListBookAdapter(list)
        binding.rvBooks.adapter = listBookAdapter

        listBookAdapter.setOnItemClickCallback(object : ListBookAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                showSelectedBook(data)
            }
        })
    }

    private fun showSelectedBook(book: Book) {
        Toast.makeText(this, "Kamu memilih " + book.bookName, Toast.LENGTH_SHORT).show()
    }
}