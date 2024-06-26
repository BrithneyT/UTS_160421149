package com.example.hobbyapp160421148.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hobbyapp160421148.databinding.FragmentStoryListAdapterBinding
import com.example.hobbyapp160421148.model.Story

class StoryListAdapter : ListAdapter<Story, StoryListAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = inflateBinding(parent)
        return NewsViewHolder(binding)
    }

    private fun inflateBinding(parent: ViewGroup): FragmentStoryListAdapterBinding {
        return FragmentStoryListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
        loadImage(holder, news)
        setItemClickListener(holder, news)
    }

    private fun loadImage(holder: NewsViewHolder, story: Story) {
        Glide.with(holder.itemView.context)
            .load(story.imageUrl)
            .into(holder.binding.showImage)
    }

    private fun setItemClickListener(holder: NewsViewHolder, story: Story) {
        holder.binding.btnRead.setOnClickListener {
            onItemClickListener?.invoke(story)
        }
    }

    private var onItemClickListener: ((Story) -> Unit)? = null

    fun setOnItemClickListener(listener: (Story) -> Unit) {
        onItemClickListener = listener
    }

    inner class NewsViewHolder(val binding: FragmentStoryListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(story: Story) {
            binding.showTitle.text = story.title
            binding.showDescription.text = story.description
            binding.showAuthor.text = story.author
        }
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }
    }
}
