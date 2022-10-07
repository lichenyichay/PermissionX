package com.example.permissionsx

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * Invisible fragment to embedded into activity for handling permission requests.
 * @author Chay
 * @since 2022/10/7
 */
typealias PermissionCallback = (Boolean, List<String>) -> Unit


class InvisibleFragment : Fragment() {

    private var callback: PermissionCallback? = null

    /**
     * @param cb
     * @param permissions
     */
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermission(permissions, 1)
    }

    /**
     *
     */
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}