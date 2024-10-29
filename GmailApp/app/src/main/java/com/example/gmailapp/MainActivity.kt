package com.example.gmailapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var emailAdapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Setup edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        setupRecyclerView()
        loadEmailData()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun loadEmailData() {
        val emailList = listOf(
            EmailItemModel(
                senderName = "Edurila.com",
                timestamp = "12:34 PM",
                subject = "$19 Only (First 10 spots)",
                previewText = "Are you looking to learn web design?",
                isImportant = true
            ),
            EmailItemModel(
                senderName = "Chris Abad",
                timestamp = "11:22 AM",
                subject = "Help make Campaign Monitor better",
                previewText = "Let us know your thoughts! No Images",
                isImportant = true
            ),
            EmailItemModel(
                senderName = "Tuto.com",
                timestamp = "11:04 AM",
                subject = "8h de formation gratuite et les nouvea...",
                previewText = "Photoshop, SEO, Blender, CSS, WordPress",
                isImportant = true
            ),
            EmailItemModel(
                senderName = "Support Team",
                timestamp = "10:26 AM",
                subject = "Société Ovh : suivi de vos services - hp...",
                previewText = "SAS OVH - http://www.ovh.com 2 rue K...",
                isImportant = true
            ),
            EmailItemModel(
                senderName = "Matt from Ionic",
                timestamp = "10:00 AM",
                subject = "The New Ionic Creator Is Here!",
                previewText = "Announcing the all-new Creator, build...",
                isImportant = true
            ),

            EmailItemModel(
                senderName = "Netflix",
                timestamp = "9:45 AM",
                subject = "New Shows Added to Your List",
                previewText = "Check out the latest additions to your...",
                isImportant = true
            ),
            EmailItemModel(
                senderName = "LinkedIn Jobs",
                timestamp = "9:30 AM",
                subject = "10 New Job Matches for You",
                previewText = "Based on your profile, we found these...",
                isImportant = false
            ),
            EmailItemModel(
                senderName = "GitHub",
                timestamp = "9:15 AM",
                subject = "Security Alert: New sign-in detected",
                previewText = "We noticed a new sign-in to your account...",
                isImportant = true
            ),
            EmailItemModel(
                senderName = "Amazon.com",
                timestamp = "9:00 AM",
                subject = "Your Order Has Been Shipped",
                previewText = "Track your package: Your recent order...",
                isImportant = false
            ),
            EmailItemModel(
                senderName = "Spotify Premium",
                timestamp = "8:45 AM",
                subject = "Your Weekly Music Mix is Ready",
                previewText = "Discover new songs based on your taste...",
                isImportant = false
            )
        )
        emailAdapter = EmailAdapter(emailList)
        recyclerView.adapter = emailAdapter
    }
}