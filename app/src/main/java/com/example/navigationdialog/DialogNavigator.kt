package com.example.navigationdialog

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

@Navigator.Name("dialog_fragment") // xmlで使用するカスタムタグ
class DialogNavigator(
    private val fragmentManager: FragmentManager
) : Navigator<DialogNavigator.Destination>() {

    companion object {
        private const val TAG = "dialog"
    }

    override fun navigate(destination: Destination, args: Bundle?, navOptions: NavOptions?, navigatorExtras: Extras?) {
        val fragment = destination.createFragment(args)

        // 結果を返すためにtargetFragmentを設定
        fragment.setTargetFragment(fragmentManager.primaryNavigationFragment, SimpleDialogArgs.fromBundle(args).requestCode)

        fragment.show(fragmentManager, TAG)

        // backStackは変更しないので、BACK_STACK_UNCHANGED
        dispatchOnNavigatorNavigated(destination.id, BACK_STACK_UNCHANGED)
    }

    override fun createDestination(): Destination {
        return Destination(this)
    }

    override fun popBackStack(): Boolean {
        return true
    }

    class Destination(navigator: Navigator<out NavDestination>) : NavDestination(navigator) {

        private var fragmentClass: Class<out DialogFragment>? = null

        override fun onInflate(context: Context, attrs: AttributeSet) {
            super.onInflate(context, attrs)

            // xmlからクラス名を取得
            val a = context.resources.obtainAttributes(attrs, R.styleable.FragmentNavigator)
            a.getString(R.styleable.FragmentNavigator_android_name)?.let { className ->
                fragmentClass = parseClassFromName(context, className, DialogFragment::class.java)
            }
            a.recycle()
        }

        fun createFragment(args: Bundle?): DialogFragment {
            // Fragmentを生成
            val fragment = fragmentClass?.newInstance()
                ?: throw IllegalStateException("fragment class not set")

            // 引数をセット
            args?.let {
                fragment.arguments = it
            }
            return fragment
        }
    }
}
