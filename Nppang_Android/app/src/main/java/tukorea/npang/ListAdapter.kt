package tukorea.npang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListAdapter(val itemList: ArrayList<ListLayout>): RecyclerView.Adapter<tukorea.npang.ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tukorea.npang.ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: tukorea.npang.ListAdapter.ViewHolder, position: Int) {
        holder.cont.text = itemList[position].contents
        holder.post.text = itemList[position].postname
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cont: TextView = itemView.findViewById(R.id.tv_post_name)
        val post: TextView = itemView.findViewById(R.id.tv_contents)
    }
}
