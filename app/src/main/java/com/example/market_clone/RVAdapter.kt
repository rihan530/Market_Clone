package com.example.market_clone


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.market_clone.databinding.RecyclerviewProductItemBinding
import java.text.NumberFormat
import java.util.Locale


class RVAdapter(private val productList: MutableList<ProductInfo>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    val moneyFormat: NumberFormat = NumberFormat.getInstance(Locale.getDefault())

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val binding: RecyclerviewProductItemBinding = RecyclerviewProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.productName.text = productList[position].productName
        holder.productImage.setImageResource(productList[position].image)
        holder.productLocation.text = productList[position].location
        holder.productPrice = moneyFormat.format(productList[position].price).toString()
        holder.productChat.text = productList[position].chat.toString()
        holder.productLike.text = productList[position].like.toString()
        if (productList[position].isLiked) {
            holder.likeIcon.setImageResource(R.drawable.ic_fav_heart)
        } else {
            holder.likeIcon.setImageResource(R.drawable.ic_heart)
        }

//        holder.bind(productList[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(private val binding: RecyclerviewProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        val productName = binding.productName
        val productImage = binding.productImage
        val productLocation = binding.location
        var productPrice = "${binding.price}원"
        val productChat = binding.chatCount
        val productLike = binding.likeCount
        val likeIcon = binding.likeIcon

        init {
            productImage.clipToOutline = true

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClick?.onClick(it, position)
                }
            }
        }
    }
}