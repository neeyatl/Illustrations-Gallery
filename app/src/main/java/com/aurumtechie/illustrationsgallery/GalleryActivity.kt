package com.aurumtechie.illustrationsgallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        galleryRecyclerView.setHasFixedSize(true)
        galleryRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        galleryRecyclerView.adapter =
            PhotosAdapter(this, ContentHelper.getAllImagesPaths(this))
    }

}