typealias PermissionCallback = (Boolean, List<String>) -> Unit
class InvisibleFragment : Fragment() {

    pritave var PermissionCallback: ()? = null
    fun requestNow(cb:PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermission(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ){
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()){
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList)}
        }
    }
}