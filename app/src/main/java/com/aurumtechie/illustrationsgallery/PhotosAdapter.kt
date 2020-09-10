package com.aurumtechie.illustrationsgallery

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class PhotosAdapter(private val context: Context, private val paths: List<String>) :
    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.photo_view, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int = paths.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val imagePath = paths[position]

        (holder.itemView as ImageView).setImageURI(Uri.parse(imagePath))

        holder.itemView.setOnClickListener {
//            context.startActivity(
//                Intent(context, Activity::class.java)
//                    .apply {
//                        putExtra("some name parameter from class", imagePath)
//                    }
//            )
            Log.d("GalleryImage", "$imagePath - was clicked")
        }
    }

}