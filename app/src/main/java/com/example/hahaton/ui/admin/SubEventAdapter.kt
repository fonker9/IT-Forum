//package com.example.hahaton.ui.admin
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.hahaton.data.model.SubEvent
//import com.example.hahaton.databinding.ItemSubEventBinding
//import java.text.SimpleDateFormat
//import java.util.Locale
//
//class SubEventAdapter(
//    private val items: List<SubEvent>
//) : RecyclerView.Adapter<SubEventAdapter.SubEventViewHolder>() {
//
//    inner class SubEventViewHolder(private val binding: ItemSubEventBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
//
//        fun bind(item: SubEvent) {
//            binding.eventTitle.text = item.title
//            //Добавить еще
//            val favoriteIcon: ImageView = binding.favoriteIcon
//            favoriteIcon.setOnClickListener {
//
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubEventViewHolder {
//        val binding = ItemSubEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return SubEventViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: SubEventViewHolder, position: Int) {
//        holder.bind(items[position])
//    }
//
//    override fun getItemCount(): Int = items.size
//}
package com.example.hahaton.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hahaton.R
import com.example.hahaton.data.model.SubEvent
import com.example.hahaton.databinding.ItemSubEventBinding
import com.example.hahaton.utils.FavoritesManager
import java.text.SimpleDateFormat
import java.util.Locale

class SubEventAdapter(
    private val items: List<SubEvent>
) : RecyclerView.Adapter<SubEventAdapter.SubEventViewHolder>() {

    inner class SubEventViewHolder(private val binding: ItemSubEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

        fun bind(item: SubEvent) {
            binding.eventTitle.text = item.title

            val favoriteIcon: ImageView = binding.favoriteIcon
            val context = binding.root.context

            // Установим иконку в зависимости от статуса
            val isFavorite = FavoritesManager.isFavorite(context, item.id)
            favoriteIcon.setImageResource(
                if (isFavorite) R.drawable.ic_favorite_full else R.drawable.ic_favorite
            )

            // Клик по избранному
            favoriteIcon.setOnClickListener {
                val currentlyFavorite = FavoritesManager.isFavorite(context, item.id)
                if (currentlyFavorite) {
                    FavoritesManager.removeFavorite(context, item.id)
                    favoriteIcon.setImageResource(R.drawable.ic_favorite)
                    Toast.makeText(context, "Удалено из избранного", Toast.LENGTH_SHORT).show()
                } else {
                    FavoritesManager.addFavorite(context, item.id)
                    favoriteIcon.setImageResource(R.drawable.ic_favorite_full)
                    Toast.makeText(context, "Добавлено в избранное", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubEventViewHolder {
        val binding = ItemSubEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubEventViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
