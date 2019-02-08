package com.uttampanchasara.baseprojectkotlin.ui.dashboard

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.DocumentSnapshot
import com.uttampanchasara.baseprojectkotlin.R
import kotlinx.android.synthetic.main.list_users.view.*

class UsersListAdapter(private val context: Context) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    private var documents = emptyList<DocumentSnapshot>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_users, p0, false))
    }

    override fun getItemCount(): Int {
        return documents.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {
        viewHolder.bind(documents[p1])
    }

    fun setUsers(documents: List<DocumentSnapshot>) {
        this.documents = documents
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(document: DocumentSnapshot) {
            itemView.txtName.text = document.getString("name")
            itemView.txtEmail.text = document.getString("email")
        }
    }
}