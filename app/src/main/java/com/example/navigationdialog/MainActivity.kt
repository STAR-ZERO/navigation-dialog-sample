package com.example.navigationdialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.plusAssign
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host)

        // 今回追加したNavigatorをnavigatorProviderに追加する
        val dialogNavigator = DialogNavigator(nav_host.childFragmentManager)
        navController.navigatorProvider += dialogNavigator

        // navController.setGraph(R.navigation.navigation) を使ってると回転したときにクラッシュする
        // NavController#restoreState でidがあるときはgraphを設定しようとするが、まだカスタムNavigatorが設定されてないため
        val graph = navController.navInflater.inflate(R.navigation.navigation)
        navController.graph = graph
    }
}
