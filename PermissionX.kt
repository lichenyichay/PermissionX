package com.example.permissionsx

import androidx.fragment.app.FragmentActivity

/**
 * Basic interfaces for developers to use PermissionX functions.
 * @author Chay
 * @since 2022/10/7
 */
object PermissionX {

    private const val TAG = "com.example.permissionsx.InvisibleFragment"

    /**
     * @param activity
     * @param permissions
     * @param callback
     */

    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)

    }
}