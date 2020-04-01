package com.example.retrofitdemo.Activity
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitdemo.Fragments.MainFragment
import com.example.retrofitdemo.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }
    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            snackbar = Snackbar.make(framefragment, getString(R.string.internet), Snackbar.LENGTH_LONG)
            snackbar?.show()
        } else {

            snackbar?.dismiss()
            supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.framefragment, MainFragment()).commit()

        }
    }
}





