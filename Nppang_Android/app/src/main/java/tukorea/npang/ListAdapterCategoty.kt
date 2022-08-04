package tukorea.npang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapterCategory(val itemList: ArrayList<ListLayoutCategory>) :
    RecyclerView.Adapter<tukorea.npang.ListAdapterCategory.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): tukorea.npang.ListAdapterCategory.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_layout_2, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(
        holder: tukorea.npang.ListAdapterCategory.ViewHolder,
        position: Int
    ) {
        holder.post.text = itemList[position].postname
        holder.category.text = itemList[position].category
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val post: TextView = itemView.findViewById(R.id.tv_post_name2)
        val category: TextView = itemView.findViewById(R.id.tv_category2)
    }
}