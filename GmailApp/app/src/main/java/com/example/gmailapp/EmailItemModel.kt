package com.example.gmailapp
data class EmailItemModel(
    val senderName: String,
    val timestamp: String,
    val subject: String,
    val previewText: String,
    var isStarred: Boolean = false,
    val isImportant: Boolean = false
) {
    fun getAvatarLetter(): String {
        return senderName.firstOrNull()?.uppercase() ?: "?"
    }
}