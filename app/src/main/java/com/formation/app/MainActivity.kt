package com.formation.app

import ComposableView
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import com.formation.app.common.ui.theme.FormationTheme
import com.formation.app.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

var outputDirectoryGlobal: File? = null

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityVM: MainActivityViewModel by viewModels()

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.let {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent) {
        val appLinkAction = intent.action
        val appLinkData: Uri? = intent.data

        println("------------------------->$appLinkAction || $appLinkData || ${appLinkData?.lastPathSegment}")
    }


    override fun onResume() {
        super.onResume()

        mainActivityVM.fetchAll(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outputDirectoryGlobal = getOutputDirectory()

        setContent {
            ComposableView()
        }
        handleIntent(intent = intent)
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let { File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

}
