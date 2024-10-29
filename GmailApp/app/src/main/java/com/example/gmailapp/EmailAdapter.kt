package com.example.gmailapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class EmailAdapter(private val emailList: List<EmailItemModel>) :
    RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    private val avatarColors = listOf(
        Color.parseColor("#F44336"),
        Color.parseColor("#E91E63"),
        Color.parseColor("#9C27B0"),
        Color.parseColor("#673AB7"),
        Color.parseColor("#3F51B5"),
        Color.parseColor("#2196F3"),
        Color.parseColor("#03A9F4"),
        Color.parseColor("#00BCD4"),
        Color.parseColor("#009688"),
        Color.parseColor("#4CAF50")
    )

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarCard: CardView = itemView.findViewById(R.id.ava)
        val avatarText: TextView = itemView.findViewById(R.id.avatar_text)
        val senderName: TextView = itemView.findViewById(R.id.sender_name)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        val subject: TextView = itemView.findViewById(R.id.subject)
        val previewText: TextView = itemView.findViewById(R.id.preview_text)
        val star: ImageView = itemView.findViewById(R.id.star)
        val label: ImageView = itemView.findViewById(R.id.label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]

        // Set avatar text and background color
        holder.avatarText.text = email.getAvatarLetter()
        holder.avatarCard.setCardBackgroundColor(avatarColors[position % avatarColors.size])

        // Set basic email information
        holder.senderName.text = email.senderName
        holder.timestamp.text = email.timestamp
        holder.subject.text = email.subject
        holder.previewText.text = email.previewText

        holder.label.visibility = if (email.isImportant) View.VISIBLE else View.GONE
        updateStarIcon(holder.star, email.isStarred)

        holder.star.setOnClickListener {
            val newStarredState = !email.isStarred
            updateStarIcon(holder.star, newStarredState)

            email.isStarred= newStarredState

            val message = if (newStarredState) {
                "Đã đánh dấu sao cho email từ ${email.senderName}"
            } else {
                "Đã bỏ đánh dấu sao cho email từ ${email.senderName}"
            }
            Toast.makeText(holder.itemView.context, message, Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Đã chọn email từ ${email.senderName}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateStarIcon(starView: ImageView, isStarred: Boolean) {
        starView.setImageResource(
            if (isStarred) R.drawable.ic_star_mark
            else R.drawable.ic_star
        )
    }

    override fun getItemCount(): Int = emailList.size
}